package com.jpaimplementation.jpaDemo.GenricClasses;


import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 🔹 Kya hai?
 * Same reflection mapper ko List / Set / Collection ke liye extend karte ho
 * Cleaner service code
 *
 * ✅ Pros
 * ✔ Clean service layer
 * ✔ Reusable everywhere
 * ✔ No boilerplate loops
 *
 * ❌ Cons
 * ❌ Still reflection based
 * ❌ No advanced mapping logic
 * ❌ Same name fields required
 *
 * 🟢 Kab use kare?
 * 👉 Medium-small Spring Boot apps
 * 👉 Simple DTOs
 * 👉 When you want clean code without dependency
 *
 */

@Component
public class GenericMapper {
    private final ModelMapper modelMapper;

    // Constructor injection (Spring will provide the Bean we defined earlier)
    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  <S, T> T convertEntityToDTO(S source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //return modelMapper.map(source, targetClass);
    }

    public  <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(s -> convertEntityToDTO(s, targetClass))
                .collect(Collectors.toList());
    }
}
