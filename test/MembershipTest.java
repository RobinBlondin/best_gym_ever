import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    private final int active = 1;
    private final int expired = 0;
    private final int none = -1;

    private final int invalid = 2;
    @Test
    void getStatusById() {
        //Expected enum declarations
        Membership expectedActive = Membership.ACTIVE;
        Membership expectedExpired = Membership.EXPIRED;
        Membership expectedNone = Membership.NONE;

        //enum declarations by method.
        Membership actualActive = Membership.getById(active);
        Membership actualExpired = Membership.getById(expired);
        Membership actualNone = Membership.getById(none);

        assertEquals(expectedActive, actualActive);
        assertEquals(expectedExpired, actualExpired);
        assertEquals(expectedNone, actualNone);

        assertThrows(IllegalArgumentException.class, () -> Membership.getByID(invalid));
    }

}