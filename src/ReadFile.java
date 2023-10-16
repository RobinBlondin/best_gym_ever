import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile extends File implements TestableReadFile {
    public ReadFile(boolean testMode, String path) {
        super(testMode, path);
    }

    public ReadFile(){}
    @Override
    public List<Customer> readFileToList() {
        List<Customer> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(getPath()))) {
            String line, line2;
            while((line = br.readLine()) != null && (line2 = br.readLine()) != null) {
                result.add(new Customer(parseName(line), parseSocialNumber(line), parseDate(line2)));
            }
        } catch(IOException e) {
            errorMessage("File not found: " + e.getMessage());
        } catch(DateTimeParseException e) {
            errorMessage("Date parsing error: " + e.getMessage());
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
