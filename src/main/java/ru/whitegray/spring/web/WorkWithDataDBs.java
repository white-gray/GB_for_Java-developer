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
                    " JOIN FILMS f ON  f.id = s.film_id" +
                    " JOIN SEANSES s2 ON s2.time_begin_seans > s.time_begin_seans " +
                    " and s2.time_begin_seans < (s.time_begin_seans + INTERVAL f.duration MINUTE)" +
                    " JOIN FILMS f2 ON f2.id = s2.film_id" +
                    " ORDER by s.time_begin_seans ASC";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String f_title = rs.getString("f.title");
                Time s_time_begin_seans = rs.getTime("s.time_begin_seans");
                Time f_duration = rs.getTime("f.duration");
                String f2_title = rs.getString("f2.title");
                Time s2_time_begin_seans = rs.getTime("s2.time_begin_seans");
                Time f2_duration = rs.getTime("f2.duration");


                System.out.print("Title first: " + f_title);
                System.out.print(", first time_begin_seans : " + s_time_begin_seans);
                System.out.print(", first_duration: " + f_duration);
                System.out.print("Title second: " + f2_title);
                System.out.print(", second time_begin_seans : " + s2_time_begin_seans);
                System.out.print(", second: " + f2_duration);
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
                " TIMEDIFF( MIN(s2.time_begin_seans), DATEADD(s1.time_begin_seans, INTERVAL f1.duration HOUR_SECOND) ) AS BREACK " +
                " FROM SEANSES s1"+
                " JOIN FILMS f1 on s1.film_id = f1.id" +
                " LEFT JOIN seanses s2 on s1.time_begin_seans < s2.time_begin_seans" +
                " GROUP BY s1.time_begin_seans" +
                " HAVING TIME_TO_SEC(BREAСK) > 60 * 30 " +
                " ORDER BY break DESC";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("s1.id");
            int film_id = rs.getInt("s1.film_id");
            Time time_begin_seans = rs.getTime("s1.time_begin_seans");
            int duration = rs.getInt("f1.duration");
            Time breack = rs.getTime("break");


            System.out.print("ID: " + id);
            System.out.print(", film_id: " + film_id);
            System.out.println(", time_begin_seans: " + time_begin_seans);
            System.out.print(", duration: " + duration);
            System.out.print(", breack: " + breack);
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

        sql = "SELECT s.film_id, COUNT(s.film_id) AS TOTAL_ALL_TIME, COUNT(b.seans_id) AS avg_buyed," +
            "sum(s.price) as total_price " +
            "from seanses s " +
            "join tickets b on s.id = b.seans_id " +
            "GROUP BY s.film_id " +
            "ORDER BY total_price DESC";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int film_id = rs.getInt("film_id");
            int total_all_time = rs.getInt("TOTAL_ALL_TIME");
            int avg_buyed = rs.getInt("avg_buyed");
            float total_price = rs.getFloat("total_price");

            System.out.print("film_id: " + film_id);
            System.out.print(", total_all_time: " + total_all_time);
            System.out.print(", avg_buyed: " + avg_buyed);
            System.out.println(", total_price: " + total_price);
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

        sql = "SELECT b.title AS TITLE HOUR(s.time_begin_seans) AS TIME_INTERVAL, SUM(price) AS SUM_PRICE, COUNT(*) FROM SEANSES s" +
                " JOIN TICKETS b ON s.id = b.seans_id" +
                " GROUP BY" +
                " (TIME_INTERVAL >= 9 AND TIME_INTERVAL < 15)," +
                " (TIME_INTERVAL > 15 AND TIME_INTERVAL < 18)," +
                " (TIME_INTERVAL > 18 AND TIME_INTERVAL < 21)," +
                " (TIME_INTERVAL > 21 AND TIME_INTERVAL < 00)";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Time time_interval = rs.getTime("TIME_INTERVAL");
            String title = rs.getString("TITLE");
            float sum_price = rs.getFloat("SUM_PRICE");

            System.out.print("time_interval: " + time_interval);
            System.out.print(", title: " + title);
            System.out.println(", sum_price: " + sum_price);
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

/*
*  пример для MySQL (но расмотреть: указать цену за билет, а не сеанс!)
*
CREATE TABLE `film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `duration` time,
  PRIMARY KEY (`id`)
);

CREATE TABLE `seans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `film_id` int(11) NOT NULL,
  `start` datetime,
  `price` int(11),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
);

CREATE TABLE `bilet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seans_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`seans_id`) REFERENCES `seans`(`id`)
);

INSERT INTO `film` VALUES (1,'Aaa','01:00:00'),(2,'Bbb','01:30:00'),(3,'Ccc','02:00:00'),(4,'Ddd','01:20:00');
INSERT INTO `seans` VALUES (1,1,'2005-08-12 09:00:00',100),(2,1,'2005-08-13 09:00:00',200),(3,1,'2005-08-14 10:00:00',300),(4,2,'2005-08-12 12:00:00',100),(5,2,'2005-08-12 18:00:00',100),(6,2,'2005-08-12 19:00:00',100),(7,3,'2005-08-12 09:30:00',150);
INSERT INTO `bilet` VALUES (1,1),(2,1),(3,1),(4,1),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,3),(14,3),(15,3),(16,4),(17,5),(18,6),(19,6),(20,7);

-- Запросы

select * from film;
select * from seans;
select * from bilet;

-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность».
select
f.name,
s.start,
f.duration,
f2.name,
s2.start,
s2.price
from seans s
join film f on s.film_id = f.id
join seans s2 on s2.start > s.start and s2.start < date_add(s.start, interval f.duration HOUR_SECOND)
join film f2 on s2.film_id = f2.id
order by s.start asc;

-- перерывы больше или равные 30 минут между фильмами, выводятся по уменьшению длительности перерыва.
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва».
select
s1.id,
s1.film_id,
s1.start,
f1.duration,
timediff( min(s2.start), date_add(s1.start, interval f1.duration HOUR_SECOND) ) as break
from seans s1
join film f1 on s1.film_id = f1.id
left join seans s2 on s1.start < s2.start
group by s1.start
HAVING time_to_sec(break) > 60 * 30
ORDER BY break DESC;

-- список фильмов, для каждого указано общее число посетителей за все время, среднее число зрителей за сеанс и общая сумма сбора по каждому, отсортированные по убыванию прибыли.
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу.
(select
s.film_id,
count(*) as total_all_time,
count(*) / count(distinct b.seans_id) as avg_buyed,
sum(s.price) as total_price
from seans s
join bilet b on s.id = b.seans_id
GROUP BY s.film_id
ORDER BY total_price desc
)
union
select "Итого", count(*), count(b.seans_id) / count(distinct b.seans_id), sum(s.price)
from seans s
join bilet b on s.id = b.seans_id;

-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00.
select hour(s.start), sum(price), count(*) from seans s
join bilet b on s.id = b.seans_id
group by
(hour(start) >= 9 and hour(start) < 15),
(hour(start) > 15 and hour(start) < 18),
(hour(start) > 18 and hour(start) < 21),
(hour(start) > 21 and hour(start) < 00)
*
* */