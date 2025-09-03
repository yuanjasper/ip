package daco;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Handles date and time formatting for tasks that are to be put inside the tasklist
 * Two main features are formatting the display date, and formatting the dates during save
 */
public class DateAndTime {

    private final LocalDateTime datetime;

    private final DateTimeFormatter[] inputFormats = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"), DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
    };
    /**
     * Returns the constructor for DateAndTime class
     *
     * @param datetime String format of the date and time input
     */
    public DateAndTime(String datetime) throws DacoException {
        this.datetime = formatDate(datetime);
    }
    /**
     * Returns LocalDateTime if the string input matches the enumerated INPUT_FORMATS
     * Otherwise, throws a DacoException indicating invalid command mark
     *
     * @param input String format of the date and time input
     */
    public LocalDateTime formatDate(String input) throws DacoException {
        for (DateTimeFormatter formats : inputFormats) {
            try {
                return LocalDateTime.parse(input.trim(), formats);
            } catch (Exception ignored) {
                throw new DacoException(DacoException.ErrorType.INVALID_COMMANDMARK);
            }
        }
        return this.datetime;
    }
    /**
     * Returns String to be printed, similar to a toString method
     */
    public String display() {
        return this.datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }
    /**
     * Returns String to format date and time during saving of the to do list
     */
    public String saveFormat() {
        return this.datetime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

}

