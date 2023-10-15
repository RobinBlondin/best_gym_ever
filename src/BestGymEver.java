import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BestGymEver {
    private final boolean testMode;
    private final List<Customer> customers;
    private final ReadFile readFile;
    private final WriteFile writeFile;

    public BestGymEver(boolean testMode) throws Exception {
        String inputData = "./files/customers.txt";
        String outputData = "./files/log.txt";
        this.testMode = testMode;

        readFile = new ReadFile(false, inputData);
        writeFile = new WriteFile(false, outputData);

        customers = readFile.readFileToList();
    }

    public static void main(String[] args) throws Exception {
        BestGymEver bg = new BestGymEver(false);
        String input = bg.getInput("");
        System.out.println(input);
    }

    public String getInput(String mockInput) {
        String namePattern = "\\w* \\w*";
        String ssPattern = "\\d{10,12}";

        if(testMode) {
            if(mockInput.matches(namePattern) || mockInput.matches(ssPattern)) {
                return mockInput;
            } else {
                return "invalid";
            }
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter your full name or social security number: ");
            String input = sc.nextLine();
            if(input.matches(namePattern) || input.matches(ssPattern)) {
                return input.toLowerCase().trim();
            } else {
                return "invalid";
            }
        }
    }

    public String matchName(String input, String entry) {
        String firstName = entry.split(" ")[0];
        String lastName = entry.split(" ")[1];

        if(input.equalsIgnoreCase(firstName) || input.equalsIgnoreCase(lastName) || input.equalsIgnoreCase(entry)) {
            return entry;
        } else {
            return input;
        }
    }

}
