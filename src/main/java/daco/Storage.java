package daco;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class responsible for loading data, saving the data file's location and contents
 */
public class Storage {
    private final java.nio.file.Path filepath;
    /**
     * Constructs the Storage class object, achieves the file path via paths
     */
    public Storage() {
        String home = System.getProperty("user.home");
        this.filepath = Paths.get(home, "data" + "daco.txt");
    }
    /**
     * Loads the data from the file into the to do list of the current run
     */
    public ArrayList<Task> load() {
        try {
            File file = new File(String.valueOf(this.filepath));
            if (file.createNewFile()) {
                return new ArrayList<Task>();
            } else {
                Scanner sc = new Scanner(file);
                ArrayList<Task> list = new ArrayList<>();
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    String[] split = data.split(" \\| ");
                    if (split[0].equals("T")) {

                        list.add(new ToDos(split[2], split[1].equals("1")));
                        continue;
                    }
                    if (split[0].equals("D")) {
                        list.add(new Deadline(split[2], split[1].equals("1"), split[3]));
                        continue;
                    }
                    if (split[0].equals("E")) {
                        list.add(new Event(split[2], split[1].equals("1"), split[3] ));
                    }
                }
                return list;
            }
        } catch (IOException | DacoException e) {
            System.out.println("Error");
        }
        return new ArrayList<>();
    }
    /**
     * Saves the to do list contents into the same file by rewriting the file
     */
    public void save(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.filepath), false);
            for (Task task : list) {
                writer.write(task.formatToSaveInFile());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
