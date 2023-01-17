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
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuildingLetter() {
        return buildingLetter;
    }

    public void setBuildingLetter(String buildingLetter) {
        this.buildingLetter = buildingLetter;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOfficeLetter() {
        return officeLetter;
    }

    public void setOfficeLetter(String officeLetter) {
        this.officeLetter = officeLetter;
    }

    @Override
    public String toString() {
        return  country + "," +
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
