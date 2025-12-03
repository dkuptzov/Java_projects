import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise2Test {
    @Test
    void testFromSchool21_1() {
        String input = "3599\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("00:59:59"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "3601\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("01:00:01"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "-100\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Incorrect time"));
    }
    @Test
    void testInputProcessing1() {
        String input = "4363454457\n436345445\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("121207:04:05"));
        assertTrue(output.contains("Could not parse a number. Please, try again"));
    }
    @Test
    void testInputProcessing2() {
        String input = "0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("00:00:00"));
    }
    @Test
    void testInputProcessing3() {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("00:00:01"));
    }
    @Test
    void testInputProcessing4() {
        String input = "61\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise2.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("00:01:01"));
    }
}
