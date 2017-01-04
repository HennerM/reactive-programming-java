package at.hennerbichler.reactiveprogramming.prototype;

import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by markush on 1/4/17.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Quering tables");
        jdbcTemplate.query("SELECT * FROM Supplier",
                (rs, rowNum) -> new Supplier(rs.getString("name"), rs.getString("inventoryApi")))
                .forEach(supplier -> log.info(supplier.toString()));

    }
}
