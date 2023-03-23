package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import kotlin.UByte;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    public DecoderResult decode(boolean[][] image) throws FormatException, ChecksumException {
        return decode(BitMatrix.parse(image));
    }

    public DecoderResult decode(BitMatrix bits) throws FormatException, ChecksumException {
        BitMatrixParser parser = new BitMatrixParser(bits);
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(parser.readCodewords(), parser.getVersion());
        int totalBytes = 0;
        for (DataBlock db : dataBlocks) {
            totalBytes += db.getNumDataCodewords();
        }
        byte[] resultBytes = new byte[totalBytes];
        int dataBlocksCount = dataBlocks.length;
        for (int j = 0; j < dataBlocksCount; j++) {
            DataBlock dataBlock = dataBlocks[j];
            DataBlock dataBlock2 = dataBlock;
            byte[] codewordBytes = dataBlock.getCodewords();
            int numDataCodewords = dataBlock2.getNumDataCodewords();
            correctErrors(codewordBytes, numDataCodewords);
            for (int i = 0; i < numDataCodewords; i++) {
                resultBytes[(i * dataBlocksCount) + j] = codewordBytes[i];
            }
        }
        return DecodedBitStreamParser.decode(resultBytes);
    }

    private void correctErrors(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & UByte.MAX_VALUE;
        }
        try {
            this.rsDecoder.decode(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException e) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
