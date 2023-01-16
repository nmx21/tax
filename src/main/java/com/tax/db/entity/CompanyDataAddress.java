package com.tax.db.entity;

import java.io.Serializable;

public class CompanyDataAddress implements Serializable {
    int companyDataId;
    int addressId;

    public CompanyDataAddress(int companyDataId, int addressId) {
        this.companyDataId = companyDataId;
        this.addressId = addressId;
    }

    public int getCompanyDataId() {
        return companyDataId;
    }

    public void setCompanyDataId(int companyDataId) {
        this.companyDataId = companyDataId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
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
