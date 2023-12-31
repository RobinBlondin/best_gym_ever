import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriteFileTest {
    Constants c = new Constants();
    Customer customer = new Customer("Alhambra Aromes", "7703021234", LocalDate.of(2023, 6, 25));
    WriteFile validPath = new WriteFile(true, c.PATH_LOG_TEST);
    WriteFile invalidPath = new WriteFile(true, "");

    ReadFile r = new ReadFile(true, c.PATH_LOG_TEST);

    @Test
    public void customerInfoToString() {
        String expected = String.format("7703021234, Alhambra Aromes\n%s", LocalDate.now());
        String actual = validPath.customerInfoToString(customer);

        assertEquals(expected, actual);
    }

    @Test
    public void logEntry() throws Exception {
        List<Customer> beforeList = r.readFileToList();
        String entry = validPath.customerInfoToString(customer);
        validPath.logEntry(entry);
        List<Customer> afterList = r.readFileToList();

        assert(beforeList.size() < afterList.size());
        assertThrows(IOException.class, () -> invalidPath.logEntry(entry));
    }
}