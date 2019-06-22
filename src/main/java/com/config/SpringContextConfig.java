package com.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

@Configuration
@MapperScan("com.mapper")  //等同 <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer"></bean> + <property name="mapperLocations" value="classpath:mapper/*.xml"/>
@PropertySource({"classpath:jdbc.properties","classpath:mybatis-config.properties"})  //引入资源
@EnableTransactionManagement
public class SpringContextConfig {

    /**
     * 配置数据源
     * @param propertiesConfig  自动引入配置类，配置类再使用资源内的数据
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource(PropertiesConfig propertiesConfig) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(propertiesConfig.getDriver());
        dataSource.setJdbcUrl(propertiesConfig.getUrl());//得不到驱动，那时候因为数据配置错误了
        dataSource.setUser(propertiesConfig.getUsername());
        dataSource.setPassword(propertiesConfig.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,MybatisConfig mybatisConfig) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.entities");
        sqlSessionFactoryBean.setConfiguration(mybatisConfig);
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
          return new DataSourceTransactionManager(dataSource);
    }

}
