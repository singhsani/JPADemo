package com.jpaimplementation.jpaDemo.DTOs;

import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserDTO {
    private Long id;
    private String username;
    private String emailId;
    private String mobileNo;
    private String password;
    private List<AddressDTO> addresses=new ArrayList<>();
}
