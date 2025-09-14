package daco;
/**
 * Most general class for items to be placed into the to do list
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Returns Task constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns Deadline constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param isDone is to indicate whether the task is marked or not
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Returns an X if marked, nothing is not marked
     *
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return this.description;
    }
    /**
     * Returns the format for the task to be printed out
     */
    public String display() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Returns new Task object after being marked done
     */
    public Task markAsDone() throws DacoException {
        return new Task(this.description, true);
    }
    /**
     * Returns new Task object after being unmarked
     */
    public Task markAsNotDone() throws DacoException {
        return new Task(this.description, false);
    }

    /**
     * Returns the state of the object's marked or unmarked
     *
     */
    public boolean isDone() {
        return this.isDone;
    }
    /**
     * Returns the string for when the task is saved to file
     * Code should not run at all time as Task object itself won't be saved
     */
    public String formatToSaveInFile() {
        return "Error";
    }
    /**
     * Returns true false if the description of the object contains the input
     *
     * @param input something you are trying to find about the description of a task
     */
    public boolean contains(String input) {
        assert input != null && !input.isEmpty();
        return (" " + this.description).toLowerCase().contains(input.toLowerCase());
    }

    public String getType() {
        return "Task";
    }

    DateAndTime getTime() {
        return null;
    }
}

