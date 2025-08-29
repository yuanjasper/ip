/**
 * Handles the input from user but does not check whether a command is available or correct
 * Programme does the responses for correct execution of input.
 */

package daco;
import java.util.Random;
import java.util.Scanner;


public class Ui {
    public final String LINESEP = "____________________________________________________________\n";
    public final String[] neutralfaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)", "(￣▽￣)ノ", "(ㆆᴗㆆ)", "(⌒ω⌒)ﾉ"};
    public final String[] sadfaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};

    public Ui() {
        String logo = " ____                  \n" +
                "|  _ \\  __ _  ___ ___  \n" +
                "| | | |/ _` |/ __/ _ \\ \n" +
                "| |_| | (_| | (_| (_) |\n" +
                "|____/ \\__,_|\\___\\___/ \n \n";

        dacoresponse(logo + "Hi there, I'm Daco! How can I help?");
    }

    public void dacoresponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
    }

    public String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    public void bye() {
            dacoresponse("Come back anytime. " + randomresponse(sadfaces));
    }

    public void input(String userinput, TaskList todolist, Scanner sc) {
        try {
            if (userinput.equals("list")) {
                todolist.showlist();
            }
            else if (userinput.startsWith("mark ")) {
                if (todolist.mark(userinput, true)) {
                    String option = sc.nextLine();
                    if (option.equals("Y")) {
                        todolist.delete(userinput);
                    } else {
                        dacoresponse("Okay, I won't do anything! " + randomresponse(neutralfaces));
                    }
                }
            }
            else if (userinput.startsWith("unmark ")) {
                todolist.mark(userinput, false);
            }
            else if (userinput.startsWith("todo ")) {
                new Parser().validtask(userinput.substring(5));
                todolist.add(new ToDos(userinput.substring(5)));
            }
            else if (userinput.startsWith("deadline ")) {
                new Parser().validdate(userinput.substring(9));
                String[] temp = userinput.substring(9).split(",");
                new Parser().validtask(temp[0]);
                todolist.add(new Deadline(temp[0], temp[1]));
            }
            else if (userinput.startsWith("event ")) {
                new Parser().validdate(userinput.substring(6));
                String[] temp = userinput.substring(6).split(", ");
                new Parser().validtask(temp[0]);
                todolist.add(new Event(temp[0], temp[1]));
            }
            else if (userinput.startsWith("delete ")) {
                todolist.delete(userinput);
            }
            else if (userinput.startsWith("find ")) {
                String tasktofind = userinput.substring(5);
                new Parser().validtask(tasktofind);
                todolist.find(tasktofind);
            }else {
                new Parser().errors(new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK));
            }
        } catch (DacoException e) {
            return;
        }
    }

    public void test() {
        System.out.println("test");
    }
}

