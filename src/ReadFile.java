import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile extends File {
    public ReadFile(boolean isTest, String path) {
        super(isTest, path);
    }

    public ReadFile(){}

    protected List<Customer> readFileToList() throws Exception {
        List<Customer> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line, line2;
            while((line = br.readLine()) != null && (line2 = br.readLine()) != null) {
                result.add(new Customer(parseName(line), parseSocialNumber(line), parseDate(line2)));
            }
        } catch(IOException e) {
            errorMessage(e, "File not found: " + e.getMessage());
        } catch(DateTimeParseException e) {
            errorMessage(e, "Date parsing error: " + e.getMessage());
        }

        return result;
    }

    protected String parseName(String line) {
        return line.split(", ")[1];
    }

    protected String parseSocialNumber(String line) {
        return line.split(", ")[0];
    }

    protected LocalDate parseDate(String line) {
        return LocalDate.parse(line);
    }
}
