package com.jpaimplementation.jpaDemo.repository;

import com.jpaimplementation.jpaDemo.UsersDetails.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepo extends JpaRepository<Users, Long> {

}
