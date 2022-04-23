package com.ibm.icu.text;

public interface StringTransform extends Transform<String, String> {
    String transform(String str);
}
