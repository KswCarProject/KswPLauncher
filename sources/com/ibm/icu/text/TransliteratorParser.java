package com.ibm.icu.text;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Normalizer;
import com.ibm.icu.text.RuleBasedTransliterator;
import com.ibm.icu.text.TransliteratorIDParser;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes.dex */
class TransliteratorParser {
    private static final char ALT_FORWARD_RULE_OP = '\u2192';
    private static final char ALT_FUNCTION = '\u2206';
    private static final char ALT_FWDREV_RULE_OP = '\u2194';
    private static final char ALT_REVERSE_RULE_OP = '\u2190';
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
    private static final String HALF_ENDERS = "=><\u2190\u2192\u2194;";
    private static final String ID_TOKEN = "::";
    private static final int ID_TOKEN_LEN = 2;
    private static final char KLEENE_STAR = '*';
    private static final char ONE_OR_MORE = '+';
    private static final String OPERATORS = "=><\u2190\u2192\u2194";
    private static final char QUOTE = '\'';
    private static final char REVERSE_RULE_OP = '<';
    private static final char RULE_COMMENT_CHAR = '#';
    private static final char SEGMENT_CLOSE = ')';
    private static final char SEGMENT_OPEN = '(';
    private static final char VARIABLE_DEF_OP = '=';
    private static final char ZERO_OR_ONE = '?';
    public UnicodeSet compoundFilter;
    private RuleBasedTransliterator.Data curData;
    public List<RuleBasedTransliterator.Data> dataVector;
    private int direction;
    private int dotStandIn = -1;
    public List<String> idBlockVector;
    private ParseData parseData;
    private List<StringMatcher> segmentObjects;
    private StringBuffer segmentStandins;
    private String undefinedVariableName;
    private char variableLimit;
    private Map<String, char[]> variableNames;
    private char variableNext;
    private List<Object> variablesVector;
    private static UnicodeSet ILLEGAL_TOP = new UnicodeSet("[\\)]");
    private static UnicodeSet ILLEGAL_SEG = new UnicodeSet("[\\{\\}\\|\\@]");
    private static UnicodeSet ILLEGAL_FUNC = new UnicodeSet("[\\^\\(\\.\\*\\+\\?\\{\\}\\|\\@]");

    /* loaded from: classes.dex */
    private class ParseData implements SymbolTable {
        private ParseData() {
        }

        @Override // com.ibm.icu.text.SymbolTable
        public char[] lookup(String name) {
            return (char[]) TransliteratorParser.this.variableNames.get(name);
        }

        @Override // com.ibm.icu.text.SymbolTable
        public UnicodeMatcher lookupMatcher(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i >= 0 && i < TransliteratorParser.this.variablesVector.size()) {
                return (UnicodeMatcher) TransliteratorParser.this.variablesVector.get(i);
            }
            return null;
        }

        @Override // com.ibm.icu.text.SymbolTable
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
            if (i >= 0 && i < TransliteratorParser.this.variablesVector.size()) {
                return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeMatcher;
            }
            return true;
        }

        public boolean isReplacer(int ch) {
            int i = ch - TransliteratorParser.this.curData.variablesBase;
            if (i >= 0 && i < TransliteratorParser.this.variablesVector.size()) {
                return TransliteratorParser.this.variablesVector.get(i) instanceof UnicodeReplacer;
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    private static abstract class RuleBody {
        abstract String handleNextLine();

        abstract void reset();

        private RuleBody() {
        }

        String nextLine() {
            String s;
            String s2 = handleNextLine();
            if (s2 != null && s2.length() > 0 && s2.charAt(s2.length() - 1) == '\\') {
                StringBuilder b = new StringBuilder(s2);
                do {
                    b.deleteCharAt(b.length() - 1);
                    s = handleNextLine();
                    if (s != null) {
                        b.append(s);
                        if (s.length() <= 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                } while (s.charAt(s.length() - 1) == '\\');
                return b.toString();
            }
            return s2;
        }
    }

    /* loaded from: classes.dex */
    private static class RuleArray extends RuleBody {
        String[] array;

        /* renamed from: i */
        int f168i;

        public RuleArray(String[] array) {
            super();
            this.array = array;
            this.f168i = 0;
        }

        @Override // com.ibm.icu.text.TransliteratorParser.RuleBody
        public String handleNextLine() {
            int i = this.f168i;
            String[] strArr = this.array;
            if (i < strArr.length) {
                this.f168i = i + 1;
                return strArr[i];
            }
            return null;
        }

        @Override // com.ibm.icu.text.TransliteratorParser.RuleBody
        public void reset() {
            this.f168i = 0;
        }
    }

    /* loaded from: classes.dex */
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
            StringBuffer buf = new StringBuffer();
            int pos2 = parseSection(rule, pos, limit, parser, buf, TransliteratorParser.ILLEGAL_TOP, false);
            this.text = buf.toString();
            if (this.cursorOffset > 0 && this.cursor != this.cursorOffsetPos) {
                TransliteratorParser.syntaxError("Misplaced |", rule, pos);
            }
            return pos2;
        }

        private int parseSection(String rule, int pos, int limit, TransliteratorParser parser, StringBuffer buf, UnicodeSet illegal, boolean isSegment) {
            int i;
            int[] iref;
            ParsePosition pp;
            int pos2;
            int varLimit;
            int quoteLimit;
            int bufStart;
            RuleHalf ruleHalf;
            int[] iref2;
            ParsePosition pp2;
            boolean z;
            int pos3;
            int qlimit;
            int qstart;
            int min;
            int qlimit2;
            RuleHalf ruleHalf2 = this;
            boolean z2 = true;
            int[] iref3 = new int[1];
            int bufStart2 = buf.length();
            ParsePosition pp3 = null;
            int quoteStart = -1;
            int quoteLimit2 = -1;
            int varStart = -1;
            int varLimit2 = -1;
            int pos4 = pos;
            while (pos4 < limit) {
                int pos5 = pos4 + 1;
                char c = rule.charAt(pos4);
                if (PatternProps.isWhiteSpace(c)) {
                    pos4 = pos5;
                } else {
                    if (TransliteratorParser.HALF_ENDERS.indexOf(c) >= 0) {
                        if (!isSegment) {
                            i = varLimit2;
                            iref = iref3;
                        } else {
                            TransliteratorParser.syntaxError("Unclosed segment", rule, pos);
                            i = varLimit2;
                            iref = iref3;
                        }
                    } else {
                        if (ruleHalf2.anchorEnd) {
                            TransliteratorParser.syntaxError("Malformed variable reference", rule, pos);
                        }
                        if (UnicodeSet.resemblesPattern(rule, pos5 - 1)) {
                            if (pp3 != null) {
                                pp = pp3;
                            } else {
                                pp = new ParsePosition(0);
                            }
                            pp.setIndex(pos5 - 1);
                            buf.append(parser.parseSet(rule, pp));
                            pp3 = pp;
                            pos4 = pp.getIndex();
                        } else if (c == '\\') {
                            if (pos5 == limit) {
                                TransliteratorParser.syntaxError("Trailing backslash", rule, pos);
                            }
                            iref3[0] = pos5;
                            int escaped = Utility.unescapeAt(rule, iref3);
                            int pos6 = iref3[0];
                            if (escaped == -1) {
                                TransliteratorParser.syntaxError("Malformed escape", rule, pos);
                            }
                            parser.checkVariableRange(escaped, rule, pos);
                            UTF16.append(buf, escaped);
                            pos4 = pos6;
                        } else if (c != '\'') {
                            parser.checkVariableRange(c, rule, pos);
                            iref = iref3;
                            if (illegal.contains(c)) {
                                TransliteratorParser.syntaxError("Illegal character '" + c + TransliteratorParser.QUOTE, rule, pos);
                            }
                            switch (c) {
                                case '$':
                                    pos2 = pos5;
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    if (pos2 == limit) {
                                        ruleHalf = this;
                                        iref2 = iref;
                                        ruleHalf.anchorEnd = true;
                                        z = true;
                                        pos4 = pos2;
                                        varLimit2 = varLimit;
                                        break;
                                    } else {
                                        ruleHalf = this;
                                        iref2 = iref;
                                        int r = UCharacter.digit(rule.charAt(pos2), 10);
                                        if (r >= 1 && r <= 9) {
                                            iref2[0] = pos2;
                                            int r2 = Utility.parseNumber(rule, iref2, 10);
                                            if (r2 < 0) {
                                                TransliteratorParser.syntaxError("Undefined segment reference", rule, pos);
                                            }
                                            pos3 = iref2[0];
                                            buf.append(parser.getSegmentStandin(r2));
                                            varLimit2 = varLimit;
                                            z = true;
                                        } else {
                                            if (pp3 != null) {
                                                pp2 = pp3;
                                            } else {
                                                pp2 = new ParsePosition(0);
                                            }
                                            pp2.setIndex(pos2);
                                            String name = parser.parseData.parseReference(rule, pp2, limit);
                                            if (name == null) {
                                                z = true;
                                                ruleHalf.anchorEnd = true;
                                                pp3 = pp2;
                                                pos4 = pos2;
                                                varLimit2 = varLimit;
                                                break;
                                            } else {
                                                z = true;
                                                int pos7 = pp2.getIndex();
                                                varStart = buf.length();
                                                parser.appendVariableDef(name, buf);
                                                pp3 = pp2;
                                                pos3 = pos7;
                                                varLimit2 = buf.length();
                                            }
                                        }
                                        pos4 = pos3;
                                        break;
                                    }
                                    break;
                                case '&':
                                case '\u2206':
                                    int varLimit3 = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    iref[0] = pos5;
                                    TransliteratorIDParser.SingleID single = TransliteratorIDParser.parseFilterID(rule, iref);
                                    if (single == null || !Utility.parseChar(rule, iref, (char) TransliteratorParser.SEGMENT_OPEN)) {
                                        TransliteratorParser.syntaxError("Invalid function", rule, pos);
                                    }
                                    Transliterator t = single.getInstance();
                                    if (t == null) {
                                        TransliteratorParser.syntaxError("Invalid function ID", rule, pos);
                                    }
                                    int bufSegStart = buf.length();
                                    int pos8 = parseSection(rule, iref[0], limit, parser, buf, TransliteratorParser.ILLEGAL_FUNC, true);
                                    FunctionReplacer r3 = new FunctionReplacer(t, new StringReplacer(buf.substring(bufSegStart), parser.curData));
                                    buf.setLength(bufSegStart);
                                    buf.append(parser.generateStandInFor(r3));
                                    z = true;
                                    pos4 = pos8;
                                    iref2 = iref;
                                    varLimit2 = varLimit3;
                                    ruleHalf = this;
                                    break;
                                case '(':
                                    int bufSegStart2 = buf.length();
                                    int segmentNumber = ruleHalf2.nextSegmentNumber;
                                    ruleHalf2.nextSegmentNumber = segmentNumber + 1;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    int pos9 = parseSection(rule, pos5, limit, parser, buf, TransliteratorParser.ILLEGAL_SEG, true);
                                    StringMatcher m = new StringMatcher(buf.substring(bufSegStart2), segmentNumber, parser.curData);
                                    parser.setSegmentObject(segmentNumber, m);
                                    buf.setLength(bufSegStart2);
                                    buf.append(parser.getSegmentStandin(segmentNumber));
                                    z = true;
                                    pos4 = pos9;
                                    iref2 = iref;
                                    varLimit2 = varLimit2;
                                    ruleHalf = this;
                                    break;
                                case ')':
                                    i = varLimit2;
                                    break;
                                case '*':
                                case '+':
                                case '?':
                                    if (isSegment && buf.length() == bufStart2) {
                                        TransliteratorParser.syntaxError("Misplaced quantifier", rule, pos);
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else {
                                        if (buf.length() == quoteLimit2) {
                                            int qstart2 = quoteStart;
                                            qlimit = quoteLimit2;
                                            qstart = qstart2;
                                        } else {
                                            int qstart3 = buf.length();
                                            if (qstart3 == varLimit2) {
                                                int qstart4 = varStart;
                                                qlimit = varLimit2;
                                                qstart = qstart4;
                                            } else {
                                                int qstart5 = buf.length();
                                                int qstart6 = qstart5 - (z2 ? 1 : 0);
                                                qlimit = qstart6 + 1;
                                                qstart = qstart6;
                                            }
                                        }
                                        try {
                                            UnicodeMatcher m2 = new StringMatcher(buf.toString(), qstart, qlimit, 0, parser.curData);
                                            switch (c) {
                                                case '+':
                                                    min = 1;
                                                    qlimit2 = Integer.MAX_VALUE;
                                                    break;
                                                case '?':
                                                    min = 0;
                                                    qlimit2 = 1;
                                                    break;
                                                default:
                                                    min = 0;
                                                    qlimit2 = Integer.MAX_VALUE;
                                                    break;
                                            }
                                            UnicodeMatcher m3 = new Quantifier(m2, min, qlimit2);
                                            buf.setLength(qstart);
                                            buf.append(parser.generateStandInFor(m3));
                                            pos2 = pos5;
                                            varLimit = varLimit2;
                                            quoteLimit = quoteLimit2;
                                            bufStart = bufStart2;
                                            ruleHalf = ruleHalf2;
                                            iref2 = iref;
                                            z = true;
                                        } catch (RuntimeException e) {
                                            String precontext = pos5 < 50 ? rule.substring(0, pos5) : "..." + rule.substring(pos5 - 50, pos5);
                                            String postContext = limit - pos5 <= 50 ? rule.substring(pos5, limit) : rule.substring(pos5, pos5 + 50) + "...";
                                            throw new IllegalIcuArgumentException("Failure in rule: " + precontext + "$$$" + postContext).initCause(e);
                                        }
                                    }
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '.':
                                    buf.append(parser.getDotStandIn());
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    ruleHalf = ruleHalf2;
                                    z = z2 ? 1 : 0;
                                    iref2 = iref;
                                    pos2 = pos5;
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '@':
                                    int i2 = ruleHalf2.cursorOffset;
                                    if (i2 < 0) {
                                        if (buf.length() > 0) {
                                            TransliteratorParser.syntaxError("Misplaced " + c, rule, pos);
                                        }
                                        ruleHalf2.cursorOffset -= z2 ? 1 : 0;
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else if (i2 > 0) {
                                        if (buf.length() != ruleHalf2.cursorOffsetPos || ruleHalf2.cursor >= 0) {
                                            TransliteratorParser.syntaxError("Misplaced " + c, rule, pos);
                                        }
                                        ruleHalf2.cursorOffset += z2 ? 1 : 0;
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else if (ruleHalf2.cursor == 0 && buf.length() == 0) {
                                        ruleHalf2.cursorOffset = -1;
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else if (ruleHalf2.cursor < 0) {
                                        ruleHalf2.cursorOffsetPos = buf.length();
                                        ruleHalf2.cursorOffset = z2 ? 1 : 0;
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else {
                                        TransliteratorParser.syntaxError("Misplaced " + c, rule, pos);
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    }
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '^':
                                    if (buf.length() == 0 && !ruleHalf2.anchorStart) {
                                        ruleHalf2.anchorStart = z2;
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    } else {
                                        TransliteratorParser.syntaxError("Misplaced anchor start", rule, pos);
                                        varLimit = varLimit2;
                                        quoteLimit = quoteLimit2;
                                        bufStart = bufStart2;
                                        ruleHalf = ruleHalf2;
                                        z = z2 ? 1 : 0;
                                        iref2 = iref;
                                        pos2 = pos5;
                                    }
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '{':
                                    if (ruleHalf2.ante >= 0) {
                                        TransliteratorParser.syntaxError("Multiple ante contexts", rule, pos);
                                    }
                                    ruleHalf2.ante = buf.length();
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    ruleHalf = ruleHalf2;
                                    z = z2 ? 1 : 0;
                                    iref2 = iref;
                                    pos2 = pos5;
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '|':
                                    if (ruleHalf2.cursor >= 0) {
                                        TransliteratorParser.syntaxError("Multiple cursors", rule, pos);
                                    }
                                    ruleHalf2.cursor = buf.length();
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    ruleHalf = ruleHalf2;
                                    z = z2 ? 1 : 0;
                                    iref2 = iref;
                                    pos2 = pos5;
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                case '}':
                                    if (ruleHalf2.post >= 0) {
                                        TransliteratorParser.syntaxError("Multiple post contexts", rule, pos);
                                    }
                                    ruleHalf2.post = buf.length();
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    ruleHalf = ruleHalf2;
                                    z = z2 ? 1 : 0;
                                    iref2 = iref;
                                    pos2 = pos5;
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                                default:
                                    varLimit = varLimit2;
                                    quoteLimit = quoteLimit2;
                                    bufStart = bufStart2;
                                    ruleHalf = ruleHalf2;
                                    z = z2 ? 1 : 0;
                                    iref2 = iref;
                                    pos2 = pos5;
                                    if (c >= '!' && c <= '~' && ((c < '0' || c > '9') && ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')))) {
                                        TransliteratorParser.syntaxError("Unquoted " + c, rule, pos);
                                    }
                                    buf.append(c);
                                    pos4 = pos2;
                                    varLimit2 = varLimit;
                                    break;
                            }
                            ruleHalf2 = ruleHalf;
                            iref3 = iref2;
                            z2 = z;
                            quoteLimit2 = quoteLimit;
                            bufStart2 = bufStart;
                        } else {
                            int iq = rule.indexOf(39, pos5);
                            if (iq == pos5) {
                                buf.append(c);
                                pos4 = pos5 + 1;
                            } else {
                                quoteStart = buf.length();
                                while (true) {
                                    if (iq < 0) {
                                        TransliteratorParser.syntaxError("Unterminated quote", rule, pos);
                                    }
                                    buf.append(rule.substring(pos5, iq));
                                    pos5 = iq + 1;
                                    if (pos5 < limit && rule.charAt(pos5) == '\'') {
                                        iq = rule.indexOf(39, pos5 + 1);
                                    }
                                }
                                quoteLimit2 = buf.length();
                                for (int iq2 = quoteStart; iq2 < quoteLimit2; iq2++) {
                                    parser.checkVariableRange(buf.charAt(iq2), rule, pos);
                                }
                                pos4 = pos5;
                            }
                        }
                    }
                    return pos5;
                }
            }
            return pos4;
        }

        void removeContext() {
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

    /* JADX WARN: Removed duplicated region for block: B:106:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0205 A[LOOP:3: B:109:0x01fd->B:111:0x0205, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0236 A[Catch: IllegalArgumentException -> 0x0284, TryCatch #1 {IllegalArgumentException -> 0x0284, blocks: (B:113:0x0232, B:115:0x0236, B:124:0x0244, B:125:0x024b, B:127:0x024d, B:129:0x0255, B:130:0x0265, B:132:0x026e, B:134:0x027d), top: B:156:0x0232 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0255 A[Catch: IllegalArgumentException -> 0x0284, LOOP:4: B:127:0x024d->B:129:0x0255, LOOP_END, TryCatch #1 {IllegalArgumentException -> 0x0284, blocks: (B:113:0x0232, B:115:0x0236, B:124:0x0244, B:125:0x024b, B:127:0x024d, B:129:0x0255, B:130:0x0265, B:132:0x026e, B:134:0x027d), top: B:156:0x0232 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02c3 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void parseRules(RuleBody ruleArray, int dir) {
        int ruleCount;
        boolean parsingIDs;
        RuleBasedTransliterator.Data data;
        int i;
        RuntimeException previous;
        int i2;
        int i3;
        IllegalArgumentException e;
        boolean pos;
        RuleBasedTransliterator.Data data2;
        int i4;
        int i5;
        boolean parsingIDs2;
        int ppp;
        int i6;
        boolean ruleCount2 = true;
        int ruleCount3 = 0;
        this.dataVector = new ArrayList();
        this.idBlockVector = new ArrayList();
        RuleBasedTransliterator.Data data3 = null;
        this.curData = null;
        this.direction = dir;
        this.compoundFilter = null;
        this.variablesVector = new ArrayList();
        this.variableNames = new HashMap();
        this.parseData = new ParseData();
        List<RuntimeException> errors = new ArrayList<>();
        int errorCount = 0;
        ruleArray.reset();
        StringBuilder idBlockResult = new StringBuilder();
        this.compoundFilter = null;
        int compoundFilterOffset = -1;
        loop0: while (true) {
            String rule = ruleArray.nextLine();
            int i7 = 0;
            int i8 = 1;
            if (rule == null) {
                ruleCount = ruleCount3;
                parsingIDs = ruleCount2;
                break;
            }
            int pos2 = 0;
            int limit = rule.length();
            int i9 = ruleCount3;
            boolean parsingIDs3 = ruleCount2;
            int ruleCount4 = i9;
            while (pos2 < limit) {
                int pos3 = pos2 + 1;
                char c = rule.charAt(pos2);
                if (!PatternProps.isWhiteSpace(c)) {
                    if (c == '#') {
                        int pos4 = rule.indexOf("\n", pos3) + 1;
                        if (pos4 == 0) {
                            break;
                        }
                        pos2 = pos4;
                    } else if (c != ';') {
                        int ruleCount5 = ruleCount4 + 1;
                        int pos5 = pos3 - 1;
                        if (pos5 + 2 + i8 <= limit) {
                            try {
                            } catch (IllegalArgumentException e2) {
                                boolean z = parsingIDs3;
                                e = e2;
                                pos = z;
                            }
                            if (rule.regionMatches(pos5, ID_TOKEN, i7, 2)) {
                                pos5 += 2;
                                char c2 = rule.charAt(pos5);
                                while (PatternProps.isWhiteSpace(c2) && pos5 < limit) {
                                    pos5++;
                                    c2 = rule.charAt(pos5);
                                }
                                int[] p = new int[i8];
                                p[i7] = pos5;
                                if (!parsingIDs3) {
                                    RuleBasedTransliterator.Data data4 = this.curData;
                                    if (data4 != null) {
                                        if (this.direction == 0) {
                                            this.dataVector.add(data4);
                                        } else {
                                            this.dataVector.add(i7, data4);
                                        }
                                        this.curData = data3;
                                    }
                                    parsingIDs3 = true;
                                }
                                try {
                                    TransliteratorIDParser.SingleID id = TransliteratorIDParser.parseSingleID(rule, p, this.direction);
                                    if (p[i7] != pos5 && Utility.parseChar(rule, p, (char) END_OF_RULE)) {
                                        if (this.direction == 0) {
                                            idBlockResult.append(id.canonID).append(END_OF_RULE);
                                            parsingIDs2 = parsingIDs3;
                                        } else {
                                            idBlockResult.insert(i7, id.canonID + END_OF_RULE);
                                            parsingIDs2 = parsingIDs3;
                                        }
                                    } else {
                                        int[] withParens = new int[1];
                                        withParens[i7] = -1;
                                        UnicodeSet f = TransliteratorIDParser.parseGlobalFilter(rule, p, this.direction, withParens, null);
                                        if (f == null || !Utility.parseChar(rule, p, (char) END_OF_RULE)) {
                                            parsingIDs2 = parsingIDs3;
                                            syntaxError("Invalid ::ID", rule, pos5);
                                        } else {
                                            boolean z2 = this.direction == 0;
                                            parsingIDs2 = parsingIDs3;
                                            boolean parsingIDs4 = withParens[0] == 0;
                                            if (z2 == parsingIDs4) {
                                                try {
                                                    if (this.compoundFilter != null) {
                                                        syntaxError("Multiple global filters", rule, pos5);
                                                    }
                                                    this.compoundFilter = f;
                                                    compoundFilterOffset = ruleCount5;
                                                } catch (IllegalArgumentException e3) {
                                                    e = e3;
                                                    pos = parsingIDs2;
                                                    if (errorCount == 30) {
                                                        IllegalIcuArgumentException icuEx = new IllegalIcuArgumentException("\nMore than 30 errors; further messages squelched");
                                                        icuEx.initCause(e);
                                                        errors.add(icuEx);
                                                        parsingIDs = pos;
                                                        ruleCount = ruleCount5;
                                                        if (!parsingIDs) {
                                                        }
                                                        if (!parsingIDs) {
                                                            if (this.direction != 0) {
                                                            }
                                                        }
                                                        while (i < this.dataVector.size()) {
                                                        }
                                                        this.variablesVector = null;
                                                        if (this.compoundFilter != null) {
                                                        }
                                                        while (i2 < this.dataVector.size()) {
                                                        }
                                                        if (this.idBlockVector.size() == 1) {
                                                            this.idBlockVector.remove(0);
                                                        }
                                                        if (errors.size() == 0) {
                                                        }
                                                    } else {
                                                        data2 = null;
                                                        i4 = 0;
                                                        e.fillInStackTrace();
                                                        errors.add(e);
                                                        errorCount++;
                                                        i5 = 1;
                                                        parsingIDs3 = pos;
                                                        pos2 = ruleEnd(rule, pos5, limit) + 1;
                                                        data3 = data2;
                                                        i7 = i4;
                                                        i8 = i5;
                                                        ruleCount4 = ruleCount5;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    ppp = p[0];
                                    parsingIDs3 = parsingIDs2;
                                    pos2 = ppp;
                                    data2 = null;
                                    i4 = 0;
                                    i5 = 1;
                                } catch (IllegalArgumentException e4) {
                                    boolean z3 = parsingIDs3;
                                    e = e4;
                                    pos = z3;
                                }
                                data3 = data2;
                                i7 = i4;
                                i8 = i5;
                                ruleCount4 = ruleCount5;
                            }
                        }
                        if (parsingIDs3) {
                            if (this.direction == 0) {
                                this.idBlockVector.add(idBlockResult.toString());
                                i6 = 0;
                            } else {
                                i6 = 0;
                                this.idBlockVector.add(0, idBlockResult.toString());
                            }
                            idBlockResult.delete(i6, idBlockResult.length());
                            parsingIDs3 = false;
                            this.curData = new RuleBasedTransliterator.Data();
                            setVariableRange(61440, 63743);
                        }
                        if (resemblesPragma(rule, pos5, limit)) {
                            ppp = parsePragma(rule, pos5, limit);
                            if (ppp < 0) {
                                syntaxError("Unrecognized pragma", rule, pos5);
                            }
                        } else {
                            ppp = parseRule(rule, pos5, limit);
                        }
                        pos2 = ppp;
                        data2 = null;
                        i4 = 0;
                        i5 = 1;
                        data3 = data2;
                        i7 = i4;
                        i8 = i5;
                        ruleCount4 = ruleCount5;
                    }
                }
                pos2 = pos3;
            }
            data3 = data3;
            boolean z4 = parsingIDs3;
            ruleCount3 = ruleCount4;
            ruleCount2 = z4;
        }
        if (!parsingIDs && idBlockResult.length() > 0) {
            if (this.direction == 0) {
                this.idBlockVector.add(idBlockResult.toString());
            } else {
                this.idBlockVector.add(0, idBlockResult.toString());
            }
        } else if (!parsingIDs && (data = this.curData) != null) {
            if (this.direction != 0) {
                this.dataVector.add(data);
            } else {
                this.dataVector.add(0, data);
            }
        }
        for (i = 0; i < this.dataVector.size(); i++) {
            RuleBasedTransliterator.Data data5 = this.dataVector.get(i);
            data5.variables = new Object[this.variablesVector.size()];
            this.variablesVector.toArray(data5.variables);
            data5.variableNames = new HashMap();
            data5.variableNames.putAll(this.variableNames);
        }
        this.variablesVector = null;
        try {
            if (this.compoundFilter != null) {
                int i10 = this.direction;
                if (i10 == 0) {
                    i3 = 1;
                    if (compoundFilterOffset == 1) {
                    }
                    throw new IllegalIcuArgumentException("Compound filters misplaced");
                }
                i3 = 1;
                if (i10 == i3) {
                    if (compoundFilterOffset == ruleCount) {
                    }
                    throw new IllegalIcuArgumentException("Compound filters misplaced");
                }
            }
            for (i2 = 0; i2 < this.dataVector.size(); i2++) {
                this.dataVector.get(i2).ruleSet.freeze();
            }
            if (this.idBlockVector.size() == 1 && this.idBlockVector.get(0).length() == 0) {
                this.idBlockVector.remove(0);
            }
        } catch (IllegalArgumentException e5) {
            e5.fillInStackTrace();
            errors.add(e5);
        }
        if (errors.size() == 0) {
            for (int i11 = errors.size() - 1; i11 > 0; i11--) {
                RuntimeException previous2 = errors.get(i11 - 1);
                while (true) {
                    previous = (RuntimeException) previous2;
                    if (previous.getCause() != null) {
                        previous2 = previous.getCause();
                    }
                }
                previous.initCause(errors.get(i11));
            }
            throw errors.get(0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0039, code lost:
        if (com.ibm.icu.text.TransliteratorParser.OPERATORS.indexOf(r8) < 0) goto L117;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int parseRule(String rule, int pos, int limit) {
        char operator = 0;
        this.segmentStandins = new StringBuffer();
        this.segmentObjects = new ArrayList();
        RuleHalf left = new RuleHalf();
        RuleHalf right = new RuleHalf();
        this.undefinedVariableName = null;
        int pos2 = left.parse(rule, pos, limit, this);
        if (pos2 != limit) {
            pos2--;
            char charAt = rule.charAt(pos2);
            operator = charAt;
        }
        syntaxError("No operator pos=" + pos2, rule, pos);
        int pos3 = pos2 + 1;
        if (operator == '<' && pos3 < limit && rule.charAt(pos3) == '>') {
            pos3++;
            operator = FWDREV_RULE_OP;
        }
        switch (operator) {
            case '\u2190':
                operator = '<';
                break;
            case '\u2192':
                operator = '>';
                break;
            case '\u2194':
                operator = FWDREV_RULE_OP;
                break;
        }
        int pos4 = right.parse(rule, pos3, limit, this);
        if (pos4 < limit) {
            pos4--;
            if (rule.charAt(pos4) == ';') {
                pos4++;
            } else {
                syntaxError("Unquoted operator", rule, pos);
            }
        }
        if (operator == '=') {
            if (this.undefinedVariableName == null) {
                syntaxError("Missing '$' or duplicate definition", rule, pos);
            }
            if (left.text.length() != 1 || left.text.charAt(0) != this.variableLimit) {
                syntaxError("Malformed LHS", rule, pos);
            }
            if (left.anchorStart || left.anchorEnd || right.anchorStart || right.anchorEnd) {
                syntaxError("Malformed variable def", rule, pos);
            }
            int n = right.text.length();
            char[] value = new char[n];
            right.text.getChars(0, n, value, 0);
            this.variableNames.put(this.undefinedVariableName, value);
            this.variableLimit = (char) (this.variableLimit + 1);
            return pos4;
        }
        if (this.undefinedVariableName != null) {
            syntaxError("Undefined variable $" + this.undefinedVariableName, rule, pos);
        }
        if (this.segmentStandins.length() > this.segmentObjects.size()) {
            syntaxError("Undefined segment reference", rule, pos);
        }
        for (int i = 0; i < this.segmentStandins.length(); i++) {
            if (this.segmentStandins.charAt(i) == 0) {
                syntaxError("Internal error", rule, pos);
            }
        }
        for (int i2 = 0; i2 < this.segmentObjects.size(); i2++) {
            if (this.segmentObjects.get(i2) == null) {
                syntaxError("Internal error", rule, pos);
            }
        }
        if (operator != '~') {
            if ((this.direction == 0) != (operator == '>')) {
                return pos4;
            }
        }
        if (this.direction == 1) {
            left = right;
            right = left;
        }
        if (operator == '~') {
            right.removeContext();
            left.cursor = -1;
            left.cursorOffset = 0;
        }
        if (left.ante < 0) {
            left.ante = 0;
        }
        if (left.post < 0) {
            left.post = left.text.length();
        }
        if (right.ante >= 0 || right.post >= 0 || left.cursor >= 0 || ((right.cursorOffset != 0 && right.cursor < 0) || right.anchorStart || right.anchorEnd || !left.isValidInput(this) || !right.isValidOutput(this) || left.ante > left.post)) {
            syntaxError("Malformed rule", rule, pos);
        }
        UnicodeMatcher[] segmentsArray = null;
        if (this.segmentObjects.size() > 0) {
            segmentsArray = new UnicodeMatcher[this.segmentObjects.size()];
            this.segmentObjects.toArray(segmentsArray);
        }
        this.curData.ruleSet.addRule(new TransliterationRule(left.text, left.ante, left.post, right.text, right.cursor, right.cursorOffset, segmentsArray, left.anchorStart, left.anchorEnd, this.curData));
        return pos4;
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

    /* JADX INFO: Access modifiers changed from: private */
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
        if (p4 >= 0) {
            pragmaNormalizeRules(Normalizer.NFC);
            return p4;
        }
        return -1;
    }

    static final void syntaxError(String msg, String rule, int start) {
        int end = ruleEnd(rule, start, rule.length());
        throw new IllegalIcuArgumentException(msg + " in \"" + Utility.escape(rule.substring(start, end)) + Typography.quote);
    }

    static final int ruleEnd(String rule, int start, int limit) {
        int end = Utility.quotedIndexOf(rule, start, limit, ";");
        if (end < 0) {
            return limit;
        }
        return end;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final char parseSet(String rule, ParsePosition pos) {
        UnicodeSet set = new UnicodeSet(rule, pos, this.parseData);
        if (this.variableNext >= this.variableLimit) {
            throw new RuntimeException("Private use variables exhausted");
        }
        set.compact();
        return generateStandInFor(set);
    }

    char generateStandInFor(Object obj) {
        for (int i = 0; i < this.variablesVector.size(); i++) {
            if (this.variablesVector.get(i) == obj) {
                return (char) (this.curData.variablesBase + i);
            }
        }
        int i2 = this.variableNext;
        if (i2 >= this.variableLimit) {
            throw new RuntimeException("Variable range exhausted");
        }
        this.variablesVector.add(obj);
        char c = this.variableNext;
        this.variableNext = (char) (c + 1);
        return c;
    }

    public char getSegmentStandin(int seg) {
        if (this.segmentStandins.length() < seg) {
            this.segmentStandins.setLength(seg);
        }
        char c = this.segmentStandins.charAt(seg - 1);
        if (c == 0) {
            char c2 = this.variableNext;
            if (c2 >= this.variableLimit) {
                throw new RuntimeException("Variable range exhausted");
            }
            this.variableNext = (char) (c2 + 1);
            this.variablesVector.add(null);
            this.segmentStandins.setCharAt(seg - 1, c2);
            return c2;
        }
        return c;
    }

    public void setSegmentObject(int seg, StringMatcher obj) {
        while (this.segmentObjects.size() < seg) {
            this.segmentObjects.add(null);
        }
        int index = getSegmentStandin(seg) - this.curData.variablesBase;
        if (this.segmentObjects.get(seg - 1) != null || this.variablesVector.get(index) != null) {
            throw new RuntimeException();
        }
        this.segmentObjects.set(seg - 1, obj);
        this.variablesVector.set(index, obj);
    }

    char getDotStandIn() {
        if (this.dotStandIn == -1) {
            this.dotStandIn = generateStandInFor(new UnicodeSet(DOT_SET));
        }
        return (char) this.dotStandIn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendVariableDef(String name, StringBuffer buf) {
        char[] ch = this.variableNames.get(name);
        if (ch == null) {
            if (this.undefinedVariableName == null) {
                this.undefinedVariableName = name;
                char c = this.variableNext;
                char c2 = this.variableLimit;
                if (c >= c2) {
                    throw new RuntimeException("Private use variables exhausted");
                }
                char c3 = (char) (c2 - 1);
                this.variableLimit = c3;
                buf.append(c3);
                return;
            }
            throw new IllegalIcuArgumentException("Undefined variable $" + name);
        }
        buf.append(ch);
    }
}
