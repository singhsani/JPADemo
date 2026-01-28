package com.jpaimplementation.jpaDemo.UsersDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="address",schema = "test")
@Data
@ToString(exclude = "user")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="state",nullable = false)
    private String state;

    @Column(name="district",nullable = false)
    private String district;

    @Column(name="country",nullable = false)
    private String country;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
