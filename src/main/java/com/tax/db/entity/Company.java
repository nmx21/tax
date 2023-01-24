package com.tax.db.entity;

import java.io.Serializable;

public class Company implements Serializable {
    private int id;
    private CompanyType type;
    private String name;
    private String inn;
    private Address address;

    public Company() {
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

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Illegal company name value, current value is blank");
        } else if (name.length() > 45) {
            throw new IllegalArgumentException("Illegal name value, current value is long (" + name + ")");
        } else {
            this.name = name;
        }
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        if (inn.isBlank()) {
            throw new IllegalArgumentException("Inn cant be blank");
        } else if (inn.length() > 10) {
            throw new IllegalArgumentException("Illegal inn value, current value is long (" + inn + ")");
        } else if (inn.length() < 8) {
            throw new IllegalArgumentException("Illegal inn value, current value is short (" + inn + ")");
        } else if (!inn.matches("^\\d+$")) {
            throw new IllegalArgumentException("Illegal inn value, must be only digit");
        } else
            this.inn = inn;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", address=" + address +
                '}';
    }
}
