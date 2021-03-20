package com.soundlab.atividades.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class WebServiceConfig {

    @Bean(name = "genericMapper")
    public ModelMapper getGenericMapper() {
        return new ModelMapper();
    }

    @Bean(name = "nonNullFieldMapper")
    public ModelMapper getNonNullFieldMapper() {
        ModelMapper nonNullFieldMapper = new ModelMapper();
        nonNullFieldMapper.getConfiguration().setSkipNullEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT);
        return nonNullFieldMapper;
    }
}
