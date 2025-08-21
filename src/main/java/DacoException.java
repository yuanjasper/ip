public class DacoException extends Exception {
    public enum ErrorType {DOES_NOT_EXIST, INVALID_COMMANDMARK, UNKNOWN_COMMAND, INVALID_NUMBER, EMPTY_TASK, EMPTY_DATE}
    private final ErrorType type;

    public DacoException(ErrorType type) {
        super("");
        this.type = type;
    }

    public ErrorType getType() {
        return this.type;
    }
}
