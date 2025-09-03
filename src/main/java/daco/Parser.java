/**
 * Main class for handling proper logic for inadequate, incomplete commands
 */
package daco;
import java.util.Random;
import java.util.ArrayList;

public class Parser {
    public final String[] sadfaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};
    public final String LINESEP = "____________________________________________________________\n";
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
        } catch (ArrayIndexOutOfBoundsException e) {
            this.errors(new DacoException(DacoException.ErrorType.EMPTY_DATE));
        } catch(DacoException e) {
            this.errors(new DacoException(e.getType()));
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
    public void errors(DacoException e) throws DacoException {
        if (e.getType() == DacoException.ErrorType.INVALID_NUMBER) {
            dacoResponse("Please input a valid number." + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.DOES_NOT_EXIST) {
            dacoResponse("Nothing there... " + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.INVALID_COMMANDMARK) {
            dacoResponse("Hey buddy, think you typed it wrongly..." + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.UNKNOWN_COMMAND) {
            dacoResponse("That's a negative, I can't do that...yet " + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.EMPTY_TASK) {
            dacoResponse("Uh.. there's no task name... " + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.EMPTY_DATE) {
            dacoResponse("Missing date... " + randomResponse(sadfaces));
        }
        if (e.getType() == DacoException.ErrorType.INVALID_FORMAT_DELETE) {
            dacoResponse("Please input correctly, for example 'delete 2' to delete the second character! " + randomResponse(sadfaces));
        }

        throw new DacoException(e.getType());
    }
    /**
     * Prints the standard chatbot output
     *
     * @param input what you want the chatbot to say besides the design stuff
     */
    public void dacoResponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
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
