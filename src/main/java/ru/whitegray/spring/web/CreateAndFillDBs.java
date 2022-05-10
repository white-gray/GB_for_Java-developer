package ru.whitegray.spring.web;

import java.sql.*;

public class CreateAndFillDBs {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/cinema";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;
    String sql;

    public void createDB_FILMS() {
        System.out.println("\n\n\nCreate table FILMS\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Created table in given database...\n");

            sql = "CREATE TABLE FILMS " +
                    "(id INTEGER not NULL, " +
                    " title VARCHAR(255), " +
                    " duration time not NULL, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            sql = "INSERT INTO FILMS VALUES (1, 'One', '01:00:0')";
            stmt.executeLargeUpdate(sql);
            sql = "INSERT INTO FILMS VALUES (2, 'Two', '1:30:0')";
            stmt.executeLargeUpdate(sql);
            sql = "INSERT INTO FILMS VALUES (3, 'Three', '2:00:0')";
            stmt.executeLargeUpdate(sql);
            sql = "INSERT INTO FILMS VALUES (4, 'Four', '01:00:0')";
            stmt.executeLargeUpdate(sql);
            sql = "INSERT INTO FILMS VALUES (5, 'Five', '2:00:0')";
            stmt.executeLargeUpdate(sql);
            System.out.println("Inserted records into the table...");


            System.out.println("Show c table");
            sql = "SELECT * FROM FILMS";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("-----------------\n" + rs + "\n-----------------");

            System.out.println("\tID\tTITLE\tDURATION");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Time duration = rs.getTime("duration");

                // Display values
                System.out.print("\t" +id);
                System.out.print("\t" +title);
                System.out.println("\t" +duration);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException se2) {
            }
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void createDB_SEANSES() {
        System.out.println("\n\n\nCreate table SEANSES\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Created table in given database...");

            sql = "CREATE TABLE SEANSES " +
                    "(id INTEGER not NULL, " +
                    " film_id INTEGER not null references films (id), " +
                    " time_begin_seans timestamp default current_timestamp, " +
                    " price  number (8, 2) not null, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            sql = "INSERT INTO SEANSES VALUES (1, 1,'2022-05-05 09:00:00' , 100.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (2, 3, '2022-05-05 10:00:00', 200.00)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (3, 1, '2022-05-05 13:30:00', 102.30)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (4, 2, '2022-05-05 14:00:00', 150.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (5, 2, '2022-05-05 14:30:00', 120.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (6, 3, '2022-05-05 15:15:00', 300.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (7, 5, '2022-05-05 16:00:00', 230.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (8, 4, '2022-05-05 17:05:00', 100.00)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (9, 3, '2022-05-05 18:00:00', 270.20)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (10, 5, '2022-05-05 18:45:00', 250.00)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (11, 4, '2022-05-05 19:00:00', 150.60)";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES VALUES (12, 2, '2022-05-05 20:00:00', 170.00)";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");


            System.out.println("Show c table");
            sql = "SELECT * FROM SEANSES";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("-----------------\n" + rs + "\n-----------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                int film_id = rs.getInt("film_id");
                Time time_begin_seans = rs.getTime("time_begin_seans");
                float price = rs.getFloat("price");

                System.out.print("ID: " + id);
                System.out.print(", film_id: " + film_id);
                System.out.print(", time_begin_seans: " + time_begin_seans);
                System.out.println(", price: " + price);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException se2) {
            }
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public void createDB_TICKETS() {
        System.out.println("\n\n\nCreate table TICKETS\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Created table in given database...\n");

            sql = "CREATE TABLE TICKETS " +
                    "(id INTEGER not NULL, " +
                    " seans_id INTEGER not null references seanses (id), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            for (int q = 1; q < 22; q++) {
                int rand = 0;
                while (rand == 0) {
                    rand = (int) (Math.random() * 12);
                }
                sql = "INSERT INTO TICKETS VALUES (" + q + ", " + rand + ")";
                stmt.executeUpdate(sql);
            }
            System.out.println("Inserted records into the table...");


            System.out.println("Show tickets table");
            sql = "SELECT * FROM TICKETS";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("-----------------\n" + rs + "\n-----------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                int seans_id = rs.getInt("seans_id");

                System.out.print("ID: " + id);
                System.out.println(", seans_id: " + seans_id);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException se2) {
            }
            try {
                conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
