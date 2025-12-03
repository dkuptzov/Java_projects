import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise10Test {
    @Test
    void testFromSchool21_1() {
        String input = "3\nName1\n16\nName2\n19\nName3\n18";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString().trim();
        assertEquals("Name2, Name3", output);
    }
    @Test
    void testFromSchool21_2() {
        String input = "3\nName1\n16\nName2\n14\nName3\n13";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString().trim();
        assertEquals("", output);
    }
    @Test
    void testFromSchool21_3() {
        String input = "3\nName1\n-2\nName2\n23\nName3\n13\nName4\n24";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertTrue(output.contains("Name2"));
        assertTrue(output.contains("Name4"));
        assertFalse(output.contains("Name3"));
        assertFalse(output.contains("Name1"));
    }
    @Test
    void testInputProcessing1() {
        String input = "1\nAlice\n25\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString();
        assertEquals("Alice\n", output);
    }
    @Test
    void testInputProcessing2() {
        String input = "2\nAlice\n25\nBob\n18\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString().trim();
        assertEquals("Alice, Bob", output);
    }
    @Test
    void testInputProcessing3() {
        String input = "r\n1\nCat\n100\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("Cat"));
    }
    @Test
    void testInputProcessing4() {
        String input = "r\n1\nCat\n0\nDog\n17\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertFalse(output.contains("Dog"));
        assertFalse(output.contains("Cat"));
    }
    @Test
    void testInputProcessing5() {
        String input = "r\n1\nCat\n0\nDog\n18\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise10.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Could not parse a number. Please, try again"));
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertTrue(output.contains("Dog"));
        assertFalse(output.contains("Cat"));
    }
}
