package com.query.dsl.JdbcService;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder<?> dsb= DataSourceBuilder.create();
        return dsb.driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/database2")
                .username("root")
                .password("password")
                .build();
    }

}
