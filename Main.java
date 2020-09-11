import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileExplorer fileExplorer = new FileExplorer();

        GameProgress progress1 = new GameProgress(90, 90, 90, 990);
        GameProgress progress2 = new GameProgress(1, 1, 1, 11);
        GameProgress progress3 = new GameProgress(44, 55, 66, 977790);

        List<String> filesToZip = new ArrayList<>();

        try {
            // Создаем 3 файла
            fileExplorer.saveObj("src//save1.txt", progress1, filesToZip);
            fileExplorer.saveObj("src//save2.txt", progress2, filesToZip);
            fileExplorer.saveObj("src//save3.txt", progress3, filesToZip);

            //Созадим архив и содержимое в нем
            fileExplorer.saveToZip("src//test.zip", filesToZip);

            //Удалим ненужные данные
            fileExplorer.deleteFiles(filesToZip);

            //Вернем данные из архива
            fileExplorer.unpackFromZip("src//test.zip", "src//");

            //Загрузим и выведем информацию об объектах
            fileExplorer.loadObj("src//save1.txt");
            fileExplorer.loadObj("src//save2.txt");
            fileExplorer.loadObj("src//save3.txt");

            // Результат вывода в консоль
            //GameProgress{health=90, weapons=90, lvl=90, distance=990.0}
            //GameProgress{health=1, weapons=1, lvl=1, distance=11.0}
            //GameProgress{health=44, weapons=55, lvl=66, distance=977790.0}

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
