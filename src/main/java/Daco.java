import java.util.*;

public class Daco {
    public static final String LINESEP = "____________________________________________________________\n";
    public static final String[] neutralfaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public static final String[] sadfaces = {"（◞‸◟）", "へ[ •́ ‸ •̀ ]ʋ", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] todolist = new Task[100];
        int counter = 0;
        String logo = " ____                  \n" +
                "|  _ \\  __ _  ___ ___  \n" +
                "| | | |/ _` |/ __/ _ \\ \n" +
                "| |_| | (_| | (_| (_) |\n" +
                "|____/ \\__,_|\\___\\___/ \n \n";

        dacoresponse(logo + "Hi there, I'm Daco! How can I help?");

        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                break;
            }
            if (userinput.equals("list")) {
                showlist(todolist, counter);
                continue;
            }
            if (userinput.startsWith("mark ")) {
                mark(todolist, userinput, true);
                continue;
            }
            if (userinput.startsWith("unmark ")) {
                mark(todolist, userinput, false);
                continue;
            }
            todolist[counter] = new Task(userinput);
            counter++;
            dacoresponse("added: " + userinput + " " + randomresponse(neutralfaces));
        }
        dacoresponse("Come back anytime. " + randomresponse(sadfaces));
    }

    public static String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    public static void showlist(Task[] list, int size) {
        if (size == 0) {
            dacoresponse("List is empty!" + randomresponse(sadfaces));
            return;
        }
        System.out.print(LINESEP + "Here's your list!\n");
        for (int i = 0; i < size; i++) {
            System.out.println("Item #" + (i + 1) + ": " + list[i].display());
        }
        System.out.println(randomresponse(neutralfaces) + "\n" + LINESEP);
    }

    public static void mark(Task[] list, String input, boolean isDone) {
        String[] command = input.split(" ");
        if (command.length != 2) {
            dacoresponse("Please input correctly, for example 'mark 2' to mark off the second character! " + randomresponse(sadfaces));
        } else {
            try {
                int number = Integer.parseInt(command[1]);
                if (number == 0) {
                    throw new NumberFormatException();
                }
                if (number < 100 && !(list[number - 1] == null)) {
                    if (isDone) {
                        list[number - 1] = list[number - 1].markasDone();
                        dacoresponse("Marked the task! " + randomresponse(neutralfaces) + "\n" + list[number - 1].display());
                    } else {
                        list[number - 1] = list[number - 1].markasNotDone();
                        dacoresponse("Unmarked the task! " + randomresponse(neutralfaces) + "\n" + list[number - 1].display());
                    }
                } else {
                    dacoresponse("Nothing there..." + randomresponse(sadfaces));
                }
            } catch (NumberFormatException e) {
                dacoresponse("Please input a valid number" + randomresponse(sadfaces));
            }
        }
    }

    public static void dacoresponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
    }
}

