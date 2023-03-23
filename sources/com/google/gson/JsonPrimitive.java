package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private final Object value;

    public JsonPrimitive(Boolean bool) {
        this.value = C$Gson$Preconditions.checkNotNull(bool);
    }

    public JsonPrimitive(Number number) {
        this.value = C$Gson$Preconditions.checkNotNull(number);
    }

    public JsonPrimitive(String string) {
        this.value = C$Gson$Preconditions.checkNotNull(string);
    }

    public JsonPrimitive(Character c) {
        this.value = ((Character) C$Gson$Preconditions.checkNotNull(c)).toString();
    }

    public JsonPrimitive deepCopy() {
        return this;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public boolean getAsBoolean() {
        if (isBoolean()) {
            return ((Boolean) this.value).booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public Number getAsNumber() {
        Object obj = this.value;
        return obj instanceof String ? new LazilyParsedNumber((String) this.value) : (Number) obj;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return ((Boolean) this.value).toString();
        }
        return (String) this.value;
    }

    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    public BigDecimal getAsBigDecimal() {
        Object obj = this.value;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(this.value.toString());
    }

    public BigInteger getAsBigInteger() {
        Object obj = this.value;
        return obj instanceof BigInteger ? (BigInteger) obj : new BigInteger(this.value.toString());
    }

    public float getAsFloat() {
        return isNumber() ? getAsNumber().floatValue() : Float.parseFloat(getAsString());
    }

    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    public short getAsShort() {
        return isNumber() ? getAsNumber().shortValue() : Short.parseShort(getAsString());
    }

    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    public byte getAsByte() {
        return isNumber() ? getAsNumber().byteValue() : Byte.parseByte(getAsString());
    }

    public char getAsCharacter() {
        return getAsString().charAt(0);
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            long value2 = getAsNumber().longValue();
            return (int) ((value2 >>> 32) ^ value2);
        }
        Object obj = this.value;
        if (!(obj instanceof Number)) {
            return obj.hashCode();
        }
        long value3 = Double.doubleToLongBits(getAsNumber().doubleValue());
        return (int) ((value3 >>> 32) ^ value3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive other = (JsonPrimitive) obj;
        if (this.value == null) {
            if (other.value == null) {
                return true;
            }
            return false;
        } else if (!isIntegral(this) || !isIntegral(other)) {
            Object obj2 = this.value;
            if (!(obj2 instanceof Number) || !(other.value instanceof Number)) {
                return obj2.equals(other.value);
            }
            double a = getAsNumber().doubleValue();
            double b = other.getAsNumber().doubleValue();
            if (a == b) {
                return true;
            }
            if (!Double.isNaN(a) || !Double.isNaN(b)) {
                return false;
            }
            return true;
        } else if (getAsNumber().longValue() == other.getAsNumber().longValue()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isIntegral(JsonPrimitive primitive) {
        Object obj = primitive.value;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
