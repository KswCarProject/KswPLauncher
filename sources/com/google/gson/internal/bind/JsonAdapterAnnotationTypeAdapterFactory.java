package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> targetType) {
        JsonAdapter annotation = (JsonAdapter) targetType.getRawType().getAnnotation(JsonAdapter.class);
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, targetType, annotation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.google.gson.TypeAdapter<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: com.google.gson.TypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.google.gson.internal.bind.TreeTypeAdapter} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.gson.TypeAdapter<?> getTypeAdapter(com.google.gson.internal.ConstructorConstructor r10, com.google.gson.Gson r11, com.google.gson.reflect.TypeToken<?> r12, com.google.gson.annotations.JsonAdapter r13) {
        /*
            r9 = this;
            java.lang.Class r0 = r13.value()
            com.google.gson.reflect.TypeToken r0 = com.google.gson.reflect.TypeToken.get(r0)
            com.google.gson.internal.ObjectConstructor r0 = r10.get(r0)
            java.lang.Object r0 = r0.construct()
            boolean r1 = r0 instanceof com.google.gson.TypeAdapter
            if (r1 == 0) goto L_0x0019
            r1 = r0
            com.google.gson.TypeAdapter r1 = (com.google.gson.TypeAdapter) r1
            goto L_0x0084
        L_0x0019:
            boolean r1 = r0 instanceof com.google.gson.TypeAdapterFactory
            if (r1 == 0) goto L_0x0025
            r1 = r0
            com.google.gson.TypeAdapterFactory r1 = (com.google.gson.TypeAdapterFactory) r1
            com.google.gson.TypeAdapter r1 = r1.create(r11, r12)
            goto L_0x0084
        L_0x0025:
            boolean r1 = r0 instanceof com.google.gson.JsonSerializer
            if (r1 != 0) goto L_0x0063
            boolean r1 = r0 instanceof com.google.gson.JsonDeserializer
            if (r1 == 0) goto L_0x002e
            goto L_0x0063
        L_0x002e:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid attempt to bind an instance of "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Class r3 = r0.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " as a @JsonAdapter for "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = r12.toString()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0063:
            boolean r1 = r0 instanceof com.google.gson.JsonSerializer
            r2 = 0
            if (r1 == 0) goto L_0x006d
            r1 = r0
            com.google.gson.JsonSerializer r1 = (com.google.gson.JsonSerializer) r1
            r4 = r1
            goto L_0x006e
        L_0x006d:
            r4 = r2
        L_0x006e:
            boolean r1 = r0 instanceof com.google.gson.JsonDeserializer
            if (r1 == 0) goto L_0x0078
            r2 = r0
            com.google.gson.JsonDeserializer r2 = (com.google.gson.JsonDeserializer) r2
            r5 = r2
            goto L_0x0079
        L_0x0078:
            r5 = r2
        L_0x0079:
            com.google.gson.internal.bind.TreeTypeAdapter r1 = new com.google.gson.internal.bind.TreeTypeAdapter
            r8 = 0
            r3 = r1
            r6 = r11
            r7 = r12
            r3.<init>(r4, r5, r6, r7, r8)
        L_0x0084:
            if (r1 == 0) goto L_0x0090
            boolean r2 = r13.nullSafe()
            if (r2 == 0) goto L_0x0090
            com.google.gson.TypeAdapter r1 = r1.nullSafe()
        L_0x0090:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.getTypeAdapter(com.google.gson.internal.ConstructorConstructor, com.google.gson.Gson, com.google.gson.reflect.TypeToken, com.google.gson.annotations.JsonAdapter):com.google.gson.TypeAdapter");
    }
}
