package Lesson_5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class BackUp {

    public void createBackUp(File source, File input) throws IOException {
        if (source.isDirectory()) {
            if (!input.exists()) {
                input.mkdir();
            }
            String[] arr = source.list();
            assert arr != null;
            for (String s : arr) {
                if (!s.equals(input.getName())) {
                    createBackUp(new File(source, s), new File(input, s));
                }
            }
        } else {
            try (FileOutputStream fileOutputStream = new FileOutputStream(input)) {
                int c;
                try (FileInputStream fileInputStream = new FileInputStream(source)) {
                    while ((c = fileInputStream.read()) != -1) {
                        fileOutputStream.write(c);
                    }
                }
            }
        }
    }
}
