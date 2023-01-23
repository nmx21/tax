package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportTypeTest {

    @Test
    void setId() {
        ReportType reportType = new ReportType();
        reportType.setId(1);
        assertEquals(1, reportType.getId());
    }

    @Test
    void setNotValidId() {
        ReportType reportType = new ReportType();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> reportType.setId(-5));
        assertEquals("ReportType Id cant be < 0", exception.getMessage());
    }

    @Test
    void setType() {
        ReportType reportType = new ReportType();
        reportType.setType("type");
        assertEquals("type", reportType.getType());
    }

    @Test
    void setBlankType() {
        ReportType reportType = new ReportType();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> reportType.setType(""));
        assertEquals("Type of ReportType cant be blank", exception.getMessage());
    }
}