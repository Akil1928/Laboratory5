package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String title;
    private Date birthday;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String lastName, String firstName, String title, Date birthday) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public int getAge(Date birthDay){


            LocalDate birth = birthDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            LocalDate now = LocalDate.now();

            return Period.between(birth, now).getYears();

    }
    public int getAge(){

        return getAge(this.birthday);
    }


    @Override
    public String toString() {
        return "(ID)"+id +"/(Name)"+lastName+", "+firstName
                + "/(Birthday)"+util.Utility.dateFormat(birthday)+ "/(Title)"+title
                +"/(Age)"+ getAge(birthday);

    }
}
