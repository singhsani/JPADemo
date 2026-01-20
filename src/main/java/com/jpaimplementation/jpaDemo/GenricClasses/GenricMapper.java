package com.jpaimplementation.jpaDemo.GenricClasses;

import com.jpaimplementation.jpaDemo.DTOs.AddressDTO;
import com.jpaimplementation.jpaDemo.UsersDetails.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GenricMapper {

        public static <S, T> T map(S source, Class<T> targetClass) {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(source, target);
                return target;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public <S, T> List<T> mapList(List<Address> source, Class<AddressDTO> target) {
            return (List<T>) source.stream()
                    .map(s -> map(s, target))
                    .toList();
        }


}
