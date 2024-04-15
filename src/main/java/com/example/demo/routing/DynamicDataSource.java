package com.example.demo.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(Object defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("Current DataSource is [" + DynamicDataSourceContextHolder.getDataSourceType() + "]");
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}