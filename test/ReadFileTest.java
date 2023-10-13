import org.junit.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReadFileTest {
    ReadFile validPath = new ReadFile(true, "files/customersTest.txt");
    ReadFile invalidPath = new ReadFile(true, "customersTest.txt");

    ReadFile r = new ReadFile();

    @Test
    public void readFileToList() throws Exception {
        Customer expectedCustomer = new Customer(
                "firstname lastname",
                "12345677890",
                LocalDate.parse("2014-03-26"));

        Customer actualCustomer = validPath.readFileToList().get(0);
        List<Customer> customers = validPath.readFileToList();
        int expectedListSize = 1;

        assertNotNull(actualCustomer);
        assertEquals(expectedListSize, customers.size());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSocialSecurityNumber(), actualCustomer.getSocialSecurityNumber());
        assertEquals(expectedCustomer.getJoinDate(), actualCustomer.getJoinDate());

        assertThrows(IOException.class, () -> invalidPath.readFileToList());
    }

    @Test
    public void parseName() {
        String line = "12345677890, firstname lastname";
        String expected = "firstname lastname";

        assertEquals(expected, r.parseName(line));
        assertNotEquals(line, r.parseName(line));
    }

    @Test
    public void parseSocialNumber() {
        String line = "12345677890, firstname lastname";
        String expected = "12345677890";

        assertEquals(expected, r.parseSocialNumber(line));
        assertNotEquals(line, r.parseSocialNumber(line));
    }

    @Test
    public void parseDate() {
        String line = "2014-03-26";
        String wrongInput = "2345234rt53";
        LocalDate expected = LocalDate.of(2014, 3, 26);

        assertEquals(expected, r.parseDate(line));
        assertThrows(DateTimeParseException.class, () -> r.parseDate(wrongInput));

    }
}