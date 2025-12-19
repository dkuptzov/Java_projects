import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise3Test {
    @Test
    void testFromSchool21_1() {
        String input = "4\ndog\nSnowball\n12\nguinea\nPiggy\n5\ncat\nSnowball\n9\nhamster\nWave\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("GuineaPig name = Piggy, age = 5. I can chill for 12 hours"));
        assertTrue(output.contains("Hamster name = Wave, age = 2. I can chill for 8 hours"));
        assertTrue(output.contains("Dog name = Snowball, age = 12. I can hunt for robbers"));
        assertTrue(output.contains("Cat name = Snowball, age = 9. I can hunt for mice"));
    }
    @Test
    void testFromSchool21_2() {
        String input = "2\ndog\nSnowball\n12\ncat\nKitty\n10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Dog name = Snowball, age = 12. I can hunt for robbers"));
        assertTrue(output.contains("Cat name = Kitty, age = 10. I can hunt for mice"));
    }
    @Test
    void testFromSchool21_3() {
        String input = "3\nturtle\ncat\nKitty\n-10\nguinea\nPiggy\n3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Exercise3.main(new String[]{});
        String output = out.toString();
        assertTrue(output.contains("Incorrect input. Unsupported pet type"));
        assertTrue(output.contains("Incorrect input. Age <= 0"));
        assertTrue(output.contains("GuineaPig name = Piggy, age = 3. I can chill for 12 hours"));
    }
}
