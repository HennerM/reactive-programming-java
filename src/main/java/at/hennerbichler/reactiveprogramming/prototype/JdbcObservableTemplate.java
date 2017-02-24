package at.hennerbichler.reactiveprogramming.prototype;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class JdbcObservableTemplate extends JdbcTemplate {
    @Autowired
    public JdbcObservableTemplate(DataSource dataSource) {
        super(dataSource);
    }

    private static class ObservableResultSetMapper<T> extends RowCountCallbackHandler {

        private final ObservableEmitter<T> emitter;
        private final RowMapper<T> rowMapper;

        private ObservableResultSetMapper(ObservableEmitter<T> emitter, RowMapper<T> rowMapper) {
            this.emitter = emitter;
            this.rowMapper = rowMapper;
        }

        @Override
        protected void processRow(ResultSet rs, int rowNum) {
            try {
                emitter.onNext(rowMapper.mapRow(rs, rowNum));
                if (rs.isLast()) {
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        }
    }
    public <T> Observable<T> queryForObservable(String sql,
                                                Object[] args,
                                                final RowMapper<T> rowMapper)
            throws DataAccessException {
        return Observable.create(emitter -> {
            query(sql, args, new ObservableResultSetMapper<>(emitter, rowMapper));
        });
    }
}
