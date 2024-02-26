package Lesson_3;

public class Freelancer extends Base{
    private double hourSalary;
    private static int id = 0;

    public Freelancer() {
        super("Freelancer_" + id, 20.8 * 8 * 200);
        id++;
    }
    public Freelancer(double hourSalary) {
        super("Freelancer_"+id, 20.8 * 8 * hourSalary);
        id ++;
    }

    public Freelancer(String name) {
        super(name, 20.8 * 8 * 200);
    }

    public Freelancer(String name, double hourSalary) {
        super(name, 20.8 * 8 * hourSalary);
    }


}
