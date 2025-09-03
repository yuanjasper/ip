package daco;
import java.util.Random;
import java.util.Scanner;
/**
 * Handles the input from user but does not check whether a command is available or correct
 * Programme does the responses for correct execution of input.
 */

public class Ui {
    public final String lineSep = "____________________________________________________________\n";
    public final String[] neutralFaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public final String[] sadFaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};
    /**
     * Shows the startup message of the bot
     */
    public Ui() {
        String logo = " ____                  \n"
                + "|  _ \\  __ _  ___ ___  \n"
                + "| | | |/ _` |/ __/ _ \\ \n"
                + "| |_| | (_| | (_| (_) |\n"
                + "|____/ \\__,_|\\___\\___/ \n \n";

        dacoResponse(logo + "Hi there, I'm Daco! How can I help?");
    }
    /**
     * Prints the standard chatbot output
     *
     * @param input what you want the chatbot to say besides the design stuff
     */
    public void dacoResponse(String input) {
        System.out.println(lineSep + input + "\n" + lineSep);
    }
    /**
     * Returns a String of a random response from an array of faces
     *
     * @param responses the array list of faces you wish to choose from
     */
    public String randomResponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    /**
     * Replies with goodbye
     */
    public void bye() {
        dacoResponse("Come back anytime. " + randomResponse(sadFaces));
    }
    /**
     * Picks the option to execute depending on the input variable
     */
    public void input(String userInput, TaskList toDoList, Scanner sc) {
        try {
            if (userInput.equals("list")) {
                toDoList.showList();
            } else if (userInput.startsWith("mark ")) {
                if (toDoList.mark(userInput, true)) {
                    String option = sc.nextLine();
                    if (option.equals("Y")) {
                        toDoList.delete(userInput);
                    } else {
                        dacoResponse("Okay, I won't do anything! " + randomResponse(neutralFaces));
                    }
                }
            } else if (userInput.startsWith("unmark ")) {
                toDoList.mark(userInput, false);
            } else if (userInput.startsWith("todo ")) {
                new Parser().verifyTask(userInput.substring(5));
                toDoList.add(new ToDos(userInput.substring(5)));
            } else if (userInput.startsWith("deadline ")) {
                new Parser().verifyDate(userInput.substring(9));
                String[] temp = userInput.substring(9).split(",");
                new Parser().verifyTask(temp[0]);
                toDoList.add(new Deadline(temp[0], temp[1]));
            } else if (userInput.startsWith("event ")) {
                new Parser().verifyDate(userInput.substring(6));
                String[] temp = userInput.substring(6).split(", ");
                new Parser().verifyTask(temp[0]);
                toDoList.add(new Event(temp[0], temp[1]));
            } else if (userInput.startsWith("delete ")) {
                toDoList.delete(userInput);
            } else if (userInput.startsWith("find ")) {
                String tasksToFind = userInput.substring(5);
                new Parser().verifyTask(tasksToFind);
                toDoList.findByDescription(tasksToFind);
            } else {
                new Parser().errors(new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK));
            }
        } catch (DacoException ignored) {
            return;
        }
    }
}

