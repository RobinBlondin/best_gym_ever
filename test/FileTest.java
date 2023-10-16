import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    void errorMessage() {
        File file = new File(true, "");

        Exception IO = new IOException();
        Exception nullP = new NullPointerException();

        assertThrows(IO.getClass(), () -> file.errorMessage(IO, ""));
        assertThrows(nullP.getClass(), () -> file.errorMessage(nullP, ""));
        //assertThrows()

    }
}