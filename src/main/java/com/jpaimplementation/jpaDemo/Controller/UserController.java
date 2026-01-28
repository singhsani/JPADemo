package com.jpaimplementation.jpaDemo.Controller;

import com.jpaimplementation.jpaDemo.DTOs.UserDTO;
import com.jpaimplementation.jpaDemo.GlobalExceptionHandler.UserNotFoundException;
import com.jpaimplementation.jpaDemo.Manager.UserManager;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import com.jpaimplementation.jpaDemo.UsersDetails.Users;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
        if(userDTO ==null){
            throw new UserNotFoundException("Data not found");
        }

        return ResponseEntity.ok(userDTO);
    }

//    @GetMapping("/getMobileNo")
//    public List<String> getMobileNo(){
//        List<Users> users= userManager.getMobile();
//        Predicate<Users> isActive= u->"HR".equals(u.getRole());
//        Consumer<Users> print= u-> System.out.print(u.getEmailId());
//        /**
//         * Scenario: Aapke paas Employee objects ki list hai aur aapko unhe Department ke basis par group karna hai.
//         */
//        Map<String,List<Users>> grouping=users.stream()
//                .filter(user-> "admin".equalsIgnoreCase(user.getRole()))
//                .collect(Collectors.groupingBy(Users::getRole));
//        System.out.println(grouping.get("admin"));
//
//        /**
//         * Multi-level Grouping (Nesting)
//         * Interviewer thoda deep jayega: "Mujhe Department wise employee nahi chahiye, mujhe Department wise unki Average Salary chahiye."
//         *
//         * Yahan hum Downstream Collector use karte hain:
//         */
////        Map<String, Double> avgSalaryByDept = users.stream()
////                .collect(Collectors.groupingBy(
////                        Users::getRole,
////                        Collectors.reducing(0D,e-> Double::sum)
////                ));
//
////
////        String list= Optional.ofNullable(users).filter(user-> us).map(Users:: getUsername).orElse("NA");
////        List<String> email=new ArrayList<>();
////        users.forEach(u->{
////            if(isActive.test(u)){
////                email.add(u.getEmailId());
////            }
////        });
//        return email;
//    }

    @PostMapping("/saveData")
    public ResponseEntity<?> saveData(@RequestBody Users users){
        users.getAddresses()
                .forEach(address -> address.setUser(users));
        userManager.saveData(users);
        return ResponseEntity.ok().build();
    }
}
