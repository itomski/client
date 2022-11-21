package de.lubowiecki.client.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDbRepository {

    private static final String TABLE = "products";

    public ProductDbRepository() throws SQLException {
        createTable();
    }

    public List<Product> find() throws SQLException {

        final String SQL = "SELECT * FROM " + TABLE;

        List<Product> products = new ArrayList<>();

        try(Connection con = DatabaseUtils.getConnection(); Statement stmt = con.createStatement()) {

            if(stmt.execute(SQL)) {
                ResultSet results = stmt.getResultSet();
                while(results.next())
                    products.add(create(results));
            }
        }

        return products;
    }

    public Product findById(int id) {
        throw new UnsupportedOperationException("Noch nicht implementiert");
    }

    public boolean save(Product p) {
        if(p.getId() > 0) {
            return update(p);
        }
        return insert(p);
    }

    private boolean insert(Product p) {
        throw new UnsupportedOperationException("Noch nicht implementiert");
    }

    private boolean update(Product p) {
        throw new UnsupportedOperationException("Noch nicht implementiert");
    }

    public boolean delete(Product p) {
        return delete(p.getId());
    }

    public boolean delete(int id) {
        throw new UnsupportedOperationException("Noch nicht implementiert");
    }

    private Product create(ResultSet result) throws SQLException {
        Product p = new Product();
        p.setId(result.getInt("id"));
        p.setName(result.getString("name"));
        p.setDescription(result.getString("description"));
        p.setAmount(result.getInt("amount"));
        p.setPrice(result.getDouble("price"));
        p.setCreatedAt(result.getDate("created_at").toLocalDate());
        return p;
    }

    private void createTable() throws SQLException {

        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                                + "id INTEGER, "
                                + "name TEXT NOT NULL, "
                                + "description TEXT, "
                                + "amount INTEGER, "
                                + "price REAL, "
                                + "created_at TEXT, "
                                + "PRIMARY KEY (id AUTOINCREMENT))";

        try(Connection con = DatabaseUtils.getConnection(); Statement stmt = con.createStatement()) {
            stmt.execute(SQL);
        }
    }
}
