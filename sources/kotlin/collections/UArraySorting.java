package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m25d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, m24d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"}, m23k = 2, m22mv = {1, 6, 0}, m20xi = 48)
/* renamed from: kotlin.collections.UArraySortingKt */
/* loaded from: classes.dex */
public final class UArraySorting {
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m541partition4UcCI2c(byte[] array, int left, int right) {
        int i = left;
        int j = right;
        byte pivot = UByteArray.m168getw2LRezQ(array, (left + right) / 2);
        while (i <= j) {
            while (Intrinsics.compare(UByteArray.m168getw2LRezQ(array, i) & UByte.MAX_VALUE, pivot & UByte.MAX_VALUE) < 0) {
                i++;
            }
            while (Intrinsics.compare(UByteArray.m168getw2LRezQ(array, j) & UByte.MAX_VALUE, pivot & UByte.MAX_VALUE) > 0) {
                j--;
            }
            if (i <= j) {
                byte tmp = UByteArray.m168getw2LRezQ(array, i);
                UByteArray.m173setVurrAj0(array, i, UByteArray.m168getw2LRezQ(array, j));
                UByteArray.m173setVurrAj0(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m545quickSort4UcCI2c(byte[] array, int left, int right) {
        int index = m541partition4UcCI2c(array, left, right);
        if (left < index - 1) {
            m545quickSort4UcCI2c(array, left, index - 1);
        }
        if (index < right) {
            m545quickSort4UcCI2c(array, index, right);
        }
    }

    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m542partitionAa5vz7o(short[] array, int left, int right) {
        int i = left;
        int j = right;
        short pivot = UShortArray.m428getMh2AYeg(array, (left + right) / 2);
        while (i <= j) {
            while (Intrinsics.compare(UShortArray.m428getMh2AYeg(array, i) & UShort.MAX_VALUE, pivot & UShort.MAX_VALUE) < 0) {
                i++;
            }
            while (Intrinsics.compare(UShortArray.m428getMh2AYeg(array, j) & UShort.MAX_VALUE, pivot & UShort.MAX_VALUE) > 0) {
                j--;
            }
            if (i <= j) {
                short tmp = UShortArray.m428getMh2AYeg(array, i);
                UShortArray.m433set01HTLdE(array, i, UShortArray.m428getMh2AYeg(array, j));
                UShortArray.m433set01HTLdE(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m546quickSortAa5vz7o(short[] array, int left, int right) {
        int index = m542partitionAa5vz7o(array, left, right);
        if (left < index - 1) {
            m546quickSortAa5vz7o(array, left, index - 1);
        }
        if (index < right) {
            m546quickSortAa5vz7o(array, index, right);
        }
    }

    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m543partitionoBK06Vg(int[] array, int left, int right) {
        int i = left;
        int j = right;
        int pivot = UIntArray.m246getpVg5ArA(array, (left + right) / 2);
        while (i <= j) {
            while (UnsignedUtils.uintCompare(UIntArray.m246getpVg5ArA(array, i), pivot) < 0) {
                i++;
            }
            while (UnsignedUtils.uintCompare(UIntArray.m246getpVg5ArA(array, j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                int tmp = UIntArray.m246getpVg5ArA(array, i);
                UIntArray.m251setVXSXFK8(array, i, UIntArray.m246getpVg5ArA(array, j));
                UIntArray.m251setVXSXFK8(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m547quickSortoBK06Vg(int[] array, int left, int right) {
        int index = m543partitionoBK06Vg(array, left, right);
        if (left < index - 1) {
            m547quickSortoBK06Vg(array, left, index - 1);
        }
        if (index < right) {
            m547quickSortoBK06Vg(array, index, right);
        }
    }

    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m540partitionnroSd4(long[] array, int left, int right) {
        int i = left;
        int j = right;
        long pivot = ULongArray.m324getsVKNKU(array, (left + right) / 2);
        while (i <= j) {
            while (UnsignedUtils.ulongCompare(ULongArray.m324getsVKNKU(array, i), pivot) < 0) {
                i++;
            }
            while (UnsignedUtils.ulongCompare(ULongArray.m324getsVKNKU(array, j), pivot) > 0) {
                j--;
            }
            if (i <= j) {
                long tmp = ULongArray.m324getsVKNKU(array, i);
                ULongArray.m329setk8EXiF4(array, i, ULongArray.m324getsVKNKU(array, j));
                ULongArray.m329setk8EXiF4(array, j, tmp);
                i++;
                j--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m544quickSortnroSd4(long[] array, int left, int right) {
        int index = m540partitionnroSd4(array, left, right);
        if (left < index - 1) {
            m544quickSortnroSd4(array, left, index - 1);
        }
        if (index < right) {
            m544quickSortnroSd4(array, index, right);
        }
    }

    /* renamed from: sortArray-4UcCI2c  reason: not valid java name */
    public static final void m549sortArray4UcCI2c(byte[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m545quickSort4UcCI2c(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray-Aa5vz7o  reason: not valid java name */
    public static final void m550sortArrayAa5vz7o(short[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m546quickSortAa5vz7o(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray-oBK06Vg  reason: not valid java name */
    public static final void m551sortArrayoBK06Vg(int[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m547quickSortoBK06Vg(array, fromIndex, toIndex - 1);
    }

    /* renamed from: sortArray--nroSd4  reason: not valid java name */
    public static final void m548sortArraynroSd4(long[] array, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(array, "array");
        m544quickSortnroSd4(array, fromIndex, toIndex - 1);
    }
}
