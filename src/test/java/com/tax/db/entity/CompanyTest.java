package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompanyTest {

    @Test
    void setId() {
        Company company = new Company();
        company.setId(1);
        assertEquals(1, company.getId());
    }

    @Test
    void setNotValidId() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setId(-5));
        assertEquals("Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setName() {
        Company company = new Company();
        company.setName("someName");
        assertEquals("someName", company.getName());
    }

    @Test
    void setCompanyNameIsLong() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setName("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal name value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setBlankName() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setName(""));
        assertEquals("Illegal company name value, current value is blank", exception.getMessage());
    }

    @Test
    void setInn() {
        Company company = new Company();
        company.setInn("1234567890");
        assertEquals("1234567890", company.getInn());
    }

    @Test
    void setBlankInn() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setInn(""));
        assertEquals("Inn cant be blank", exception.getMessage());
    }

    @Test
    void setLongInn() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setInn("123456789012345"));
        assertEquals("Illegal inn value, current value is long (123456789012345)", exception.getMessage());
    }

    @Test
    void setShortInn() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setInn("12345"));
        assertEquals("Illegal inn value, current value is short (12345)", exception.getMessage());
    }

    @Test
    void setNotDigitInn() {
        Company company = new Company();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> company.setInn("12345s1234"));
        assertEquals("Illegal inn value, must be only digit", exception.getMessage());
    }
}