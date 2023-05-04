package com.DB;
import java.sql.*;
import java.util.List;

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

    public void executeInsert(List<Student> studentList) throws SQLException {
        String sql = "INSERT INTO student (Name, Sex, Age) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for(Student student : studentList)
            {
                stmt.setString(1, student.getName());
                stmt.setString(2, student.getSex());
                stmt.setString(3, student.getAge());
                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }
}
