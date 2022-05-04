package ru.whitegray.spring.web;

import java.sql.*;

public class WorkWithDataDBs {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/cinema";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;
    String sql;

    public void mergeFilms() {
        System.out.println("\n\n\nShow a merged films\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            sql = "SELECT * FROM SEANSES";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                float price = rs.getFloat("price");
                Time time_begin_seans = rs.getTime("time_begin_seans");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", title: " + title);
                System.out.print(", duration: " + duration);
                System.out.print(", price: " + price);
                System.out.println(", time_begin_seans: " + time_begin_seans + "---" + (time_begin_seans.getTime()+duration*100L));
            }

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }



}
