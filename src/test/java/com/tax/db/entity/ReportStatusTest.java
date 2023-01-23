package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportStatusTest {

    @Test
    void setId() {
        ReportStatus reportStatus = new ReportStatus();
        reportStatus.setId(1);
        assertEquals(1, reportStatus.getId());
    }

    @Test
    void setNotValidId() {
        ReportStatus reportStatus = new ReportStatus();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> reportStatus.setId(-5));
        assertEquals("ReportStatus Id cant be < 0", exception.getMessage());
    }

    @Test
    void setType() {
        ReportStatus reportStatus = new ReportStatus();
        reportStatus.setType("type");
        assertEquals("type", reportStatus.getType());
    }

    @Test
    void setBlankType() {
        ReportStatus reportStatus = new ReportStatus();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> reportStatus.setType(""));
        assertEquals("Type of ReportStatus cant be blank", exception.getMessage());
    }
}