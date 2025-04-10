package domain;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    @Test
    void test() {

        //informática,
        //administración, inglés, turismo, agronomía, diseño publicitario, diseño web, asesor,
        //doctor, abogado; y las edades: 20, 23, 25, 28, 30, 34, 37,39, 42, 44, 48, 52, 55, 57,
        //60, 63, 65
        CircularLinkedList list = new CircularLinkedList();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005,6,21);
        list.add(new Employee(111111111,"Alfonso", "Davis","atministracion",calendar.getTime()));
        calendar.set(2002,7,19);
        list.add(new Employee(22222222,"Watson", "Akil","informatica",calendar.getTime()));
        calendar.set(2000,8,15);
        list.add(new Employee(333333333,"Moreno", "Javier","informatica",calendar.getTime()));
        calendar.set(1997,9,28);
        list.add(new Employee(444444444,"Angulo", "Marvin","diseño publicitario",calendar.getTime()));
        calendar.set(1995,8,15);
        list.add(new Employee(555555555,"Waston", "Kendall","agronomia",calendar.getTime()));
        calendar.set(1991,4,12);
        list.add(new Employee(555555555,"Ronaldo", "Cristiano","doctor",calendar.getTime()));
        calendar.set(1988,2,1);
        list.add(new Employee(666666666,"Campbell", "Joel","abogado",calendar.getTime()));
        calendar.set(1986,12,23);
        list.add(new Employee(777777777,"Calvo", "Francisco","ingles",calendar.getTime()));
        calendar.set(1983,11,1);
        list.add(new Employee(888888888,"Navas", "Keylor","turismo",calendar.getTime()));
        calendar.set(1981,6,6);
        list.add(new Employee(999999999,"Junior", "Vinicius","asesor",calendar.getTime()));


        System.out.println(list);
        try {
            System.out.println(showAgeList(list, "Empleados con rango de edad entre 18 y 25",18, 25));

        System.out.println(showAgeList(list, "Empleados con rango de edad entre 26 y 40",26, 40));
        System.out.println(showAgeList(list, "Empleados con rango de edad entre 41 y 55",41, 55));
        System.out.println(showAgeList(list, "Empleados con rango de edad mayor a 55",55, 100));
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

     CircularLinkedList my_list = getTitleList(list, "Informatica");
        System.out.println("Empleados con la profesion: Informatica:\n"+ my_list);
        my_list = getTitleList(list,"Ingles");
        System.out.println("Empleados con la profesion: Ingles:\n"+ my_list);
        my_list = getTitleList(list,"Turismo");
        System.out.println("Empleados con la profesion: Turismo:\n"+ my_list);
        my_list = getTitleList(list,"doctor");
        System.out.println("Empleados con la profesion: doctor:\n"+ my_list);


    }
    private CircularLinkedList getTitleList(CircularLinkedList list, String title){
        CircularLinkedList  result = new CircularLinkedList();
        return result;
    }
    private String showAgeList(CircularLinkedList list,String msg, int low, int high) throws ListException {


        String result =msg+"\n";
for (int i =1;i<= list.size();i++){
    Employee employee = (Employee) list.getNode(i).data;
    int age = employee.getAge(employee.getBirthday());
    if(age >= low && age <= high);
    result+=employee+"\n";
}
        return result;

    }
}