import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File dir1 = new File("Games/src");
        if (dir1.mkdir())
            System.out.println("Каталог src создан");

        File dir2 = new File("Games/res");
        if (dir2.mkdir())
            System.out.println("Каталог res создан");

        File dir3 = new File("Games/savegames");
        if (dir3.mkdir())
            System.out.println("Каталог savegames создан");

        File dir4 = new File("Games/temp");
        if (dir4.mkdir())
            System.out.println("Каталог temp создан");

        File dir5 = new File("Games/src/main");
        if (dir5.mkdir())
            System.out.println("Каталог main создан");

        File dir6 = new File("Games/src/test");
        if (dir6.mkdir())
            System.out.println("Каталог test создан");

        File myFile1 = new File("Games/src/main/Main.java");
        try {
            if (myFile1.createNewFile())
                System.out.println("Фаил Main.java был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File myFile2 = new File("Games/src/main/Utils.java");
        try {
            if (myFile2.createNewFile())
                System.out.println("Фаил Utils.java был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        File dir7 = new File("Games/res/drawables");
        if (dir7.mkdir())
            System.out.println("Каталог drawables создан");

        File dir8 = new File("Games/res/c");
        if (dir8.mkdir())
            System.out.println("Каталог vectors создан");

        File dir9 = new File("Games/res/icons");
        if (dir9.mkdir())
            System.out.println("Каталог icons создан");

        File myFile3 = new File("Games/temp/temp.txt");
        try {
            if (myFile3.createNewFile())
                System.out.println("Фаил temp.txt был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Созданы: " + '\n' + dir1 + '\n' + dir2 + '\n' + dir3 + '\n' + dir4 + '\n' + dir5 + '\n' +
                dir6 + '\n' + dir7 + '\n' + dir8 + '\n' + dir9 + '\n' +
                myFile1 + '\n' + myFile2 + '\n' + myFile3);
        String text = sb.toString();

        try (FileWriter writer = new FileWriter("temp.txt", true)) {

            writer.write(text);
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
