/** Extension of Task to be placed in a to do list, todos represents basic tasks
 *
 * handles formatting to be saved in file for entire row
 */
package daco;
public class ToDos extends Task {
    /**
     * Returns ToDos constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     */
    public ToDos(String description) {
        super(description);
    }
    /**
     * Returns Deadline constructor class, assisted by Task class constructor
     *
     * @param description  is to describe the task
     * @param isDone is to indicate whether the task is marked or not
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return super.toString();
    }
    /**
     * Returns the format for the task to be printed out
     */
    public String display() {
        return "[T]" + super.display();
    }
    /**
     * Returns new Todos object after being marked done
     */
    public ToDos markAsDone() {
        return new ToDos(this.description, true);
    }
    /**
     * Returns new Todos object after being unmarked
     */
    public ToDos markAsNotDone() {
        return new ToDos(this.description, false);
    }
    /**
     * Returns the format for the task to be in during saving of the to do list
     */
    public String formatToSaveInFile() {
        return "T | " + (super.isDone() ? 1 : 0) + " | " + this.description + "\n";
    }
}

