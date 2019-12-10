package com.example.multiDatasourceDemo.config;

import com.example.multiDatasourceDemo.anothermapper.anotherMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.inject.Named;
import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.multiDatasourceDemo.mapper")
public class MyBatisConfiguration {
    public static final String PRIMARY_SESSION_FACTORY = "primarySessionFactory";
    public static final String METADATA_SESSION_FACTORY = "metadataSessionFactory";

    @Bean(name = PRIMARY_SESSION_FACTORY)
    @Primary
    public SqlSessionFactoryBean primarySqlSessionFactory(
            @Named(DatabaseConfiguration.PRIMARY_DATASOURCE)final DataSource primaryDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDataSource);
        PathMatchingResourcePatternResolver pathM3R = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathM3R.getResources("classpath:mapper/**/*Mapper.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.domain");
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactoryBean;
    }

    @Bean(name = METADATA_SESSION_FACTORY)
    public SqlSessionFactoryBean metaSqlSessionFactory(
            @Named(DatabaseConfiguration.METADATA_DATASOURCE) final DataSource anotherDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(anotherDataSource);
        PathMatchingResourcePatternResolver pathM3R = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathM3R.getResources("classpath:anothermapper/anotherMapper.xml"));
        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperFactoryBean<anotherMapper> dbMapper(
            @Named(METADATA_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        MapperFactoryBean<anotherMapper> factoryBean = new MapperFactoryBean<>(anotherMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }
}
