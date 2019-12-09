package com.example.multiDatasourceDemo.com.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    public static final String PRIMARY_DATASOURCE = "PriDS";
    public static final String METADATA_DATASOURCE = "MetaDS";

    @Bean(name = PRIMARY_DATASOURCE, destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    public DataSource dataSourcePrimary(){
        return new HikariDataSource();
    }

    @Bean(name = METADATA_DATASOURCE, destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource.schema")
    public DataSource dataSourceMetadata(){
        return new HikariDataSource();
    }

}
