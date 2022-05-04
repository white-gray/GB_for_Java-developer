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
                    " title VARCHAR(255), " +
                    " duration numeric(3, 0) not NULL, " +
                    " price numeric(8, 2) not NULL, " +
                    "time_begin_seans timestamp default current_timestamp, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            sql = "INSERT INTO SEANSES " + "VALUES (1, 'One', 60, 100.20, '2022-05-03 09:00:00')";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES " + "VALUES (2, 'Two', 90, 200.20, '2022-05-03 09:10:00')";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES " + "VALUES (3, 'One', 60, 140.30, '2022-05-03 10:00:00')";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO SEANSES " + "VALUES (4, 'Two', 90, 100.00, '2022-05-03 11:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (5, 'Two', 90, 100.00, '2022-05-03 14:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (6, 'Three', 120, 300.50, '2022-05-03 13:30:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (7, 'One', 60, 100.20, '2022-05-03 12:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (8, 'Three', 120, 303.20, '2022-05-03 14:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (9, 'Four', 90, 400.20, '2022-05-03 16:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (10, 'One', 60, 123.20, '2022-05-03 14:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (11,'Two', 90, 230.20, '2022-05-03 17:00:00')";

            stmt.executeUpdate(sql);

            sql = "INSERT INTO SEANSES " + "VALUES (12, 'Five', 120, 500.20, '2022-05-03 19:20:00')";

            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");


            System.out.println("Show c table");
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
                System.out.println(", time_begin_seans: " + time_begin_seans);
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


    public void createDB_TICKETS() {
        System.out.println("\n\n\nCreate table TICKETS\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Created table in given database...");

            sql = "CREATE TABLE TICKETS " +
                    "(id INTEGER not NULL, " +
                    " number_ticket bigint  not null, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            for (int q = 1; q < 22; q++) {
                sql = "INSERT INTO TICKETS " + "VALUES (" + q+ ", " + q+")";
                stmt.executeUpdate(sql);
            }
            System.out.println("Inserted records into the table...");


            System.out.println("Show c table");
            sql = "SELECT * FROM TICKETS";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                int number_ticket = rs.getInt("number_ticket");

                // Display values
                System.out.print("ID: " + id);
                System.out.println(", number_ticket: " + number_ticket);
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
 public void createDB_ORDER_ITEMS() {
        System.out.println("\n\n\nCreate table ORDER_ITEMS\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Created table in given database...");

            sql = "CREATE TABLE ORDER_ITEMS " +
                    "(id INTEGER not NULL, " +
                    " seans_id          bigint not null references seanses (id), " +
                    " ticket_id         bigint not null references tickets (id), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);


            System.out.println("Fill the table with data");
            stmt = conn.createStatement();
            for (int q = 1; q < 22; q++) {
                int rand=0;
                while (rand == 0) {
                    rand = (int) (Math.random()*12);
                }
                System.out.println("rand = " + rand);
                sql = "INSERT INTO ORDER_ITEMS " + "VALUES (" + q+ ", " + rand + ", " + q+")";
                stmt.executeUpdate(sql);
            }
            System.out.println("Inserted records into the table...");


            System.out.println("Show c table");
            sql = "SELECT * FROM ORDER_ITEMS";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                long seans_id = rs.getLong("seans_id");
                long ticket_id = rs.getLong("ticket_id");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", seans_id: " + seans_id);
                System.out.println(", ticket_id: " + ticket_id);
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

    public void dropTable(String tableName) {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Drop table " + tableName + " in given database...");

            sql = "DROP TABLE " + tableName;
            stmt.executeUpdate(sql);
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
        System.out.println("Table dropped");
    }
}
