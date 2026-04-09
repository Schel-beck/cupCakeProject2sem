package app.Controllers;

import app.controllers.UserController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTest {

    @Test
    void shouldRejectBlankname() {
        String error = UserController.validateUser("", "1234Aaa.", "ben@kunde.com");
        assertEquals("Brugernavn skal udfyldes", error);
    }

    @Test
    void shouldRejectBlankPassword() {
        String error = UserController.validateUser("tim", "" , "ben@kunde.com");
        assertEquals("Password skal udfyldes", error);
    }


    @Test
    void shouldAcceptValidUsernameAndPassword() {
        String error = UserController.validateUser("test", "testtest1@", "ben@kunde.com");
        assertTrue(error.isEmpty());

    }

    @Test
    void shouldRejectTooShortPassword() {
        String error = UserController.validateUser("lars", "12341111", "ben@kunde.com");
        assertEquals("Password skal være mindst 8 tegn", error);
    }

    @Test
    void shouldRejectNoNumberPassword() {
        String error = UserController.validateUser("lars", "bassemand", "ben@kunde.com");
        assertEquals("Password skal have mindst 1 tal", error);
    }
    @Test
    void shouldRejectSpecialPassword() {
        String error = UserController.validateUser("lars", "bassemand1", "ben@kunde.com");
        assertEquals("Password skal have mindst 1 special tegn", error);
    }
}
