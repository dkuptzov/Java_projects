import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise6Test {
    @Test
    void testFromSchool21_1() {
        String input = "4\n100.0 50.0 60.0 10.0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("10.0 50.0 60.0 100.0"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "-1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. Size <= 0"));
    }
    @Test
    void test1() {
        String input = "5\n1.111 7.777 6.6 5.5 4.4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("1.111 4.4 5.5 6.6 7.777"));
    }
    @Test
    void test2() {
        String input = "R\n1\n11111\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("11111.0"));
    }
    @Test
    void test3() {
        String input = "9\n0.001 0.009 0.002 0.008 0.003 0.007 0.004 0.006 0.005\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise6.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("0.001 0.002 0.003 0.004 0.005 0.006 0.007 0.008 0.009"));
    }
}
