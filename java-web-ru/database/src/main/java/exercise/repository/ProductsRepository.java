package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        var productInDb = find(product.getId());

        if (productInDb.isPresent()) {
            update(product);
        } else {
            insert(product);
        }
    }

    public static void update(Product product) throws SQLException {
        var sql = """
            UPDATE products
            SET title = ?, price = ?
            WHERE id = ?
            """;

        try (
            var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static void insert(Product product) throws SQLException {
        var sql = "INSERT INTO products (title, price) VALUES (?, ?)";

        try (
            var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                var id = generatedKeys.getLong(1);
                product.setId(id);
            }
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        if (id == null) {
            return Optional.empty();
        }

        var sql = "SELECT * FROM products WHERE id = ?";

        try (
            var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var product = new Product(
                    resultSet.getString("title"),
                    resultSet.getInt("price")
                );
                product.setId(id);
                return Optional.of(product);
            }

            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        try (
            var connection = dataSource.getConnection();
            var statement = connection.createStatement();
        ) {
            var resultSet = statement.executeQuery("SELECT * FROM products");
            var products = new ArrayList<Product>();

            while (resultSet.next()) {
                var product = new Product(
                    resultSet.getString("title"),
                    resultSet.getInt("price")
                );
                product.setId(resultSet.getLong("id"));
                products.add(product);
            }

            return products;
        }
    }
    // END
}
