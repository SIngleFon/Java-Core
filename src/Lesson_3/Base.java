package Lesson_3;

public abstract class Base implements Comparable<Base>{
    protected String name;
    protected double salary;

    protected Base(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Base{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Base o) {
        return (int)this.getSalary() - (int)o.getSalary();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
