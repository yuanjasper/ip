package daco;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final java.nio.file.Path filepath;
    private final boolean doesExist;

    public Storage() {
        String home = System.getProperty("user.home");
        java.nio.file.Path filepath = Paths.get(home,"data" + "daco.txt");
        this.filepath = filepath;
        this.doesExist = java.nio.file.Files.exists(filepath);
    }

    public ArrayList<Task> load() throws IOException {
        try {
            File file = new File(String.valueOf(this.filepath));
            if (file.createNewFile()) {
                System.out.println("Created File at" + String.valueOf(this.filepath));
                return new ArrayList<Task>();
            }
            else {
                System.out.println("File already at" + String.valueOf(this.filepath));
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

    public void save(ArrayList<Task> list) {
        try {
            FileWriter writer = new FileWriter(String.valueOf(this.filepath), false);
            for (Task task : list) {
                writer.write(task.saveinfile());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
