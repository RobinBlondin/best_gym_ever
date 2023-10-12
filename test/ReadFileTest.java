import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ReadFileTest {
    ReadFile r = new ReadFile(true);
    String validPath = "files/testFile.txt";
    String invalidPath = "testFile.txt";

    @Test
    public void readFileToList() throws Exception {
        Customer expectedCustomer = new Customer(
                "firstname lastname",
                "12345677890",
                LocalDate.parse("2014-03-26"));

        Customer actualCustomer = r.readFileToList(validPath).get(0);
        List<Customer> customers = r.readFileToList(validPath);
        int expectedListSize = 1;

        assertNotNull(actualCustomer);
        assertEquals(expectedListSize, customers.size());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getSocialSecurityNumber(), actualCustomer.getSocialSecurityNumber());
        assertEquals(expectedCustomer.getJoinDate(), actualCustomer.getJoinDate());

        assertThrows(IOException.class, () -> r.readFileToList(invalidPath));
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
    
    @Test
    public void errorMessage() {
        Exception e = new NoSuchElementException();
        Exception f = new InputMismatchException();
        Exception g = new NullPointerException();
        
        assertThrows(e.getClass(), () -> r.errorMessage(e, ""));
        assertThrows(f.getClass(), () -> r.errorMessage(f, ""));
        assertThrows(g.getClass(), () -> r.errorMessage(g, ""));


    }



}