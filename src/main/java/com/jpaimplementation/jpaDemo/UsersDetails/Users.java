package com.jpaimplementation.jpaDemo.UsersDetails;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users" ,schema = "test")
@NoArgsConstructor
@Data
@ToString(exclude = "addresses")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String username;

    @Column(name="email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "mobileNo", nullable = false, unique = true)
    private String mobileNo;

    @Column(name="login_id",nullable = false,unique = true)
    private String loginId;

    @Column(name="password",nullable = false,unique = true)
    private volatile String password;

    @Column(name="role",nullable = false)
    private String role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses=new ArrayList<>();


}
