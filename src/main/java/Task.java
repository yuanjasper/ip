public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return this.description;
    }

    public String display() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public Task markasDone() {
        return new Task(this.description, true);
    }

    public Task markasNotDone() {
        return new Task(this.description, false);
    }
}
