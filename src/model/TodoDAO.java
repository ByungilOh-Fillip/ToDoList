package model;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class TodoDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/todoList";
    private static final String USER = "${USERNAME}";
    private static final String PASSWORD = "${PASSWORD}";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } // DB connect API

    public void createTodo(TodoDTO todo) {
        String sql = "INSERT INTO todo (write_at, todo_date, content) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(todo.getWriteAt()));
            pstmt.setDate(2, Date.valueOf(todo.getTodoDate()));
            pstmt.setString(3, todo.getContent());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // add_Todo

    public List<TodoDTO> Daily_ToDo(Date date) {
        String sql = "SELECT * FROM todo WHERE todo_date = ?";
        List<TodoDTO> todos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TodoDTO todo = new TodoDTO();
                todo.setId(rs.getInt("id"));
                todo.setWriteAt(rs.getTimestamp("write_at").toLocalDateTime());
                todo.setTodoDate(rs.getDate("todo_date").toLocalDate());
                todo.setContent(rs.getString("content"));

                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    } // find_TodayToDo

    public List<TodoDTO> selectDate(Date date) {
        String sql = "SELECT * FROM todo WHERE todo_date = ?";
        List<TodoDTO> todos = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TodoDTO todo = new TodoDTO();
                todo.setId(rs.getInt("id"));
                todo.setWriteAt(rs.getTimestamp("write_at").toLocalDateTime());
                todo.setTodoDate(rs.getDate("todo_date").toLocalDate());
                todo.setContent(rs.getString("content"));

                todos.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    } // find_Date_Todo_List

    public void updateTodoContent(int id, String newContent) {
        String sql = "UPDATE todo SET content = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newContent);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // Update_content_id_Todo

    public void deleteTodo(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // Delete_column_id_Todo
}
