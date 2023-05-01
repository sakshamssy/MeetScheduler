package com.example.Event.Management.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.MongoDbFactoryParser;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoSimpleTypes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class MongoConfig {

    @Bean
    @Primary
    public MappingMongoConverter mongoConverter(
            @Autowired MongoMappingContext mongoMappingContext,
            @Autowired MongoDatabaseFactory mainMongoFactory,
            @Autowired MongoCustomConversions conversions
    ) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mainMongoFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        mongoConverter.setMapKeyDotReplacement("#");
        mongoConverter.afterPropertiesSet();
        mongoConverter.setCustomConversions(conversions);
        return mongoConverter;
    }

    @Bean
    public MongoMappingContext mongoMappingContext() {
        MongoMappingContext context = new MongoMappingContext();
        context.setSimpleTypeHolder(new SimpleTypeHolder(new HashSet<>(Arrays.asList(
                LocalDateTime.class,
                LocalDateTime.class
        )), MongoSimpleTypes.HOLDER));
        return context;
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        converterList.add(new MongoLocalDateTimeFromStringConverter());
        converterList.add(new MongoDateTimeFromStringConverter());
        return new MongoCustomConversions(converterList);
    }

    private static final class MongoLocalDateTimeFromStringConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            return source == null ? null : LocalTime.parse(source);
        }
    }

    private static final class MongoDateTimeFromStringConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            return source == null ? null :LocalTime.parse(source);
        }
    }
}
