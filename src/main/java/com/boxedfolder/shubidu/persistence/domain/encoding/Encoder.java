package com.boxedfolder.shubidu.persistence.domain.encoding;

public interface Encoder<A, T> {
    A encode(T obj);
}
