import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {
    private final boolean testMode;
    private final List<Customer> customers;
    private final WriteFile writeFile;
    private final WriteFile writeFileTest;
    private final Constants c;

    public BestGymEver(boolean testMode) throws Exception {
        this.testMode = testMode;
        c = new Constants();
        writeFile = new WriteFile(false, c.PATH_LOG);
        writeFileTest = new WriteFile(true, c.PATH_LOG_TEST);
        customers = new ReadFile(false, c.PATH_CUSTOMERS).readFileToList();

    }

    public static void main(String[] args) throws Exception {
        BestGymEver bg = new BestGymEver(false);
        while (bg.mainProgram(bg)) {
            bg.writeFile.pause();
        }
    }

    public boolean mainProgram(BestGymEver bg) throws Exception {
        String input = bg.getInput("");
        if(input.equalsIgnoreCase("exit")) {
            return false;
        }
        Customer c = bg.findCustomer(input);
        String result = bg.formatOutputMessage(c);
        System.out.println(result);
        return true;
    }

    public String getInput(String mockInput) {
        if(testMode) {
            return validateInput(mockInput)? fixInputFormat(mockInput) : "";
        }
        while (true) {
            String input = scannerInput();
            if (input.equalsIgnoreCase("exit")){
                return "exit";
            } else if (validateInput(input)) {
                return fixInputFormat(input);
            } else {
                System.out.print(c.BAD_FORMAT_MESSAGE);
                writeFile.pause();
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
                    if(testMode) {
                        writeFileTest.logEntry(writeFileTest.customerInfoToString(c));
                    } else {
                        writeFile.logEntry(writeFile.customerInfoToString(c));
                    }
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
        if (input.matches(c.NAME_PATTERN)) {
            return formatName(input);
        } else if (input.matches(c.SSN_PATTERN)) {
            return formatSSN(input);
        }
        return input;
    }

    public String formatName(String name) {
        String[] nameArr = name.split(" ");

        for(int i = 0; i < nameArr.length; i++) {
            nameArr[i] = nameArr[i].substring(0,1).toUpperCase() + nameArr[i].substring(1).toLowerCase();
        }
        return String.join(" ", nameArr);
    }

    public String formatSSN(String ssn) {
        return ssn.length() == 12? ssn.substring(2): ssn;
    }


    public String formatOutputMessage(Customer customer) {
        String name = customer.getName().isEmpty() ? "Unknown" : customer.getName();
        System.out.println();
        return String.format("%s have %s subscription", name, customer.getSubscription().getStatus());

    }

}
