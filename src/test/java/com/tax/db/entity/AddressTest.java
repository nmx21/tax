package com.tax.db.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    void setId() {
        Address address = new Address();
        address.setId(1);
        assertEquals(1, address.getId());
    }

    @Test
    void setNotValidId() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setId(-5));
        assertEquals("Id cant be < 0 and >2147483647", exception.getMessage());
    }

    @Test
    void setCountry() {
        Address address = new Address();
        address.setCountry("Country");
        assertEquals("Country", address.getCountry());
    }

    @Test
    void setBlankCountry() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setCountry(""));
        assertEquals("Illegal country name value, current value is blank", exception.getMessage());
    }

    @Test
    void setCountryNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setCountry("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal country name value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setRegion() {
        Address address = new Address();
        address.setRegion("Region");
        assertEquals("Region", address.getRegion());
    }

    @Test
    void setBlankRegion() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setRegion(""));
        assertEquals("Illegal region name value, current value is blank", exception.getMessage());
    }

    @Test
    void setRegionNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setRegion("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal region name value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setCity() {
        Address address = new Address();
        address.setCity("City");
        assertEquals("City", address.getCity());
    }

    @Test
    void setBlankCity() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setCity(""));
        assertEquals("Illegal city name value, current value is blank", exception.getMessage());
    }

    @Test
    void setCityNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setCity("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal city name value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setStreet() {
        Address address = new Address();
        address.setStreet("Street");
        assertEquals("Street", address.getStreet());
    }

    @Test
    void setBlankStreet() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setStreet(""));
        assertEquals("Illegal street name value, current value is blank", exception.getMessage());
    }

    @Test
    void setStreetNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setStreet("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm"));
        assertEquals("Illegal street name value, current value is long (qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnm)", exception.getMessage());
    }

    @Test
    void setBuilding() {
        Address address = new Address();
        address.setBuilding("3");
        assertEquals("3", address.getBuilding());
    }

    @Test
    void setBlankBuilding() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setBuilding(""));
        assertEquals("Illegal building name value, current value is blank", exception.getMessage());
    }

    @Test
    void setBuildingIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setBuilding("123456"));
        assertEquals("Illegal building value, current value is long (123456)", exception.getMessage());
    }

    @Test
    void setBuildingWithLetter() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setBuilding("123W"));
        assertEquals("Illegal building value, must be only digit", exception.getMessage());
    }

    @Test
    void setBuildingLetter() {
        Address address = new Address();
        address.setBuildingLetter("C");
        assertEquals("C", address.getBuildingLetter());
    }

    @Test
    void setBlankBuildingLetter() {
        Address address = new Address();
        address.setBuildingLetter("");
        assertEquals("", address.getBuildingLetter());
    }

    @Test
    void setBuildingLetterNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setBuildingLetter("qwe"));
        assertEquals("Illegal building letter value, current value is long (qwe)", exception.getMessage());
    }

    @Test
    void setOffice() {
        Address address = new Address();
        address.setOffice("5");
        assertEquals("5", address.getOffice());
    }

    @Test
    void setBlankOffice() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setOffice(""));
        assertEquals("Office cant be blank", exception.getMessage());
    }

    @Test
    void setOfficeIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setOffice("123456"));
        assertEquals("Illegal office value, current value is long (123456)", exception.getMessage());
    }

    @Test
    void setOfficeWithLetter() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setOffice("123W"));
        assertEquals("Illegal office value, must be only digit", exception.getMessage());
    }

    @Test
    void setOfficeLetter() {
        Address address = new Address();
        address.setOfficeLetter("B");
        assertEquals("B", address.getOfficeLetter());
    }

    @Test
    void setBlankOfficeLetter() {
        Address address = new Address();
        address.setOfficeLetter("");
        assertEquals("", address.getOfficeLetter());
    }

    @Test
    void setBuildingOfficeNameIsLong() {
        Address address = new Address();
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> address.setOfficeLetter("qwe"));
        assertEquals("Illegal office letter value, current value is long (qwe)", exception.getMessage());
    }
}