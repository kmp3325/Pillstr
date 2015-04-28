package models;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by keegan on 4/27/15.
 */
public class PillEventTest extends TestCase {

    @Test
    public void testGetDay() {
        PillEvent pillEvent = new PillEvent(1, 1, 1, 1, 1);
        assertEquals(1, pillEvent.getDay());
    }
}
