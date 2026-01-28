package com.jpaimplementation.jpaDemo.JwtConfiguration;

import com.jpaimplementation.jpaDemo.Manager.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    UserManager userManager;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

//        request.getAddresses()
//                .forEach(address -> address.setUser(request));
//        userManager.saveData(request);
        this.doAuthenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        String token = jwtHelper.genrateToken(userDetails.getUsername());

        JwtResponse jwtResponse = JwtResponse.builder()
                .jwtToken(token)
                .build();

        return ResponseEntity.ok(jwtResponse);

    }

       @GetMapping("/public")
        public String get(){
          return "Hello";
        }

    private void doAuthenticate(String email ,String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(email,password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException badCe){
            throw new BadCredentialsException(" Invailid user name and password"+badCe);

        }

    }
}
