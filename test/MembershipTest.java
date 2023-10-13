import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    private final int invalid = 2;
    @Test
    void getStatusById() {
        //Expected enum declarations
        Membership expectedActive = Membership.ACTIVE;
        Membership expectedExpired = Membership.EXPIRED;
        Membership expectedNone = Membership.NONE;

        //enum declarations by method.
        int active = 1;
        Membership actualActive = Membership.getById(active);
        int expired = 0;
        Membership actualExpired = Membership.getById(expired);
        int none = -1;
        Membership actualNone = Membership.getById(none);

        assertEquals(expectedActive, actualActive);
        assertEquals(expectedExpired, actualExpired);
        assertEquals(expectedNone, actualNone);

        assertThrows(IllegalArgumentException.class, () -> Membership.getById(invalid));
    }

}