/** Extension of Task to be placed in a to do list, event represents when the event starts
 *
 * handles formatting to be saved in file for entire row
 */

package daco;

public class Event extends Task {
    protected DateAndTime duration;

    public Event(String description, String duration) throws DacoException {
        super(description);
        this.duration = new DateAndTime(duration);
    }

    public Event(String description, boolean isDone, String duration) throws DacoException {
        super(description, isDone);
        this.duration = new DateAndTime(duration);
    }

    public Event(String description, boolean isDone, DateAndTime duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public String toString() {
        return super.toString();
    }

    public String display() {
        return "[E]" + super.display() + " (" + this.duration.display() + ")";
    }

    public Event markasDone() {
        return new Event(this.description, true, this.duration);
    }

    public Event markasNotDone() {
        return new Event(this.description, false, this.duration);
    }

    public String saveinfile() {
        return "E | " + (super.getisDone() ? 1 : 0) + " | " + this.description + " | " + this.duration.save() + "\n";
    }
}

