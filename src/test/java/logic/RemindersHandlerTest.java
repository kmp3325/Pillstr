package logic;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by keegan on 4/27/15.
 */
public class RemindersHandlerTest extends TestCase {

    @Test
    public void testParseTime() {
        long time = Long.parseLong("1430177653000");
        String timeString = RemindersHandler.parseTime(time);
        assertEquals("2015-04-27 19:34:13", timeString);
    }
}
