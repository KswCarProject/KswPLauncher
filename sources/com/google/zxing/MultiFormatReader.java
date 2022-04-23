package com.google.zxing;

import com.google.zxing.aztec.AztecReader;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatReader implements Reader {
    private Map<DecodeHintType, ?> hints;
    private Reader[] readers;

    public Result decode(BinaryBitmap image) throws NotFoundException {
        setHints((Map<DecodeHintType, ?>) null);
        return decodeInternal(image);
    }

    public Result decode(BinaryBitmap image, Map<DecodeHintType, ?> hints2) throws NotFoundException {
        setHints(hints2);
        return decodeInternal(image);
    }

    public Result decodeWithState(BinaryBitmap image) throws NotFoundException {
        if (this.readers == null) {
            setHints((Map<DecodeHintType, ?>) null);
        }
        return decodeInternal(image);
    }

    public void setHints(Map<DecodeHintType, ?> hints2) {
        Collection<BarcodeFormat> formats;
        this.hints = hints2;
        boolean z = true;
        boolean tryHarder = hints2 != null && hints2.containsKey(DecodeHintType.TRY_HARDER);
        if (hints2 == null) {
            formats = null;
        } else {
            formats = (Collection) hints2.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        Collection<Reader> readers2 = new ArrayList<>();
        if (formats != null) {
            if (!formats.contains(BarcodeFormat.UPC_A) && !formats.contains(BarcodeFormat.UPC_E) && !formats.contains(BarcodeFormat.EAN_13) && !formats.contains(BarcodeFormat.EAN_8) && !formats.contains(BarcodeFormat.CODABAR) && !formats.contains(BarcodeFormat.CODE_39) && !formats.contains(BarcodeFormat.CODE_93) && !formats.contains(BarcodeFormat.CODE_128) && !formats.contains(BarcodeFormat.ITF) && !formats.contains(BarcodeFormat.RSS_14) && !formats.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = false;
            }
            boolean addOneDReader = z;
            if (z && !tryHarder) {
                readers2.add(new MultiFormatOneDReader(hints2));
            }
            if (formats.contains(BarcodeFormat.QR_CODE)) {
                readers2.add(new QRCodeReader());
            }
            if (formats.contains(BarcodeFormat.DATA_MATRIX)) {
                readers2.add(new DataMatrixReader());
            }
            if (formats.contains(BarcodeFormat.AZTEC)) {
                readers2.add(new AztecReader());
            }
            if (formats.contains(BarcodeFormat.PDF_417)) {
                readers2.add(new PDF417Reader());
            }
            if (formats.contains(BarcodeFormat.MAXICODE)) {
                readers2.add(new MaxiCodeReader());
            }
            if (addOneDReader && tryHarder) {
                readers2.add(new MultiFormatOneDReader(hints2));
            }
        }
        if (readers2.isEmpty()) {
            if (!tryHarder) {
                readers2.add(new MultiFormatOneDReader(hints2));
            }
            readers2.add(new QRCodeReader());
            readers2.add(new DataMatrixReader());
            readers2.add(new AztecReader());
            readers2.add(new PDF417Reader());
            readers2.add(new MaxiCodeReader());
            if (tryHarder) {
                readers2.add(new MultiFormatOneDReader(hints2));
            }
        }
        this.readers = (Reader[]) readers2.toArray(new Reader[readers2.size()]);
    }

    public void reset() {
        Reader[] readerArr = this.readers;
        if (readerArr != null) {
            for (Reader reset : readerArr) {
                reset.reset();
            }
        }
    }

    private Result decodeInternal(BinaryBitmap image) throws NotFoundException {
        Reader[] readerArr = this.readers;
        if (readerArr != null) {
            int length = readerArr.length;
            int i = 0;
            while (i < length) {
                try {
                    return readerArr[i].decode(image, this.hints);
                } catch (ReaderException e) {
                    i++;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
