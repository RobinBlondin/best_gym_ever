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

    public String getInput(String ignored) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter your full name or social security number: ");
            String input = sc.nextLine();
            if (validateInput(input)) {
                return fixInputFormat(input);
            } else {
                System.out.println(BAD_FORMAT_MESSAGE);
            }
        }
    }

    public boolean validateInput(String input) {
        return (input.matches(NAME_PATTERN) || input.matches(SSN_PATTERN));
    }

    public Customer findCustomer(String input) {
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
        String name = customer.getName().isEmpty() ? "Input individual" : customer.getName();
        return String.format("%s have %s subscription", name, customer.getSubscription().getStatus());
    }

}
