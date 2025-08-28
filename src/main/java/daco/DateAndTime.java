package daco;
/**
 * Handles date and time formatting for tasks that are to be put inside the tasklist
 *
 * Two main features are formatting the display date, and formatting the dates during save
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    private final LocalDateTime datetime;

    private static final DateTimeFormatter[] INPUT_FORMATS = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"), DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
    };

    public DateAndTime(String datetime) throws DacoException {
        this.datetime = formatDate(datetime);
    }

    public LocalDateTime formatDate(String input) throws DacoException {
        for (DateTimeFormatter formats : INPUT_FORMATS) {
            try {
                return LocalDateTime.parse(input.trim(), formats);
            } catch (Exception ignored) {

            }
        }
        throw new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK);
    }

    public String display() {
        return this.datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    public String save() {
        return this.datetime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

}

