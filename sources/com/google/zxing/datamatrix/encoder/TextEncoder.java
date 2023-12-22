package com.google.zxing.datamatrix.encoder;

/* loaded from: classes.dex */
final class TextEncoder extends C40Encoder {
    TextEncoder() {
    }

    @Override // com.google.zxing.datamatrix.encoder.C40Encoder, com.google.zxing.datamatrix.encoder.Encoder
    public int getEncodingMode() {
        return 2;
    }

    @Override // com.google.zxing.datamatrix.encoder.C40Encoder
    int encodeChar(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c < 'a' || c > 'z') {
            if (c < ' ') {
                sb.append((char) 0);
                sb.append(c);
                return 2;
            } else if (c >= '!' && c <= '/') {
                sb.append((char) 1);
                sb.append((char) (c - '!'));
                return 2;
            } else if (c >= ':' && c <= '@') {
                sb.append((char) 1);
                sb.append((char) ((c - ':') + 15));
                return 2;
            } else if (c >= '[' && c <= '_') {
                sb.append((char) 1);
                sb.append((char) ((c - '[') + 22));
                return 2;
            } else if (c == '`') {
                sb.append((char) 2);
                sb.append((char) (c - '`'));
                return 2;
            } else if (c >= 'A' && c <= 'Z') {
                sb.append((char) 2);
                sb.append((char) ((c - 'A') + 1));
                return 2;
            } else if (c >= '{' && c <= '\u007f') {
                sb.append((char) 2);
                sb.append((char) ((c - '{') + 27));
                return 2;
            } else {
                sb.append("\u0001\u001e");
                return encodeChar((char) (c - '\u0080'), sb) + 2;
            }
        } else {
            sb.append((char) ((c - 'a') + 14));
            return 1;
        }
    }
}
