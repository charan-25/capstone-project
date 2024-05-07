package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class Banks {

    @Id
    @Column(name = "BNK_ID")
    private Integer id;

    @UpdateTimestamp
    @Column(name = "BNK_LAST_UPDATED")
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "BNK_UPDATED_USER",referencedColumnName = "user_id")
    private Users updatedUser;

    @Column(name = "BNK_NAME")
    private String bankName;

    @Column(name = "BNK_SHORT_NAME")
    private String bankShortName;

    @Column(name="BNK_ADDRESS")
    private String bankAddress;

    @Column(name = "BNK_CITY")
    private String city;

    @Column(name = "BNK_STATE")
    private String state;

    @Column(name = "BNK_PIN_CODE")
    private String pinCode;

    @JoinColumn(name = "BNK_CURRENCY_CODE",referencedColumnName ="CRNCY_CODE" )
    private CurrenciesEntity currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public CurrenciesEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrenciesEntity currency) {
        this.currency = currency;
    }
}
