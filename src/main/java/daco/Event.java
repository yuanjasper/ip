/** Extension of Task to be placed in a to do list, event represents when the event starts
 *
 * handles formatting to be saved in file for entire row
 */

package daco;

public class Event extends Task {
    protected DateAndTime duration;
    /**
     * Returns Event constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param duration is to indicate the duration of the task
     * @throws DacoException if the duration is an of wrong format
     */
    public Event(String description, String duration) throws DacoException {
        super(description);
        this.duration = new DateAndTime(duration);
    }
    /**
     * Returns Event constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param duration is to indicate the duration of the task
     * @throws DacoException if the duration is an of wrong format
     */
    public Event(String description, boolean isDone, String duration) throws DacoException {
        super(description, isDone);
        this.duration = new DateAndTime(duration);
    }
    /**
     * Returns Event constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param duration is to indicate the duration of the task
     */
    public Event(String description, boolean isDone, DateAndTime duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public String toString() {
        return super.toString();
    }
    /**
     * Returns the format for the task to be printed out
     */
    public String display() {
        return "[E]" + super.display() + " (" + this.duration.display() + ")";
    }
    /**
     * Returns new Event object after being marked done
     */
    public Event markAsDone() {
        return new Event(this.description, true, this.duration);
    }
    /**
     * Returns new Event object after being unmarked
     */
    public Event markAsNotDone() {
        return new Event(this.description, false, this.duration);
    }
    /**
     * Returns the format for the task to be in during saving of the to do list
     */
    public String formatToSaveInFile() {
        return "E | " + (super.isDone() ? 1 : 0) + " | " + this.description + " | " + this.duration.saveFormat() + "\n";
    }
}

