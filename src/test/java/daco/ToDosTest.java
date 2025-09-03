package daco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDosTest {
    @Test
    public void saveinfile_notdone() {
        ToDos t = new ToDos("test", false);
        assertEquals("T | 0 | " + "test" + "\n", t.formatToSaveInFile());
    }
    @Test
    public void saveinfile_emptystring() {
        ToDos t = new ToDos("", false);
        assertEquals("T | 0 | " + "" + "\n", t.formatToSaveInFile());
    }
}
