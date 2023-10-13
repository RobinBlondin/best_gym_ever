import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {



    @Test
    public void isSubscriber() {
        Customer lessThanYear = new Customer("", "", LocalDate.of(2023, 6, 25));
        Customer exactlyOneYear = new Customer("", "", LocalDate.now().minusYears(1));
        Customer moreThanYear = new Customer("", "", LocalDate.of(2022, 6, 25));

        assertTrue(lessThanYear.isSubscriber());
        assertTrue(exactlyOneYear.isSubscriber());
        assertFalse(moreThanYear.isSubscriber());
    }

}