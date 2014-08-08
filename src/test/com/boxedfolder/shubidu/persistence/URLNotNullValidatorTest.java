package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.URLNotNullValidator;
import com.boxedfolder.shubidu.persistence.domain.helper.validation.Validator;
import com.boxedfolder.shubidu.persistence.service.URLService;
import org.junit.Test;

public class URLNotNullValidatorTest {
    @Test(expected = URLService.URLNotFoundException.class)
    public void testValidatorThrowsException() {
        Validator<URL> validator = new URLNotNullValidator();
        validator.validate(null);
    }
}
