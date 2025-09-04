package daco;

import java.util.ArrayList;
import java.util.Random;
/**
 * Representation of the to do list, contains only Task class items
 * Handles functions related to manipulation of data in the to do list.
 */
public class TaskList {
    public final ArrayList<Task> list;
    public final String lineSep = "________________________________________________________\n";
    public final String[] neutralFaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public final String[] sadFaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};


    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
    /**
     * Prints out either two states of the list, empty or non-empty
     *
     */
    public String showList() {
        if (this.list.isEmpty()) {
            return dacoResponse("List is empty!" + randomResponse(sadFaces));
        }
        String listToPrint = lineSep + "Here's your list!\n";
        for (int i = 0; i < this.list.size(); i++) {
            listToPrint = listToPrint + "Item #" + (i + 1) + ": " + this.list.get(i).display() + "\n";
        }
        return listToPrint + randomResponse(neutralFaces) + "\n" + lineSep;
    }
    /**
     * Deletes the index of list, using Parser to handle invalid inputs
     *
     * @param input list item number
     */
    public String delete(String input, Storage loadedfile) throws DacoException {
        String[] command = new Parser().verifyDeleteFormat(input);
        int number = Integer.parseInt(command[1]);
        new Parser().existItem(number, this.list);
        Task removedtask = this.list.get(number - 1);
        this.list.remove(number - 1);
        loadedfile.save(this.list);
        return dacoResponse("Task removed! " + randomResponse(neutralFaces)
                    + "\n" + removedtask.display() + "\n" + this.itemsInList());
    }
    /**
     * Marks or unmarks the item on the list using list item number
     *
     * @param input list item number
     * @param isDone state of what the object should be in
     */
    public String mark(String input, boolean isDone, Storage loadedfile) throws DacoException {
        String[] command = new Parser().verifyDeleteFormat(input);
        int number = Integer.parseInt(command[1]);
        new Parser().existItem(number, this.list);

        if (isDone) {
            this.list.set(number - 1, this.list.get(number - 1).markAsDone());
            loadedfile.save(this.list);
            return dacoResponse("Marked the task! " + randomResponse(neutralFaces));
        } else {
            this.list.set(number - 1, this.list.get(number - 1).markAsNotDone());
            loadedfile.save(this.list);
            return dacoResponse("Unmarked the task! "
                    + randomResponse(neutralFaces) + "\n" + this.list.get(number - 1).display());
        }
    }
    /**
     * Prints the standard chatbot output
     *
     * @param input what you want the chatbot to say besides the design stuff
     */
    public String dacoResponse(String input) {
        return lineSep + input + "\n" + lineSep;
    }
    /**
     * Returns a String of what the bot should say based on the number of items there are in the list
     */
    public String itemsInList() {
        int counter = this.list.size();
        return "\nThere " + (counter == 1 ? "is " + counter
                + " item" : "are " + counter + " items") + " in the list! " + randomResponse(neutralFaces);
    }
    /**
     * Returns a String of a random response from an array of faces
     *
     * @param responses the array list of faces you wish to choose from
     */
    public String randomResponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }
    /**
     * Adds task to the list
     *
     * @param task the task that you want to add
     */
    public String add(Task task, Storage loadedfile) {
        this.list.add(task);
        loadedfile.save(this.list);
        return dacoResponse("The following task has been added:\n" + this.getLast().display() + this.itemsInList());
    }

    public Task getLast() {
        return this.list.get(this.list.size() - 1);
    }
    /**
     * finds items based on common description given the input
     *
     * @param description what the user wants to search
     */
    public String findByDescription(String description) {
        boolean isEmpty = true;
        String output = "";
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).contains(description)) {
                isEmpty = false;
                output = output + "\n" + "Item #" + (i + 1) + ": " + this.list.get(i).display();
            }
        }
        if (isEmpty) {
            return dacoResponse("Nothing matches your search...");
        } else {
            return dacoResponse("Here's what we got!" + output);
        }
    }
}
