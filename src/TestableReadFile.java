import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

public interface TestableReadFile {
    List<Customer> readFileToList() throws IOException, DateTimeParseException;

}
