package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void test(){
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 0; i < 20; i++) {
            list.add(util.Utility.random(50));
        }
        System.out.println(list);
        try {
            list.sort();
            System.out.println(list);

        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }
    void test11(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(20); list.add(10); list.add(15);
        System.out.println(list);
        try {
            System.out.println("Get Last: "+list.getLast()+". Get Prev:"
                    +list.getPrev(list.getLast()));
            Object element = list.removeLast();
            System.out.println("Removed Last:" +element);
            System.out.println(list);
            System.out.println("Get Last: "+list.getLast()+". Get Prev:"
                    +list.getPrev(list.getLast()));
            element = list.removeLast();
            System.out.println("Removed Last:" +element);
            System.out.println(list);
            System.out.println("Get Last: "+list.getLast()+". Get Prev:"
                    +list.getPrev(list.getLast()));
            System.out.println("GetPrev(50): "+ list.getPrev(50));
            element = list.removeLast();
            System.out.println("Removed Last:" +element);
            System.out.println("Get Last: "+list.getLast()+". Get Prev:"
                    +list.getPrev(list.getLast()));
            System.out.println(list);


        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    void test1(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(new Student("1", "Maria", 20, "Cartago"));
        list.add(new Student("2", "Carlos", 22, "San José"));

        System.out.println(list); //muestra la lista de estudiantes
        try {
            System.out.println(list.contains(new Student("2")));

        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    void test2() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);
        list.addFirst(50);
        list.addFirst(40);
        list.add(70);
        list.add(5);
        System.out.println(list);
        try {
            System.out.println("List size: "+list.size());
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            System.out.println(list);
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            /*for (int i = 0; i < 6 ; i++) {
                list.removeFirst();
            }*/
            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }
            System.out.println(list);

            for (int i = 0; i <10 ; i++) {
                int value = util.Utility.random(50);
                System.out.println(
                        list.contains(value)
                        ? "The element ["+value+"] exists in the list. " +
                                "Index: "+list.indexOf(value)
                        :"The element ["+value+" does not exist in the list"
                );

                //probamos remove
                if(list.contains(value)){
                    list.remove(value);
                    System.out.println("The element ["+value+"] has been deleted");
                }
            }

            System.out.println(list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }
}