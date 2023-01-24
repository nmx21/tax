package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportTest {

    @Test
    void setId() {
        Report report = new Report();
        report.setId(1);
        assertEquals(1, report.getId());
    }

    @Test
    void setNotValidId() {
        Report report = new Report();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> report.setId(-5));
        assertEquals("Report Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setFinancialIncome() {
        Report report = new Report();
        report.setFinancialIncome(100);
        assertEquals(100, report.getFinancialIncome());
    }

    @Test
    void setNotValidFinancialIncome() {
        Report report = new Report();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> report.setFinancialIncome(-5));
        assertEquals("Financial Income cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setTaxAmount() {
        Report report = new Report();
        report.setTaxAmount(100);
        assertEquals(100, report.getTaxAmount());
    }

    @Test
    void setNotValidTaxAmount() {
        Report report = new Report();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> report.setTaxAmount(-5));
        assertEquals("Tax Amount cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setDescription() {
        Report report = new Report();
        report.setDescription("Some description");
        assertEquals("Some description", report.getDescription());
    }

    @Test
    void setLongDescription() {
        Report report = new Report();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> report.setDescription("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal description value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setComment() {
        Report report = new Report();
        report.setComment("Some comment");
        assertEquals("Some comment", report.getComment());
    }

    @Test
    void setLongComment() {
        Report report = new Report();
        StringBuffer buffer = new StringBuffer(10001);
        for (int i = 0; i < 10001; i++) {
            buffer.append("a");
        }
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> report.setComment(String.valueOf(buffer)));
        assertEquals("Illegal comment value, current value is long (more than 10001 symbol)", exception.getMessage());
    }
}