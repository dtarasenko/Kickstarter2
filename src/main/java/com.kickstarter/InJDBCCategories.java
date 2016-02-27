package com.kickstarter;

import java.sql.*;

/**
 * Created by dtarasenko on 2/27/2016.
 */
public class InJDBCCategories implements Categories {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Что-то не так с загрузкой драйвера", e);
        }
    }


    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kickstarter", "postgres", "root");
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void add(Category category) {

    }

    @Override
    public String[] getCategories() {
        return new String[0];
    }

    @Override
    public Category get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

}
