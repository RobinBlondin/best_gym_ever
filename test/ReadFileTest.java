import org.junit.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReadFileTest{
    Constants c = new Constants();
    ReadFile validPath = new ReadFile(true, c.PATH_CUSTOMERS_TEST);
    ReadFile invalidDate = new ReadFile(true, c.INVALID_DATE_PATH);

    ReadFile invalidPath = new ReadFile(true, "");

    @Test
    public void readFileToList_valid_data() throws Exception {
        Customer expectedCustomer = new Customer(
                "firstname lastname",
                "1234567890",
                LocalDate.parse("2014-03-26"));

        Customer actualCustomer = validPath.readFileToList().get(0);
        List<Customer> customers = validPath.readFileToList();
        int expectedListSize = 1;

        assertNotNull(actualCustomer);
        assertEquals(expectedListSize, customers.size());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSocialSecurityNumber(), actualCustomer.getSocialSecurityNumber());
        assertEquals(expectedCustomer.getJoinDate(), actualCustomer.getJoinDate());
    }

    @Test
    public void readFileToList_invalid_date() {
        assertThrows(IOException.class, () -> invalidPath.readFileToList());
        assertThrows(DateTimeParseException.class, () -> invalidDate.readFileToList());
    }

    @Test
    public void parseName() {
        String line1 = "1234567890, firstname lastname";
        String line2 = "1234567890, firstname";
        String expected1 = "firstname lastname";
        String expected2 = "firstname";

        assertEquals(expected1, validPath.parseName(line1));
        assertEquals(expected2, validPath.parseName(line2));
        assertNotEquals(line1, validPath.parseName(line1));
    }

    @Test
    public void parseSocialNumber() {
        String validNumber1 = "1234567890, firstname lastname";
        String validNumber2 = "123456789012, firstname lastname";
        String expected1 = "1234567890";
        String expected2 = "123456789012";

        assertEquals(expected1, validPath.parseSocialNumber(validNumber1));
        assertEquals(expected2, validPath.parseSocialNumber(validNumber2));
        assertNotEquals(validNumber1, validPath.parseSocialNumber(validNumber1));
        assertNotEquals(validNumber2, validPath.parseSocialNumber(validNumber2));
    }

    @Test
    public void parseDate() {
        String line = "2014-03-26";
        String invalidDate = "2345234rt53";
        LocalDate expected = LocalDate.of(2014, 3, 26);

        assertEquals(expected, validPath.parseDate(line));
        assertThrows(DateTimeParseException.class, () -> validPath.parseDate(invalidDate));
    }
}