package com.ibm.icu.text;

import com.ibm.icu.impl.Utility;
import com.ibm.icu.text.RuleBasedTransliterator;

class StringReplacer implements UnicodeReplacer {
    private int cursorPos;
    private final RuleBasedTransliterator.Data data;
    private boolean hasCursor;
    private boolean isComplex;
    private String output;

    public StringReplacer(String theOutput, int theCursorPos, RuleBasedTransliterator.Data theData) {
        this.output = theOutput;
        this.cursorPos = theCursorPos;
        this.hasCursor = true;
        this.data = theData;
        this.isComplex = true;
    }

    public StringReplacer(String theOutput, RuleBasedTransliterator.Data theData) {
        this.output = theOutput;
        this.cursorPos = 0;
        this.hasCursor = false;
        this.data = theData;
        this.isComplex = true;
    }

    public int replace(Replaceable text, int start, int limit, int[] cursor) {
        int outLen;
        int newStart;
        int destStart;
        Replaceable replaceable = text;
        int i = start;
        int i2 = limit;
        int[] iArr = cursor;
        int newStart2 = 0;
        if (!this.isComplex) {
            replaceable.replace(i, i2, this.output);
            outLen = this.output.length();
            newStart2 = this.cursorPos;
        } else {
            StringBuffer buf = new StringBuffer();
            this.isComplex = false;
            int tempStart = text.length();
            int destStart2 = tempStart;
            boolean z = true;
            if (i > 0) {
                int len = UTF16.getCharCount(replaceable.char32At(i - 1));
                replaceable.copy(i - len, i, tempStart);
                destStart = destStart2 + len;
            } else {
                replaceable.replace(tempStart, tempStart, "￿");
                destStart = destStart2 + 1;
            }
            int destLimit = destStart;
            int tempExtra = 0;
            int oOutput = 0;
            while (oOutput < this.output.length()) {
                if (oOutput == this.cursorPos) {
                    newStart2 = (buf.length() + destLimit) - destStart;
                }
                int c = UTF16.charAt(this.output, oOutput);
                int nextIndex = UTF16.getCharCount(c) + oOutput;
                if (nextIndex == this.output.length()) {
                    int tempExtra2 = UTF16.getCharCount(replaceable.char32At(i2));
                    replaceable.copy(i2, i2 + tempExtra2, destLimit);
                    tempExtra = tempExtra2;
                }
                UnicodeReplacer r = this.data.lookupReplacer(c);
                if (r == null) {
                    UTF16.append(buf, c);
                } else {
                    this.isComplex = z;
                    if (buf.length() > 0) {
                        replaceable.replace(destLimit, destLimit, buf.toString());
                        destLimit += buf.length();
                        buf.setLength(0);
                    }
                    destLimit += r.replace(replaceable, destLimit, destLimit, iArr);
                }
                oOutput = nextIndex;
                z = true;
            }
            if (buf.length() > 0) {
                replaceable.replace(destLimit, destLimit, buf.toString());
                destLimit += buf.length();
            }
            if (oOutput == this.cursorPos) {
                newStart2 = destLimit - destStart;
            }
            int outLen2 = destLimit - destStart;
            replaceable.copy(destStart, destLimit, i);
            replaceable.replace(tempStart + outLen2, destLimit + tempExtra + outLen2, "");
            replaceable.replace(i + outLen2, i2 + outLen2, "");
            outLen = outLen2;
        }
        if (this.hasCursor != 0) {
            int i3 = this.cursorPos;
            if (i3 < 0) {
                int newStart3 = start;
                int n = this.cursorPos;
                while (n < 0 && newStart3 > 0) {
                    newStart3 -= UTF16.getCharCount(replaceable.char32At(newStart3 - 1));
                    n++;
                }
                newStart = newStart3 + n;
            } else if (i3 > this.output.length()) {
                int newStart4 = i + outLen;
                int n2 = this.cursorPos - this.output.length();
                while (n2 > 0 && newStart4 < text.length()) {
                    newStart4 += UTF16.getCharCount(replaceable.char32At(newStart4));
                    n2--;
                }
                newStart = newStart4 + n2;
            } else {
                newStart = newStart2 + i;
            }
            iArr[0] = newStart;
        }
        return outLen;
    }

    public String toReplacerPattern(boolean escapeUnprintable) {
        int cursor;
        int cursor2;
        StringBuffer rule = new StringBuffer();
        StringBuffer quoteBuf = new StringBuffer();
        int cursor3 = this.cursorPos;
        if (this.hasCursor && cursor3 < 0) {
            while (true) {
                cursor2 = cursor3 + 1;
                if (cursor3 >= 0) {
                    break;
                }
                Utility.appendToRule(rule, 64, true, escapeUnprintable, quoteBuf);
                cursor3 = cursor2;
            }
            cursor3 = cursor2;
        }
        for (int i = 0; i < this.output.length(); i++) {
            if (this.hasCursor && i == cursor3) {
                Utility.appendToRule(rule, 124, true, escapeUnprintable, quoteBuf);
            }
            char c = this.output.charAt(i);
            UnicodeReplacer r = this.data.lookupReplacer(c);
            if (r == null) {
                Utility.appendToRule(rule, c, false, escapeUnprintable, quoteBuf);
            } else {
                StringBuffer buf = new StringBuffer(" ");
                buf.append(r.toReplacerPattern(escapeUnprintable));
                buf.append(' ');
                Utility.appendToRule(rule, buf.toString(), true, escapeUnprintable, quoteBuf);
            }
        }
        if (this.hasCursor != 0 && cursor3 > this.output.length()) {
            int cursor4 = cursor3 - this.output.length();
            while (true) {
                cursor = cursor4 - 1;
                if (cursor4 <= 0) {
                    break;
                }
                Utility.appendToRule(rule, 64, true, escapeUnprintable, quoteBuf);
                cursor4 = cursor;
            }
            Utility.appendToRule(rule, 124, true, escapeUnprintable, quoteBuf);
            int i2 = cursor;
        }
        Utility.appendToRule(rule, -1, true, escapeUnprintable, quoteBuf);
        return rule.toString();
    }

    public void addReplacementSetTo(UnicodeSet toUnionTo) {
        int i = 0;
        while (i < this.output.length()) {
            int ch = UTF16.charAt(this.output, i);
            UnicodeReplacer r = this.data.lookupReplacer(ch);
            if (r == null) {
                toUnionTo.add(ch);
            } else {
                r.addReplacementSetTo(toUnionTo);
            }
            i += UTF16.getCharCount(ch);
        }
    }
}
