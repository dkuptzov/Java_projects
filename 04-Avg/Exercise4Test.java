import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise4Test {
    @Test
    void testFromSchool21_1() {
        String input = "4\n1 2 3 4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("There are no negative elements"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "-1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. Size <= 0"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "4\n1 -2 3 -4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("-3"));
    }
    @Test
    void test1() {
        String input = "2\n1.0 1\n5 -6\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("-6"));
    }
    @Test
    void test2() {
        String input = "10\n-1 -1 -1 -1 -1 -1 -1 -1 -1 -1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("-1"));
    }
    @Test
    void test3() {
        String input = "4\n-100 -100 -20 -20\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise4.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("-60"));
    }
}
