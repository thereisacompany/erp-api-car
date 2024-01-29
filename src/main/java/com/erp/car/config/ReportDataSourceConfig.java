package com.erp.car.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public class ReportDataSourceConfig {

    @Autowired
    MybatisProperties mybatisProperties;

    @Bean(name = "ReportDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource sportDataSource() { return DataSourceBuilder.create().build();}

    @Bean(name = "ReportSessionFactory")
    @Primary
    public SqlSessionFactory reportSessionFactory(@Qualifier("ReportDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        bean.setMapperLocations(mybatisProperties.resolveMapperLocations());
        return bean.getObject();
    }

    @Bean(name = "ReportTransactionManager")
    @Primary
    public DataSourceTransactionManager reportTransactionManger(@Qualifier("ReportDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ReportSessionTemplate")
    @Primary
    public SqlSessionTemplate reportSessionTemplate(@Qualifier("ReportSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
