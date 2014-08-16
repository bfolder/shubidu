package com.boxedfolder.shubidu.config.jpa;

import com.boxedfolder.shubidu.config.Modes;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@PropertySource("classpath:/database.properties")
@Profile({Modes.PRODUCTION, Modes.DEFAULT})
@Configuration
public class JPAExternalDriverConfig extends JPACommonConfig {
    @Override
    public DataSource dataSource() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(20);
        return dataSource;
    }
}
