package daco;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void validitemdate_moregaps() {
        try {
            assertEquals(new String[] {"1"}, new Parser().verifyDeleteFormat("delete 3 5 1"));
            fail();
        } catch (DacoException e) {
            assertEquals(1,1);
        }
    }
    @Test
    public void validitemdate_emptystring() {
        try {
            assertEquals(new String[] {""}, new Parser().verifyDeleteFormat(""));
            fail();
        } catch (DacoException e) {
            assertEquals(1,1);
        }
    }


}
