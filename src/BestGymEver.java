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
    private final List<Customer> customers;
    private final WriteFile writeFile;

    public BestGymEver() {
        String inputData = "./files/customers.txt";
        String outputData = "./files/log.txt";

        writeFile = new WriteFile(false, outputData);
        customers = new ReadFile(false, inputData).readFileToList();
    }

    public static void main(String[] args) throws Exception {
        BestGymEver bg = new BestGymEver(false);
        String input = bg.getInput("");
        System.out.println(input);
    }

    public String getInput(String mockInput) {
        String namePattern = "\\w* \\w*";
        String ssPattern = "\\d{10,12}";

        if (testMode) {
            if (mockInput.matches(namePattern) || mockInput.matches(ssPattern)) {
                return mockInput;
            } else {
                return "invalid";
            }
        } else {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter your full name or social security number: ");
                String input = sc.nextLine();
                if (input.matches(namePattern) || input.matches(ssPattern)) {
                    return fixInputFormat(input);
                } else {
                    System.out.println("""
                                    Bad format. Please enter either:
                                                                
                                    Full name: "firstname lastname"
                                    Social security number: YYMMDDXXXX
                                                            YYYYMMDDXXXX
                                    """);
                }
            }
        }
    }

    public String matchName(String input, String entry) {
        String firstName = entry.split(" ")[0];
        String lastName = entry.split(" ")[1];

        Customer result = null;
        for (Customer c : customers) {
            if (input.equalsIgnoreCase(c.getName()) || input.contains(c.getSocialSecurityNumber())) {
                if (c.isSubscriber()) {
                    writeFile.logEntry(writeFile.customerInfoToString(c));
                }
                return c;
            }
        }

        if (input.matches(name)) {
            return new Customer(input, "");
        } else {
            return new Customer("", input);
        }

    }

    public String fixInputFormat(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();
        String namePattern = "\\w* \\w*";
        String ssPattern = "\\d{10,12}";

        if (input.matches(namePattern)) {
            for (String name : input.split(" ")) {
                result.append(name.charAt(0)).append(name.substring(1).toLowerCase()).append(" ");
            }
        } else if (input.matches(ssPattern)) {
            if (input.length() > 10) {
                result.append(input.substring(2));
            } else {
                result.append(input);
            }
        }
        return result.toString().trim();
    }

    public String formatOutputMessage(Customer customer) {
        String name = customer.getName().isEmpty()? "Input individual" : customer.getName();
        return String.format("%s have %s subscription", name, customer.getSubscription().getStatus());
    }

}
