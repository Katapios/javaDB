package com.katapios;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DB db = new DB();
        try {
            db.isConnected();
            db.createTable();
            //db.insertArticle("New article","full text","12.08.2020","Admin");
            //db.insertArticle("New article2","full text2","12.08.2020","User");
            //db.insertArticle("New article3","full text3","12.08.2020","Some Man");
            //db.updateArticles();
            //db.deleteEntry();
            //db.deleteTable();
            db.getArticles("articles");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
