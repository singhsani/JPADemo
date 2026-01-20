package com.jpaimplementation.jpaDemo.Manager;

import com.jpaimplementation.jpaDemo.DTOs.AddressDTO;
import com.jpaimplementation.jpaDemo.DTOs.UserDTO;
import com.jpaimplementation.jpaDemo.GenricClasses.GenricMapper;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import com.jpaimplementation.jpaDemo.UsersDetails.Users;
import com.jpaimplementation.jpaDemo.repository.UserJpaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private GenricMapper genricMapper;

    @Transactional
    public UserDTO getUserDataById(Long id){
        Users users=userJpaRepo.findById(id).orElseThrow();
        UserDTO userDTO=new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUsername(users.getUsername());
        userDTO.setEmailId(users.getEmailId());
        userDTO.setMobileNo(users.getMobileNo());
        userDTO.setAddresses(genricMapper.mapList(users.getAddresses(), AddressDTO.class));
        return userDTO ;
    }

    public void saveData(Users users) {
        userJpaRepo.save(users);
    }
}
