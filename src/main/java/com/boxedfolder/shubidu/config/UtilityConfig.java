package com.boxedfolder.shubidu.config;

import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
    @Bean
    public Encoder encoder() {
        return new Base62Encoder();
    }
}
