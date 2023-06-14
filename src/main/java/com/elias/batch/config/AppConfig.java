package com.elias.batch.config;

import jakarta.annotation.PostConstruct;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondaryDatasource")
    public DataSource secondaryDatasource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .addScript("classpath:org/springframework/batch/core/schema-drop-hsqldb.sql")
                .addScript("classpath:org/springframework/batch/core/schema-hsqldb.sql")
                .setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
                .build();
    }

    // NOTE: In addition, need to add the following VM arguments -Djava.awt.headless=false when the application is started.
    // in this app, added in a static block in the spring boot main application
    @PostConstruct
    public void startDBManager() {
        //hsqldb
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
    }
}
