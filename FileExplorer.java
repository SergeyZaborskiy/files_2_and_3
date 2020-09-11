import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileExplorer {
    private static StringBuilder logging = new StringBuilder();

    public void createDirectory(String path) {
        File directory = new File(path);
        if (directory.mkdir()) {
            logging.append("Каталог " + path + " успешно создан!").append('\n');
        }
    }

    public void createFile(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                logging.append("Файл " + path + " успешно создан!").append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeLogToFile(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(logging.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjToZip(String zipPath, List<String> filesToZip) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String file : filesToZip) {
            try (FileInputStream fis = new FileInputStream(file)) {

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    public <T> void saveObj(String path, T object, List<String> filesToZip) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(object);
            filesToZip.add(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToZip(String zipPath, List<String> filePath) throws IOException {

        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath));

        for (String originalFilePath : filePath) {
            File file = new File(originalFilePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                fis.read(buffer);

                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                zout.write(buffer);
                fis.close();
                zout.closeEntry();

            }
        }

        zout.close();
    }

    public void deleteFiles(List<String> files) {
        for (String filePath : files) {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            } else {
                System.out.println("не существует");
            }
        }
    }

    public void unpackFromZip(String zipPath, String filePath) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath));
        ZipEntry entry;
        File file;

        while ((entry = zis.getNextEntry()) != null) {
            byte[] buffer = new byte[4096];
            zis.read(buffer);
            file = new File(filePath + entry.getName());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            fos.close();
        }
    }

    public void loadObj(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        GameProgress restored = (GameProgress) ois.readObject();
        System.out.println(restored);
    }
}




