import java.util.*;

public class Daco {
    public static final String LINESEP = "____________________________________________________________\n";
    public static final String[] neutralfaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public static final String[] sadfaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todolist = new ArrayList<>();
        String logo = " ____                  \n" +
                "|  _ \\  __ _  ___ ___  \n" +
                "| | | |/ _` |/ __/ _ \\ \n" +
                "| |_| | (_| | (_| (_) |\n" +
                "|____/ \\__,_|\\___\\___/ \n \n";

        dacoresponse(logo + "Hi there, I'm Daco! How can I help?");

        while (true) {
            try {
                String userinput = sc.nextLine();
                if (userinput.equals("bye")) {
                    break;
                }
                if (userinput.equals("list")) {
                    showlist(todolist);
                    continue;
                }
                if (userinput.startsWith("mark ")) {
                    mark(todolist, userinput, true);
                    String yesorno = sc.nextLine();
                    if (yesorno.equals("Y")) {
                        delete(todolist, userinput);
                        continue;
                    }
                    continue;
                }
                if (userinput.startsWith("unmark ")) {
                    mark(todolist, userinput, false);
                    continue;
                }
                if (userinput.startsWith("todo ")) {
                    validtask(userinput.substring(5));
                    todolist.add(new ToDos(userinput.substring(5)));
                    dacoresponse("The following task has been added:\n" + todolist.getLast().display() + itemsinlist(todolist.size()));
                    continue;
                }
                if (userinput.startsWith("deadline ")) {
                    validdate(userinput.substring(9));
                    String[] temp = userinput.substring(9).split(",");
                    validtask(temp[0]);
                    todolist.add(new Deadline(temp[0],temp[1]));
                    dacoresponse("The following task has been added:\n" + todolist.getLast().display() + itemsinlist(todolist.size()));
                    continue;
                }
                if (userinput.startsWith("event ")) {
                    validdate(userinput.substring(6));
                    String[] temp = userinput.substring(6).split(", ");
                    validtask(temp[0]);
                    todolist.add(new Event(temp[0],temp[1]));
                    dacoresponse("The following task has been added:\n" + todolist.getLast().display() + itemsinlist(todolist.size()));
                    continue;
                }
                if (userinput.startsWith("delete ")) {
                    delete(todolist, userinput);
                    continue;
                }
                throw new DacoException(DacoException.ErrorType.UNKNOWN_COMMAND);
            } catch (DacoException e) {
                if (e.getType() == DacoException.ErrorType.INVALID_NUMBER) {
                    dacoresponse("Please input a valid number." + randomresponse(sadfaces));
                }
                if (e.getType() == DacoException.ErrorType.DOES_NOT_EXIST) {
                    dacoresponse("Nothing there... " + randomresponse(sadfaces));
                }
                if (e.getType() == DacoException.ErrorType.INVALID_COMMANDMARK) {
                    dacoresponse("Hey buddy, think you typed it wrongly..." + randomresponse(sadfaces));
                }
                if (e.getType() == DacoException.ErrorType.UNKNOWN_COMMAND) {
                    dacoresponse("That's a negative, I can't do that...yet " + randomresponse(sadfaces));
                }
                if (e.getType() == DacoException.ErrorType.EMPTY_TASK) {
                    dacoresponse("Uh.. there's no task name... " + randomresponse(sadfaces));
                }
                if (e.getType() == DacoException.ErrorType.EMPTY_DATE) {
                    dacoresponse("Missing date... " + randomresponse(sadfaces));
                }

            }
        }
        dacoresponse("Come back anytime. " + randomresponse(sadfaces));
    }

    public static String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    public static void showlist(ArrayList<Task> list) {
        if (list.isEmpty()) {
            dacoresponse("List is empty!" + randomresponse(sadfaces));
            return;
        }
        System.out.print(LINESEP + "Here's your list!\n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Item #" + (i + 1) + ": " + list.get(i).display());
        }
        System.out.println(randomresponse(neutralfaces) + "\n" + LINESEP);
    }

    public static void delete(ArrayList<Task> list, String input) throws DacoException {
        String[] command = input.split(" ");
        if (command.length != 2) {
            dacoresponse("Please input correctly, for example 'mark 2' to mark off the second character! " + randomresponse(sadfaces));
        } else {
            try {
                int number = Integer.parseInt(command[1]);
                if (number <= 0) {
                    throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
                }
                if (number > list.size()) {
                    throw new DacoException(DacoException.ErrorType.DOES_NOT_EXIST);
                } else {
                    dacoresponse("Task removed! " + randomresponse(neutralfaces) + "\n" + list.get(number - 1).display() + "\n" + itemsinlist(list.size() - 1));
                    list.remove(number - 1);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
            }
        }
    }

    public static void mark(ArrayList<Task> list, String input, boolean isDone) throws DacoException {
        String[] command = input.split(" ");
        if (command.length != 2) {
            dacoresponse("Please input correctly, for example 'mark 2' to mark off the second character! " + randomresponse(sadfaces));
        } else {
            try {
                int number = Integer.parseInt(command[1]);
                if (number <= 0) {
                    throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
                }
                if (!(list.get(number-1) == null)) {
                    if (isDone) {
                        list.set(number - 1, list.get(number-1).markasDone());
                        dacoresponse("Marked the task! " + randomresponse(neutralfaces)
                                + "\n" + list.get(number - 1).display() + "\nWould you like to delete the task? (Y/N)");
                    } else {
                        list.set(number - 1, list.get(number-1).markasNotDone());
                        dacoresponse("Unmarked the task! " + randomresponse(neutralfaces) + "\n" + list.get(number - 1).display());
                    }
                } else {
                    throw new DacoException(DacoException.ErrorType.DOES_NOT_EXIST);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DacoException(DacoException.ErrorType.INVALID_NUMBER);
            }
        }
    }

    public static void dacoresponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
    }

    public static String itemsinlist(int counter) {
        return "\nThere " + (counter == 1 ? "is " + counter + " item" : "are " + counter + " items") +  " in the list! " + randomresponse(neutralfaces);
    }

    public static void validtask(String input) throws DacoException {
        String temp = input.replaceAll("\\s", "");
        if (temp.isEmpty()) {
            throw new DacoException(DacoException.ErrorType.EMPTY_TASK);
        }
    }

    public static void validdate(String input) throws DacoException {
        try {
            String[] temp = input.split(", ");
            validtask(temp[0]);
            validtask(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DacoException(DacoException.ErrorType.EMPTY_DATE);
        }
    }
}

