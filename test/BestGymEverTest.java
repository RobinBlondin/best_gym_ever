import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEverTest {
    Constants c = new Constants();
    List<Customer> customers = new ReadFile(true, c.PATH_CUSTOMERS).readFileToList();
    BestGymEver bg = new BestGymEver(true);

    public BestGymEverTest() throws Exception {
    }

    @Test
    public void getInput_valid() {
        String input1 = "ROBIN BLONDIN";
        String input2 = "robin blondin";
        String input3 = "RoBiN bLoNdIn";
        String input4 = "8505081234";
        String input5 = "198505081234";

        String expected1 = "Robin Blondin";
        String expected2 = "8505081234";

        assertEquals(expected1, bg.getInput(input1));
        assertEquals(expected1, bg.getInput(input2));
        assertEquals(expected1, bg.getInput(input3));
        assertEquals(expected2, bg.getInput(input4));
        assertEquals(expected2, bg.getInput(input5));
    }

    @Test
    public void getInput_invalid() {
        String input1 = "robin";
        String input2 = "blondin";
        String input3 = "850508";
        String input4 = "1985050812345";

        String expected = "";

        assertEquals(expected, bg.getInput(input1));
        assertEquals(expected, bg.getInput(input2));
        assertEquals(expected, bg.getInput(input3));
        assertEquals(expected, bg.getInput(input4));
    }

    @Test
    public void validateInput_valid_inputs() {
        String validInput1 = "Alhambra Aromes";
        String validInput2 = "7703021234";
        String validInput3 = "197703021234";

        assertTrue(bg.validateInput(validInput1));
        assertTrue(bg.validateInput(validInput2));
        assertTrue(bg.validateInput(validInput3));
    }

    @Test
    public void validateInput_invalid_inputs() {
        String invalidInput1 = "Alhambra";
        String invalidInput2 = "Mitsuko";
        String invalidInput3 = "1977030212345";
        String invalidInput4 = "";

        assertFalse(bg.validateInput(invalidInput1));
        assertFalse(bg.validateInput(invalidInput2));
        assertFalse(bg.validateInput(invalidInput3));
        assertFalse(bg.validateInput(invalidInput4));
    }

    @Test
    public void fixInputFormat_validated_inputs() {
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

    @Test
    public void fixInputFormat_non_valid_inputs() {
        String input1 = "robin";
        String input2 = "BLONDIN";
        String input3 = "850508";
        String input4 = "85050814745";
        String input5 = "198505081474";

        assertEquals(input1, bg.fixInputFormat(input1));
        assertEquals(input2, bg.fixInputFormat(input2));
        assertEquals(input3, bg.fixInputFormat(input3));
        assertEquals(input4, bg.fixInputFormat(input4));
        assertNotEquals(input5, bg.fixInputFormat(input5));
    }

    @Test
    public void formatName() {
        String input1 = "robin blondin";
        String input2 = "ROBIN BLONDIN";
        String input3 =  "rObIn BlOnDiN";

        String expected = "Robin Blondin";

        assertEquals(expected, bg.formatName(input1));
        assertEquals(expected, bg.formatName(input1));
        assertEquals(expected, bg.formatName(input1));
        assertNotEquals(input1, bg.formatName(input1));
        assertNotEquals(input2, bg.formatName(input2));
        assertNotEquals(input3, bg.formatName(input3));
    }

    @Test
    public void formatSSN() {
        String input1 = "8505081234";
        String input2 = "198505081234";
        String input3 = "202310171234";

        String expected1 = "8505081234";
        String expected2 = "2310171234";

        assertEquals(expected1, bg.formatSSN(input1));
        assertEquals(expected1, bg.formatSSN(input2));
        assertEquals(expected2, bg.formatSSN(input3));
        assertNotEquals(input2, bg.formatSSN(input2));
        assertNotEquals(input3, bg.formatSSN(input3));
    }

    @Test
    public void findCustomer_Customers_In_List() throws Exception {
        String validName1 = "Alhambra Aromes";
        String validName2 = "Nahema Ninsson";

        String validSSNumber1 = "7703021234";
        String validSSNumber2 = "7805211234";

        Customer expected1 = customers.get(0);
        Customer expected2 = customers.get(13);

        assertEquals(expected1.getName(), bg.findCustomer(validName1).getName());
        assertEquals(expected1.getSocialSecurityNumber(), bg.findCustomer(validSSNumber1).getSocialSecurityNumber());
        assertEquals(expected1.getJoinDate(), bg.findCustomer(validName1).getJoinDate());
        assertEquals(expected1.getSubscription(), bg.findCustomer(validName1).getSubscription());
        assertEquals(expected2.getName(), bg.findCustomer(validName2).getName());
        assertEquals(expected2.getSocialSecurityNumber(), bg.findCustomer(validSSNumber2).getSocialSecurityNumber());
        assertEquals(expected2.getJoinDate(), bg.findCustomer(validName2).getJoinDate());
        assertEquals(expected2.getSubscription(), bg.findCustomer(validName2).getSubscription());
    }

    @Test
    public void findCustomer_Customers_Not_In_List() throws Exception {
        String name = "robin blondin";
        String ssNumber = "8505081234";

        Customer expected1 = new Customer("robin blondin", "");
        Customer expected2 = new Customer("", "8505081234");

        assertEquals(expected1.getName(), bg.findCustomer(name).getName());
        assertEquals(expected1.getSocialSecurityNumber(), bg.findCustomer(name).getSocialSecurityNumber());
        assertEquals(expected1.getJoinDate(), bg.findCustomer(name).getJoinDate());
        assertEquals(expected1.getSubscription(), bg.findCustomer(name).getSubscription());

        assertEquals(expected2.getName(), bg.findCustomer(ssNumber).getName());
        assertEquals(expected2.getSocialSecurityNumber(), bg.findCustomer(ssNumber).getSocialSecurityNumber());
        assertEquals(expected2.getJoinDate(), bg.findCustomer(ssNumber).getJoinDate());
        assertEquals(expected2.getSubscription(), bg.findCustomer(ssNumber).getSubscription());
    }

    @Test
    public void formatOutputMessage() {
        Customer c1 = customers.get(0);
        Customer c2 = customers.get(1);
        Customer c3 = new Customer("Robin Blondin", "");
        Customer c4 = new Customer("", "8505081234");
        Customer c5 = new Customer("Robin Blondin", "8505081474", LocalDate.now().minusYears(1));

        String expectedActive1 = "Alhambra Aromes have an active subscription";
        String expectedActive2 = "Robin Blondin have an active subscription";
        String expectedExpired = "Bear Belle have an expired subscription";
        String expectedNone1 = "Robin Blondin have never had an subscription";
        String expectedNone2 = "Unknown have never had an subscription";

        assertEquals(expectedActive1, bg.formatOutputMessage(c1));
        assertEquals(expectedActive2, bg.formatOutputMessage(c5));
        assertEquals(expectedExpired, bg.formatOutputMessage(c2));
        assertEquals(expectedNone1, bg.formatOutputMessage(c3));
        assertEquals(expectedNone2, bg.formatOutputMessage(c4));
    }
}
