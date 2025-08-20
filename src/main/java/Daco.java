import java.util.*;

public class Daco {
    static String LINESEP = "____________________________________________________________\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String neutralfaces[] = {"(´⌣`ʃƪ)", "| (• ◡•)|", "(◌˘◡˘◌)"};
        String sadfaces[] = {"（◞‸◟）", "へ[ •́ ‸ •̀ ]ʋ"};
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
            System.out.println(LINESEP + userinput + " " + selectface(neutralfaces) + "\n" + LINESEP);
        }
        System.out.println(LINESEP + "Come back anytime. " + selectface(sadfaces) +"\n" + LINESEP);
    }

    public static String selectface(String[] emotions) {
        Random random = new Random();
        return emotions[random.nextInt(emotions.length)];
    }
}
