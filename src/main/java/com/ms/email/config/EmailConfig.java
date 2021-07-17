package com.ms.email.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
