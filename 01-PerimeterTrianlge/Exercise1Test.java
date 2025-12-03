import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise1Test {
    @Test
    void testFromSchool21_1() {
        String input = "1,0\n2,0\n2,0\n1,0\n5,0\n5,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise1.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Perimeter: 11,414"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "2,0\n1,0\n2,0\n1,0\n2,0\n1,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise1.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("It's not a triangle"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "2,0\n1,0\n2,0\n1,0\n3,0\n1,0\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise1.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("It's not a triangle"));
    }
    @Test
    void testInputProcessing1() {
        String input = "1,743\n6,436\n7,364\n4,623\n17,356\n20,111\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise1.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Perimeter: 45,093"));
    }
    @Test
    void testInputProcessing2() {
        String input = "1.0\n1,743\n6,436\n7,364\n4,623\n17,356\n20,111\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise1.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("Perimeter: 45,093"));
    }
}
