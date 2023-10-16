import java.util.List;
import java.util.Scanner;

public class BestGymEver {
    String BAD_FORMAT_MESSAGE = """
                        Bad format. Please enter either:
                                   
                        Full name: "firstname lastname"
                        Social security number: YYMMDDXXXX
                                                YYYYMMDDXXXX
                        """;
    protected final String NAME_PATTERN = "\\w+ \\w+";
    protected final String SSN_PATTERN = "\\d{10,12}";
    //endregion

    boolean testMode;
    private final List<Customer> customers;
    private final WriteFile writeFile;

    public BestGymEver(boolean testMode, String inputData, String outputData) throws Exception {
        this.testMode = testMode;
        writeFile = new WriteFile(false, outputData);
        customers = new ReadFile(false, inputData).readFileToList();
    }

    public static void main(String[] args) throws Exception {
        String inputPath = "files/customers.txt";
        String outputPath = "files/log.txt";
        BestGymEver bg = new BestGymEver(false, inputPath, outputPath);

        String input = bg.getInput("");
        Customer c = bg.findCustomer(input);
        String result = bg.formatOutputMessage(c);
        System.out.println(result);
    }

    public String getInput(String mockInput) {
        if(testMode) {
            return validateInput(mockInput)? fixInputFormat(mockInput) : "";
        }
        while (true) {
            String input = scannerInput();
            if (validateInput(input)) {
                return fixInputFormat(input);
            } else {
                System.out.println(BAD_FORMAT_MESSAGE);
            }
        }
    }

    public String scannerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your full name or social security number: ");
        return sc.nextLine();
    }

    public boolean validateInput(String input) {
        return (input.matches(NAME_PATTERN) || input.matches(SSN_PATTERN));
    }

    public Customer findCustomer(String input) throws Exception {
        for (Customer c : customers) {
            if (input.equalsIgnoreCase(c.getName()) || input.contains(c.getSocialSecurityNumber())) {
                if (c.isSubscriber()) {
                    writeFile.logEntry(writeFile.customerInfoToString(c));
                }
                return c;
            }
        }

        if (input.matches(NAME_PATTERN)) {
            return new Customer(input, "");
        } else {
            return new Customer("", input);
        }

    }

    public String fixInputFormat(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();

        if (input.matches(NAME_PATTERN)) {
            for (String name : input.split(" ")) {
                result.append(name.charAt(0)).append(name.substring(1).toLowerCase()).append(" ");
            }
        } else if (input.matches(SSN_PATTERN)) {
            if (input.length() > 10) {
                result.append(input.substring(2));
            } else {
                result.append(input);
            }
        }
        return result.toString().trim();
    }


    public String formatOutputMessage(Customer customer) {
        String name = customer.getName().isEmpty() ? "Input individual" : customer.getName();
        return String.format("%s have %s subscription", name, customer.getSubscription().getStatus());
    }

}
