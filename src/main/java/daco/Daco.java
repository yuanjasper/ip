package daco;
import java.io.IOException;
import java.util.Scanner;
/**
 * Class that handle the start of entire programme
 */
public class Daco {
    /**
     * Runs the program, initialises storage to retrieve previous to do list
     * If the position is unset, NaN is returned.
     *
     */
    public static void main(String[] args) throws IOException {

        Storage loadedfile = new Storage();
        Scanner sc = new Scanner(System.in);
        TaskList toDoList = new TaskList(loadedfile.load());
        Ui ui = new Ui();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                loadedfile.save(toDoList.getList());
                ui.bye();
                break;
            } else {
                ui.input(userInput, toDoList, loadedfile);
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        Storage loadedfile = new Storage();
        TaskList toDoList = new TaskList(loadedfile.load());
        Ui ui = new Ui();

        if (userInput.equals("bye")) {
            loadedfile.save(toDoList.getList());
            return ui.bye();
        } else {
            return ui.input(userInput, toDoList, loadedfile);
        }
    }
}

