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
}
