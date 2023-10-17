public class Constants {
    public final String WELCOME_MESSAGE = """
           =========================================
                        BEST GYM EVER
           =========================================
           Greetings!
           
           To check in a member you need
           to enter one of the following information:
                        
           - Full name of the visitor
                        
           - The social security number of the visitor
           
           - Type "exit" to quit.
           
           Please enter below!
           
           =========================================
           >\s""";

    public final String BAD_FORMAT_MESSAGE = """
            =========================================
                          INPUT ERROR
            =========================================
            Sorry, we couldn't process your input.
                                                
            Please ensure you use one of the following formats:
                                                
            - Full name: "firstname lastname"
            \s
            Example: "John Doe"
            \s
            - Social security number:\s
            \s
            Format: YYMMDDXXXX or YYYYMMDDXXXX
            Example: 2305021234 or 202305021234
                                                
            =========================================
            Press enter to continue...
            """;
    public final String NAME_PATTERN = "\\w+ \\w+";
    public final String SSN_PATTERN = "\\d{10,12}";
    public final String ALL_BUT_NAME = "[\\d\\W]+[\\W\\d]+";
    public final String ALL_BUT_SSN = "\\D+ \\D+";

    public final String PATH_CUSTOMERS = "files/customers.txt";
    public final String PATH_CUSTOMERS_TEST = "files/customersTest.txt";
    public final String PATH_LOG = "files/log.txt";
    public final String PATH_LOG_TEST = "files/logTest.txt";
    public final String INVALID_NAME_PATH = "files/customers_invalid_name.txt";
    public final String INVALID_SSN_PATH = "files/customers_invalid_ssn.txt";
    public final String INVALID_DATE_PATH = "files/customers_invalid_date.txt";

}
