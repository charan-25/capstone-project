package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import javax.xml.crypto.Data;
import java.util.Date;

public class CurrenciesEntity {

    @Id
    @Column(name = "CRNCY_CODE")
    private String currencyCode;

    @Column(name = "CRNCY_LAST_UPDATED")
    private Date lastUpdated;

    @JoinColumn(name = "CRNCY_UPDATED_USER",referencedColumnName = "USER_ID")
    @ManyToOne
    private Users updatedUser;

    @Column(name = "CRNCY_NAME")
    private String currencyName;

    @Column(name = "CRNCY_SHORT_NAME")
    private String currShortName;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Users getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Users updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrShortName() {
        return currShortName;
    }

    public void setCurrShortName(String currShortName) {
        this.currShortName = currShortName;
    }
}
