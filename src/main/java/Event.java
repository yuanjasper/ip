public class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }

    public String toString() {
        return super.toString();
    }

    public String display() {
        return "[E]" + super.display() + " (" + this.duration + ")";
    }

    public Event markasDone() {
        return new Event(this.description, true, this.duration);
    }

    public Event markasNotDone() {
        return new Event(this.description, false, this.duration);
    }

    public String saveinfile() {
        return "E | " + (super.getisDone() ? 1 : 0) + " | " + this.description + " | " + this.duration + "\n";
    }
}

