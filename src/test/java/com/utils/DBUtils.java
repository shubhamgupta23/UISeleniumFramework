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

    private static final boolean DB_ENABLED = Boolean.parseBoolean(PropertyUtils.getProperty("DB.ENABLED"));

    private static Connection conn;

    private DBUtils() {
    }

    private static boolean isDBEnabled(){
        return DB_ENABLED;
    }

    public static Connection getConnection() {
        if(!isDBEnabled()){
            return null;
        }
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
        if(!isDBEnabled()){
            return Collections.emptyMap();
        }
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
        if(!isDBEnabled()){
            return null;
        }
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
        if(isDBEnabled()){
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close connection", e);
            }
        }
    }

    public static List<Map<String, Object>> getAllDBRowsAsListOfMap(String query) {
        if(!isDBEnabled()){
            return Collections.emptyList();
        }
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (!rs.next()) {
                return Collections.emptyList();
            }
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            List<Map<String, Object>> rowlist = new ArrayList<>();
            do{
                Map<String, Object> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(resultSetMetaData.getColumnName(i), rs.getObject(i));
                }
                rowlist.add(map);
            }while (rs.next());
            return rowlist;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get the list of rows : " + e);
        }
    }

    public static int updateQuery(String query) {
        if(!isDBEnabled()){
            return 0;
        }
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to update via query :"+ex);
        }
    }

    public static void storeResultInDB(String testname, String status){
        if(isDBEnabled()){
            String query = "select count(*) as count from test_results where test_name = '"+testname+"';";
            int count = Integer.parseInt(getColumnByName(query,"count"));
            if(count==0){
                updateQuery("INSERT INTO automation_db.test_results (test_name, status) VALUES('"+testname+"', '"+status+"');");
            }else{
                updateQuery("update test_results set status = '"+status+"' where test_name = '"+testname+"';");
            }
        }
    }

}