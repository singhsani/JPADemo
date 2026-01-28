package com.jpaimplementation.jpaDemo.Manager;

import com.jpaimplementation.jpaDemo.DTOs.AddressDTO;
import com.jpaimplementation.jpaDemo.DTOs.UserDTO;
import com.jpaimplementation.jpaDemo.GenricClasses.GenericMapper;
import com.jpaimplementation.jpaDemo.GlobalExceptionHandler.UserNotFoundException;
import com.jpaimplementation.jpaDemo.MapStruct.UserMapper;
import com.jpaimplementation.jpaDemo.ModalMapper.ModalMapper;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import com.jpaimplementation.jpaDemo.UsersDetails.Users;
import com.jpaimplementation.jpaDemo.repository.UserJpaRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserManager {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Autowired
    private ModalMapper modalMapper;
   // private GenericMapper genricMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public UserDTO getUserDataById(Long id){
        Users users=userJpaRepo.findById(id).orElseThrow(()->  new UserNotFoundException("data not found"));
        UserDTO userDTO = userMapper.toDto(users);
        userDTO.setAddresses(userMapper.toAddressDtoList(users.getAddresses()));
        return userDTO ;
    }

    public void saveData(Users users) {
        String hashPassword=passwordEncoder.encode(users.getPassword());
        users.setPassword(hashPassword);
        userJpaRepo.save(users);
    }

    public List<Users> getMobile() {
        return userJpaRepo.findAll();
    }
}
