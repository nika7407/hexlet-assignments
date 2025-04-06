package exercise.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;
import java.sql.SQLException;

public class ProductsRepository extends BaseRepository {

    public static void save(Product product) throws SQLException {
        var dataSource = BaseRepository.dataSource;
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";

        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getPrice());
            stmt.executeUpdate();

            // Установка ID из сгенерированных ключей
            try (var keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    product.setId(keys.getLong(1));
                }
            }
        }
    }

    public static List<Product> getEntities() throws SQLException {
        var dataSource = BaseRepository.dataSource;
        String sql = "SELECT id, title, price FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                int price = rs.getInt("price");

                Product product = new Product(title, price);
                product.setId(id); // ← добавлено
                products.add(product);
            }
        }
        return products;
    }

    public static Optional<Product> find(long id) throws SQLException {
        var dataSource = BaseRepository.dataSource;
        String sql = "SELECT id, title, price FROM products WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    int price = rs.getInt("price");

                    Product product = new Product(title, price);
                    product.setId(rs.getLong("id"));
                    return Optional.of(product);
                }
            }
        }
        return Optional.empty();
    }
}

