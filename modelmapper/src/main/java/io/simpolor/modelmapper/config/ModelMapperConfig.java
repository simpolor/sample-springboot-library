package io.simpolor.modelmapper.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();

        // 매핑 전략 설정
        // MatchingStrategies.STANDARD :
        // MatchingStrategies.STRICT : 정확히 필드명 일치
        // MatchingStrategies.LOOSE :
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        // Null인 필드 스킵 설정
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

        return modelMapper;
    }
}
