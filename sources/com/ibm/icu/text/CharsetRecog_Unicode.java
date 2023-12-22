package com.ibm.icu.text;

import kotlin.UByte;

/* loaded from: classes.dex */
abstract class CharsetRecog_Unicode extends CharsetRecognizer {
    @Override // com.ibm.icu.text.CharsetRecognizer
    abstract String getName();

    @Override // com.ibm.icu.text.CharsetRecognizer
    abstract CharsetMatch match(CharsetDetector charsetDetector);

    CharsetRecog_Unicode() {
    }

    static int codeUnit16FromBytes(byte hi, byte lo) {
        return ((hi & UByte.MAX_VALUE) << 8) | (lo & UByte.MAX_VALUE);
    }

    static int adjustConfidence(int codeUnit, int confidence) {
        if (codeUnit == 0) {
            confidence -= 10;
        } else if ((codeUnit >= 32 && codeUnit <= 255) || codeUnit == 10) {
            confidence += 10;
        }
        if (confidence < 0) {
            return 0;
        }
        if (confidence > 100) {
            return 100;
        }
        return confidence;
    }

    /* loaded from: classes.dex */
    static class CharsetRecog_UTF_16_BE extends CharsetRecog_Unicode {
        CharsetRecog_UTF_16_BE() {
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "UTF-16BE";
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            byte[] input = det.fRawInput;
            int confidence = 10;
            int bytesToCheck = Math.min(input.length, 30);
            int charIndex = 0;
            while (true) {
                if (charIndex >= bytesToCheck - 1) {
                    break;
                }
                int codeUnit = codeUnit16FromBytes(input[charIndex], input[charIndex + 1]);
                if (charIndex == 0 && codeUnit == 65279) {
                    confidence = 100;
                    break;
                }
                confidence = adjustConfidence(codeUnit, confidence);
                if (confidence == 0 || confidence == 100) {
                    break;
                }
                charIndex += 2;
            }
            if (bytesToCheck < 4 && confidence < 100) {
                confidence = 0;
            }
            if (confidence > 0) {
                return new CharsetMatch(det, this, confidence);
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static class CharsetRecog_UTF_16_LE extends CharsetRecog_Unicode {
        CharsetRecog_UTF_16_LE() {
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "UTF-16LE";
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            byte[] input = det.fRawInput;
            int confidence = 10;
            int bytesToCheck = Math.min(input.length, 30);
            int charIndex = 0;
            while (true) {
                if (charIndex >= bytesToCheck - 1) {
                    break;
                }
                int codeUnit = codeUnit16FromBytes(input[charIndex + 1], input[charIndex]);
                if (charIndex == 0 && codeUnit == 65279) {
                    confidence = 100;
                    break;
                }
                confidence = adjustConfidence(codeUnit, confidence);
                if (confidence == 0 || confidence == 100) {
                    break;
                }
                charIndex += 2;
            }
            if (bytesToCheck < 4 && confidence < 100) {
                confidence = 0;
            }
            if (confidence > 0) {
                return new CharsetMatch(det, this, confidence);
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static abstract class CharsetRecog_UTF_32 extends CharsetRecog_Unicode {
        abstract int getChar(byte[] bArr, int i);

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        abstract String getName();

        CharsetRecog_UTF_32() {
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        CharsetMatch match(CharsetDetector det) {
            byte[] input = det.fRawInput;
            int limit = (det.fRawLength / 4) * 4;
            int numValid = 0;
            int numInvalid = 0;
            boolean hasBOM = false;
            int confidence = 0;
            if (limit == 0) {
                return null;
            }
            if (getChar(input, 0) == 65279) {
                hasBOM = true;
            }
            for (int i = 0; i < limit; i += 4) {
                int ch = getChar(input, i);
                if (ch < 0 || ch >= 1114111 || (ch >= 55296 && ch <= 57343)) {
                    numInvalid++;
                } else {
                    numValid++;
                }
            }
            if (hasBOM && numInvalid == 0) {
                confidence = 100;
            } else if (hasBOM && numValid > numInvalid * 10) {
                confidence = 80;
            } else if (numValid > 3 && numInvalid == 0) {
                confidence = 100;
            } else if (numValid > 0 && numInvalid == 0) {
                confidence = 80;
            } else if (numValid > numInvalid * 10) {
                confidence = 25;
            }
            if (confidence == 0) {
                return null;
            }
            return new CharsetMatch(det, this, confidence);
        }
    }

    /* loaded from: classes.dex */
    static class CharsetRecog_UTF_32_BE extends CharsetRecog_UTF_32 {
        CharsetRecog_UTF_32_BE() {
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32
        int getChar(byte[] input, int index) {
            return ((input[index + 0] & UByte.MAX_VALUE) << 24) | ((input[index + 1] & UByte.MAX_VALUE) << 16) | ((input[index + 2] & UByte.MAX_VALUE) << 8) | (input[index + 3] & UByte.MAX_VALUE);
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32, com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "UTF-32BE";
        }
    }

    /* loaded from: classes.dex */
    static class CharsetRecog_UTF_32_LE extends CharsetRecog_UTF_32 {
        CharsetRecog_UTF_32_LE() {
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32
        int getChar(byte[] input, int index) {
            return ((input[index + 3] & UByte.MAX_VALUE) << 24) | ((input[index + 2] & UByte.MAX_VALUE) << 16) | ((input[index + 1] & UByte.MAX_VALUE) << 8) | (input[index + 0] & UByte.MAX_VALUE);
        }

        @Override // com.ibm.icu.text.CharsetRecog_Unicode.CharsetRecog_UTF_32, com.ibm.icu.text.CharsetRecog_Unicode, com.ibm.icu.text.CharsetRecognizer
        String getName() {
            return "UTF-32LE";
        }
    }
}
