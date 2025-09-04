package daco;
import java.util.Random;
import java.util.Scanner;
/**
 * Handles the input from user but does not check whether a command is available or correct
 * Programme does the responses for correct execution of input.
 */

public class Ui {
    public final String lineSep = "________________________________________________________\n";
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
    public String dacoResponse(String input) {
        return lineSep + input + "\n" + lineSep;
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
    public String bye() {
        return dacoResponse("Come back anytime. " + randomResponse(sadFaces));
    }
    /**
     * Picks the option to execute depending on the input variable
     */
    public String input(String userInput, TaskList toDoList, Storage loadedfile) {
        try {
            if (userInput.equals("list")) { //DONE
                return toDoList.showList();
            } else if (userInput.startsWith("mark ")) { //DONE
                return toDoList.mark(userInput, true, loadedfile);
            } else if (userInput.startsWith("unmark ")) { //DONE
                return toDoList.mark(userInput, false, loadedfile);
            } else if (userInput.startsWith("todo ")) { //DONE
                new Parser().verifyTask(userInput.substring(5));
                return toDoList.add(new ToDos(userInput.substring(5)), loadedfile);
            } else if (userInput.startsWith("deadline ")) { //DONE
                new Parser().verifyDate(userInput.substring(9));
                String[] temp = userInput.substring(9).split(",");
                new Parser().verifyTask(temp[0]);
                return toDoList.add(new Deadline(temp[0], temp[1]), loadedfile);
            } else if (userInput.startsWith("event ")) { //DONE
                new Parser().verifyDate(userInput.substring(6));
                String[] temp = userInput.substring(6).split(", ");
                new Parser().verifyTask(temp[0]);
                return toDoList.add(new Event(temp[0], temp[1]), loadedfile);
            } else if (userInput.startsWith("delete ")) { //DONE
                return toDoList.delete(userInput, loadedfile);
            } else if (userInput.startsWith("find ")) { //DONE
                String tasksToFind = userInput.substring(5);
                new Parser().verifyTask(tasksToFind);
                return toDoList.findByDescription(tasksToFind);
            } else {
                throw new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK);
            }
        } catch (DacoException e) {
            return new Parser().errors(e);
        }
    }
}

