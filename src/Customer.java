import java.time.LocalDate;

public class Customer extends Person {
    private LocalDate joinDate;
    private Subscription subscription;

    public Customer(){}

    public Customer(String name, String socialSecurityNumber, LocalDate joinDate) {
        super(name, socialSecurityNumber);
        this.joinDate = joinDate;
        setSubscription();
    }

    //region Getter and setters
    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription() {
        if (isSubscriber()) {
            this.subscription = Subscription.ACTIVE;
        } else {
            this.subscription = Subscription.EXPIRED;
        }

    }
    //endregion

    protected boolean isSubscriber(){
        return !(joinDate.plusYears(1).isBefore(LocalDate.now()));
    }

}
