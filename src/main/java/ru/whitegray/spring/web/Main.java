package ru.whitegray.spring.web;

public class Main {
    public static void main(String[] args) {
        CreateAndFillDBs createAndFillDBs = new CreateAndFillDBs();
        createAndFillDBs.createDB_SEANSES();
        createAndFillDBs.createDB_TICKETS();
        createAndFillDBs.createDB_ORDER_ITEMS();






        createAndFillDBs.dropTable("ORDER_ITEMS");
        createAndFillDBs.dropTable("SEANSES");
        createAndFillDBs.dropTable("TICKETS");

    }

}
