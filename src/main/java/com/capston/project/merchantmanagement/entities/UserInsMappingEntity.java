package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="user_ins_mapping")
public class UserInsMappingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "SEQ_USR_INS_MAP")
    @Column(name="UIM_ID")
    private Integer mappingId;

    @UpdateTimestamp
    @Column(name="UIM_LAST_UPDATED")
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name="UIM_UPDATED_USER",referencedColumnName = "user_id")
    private Users updatedUser;

    @ManyToMany
    @JoinColumn(name="UIM_USR_ID",referencedColumnName = "user_id")
    private Users user;

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
