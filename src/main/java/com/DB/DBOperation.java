package com.DB;
import java.sql.*;

public class DBOperation {
    private final Connection conn;

    public DBOperation(UserConfig userConfig) throws SQLException {
        String url = String.format("jdbc:mysql://%s:3306/%s?&useSSL=false&serverTimezone=UTC", userConfig.getHost(), userConfig.getDatabase());
        conn = DriverManager.getConnection(url, userConfig.getUserName(), userConfig.getPassword());
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeInsert(String name, String sex, String age) throws SQLException {
        String sql = "INSERT INTO student (Name, Sex, Age) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setString(3, age);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        }
    }
}
