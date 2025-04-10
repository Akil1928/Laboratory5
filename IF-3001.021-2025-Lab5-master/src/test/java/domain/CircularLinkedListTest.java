package domain;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    void test() {
        CircularLinkedList list = new CircularLinkedList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005,6,21);
        list.add(new Employee(111111111,"Campos", "Davis","atministracion",calendar.getTime()));
        calendar.set(2006,7,19);
        list.add(new Employee(22222222,"Watson", "Akil","informatica",calendar.getTime()));
        calendar.set(2000,8,15);
        list.add(new Employee(333333333,"Moreno", "Javier","informatica",calendar.getTime()));

        System.out.println(list);
    }
}