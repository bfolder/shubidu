package com.boxedfolder.shubidu.persistence.domain.helper.validation;

import com.boxedfolder.shubidu.persistence.domain.URL;
import com.boxedfolder.shubidu.persistence.service.URLService;

public class URLNotNullValidator implements Validator<URL> {
    @Override
    public boolean validate(URL url) {
        if (url == null) {
            throw new URLService.URLNotFoundException();
        }
        return true;
    }
}
