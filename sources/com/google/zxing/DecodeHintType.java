package com.google.zxing;

import java.util.List;

public enum DecodeHintType {
    OTHER(Object.class),
    PURE_BARCODE(Void.class),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(Void.class),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(r0),
    ASSUME_CODE_39_CHECK_DIGIT(Void.class),
    ASSUME_GS1(Void.class),
    RETURN_CODABAR_START_END(Void.class),
    NEED_RESULT_POINT_CALLBACK(ResultPointCallback.class),
    ALLOWED_EAN_EXTENSIONS(r0);
    
    private final Class<?> valueType;

    private DecodeHintType(Class<?> valueType2) {
        this.valueType = valueType2;
    }

    public Class<?> getValueType() {
        return this.valueType;
    }
}
