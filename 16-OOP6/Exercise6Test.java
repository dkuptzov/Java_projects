import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise6Test {
    @Test
    void testFromSchool21_1() {
        String input = "3\ndog\nSnowball\n12\ndog\nSnowball2\n10\ndog\nSnowball3\n9\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        System.err.println("\n=== TEST DEBUG INFO ===");
        System.err.println("Full output:");
        System.err.println(output);
        System.err.println("======================\n");
        assertTrue(output.contains("Dog name = Snowball, age = 12"));
        assertTrue(output.contains("Dog name = Snowball2, age = 10"));
        assertTrue(output.contains("Dog name = Snowball3, age = 9"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "3\ndog\nSnowball\n12\ncat\nKitty\n10\ndog\nBalloon\n9\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        System.err.println("\n=== TEST DEBUG INFO ===");
        System.err.println("Full output:");
        System.err.println(output);
        System.err.println("======================\n");
        assertTrue(output.contains("Dog name = Snowball, age = 12"));
        assertTrue(output.contains("Cat name = Kitty, age = 10"));
        assertTrue(output.contains("Dog name = Balloon, age = 9"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "3\nhamster\ncat\nKitty\n-10\ncat\nFura\n9\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        System.err.println("\n=== TEST DEBUG INFO ===");
        System.err.println("Full output:");
        System.err.println(output);
        System.err.println("======================\n");
        assertTrue(output.contains("Incorrect input. Unsupported pet type"));
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertTrue(output.contains("Cat name = Fura, age = 9"));
    }
}
