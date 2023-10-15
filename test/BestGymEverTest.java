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
        String expectedNone2 = "Input individual have never had an subscription";

        assertEquals(expectedActive1, bg.formatOutputMessage(c1));
        assertEquals(expectedActive2, bg.formatOutputMessage(c5));
        assertEquals(expectedExpired, bg.formatOutputMessage(c2));
        assertEquals(expectedNone1, bg.formatOutputMessage(c3));
        assertEquals(expectedNone2, bg.formatOutputMessage(c4));

    }
}
