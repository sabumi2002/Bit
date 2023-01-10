package day0110;

public class Employee {
    public int id;
    public String name;
    public String rank;
    public String department;
    public int salary;

    public Employee(int id, String name, String grade, String department, int salary){
        this.id = id;
        this.name = name;
        this.rank = grade;
        this.department = department;
        this.salary = salary;
    }
    public void printInfo(){
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.rank);
        System.out.println(this.department);
        System.out.println(this.salary);
    }

}

