package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class Countries {

    @Id
    @Column(name = "CNTRY_CODE")
    private String countryCode;

    @UpdateTimestamp
    @Column(name="CNTRY_LAST_UPDATED")
    private Date lastUpdated;

    @JoinColumn(name = "CNTRY_UPDATED_USER",referencedColumnName = "USER_ID")
    @ManyToOne
    private Users updatedUser;

    @Column(name = "CNTRY_NAME")
    private String countryName;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
