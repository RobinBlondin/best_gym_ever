import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class WriteFile extends File {
    public WriteFile(boolean isTest, String path) {
        super(isTest, path);
    }

    protected void logEntry(String entry) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getPath(), true))) {
            bw.append(entry);
            bw.newLine();
        } catch(IOException e) {
            errorMessage(e, "File not found: " + e.getMessage());
        }
    }
    protected String customerInfoToString(Customer customer) {
        return String.format("%s, %s\n%s", customer.getSocialSecurityNumber(), customer.getName(), LocalDate.now());
    }
}