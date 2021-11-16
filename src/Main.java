import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        install();
        saveGame();
        zipWrite();
        loading();
    }

    private static void makeFile(String name) {
        File file = new File(name);
        try {
            if (file.createNewFile())
                System.out.println("Файл " + name + " был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void makeDir(String name) {
        File dir = new File(name);
        if (dir.mkdir())
            System.out.println("Каталог " + name + " создан");
    }

    private static void fileDelete(String name) {
        File file = new File(name);
        try {
            if (file.delete())
                System.out.println("Файл " + name + " удалён");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void install() {

        makeDir("Games/src");
        makeDir("Games/res");
        makeDir("Games/savegames");
        makeDir("Games/temp");
        makeDir("Games/src/main");
        makeDir("Games/src/test");
        makeFile("Games/src/main/Main.java");
        makeFile("Games/src/main/Utils.java");
        makeDir("Games/res/drawables");
        makeDir("Games/res/vectors");
        makeDir("Games/res/icons");
        makeFile("Games/temp/temp.txt");


//        StringBuilder sb = new StringBuilder();
//        sb.append("Созданы: " + '\n' +; +'\n' + dir2 + '\n' + dir3 + '\n' + dir4 + '\n' + dir5 + '\n' +
//                dir6 + '\n' + dir7 + '\n' + dir8 + '\n' + dir9 + '\n' +
//                myFile1 + '\n' + myFile2 + '\n' + myFile3);
//        String text = sb.toString();
//
//        try (FileWriter writer = new FileWriter("temp.txt", true)) {
//
//            writer.write(text);
//            writer.append('\n');
//            writer.flush();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    private static void saveGame() {

        GameProgress gameProgress1 = new GameProgress(95, 49, 2, 120.5);
        GameProgress gameProgress2 = new GameProgress(88, 40, 4, 150.7);
        GameProgress gameProgress3 = new GameProgress(69, 31, 7, 213);

        try (FileOutputStream fos = new FileOutputStream("Games/savegames/save1.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("Games/savegames/save2.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("Games/savegames/save3.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void zipWrite() {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("Games/savegames/zip_save.zip"));
             FileInputStream fis1 = new FileInputStream("Games/savegames/save1.dat");
             FileInputStream fis2 = new FileInputStream("Games/savegames/save1.dat");
             FileInputStream fis3 = new FileInputStream("Games/savegames/save1.dat")) {

            ZipEntry entry1 = new ZipEntry("packed_save1");
            ZipEntry entry2 = new ZipEntry("packed_save2");
            ZipEntry entry3 = new ZipEntry("packed_save3");

            byte[] buffer = new byte[fis1.available()];

            zout.putNextEntry(entry1);
            fis1.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            zout.putNextEntry(entry2);
            fis2.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

            zout.putNextEntry(entry3);
            fis3.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        fileDelete("Games/savegames/save1.dat");
        fileDelete("Games/savegames/save2.dat");
        fileDelete("Games/savegames/save3.dat");
    }

    private static void loading() {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream("Games/savegames/zip_save.zip"))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileInputStream fis = new FileInputStream("packed_save1")) {
            int i;
            while((i = fis.read()) != -1){
                System.out.println((char) i);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}