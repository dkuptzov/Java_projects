import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise5Test {
    @Test
    void testFromSchool21_1() {
        String input = "4\n100 200 300 400\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise5.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("There are no such elements"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "-1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise5.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. Size <= 0"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "5\n1 202 300 200005 301213\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise5.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("1 202 301213"));
    }
    @Test
    void test1() {
        String input = "1\n156645381\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise5.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("156645381"));
    }
    @Test
    void test2() {
        String input = "R\n1\n11111\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise5.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("11111"));
    }
}
