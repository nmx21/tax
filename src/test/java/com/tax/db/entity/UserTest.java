package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void createValidUser() {
        User user = new User("valid", "valid", "valid@google.com");
        assertEquals("valid", user.getUsername());
        assertEquals("valid", user.getPassword());
        assertEquals("valid@google.com", user.getEmail());
    }

    @Test
    void setId() {
        User user = new User();
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    void setNotValidId() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setId(-5));
        assertEquals("User Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setUsername() {
        User user = new User();
        user.setUsername("someName");
        assertEquals("someName", user.getUsername());
    }

    @Test
    void setUsernameIsEmpty() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setUsername(""));
        assertEquals("Illegal username value, current value is blank", exception.getMessage());
    }

    @Test
    void setUsernameIsShort() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setUsername("qwe"));
        assertEquals("Illegal username value, current value is short (qwe)", exception.getMessage());
    }

    @Test
    void setUsernameIsLong() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setUsername("qwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal username value, current value is long (qwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setPassword() {
        User user = new User();
        user.setPassword("pass");
        assertEquals("pass", user.getPassword());
    }

    @Test
    void setPasswordIsEmpty() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setPassword(""));
        assertEquals("Illegal password value, current value is blank", exception.getMessage());
    }

    @Test
    void setPasswordIsShort() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setPassword("qwe"));
        assertEquals("Illegal password value, current value is short (qwe)", exception.getMessage());
    }

    @Test
    void setPasswordIsLong() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setPassword("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal password value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setEmail() {
        User user = new User();
        user.setEmail("mail@mail.com");
        assertEquals("mail@mail.com", user.getEmail());
    }

    @Test
    void setNotValidEmail() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setEmail("email@email"));
        assertEquals("Illegal email value", exception.getMessage());
    }

    @Test
    void setRoleId() {
        User user = new User();
        user.setRoleId(1);
        assertEquals(1, user.getRoleId());
    }

    @Test
    void setNotValidRoleId() {
        User user = new User();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setRoleId(-5));
        assertEquals("Illegal roleId value. RoleId cant be < 0", exception.getMessage());
    }
}