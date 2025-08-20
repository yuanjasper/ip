import java.util.*;

public class Daco {
    public static final String LINESEP = "____________________________________________________________\n";
    public static final String[] neutralfaces = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)"};
    public static final String[] sadfaces = {"（◞‸◟）", "へ[ •́ ‸ •̀ ]ʋ"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] todolist = new String[100];
        int counter = 0;
        String logo;
        logo = " ____                  \n" +
                "|  _ \\  __ _  ___ ___  \n" +
                "| | | |/ _` |/ __/ _ \\ \n" +
                "| |_| | (_| | (_| (_) |\n" +
                "|____/ \\__,_|\\___\\___/ \n";
        System.out.println(logo + LINESEP);
        System.out.println("Hi there, I'm Daco! How can I help.\n" + LINESEP);

        while (true) {
            String userinput = sc.nextLine();
            if (userinput.equals("bye")) {
                break;
            }
            if (userinput.equals("list")) {
                showlist(todolist, counter);
                continue;
            }
            todolist[counter] = userinput;
            counter++;
            System.out.println(LINESEP + "added: " + userinput + " " + randomresponse(neutralfaces) + "\n" + LINESEP);
        }
        System.out.println(LINESEP + "Come back anytime. " + randomresponse(sadfaces) + "\n" + LINESEP);


    }

    public static String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }

    public static void showlist(String[] list, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("Item #" + (i + 1) + ": " + list[i]);
        }
        System.out.println(randomresponse(neutralfaces));
    }
}

