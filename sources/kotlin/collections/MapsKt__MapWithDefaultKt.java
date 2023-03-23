package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001aQ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001aX\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007¢\u0006\u0002\b\r¨\u0006\u000e"}, d2 = {"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, k = 5, mv = {1, 6, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
/* compiled from: MapWithDefault.kt */
class MapsKt__MapWithDefaultKt {
    public static final <K, V> V getOrImplicitDefaultNullable(Map<K, ? extends V> $this$getOrImplicitDefault, K key) {
        Intrinsics.checkNotNullParameter($this$getOrImplicitDefault, "<this>");
        if ($this$getOrImplicitDefault instanceof MapWithDefault) {
            return ((MapWithDefault) $this$getOrImplicitDefault).getOrImplicitDefault(key);
        }
        Map $this$getOrElseNullable$iv = $this$getOrImplicitDefault;
        Object value$iv = $this$getOrElseNullable$iv.get(key);
        if (value$iv != null || $this$getOrElseNullable$iv.containsKey(key)) {
            return value$iv;
        }
        throw new NoSuchElementException("Key " + key + " is missing in the map.");
    }

    public static final <K, V> Map<K, V> withDefault(Map<K, ? extends V> $this$withDefault, Function1<? super K, ? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter($this$withDefault, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if ($this$withDefault instanceof MapWithDefault) {
            return MapsKt.withDefault(((MapWithDefault) $this$withDefault).getMap(), defaultValue);
        }
        return new MapWithDefaultImpl<>($this$withDefault, defaultValue);
    }

    public static final <K, V> Map<K, V> withDefaultMutable(Map<K, V> $this$withDefault, Function1<? super K, ? extends V> defaultValue) {
        Intrinsics.checkNotNullParameter($this$withDefault, "<this>");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        if ($this$withDefault instanceof MutableMapWithDefault) {
            return MapsKt.withDefaultMutable(((MutableMapWithDefault) $this$withDefault).getMap(), defaultValue);
        }
        return new MutableMapWithDefaultImpl<>($this$withDefault, defaultValue);
    }
}
