package com.utils;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "automation_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin@123";

    private static final String DB_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;

    private static Connection conn;

    private DBUtils() {
    }

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Getting SQL exception provided wrong db URL " + e);
        }
        return conn;
    }

    public static Map<String, Object> getSingleRowAsMap(String query) {
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                return Collections.emptyMap();
            }
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(resultSetMetaData.getColumnName(i), rs.getObject(i));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get the row as map : " + e);
        }
    }

    public static String getColumnByName(String query, String column) {
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(column);
            }
            return "";
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get a column value by name : " + e);
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close connection", e);
        }
    }

    public static List<Map<String, Object>> getAllDBRowsAsListOfMap(String query) {
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                return Collections.emptyList();
            }
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            List<Map<String, Object>> rowlist = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(resultSetMetaData.getColumnName(i), rs.getObject(i));
                }
                rowlist.add(map);
            }
            return rowlist;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get the list of rows : " + e);
        }
    }

    public static int updateQuery(String query) {
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to update via query :"+ex);
        }
    }

}