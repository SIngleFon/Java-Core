package Lesson_1.App;

import Lesson_1.Calculate.Calculate;
import Lesson_1.Formatter.Formatter;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Введите первое число: ");
//        int a = scanner.nextInt();
//
//        System.out.print("Введите второе число: ");
//        int b = scanner.nextInt();

        int result = Calculate.add(5, 5);
        System.out.println(Formatter.decorate(result));
        result = Calculate.sub(2, 2);
        System.out.println(Formatter.decorate(result));
        result = Calculate.mul(2, 2);
        System.out.println(Formatter.decorate(result));
        result = Calculate.div(2, 2);
        System.out.println(Formatter.decorate(result));
    }
}
