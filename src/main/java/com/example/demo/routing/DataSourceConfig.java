package com.example.demo.routing;

import com.example.demo.aspect.WriterDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
//    @ConfigurationProperties("spring.datasource.writer")
    public DataSource remoteDataSource() throws SQLException {

        DataSource ds = DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/test1")
                .username("root")
                .password("")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        return new WriterDataSource(ds);
    }


    @Bean
    @ConfigurationProperties("spring.datasource.reader")
    public DataSource localDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource remoteDataSource, DataSource localDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITER.name(), remoteDataSource);
        targetDataSources.put(DataSourceType.READER.name(), localDataSource);
        return new DynamicDataSource(remoteDataSource, targetDataSources);
    }
}
