package com.google.zxing.client.result;

public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01e5, code lost:
        if (r3.equals("10") != false) goto L_0x01fd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.ExpandedProductParsedResult parse(com.google.zxing.Result r34) {
        /*
            r33 = this;
            com.google.zxing.BarcodeFormat r0 = r34.getBarcodeFormat()
            com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.RSS_EXPANDED
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r0 = getMassagedText(r34)
            r1 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            java.util.HashMap r15 = new java.util.HashMap
            r15.<init>()
            r16 = 0
            r19 = r3
            r20 = r4
            r21 = r5
            r22 = r6
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r10
            r27 = r11
            r28 = r12
            r29 = r13
            r30 = r14
            r14 = r16
            r3 = r2
        L_0x003d:
            int r4 = r0.length()
            if (r14 >= r4) goto L_0x0251
            java.lang.String r4 = findAIvalue(r14, r0)
            r3 = r4
            if (r4 != 0) goto L_0x004b
            return r2
        L_0x004b:
            int r4 = r3.length()
            r5 = 2
            int r4 = r4 + r5
            int r4 = r4 + r14
            r6 = r4
            java.lang.String r4 = findValue(r4, r0)
            int r7 = r4.length()
            int r14 = r6 + r7
            r6 = -1
            int r7 = r3.hashCode()
            r8 = 0
            r9 = 4
            r10 = 3
            switch(r7) {
                case 1536: goto L_0x01f2;
                case 1537: goto L_0x01e8;
                case 1567: goto L_0x01df;
                case 1568: goto L_0x01d5;
                case 1570: goto L_0x01cb;
                case 1572: goto L_0x01c1;
                case 1574: goto L_0x01b7;
                case 1567966: goto L_0x01ad;
                case 1567967: goto L_0x01a2;
                case 1567968: goto L_0x0196;
                case 1567969: goto L_0x018a;
                case 1567970: goto L_0x017e;
                case 1567971: goto L_0x0172;
                case 1567972: goto L_0x0166;
                case 1567973: goto L_0x015a;
                case 1567974: goto L_0x014e;
                case 1567975: goto L_0x0142;
                case 1568927: goto L_0x0136;
                case 1568928: goto L_0x012a;
                case 1568929: goto L_0x011e;
                case 1568930: goto L_0x0112;
                case 1568931: goto L_0x0106;
                case 1568932: goto L_0x00fa;
                case 1568933: goto L_0x00ee;
                case 1568934: goto L_0x00e2;
                case 1568935: goto L_0x00d6;
                case 1568936: goto L_0x00ca;
                case 1575716: goto L_0x00be;
                case 1575717: goto L_0x00b2;
                case 1575718: goto L_0x00a6;
                case 1575719: goto L_0x009a;
                case 1575747: goto L_0x008e;
                case 1575748: goto L_0x0082;
                case 1575749: goto L_0x0076;
                case 1575750: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x01fc
        L_0x006a:
            java.lang.String r5 = "3933"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 34
            goto L_0x01fd
        L_0x0076:
            java.lang.String r5 = "3932"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 33
            goto L_0x01fd
        L_0x0082:
            java.lang.String r5 = "3931"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 32
            goto L_0x01fd
        L_0x008e:
            java.lang.String r5 = "3930"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 31
            goto L_0x01fd
        L_0x009a:
            java.lang.String r5 = "3923"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 30
            goto L_0x01fd
        L_0x00a6:
            java.lang.String r5 = "3922"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 29
            goto L_0x01fd
        L_0x00b2:
            java.lang.String r5 = "3921"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 28
            goto L_0x01fd
        L_0x00be:
            java.lang.String r5 = "3920"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 27
            goto L_0x01fd
        L_0x00ca:
            java.lang.String r5 = "3209"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 26
            goto L_0x01fd
        L_0x00d6:
            java.lang.String r5 = "3208"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 25
            goto L_0x01fd
        L_0x00e2:
            java.lang.String r5 = "3207"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 24
            goto L_0x01fd
        L_0x00ee:
            java.lang.String r5 = "3206"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 23
            goto L_0x01fd
        L_0x00fa:
            java.lang.String r5 = "3205"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 22
            goto L_0x01fd
        L_0x0106:
            java.lang.String r5 = "3204"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 21
            goto L_0x01fd
        L_0x0112:
            java.lang.String r5 = "3203"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 20
            goto L_0x01fd
        L_0x011e:
            java.lang.String r5 = "3202"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 19
            goto L_0x01fd
        L_0x012a:
            java.lang.String r5 = "3201"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 18
            goto L_0x01fd
        L_0x0136:
            java.lang.String r5 = "3200"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 17
            goto L_0x01fd
        L_0x0142:
            java.lang.String r5 = "3109"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 16
            goto L_0x01fd
        L_0x014e:
            java.lang.String r5 = "3108"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 15
            goto L_0x01fd
        L_0x015a:
            java.lang.String r5 = "3107"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 14
            goto L_0x01fd
        L_0x0166:
            java.lang.String r5 = "3106"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 13
            goto L_0x01fd
        L_0x0172:
            java.lang.String r5 = "3105"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 12
            goto L_0x01fd
        L_0x017e:
            java.lang.String r5 = "3104"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 11
            goto L_0x01fd
        L_0x018a:
            java.lang.String r5 = "3103"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 10
            goto L_0x01fd
        L_0x0196:
            java.lang.String r5 = "3102"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 9
            goto L_0x01fd
        L_0x01a2:
            java.lang.String r5 = "3101"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 8
            goto L_0x01fd
        L_0x01ad:
            java.lang.String r5 = "3100"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 7
            goto L_0x01fd
        L_0x01b7:
            java.lang.String r5 = "17"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 6
            goto L_0x01fd
        L_0x01c1:
            java.lang.String r5 = "15"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 5
            goto L_0x01fd
        L_0x01cb:
            java.lang.String r5 = "13"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = r9
            goto L_0x01fd
        L_0x01d5:
            java.lang.String r5 = "11"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = r10
            goto L_0x01fd
        L_0x01df:
            java.lang.String r7 = "10"
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L_0x0068
            goto L_0x01fd
        L_0x01e8:
            java.lang.String r5 = "01"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = 1
            goto L_0x01fd
        L_0x01f2:
            java.lang.String r5 = "00"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x0068
            r5 = r8
            goto L_0x01fd
        L_0x01fc:
            r5 = r6
        L_0x01fd:
            switch(r5) {
                case 0: goto L_0x024d;
                case 1: goto L_0x024a;
                case 2: goto L_0x0246;
                case 3: goto L_0x0242;
                case 4: goto L_0x023e;
                case 5: goto L_0x023a;
                case 6: goto L_0x0236;
                case 7: goto L_0x022c;
                case 8: goto L_0x022c;
                case 9: goto L_0x022c;
                case 10: goto L_0x022c;
                case 11: goto L_0x022c;
                case 12: goto L_0x022c;
                case 13: goto L_0x022c;
                case 14: goto L_0x022c;
                case 15: goto L_0x022c;
                case 16: goto L_0x022c;
                case 17: goto L_0x0222;
                case 18: goto L_0x0222;
                case 19: goto L_0x0222;
                case 20: goto L_0x0222;
                case 21: goto L_0x0222;
                case 22: goto L_0x0222;
                case 23: goto L_0x0222;
                case 24: goto L_0x0222;
                case 25: goto L_0x0222;
                case 26: goto L_0x0222;
                case 27: goto L_0x021a;
                case 28: goto L_0x021a;
                case 29: goto L_0x021a;
                case 30: goto L_0x021a;
                case 31: goto L_0x0205;
                case 32: goto L_0x0205;
                case 33: goto L_0x0205;
                case 34: goto L_0x0205;
                default: goto L_0x0200;
            }
        L_0x0200:
            r15.put(r3, r4)
            goto L_0x003d
        L_0x0205:
            int r5 = r4.length()
            if (r5 >= r9) goto L_0x020c
            return r2
        L_0x020c:
            java.lang.String r28 = r4.substring(r10)
            java.lang.String r30 = r4.substring(r8, r10)
            java.lang.String r29 = r3.substring(r10)
            goto L_0x003d
        L_0x021a:
            r28 = r4
            java.lang.String r29 = r3.substring(r10)
            goto L_0x003d
        L_0x0222:
            r25 = r4
            java.lang.String r26 = "LB"
            java.lang.String r27 = r3.substring(r10)
            goto L_0x003d
        L_0x022c:
            r25 = r4
            java.lang.String r26 = "KG"
            java.lang.String r27 = r3.substring(r10)
            goto L_0x003d
        L_0x0236:
            r24 = r4
            goto L_0x003d
        L_0x023a:
            r23 = r4
            goto L_0x003d
        L_0x023e:
            r22 = r4
            goto L_0x003d
        L_0x0242:
            r21 = r4
            goto L_0x003d
        L_0x0246:
            r20 = r4
            goto L_0x003d
        L_0x024a:
            r1 = r4
            goto L_0x003d
        L_0x024d:
            r19 = r4
            goto L_0x003d
        L_0x0251:
            com.google.zxing.client.result.ExpandedProductParsedResult r2 = new com.google.zxing.client.result.ExpandedProductParsedResult
            r3 = r2
            r4 = r0
            r5 = r1
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r31 = r14
            r14 = r27
            r32 = r15
            r15 = r28
            r16 = r29
            r17 = r30
            r18 = r32
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.ExpandedProductResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.ExpandedProductParsedResult");
    }

    private static String findAIvalue(int i, String rawText) {
        if (rawText.charAt(i) != '(') {
            return null;
        }
        CharSequence rawTextAux = rawText.substring(i + 1);
        StringBuilder buf = new StringBuilder();
        for (int index = 0; index < rawTextAux.length(); index++) {
            char charAt = rawTextAux.charAt(index);
            char currentChar = charAt;
            if (charAt == ')') {
                return buf.toString();
            }
            if (currentChar < '0' || currentChar > '9') {
                return null;
            }
            buf.append(currentChar);
        }
        return buf.toString();
    }

    private static String findValue(int i, String rawText) {
        StringBuilder buf = new StringBuilder();
        String rawTextAux = rawText.substring(i);
        for (int index = 0; index < rawTextAux.length(); index++) {
            char charAt = rawTextAux.charAt(index);
            char c = charAt;
            if (charAt == '(') {
                if (findAIvalue(index, rawTextAux) != null) {
                    break;
                }
                buf.append('(');
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }
}
