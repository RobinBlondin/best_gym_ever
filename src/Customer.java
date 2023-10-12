import java.time.LocalDate;

public class Customer extends Person {
    private LocalDate joinDate;

    public Customer(){}

    public Customer(String name, String socialSecurityNumber, LocalDate joinDate) {
        super(name, socialSecurityNumber);
        this.joinDate = joinDate;
    }

    //region Getter and setters
    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
    //endregion
}
