/**
 * Extension of Task to be placed in a to do list, deadline represents by when to be completed
 *
 * handles formatting to be saved in file for entire row
 */
package daco;

public class Deadline extends Task {
    protected DateAndTime deadline;

    /**
     * Returns Deadline constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param deadline is to indicate the deadline of the task
     * @throws DacoException if the deadline is an of wrong format
     */
    public Deadline(String description, String deadline) throws DacoException {
        super(description);
        this.deadline = new DateAndTime(deadline);
    }
    /**
     * Returns Deadline constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param isDone is to describe the task being marked off or on
     * @param deadline is to indicate the deadline of the task
     */
    public Deadline(String description, boolean isDone, DateAndTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }
    /**
     * Returns Deadline constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param isDone is to describe the task being marked off or on
     * @param deadline is to indicate the deadline of the task
     * @throws DacoException if the deadline is an of wrong format
     */
    public Deadline(String description, boolean isDone, String deadline) throws DacoException {
        super(description, isDone);
        this.deadline = new DateAndTime(deadline);
    }

    public String toString() {
        return super.toString();
    }
    /**
     * Returns the format for the task to be printed out
     */
    public String display() {
        return "[D]" + super.display()  + " (Due: " + this.deadline.display() + ")";
    }
    /**
     * Returns new Deadline object after being marked done
     */
    public Deadline markAsDone() throws DacoException {
        return new Deadline(this.description, true, this.deadline);
    }
    /**
     * Returns new Deadline object after being unmarked
     */
    public Deadline markAsNotDone() throws DacoException {
        return new Deadline(this.description, false, this.deadline);
    }
    /**
     * Returns the format for the task to be in during saving of the to do list
     */
    public String formatToSaveInFile() {
        return "D | " + (super.isDone() ? 1 : 0) + " | " + this.description + " | " + this.deadline.saveFormat() + "\n";
    }
}

