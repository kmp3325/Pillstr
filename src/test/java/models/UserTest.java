package models;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by keegan on 4/27/15.
 */
public class UserTest extends TestCase {

    @Test
    public void testGetPhone() {
        User user = new User(1, "Keegan Parrotte", "keegan", "keegan.parrotte@yahoo.com", "5555555555", "keegan");
        assertEquals("5555555555", user.getPhone());
    }
}
