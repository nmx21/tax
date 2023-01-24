package com.tax.db.entity;

import java.io.Serializable;

public class CompanyDataAddress implements Serializable {
    private int companyDataId;
    private int addressId;

    public CompanyDataAddress(int companyDataId, int addressId) {
        setCompanyDataId(companyDataId);
        setAddressId(addressId);
    }

    public int getCompanyDataId() {
        return companyDataId;
    }

    public void setCompanyDataId(int companyDataId) {
        if (companyDataId < 0) {
            throw new IllegalArgumentException("Company Data Id cant be < 0 and >" + Integer.MAX_VALUE);
        }
        this.companyDataId = companyDataId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        if (addressId < 0) {
            throw new IllegalArgumentException("Address Id cant be < 0 and >" + Integer.MAX_VALUE);
        }
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "CompanyDataAddress{" +
                "company_data_id=" + companyDataId +
                ", address_id=" + addressId +
                '}';
    }
}
