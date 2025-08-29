/**
 * Class that handle the start of entire programme
 */

package daco;
import java.io.IOException;
import java.util.Scanner;

public class Daco {

    public static void main(String[] args) throws IOException, DacoException {

        Storage loadedfile = new Storage();
        Scanner sc = new Scanner(System.in);
        TaskList todolist = new TaskList(loadedfile.load());
        Ui ui = new Ui();

        while (true) {
                String userinput = sc.nextLine();
                if (userinput.equals("bye")) {
                    loadedfile.save(todolist.getlist());
                    ui.bye();
                    break;
                } else {
                    ui.input(userinput, todolist, sc);
                }
        }
    }
}

