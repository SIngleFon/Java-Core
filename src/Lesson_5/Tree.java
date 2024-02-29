package Lesson_5;

import java.io.File;

public class Tree {

    /**
     * TODO: TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param args
     */
    public static void main(String[] args) {
        print(new File("."), "", true);
    }

    static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
            {
                subDirTotal++;
            }
        }
        for (int d = 0; d < files.length; d++){
            if (files[d].isDirectory()){
                continue;
            }
            if (files[d].isFile()) {
                if(d == files.length -1){
                    System.out.println(indent +"└─"+ files[d].getName());
                    break;
                }
                else{
                System.out.println(indent +"├─"+ files[d].getName());
                }
            }
        }

        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory() & !files[i].getName().equals(".git")
                    & !files[i].getName().equals(".idea")
                    & !files[i].getName().equals("out") )
            {
                print(files[i], indent, subDirTotal == ++subDirCounter);
            }
        }

    }
}


