package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Entity
@Table(name="user_interface_list")
public class UserInterfaceList {

    @Id
    @Column(name="UIL_ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "seq_uil")
    @SequenceGenerator(name="seq_uil")
    private Integer interfaceId;

    @UpdateTimestamp
    @Column(name="UIL_LAST_UPDATED")
    private Date lastUpdate;

    @Column(name="UIL_UPDATED_USER")
    private Integer updatedUser;

    @Column(name="UIL_DESCRIPTION")
    private String description;

    @Column(name="UIL_MODULE_NUMBER")
    private Integer moduleNumber;

    @Column(name="UIL_PARENT_NUMBER")
    private Integer parentNumber;

    @Column(name="UIL_PAGE_NAME")
    private String pageName;

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Integer updatedUser) {
        this.updatedUser = updatedUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(Integer moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    public Integer getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(Integer parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
