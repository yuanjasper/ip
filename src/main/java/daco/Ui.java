package daco;
import java.util.Random;

/**
 * Handles the input from user but does not check whether a command is available or correct
 * Programme does the responses for correct execution of input.
 */

public class Ui {
    public final String LINE_SEPERATOR = "________________________________________________________\n";
    public final String[] NEUTRAL_FACES = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public final String[] SAD_FACES = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};

    public Ui() {
    }

    /**
     * Prints the standard chatbot output
     *
     * @param input what you want the chatbot to say besides the design stuff
     */
    public String dacoResponse(String input) {
        return LINE_SEPERATOR + input + "\n" + LINE_SEPERATOR;
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
        return dacoResponse("Come back anytime. " + randomResponse(SAD_FACES));
    }

    /**
     * Picks the option to execute depending on the input variable
     */
    public String input(String userInput, TaskList toDoList, Storage loadedFile) {
        try {
            new Parser().emptyCommand(userInput);
            String input = userInput.trim().split(" ")[0].toLowerCase();
            switch (input) {
            case "list":
                return toDoList.showList();
            case "mark":
                return toDoList.mark(userInput, true, loadedFile);
            case "unmark":
                return toDoList.mark(userInput, false, loadedFile);
            case "todo":
                new Parser().verifyTask(userInput.substring(4));
                return toDoList.add(new ToDos(userInput.substring(5)), loadedFile);
            case "deadline":
                new Parser().verifyDate(userInput.substring(8));
                String[] temp = userInput.substring(9).split(",");
                new Parser().verifyTask(temp[0]);
                return toDoList.add(new Deadline(temp[0], temp[1]), loadedFile);
            case "event":
                new Parser().verifyDate(userInput.substring(5));
                String[] temporary = userInput.substring(6).split(", ");
                new Parser().verifyTask(temporary[0]);
                return toDoList.add(new Event(temporary[0], temporary[1]), loadedFile);
            case "delete":
                return toDoList.delete(userInput, loadedFile);
            case "find":
                String tasksToFind = userInput.substring(4);
                new Parser().verifyFindFormat(tasksToFind);
                return toDoList.findByDescription(tasksToFind);
            case "sort":
                return toDoList.sortByDate();
            case "help":
                return this.help();
            default:
                throw new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK);
            }
        } catch (DacoException e) {
            return new Parser().errors(e);
        }
    }
    /**
     * Returns the list of functions and how to write them
     */
    public String help() {
        return dacoResponse("Hi there, I see you requested help! Here's what I can do \n"
                + "list: Shows you items in your list\n"
                + "mark <item #>: Marks the item at the requested number\n"
                + "unmark <item #>: Unmarks the item at the requested number\n"
                + "todo <description>: Adds a todo task into your list\n"
        + "deadline <description>, <time>: Adds a deadline into your list \n"
        + "event <description>, <time>: Adds an event into your list\n"
        + "delete <item #>: Deletes the item at the requested number\n"
        + "find <description>: Finds item(s) matching partial description requested\n"
        + "sort: Gives you a list of items sorted in chronological order");

    }
}

