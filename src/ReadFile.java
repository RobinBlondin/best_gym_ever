import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile extends File {
    public ReadFile(boolean testMode, String path) {
        super(testMode, path);
    }

    public ReadFile(){}

    public List<Customer> readFileToList() throws Exception {
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

    public String parseName(String line) {
        return line.replaceAll("[\\d\\W]+[\\W\\d]+", "");
    }

    public String parseSocialNumber(String line) {
        return line.replaceAll("\\D+ \\D+", "");
    }

    public LocalDate parseDate(String line) {
        return LocalDate.parse(line);
    }
}
