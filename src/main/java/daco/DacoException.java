package daco;

/**
 * An exception class made to handle common errors within Daco chatbot
 * Handles the different type, able to get the specific type
 */

public class DacoException extends Exception {
    public enum ErrorType {DOES_NOT_EXIST, INVALID_COMMANDMARK, UNKNOWN_COMMAND, INVALID_NUMBER, EMPTY_TASK, EMPTY_DATE, INVALID_FORMAT_DELETE}
    protected final ErrorType type;

    public DacoException(ErrorType type) {
        super("");
        this.type = type;
    }

    public ErrorType getType() {
        return this.type;
    }
}

