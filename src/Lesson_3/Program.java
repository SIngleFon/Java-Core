package Lesson_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        List<Base> base = new ArrayList<>();
        base.add(new Worker());
        base.add(new Worker(55000));
        base.add(new Worker("Tim"));
        base.add(new Worker("Lany", 88000));
        base.add(new Freelancer());
        base.add(new Freelancer(250));
        base.add(new Freelancer("Gracy"));
        base.add(new Freelancer("Dmitry", 100));

        System.out.println("\nUnsorted list\n");
        for ( Base bases : base ){
            System.out.println(bases);
        }
        System.out.println("\nComparator sorted list \n");
        Collections.sort(base);
        for ( Base bases : base ){
            System.out.println(bases);
        }
    }
}
