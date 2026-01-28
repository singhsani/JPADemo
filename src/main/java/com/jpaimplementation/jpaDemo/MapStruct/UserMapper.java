package com.jpaimplementation.jpaDemo.MapStruct;

import com.jpaimplementation.jpaDemo.DTOs.AddressDTO;
import com.jpaimplementation.jpaDemo.DTOs.UserDTO;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import com.jpaimplementation.jpaDemo.UsersDetails.Users;
import org.mapstruct.Mapper;

/**
 * Compile time par mapper generate hota hai
 * No reflection
 * Fastest & safest
 *
 * ✅ Pros
 * ✔ Fastest
 * ✔ Compile-time safety
 * ✔ No LazyInitialization issue
 * ✔ Enterprise standard
 *
 *
 * 🟢 Kab use kare?
 * 👉 Large applications
 * 👉 High performance needed
 * 👉 Enterprise / product-level systems
 */
import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(Users users);
    AddressDTO toDto(Address address);
    List<AddressDTO> toAddressDtoList(List<Address> addresses);
}
