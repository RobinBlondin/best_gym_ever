import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    protected List<Customer> readFileToList(boolean isTest, String path) throws IOException {
        List<Customer> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = br.readLine()) != null) {
                result.add(new Customer(parseName(line), parseSocialNumber(line), parseDate(br.readLine())));
            }
        } catch(IOException e) {
            if(isTest) {
                throw e;
            } else {
                System.out.println("File not found!");
            }
        } catch(DateTimeParseException e) {
            if(isTest) {
                throw e;
            } else {
                System.out.println("parseDate couldn't parse String to LocalDate");
            }
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
