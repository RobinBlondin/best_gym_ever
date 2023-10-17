import java.util.List;
import java.util.Scanner;

public class BestGymEver {
    private final boolean testMode;
    private final List<Customer> customers;
    private final WriteFile writeFile;
    private final Constants c;

    public BestGymEver(boolean testMode, String inputData, String outputData) throws Exception {
        this.testMode = testMode;
        c = new Constants();
        writeFile = new WriteFile(false, c.PATH_LOG);
        writeFileTest = new WriteFile(true, c.PATH_LOG_TEST);
        customers = new ReadFile(false, c.PATH_CUSTOMERS).readFileToList();

    }

    public static void main(String[] args) throws Exception {
        BestGymEver bg = new BestGymEver(false);
        while (bg.mainProgram(bg)) {
            bg.pause();
        }
    }

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
                System.out.print(c.BAD_FORMAT_MESSAGE);
                pause();
            }
        }
    }

    public String scannerInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print(c.WELCOME_MESSAGE);
        return sc.nextLine();
    }

    public boolean validateInput(String input) {
        return (input.matches(c.NAME_PATTERN) || input.matches(c.SSN_PATTERN));
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

        if (input.matches(c.NAME_PATTERN)) {
            return new Customer(input, "");
        } else {
            return new Customer("", input);
        }

    }

    public String fixInputFormat(String input) {
        input = input.toUpperCase();
        StringBuilder result = new StringBuilder();

        if (input.matches(c.NAME_PATTERN)) {
            for (String name : input.split(" ")) {
                result.append(name.charAt(0)).append(name.substring(1).toLowerCase()).append(" ");
            }
        } else if (input.matches(c.SSN_PATTERN)) {
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

    public void pause() {
        try {
            System.in.read();
        } catch(IOException e) {
            //
        }
    }

}
