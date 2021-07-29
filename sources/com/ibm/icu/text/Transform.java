package com.ibm.icu.text;

public interface Transform<S, D> {
    D transform(S s);
}
