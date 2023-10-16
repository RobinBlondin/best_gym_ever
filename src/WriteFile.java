import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class WriteFile extends File implements TestableWriteFile {
    public WriteFile(boolean isTest, String path) {
        super(isTest, path);
    }

    public void logEntry(String entry) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getPath(), true))) {
            bw.append(entry);
            bw.newLine();
        } catch(IOException e) {
            errorMessage("File not found: " + e.getMessage());
        }
    }
    public String customerInfoToString(Customer customer) {
        return String.format("%s, %s\n%s", customer.getSocialSecurityNumber(), customer.getName(), LocalDate.now());
    }
}