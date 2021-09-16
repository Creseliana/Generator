package com.creseliana.generator.service.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {

    public static <F, T> List<T> mapList(ModelMapper mapper, List<F> list, Class<T> aClass) {
        return list.stream()
                .map(element -> mapper.map(element, aClass))
                .collect(Collectors.toList());
    }
}
