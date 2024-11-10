package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MassageDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/todoList";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } // DB connect API

    public void sendMassage(String massage) {
        String sql = "INSERT INTO massage (message,write_at) VALUES ( ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, massage);
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // add_comment
}
