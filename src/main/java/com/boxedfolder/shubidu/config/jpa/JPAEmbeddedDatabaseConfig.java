package com.boxedfolder.shubidu.config.jpa;

import com.boxedfolder.shubidu.config.Modes;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Profile({Modes.DEVELOPMENT, Modes.TESTING})
@Configuration
public class JPAEmbeddedDatabaseConfig extends JPACommonConfig {
    @Override
    public DataSource dataSource() throws SQLException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }
}
