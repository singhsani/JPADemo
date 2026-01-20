package com.jpaimplementation.jpaDemo.Controller;

import com.jpaimplementation.jpaDemo.DTOs.UserDTO;
import com.jpaimplementation.jpaDemo.Manager.UserManager;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import com.jpaimplementation.jpaDemo.UsersDetails.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserManager userManager;
    @GetMapping("/getUserDetail/{id}")
    public ResponseEntity<UserDTO> getDataById(@PathVariable("id") Long id){
        //return userJpaRepo.findById(id).orElseThrow();
        UserDTO userDTO=userManager.getUserDataById(id);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/saveData")
    public ResponseEntity<?> saveData(@RequestBody Users users){
        users.getAddresses()
                .forEach(address -> address.setUser(users));
        userManager.saveData(users);
        return ResponseEntity.ok().build();
    }
}
