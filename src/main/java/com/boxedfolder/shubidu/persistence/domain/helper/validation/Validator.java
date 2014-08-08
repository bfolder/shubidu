package com.boxedfolder.shubidu.persistence.domain.helper.validation;

public interface Validator<T> {
    boolean validate(T object);
}
