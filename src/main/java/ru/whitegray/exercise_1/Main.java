package ru.whitegray.exercise_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Collection collect = new ArrayList(Arrays.asList("A",1,2,3,4,5,5,2,4,32,2,34,42,2,234,214,2,"B", "C"));
        linkedList.addAll(collect);
        // создан список
        System.out.println("создан список");
        showLinkedList(linkedList);

        Collection collect2= new ArrayList(Arrays.asList("A",1,2,"B", "C", "___НовыйСписок1___"));
        linkedList.addAll(collect2);
        // добавление дополнительного списка к концу имеющегося списка
        System.out.println("\nдобавление дополнительного списка к концу имеющегося списка");
        showLinkedList(linkedList);

        linkedList.add("new_1");
        // добавление объекта к концу имеющегося списка
        System.out.println("\nдобавление объекта к концу имеющегося списка");
        showLinkedList(linkedList);

        LinkedList linkedList2 = (LinkedList) linkedList.clone();
        // возвращает копию текущего списка
        System.out.println("\nвозвращает копию текущего списка");
        System.out.println("\tновый лист ");
        showLinkedList(linkedList2);
        System.out.println("\tявляется клоном старого ");
        showLinkedList(linkedList);

        linkedList.addFirst("new_2");
        // добавление объекта к началу имеющегося списка
        System.out.println("\nдобавление объекта к началу имеющегося списка");
        showLinkedList(linkedList);

        linkedList.add(11, "new_12");
        // добавление объекта на 12ю позицию имеющегося списка
        System.out.println("\nдобавление объекта на 12ю позицию имеющегося списка");
        showLinkedList(linkedList);

        Collection collect3= new ArrayList(Arrays.asList("2-1", "2-2", "2-3"));
        linkedList.addAll(17, collect3);
        // добавление дополнительного списка, начиная с 18го элемента имеющегося списка
        System.out.println("\nдобавление дополнительного списка, начиная с 18го элемента имеющегося списка");
        showLinkedList(linkedList);

        linkedList.removeFirst();
        // удаление первого объекта имеющегося списка
        System.out.println("\nудаление первого объекта имеющегося списка");
        showLinkedList(linkedList);

        linkedList.removeLast();
        // удаление посдеднего объекта имеющегося списка
        System.out.println("\nудаление посдеднего объекта имеющегося списка");
        showLinkedList(linkedList);

        linkedList.remove(13);
        // удаление 13го объекта имеющегося списка
        System.out.println("\nудаление 13го объекта имеющегося списка");
        showLinkedList(linkedList);

        linkedList.remove("A");
        // удаление указанного объекта "A" первого найденного в списке
        System.out.println("\nудаление указанного объекта 'A' первого найденного в списке");
        showLinkedList(linkedList);

        linkedList.removeLastOccurrence("C");
        // удаление указанного объекта "C" последнего найденного в списке
        System.out.println("\nудаление указанного объекта 'C' последнего найденного в списке");
        showLinkedList(linkedList);

        Collection collect4= new ArrayList(Arrays.asList("A",1,2,"B", "C"));
        linkedList.removeAll(collect4);
        // удаление из списка элементов, указанных в списке
        System.out.println("\nудаление из списка элементов, указанных в списке");
        showLinkedList(linkedList);

        // чтение значения 12го эдемента
        System.out.println("\nчтение значения 12го эдемента ");
        System.out.println("\t\t12й элемент: " + linkedList.get(12));

        // проверка есть ли в списке элемент со значением 'new_'
        System.out.println("\nпроверка есть ли в списке элемент со значением 'new_'");
        System.out.println("\t\tналичие 'new_': " + linkedList.contains("new_"));

        linkedList.clear();
        // удаление всех значений в списке
        System.out.println("\nудаление всех значений в списке");
        showLinkedList(linkedList);

    }

    private static void showLinkedList(LinkedList linkedList) {
        for (Object element : linkedList) {
            System.out.print(element + ", ");
        }
        System.out.println();
    }

}
