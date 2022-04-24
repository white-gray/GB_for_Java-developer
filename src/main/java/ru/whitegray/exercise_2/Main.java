package ru.whitegray.exercise_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(Arrays.asList("A",1,2,3,4,5,5,2,4,32,2,34,42,2,234,214,2,"B", "C"));
        // создан список
        System.out.println("создан список");
        showLinkedList(arrayList);

        Collection collect2= new ArrayList(Arrays.asList("A",1,2,"B", "C", "___НовыйСписок1___"));
        arrayList.addAll(collect2);
        // добавление дополнительного списка к концу имеющегося списка
        System.out.println("\nдобавление дополнительного списка к концу имеющегося списка");
        showLinkedList(arrayList);

        arrayList.add("new_1");
        // добавление объекта к концу имеющегося списка
        System.out.println("\nдобавление объекта к концу имеющегося списка");
        showLinkedList(arrayList);

        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        // возвращает копию текущего списка
        System.out.println("\nвозвращает копию текущего списка");
        System.out.println("\tновый лист ");
        showLinkedList(arrayList2);
        System.out.println("\tявляется клоном старого ");
        showLinkedList(arrayList);

        arrayList.add(11, "new_12");
        // добавление объекта на 12ю позицию имеющегося списка
        System.out.println("\nдобавление объекта на 12ю позицию имеющегося списка");
        showLinkedList(arrayList);

        Collection collect3= new ArrayList(Arrays.asList("2-1", "2-2", "2-3"));
        arrayList.addAll(17, collect3);
        // добавление дополнительного списка, начиная с 18го элемента имеющегося списка
        System.out.println("\nдобавление дополнительного списка, начиная с 18го элемента имеющегося списка");
        showLinkedList(arrayList);

        arrayList.remove(13);
        // удаление 13го объекта имеющегося списка
        System.out.println("\nудаление 13го объекта имеющегося списка");
        showLinkedList(arrayList);

        arrayList.remove("A");
        // удаление указанного объекта "A" первого найденного в списке
        System.out.println("\nудаление указанного объекта 'A' первого найденного в списке");
        showLinkedList(arrayList);

        Collection collect4= new ArrayList(Arrays.asList("A",1,2,"B", "C"));
        arrayList.removeAll(collect4);
        // удаление из списка элементов, указанных в списке
        System.out.println("\nудаление из списка элементов, указанных в списке");
        showLinkedList(arrayList);

        // чтение значения 12го эдемента
        System.out.println("\nчтение значения 12го эдемента ");
        System.out.println("\t\t12й элемент: " + arrayList.get(12));

        // проверка есть ли в списке элемент со значением 'new_'
        System.out.println("\nпроверка есть ли в списке элемент со значением 'new_'");
        System.out.println("\t\tналичие 'new_': " + arrayList.contains("new_"));

        arrayList.clear();
        // удаление всех значений в списке
        System.out.println("\nудаление всех значений в списке");
        showLinkedList(arrayList);

    }

    private static void showLinkedList(ArrayList arrayList) {
        for (Object element : arrayList) {
            System.out.print(element + ", ");
        }
        System.out.println();
    }

}
