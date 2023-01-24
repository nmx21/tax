package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTypeTest {

    @Test
    void setId() {
        CompanyType companyType = new CompanyType();
        companyType.setId(3);
        assertEquals(3, companyType.getId());
    }

    @Test
    void setNotValidId() {
        CompanyType companyType = new CompanyType();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> companyType.setId(-5));
        assertEquals("Company type Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setType() {
        CompanyType companyType = new CompanyType();
        companyType.setType("someType");
        assertEquals("someType",companyType.getType());
    }

    @Test
    void setBlankType() {
        CompanyType companyType = new CompanyType();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> companyType.setType(""));
        assertEquals("Illegal company type value, current value is blank", exception.getMessage());
    }

    @Test
    void setLongType() {
        CompanyType companyType = new CompanyType();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> companyType.setType("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal company type value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }
}
