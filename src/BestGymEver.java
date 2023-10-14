import java.util.List;
import java.util.Scanner;

public class BestGymEver {
    private final boolean testMode;
    private final List<Customer> customers;
    private final Scanner sc;
    private final ReadFile readFile;
    private final WriteFile writeFile;

    public BestGymEver(boolean testMode) throws Exception {
        String inputData = "./files/customers.txt";
        String outputData = "./files/log.txt";
        this.testMode = testMode;

        readFile = new ReadFile(false, inputData);
        writeFile = new WriteFile(false, outputData);
        sc = new Scanner(System.in);

        customers = readFile.readFileToList();
    }

    public static void main(String[] args) {

    }

}
