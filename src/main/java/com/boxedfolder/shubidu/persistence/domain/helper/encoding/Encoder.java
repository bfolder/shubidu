package com.boxedfolder.shubidu.persistence.domain.helper.encoding;

public interface Encoder<A, T> {
    A encode(T id);
}
