package domain;

import org.junit.jupiter.api.Test;
import java.util.Calendar;

public class CircularDoublyLinkedListTest {

    @Test
    public void testClasificacionPorEdad() throws ListException {
        CircularDoublyLinkedList a = new CircularDoublyLinkedList();
        CircularDoublyLinkedList b = new CircularDoublyLinkedList();
        CircularDoublyLinkedList c = new CircularDoublyLinkedList();
        CircularDoublyLinkedList d = new CircularDoublyLinkedList();

        CircularLinkedList list = new CircularLinkedList();
        Calendar calendar = Calendar.getInstance();

        calendar.set(2005, 6, 21);
        list.add(new Employee(111111111, "Alfonso", "Davis", "administracion", calendar.getTime()));

        calendar.set(2002, 7, 19);
        list.add(new Employee(222222222, "Watson", "Akil", "informatica", calendar.getTime()));

        calendar.set(2000, 8, 15);
        list.add(new Employee(333333333, "Moreno", "Javier", "informatica", calendar.getTime()));

        calendar.set(1997, 9, 28);
        list.add(new Employee(444444444, "Cerdas", "Cadima", "diseño publicitario", calendar.getTime()));

        calendar.set(1995, 8, 15);
        list.add(new Employee(555555555, "Sibaja", "Ana", "agronomia", calendar.getTime()));

        calendar.set(1991, 4, 12);
        list.add(new Employee(666666666, "Moreno", "Melvin", "doctor", calendar.getTime()));

        calendar.set(1988, 2, 1);
        list.add(new Employee(777777777, "Moreno", "Clareth", "abogado", calendar.getTime()));

        calendar.set(1986, 11, 1);
        list.add(new Employee(888888888, "Whimmy", "Jashburn", "turismo", calendar.getTime()));

        calendar.set(1962, 6, 6);
        list.add(new Employee(999999999, "Junior", "Vinicius", "asesor", calendar.getTime()));

        calendar.set(1995, 6, 15);
        list.add(new Employee(101010101, "Jlia", "Jones", "ingles", calendar.getTime()));

        for (int i = 1; i <= list.size(); i++) {
            Employee employee = (Employee) list.getNode(i).data;
            int age = employee.getAge();

            if (age >= 18 && age <= 25) {
                a.add(employee);
            } else if (age >= 26 && age <= 40) {
                b.add(employee);
            } else if (age >= 41 && age <= 55) {
                c.add(employee);
            } else if (age > 55) {
                d.add(employee);
            }
        }

        System.out.println("Lista A - Edad 18 a 25:");
        System.out.println(a);
        System.out.println("\nLista B - Edad 26 a 40:");
        System.out.println(b);
        System.out.println("\nLista C - Edad 41 a 55:");
        System.out.println(c);
        System.out.println("\nLista D - Edad mayor a 55:");
        System.out.println(d);
    }
}
