package com.possani.exploringmars.configuration;

import com.possani.exploringmars.converter.ProbeDtoToProbeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hugo. All rights reserved.
 */
@Configuration
public class ApiConfiguration {

    public Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();

        converters.add(new ProbeDtoToProbeConverter());
        return converters;
    }

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(this.getConverters());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
}
