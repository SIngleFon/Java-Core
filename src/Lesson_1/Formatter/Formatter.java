package Lesson_1.Formatter;

public class Formatter {
    public static String decorate(int a) {
        /*
         * Метод декорирует число, добавляя к нему строку
         * при помощи функции форматирования строк
         * */
        return String.format("Answer: %d.", a);
    }
}
