import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise2Test {
    @Test
    void testFromSchool21_1() {
        String input = "3\ndog\nSnowball\n12\n5,0\ndog\nSnowball2\n10\n10,0\ndog\nSnowball3\n9\n9,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Dog name = Snowball, age = 12, mass = 5,00, feed = 1,50"));
        assertTrue(output.contains("Dog name = Snowball2, age = 10, mass = 10,00, feed = 3,00"));
        assertTrue(output.contains("Dog name = Snowball3, age = 9, mass = 9,00, feed = 2,70"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "3\ndog\nSnowball\n12\n5,0\ncat\nKitty\n10\n10,0\ndog\nBalloon\n9\n9,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Dog name = Snowball, age = 12, mass = 5,00, feed = 1,50"));
        assertTrue(output.contains("Cat name = Kitty, age = 10, mass = 10,00, feed = 1,00"));
        assertTrue(output.contains("Dog name = Balloon, age = 9, mass = 9,00, feed = 2,70"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "4\nhamster\ncat\nKitty\n-10\ndog\nBalloon\n9\n-9\ncat\nFura\n9\n12,5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Incorrect input. Unsupported pet type"));
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertTrue(output.contains("Incorrect input. Mass <= 0"));
        assertTrue(output.contains("Cat name = Fura, age = 9, mass = 12,50, feed = 1,25"));
    }
}
