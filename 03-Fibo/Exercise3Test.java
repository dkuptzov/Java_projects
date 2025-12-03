import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise3Test {
    @Test
    void testFromSchool21_1() {
        String input = "10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("55"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "100000000\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Too large n"));
    }
    @Test
    void test1() {
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("0"));
    }
    @Test
    void test2() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("1"));
    }
    @Test
    void test3() {
        String input = "ttt\n45\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("1134903170"));
    }
}
