package at.hennerbichler.reactiveprogramming.prototype.dal;

import at.hennerbichler.reactiveprogramming.prototype.JdbcObservableTemplate;
import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by markush on 1/4/17.
 */

@Repository
public class SupplierRepository {


    private JdbcObservableTemplate jdbcTemplate;

    private RowMapper<Supplier> supplierMapper = ((rs, rowNum) ->
            new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("inventoryApi")));


    @Autowired
    public SupplierRepository(JdbcObservableTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Observable<Supplier> findForProduct(String productName) {

        return jdbcTemplate.queryForObservable("SELECT Supplier.id, Supplier.name, inventoryApi FROM Supplier " +
                            "INNER JOIN Product ON Product.supplierId = Supplier.id " +
                            "WHERE Product.name LIKE ?", new Object[]{ "%" + productName + "%"}, supplierMapper);
    }
}
