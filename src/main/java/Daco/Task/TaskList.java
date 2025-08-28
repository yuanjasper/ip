package Daco.Task;
import Daco.Ui.DacoException;
import java.util.ArrayList;
import java.util.Random;

public class TaskList {
    public final ArrayList<Task> list;
    public final String LINESEP = "____________________________________________________________\n";
    public final String[] neutralfaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public final String[] sadfaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};


    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getlist() {
        return this.list;
    }

    public void showlist() {
        if (this.list.isEmpty()) {
            dacoresponse("List is empty!" + randomresponse(sadfaces));
            return;
        }
        System.out.print(LINESEP + "Here's your list!\n");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("Item #" + (i + 1) + ": " + this.list.get(i).display());
        }
        System.out.println(randomresponse(neutralfaces) + "\n" + LINESEP);
    }

    public void delete(String input) throws DacoException {
        String[] command = input.split(" ");
        if (command.length != 2) {
            dacoresponse("Please input correctly, for example 'mark 2' to mark off the second character! " + randomresponse(sadfaces));
        } else {
            try {
                int number = Integer.parseInt(command[1]);
                if (number <= 0) {
                    throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
                }
                if (number > this.list.size()) {
                    throw new DacoException(DacoException.ErrorType.DOES_NOT_EXIST);
                } else {
                    this.list.remove(number - 1);
                    dacoresponse("Task removed! " + randomresponse(neutralfaces) + "\n" + this.list.get(number - 1).display() + "\n" + this.itemsinlist());
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
            }
        }
    }

    public void mark(String input, boolean isDone) throws DacoException {
        String[] command = input.split(" ");
        if (command.length != 2) {
            dacoresponse("Please input correctly, for example 'mark 2' to mark off the second character! " + randomresponse(sadfaces));
        } else {
            try {
                int number = Integer.parseInt(command[1]);
                if (number <= 0) {
                    throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
                }
                if (!(this.list.get(number - 1) == null)) {
                    if (isDone) {
                        this.list.set(number - 1, this.list.get(number - 1).markasDone());
                        dacoresponse("Marked the task! " + randomresponse(neutralfaces)
                                + "\n" + this.list.get(number - 1).display() + "\nWould you like to delete the task? (Y/N)");
                    } else {
                        this.list.set(number - 1, this.list.get(number - 1).markasNotDone());
                        dacoresponse("Unmarked the task! " + randomresponse(neutralfaces) + "\n" + this.list.get(number - 1).display());
                    }
                } else {
                    throw new DacoException(DacoException.ErrorType.DOES_NOT_EXIST);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
            }
        }
    }

    public void dacoresponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
    }

    public String itemsinlist() {
        int counter = this.list.size();
        return "\nThere " + (counter == 1 ? "is " + counter + " item" : "are " + counter + " items") + " in the list! " + randomresponse(neutralfaces);
    }

    public String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    public void add(Task task) {
        this.list.add(task);
        dacoresponse("The following task has been added:\n" + this.getLast().display() + this.itemsinlist());
    }

    public Task getLast() {
        return this.list.getLast();
    }
}