public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return super.toString();
    }

    public String display() {
        return "[T]" + super.display();
    }

    public ToDos markasDone() {
        return new ToDos(this.description, true);
    }

    public ToDos markasNotDone() {
        return new ToDos(this.description, false);
    }
}

