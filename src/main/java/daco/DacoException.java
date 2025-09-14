package daco;

/**
 * An exception class made to handle common errors within Daco chatbot
 * Handles the different type, able to get the specific type
 */

public class DacoException extends Exception {
    /**
     * Enumeration of all the ErrorTypes
     */
    public enum ErrorType { DOES_NOT_EXIST, INVALID_COMMANDMARK, UNKNOWN_COMMAND,
        INVALID_NUMBER, EMPTY_TASK, EMPTY_DATE, INVALID_FORMAT_DELETE, EMPTY_COMMAND, INCOMPLETE_FIND }
    protected final ErrorType type;

    /**
     * Returns the constructor for DacoException class
     *
     * @param type is of ErrorType
     */
    public DacoException(ErrorType type) {
        super("");
        this.type = type;
    }

    /**
     * Returns ErrorType of a DacoException object
     */
    public ErrorType getType() {
        return this.type;
    }
}

