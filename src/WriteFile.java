import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class WriteFile extends File{
    public WriteFile(boolean testMode, String path) {
        super(testMode, path);
    }

    public void logEntry(String entry) throws Exception {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getPath(), true))) {
            bw.append(entry);
            bw.newLine();
        } catch(IOException e) {
            errorMessage(e, "File not found: " + e.getMessage());
        }
    }
    public String customerInfoToString(Customer customer) {
        return String.format("%s, %s\n%s", customer.getSocialSecurityNumber(), customer.getName(), LocalDate.now());
    }
}