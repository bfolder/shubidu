package com.boxedfolder.shubidu.config;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Base62Encoder;
import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Encoder;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.URLNotNullValidator;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
    @Bean
    public Encoder encoder() {
        return new Base62Encoder();
    }

    @Bean
    public Validator<URL> notNullValidator() {
        return new URLNotNullValidator();
    }
}
