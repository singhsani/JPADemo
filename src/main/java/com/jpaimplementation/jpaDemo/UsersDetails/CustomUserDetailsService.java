package com.jpaimplementation.jpaDemo.UsersDetails;

import com.jpaimplementation.jpaDemo.repository.UserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userJpaRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Not found !"));
        return User.withUsername(users.getUsername()).password(users.getPassword()).roles("ADMIN_ROLE").build();
    }
}
