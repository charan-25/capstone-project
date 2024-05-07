package com.capston.project.merchantmanagement.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;

@Entity
@Table(name ="user_login_history")
public class UserLoginHistory {

    @Id
    @Column(name = "usr_ser_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer serialNumber;

    @Column(name="usr_login_time")
    private Date loginTime;

    @Column(name="usr_logout_time")
    private Date logoutTime;

    @Column(name="usr_login_ip")
    private String loginIP;

    @ManyToOne
    //Main purpose is to Handle the scenarios where foreign key referenced table doesn't exist
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="usr_user_id",referencedColumnName = "user_id")
    private Users users;

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
