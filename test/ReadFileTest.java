import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ReadFileTest {
    boolean isTest = true;
    ReadFile r = new ReadFile();
    String validPath = "files/testFile.txt";
    String invalidPath = "testFile.txt";

    @Test
    public void readFileToList() throws IOException {
        Customer expectedCustomer = new Customer(
                "firstname lastname",
                "12345677890",
                LocalDate.parse("2014-03-26"));

        Customer actualCustomer = r.readFileToList(false, validPath).get(0);

        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSocialSecurityNumber(), actualCustomer.getSocialSecurityNumber());
        assertEquals(expectedCustomer.getJoinDate(), actualCustomer.getJoinDate());

        assertThrows(IOException.class, () -> r.readFileToList(isTest, invalidPath));
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