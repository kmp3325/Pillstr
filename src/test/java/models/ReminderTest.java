package models;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by keegan on 4/27/15.
 */
public class ReminderTest extends TestCase {

    @Test
    public void testGetTaken() {
        Reminder reminder = new Reminder(1, 1, true, 123);
        assertTrue(reminder.getTaken());
    }
}
