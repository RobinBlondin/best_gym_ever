import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {

    private final int invalid = 2;
    @Test
    void getStatusById() {
        //Expected enum declarations
        Subscription expectedActive = Subscription.ACTIVE;
        Subscription expectedExpired = Subscription.EXPIRED;
        Subscription expectedNone = Subscription.NONE;

        //enum declarations by method.
        int active = 1;
        Subscription actualActive = Subscription.getById(active);
        int expired = 0;
        Subscription actualExpired = Subscription.getById(expired);
        int none = -1;
        Subscription actualNone = Subscription.getById(none);

        assertEquals(expectedActive, actualActive);
        assertEquals(expectedExpired, actualExpired);
        assertEquals(expectedNone, actualNone);

        assertThrows(IllegalArgumentException.class, () -> Subscription.getById(invalid));
    }

}