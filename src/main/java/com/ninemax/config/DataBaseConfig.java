package com.ninemax.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

/**
 * Created by Pual on 2016/8/26.
 */

@Component
@EnableConfigurationProperties(DataBaseProperties.class)
@EnableTransactionManagement
@MapperScan(basePackages = "com.ninemax.mapper.sql")
public class DataBaseConfig {

    @Resource
    private ApplicationContext context;
    @Resource
    private DataBaseProperties properties;

    /**
     * 编码过滤器
     *
     * @return
     */
    @Bean
    public CharacterEncodingFilter initCharacterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    /**
     * 数据源dataSource
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    /**
     * 事务管理器transactionManager
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

    /**
     * sqlSessionFactoryBean
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ninemax.entity");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setConfigLocation(context.getResource("classpath:mybatis-config.xml"));
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/ninemax/mapper/*.xml"));
        } catch (Exception ix) {
            ix.printStackTrace();
        }
        return sqlSessionFactoryBean;
    }
}
