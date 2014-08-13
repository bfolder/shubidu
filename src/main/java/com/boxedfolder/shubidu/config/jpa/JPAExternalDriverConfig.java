package com.boxedfolder.shubidu.config.jpa;

import com.boxedfolder.shubidu.config.Modes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@PropertySource("classpath:/database.properties")
@Profile({Modes.PRODUCTION, Modes.DEFAULT})
@Configuration
public class JPAExternalDriverConfig extends JPACommonConfig {
    @Autowired
    private Environment env;

    @Override
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
}
