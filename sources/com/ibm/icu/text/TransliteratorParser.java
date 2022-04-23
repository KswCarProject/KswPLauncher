package com.ibm.icu.text;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Normalizer;
import com.ibm.icu.text.RuleBasedTransliterator;
import java.text.ParsePosition;
import java.util.List;
import java.util.Map;

class TransliteratorParser {
    private static final char ALT_FORWARD_RULE_OP = '→';
    private static final char ALT_FUNCTION = '∆';
    private static final char ALT_FWDREV_RULE_OP = '↔';
    private static final char ALT_REVERSE_RULE_OP = '←';
    private static final char ANCHOR_START = '^';
    private static final char CONTEXT_ANTE = '{';
    private static final char CONTEXT_POST = '}';
    private static final char CURSOR_OFFSET = '@';
    private static final char CURSOR_POS = '|';
    private static final char DOT = '.';
    private static final String DOT_SET = "[^[:Zp:][:Zl:]\\r\\n$]";
    private static final char END_OF_RULE = ';';
    private static final char ESCAPE = '\\';
    private static final char FORWARD_RULE_OP = '>';
    private static final char FUNCTION = '&';
    private static final char FWDREV_RULE_OP = '~';
    private static final String HALF_ENDERS = "=><←→↔;";
    private static final String ID_TOKEN = "::";
    private static final int ID_TOKEN_LEN = 2;
    /* access modifiers changed from: private */
    public static UnicodeSet ILLEGAL_FUNC = new UnicodeSet("[\\^\\(\\.\\*\\+\\?\\{\\}\\|\\@]");
    /* access modifiers changed from: private */
    public static UnicodeSet ILLEGAL_SEG = new UnicodeSet("[\\{\\}\\|\\@]");
    /* access modifiers changed from: private */
    public static UnicodeSet ILLEGAL_TOP = new UnicodeSet("[\\)]");
    private static final char KLEENE_STAR = '*';
    private static final char ONE_OR_MORE = '+';
    private static final String OPERATORS = "=><←→↔";
    private static final char QUOTE = '\'';
    private static final char REVERSE_RULE_OP = '<';
    private static final char RULE_COMMENT_CHAR = '#';
    private static final char SEGMENT_CLOSE = ')';
    private static final char SEGMENT_OPEN = '(';
    private static final char VARIABLE_DEF_OP = '=';
    private static final char ZERO_OR_ONE = '?';
    public UnicodeSet compoundFilter;
    /* access modifiers changed from: private */
    public RuleBasedTransliterator.Data curData;
    public List<RuleBasedTransliterator.Data> dataVector;
    private int direction;
    private int dotStandIn = -1;
    public List<String> idBlockVector;
    /* access modifiers changed from: private */
    public ParseData parseData;
    private List<StringMatcher> segmentObjects;
    private StringBuffer segmentStandins;
    private String undefinedVariableName;
    private char variableLimit;
    /* access modifiers changed from: private */
    public Map<String, char[]> variableNames;
    private char variableNext;
    /* access modifiers changed from: private */
    public List<Object> variablesVector;

    private class ParseData implements SymbolTable {
        private ParseData() {
        }

        public char[] lookup(String name) {
            return (char[]) TransliteratorParser.this.variableNames.get(name);
        }

        public UnicodeMatcher lookupMatcher(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return null;
            }
            return (UnicodeMatcher) TransliteratorParser.this.variablesVector.get(i);
        }

        public String parseReference(String text, ParsePosition pos, int limit) {
            int start = pos.getIndex();
            int i = start;
            while (i < limit) {
                char c = text.charAt(i);
                if ((i == start && !UCharacter.isUnicodeIdentifierStart(c)) || !UCharacter.isUnicodeIdentifierPart(c)) {
                    break;
                }
                i++;
            }
            if (i == start) {
                return null;
            }
            pos.setIndex(i);
            return text.substring(start, i);
        }

        public boolean isMatcher(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeMatcher;
        }

        public boolean isReplacer(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i < 0 || i >= TransliteratorParser.this.variablesVector.size()) {
                return true;
            }
            return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeReplacer;
        }
    }

    private static abstract class RuleBody {
        /* access modifiers changed from: package-private */
        public abstract String handleNextLine();

        /* access modifiers changed from: package-private */
        public abstract void reset();

        private RuleBody() {
        }

        /* access modifiers changed from: package-private */
        public String nextLine() {
            String s;
            String s2 = handleNextLine();
            if (s2 == null || s2.length() <= 0 || s2.charAt(s2.length() - 1) != '\\') {
                return s2;
            }
            StringBuilder b = new StringBuilder(s2);
            do {
                b.deleteCharAt(b.length() - 1);
                s = handleNextLine();
                if (s == null) {
                    break;
                }
                b.append(s);
                if (s.length() <= 0) {
                    break;
                }
            } while (s.charAt(s.length() - 1) != '\\');
            return b.toString();
        }
    }

    private static class RuleArray extends RuleBody {
        String[] array;
        int i = 0;

        public RuleArray(String[] array2) {
            super();
            this.array = array2;
        }

        public String handleNextLine() {
            int i2 = this.i;
            String[] strArr = this.array;
            if (i2 >= strArr.length) {
                return null;
            }
            this.i = i2 + 1;
            return strArr[i2];
        }

        public void reset() {
            this.i = 0;
        }
    }

    private static class RuleHalf {
        public boolean anchorEnd;
        public boolean anchorStart;
        public int ante;
        public int cursor;
        public int cursorOffset;
        private int cursorOffsetPos;
        private int nextSegmentNumber;
        public int post;
        public String text;

        private RuleHalf() {
            this.cursor = -1;
            this.ante = -1;
            this.post = -1;
            this.cursorOffset = 0;
            this.cursorOffsetPos = 0;
            this.anchorStart = false;
            this.anchorEnd = false;
            this.nextSegmentNumber = 1;
        }

        public int parse(String rule, int pos, int limit, TransliteratorParser parser) {
            int start = pos;
            StringBuffer buf = new StringBuffer();
            int pos2 = parseSection(rule, pos, limit, parser, buf, TransliteratorParser.ILLEGAL_TOP, false);
            this.text = buf.toString();
            if (this.cursorOffset > 0 && this.cursor != this.cursorOffsetPos) {
                TransliteratorParser.syntaxError("Misplaced |", rule, start);
            }
            return pos2;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x051a, code lost:
            r9 = r1;
            r8 = r2;
            r15 = r6;
            r6 = r24;
            r7 = r25;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int parseSection(java.lang.String r29, int r30, int r31, com.ibm.icu.text.TransliteratorParser r32, java.lang.StringBuffer r33, com.ibm.icu.text.UnicodeSet r34, boolean r35) {
            /*
                r28 = this;
                r9 = r28
                r10 = r29
                r11 = r31
                r12 = r32
                r13 = r33
                r14 = r30
                r0 = 0
                r1 = -1
                r2 = -1
                r3 = -1
                r4 = -1
                r15 = 1
                int[] r8 = new int[r15]
                int r7 = r33.length()
                r16 = r0
                r17 = r1
                r6 = r2
                r18 = r3
                r5 = r4
                r0 = r30
            L_0x0022:
                if (r0 >= r11) goto L_0x0523
                int r4 = r0 + 1
                char r3 = r10.charAt(r0)
                boolean r0 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r3)
                if (r0 == 0) goto L_0x0032
                r0 = r4
                goto L_0x0022
            L_0x0032:
                java.lang.String r0 = "=><←→↔;"
                int r0 = r0.indexOf(r3)
                if (r0 < 0) goto L_0x0051
                if (r35 == 0) goto L_0x0049
                java.lang.String r0 = "Unclosed segment"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
                r24 = r5
                r19 = r8
                r8 = r34
                goto L_0x0390
            L_0x0049:
                r24 = r5
                r19 = r8
                r8 = r34
                goto L_0x0390
            L_0x0051:
                boolean r0 = r9.anchorEnd
                if (r0 == 0) goto L_0x005a
                java.lang.String r0 = "Malformed variable reference"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x005a:
                int r0 = r4 + -1
                boolean r0 = com.ibm.icu.text.UnicodeSet.resemblesPattern(r10, r0)
                r1 = 0
                if (r0 == 0) goto L_0x0081
                if (r16 != 0) goto L_0x006b
                java.text.ParsePosition r0 = new java.text.ParsePosition
                r0.<init>(r1)
                goto L_0x006d
            L_0x006b:
                r0 = r16
            L_0x006d:
                int r1 = r4 + -1
                r0.setIndex(r1)
                char r1 = r12.parseSet(r10, r0)
                r13.append(r1)
                int r1 = r0.getIndex()
                r16 = r0
                r0 = r1
                goto L_0x0022
            L_0x0081:
                r0 = 92
                r2 = -1
                if (r3 != r0) goto L_0x00a5
                if (r4 != r11) goto L_0x008d
                java.lang.String r0 = "Trailing backslash"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x008d:
                r8[r1] = r4
                int r0 = com.ibm.icu.impl.Utility.unescapeAt(r10, r8)
                r1 = r8[r1]
                if (r0 != r2) goto L_0x009c
                java.lang.String r2 = "Malformed escape"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r2, r10, r14)
            L_0x009c:
                r12.checkVariableRange(r0, r10, r14)
                com.ibm.icu.text.UTF16.append(r13, r0)
                r0 = r1
                goto L_0x0022
            L_0x00a5:
                r0 = 39
                if (r3 != r0) goto L_0x00ee
                int r1 = r10.indexOf(r0, r4)
                if (r1 != r4) goto L_0x00b6
                r13.append(r3)
                int r0 = r4 + 1
                goto L_0x0022
            L_0x00b6:
                int r17 = r33.length()
            L_0x00ba:
                if (r1 >= 0) goto L_0x00c1
                java.lang.String r2 = "Unterminated quote"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r2, r10, r14)
            L_0x00c1:
                java.lang.String r2 = r10.substring(r4, r1)
                r13.append(r2)
                int r4 = r1 + 1
                if (r4 >= r11) goto L_0x00d9
                char r2 = r10.charAt(r4)
                if (r2 != r0) goto L_0x00d9
                int r2 = r4 + 1
                int r1 = r10.indexOf(r0, r2)
                goto L_0x00ba
            L_0x00d9:
                int r6 = r33.length()
                r0 = r17
            L_0x00df:
                if (r0 >= r6) goto L_0x00eb
                char r1 = r13.charAt(r0)
                r12.checkVariableRange(r1, r10, r14)
                int r0 = r0 + 1
                goto L_0x00df
            L_0x00eb:
                r0 = r4
                goto L_0x0022
            L_0x00ee:
                r12.checkVariableRange(r3, r10, r14)
                r19 = r8
                r8 = r34
                boolean r20 = r8.contains((int) r3)
                if (r20 == 0) goto L_0x0115
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Illegal character '"
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.String r0 = r0.toString()
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x0115:
                switch(r3) {
                    case 36: goto L_0x046f;
                    case 38: goto L_0x03f5;
                    case 40: goto L_0x039c;
                    case 41: goto L_0x038c;
                    case 42: goto L_0x0284;
                    case 43: goto L_0x0284;
                    case 46: goto L_0x0270;
                    case 63: goto L_0x0284;
                    case 64: goto L_0x01b6;
                    case 94: goto L_0x018b;
                    case 123: goto L_0x016f;
                    case 124: goto L_0x0153;
                    case 125: goto L_0x0137;
                    case 8710: goto L_0x03f5;
                    default: goto L_0x0118;
                }
            L_0x0118:
                r27 = r3
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                r0 = 33
                if (r3 < r0) goto L_0x0514
                r0 = 126(0x7e, float:1.77E-43)
                if (r3 > r0) goto L_0x0514
                r0 = 48
                if (r3 < r0) goto L_0x04ee
                r0 = 57
                if (r3 <= r0) goto L_0x0514
                goto L_0x04ee
            L_0x0137:
                int r0 = r9.post
                if (r0 < 0) goto L_0x0140
                java.lang.String r0 = "Multiple post contexts"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x0140:
                int r0 = r33.length()
                r9.post = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x0153:
                int r0 = r9.cursor
                if (r0 < 0) goto L_0x015c
                java.lang.String r0 = "Multiple cursors"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x015c:
                int r0 = r33.length()
                r9.cursor = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x016f:
                int r0 = r9.ante
                if (r0 < 0) goto L_0x0178
                java.lang.String r0 = "Multiple ante contexts"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x0178:
                int r0 = r33.length()
                r9.ante = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x018b:
                int r0 = r33.length()
                if (r0 != 0) goto L_0x01a4
                boolean r0 = r9.anchorStart
                if (r0 != 0) goto L_0x01a4
                r9.anchorStart = r15
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x01a4:
                java.lang.String r0 = "Misplaced anchor start"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x01b6:
                int r0 = r9.cursorOffset
                java.lang.String r1 = "Misplaced "
                if (r0 >= 0) goto L_0x01e8
                int r0 = r33.length()
                if (r0 <= 0) goto L_0x01d6
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.StringBuilder r0 = r0.append(r3)
                java.lang.String r0 = r0.toString()
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x01d6:
                int r0 = r9.cursorOffset
                int r0 = r0 - r15
                r9.cursorOffset = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x01e8:
                if (r0 <= 0) goto L_0x021c
                int r0 = r33.length()
                int r2 = r9.cursorOffsetPos
                if (r0 != r2) goto L_0x01f6
                int r0 = r9.cursor
                if (r0 < 0) goto L_0x020a
            L_0x01f6:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.StringBuilder r0 = r0.append(r3)
                java.lang.String r0 = r0.toString()
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x020a:
                int r0 = r9.cursorOffset
                int r0 = r0 + r15
                r9.cursorOffset = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x021c:
                int r0 = r9.cursor
                if (r0 != 0) goto L_0x0236
                int r0 = r33.length()
                if (r0 != 0) goto L_0x0236
                r0 = -1
                r9.cursorOffset = r0
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x0236:
                int r0 = r9.cursor
                if (r0 >= 0) goto L_0x024f
                int r0 = r33.length()
                r9.cursorOffsetPos = r0
                r9.cursorOffset = r15
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x024f:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.StringBuilder r0 = r0.append(r3)
                java.lang.String r0 = r0.toString()
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x0270:
                char r0 = r32.getDotStandIn()
                r13.append(r0)
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x0284:
                if (r35 == 0) goto L_0x029e
                int r0 = r33.length()
                if (r0 != r7) goto L_0x029e
                java.lang.String r0 = "Misplaced quantifier"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r6 = r15
                r2 = r19
                r15 = r4
                goto L_0x0517
            L_0x029e:
                int r0 = r33.length()
                if (r0 != r6) goto L_0x02aa
                r0 = r17
                r1 = r6
                r2 = r1
                r1 = r0
                goto L_0x02bf
            L_0x02aa:
                int r0 = r33.length()
                if (r0 != r5) goto L_0x02b6
                r0 = r18
                r1 = r5
                r2 = r1
                r1 = r0
                goto L_0x02bf
            L_0x02b6:
                int r0 = r33.length()
                int r0 = r0 - r15
                int r1 = r0 + 1
                r2 = r1
                r1 = r0
            L_0x02bf:
                com.ibm.icu.text.StringMatcher r0 = new com.ibm.icu.text.StringMatcher     // Catch:{ RuntimeException -> 0x0316 }
                java.lang.String r21 = r33.toString()     // Catch:{ RuntimeException -> 0x0316 }
                r24 = 0
                com.ibm.icu.text.RuleBasedTransliterator$Data r25 = r32.curData     // Catch:{ RuntimeException -> 0x0316 }
                r20 = r0
                r22 = r1
                r23 = r2
                r20.<init>(r21, r22, r23, r24, r25)     // Catch:{ RuntimeException -> 0x0316 }
                r20 = 0
                r21 = 2147483647(0x7fffffff, float:NaN)
                switch(r3) {
                    case 43: goto L_0x02ef;
                    case 63: goto L_0x02e4;
                    default: goto L_0x02dd;
                }
            L_0x02dd:
                r15 = r20
                r20 = r2
                r2 = r21
                goto L_0x02f7
            L_0x02e4:
                r20 = 0
                r21 = 1
                r15 = r20
                r20 = r2
                r2 = r21
                goto L_0x02f7
            L_0x02ef:
                r20 = 1
                r15 = r20
                r20 = r2
                r2 = r21
            L_0x02f7:
                r21 = r3
                com.ibm.icu.text.Quantifier r3 = new com.ibm.icu.text.Quantifier
                r3.<init>(r0, r15, r2)
                r0 = r3
                r13.setLength(r1)
                char r3 = r12.generateStandInFor(r0)
                r13.append(r3)
                r15 = r4
                r21 = r5
                r24 = r6
                r25 = r7
                r1 = r9
                r2 = r19
                r6 = 1
                goto L_0x0517
            L_0x0316:
                r0 = move-exception
                r20 = r2
                r21 = r3
                java.lang.String r2 = "..."
                r3 = 50
                if (r4 >= r3) goto L_0x0327
                r15 = 0
                java.lang.String r15 = r10.substring(r15, r4)
                goto L_0x033e
            L_0x0327:
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                r15.<init>()
                java.lang.StringBuilder r15 = r15.append(r2)
                int r3 = r4 + -50
                java.lang.String r3 = r10.substring(r3, r4)
                java.lang.StringBuilder r3 = r15.append(r3)
                java.lang.String r15 = r3.toString()
            L_0x033e:
                r3 = r15
                int r15 = r11 - r4
                r23 = r1
                r1 = 50
                if (r15 > r1) goto L_0x034c
                java.lang.String r1 = r10.substring(r4, r11)
                goto L_0x0363
            L_0x034c:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                int r15 = r4 + 50
                java.lang.String r15 = r10.substring(r4, r15)
                java.lang.StringBuilder r1 = r1.append(r15)
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
            L_0x0363:
                com.ibm.icu.impl.IllegalIcuArgumentException r2 = new com.ibm.icu.impl.IllegalIcuArgumentException
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                r15.<init>()
                r24 = r5
                java.lang.String r5 = "Failure in rule: "
                java.lang.StringBuilder r5 = r15.append(r5)
                java.lang.StringBuilder r5 = r5.append(r3)
                java.lang.String r15 = "$$$"
                java.lang.StringBuilder r5 = r5.append(r15)
                java.lang.StringBuilder r5 = r5.append(r1)
                java.lang.String r5 = r5.toString()
                r2.<init>(r5)
                com.ibm.icu.impl.IllegalIcuArgumentException r2 = r2.initCause(r0)
                throw r2
            L_0x038c:
                r21 = r3
                r24 = r5
            L_0x0390:
                r0 = r4
                r25 = r7
                r1 = r9
                r2 = r19
                r21 = r24
                r24 = r6
                goto L_0x052b
            L_0x039c:
                r21 = r3
                r24 = r5
                int r0 = r33.length()
                int r1 = r9.nextSegmentNumber
                int r2 = r1 + 1
                r9.nextSegmentNumber = r2
                r15 = r1
                com.ibm.icu.text.UnicodeSet r20 = com.ibm.icu.text.TransliteratorParser.ILLEGAL_SEG
                r23 = 1
                r1 = r28
                r2 = r29
                r5 = r21
                r3 = r4
                r26 = r4
                r4 = r31
                r27 = r5
                r21 = r24
                r5 = r32
                r24 = r6
                r6 = r33
                r25 = r7
                r7 = r20
                r9 = r19
                r8 = r23
                int r1 = r1.parseSection(r2, r3, r4, r5, r6, r7, r8)
                com.ibm.icu.text.StringMatcher r2 = new com.ibm.icu.text.StringMatcher
                java.lang.String r3 = r13.substring(r0)
                com.ibm.icu.text.RuleBasedTransliterator$Data r4 = r32.curData
                r2.<init>(r3, r15, r4)
                r12.setSegmentObject(r15, r2)
                r13.setLength(r0)
                char r3 = r12.getSegmentStandin(r15)
                r13.append(r3)
                r6 = 1
                r0 = r1
                r2 = r9
                r5 = r21
                r1 = r28
                goto L_0x051a
            L_0x03f5:
                r27 = r3
                r26 = r4
                r21 = r5
                r24 = r6
                r25 = r7
                r9 = r19
                r15 = r26
                r1 = 0
                r9[r1] = r15
                com.ibm.icu.text.TransliteratorIDParser$SingleID r0 = com.ibm.icu.text.TransliteratorIDParser.parseFilterID(r10, r9)
                if (r0 == 0) goto L_0x0414
                r1 = 40
                boolean r1 = com.ibm.icu.impl.Utility.parseChar(r10, r9, r1)
                if (r1 != 0) goto L_0x0419
            L_0x0414:
                java.lang.String r1 = "Invalid function"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r1, r10, r14)
            L_0x0419:
                com.ibm.icu.text.Transliterator r8 = r0.getInstance()
                if (r8 != 0) goto L_0x0424
                java.lang.String r1 = "Invalid function ID"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r1, r10, r14)
            L_0x0424:
                int r7 = r33.length()
                r1 = 0
                r3 = r9[r1]
                com.ibm.icu.text.UnicodeSet r19 = com.ibm.icu.text.TransliteratorParser.ILLEGAL_FUNC
                r20 = 1
                r1 = r28
                r2 = r29
                r4 = r31
                r5 = r32
                r6 = r33
                r30 = r0
                r0 = r7
                r7 = r19
                r19 = r9
                r9 = r8
                r8 = r20
                int r1 = r1.parseSection(r2, r3, r4, r5, r6, r7, r8)
                com.ibm.icu.text.FunctionReplacer r2 = new com.ibm.icu.text.FunctionReplacer
                com.ibm.icu.text.StringReplacer r3 = new com.ibm.icu.text.StringReplacer
                java.lang.String r4 = r13.substring(r0)
                com.ibm.icu.text.RuleBasedTransliterator$Data r5 = r32.curData
                r3.<init>(r4, r5)
                r2.<init>(r9, r3)
                r13.setLength(r0)
                char r3 = r12.generateStandInFor(r2)
                r13.append(r3)
                r6 = 1
                r0 = r1
                r2 = r19
                r5 = r21
                r1 = r28
                goto L_0x051a
            L_0x046f:
                r27 = r3
                r15 = r4
                r21 = r5
                r24 = r6
                r25 = r7
                if (r15 != r11) goto L_0x0484
                r0 = 1
                r1 = r28
                r2 = r19
                r1.anchorEnd = r0
                r6 = r0
                goto L_0x0517
            L_0x0484:
                r0 = 1
                r1 = r28
                r2 = r19
                char r3 = r10.charAt(r15)
                r4 = 10
                int r5 = com.ibm.icu.lang.UCharacter.digit(r3, r4)
                if (r5 < r0) goto L_0x04b5
                r0 = 9
                if (r5 > r0) goto L_0x04b5
                r6 = 0
                r2[r6] = r15
                int r0 = com.ibm.icu.impl.Utility.parseNumber(r10, r2, r4)
                if (r0 >= 0) goto L_0x04a7
                java.lang.String r4 = "Undefined segment reference"
                com.ibm.icu.text.TransliteratorParser.syntaxError(r4, r10, r14)
            L_0x04a7:
                r4 = 0
                r4 = r2[r4]
                char r5 = r12.getSegmentStandin(r0)
                r13.append(r5)
                r5 = r21
                r6 = 1
                goto L_0x04ec
            L_0x04b5:
                if (r16 != 0) goto L_0x04c0
                java.text.ParsePosition r0 = new java.text.ParsePosition
                r4 = 0
                r0.<init>(r4)
                r16 = r0
                goto L_0x04c2
            L_0x04c0:
                r0 = r16
            L_0x04c2:
                r0.setIndex(r15)
                com.ibm.icu.text.TransliteratorParser$ParseData r4 = r32.parseData
                java.lang.String r4 = r4.parseReference(r10, r0, r11)
                if (r4 != 0) goto L_0x04d8
                r6 = 1
                r1.anchorEnd = r6
                r16 = r0
                r0 = r15
                r5 = r21
                goto L_0x051a
            L_0x04d8:
                r6 = 1
                int r7 = r0.getIndex()
                int r18 = r33.length()
                r12.appendVariableDef(r4, r13)
                int r8 = r33.length()
                r16 = r0
                r4 = r7
                r5 = r8
            L_0x04ec:
                r0 = r4
                goto L_0x051a
            L_0x04ee:
                r0 = 65
                if (r3 < r0) goto L_0x04f6
                r0 = 90
                if (r3 <= r0) goto L_0x0514
            L_0x04f6:
                r0 = 97
                if (r3 < r0) goto L_0x04fe
                r0 = 122(0x7a, float:1.71E-43)
                if (r3 <= r0) goto L_0x0514
            L_0x04fe:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r4 = "Unquoted "
                java.lang.StringBuilder r0 = r0.append(r4)
                java.lang.StringBuilder r0 = r0.append(r3)
                java.lang.String r0 = r0.toString()
                com.ibm.icu.text.TransliteratorParser.syntaxError(r0, r10, r14)
            L_0x0514:
                r13.append(r3)
            L_0x0517:
                r0 = r15
                r5 = r21
            L_0x051a:
                r9 = r1
                r8 = r2
                r15 = r6
                r6 = r24
                r7 = r25
                goto L_0x0022
            L_0x0523:
                r21 = r5
                r24 = r6
                r25 = r7
                r2 = r8
                r1 = r9
            L_0x052b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TransliteratorParser.RuleHalf.parseSection(java.lang.String, int, int, com.ibm.icu.text.TransliteratorParser, java.lang.StringBuffer, com.ibm.icu.text.UnicodeSet, boolean):int");
        }

        /* access modifiers changed from: package-private */
        public void removeContext() {
            String str = this.text;
            int i = this.ante;
            if (i < 0) {
                i = 0;
            }
            int i2 = this.post;
            if (i2 < 0) {
                i2 = str.length();
            }
            this.text = str.substring(i, i2);
            this.post = -1;
            this.ante = -1;
            this.anchorEnd = false;
            this.anchorStart = false;
        }

        public boolean isValidOutput(TransliteratorParser parser) {
            int i = 0;
            while (i < this.text.length()) {
                int c = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(c);
                if (!parser.parseData.isReplacer(c)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isValidInput(TransliteratorParser parser) {
            int i = 0;
            while (i < this.text.length()) {
                int c = UTF16.charAt(this.text, i);
                i += UTF16.getCharCount(c);
                if (!parser.parseData.isMatcher(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void parse(String rules, int dir) {
        parseRules(new RuleArray(new String[]{rules}), dir);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01e6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0205 A[LOOP:3: B:112:0x01fd->B:114:0x0205, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0236 A[Catch:{ IllegalArgumentException -> 0x0284 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0255 A[Catch:{ IllegalArgumentException -> 0x0284 }, LOOP:4: B:131:0x024d->B:133:0x0255, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0291  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02c3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01b5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseRules(com.ibm.icu.text.TransliteratorParser.RuleBody r22, int r23) {
        /*
            r21 = this;
            r1 = r21
            r0 = 1
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.dataVector = r3
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.idBlockVector = r3
            r3 = 0
            r1.curData = r3
            r4 = r23
            r1.direction = r4
            r1.compoundFilter = r3
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r1.variablesVector = r5
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            r1.variableNames = r5
            com.ibm.icu.text.TransliteratorParser$ParseData r5 = new com.ibm.icu.text.TransliteratorParser$ParseData
            r5.<init>()
            r1.parseData = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 0
            r22.reset()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r1.compoundFilter = r3
            r8 = -1
        L_0x0041:
            java.lang.String r9 = r22.nextLine()
            r10 = 0
            r11 = 1
            if (r9 != 0) goto L_0x004d
            r3 = r2
            r2 = r0
            goto L_0x01c5
        L_0x004d:
            r12 = 0
            int r13 = r9.length()
            r20 = r2
            r2 = r0
            r0 = r20
        L_0x0057:
            if (r12 >= r13) goto L_0x02dd
            int r14 = r12 + 1
            char r12 = r9.charAt(r12)
            boolean r15 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r12)
            if (r15 == 0) goto L_0x0066
            goto L_0x007e
        L_0x0066:
            r15 = 35
            if (r12 != r15) goto L_0x0079
            java.lang.String r15 = "\n"
            int r15 = r9.indexOf(r15, r14)
            int r14 = r15 + 1
            if (r14 != 0) goto L_0x0077
            r10 = r3
            goto L_0x02de
        L_0x0077:
            r12 = r14
            goto L_0x0057
        L_0x0079:
            r15 = 59
            if (r12 != r15) goto L_0x0080
        L_0x007e:
            r12 = r14
            goto L_0x0057
        L_0x0080:
            int r16 = r0 + 1
            int r14 = r14 + -1
            int r0 = r14 + 2
            int r0 = r0 + r11
            if (r0 > r13) goto L_0x0161
            java.lang.String r0 = "::"
            r15 = 2
            boolean r0 = r9.regionMatches(r14, r0, r10, r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r0 == 0) goto L_0x0161
            int r14 = r14 + 2
            char r0 = r9.charAt(r14)     // Catch:{ IllegalArgumentException -> 0x015a }
            r12 = r0
        L_0x0099:
            boolean r0 = com.ibm.icu.impl.PatternProps.isWhiteSpace(r12)     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r0 == 0) goto L_0x00a9
            if (r14 >= r13) goto L_0x00a9
            int r14 = r14 + 1
            char r0 = r9.charAt(r14)     // Catch:{ IllegalArgumentException -> 0x015a }
            r12 = r0
            goto L_0x0099
        L_0x00a9:
            int[] r0 = new int[r11]     // Catch:{ IllegalArgumentException -> 0x015a }
            r0[r10] = r14     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r2 != 0) goto L_0x00c5
            com.ibm.icu.text.RuleBasedTransliterator$Data r15 = r1.curData     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r15 == 0) goto L_0x00c4
            int r11 = r1.direction     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r11 != 0) goto L_0x00bd
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r11 = r1.dataVector     // Catch:{ IllegalArgumentException -> 0x015a }
            r11.add(r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x00c2
        L_0x00bd:
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r11 = r1.dataVector     // Catch:{ IllegalArgumentException -> 0x015a }
            r11.add(r10, r15)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x00c2:
            r1.curData = r3     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x00c4:
            r2 = 1
        L_0x00c5:
            int r11 = r1.direction     // Catch:{ IllegalArgumentException -> 0x0153 }
            com.ibm.icu.text.TransliteratorIDParser$SingleID r11 = com.ibm.icu.text.TransliteratorIDParser.parseSingleID(r9, r0, r11)     // Catch:{ IllegalArgumentException -> 0x0153 }
            r15 = r0[r10]     // Catch:{ IllegalArgumentException -> 0x0153 }
            if (r15 == r14) goto L_0x0104
            r15 = 59
            boolean r18 = com.ibm.icu.impl.Utility.parseChar(r9, r0, r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r18 == 0) goto L_0x0104
            int r15 = r1.direction     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r15 != 0) goto L_0x00e9
            java.lang.String r15 = r11.canonID     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.StringBuilder r15 = r7.append(r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            r3 = 59
            r15.append(r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            r17 = r2
            goto L_0x0147
        L_0x00e9:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x015a }
            r3.<init>()     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.String r15 = r11.canonID     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.StringBuilder r3 = r3.append(r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            r15 = 59
            java.lang.StringBuilder r3 = r3.append(r15)     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x015a }
            r7.insert(r10, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            r17 = r2
            goto L_0x0147
        L_0x0104:
            r3 = 1
            int[] r15 = new int[r3]     // Catch:{ IllegalArgumentException -> 0x0153 }
            r3 = -1
            r15[r10] = r3     // Catch:{ IllegalArgumentException -> 0x0153 }
            r3 = r15
            int r15 = r1.direction     // Catch:{ IllegalArgumentException -> 0x0153 }
            r10 = 0
            com.ibm.icu.text.UnicodeSet r15 = com.ibm.icu.text.TransliteratorIDParser.parseGlobalFilter(r9, r0, r15, r3, r10)     // Catch:{ IllegalArgumentException -> 0x0153 }
            r10 = r15
            if (r10 == 0) goto L_0x0140
            r15 = 59
            boolean r15 = com.ibm.icu.impl.Utility.parseChar(r9, r0, r15)     // Catch:{ IllegalArgumentException -> 0x0153 }
            if (r15 == 0) goto L_0x0140
            int r15 = r1.direction     // Catch:{ IllegalArgumentException -> 0x0153 }
            if (r15 != 0) goto L_0x0123
            r15 = 1
            goto L_0x0124
        L_0x0123:
            r15 = 0
        L_0x0124:
            r17 = 0
            r19 = r3[r17]     // Catch:{ IllegalArgumentException -> 0x0153 }
            r17 = r2
            if (r19 != 0) goto L_0x012e
            r2 = 1
            goto L_0x012f
        L_0x012e:
            r2 = 0
        L_0x012f:
            if (r15 != r2) goto L_0x0147
            com.ibm.icu.text.UnicodeSet r2 = r1.compoundFilter     // Catch:{ IllegalArgumentException -> 0x014e }
            if (r2 == 0) goto L_0x013a
            java.lang.String r2 = "Multiple global filters"
            syntaxError(r2, r9, r14)     // Catch:{ IllegalArgumentException -> 0x014e }
        L_0x013a:
            r1.compoundFilter = r10     // Catch:{ IllegalArgumentException -> 0x014e }
            r2 = r16
            r8 = r2
            goto L_0x0147
        L_0x0140:
            r17 = r2
            java.lang.String r2 = "Invalid ::ID"
            syntaxError(r2, r9, r14)     // Catch:{ IllegalArgumentException -> 0x014e }
        L_0x0147:
            r2 = 0
            r3 = r0[r2]     // Catch:{ IllegalArgumentException -> 0x014e }
            r0 = r3
            r2 = r17
            goto L_0x01ab
        L_0x014e:
            r0 = move-exception
            r2 = r0
            r0 = r17
            goto L_0x01b1
        L_0x0153:
            r0 = move-exception
            r17 = r2
            r2 = r0
            r0 = r17
            goto L_0x01b1
        L_0x015a:
            r0 = move-exception
            r20 = r2
            r2 = r0
            r0 = r20
            goto L_0x01b1
        L_0x0161:
            if (r2 == 0) goto L_0x0194
            int r0 = r1.direction     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r0 != 0) goto L_0x0172
            java.util.List<java.lang.String> r0 = r1.idBlockVector     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.String r3 = r7.toString()     // Catch:{ IllegalArgumentException -> 0x015a }
            r0.add(r3)     // Catch:{ IllegalArgumentException -> 0x015a }
            r10 = 0
            goto L_0x017c
        L_0x0172:
            java.util.List<java.lang.String> r0 = r1.idBlockVector     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.String r3 = r7.toString()     // Catch:{ IllegalArgumentException -> 0x015a }
            r10 = 0
            r0.add(r10, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x017c:
            int r0 = r7.length()     // Catch:{ IllegalArgumentException -> 0x015a }
            r7.delete(r10, r0)     // Catch:{ IllegalArgumentException -> 0x015a }
            r2 = 0
            com.ibm.icu.text.RuleBasedTransliterator$Data r0 = new com.ibm.icu.text.RuleBasedTransliterator$Data     // Catch:{ IllegalArgumentException -> 0x015a }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x015a }
            r1.curData = r0     // Catch:{ IllegalArgumentException -> 0x015a }
            r0 = 61440(0xf000, float:8.6096E-41)
            r3 = 63743(0xf8ff, float:8.9323E-41)
            r1.setVariableRange(r0, r3)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x0194:
            boolean r0 = resemblesPragma(r9, r14, r13)     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r0 == 0) goto L_0x01a7
            int r0 = r1.parsePragma(r9, r14, r13)     // Catch:{ IllegalArgumentException -> 0x015a }
            if (r0 >= 0) goto L_0x01a5
            java.lang.String r3 = "Unrecognized pragma"
            syntaxError(r3, r9, r14)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x01a5:
            goto L_0x01ab
        L_0x01a7:
            int r0 = r1.parseRule(r9, r14, r13)     // Catch:{ IllegalArgumentException -> 0x015a }
        L_0x01ab:
            r12 = r0
            r10 = 0
            r11 = 0
            r15 = 1
            goto L_0x02d6
        L_0x01b1:
            r3 = 30
            if (r6 != r3) goto L_0x02c4
            com.ibm.icu.impl.IllegalIcuArgumentException r3 = new com.ibm.icu.impl.IllegalIcuArgumentException
            java.lang.String r10 = "\nMore than 30 errors; further messages squelched"
            r3.<init>(r10)
            r3.initCause(r2)
            r5.add(r3)
            r2 = r0
            r3 = r16
        L_0x01c5:
            if (r2 == 0) goto L_0x01e6
            int r0 = r7.length()
            if (r0 <= 0) goto L_0x01e6
            int r0 = r1.direction
            if (r0 != 0) goto L_0x01db
            java.util.List<java.lang.String> r0 = r1.idBlockVector
            java.lang.String r9 = r7.toString()
            r0.add(r9)
            goto L_0x01fc
        L_0x01db:
            java.util.List<java.lang.String> r0 = r1.idBlockVector
            java.lang.String r9 = r7.toString()
            r10 = 0
            r0.add(r10, r9)
            goto L_0x01fc
        L_0x01e6:
            if (r2 != 0) goto L_0x01fc
            com.ibm.icu.text.RuleBasedTransliterator$Data r0 = r1.curData
            if (r0 == 0) goto L_0x01fc
            int r9 = r1.direction
            if (r9 != 0) goto L_0x01f6
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector
            r9.add(r0)
            goto L_0x01fc
        L_0x01f6:
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector
            r10 = 0
            r9.add(r10, r0)
        L_0x01fc:
            r0 = 0
        L_0x01fd:
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector
            int r9 = r9.size()
            if (r0 >= r9) goto L_0x022f
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector
            java.lang.Object r9 = r9.get(r0)
            com.ibm.icu.text.RuleBasedTransliterator$Data r9 = (com.ibm.icu.text.RuleBasedTransliterator.Data) r9
            java.util.List<java.lang.Object> r10 = r1.variablesVector
            int r10 = r10.size()
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r9.variables = r10
            java.util.List<java.lang.Object> r10 = r1.variablesVector
            java.lang.Object[] r11 = r9.variables
            r10.toArray(r11)
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            r9.variableNames = r10
            java.util.Map<java.lang.String, char[]> r10 = r9.variableNames
            java.util.Map<java.lang.String, char[]> r11 = r1.variableNames
            r10.putAll(r11)
            int r0 = r0 + 1
            goto L_0x01fd
        L_0x022f:
            r10 = 0
            r1.variablesVector = r10
            com.ibm.icu.text.UnicodeSet r0 = r1.compoundFilter     // Catch:{ IllegalArgumentException -> 0x0284 }
            if (r0 == 0) goto L_0x024c
            int r0 = r1.direction     // Catch:{ IllegalArgumentException -> 0x0284 }
            if (r0 != 0) goto L_0x023e
            r9 = 1
            if (r8 != r9) goto L_0x0244
            goto L_0x023f
        L_0x023e:
            r9 = 1
        L_0x023f:
            if (r0 != r9) goto L_0x024c
            if (r8 != r3) goto L_0x0244
            goto L_0x024c
        L_0x0244:
            com.ibm.icu.impl.IllegalIcuArgumentException r0 = new com.ibm.icu.impl.IllegalIcuArgumentException     // Catch:{ IllegalArgumentException -> 0x0284 }
            java.lang.String r9 = "Compound filters misplaced"
            r0.<init>(r9)     // Catch:{ IllegalArgumentException -> 0x0284 }
            throw r0     // Catch:{ IllegalArgumentException -> 0x0284 }
        L_0x024c:
            r0 = 0
        L_0x024d:
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector     // Catch:{ IllegalArgumentException -> 0x0284 }
            int r9 = r9.size()     // Catch:{ IllegalArgumentException -> 0x0284 }
            if (r0 >= r9) goto L_0x0265
            java.util.List<com.ibm.icu.text.RuleBasedTransliterator$Data> r9 = r1.dataVector     // Catch:{ IllegalArgumentException -> 0x0284 }
            java.lang.Object r9 = r9.get(r0)     // Catch:{ IllegalArgumentException -> 0x0284 }
            com.ibm.icu.text.RuleBasedTransliterator$Data r9 = (com.ibm.icu.text.RuleBasedTransliterator.Data) r9     // Catch:{ IllegalArgumentException -> 0x0284 }
            com.ibm.icu.text.TransliterationRuleSet r10 = r9.ruleSet     // Catch:{ IllegalArgumentException -> 0x0284 }
            r10.freeze()     // Catch:{ IllegalArgumentException -> 0x0284 }
            int r0 = r0 + 1
            goto L_0x024d
        L_0x0265:
            java.util.List<java.lang.String> r0 = r1.idBlockVector     // Catch:{ IllegalArgumentException -> 0x0284 }
            int r0 = r0.size()     // Catch:{ IllegalArgumentException -> 0x0284 }
            r9 = 1
            if (r0 != r9) goto L_0x0283
            java.util.List<java.lang.String> r0 = r1.idBlockVector     // Catch:{ IllegalArgumentException -> 0x0284 }
            r9 = 0
            java.lang.Object r0 = r0.get(r9)     // Catch:{ IllegalArgumentException -> 0x0284 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ IllegalArgumentException -> 0x0284 }
            int r0 = r0.length()     // Catch:{ IllegalArgumentException -> 0x0284 }
            if (r0 != 0) goto L_0x0283
            java.util.List<java.lang.String> r0 = r1.idBlockVector     // Catch:{ IllegalArgumentException -> 0x0284 }
            r9 = 0
            r0.remove(r9)     // Catch:{ IllegalArgumentException -> 0x0284 }
        L_0x0283:
            goto L_0x028b
        L_0x0284:
            r0 = move-exception
            r0.fillInStackTrace()
            r5.add(r0)
        L_0x028b:
            int r0 = r5.size()
            if (r0 == 0) goto L_0x02c3
            int r0 = r5.size()
            r9 = 1
            int r0 = r0 - r9
        L_0x0297:
            if (r0 <= 0) goto L_0x02bb
            int r9 = r0 + -1
            java.lang.Object r9 = r5.get(r9)
            java.lang.RuntimeException r9 = (java.lang.RuntimeException) r9
        L_0x02a1:
            java.lang.Throwable r10 = r9.getCause()
            if (r10 == 0) goto L_0x02af
            java.lang.Throwable r10 = r9.getCause()
            r9 = r10
            java.lang.RuntimeException r9 = (java.lang.RuntimeException) r9
            goto L_0x02a1
        L_0x02af:
            java.lang.Object r10 = r5.get(r0)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            r9.initCause(r10)
            int r0 = r0 + -1
            goto L_0x0297
        L_0x02bb:
            r11 = 0
            java.lang.Object r0 = r5.get(r11)
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0
            throw r0
        L_0x02c3:
            return
        L_0x02c4:
            r10 = 0
            r11 = 0
            r2.fillInStackTrace()
            r5.add(r2)
            int r6 = r6 + 1
            int r3 = ruleEnd(r9, r14, r13)
            r15 = 1
            int r3 = r3 + r15
            r2 = r0
            r12 = r3
        L_0x02d6:
            r3 = r10
            r10 = r11
            r11 = r15
            r0 = r16
            goto L_0x0057
        L_0x02dd:
            r10 = r3
        L_0x02de:
            r3 = r10
            r20 = r2
            r2 = r0
            r0 = r20
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TransliteratorParser.parseRules(com.ibm.icu.text.TransliteratorParser$RuleBody, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0039, code lost:
        if (OPERATORS.indexOf(r8) < 0) goto L_0x003b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseRule(java.lang.String r23, int r24, int r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r25
            r3 = r24
            r4 = 0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            r0.segmentStandins = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0.segmentObjects = r5
            com.ibm.icu.text.TransliteratorParser$RuleHalf r5 = new com.ibm.icu.text.TransliteratorParser$RuleHalf
            r6 = 0
            r5.<init>()
            com.ibm.icu.text.TransliteratorParser$RuleHalf r7 = new com.ibm.icu.text.TransliteratorParser$RuleHalf
            r7.<init>()
            r0.undefinedVariableName = r6
            r6 = r24
            int r6 = r5.parse(r1, r6, r2, r0)
            if (r6 == r2) goto L_0x003b
            int r6 = r6 + -1
            char r8 = r1.charAt(r6)
            r4 = r8
            java.lang.String r9 = "=><←→↔"
            int r8 = r9.indexOf(r8)
            if (r8 >= 0) goto L_0x0051
        L_0x003b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "No operator pos="
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r8 = r8.toString()
            syntaxError(r8, r1, r3)
        L_0x0051:
            r8 = 1
            int r6 = r6 + r8
            r9 = 60
            r10 = 62
            if (r4 != r9) goto L_0x0065
            if (r6 >= r2) goto L_0x0065
            char r9 = r1.charAt(r6)
            if (r9 != r10) goto L_0x0065
            int r6 = r6 + 1
            r4 = 126(0x7e, float:1.77E-43)
        L_0x0065:
            switch(r4) {
                case 8592: goto L_0x006f;
                case 8593: goto L_0x0068;
                case 8594: goto L_0x006c;
                case 8595: goto L_0x0068;
                case 8596: goto L_0x0069;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x0072
        L_0x0069:
            r4 = 126(0x7e, float:1.77E-43)
            goto L_0x0072
        L_0x006c:
            r4 = 62
            goto L_0x0072
        L_0x006f:
            r4 = 60
        L_0x0072:
            int r6 = r7.parse(r1, r6, r2, r0)
            if (r6 >= r2) goto L_0x008a
            int r6 = r6 + -1
            char r9 = r1.charAt(r6)
            r11 = 59
            if (r9 != r11) goto L_0x0085
            int r6 = r6 + 1
            goto L_0x008a
        L_0x0085:
            java.lang.String r9 = "Unquoted operator"
            syntaxError(r9, r1, r3)
        L_0x008a:
            r9 = 61
            r11 = 0
            if (r4 != r9) goto L_0x00df
            java.lang.String r9 = r0.undefinedVariableName
            if (r9 != 0) goto L_0x0098
            java.lang.String r9 = "Missing '$' or duplicate definition"
            syntaxError(r9, r1, r3)
        L_0x0098:
            java.lang.String r9 = r5.text
            int r9 = r9.length()
            if (r9 != r8) goto L_0x00aa
            java.lang.String r9 = r5.text
            char r9 = r9.charAt(r11)
            char r10 = r0.variableLimit
            if (r9 == r10) goto L_0x00af
        L_0x00aa:
            java.lang.String r9 = "Malformed LHS"
            syntaxError(r9, r1, r3)
        L_0x00af:
            boolean r9 = r5.anchorStart
            if (r9 != 0) goto L_0x00bf
            boolean r9 = r5.anchorEnd
            if (r9 != 0) goto L_0x00bf
            boolean r9 = r7.anchorStart
            if (r9 != 0) goto L_0x00bf
            boolean r9 = r7.anchorEnd
            if (r9 == 0) goto L_0x00c4
        L_0x00bf:
            java.lang.String r9 = "Malformed variable def"
            syntaxError(r9, r1, r3)
        L_0x00c4:
            java.lang.String r9 = r7.text
            int r9 = r9.length()
            char[] r10 = new char[r9]
            java.lang.String r12 = r7.text
            r12.getChars(r11, r9, r10, r11)
            java.util.Map<java.lang.String, char[]> r11 = r0.variableNames
            java.lang.String r12 = r0.undefinedVariableName
            r11.put(r12, r10)
            char r11 = r0.variableLimit
            int r11 = r11 + r8
            char r8 = (char) r11
            r0.variableLimit = r8
            return r6
        L_0x00df:
            java.lang.String r9 = r0.undefinedVariableName
            if (r9 == 0) goto L_0x00fb
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "Undefined variable $"
            java.lang.StringBuilder r9 = r9.append(r12)
            java.lang.String r12 = r0.undefinedVariableName
            java.lang.StringBuilder r9 = r9.append(r12)
            java.lang.String r9 = r9.toString()
            syntaxError(r9, r1, r3)
        L_0x00fb:
            java.lang.StringBuffer r9 = r0.segmentStandins
            int r9 = r9.length()
            java.util.List<com.ibm.icu.text.StringMatcher> r12 = r0.segmentObjects
            int r12 = r12.size()
            if (r9 <= r12) goto L_0x010e
            java.lang.String r9 = "Undefined segment reference"
            syntaxError(r9, r1, r3)
        L_0x010e:
            r9 = 0
        L_0x010f:
            java.lang.StringBuffer r12 = r0.segmentStandins
            int r12 = r12.length()
            java.lang.String r13 = "Internal error"
            if (r9 >= r12) goto L_0x0127
            java.lang.StringBuffer r12 = r0.segmentStandins
            char r12 = r12.charAt(r9)
            if (r12 != 0) goto L_0x0124
            syntaxError(r13, r1, r3)
        L_0x0124:
            int r9 = r9 + 1
            goto L_0x010f
        L_0x0127:
            r9 = 0
        L_0x0128:
            java.util.List<com.ibm.icu.text.StringMatcher> r12 = r0.segmentObjects
            int r12 = r12.size()
            if (r9 >= r12) goto L_0x013e
            java.util.List<com.ibm.icu.text.StringMatcher> r12 = r0.segmentObjects
            java.lang.Object r12 = r12.get(r9)
            if (r12 != 0) goto L_0x013b
            syntaxError(r13, r1, r3)
        L_0x013b:
            int r9 = r9 + 1
            goto L_0x0128
        L_0x013e:
            r9 = 126(0x7e, float:1.77E-43)
            if (r4 == r9) goto L_0x0151
            int r12 = r0.direction
            if (r12 != 0) goto L_0x0148
            r12 = r8
            goto L_0x0149
        L_0x0148:
            r12 = r11
        L_0x0149:
            if (r4 != r10) goto L_0x014d
            r10 = r8
            goto L_0x014e
        L_0x014d:
            r10 = r11
        L_0x014e:
            if (r12 == r10) goto L_0x0151
            return r6
        L_0x0151:
            int r10 = r0.direction
            if (r10 != r8) goto L_0x0158
            r8 = r5
            r5 = r7
            r7 = r8
        L_0x0158:
            if (r4 != r9) goto L_0x0162
            r7.removeContext()
            r8 = -1
            r5.cursor = r8
            r5.cursorOffset = r11
        L_0x0162:
            int r8 = r5.ante
            if (r8 >= 0) goto L_0x0168
            r5.ante = r11
        L_0x0168:
            int r8 = r5.post
            if (r8 >= 0) goto L_0x0174
            java.lang.String r8 = r5.text
            int r8 = r8.length()
            r5.post = r8
        L_0x0174:
            int r8 = r7.ante
            if (r8 >= 0) goto L_0x01a2
            int r8 = r7.post
            if (r8 >= 0) goto L_0x01a2
            int r8 = r5.cursor
            if (r8 >= 0) goto L_0x01a2
            int r8 = r7.cursorOffset
            if (r8 == 0) goto L_0x0188
            int r8 = r7.cursor
            if (r8 < 0) goto L_0x01a2
        L_0x0188:
            boolean r8 = r7.anchorStart
            if (r8 != 0) goto L_0x01a2
            boolean r8 = r7.anchorEnd
            if (r8 != 0) goto L_0x01a2
            boolean r8 = r5.isValidInput(r0)
            if (r8 == 0) goto L_0x01a2
            boolean r8 = r7.isValidOutput(r0)
            if (r8 == 0) goto L_0x01a2
            int r8 = r5.ante
            int r9 = r5.post
            if (r8 <= r9) goto L_0x01a7
        L_0x01a2:
            java.lang.String r8 = "Malformed rule"
            syntaxError(r8, r1, r3)
        L_0x01a7:
            r8 = 0
            java.util.List<com.ibm.icu.text.StringMatcher> r9 = r0.segmentObjects
            int r9 = r9.size()
            if (r9 <= 0) goto L_0x01bd
            java.util.List<com.ibm.icu.text.StringMatcher> r9 = r0.segmentObjects
            int r9 = r9.size()
            com.ibm.icu.text.UnicodeMatcher[] r8 = new com.ibm.icu.text.UnicodeMatcher[r9]
            java.util.List<com.ibm.icu.text.StringMatcher> r9 = r0.segmentObjects
            r9.toArray(r8)
        L_0x01bd:
            com.ibm.icu.text.RuleBasedTransliterator$Data r9 = r0.curData
            com.ibm.icu.text.TransliterationRuleSet r9 = r9.ruleSet
            com.ibm.icu.text.TransliterationRule r15 = new com.ibm.icu.text.TransliterationRule
            java.lang.String r11 = r5.text
            int r12 = r5.ante
            int r13 = r5.post
            java.lang.String r14 = r7.text
            int r10 = r7.cursor
            int r1 = r7.cursorOffset
            boolean r2 = r5.anchorStart
            r21 = r3
            boolean r3 = r5.anchorEnd
            r24 = r4
            com.ibm.icu.text.RuleBasedTransliterator$Data r4 = r0.curData
            r16 = r10
            r10 = r15
            r0 = r15
            r15 = r16
            r16 = r1
            r17 = r8
            r18 = r2
            r19 = r3
            r20 = r4
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r9.addRule(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.TransliteratorParser.parseRule(java.lang.String, int, int):int");
    }

    private void setVariableRange(int start, int end) {
        if (start > end || start < 0 || end > 65535) {
            throw new IllegalIcuArgumentException("Invalid variable range " + start + ", " + end);
        }
        this.curData.variablesBase = (char) start;
        if (this.dataVector.size() == 0) {
            this.variableNext = (char) start;
            this.variableLimit = (char) (end + 1);
        }
    }

    /* access modifiers changed from: private */
    public void checkVariableRange(int ch, String rule, int start) {
        if (ch >= this.curData.variablesBase && ch < this.variableLimit) {
            syntaxError("Variable range character in rule", rule, start);
        }
    }

    private void pragmaMaximumBackup(int backup) {
        throw new IllegalIcuArgumentException("use maximum backup pragma not implemented yet");
    }

    private void pragmaNormalizeRules(Normalizer.Mode mode) {
        throw new IllegalIcuArgumentException("use normalize rules pragma not implemented yet");
    }

    static boolean resemblesPragma(String rule, int pos, int limit) {
        return Utility.parsePattern(rule, pos, limit, "use ", (int[]) null) >= 0;
    }

    private int parsePragma(String rule, int pos, int limit) {
        int[] array = new int[2];
        int pos2 = pos + 4;
        int p = Utility.parsePattern(rule, pos2, limit, "~variable range # #~;", array);
        if (p >= 0) {
            setVariableRange(array[0], array[1]);
            return p;
        }
        int p2 = Utility.parsePattern(rule, pos2, limit, "~maximum backup #~;", array);
        if (p2 >= 0) {
            pragmaMaximumBackup(array[0]);
            return p2;
        }
        int p3 = Utility.parsePattern(rule, pos2, limit, "~nfd rules~;", (int[]) null);
        if (p3 >= 0) {
            pragmaNormalizeRules(Normalizer.NFD);
            return p3;
        }
        int p4 = Utility.parsePattern(rule, pos2, limit, "~nfc rules~;", (int[]) null);
        if (p4 < 0) {
            return -1;
        }
        pragmaNormalizeRules(Normalizer.NFC);
        return p4;
    }

    static final void syntaxError(String msg, String rule, int start) {
        throw new IllegalIcuArgumentException(msg + " in \"" + Utility.escape(rule.substring(start, ruleEnd(rule, start, rule.length()))) + '\"');
    }

    static final int ruleEnd(String rule, int start, int limit) {
        int end = Utility.quotedIndexOf(rule, start, limit, ";");
        if (end < 0) {
            return limit;
        }
        return end;
    }

    /* access modifiers changed from: private */
    public final char parseSet(String rule, ParsePosition pos) {
        UnicodeSet set = new UnicodeSet(rule, pos, this.parseData);
        if (this.variableNext < this.variableLimit) {
            set.compact();
            return generateStandInFor(set);
        }
        throw new RuntimeException("Private use variables exhausted");
    }

    /* access modifiers changed from: package-private */
    public char generateStandInFor(Object obj) {
        for (int i = 0; i < this.variablesVector.size(); i++) {
            if (this.variablesVector.get(i) == obj) {
                return (char) (this.curData.variablesBase + i);
            }
        }
        if (this.variableNext < this.variableLimit) {
            this.variablesVector.add(obj);
            char c = this.variableNext;
            this.variableNext = (char) (c + 1);
            return c;
        }
        throw new RuntimeException("Variable range exhausted");
    }

    public char getSegmentStandin(int seg) {
        if (this.segmentStandins.length() < seg) {
            this.segmentStandins.setLength(seg);
        }
        char c = this.segmentStandins.charAt(seg - 1);
        if (c != 0) {
            return c;
        }
        char c2 = this.variableNext;
        if (c2 < this.variableLimit) {
            this.variableNext = (char) (c2 + 1);
            char c3 = c2;
            this.variablesVector.add((Object) null);
            this.segmentStandins.setCharAt(seg - 1, c3);
            return c3;
        }
        throw new RuntimeException("Variable range exhausted");
    }

    public void setSegmentObject(int seg, StringMatcher obj) {
        while (this.segmentObjects.size() < seg) {
            this.segmentObjects.add((Object) null);
        }
        int index = getSegmentStandin(seg) - this.curData.variablesBase;
        if (this.segmentObjects.get(seg - 1) == null && this.variablesVector.get(index) == null) {
            this.segmentObjects.set(seg - 1, obj);
            this.variablesVector.set(index, obj);
            return;
        }
        throw new RuntimeException();
    }

    /* access modifiers changed from: package-private */
    public char getDotStandIn() {
        if (this.dotStandIn == -1) {
            this.dotStandIn = generateStandInFor(new UnicodeSet(DOT_SET));
        }
        return (char) this.dotStandIn;
    }

    /* access modifiers changed from: private */
    public void appendVariableDef(String name, StringBuffer buf) {
        char[] ch = this.variableNames.get(name);
        if (ch != null) {
            buf.append(ch);
        } else if (this.undefinedVariableName == null) {
            this.undefinedVariableName = name;
            char c = this.variableNext;
            char c2 = this.variableLimit;
            if (c < c2) {
                char c3 = (char) (c2 - 1);
                this.variableLimit = c3;
                buf.append(c3);
                return;
            }
            throw new RuntimeException("Private use variables exhausted");
        } else {
            throw new IllegalIcuArgumentException("Undefined variable $" + name);
        }
    }
}
