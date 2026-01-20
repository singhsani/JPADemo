package com.jpaimplementation.jpaDemo.UsersDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users" ,schema = "test")
@NoArgsConstructor
@Data
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


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses=new ArrayList<>();



}
