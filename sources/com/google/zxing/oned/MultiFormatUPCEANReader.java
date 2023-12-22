package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    public MultiFormatUPCEANReader(Map<DecodeHintType, ?> hints) {
        Collection<BarcodeFormat> possibleFormats = hints == null ? null : (Collection) hints.get(DecodeHintType.POSSIBLE_FORMATS);
        Collection<UPCEANReader> readers = new ArrayList<>();
        if (possibleFormats != null) {
            if (possibleFormats.contains(BarcodeFormat.EAN_13)) {
                readers.add(new EAN13Reader());
            } else if (possibleFormats.contains(BarcodeFormat.UPC_A)) {
                readers.add(new UPCAReader());
            }
            if (possibleFormats.contains(BarcodeFormat.EAN_8)) {
                readers.add(new EAN8Reader());
            }
            if (possibleFormats.contains(BarcodeFormat.UPC_E)) {
                readers.add(new UPCEReader());
            }
        }
        if (readers.isEmpty()) {
            readers.add(new EAN13Reader());
            readers.add(new EAN8Reader());
            readers.add(new UPCEReader());
        }
        this.readers = (UPCEANReader[]) readers.toArray(new UPCEANReader[readers.size()]);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038 A[Catch: ReaderException -> 0x0085, TRY_ENTER, TRY_LEAVE, TryCatch #0 {ReaderException -> 0x0085, blocks: (B:5:0x0018, B:26:0x0058, B:14:0x0038), top: B:39:0x0018 }] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decodeRow(int rowNumber, BitArray row, Map<DecodeHintType, ?> hints) throws NotFoundException {
        int[] startGuardPattern;
        int i;
        Map<DecodeHintType, ?> map = hints;
        int[] startGuardPattern2 = UPCEANReader.findStartGuardPattern(row);
        UPCEANReader[] uPCEANReaderArr = this.readers;
        int length = uPCEANReaderArr.length;
        int i2 = 0;
        int i3 = 0;
        Result resultUPCA = null;
        while (i3 < length) {
            UPCEANReader reader = uPCEANReaderArr[i3];
            try {
                Result result = reader.decodeRow(rowNumber, row, startGuardPattern2, map);
                if (result.getBarcodeFormat() == BarcodeFormat.EAN_13) {
                    try {
                        if (result.getText().charAt(i2) == '0') {
                            i = 1;
                            Collection<BarcodeFormat> collection = map != null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                            Collection<BarcodeFormat> possibleFormats = collection;
                            int i4 = (collection != null || possibleFormats.contains(BarcodeFormat.UPC_A)) ? 1 : i2;
                            if (i != 0 || i4 == 0) {
                                return result;
                            }
                            startGuardPattern = startGuardPattern2;
                            try {
                                Result result2 = new Result(result.getText().substring(1), result.getRawBytes(), result.getResultPoints(), BarcodeFormat.UPC_A);
                                resultUPCA = result2;
                                result2.putAllMetadata(result.getResultMetadata());
                                return resultUPCA;
                            } catch (ReaderException e) {
                                i3++;
                                map = hints;
                                startGuardPattern2 = startGuardPattern;
                                i2 = 0;
                            }
                        }
                    } catch (ReaderException e2) {
                        startGuardPattern = startGuardPattern2;
                        i3++;
                        map = hints;
                        startGuardPattern2 = startGuardPattern;
                        i2 = 0;
                    }
                }
                i = i2;
                if (map != null) {
                }
                Collection<BarcodeFormat> possibleFormats2 = collection;
                if (collection != null) {
                }
                if (i != 0) {
                }
                return result;
            } catch (ReaderException e3) {
                startGuardPattern = startGuardPattern2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        for (UPCEANReader uPCEANReader : this.readers) {
            uPCEANReader.reset();
        }
    }
}
