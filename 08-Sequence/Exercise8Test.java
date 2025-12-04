import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise8Test {
    @Test
    void testFromSchool21_1() {
        String input = "1 2 3 5 4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is not ordered from the ordinal number of the number 5"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "a\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Input error"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "10 20 50 80 90 g\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is ordered in ascending order"));
    }
    @Test
    void test1() {
        String input = "100 99 88\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is not ordered from the ordinal number of the number 2"));
    }
    @Test
    void test2() {
        String input = "-5 -4 -3 -2 -1 0 -1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is not ordered from the ordinal number of the number 7"));
    }
    @Test
    void test3() {
        String input = "1 2 3 4 5 6 7 8 9 1000 10000 100001 100002 100003 100004 8634785 5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is not ordered from the ordinal number of the number 17"));
    }
    @Test
    void test4() {
        String input = "1 2 3 4 5 6 7 8 9 end\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is ordered in ascending order"));
    }
    @Test
    void test5() {
        String input = "1 2 3 4 5 6 7 8 9 end 1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise8.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("The sequence is not ordered from the ordinal number of the number 11"));
    }
}
