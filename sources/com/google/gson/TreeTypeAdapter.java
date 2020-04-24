package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class TreeTypeAdapter<T> extends TypeAdapter<T> {
    private TypeAdapter<T> delegate;
    private final JsonDeserializer<T> deserializer;
    private final Gson gson;
    private final JsonSerializer<T> serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken<T> typeToken;

    private TreeTypeAdapter(JsonSerializer<T> serializer2, JsonDeserializer<T> deserializer2, Gson gson2, TypeToken<T> typeToken2, TypeAdapterFactory skipPast2) {
        this.serializer = serializer2;
        this.deserializer = deserializer2;
        this.gson = gson2;
        this.typeToken = typeToken2;
        this.skipPast = skipPast2;
    }

    public T read(JsonReader in) throws IOException {
        if (this.deserializer == null) {
            return delegate().read(in);
        }
        JsonElement value = Streams.parse(in);
        if (value.isJsonNull()) {
            return null;
        }
        return this.deserializer.deserialize(value, this.typeToken.getType(), this.gson.deserializationContext);
    }

    public void write(JsonWriter out, T value) throws IOException {
        if (this.serializer == null) {
            delegate().write(out, value);
        } else if (value == null) {
            out.nullValue();
        } else {
            Streams.write(this.serializer.serialize(value, this.typeToken.getType(), this.gson.serializationContext), out);
        }
    }

    private TypeAdapter<T> delegate() {
        TypeAdapter<T> d = this.delegate;
        if (d != null) {
            return d;
        }
        TypeAdapter<T> delegateAdapter = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
        this.delegate = delegateAdapter;
        return delegateAdapter;
    }

    public static TypeAdapterFactory newFactory(TypeToken<?> exactType, Object typeAdapter) {
        return new SingleTypeFactory(typeAdapter, exactType, false, (Class) null);
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken<?> exactType, Object typeAdapter) {
        return new SingleTypeFactory(typeAdapter, exactType, exactType.getType() == exactType.getRawType(), (Class) null);
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class<?> hierarchyType, Object typeAdapter) {
        return new SingleTypeFactory(typeAdapter, (TypeToken) null, false, hierarchyType);
    }

    private static class SingleTypeFactory implements TypeAdapterFactory {
        private final JsonDeserializer<?> deserializer;
        private final TypeToken<?> exactType;
        private final Class<?> hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer<?> serializer;

        private SingleTypeFactory(Object typeAdapter, TypeToken<?> exactType2, boolean matchRawType2, Class<?> hierarchyType2) {
            JsonDeserializer<?> jsonDeserializer = null;
            this.serializer = typeAdapter instanceof JsonSerializer ? (JsonSerializer) typeAdapter : null;
            this.deserializer = typeAdapter instanceof JsonDeserializer ? (JsonDeserializer) typeAdapter : jsonDeserializer;
            C$Gson$Preconditions.checkArgument((this.serializer == null && this.deserializer == null) ? false : true);
            this.exactType = exactType2;
            this.matchRawType = matchRawType2;
            this.hierarchyType = hierarchyType2;
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (this.exactType != null ? this.exactType.equals(type) || (this.matchRawType && this.exactType.getType() == type.getRawType()) : this.hierarchyType.isAssignableFrom(type.getRawType())) {
                return new TreeTypeAdapter(this.serializer, this.deserializer, gson, type, this);
            }
            return null;
        }
    }
}
