package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDataAddressTest {

    @Test
    void setCompanyDataId() {
        CompanyDataAddress companyDataAddress = new CompanyDataAddress(1,2);
        assertEquals(1, companyDataAddress.getCompanyDataId());
        assertEquals(2,companyDataAddress.getAddressId());
    }

    @Test
    void setNotValidCompanyDataAddress() {
        final CompanyDataAddress[] companyDataAddress = new CompanyDataAddress[1];
        Exception exception = assertThrows(IllegalArgumentException.class,
                () ->  companyDataAddress[0] = new CompanyDataAddress(-1,2));
        assertEquals("Company Data Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setNotValidAddressId() {
        final CompanyDataAddress[] companyDataAddress = new CompanyDataAddress[1];
        Exception exception = assertThrows(IllegalArgumentException.class,
                () ->  companyDataAddress[0] = new CompanyDataAddress(1,-2));
        assertEquals("Address Id cant be < 0 and >2147483647", exception.getMessage());
    }
}