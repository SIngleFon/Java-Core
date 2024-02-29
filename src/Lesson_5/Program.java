package Lesson_5;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Program {
    //BACKUP
    public static void main(String[] args) throws IOException {
        BackUp backUp = new BackUp();
        backUp.createBackUp(new File("."), new File("backup"));

    }

}
