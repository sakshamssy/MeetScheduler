package com.example.Event.Management.Config;

import org.springframework.cglib.core.Local;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source){
        return LocalTime.parse(source);
    }
}
