package com.jpaimplementation.jpaDemo.DTOs;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String city;
    private String state;
    private String country;
}
