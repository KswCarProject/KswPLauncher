package com.google.gson;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

final class DefaultDateTypeAdapter extends TypeAdapter<Date> {
    private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    private final List<DateFormat> dateFormats;
    private final Class<? extends Date> dateType;

    DefaultDateTypeAdapter(Class<? extends Date> dateType2) {
        ArrayList arrayList = new ArrayList();
        this.dateFormats = arrayList;
        this.dateType = verifyDateType(dateType2);
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (JavaVersion.isJava9OrLater()) {
            arrayList.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
        }
    }

    DefaultDateTypeAdapter(Class<? extends Date> dateType2, String datePattern) {
        ArrayList arrayList = new ArrayList();
        this.dateFormats = arrayList;
        this.dateType = verifyDateType(dateType2);
        arrayList.add(new SimpleDateFormat(datePattern, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            arrayList.add(new SimpleDateFormat(datePattern));
        }
    }

    DefaultDateTypeAdapter(Class<? extends Date> dateType2, int style) {
        ArrayList arrayList = new ArrayList();
        this.dateFormats = arrayList;
        this.dateType = verifyDateType(dateType2);
        arrayList.add(DateFormat.getDateInstance(style, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            arrayList.add(DateFormat.getDateInstance(style));
        }
        if (JavaVersion.isJava9OrLater()) {
            arrayList.add(PreJava9DateFormatProvider.getUSDateFormat(style));
        }
    }

    public DefaultDateTypeAdapter(int dateStyle, int timeStyle) {
        this(Date.class, dateStyle, timeStyle);
    }

    public DefaultDateTypeAdapter(Class<? extends Date> dateType2, int dateStyle, int timeStyle) {
        ArrayList arrayList = new ArrayList();
        this.dateFormats = arrayList;
        this.dateType = verifyDateType(dateType2);
        arrayList.add(DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US));
        if (!Locale.getDefault().equals(Locale.US)) {
            arrayList.add(DateFormat.getDateTimeInstance(dateStyle, timeStyle));
        }
        if (JavaVersion.isJava9OrLater()) {
            arrayList.add(PreJava9DateFormatProvider.getUSDateTimeFormat(dateStyle, timeStyle));
        }
    }

    private static Class<? extends Date> verifyDateType(Class<? extends Date> dateType2) {
        if (dateType2 == Date.class || dateType2 == java.sql.Date.class || dateType2 == Timestamp.class) {
            return dateType2;
        }
        throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class + ", or " + java.sql.Date.class + " but was " + dateType2);
    }

    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        synchronized (this.dateFormats) {
            out.value(this.dateFormats.get(0).format(value));
        }
    }

    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        Date date = deserializeToDate(in.nextString());
        Class<? extends Date> cls = this.dateType;
        if (cls == Date.class) {
            return date;
        }
        if (cls == Timestamp.class) {
            return new Timestamp(date.getTime());
        }
        if (cls == java.sql.Date.class) {
            return new java.sql.Date(date.getTime());
        }
        throw new AssertionError();
    }

    private Date deserializeToDate(String s) {
        synchronized (this.dateFormats) {
            for (DateFormat dateFormat : this.dateFormats) {
                try {
                    Date parse = dateFormat.parse(s);
                    return parse;
                } catch (ParseException e) {
                }
            }
            try {
                Date parse2 = ISO8601Utils.parse(s, new ParsePosition(0));
                return parse2;
            } catch (ParseException e2) {
                throw new JsonSyntaxException(s, e2);
            }
        }
    }

    public String toString() {
        DateFormat defaultFormat = this.dateFormats.get(0);
        if (defaultFormat instanceof SimpleDateFormat) {
            return "DefaultDateTypeAdapter(" + ((SimpleDateFormat) defaultFormat).toPattern() + ')';
        }
        return "DefaultDateTypeAdapter(" + defaultFormat.getClass().getSimpleName() + ')';
    }
}
