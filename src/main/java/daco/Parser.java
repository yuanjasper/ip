package daco;
import java.util.ArrayList;
import java.util.Random;
/**
 * Main class for handling proper logic for inadequate, incomplete commands
 */
public class Parser {
    public final String[] sadFaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};
    public final String lineSep = "________________________________________________________\n";
    /**
     * Checks that the input given has a non-empty date, otherwise throws the empty date exception
     *
     * @param input String of user's input during typing their entire command
     * @throws DacoException when is empty
     */
    public void verifyDate(String input) throws DacoException {
        try {
            String[] temp = input.split(", ");
            verifyTask(temp[0]);
            verifyTask(temp[1]);
            new DateAndTime(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) { //may cause double throwing if wrong
            throw new DacoException(DacoException.ErrorType.EMPTY_DATE);
        } catch (DacoException e) {
            throw new DacoException(e.getType());
        }
    }
    /**
     * Checks that the input given has a non-empty task, otherwise throws the empty task exception
     *
     * @param input String of user's input during typing their entire command
     * @throws DacoException when is empty
     */
    public void verifyTask(String input) throws DacoException {
        String temp = input.replaceAll("\\s", "");
        if (temp.isEmpty()) {
            this.errors(new DacoException(DacoException.ErrorType.EMPTY_TASK));
        }
    }
    /**
     * Checks that the input given has a non-empty task, otherwise throws the invalid format or invlaid number error
     *
     * @param input String of user's input during typing their entire command
     * @throws DacoException when is incorrect
     */
    public String[] verifyDeleteFormat(String input) throws DacoException {
        String[] command = input.split(" ");
        if (command.length != 2) {
            this.errors(new DacoException(DacoException.ErrorType.INVALID_FORMAT_DELETE));
        }
        try {
            Integer.parseInt(command[1]);
            return command;
        } catch (NumberFormatException e) {
            this.errors(new DacoException(DacoException.ErrorType.INVALID_NUMBER));
        }
        return command;
    }
    /**
     * Checks that the input given access a correct / available item
     *
     * @param number adjusted to follow list numberings
     * @param list to do list
     * @throws DacoException when is empty
     */
    public void existItem(int number, ArrayList<Task> list) throws DacoException {
        if (number <= 0 || number > list.size()) {
            this.errors(new DacoException(DacoException.ErrorType.DOES_NOT_EXIST));
        }
    }
    /**
     * Prints the output for all errors
     *
     * @param e takes in the DacoException to check for the specific type before outputting message
     * @throws DacoException
     */
    public String errors(DacoException e) {
        if (e.getType() == DacoException.ErrorType.INVALID_NUMBER) {
            return dacoResponse("Please input a valid number." + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.DOES_NOT_EXIST) {
            return dacoResponse("Nothing there... " + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.INVALID_COMMANDMARK) {
            return dacoResponse("Hey buddy, think you typed it wrongly..." + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.UNKNOWN_COMMAND) {
            return dacoResponse("That's a negative, I can't do that...yet " + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.EMPTY_TASK) {
            return dacoResponse("Uh.. there's no task name... " + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.EMPTY_DATE) {
            return dacoResponse("Missing date... " + randomResponse(sadFaces));
        }
        if (e.getType() == DacoException.ErrorType.INVALID_FORMAT_DELETE) {
            return dacoResponse("Please input correctly, for example "
                    + "'delete 2' to delete the second character! " + randomResponse(sadFaces));
        }
        return dacoResponse("No error found");
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
}
