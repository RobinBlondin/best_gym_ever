import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEverTest {
    String path = "./files/customers.txt";
    List<Customer> customers = new ReadFile(true, path).readFileToList();

    BestGymEver bg = new BestGymEver(true);

    public BestGymEverTest() throws Exception {
    }



    @Test
    public void getInput_Valid_Inputs() {
        String validInput1 = "Alhambra Aromes";
        String validInput2 = "7703021234";
        String validInput3 = "197703021234";

        String expected1 = customers.get(0).getName();
        String expected2 = customers.get(0).getSocialSecurityNumber();
        String expected3 = "197703021234";

        assertEquals(expected1, bg.getInput(validInput1));
        assertEquals(expected2, bg.getInput(validInput2));
        assertEquals(expected3, bg.getInput(validInput3));
    }

    @Test
    public void getInput_Invalid_Inputs() {
        String invalidInput1 = "Alhambra";
        String invalidInput2 = "Mitsuko";
        String invalidInput3 = "1977030212345";

        String expected4 = "invalid";

        assertEquals(expected4, bg.getInput(invalidInput1));
        assertEquals(expected4, bg.getInput(invalidInput2));
        assertEquals(expected4, bg.getInput(invalidInput3));
    }

    @Test
    public void fixInputFormat() {
        String input1 = "robin blondin";
        String input2 = "ROBIN BLONDIN";
        String input3 = "8505081474";
        String input4 = "198505081474";

        String expected1 = "Robin Blondin";
        String expected2 = "8505081474";

        assertEquals(expected1, bg.fixInputFormat(input1));
        assertEquals(expected1, bg.fixInputFormat(input2));
        assertEquals(expected2, bg.fixInputFormat(input3));
        assertEquals(expected2, bg.fixInputFormat(input4));

    }

        String expectedOutput = "";
    }
}
