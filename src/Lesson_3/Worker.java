package Lesson_3;

public class Worker extends Base{
    private static int id = 0;

    public Worker() {
        super("Worker_" + id, 80000);
        id++;
    }
    public Worker(double salary) {
        super("Worker_"+id, salary);
        id ++;
    }

    public Worker(String name) {
        super(name, 80000);
    }

    public Worker(String name, double salary) {
        super(name, salary);
    }
}
