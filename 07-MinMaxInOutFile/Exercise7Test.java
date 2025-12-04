import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise7Test {
    @Test
    void testFromSchool21_1() {
        String input = "file1.txt\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise7.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("4"));
        assertTrue(output.contains("100.0 50.0 60.0 10.0"));
        assertTrue(output.contains("Saving min and max values in file"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "file2.txt\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise7.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. Size <= 0"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "file3.txt\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise7.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. Insufficient number of elements"));
    }
    @Test
    void testFromSchool21_4() {
        String input = "file4.txt\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise7.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("5"));
        assertTrue(output.contains("20.0 50.0 60.0 10.0 1.0"));
        assertTrue(output.contains("Saving min and max values in file"));
    }
    @Test
    void testFromSchool21_5() {
        String input = "no_file.txt\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise7.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error. File doesn't exist"));
    }
}
