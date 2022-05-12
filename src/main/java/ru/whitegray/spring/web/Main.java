package ru.whitegray.spring.web;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CreateAndFillDBs createAndFillDBs = new CreateAndFillDBs();
        createAndFillDBs.createDB_FILMS();
        createAndFillDBs.createDB_SEANSES();
        createAndFillDBs.createDB_TICKETS();

        WorkWithDataDBs workWithDataDBs = new WorkWithDataDBs();
        workWithDataDBs.mergeFilms();
        workWithDataDBs.longClearPause();
        workWithDataDBs.cashForFilms();
        workWithDataDBs.howManyVisitors();



        workWithDataDBs.dropTable("TICKETS");
        workWithDataDBs.dropTable("SEANSES");
        workWithDataDBs.dropTable("FILMS");

    }

}
