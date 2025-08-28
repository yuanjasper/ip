package Daco.Ui;

import java.util.Random;

public class Parser {
    public final String[] sadfaces = {"（◞‸◟）", "(˘︹˘)", "( ;︵; )", "（；_・）", "(ノ_ヽ)"};
    public final String LINESEP = "____________________________________________________________\n";

    public void validdate(String input) throws DacoException {
        try {
            String[] temp = input.split(", ");
            validtask(temp[0]);
            validtask(temp[1]);
            new DateAndTime(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.errors(new DacoException(DacoException.ErrorType.EMPTY_DATE));
        } catch(DacoException e) {
            this.errors(new DacoException(e.getType()));
        }
    }

    public void validtask(String input) throws DacoException {
        String temp = input.replaceAll("\\s", "");
        if (temp.isEmpty()) {
            this.errors(new DacoException(DacoException.ErrorType.EMPTY_TASK));
        }
    }

    public void errors(DacoException e) throws DacoException {
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
        throw new DacoException(e.getType());
    }

    public void dacoresponse(String input) {
        System.out.println(LINESEP + input + "\n" + LINESEP);
    }

    public String randomresponse(String[] responses) {
        Random random = new Random();
        return responses[random.nextInt(responses.length)];
    }
}
