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

    /*
    * ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
    * Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
    * */
    public void mergeFilms() {
        System.out.println("\n\n\nMerged films show\n");
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            sql = "SELECT f.title, s.time_begin_seans, f.duration, f2.title, s2.time_begin_seans, f2.duration" +
                    " FROM SEANSES s" +
                    " JOIN FILMS f ON s.film_id = f.id" +
                    " JOIN SEANSES s2 ON s2.time_begin_seans > s.time_begin_seans and s2.time_begin_seans < (s.time_begin_seans, INTERVAL f.duration HOUR_SECOND)" +
                    " JOIN FILMS f2 ON s2.film_id = f2.id" +
                    " ORDER by s.time_begin_seans ASC";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                Time time_begin_seans = rs.getTime("time_begin_seans");
                int duration = rs.getInt("duration");
//                float price = rs.getFloat("price");

                System.out.print("ID: " + id);
                System.out.print(", title: " + title);
                System.out.println(", time_begin_seans: " + time_begin_seans);
                System.out.print(", duration: " + duration);
//                System.out.print(", price: " + price);
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

/*
* перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
* Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
* */
public void longClearPause() {
    System.out.println("\n\n\nPaused between films show\n");
    try {
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        sql = "SELECT s1.id, s1.film_id, s1.time_begin_seans, f1.duration, " +
                " TIMEDIFF( MIN(s2.time_begin_seans), DATEADD(s1.time_begin_seans, INTERVAL f1.duration HOUR_SECOND) ) AS BREAK " +
                " FROM SEANSES s1"+
                " JOIN FILMS f1 on s1.film_id = f1.id" +
                " LEFT JOIN seanses s2 on s1.time_begin_seans < s2.time_begin_seans" +
                " GROUP BY s1.time_begin_seans" +
                " HAVING TIME_TO_SEC(BREAK) > 60 * 30 " +
                " ORDER BY break DESC";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            int film_id = rs.getInt("film_id");
            Time time_begin_seans = rs.getTime("time_begin_seans");
            int duration = rs.getInt("duration");

            System.out.print("ID: " + id);
            System.out.print(", film_id: " + film_id);
            System.out.println(", time_begin_seans: " + time_begin_seans);
            System.out.print(", duration: " + duration);
        }

    } catch (SQLException se) {
        //Handle errors for JDBC
        se.printStackTrace();
    } catch (Exception e) {
        //Handle errors for Class.forName
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}


/*
* список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс
*  и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
* Внизу таблицы строчка «итого», содержащая данные по всем фильмам сразу;
**/
public void cashForFilms() {
    System.out.println("\n\n\nCash for films show\n");
    try {
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        sql = "(SELECT s.film_id, COUNT(*) AS TOTAL_ALL_TIME, COUNT(*) / COUNT(DISTINCT b.seans_id) AS avg_buyed," +
            "sum(s.price) as total_price " +
            "from seanses s " +
            "join tickets b on s.id = b.seans_id " +
            "GROUP BY s.film_id " +
            "ORDER BY total_price DESC)" +
            "UNION " +
            "SELECT 'Итого', COUNT(*), COUNT(b.seans_id) / COUNT(DISTINCT b.seans_id), SUM(s.price) " +
            "FROM seanses s " +
            "JOIN tickets b on s.id = b.seans_id";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            int film_id = rs.getInt("film_id");
            Time time_begin_seans = rs.getTime("time_begin_seans");
            int duration = rs.getInt("duration");

            System.out.print("ID: " + id);
            System.out.print(", film_id: " + film_id);
            System.out.println(", time_begin_seans: " + time_begin_seans);
            System.out.print(", duration: " + duration);
        }

    } catch (SQLException se) {
        //Handle errors for JDBC
        se.printStackTrace();
    } catch (Exception e) {
        //Handle errors for Class.forName
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}


/*
* число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
*    с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.)
* */
public void howManyVisitors() {
    System.out.println("\n\n\nHow many visitors at a certain time\n");
    try {
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        sql = "SELECT HOUR(s.time_begin_seans), SUM(price), COUNT(*) FROM seanses s" +
                " JOIN tickets b on s.id = b.seans_id" +
                " GROUP BY" +
                " (HOUR(time_begin_seans) >= 9 AND HOUR(time_begin_seans) < 15)," +
                " (HOUR(time_begin_seans) > 15 AND HOUR(time_begin_seans) < 18)," +
                " (HOUR(time_begin_seans) > 18 AND HOUR(time_begin_seans) < 21)," +
                " (HOUR(time_begin_seans) > 21 AND HOUR(time_begin_seans) < 00)";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            Time time_begin_seans = rs.getTime("time_begin_seans");
            float price = rs.getFloat("price");
            String seans = rs.getString("seans");

            System.out.print("ID: " + id);
            System.out.print(", time_begin_seans: " + time_begin_seans);
            System.out.println(", price: " + price);
            System.out.print(", seans: " + seans);
        }

    } catch (SQLException se) {
        //Handle errors for JDBC
        se.printStackTrace();
    } catch (Exception e) {
        //Handle errors for Class.forName
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}



    public void dropTable(String tableName) {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("\n\nConnecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Drop table " + tableName + " in given database...");

            sql = "DROP TABLE " + tableName;
            stmt.executeUpdate(sql);
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
        System.out.println("Table " + tableName + " dropped");
    }

}
