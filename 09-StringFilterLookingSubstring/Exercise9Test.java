import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise9Test {
    @Test
    void testFromSchool21_1() {
        String input = "4\nFirst car\nSecond door\nThird message\nFourth wood\noo\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise9.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Second door, Fourth wood"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "2\nFirst car\nSecond door\nkek  ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise9.main(new String[]{});
        String output = out.toString();
        assertEquals("", output.trim());
        assertFalse(output.contains("First"));
        assertFalse(output.contains("Second"));
        assertFalse(output.contains("car"));
        assertFalse(output.contains("door"));
    }
    @Test
    void test1() {
        String input = "8\na1\na2\na3\na4\na5\na6\na7\naaa\na\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise9.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("a1, a2, a3, a4, a5, a6, a7, aaa"));
    }
    @Test
    void test2() {
        String input = "3\nabra\nkada\nbra\nak\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise9.main(new String[]{});
        String output = out.toString();
        assertEquals("", output.trim());
    }
    @Test
    void test3() {
        String input = "3\nabra\nkada\nbra\nbra\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise9.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("abra, bra"));
    }
}
