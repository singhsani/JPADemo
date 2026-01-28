package com.jpaimplementation.jpaDemo.ModalMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Runtime object-to-object mapper
 * Automatically maps nested objects
 * Convention + configuration based
 *
 * ✅ Pros
 * ✔ Nested mapping automatic
 * ✔ Very clean
 * ✔ Less code
 * ✔ Widely used
 *
 * 🟢 Kab use kare?
 * 👉 REST APIs
 * 👉 Moderate complexity mapping
 */

@Component
public class ModalMapper {


    @Autowired
    private ModelMapper modelMapper;


    public <S,T> T map(S source ,Class<T> target){
        return modelMapper.map(source,target);
    }

    public <S,T> List<T> mapList(List<S> source,Class<T> target){
        return source.stream().map(a-> modelMapper.map(a,target)).collect(Collectors.toList());
    }

}
