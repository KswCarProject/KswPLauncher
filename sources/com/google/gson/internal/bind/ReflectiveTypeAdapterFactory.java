package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor2, FieldNamingStrategy fieldNamingPolicy2, Excluder excluder2, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory2) {
        this.constructorConstructor = constructorConstructor2;
        this.fieldNamingPolicy = fieldNamingPolicy2;
        this.excluder = excluder2;
        this.jsonAdapterFactory = jsonAdapterFactory2;
    }

    public boolean excludeField(Field f, boolean serialize) {
        return excludeField(f, serialize, this.excluder);
    }

    static boolean excludeField(Field f, boolean serialize, Excluder excluder2) {
        return !excluder2.excludeClass(f.getType(), serialize) && !excluder2.excludeField(f, serialize);
    }

    private List<String> getFieldNames(Field f) {
        SerializedName annotation = (SerializedName) f.getAnnotation(SerializedName.class);
        if (annotation == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(f));
        }
        String name = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(name);
        }
        List<String> fieldNames = new ArrayList<>(alternates.length + 1);
        fieldNames.add(name);
        for (String alternate : alternates) {
            fieldNames.add(alternate);
        }
        return fieldNames;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null;
        }
        return new Adapter(this.constructorConstructor.get(type), getBoundFields(gson, type, raw));
    }

    private BoundField createBoundField(Gson context, Field field, String name, TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
        Gson gson = context;
        TypeToken<?> typeToken = fieldType;
        boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        JsonAdapter annotation = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> mapped = null;
        if (annotation != null) {
            mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, annotation);
        }
        final boolean jsonAdapterPresent = mapped != null;
        if (mapped == null) {
            mapped = gson.getAdapter(typeToken);
        }
        final TypeAdapter<?> typeAdapter = mapped;
        final Field field2 = field;
        final Gson gson2 = context;
        final TypeToken<?> typeToken2 = fieldType;
        JsonAdapter jsonAdapter = annotation;
        final boolean z = isPrimitive;
        return new BoundField(name, serialize, deserialize) {
            /* access modifiers changed from: package-private */
            public void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException {
                TypeAdapter t;
                Object fieldValue = field2.get(value);
                if (jsonAdapterPresent) {
                    t = typeAdapter;
                } else {
                    t = new TypeAdapterRuntimeTypeWrapper(gson2, typeAdapter, typeToken2.getType());
                }
                t.write(writer, fieldValue);
            }

            /* access modifiers changed from: package-private */
            public void read(JsonReader reader, Object value) throws IOException, IllegalAccessException {
                Object fieldValue = typeAdapter.read(reader);
                if (fieldValue != null || !z) {
                    field2.set(value, fieldValue);
                }
            }

            public boolean writeField(Object value) throws IOException, IllegalAccessException {
                if (this.serialized && field2.get(value) != value) {
                    return true;
                }
                return false;
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        Map<String, BoundField> result = new LinkedHashMap<>();
        if (raw.isInterface()) {
            return result;
        }
        Type declaredType = type.getType();
        TypeToken<?> type2 = type;
        Class<? super Object> cls = raw;
        while (cls != Object.class) {
            Field[] fields = cls.getDeclaredFields();
            int length = fields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = fields[i];
                boolean serialize = excludeField(field, true);
                boolean deserialize = excludeField(field, z);
                if (serialize || deserialize) {
                    this.accessor.makeAccessible(field);
                    Type fieldType = C$Gson$Types.resolve(type2.getType(), cls, field.getGenericType());
                    List<String> fieldNames = getFieldNames(field);
                    int i2 = 0;
                    int size = fieldNames.size();
                    BoundField previous = null;
                    while (i2 < size) {
                        String name = fieldNames.get(i2);
                        if (i2 != 0) {
                            serialize = false;
                        }
                        boolean serialize2 = serialize;
                        String name2 = name;
                        int i3 = i2;
                        BoundField previous2 = previous;
                        int size2 = size;
                        List<String> fieldNames2 = fieldNames;
                        Field field2 = field;
                        previous = previous2 == null ? result.put(name2, createBoundField(context, field, name2, TypeToken.get(fieldType), serialize2, deserialize)) : previous2;
                        i2 = i3 + 1;
                        serialize = serialize2;
                        fieldNames = fieldNames2;
                        size = size2;
                        field = field2;
                    }
                    int i4 = i2;
                    BoundField previous3 = previous;
                    int i5 = size;
                    List<String> list = fieldNames;
                    Field field3 = field;
                    if (previous3 != null) {
                        throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous3.name);
                    }
                }
                i++;
                z = false;
            }
            type2 = TypeToken.get(C$Gson$Types.resolve(type2.getType(), cls, cls.getGenericSuperclass()));
            cls = type2.getRawType();
        }
        return result;
    }

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        /* access modifiers changed from: package-private */
        public abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: package-private */
        public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: package-private */
        public abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String name2, boolean serialized2, boolean deserialized2) {
            this.name = name2;
            this.serialized = serialized2;
            this.deserialized = deserialized2;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        Adapter(ObjectConstructor<T> constructor2, Map<String, BoundField> boundFields2) {
            this.constructor = constructor2;
            this.boundFields = boundFields2;
        }

        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            T instance = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    BoundField field = this.boundFields.get(in.nextName());
                    if (field != null) {
                        if (field.deserialized) {
                            field.read(in, instance);
                        }
                    }
                    in.skipValue();
                }
                in.endObject();
                return instance;
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(value)) {
                        out.name(boundField.name);
                        boundField.write(out, value);
                    }
                }
                out.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }
}
