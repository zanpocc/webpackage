package com.zanpo.it.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Spring 初始化时注入代理的数据源
 *
 * @author cg
 * @date 2020/10/30 02:15
 */
@Configuration
public class DataSourceConfiguration {
    @Bean
    public DataSource dataSource() {
        return new HikariDataSourceProxy();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        String[] locations = {"classpath*:/com/zanpo/it/dao/*/*/*Mapper.xml"};
        Resource resource = null;
        // TODO
        // Stream.of(Optional.ofNullable(locations).orElse(new String[0])).flatMap(e -> Stream.of(getResources(e))).toArray(ServerProperties.Tomcat.Resource[]::new);
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer scannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage("com.ybwx.spring.mybatis.mapper");
        return configurer;
    }
}
