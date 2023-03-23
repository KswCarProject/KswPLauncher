package com.ibm.icu.text;

import com.bumptech.glide.load.Key;
import kotlin.UByte;

class CharsetRecog_UTF8 extends CharsetRecognizer {
    CharsetRecog_UTF8() {
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return Key.STRING_CHARSET_NAME;
    }

    /* access modifiers changed from: package-private */
    public CharsetMatch match(CharsetDetector det) {
        int trailBytes;
        boolean hasBOM = false;
        int numValid = 0;
        int numInvalid = 0;
        byte[] input = det.fRawInput;
        if (det.fRawLength >= 3 && (input[0] & UByte.MAX_VALUE) == 239 && (input[1] & UByte.MAX_VALUE) == 187 && (input[2] & UByte.MAX_VALUE) == 191) {
            hasBOM = true;
        }
        int i = 0;
        while (i < det.fRawLength) {
            byte b = input[i];
            if ((b & 128) != 0) {
                if ((b & 224) == 192) {
                    trailBytes = 1;
                } else if ((b & 240) == 224) {
                    trailBytes = 2;
                } else if ((b & 248) == 240) {
                    trailBytes = 3;
                } else {
                    numInvalid++;
                }
                while (true) {
                    i++;
                    if (i < det.fRawLength) {
                        if ((input[i] & 192) == 128) {
                            trailBytes--;
                            if (trailBytes == 0) {
                                numValid++;
                                break;
                            }
                        } else {
                            numInvalid++;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            i++;
        }
        int confidence = 0;
        if (hasBOM && numInvalid == 0) {
            confidence = 100;
        } else if (hasBOM && numValid > numInvalid * 10) {
            confidence = 80;
        } else if (numValid > 3 && numInvalid == 0) {
            confidence = 100;
        } else if (numValid > 0 && numInvalid == 0) {
            confidence = 80;
        } else if (numValid == 0 && numInvalid == 0) {
            confidence = 15;
        } else if (numValid > numInvalid * 10) {
            confidence = 25;
        }
        if (confidence == 0) {
            return null;
        }
        return new CharsetMatch(det, this, confidence);
    }
}
