package com.tax.db.entity;

import java.io.Serializable;

public class Address implements Serializable {

    private int id;
    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String buildingLetter;
    private String office;
    private String officeLetter;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cant be < 0 and >" + Integer.MAX_VALUE);
        }
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.isBlank()) {
            throw new IllegalArgumentException("Illegal country name value, current value is blank");
        } else if (country.length() > 100) {
            throw new IllegalArgumentException("Illegal country name value, current value is long (" + country + ")");
        } else {
            this.country = country;
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region.isBlank()) {
            throw new IllegalArgumentException("Illegal region name value, current value is blank");
        } else if (region.length() > 100) {
            throw new IllegalArgumentException("Illegal region name value, current value is long (" + region + ")");
        } else {
            this.region = region;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.isBlank()) {
            throw new IllegalArgumentException("Illegal city name value, current value is blank");
        } else if (city.length() > 100) {
            throw new IllegalArgumentException("Illegal city name value, current value is long (" + city + ")");
        } else {
            this.city = city;
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street.isBlank()) {
            throw new IllegalArgumentException("Illegal street name value, current value is blank");
        } else if (street.length() > 100) {
            throw new IllegalArgumentException("Illegal street name value, current value is long (" + street + ")");
        } else {
            this.street = street;
        }
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        if (building.isBlank()) {
            throw new IllegalArgumentException("Illegal building name value, current value is blank");
        } else if (building.length() > 5) {
            throw new IllegalArgumentException("Illegal building value, current value is long (" + building + ")");
        } else if (!building.matches("^\\d+$")) {
            throw new IllegalArgumentException("Illegal building value, must be only digit");
        } else
            this.building = building;
    }

    public String getBuildingLetter() {
        return buildingLetter;
    }

    public void setBuildingLetter(String buildingLetter) {
        if (buildingLetter.length() > 2) {
            throw new IllegalArgumentException("Illegal building letter value, current value is long (" + buildingLetter + ")");
        } else {
            this.buildingLetter = buildingLetter;
        }
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        if (office.isBlank()) {
            throw new IllegalArgumentException("Office cant be blank");
        } else if (office.length() > 5) {
            throw new IllegalArgumentException("Illegal office value, current value is long (" + office + ")");
        } else if (!office.matches("^\\d+$")) {
            throw new IllegalArgumentException("Illegal office value, must be only digit");
        } else
            this.office = office;
    }

    public String getOfficeLetter() {
        return officeLetter;
    }

    public void setOfficeLetter(String officeLetter) {
        if (officeLetter.length() > 2) {
            throw new IllegalArgumentException("Illegal office letter value, current value is long (" + officeLetter + ")");
        } else {
            this.officeLetter = officeLetter;
        }
    }

    @Override
    public String toString() {
        return country + "," +
                region + "," +
                city + "," +
                street + "," +
                building + "," +
                buildingLetter + "," +
                office + "," +
                officeLetter
                ;
    }
}
