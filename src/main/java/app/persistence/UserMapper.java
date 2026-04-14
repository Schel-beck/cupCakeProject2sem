package app.persistence;

import app.entities.Users;
import app.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper {

    private ConnectionPool connectionPool;
    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public static Users login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = """
                SELECT *
                FROM users u
                WHERE u.email = ? AND u.password = ?
                """;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    int id = resultSet.getInt("user_id");
                    int balance = resultSet.getInt("balance");
                    boolean is_admin = resultSet.getBoolean("is_admin");
                    return new Users(null, email, password, id, balance, is_admin);
                }
                else{
                    return null;
                }

            }
        } catch (SQLException e) {
            throw new DatabaseException("login fejlede", e.getMessage());
        }
    }

    public static void createUser(String name, String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Kunne ikke oprette bruger", e.getMessage());
        }
    }
}

