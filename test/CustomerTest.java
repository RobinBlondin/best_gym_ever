import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    Customer lessThanYear = new Customer("", "", LocalDate.now().minusMonths(6));
    Customer exactlyOneYear = new Customer("", "", LocalDate.now().minusYears(1));
    Customer moreThanYear = new Customer("", "", LocalDate.now().minusMonths(18));


    @Test
    public void isSubscriber() {
        assertTrue(lessThanYear.isSubscriber());
        assertTrue(exactlyOneYear.isSubscriber());
        assertFalse(moreThanYear.isSubscriber());
    }

    @Test
    public void setSubscription() {
        Subscription expectedActive = Subscription.ACTIVE;
        Subscription expectedExpired = Subscription.EXPIRED;

        assertEquals(expectedActive, lessThanYear.getSubscription());
        assertEquals(expectedActive, exactlyOneYear.getSubscription());
        assertEquals(expectedExpired, moreThanYear.getSubscription());
    }
}