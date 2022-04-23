package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

public final class ProductResultParser extends ResultParser {
    public ProductParsedResult parse(Result result) {
        String normalizedProductID;
        BarcodeFormat barcodeFormat = result.getBarcodeFormat();
        BarcodeFormat format = barcodeFormat;
        if (barcodeFormat != BarcodeFormat.UPC_A && format != BarcodeFormat.UPC_E && format != BarcodeFormat.EAN_8 && format != BarcodeFormat.EAN_13) {
            return null;
        }
        String massagedText = getMassagedText(result);
        String rawText = massagedText;
        if (!isStringOfDigits(massagedText, massagedText.length())) {
            return null;
        }
        if (format == BarcodeFormat.UPC_E && rawText.length() == 8) {
            normalizedProductID = UPCEReader.convertUPCEtoUPCA(rawText);
        } else {
            normalizedProductID = rawText;
        }
        return new ProductParsedResult(rawText, normalizedProductID);
    }
}
