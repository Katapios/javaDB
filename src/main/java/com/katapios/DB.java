package com.katapios;

import java.sql.*;

public class DB {
    private final String HOST = "localhost";
    private final String PORT = "6033";
    private final String DB_NAME = "web";
    private final String LOGIN = "db_user";
    private final String PASS = "db_user_pass";

    private Connection dbConn;


    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?autoReconnect=true&amp&useSSL=false";
        //Class.forName("com.mysql.cj.jdbc.Driver");
        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public void createTable() throws SQLException, ClassNotFoundException {

        String sqlCreateUsers = "CREATE TABLE IF NOT EXISTS users"
                + " (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), password VARCHAR(100))"
                + " ENGINE=MYISAM";

        String sqlCreateArticles = "CREATE TABLE IF NOT EXISTS articles"
                + " (id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,"
                + " title VARCHAR(50),"
                + " text TEXT,"
                + " date VARCHAR(50),"
                + " author VARCHAR(30))"
                + " ENGINE=MYISAM";

        Statement statement = getDbConnection().createStatement();

        statement.executeUpdate(sqlCreateUsers);
        statement.executeUpdate(sqlCreateArticles);


    }

    public void insertArticle(String title, String text, String date, String author) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `articles` (title,text,date,author) VALUES (?,?,?,?)";

        PreparedStatement prst = getDbConnection().prepareStatement(sql);
        prst.setString(1, title);
        prst.setString(2, text);
        prst.setString(3, date);
        prst.setString(4, author);

        prst.executeUpdate();
    }

    public void getArticles(String table) throws SQLException, ClassNotFoundException {
        //String sql = "SELECT * FROM " + table + " WHERE `title` LIKE '%artic%' OR `id` = 4 ORDER BY `date` ASC LIMIT 1 OFFSET 2";
        String sql = "SELECT * FROM " + table;
        Statement statement = getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("title"));
            System.out.println(resultSet.getString("text"));
            System.out.println(resultSet.getString("author"));
            System.out.println("");
        }

    }

    public void updateArticles() throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `articles` SET `title` = ? WHERE `id` = 3";

        PreparedStatement prst = getDbConnection().prepareStatement(sql);
        prst.setString(1, "new updated article");

        prst.executeUpdate();
    }

    public void deleteEntry() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `articles` WHERE `id` = 3";

        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

    public void deleteTable() throws SQLException, ClassNotFoundException {
        String sql = "DROP TABLE `articles`";

        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);
    }

}