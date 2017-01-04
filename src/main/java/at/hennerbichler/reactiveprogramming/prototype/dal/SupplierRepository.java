package at.hennerbichler.reactiveprogramming.prototype.dal;

import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
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


    private JdbcTemplate jdbcTemplate;

    private RowMapper<Supplier> supplierMapper = ((rs, rowNum) -> new Supplier(rs.getString("name"), rs.getString("inventoryApi"));


    @Autowired
    public SupplierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Supplier> findForProduct(String productName) {
        return jdbcTemplate.query("SELECT Supplier.name, inventoryApi FROM Supplier " +
                            "INNER JOIN Product ON Product.supplierId = Supplier.id " +
                            "WHERE Product.name LIKE '%?%'", new Object[]{ productName}, supplierMapper);
    }
}
