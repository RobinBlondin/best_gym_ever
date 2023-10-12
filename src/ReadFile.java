import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private final boolean isTest;

    public ReadFile(boolean isTest) {
        this.isTest = isTest;
    }

    protected List<Customer> readFileToList(String path) throws Exception {
        List<Customer> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line, line2;
            while((line = br.readLine()) != null && (line2 = br.readLine()) != null) {
                result.add(new Customer(parseName(line), parseSocialNumber(line), parseDate(line2)));
            }
        } catch(IOException | DateTimeParseException e) {
            errorMessage(e, e.getMessage());
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
    
    protected void errorMessage(Exception e, String message) throws Exception {
        if(isTest) {
            throw e;
        } else {
            System.out.println(message);
        }
    }
}
