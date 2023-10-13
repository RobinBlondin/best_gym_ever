import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    File file = new File(true, "");

    @Test
    void errorMessage() {
        Exception e = new NoSuchElementException();
        Exception f = new InputMismatchException();
        Exception g = new NullPointerException();

        assertThrows(e.getClass(), () -> file.errorMessage(e, ""));
        assertThrows(f.getClass(), () -> file.errorMessage(f, ""));
        assertThrows(g.getClass(), () -> file.errorMessage(g, ""));
    }
}