import org.junit.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEverTest {
    String path = "./files/customers.txt";
    List<Customer> customers = new ReadFile(true, path).readFileToList();

    BestGymEver bg = new BestGymEver(true);

    public BestGymEverTest() throws Exception {
    }

    @Test
    public void matchName() {
        String input1 = "Alhambra";
        String input2 = "Aromes";
        String input3 = "Mitsuko";
        String input4 = "Mayotte";
        String input5 = "Alhambra Aromes";
        String input6 = "Mitsuko Mayotte";
        String wrongInput = "Robin Blondin";

        String expectedOutput1 = "Alhambra Aromes";
        String expectedOutput2 = "Mitsuko Mayotte";
        String expectedInputNoMatch = "Robin Blondin";

        String testEntry1 = customers.get(0).getName();
        String testEntry2 = customers.get(12).getName();

        assertEquals(expectedOutput1, bg.matchName(input1, testEntry1));
        assertEquals(expectedOutput1, bg.matchName(input2, testEntry1));
        assertEquals(expectedOutput2, bg.matchName(input3, testEntry2));
        assertEquals(expectedOutput2, bg.matchName(input4, testEntry2));
        assertNotEquals(expectedInputNoMatch, bg.matchName(input1, testEntry1));
        assertEquals(expectedInputNoMatch, bg.matchName(wrongInput, testEntry1));
        assertEquals(expectedInputNoMatch, bg.matchName(wrongInput, testEntry2));
    }

    @Test
    public void getInput() {
        String validInput1 = "Alhambra Aromes";
        String validInput2 = "7703021234";
        String validInput3 = "197703021234";
        String invalidInput1 = "Alhambra";
        String invalidInput2 = "Mitsuko";
        String invalidInput3 = "1977030212345";


        String expected1 = customers.get(0).getName();
        String expected2 = customers.get(0).getSocialSecurityNumber();
        String expected3 = "197703021234";
        String expected4 = "invalid";

        assertEquals(expected1, bg.getInput(validInput1));
        assertEquals(expected2, bg.getInput(validInput2));
        assertEquals(expected3, bg.getInput(validInput3));
        assertEquals(expected4, bg.getInput(invalidInput1));
        assertEquals(expected4, bg.getInput(invalidInput2));
        assertEquals(expected4, bg.getInput(invalidInput3));

    }

    @Test
    public void ifSubscriber() {
        Customer isSubscriber = customers.get(0);
        Customer isNotSubscriber = customers.get(1);

        String expectedOutput = "";
    }
}
