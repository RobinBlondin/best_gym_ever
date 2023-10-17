import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class Customer extends Person {
    private LocalDate joinDate;
    private Subscription subscription;

    //region Constructors
    public Customer(){
        this.subscription = Subscription.NONE;
    }
    public Customer(String name, String socialSecurityNumber) {
        super(name, socialSecurityNumber);
        this.subscription = Subscription.NONE;
    }
    public Customer(String name, String socialSecurityNumber, LocalDate joinDate) {
        super(name, socialSecurityNumber);
        this.joinDate = joinDate;
        setSubscription();
    }
    //endregion

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
        if(joinDate != null) {
            return !(joinDate.plusYears(1).isBefore(LocalDate.now()));
        }
        return false;
    }


}
