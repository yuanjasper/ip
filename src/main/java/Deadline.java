public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString();
    }

    public String display() {
        return "[D]" + super.display()  + " (Due:" + this.deadline + ")";
    }

    public Deadline markasDone() {
        return new Deadline(this.description, true, this.deadline);
    }

    public Deadline markasNotDone() {
        return new Deadline(this.description, false, this.deadline);
    }

    public String saveinfile() {
        return "D | " + (super.getisDone() ? 1 : 0) + " | " + this.description + " | " + this.deadline + "\n";
    }
}

