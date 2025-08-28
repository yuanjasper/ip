package daco;

public class Deadline extends Task {
    protected DateAndTime deadline;

    public Deadline(String description, String deadline) throws DacoException {
        super(description);
        this.deadline = new DateAndTime(deadline);
    }

    public Deadline(String description, boolean isDone, DateAndTime deadline) throws DacoException {
        super(description, isDone);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) throws DacoException {
        super(description, isDone);
        this.deadline = new DateAndTime(deadline);
    }

    public String toString() {
        return super.toString();
    }

    public String display() {
        return "[D]" + super.display()  + " (Due: " + this.deadline.display() + ")";
    }

    public Deadline markasDone() throws DacoException {
        return new Deadline(this.description, true, this.deadline);
    }

    public Deadline markasNotDone() throws DacoException {
        return new Deadline(this.description, false, this.deadline);
    }

    public String saveinfile() {
        return "D | " + (super.getisDone() ? 1 : 0) + " | " + this.description + " | " + this.deadline.save() + "\n";
    }
}

