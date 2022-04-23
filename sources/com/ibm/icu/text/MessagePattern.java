package com.ibm.icu.text;

import com.ibm.icu.impl.ICUConfig;
import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.ICUCloneNotSupportedException;
import java.util.ArrayList;

public final class MessagePattern implements Cloneable, Freezable<MessagePattern> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ARG_NAME_NOT_NUMBER = -1;
    public static final int ARG_NAME_NOT_VALID = -2;
    private static final int MAX_PREFIX_LENGTH = 24;
    public static final double NO_NUMERIC_VALUE = -1.23456789E8d;
    /* access modifiers changed from: private */
    public static final ArgType[] argTypes = ArgType.values();
    private static final ApostropheMode defaultAposMode = ApostropheMode.valueOf(ICUConfig.get("com.ibm.icu.text.MessagePattern.ApostropheMode", "DOUBLE_OPTIONAL"));
    private ApostropheMode aposMode;
    private volatile boolean frozen;
    private boolean hasArgNames;
    private boolean hasArgNumbers;
    private String msg;
    private boolean needsAutoQuoting;
    private ArrayList<Double> numericValues;
    private ArrayList<Part> parts;

    public enum ApostropheMode {
        DOUBLE_OPTIONAL,
        DOUBLE_REQUIRED
    }

    public MessagePattern() {
        this.parts = new ArrayList<>();
        this.aposMode = defaultAposMode;
    }

    public MessagePattern(ApostropheMode mode) {
        this.parts = new ArrayList<>();
        this.aposMode = mode;
    }

    public MessagePattern(String pattern) {
        this.parts = new ArrayList<>();
        this.aposMode = defaultAposMode;
        parse(pattern);
    }

    public MessagePattern parse(String pattern) {
        preParse(pattern);
        parseMessage(0, 0, 0, ArgType.NONE);
        postParse();
        return this;
    }

    public MessagePattern parseChoiceStyle(String pattern) {
        preParse(pattern);
        parseChoiceStyle(0, 0);
        postParse();
        return this;
    }

    public MessagePattern parsePluralStyle(String pattern) {
        preParse(pattern);
        parsePluralOrSelectStyle(ArgType.PLURAL, 0, 0);
        postParse();
        return this;
    }

    public MessagePattern parseSelectStyle(String pattern) {
        preParse(pattern);
        parsePluralOrSelectStyle(ArgType.SELECT, 0, 0);
        postParse();
        return this;
    }

    public void clear() {
        if (!isFrozen()) {
            this.msg = null;
            this.hasArgNumbers = false;
            this.hasArgNames = false;
            this.needsAutoQuoting = false;
            this.parts.clear();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to clear() a frozen MessagePattern instance.");
    }

    public void clearPatternAndSetApostropheMode(ApostropheMode mode) {
        clear();
        this.aposMode = mode;
    }

    public boolean equals(Object other) {
        String str;
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        MessagePattern o = (MessagePattern) other;
        if (!this.aposMode.equals(o.aposMode) || ((str = this.msg) != null ? !str.equals(o.msg) : o.msg != null) || !this.parts.equals(o.parts)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.aposMode.hashCode() * 37;
        String str = this.msg;
        return ((hashCode + (str != null ? str.hashCode() : 0)) * 37) + this.parts.hashCode();
    }

    public ApostropheMode getApostropheMode() {
        return this.aposMode;
    }

    /* access modifiers changed from: package-private */
    public boolean jdkAposMode() {
        return this.aposMode == ApostropheMode.DOUBLE_REQUIRED;
    }

    public String getPatternString() {
        return this.msg;
    }

    public boolean hasNamedArguments() {
        return this.hasArgNames;
    }

    public boolean hasNumberedArguments() {
        return this.hasArgNumbers;
    }

    public String toString() {
        return this.msg;
    }

    public static int validateArgumentName(String name) {
        if (!PatternProps.isIdentifier(name)) {
            return -2;
        }
        return parseArgNumber(name, 0, name.length());
    }

    public String autoQuoteApostropheDeep() {
        if (!this.needsAutoQuoting) {
            return this.msg;
        }
        StringBuilder modified = null;
        int i = countParts();
        while (i > 0) {
            i--;
            Part part = getPart(i);
            Part part2 = part;
            if (part.getType() == Part.Type.INSERT_CHAR) {
                if (modified == null) {
                    modified = new StringBuilder(this.msg.length() + 10).append(this.msg);
                }
                modified.insert(part2.index, (char) part2.value);
            }
        }
        if (modified == null) {
            return this.msg;
        }
        return modified.toString();
    }

    public int countParts() {
        return this.parts.size();
    }

    public Part getPart(int i) {
        return this.parts.get(i);
    }

    public Part.Type getPartType(int i) {
        return this.parts.get(i).type;
    }

    public int getPatternIndex(int partIndex) {
        return this.parts.get(partIndex).index;
    }

    public String getSubstring(Part part) {
        int index = part.index;
        return this.msg.substring(index, part.length + index);
    }

    public boolean partSubstringMatches(Part part, String s) {
        return part.length == s.length() && this.msg.regionMatches(part.index, s, 0, part.length);
    }

    public double getNumericValue(Part part) {
        Part.Type type = part.type;
        if (type == Part.Type.ARG_INT) {
            return (double) part.value;
        }
        if (type == Part.Type.ARG_DOUBLE) {
            return this.numericValues.get(part.value).doubleValue();
        }
        return -1.23456789E8d;
    }

    public double getPluralOffset(int pluralStart) {
        Part part = this.parts.get(pluralStart);
        if (part.type.hasNumericValue()) {
            return getNumericValue(part);
        }
        return 0.0d;
    }

    public int getLimitPartIndex(int start) {
        int limit = this.parts.get(start).limitPartIndex;
        if (limit < start) {
            return start;
        }
        return limit;
    }

    public static final class Part {
        private static final int MAX_LENGTH = 65535;
        private static final int MAX_VALUE = 32767;
        /* access modifiers changed from: private */
        public final int index;
        /* access modifiers changed from: private */
        public final char length;
        /* access modifiers changed from: private */
        public int limitPartIndex;
        /* access modifiers changed from: private */
        public final Type type;
        /* access modifiers changed from: private */
        public short value;

        private Part(Type t, int i, int l, int v) {
            this.type = t;
            this.index = i;
            this.length = (char) l;
            this.value = (short) v;
        }

        public Type getType() {
            return this.type;
        }

        public int getIndex() {
            return this.index;
        }

        public int getLength() {
            return this.length;
        }

        public int getLimit() {
            return this.index + this.length;
        }

        public int getValue() {
            return this.value;
        }

        public ArgType getArgType() {
            Type type2 = getType();
            if (type2 == Type.ARG_START || type2 == Type.ARG_LIMIT) {
                return MessagePattern.argTypes[this.value];
            }
            return ArgType.NONE;
        }

        public enum Type {
            MSG_START,
            MSG_LIMIT,
            SKIP_SYNTAX,
            INSERT_CHAR,
            REPLACE_NUMBER,
            ARG_START,
            ARG_LIMIT,
            ARG_NUMBER,
            ARG_NAME,
            ARG_TYPE,
            ARG_STYLE,
            ARG_SELECTOR,
            ARG_INT,
            ARG_DOUBLE;

            public boolean hasNumericValue() {
                return this == ARG_INT || this == ARG_DOUBLE;
            }
        }

        public String toString() {
            return this.type.name() + "(" + ((this.type == Type.ARG_START || this.type == Type.ARG_LIMIT) ? getArgType().name() : Integer.toString(this.value)) + ")@" + this.index;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || getClass() != other.getClass()) {
                return false;
            }
            Part o = (Part) other;
            if (this.type.equals(o.type) && this.index == o.index && this.length == o.length && this.value == o.value && this.limitPartIndex == o.limitPartIndex) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((((this.type.hashCode() * 37) + this.index) * 37) + this.length) * 37) + this.value;
        }
    }

    public enum ArgType {
        NONE,
        SIMPLE,
        CHOICE,
        PLURAL,
        SELECT,
        SELECTORDINAL;

        public boolean hasPluralStyle() {
            return this == PLURAL || this == SELECTORDINAL;
        }
    }

    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    public MessagePattern cloneAsThawed() {
        try {
            MessagePattern newMsg = (MessagePattern) super.clone();
            newMsg.parts = (ArrayList) this.parts.clone();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                newMsg.numericValues = (ArrayList) arrayList.clone();
            }
            newMsg.frozen = false;
            return newMsg;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public MessagePattern freeze() {
        this.frozen = true;
        return this;
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    private void preParse(String pattern) {
        if (!isFrozen()) {
            this.msg = pattern;
            this.hasArgNumbers = false;
            this.hasArgNames = false;
            this.needsAutoQuoting = false;
            this.parts.clear();
            ArrayList<Double> arrayList = this.numericValues;
            if (arrayList != null) {
                arrayList.clear();
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("Attempt to parse(" + prefix(pattern) + ") on frozen MessagePattern instance.");
    }

    private void postParse() {
    }

    private int parseMessage(int index, int msgStartLength, int nestingLevel, ArgType parentType) {
        int index2;
        if (nestingLevel <= 32767) {
            int msgStart = this.parts.size();
            addPart(Part.Type.MSG_START, index, msgStartLength, nestingLevel);
            int index3 = index + msgStartLength;
            while (index3 < this.msg.length()) {
                int index4 = index3 + 1;
                int index5 = this.msg.charAt(index3);
                if (index5 == 39) {
                    if (index4 == this.msg.length()) {
                        addPart(Part.Type.INSERT_CHAR, index4, 0, 39);
                        this.needsAutoQuoting = true;
                    } else {
                        char c = this.msg.charAt(index4);
                        if (c == '\'') {
                            addPart(Part.Type.SKIP_SYNTAX, index4, 1, 0);
                            index3 = index4 + 1;
                        } else if (this.aposMode == ApostropheMode.DOUBLE_REQUIRED || c == '{' || c == '}' || ((parentType == ArgType.CHOICE && c == '|') || (parentType.hasPluralStyle() && c == '#'))) {
                            addPart(Part.Type.SKIP_SYNTAX, index4 - 1, 1, 0);
                            while (true) {
                                index2 = this.msg.indexOf(39, index4 + 1);
                                if (index2 < 0) {
                                    int index6 = this.msg.length();
                                    addPart(Part.Type.INSERT_CHAR, index6, 0, 39);
                                    this.needsAutoQuoting = true;
                                    index3 = index6;
                                    break;
                                } else if (index2 + 1 >= this.msg.length() || this.msg.charAt(index2 + 1) != '\'') {
                                    addPart(Part.Type.SKIP_SYNTAX, index2, 1, 0);
                                    index3 = index2 + 1;
                                } else {
                                    index4 = index2 + 1;
                                    addPart(Part.Type.SKIP_SYNTAX, index4, 1, 0);
                                }
                            }
                            addPart(Part.Type.SKIP_SYNTAX, index2, 1, 0);
                            index3 = index2 + 1;
                        } else {
                            addPart(Part.Type.INSERT_CHAR, index4, 0, 39);
                            this.needsAutoQuoting = true;
                        }
                    }
                } else if (parentType.hasPluralStyle() && index5 == 35) {
                    addPart(Part.Type.REPLACE_NUMBER, index4 - 1, 1, 0);
                } else if (index5 == 123) {
                    index3 = parseArg(index4 - 1, 1, nestingLevel);
                } else if ((nestingLevel > 0 && index5 == 125) || (parentType == ArgType.CHOICE && index5 == 124)) {
                    addLimitPart(msgStart, Part.Type.MSG_LIMIT, index4 - 1, (parentType == ArgType.CHOICE && index5 == 125) ? 0 : 1, nestingLevel);
                    if (parentType == ArgType.CHOICE) {
                        return index4 - 1;
                    }
                    return index4;
                }
                index3 = index4;
            }
            if (nestingLevel <= 0 || inTopLevelChoiceMessage(nestingLevel, parentType)) {
                addLimitPart(msgStart, Part.Type.MSG_LIMIT, index3, 0, nestingLevel);
                return index3;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IndexOutOfBoundsException();
    }

    private int parseArg(int index, int argStartLength, int nestingLevel) {
        ArgType argType;
        int index2;
        int i = index;
        int i2 = argStartLength;
        int i3 = nestingLevel;
        int argStart = this.parts.size();
        ArgType argType2 = ArgType.NONE;
        addPart(Part.Type.ARG_START, i, i2, argType2.ordinal());
        int skipWhiteSpace = skipWhiteSpace(i + i2);
        int index3 = skipWhiteSpace;
        int nameIndex = skipWhiteSpace;
        if (index3 != this.msg.length()) {
            int index4 = skipIdentifier(index3);
            int number = parseArgNumber(nameIndex, index4);
            if (number >= 0) {
                int length = index4 - nameIndex;
                if (length > 65535 || number > 32767) {
                    throw new IndexOutOfBoundsException("Argument number too large: " + prefix(nameIndex));
                }
                this.hasArgNumbers = true;
                addPart(Part.Type.ARG_NUMBER, nameIndex, length, number);
            } else if (number == -1) {
                int length2 = index4 - nameIndex;
                if (length2 <= 65535) {
                    this.hasArgNames = true;
                    addPart(Part.Type.ARG_NAME, nameIndex, length2, 0);
                } else {
                    throw new IndexOutOfBoundsException("Argument name too long: " + prefix(nameIndex));
                }
            } else {
                throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
            }
            int index5 = skipWhiteSpace(index4);
            if (index5 != this.msg.length()) {
                char c = this.msg.charAt(index5);
                if (c == '}') {
                    argType = argType2;
                    char c2 = c;
                    index2 = index5;
                } else if (c == ',') {
                    int typeIndex = skipWhiteSpace(index5 + 1);
                    int index6 = typeIndex;
                    while (index6 < this.msg.length() && isArgTypeChar(this.msg.charAt(index6))) {
                        index6++;
                    }
                    int length3 = index6 - typeIndex;
                    int index7 = skipWhiteSpace(index6);
                    if (index7 != this.msg.length()) {
                        if (length3 != 0) {
                            char charAt = this.msg.charAt(index7);
                            char c3 = charAt;
                            if (charAt == ',' || c3 == '}') {
                                if (length3 <= 65535) {
                                    ArgType argType3 = ArgType.SIMPLE;
                                    if (length3 == 6) {
                                        if (isChoice(typeIndex)) {
                                            argType3 = ArgType.CHOICE;
                                        } else if (isPlural(typeIndex)) {
                                            argType3 = ArgType.PLURAL;
                                        } else if (isSelect(typeIndex)) {
                                            argType3 = ArgType.SELECT;
                                        }
                                    } else if (length3 == 13 && isSelect(typeIndex) && isOrdinal(typeIndex + 6)) {
                                        argType3 = ArgType.SELECTORDINAL;
                                    }
                                    short unused = this.parts.get(argStart).value = (short) argType3.ordinal();
                                    if (argType3 == ArgType.SIMPLE) {
                                        addPart(Part.Type.ARG_TYPE, typeIndex, length3, 0);
                                    }
                                    if (c3 != '}') {
                                        int index8 = index7 + 1;
                                        if (argType3 == ArgType.SIMPLE) {
                                            argType = argType3;
                                            char c4 = c3;
                                            index2 = parseSimpleStyle(index8);
                                        } else if (argType3 == ArgType.CHOICE) {
                                            argType = argType3;
                                            char c5 = c3;
                                            index2 = parseChoiceStyle(index8, i3);
                                        } else {
                                            argType = argType3;
                                            char c6 = c3;
                                            index2 = parsePluralOrSelectStyle(argType3, index8, i3);
                                        }
                                    } else if (argType3 == ArgType.SIMPLE) {
                                        argType = argType3;
                                        char c7 = c3;
                                        index2 = index7;
                                    } else {
                                        throw new IllegalArgumentException("No style field for complex argument: " + prefix(nameIndex));
                                    }
                                } else {
                                    throw new IndexOutOfBoundsException("Argument type name too long: " + prefix(nameIndex));
                                }
                            }
                        }
                        throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
                    }
                    throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
                } else {
                    throw new IllegalArgumentException("Bad argument syntax: " + prefix(nameIndex));
                }
                addLimitPart(argStart, Part.Type.ARG_LIMIT, index2, 1, argType.ordinal());
                return index2 + 1;
            }
            throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseSimpleStyle(int index) {
        int start = index;
        int nestedBraces = 0;
        while (index < this.msg.length()) {
            int index2 = index + 1;
            int index3 = this.msg.charAt(index);
            if (index3 == 39) {
                int index4 = this.msg.indexOf(39, index2);
                if (index4 >= 0) {
                    index = index4 + 1;
                } else {
                    throw new IllegalArgumentException("Quoted literal argument style text reaches to the end of the message: " + prefix(start));
                }
            } else if (index3 == 123) {
                nestedBraces++;
                index = index2;
            } else if (index3 != 125) {
                index = index2;
            } else if (nestedBraces > 0) {
                nestedBraces--;
                index = index2;
            } else {
                int index5 = index2 - 1;
                int length = index5 - start;
                if (length <= 65535) {
                    addPart(Part.Type.ARG_STYLE, start, length, 0);
                    return index5;
                }
                throw new IndexOutOfBoundsException("Argument style text too long: " + prefix(start));
            }
        }
        throw new IllegalArgumentException("Unmatched '{' braces in message " + prefix());
    }

    private int parseChoiceStyle(int index, int nestingLevel) {
        int start = index;
        int index2 = skipWhiteSpace(index);
        if (index2 == this.msg.length() || this.msg.charAt(index2) == '}') {
            throw new IllegalArgumentException("Missing choice argument pattern in " + prefix());
        }
        while (true) {
            int numberIndex = index2;
            int index3 = skipDouble(index2);
            int length = index3 - numberIndex;
            if (length == 0) {
                throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(start));
            } else if (length <= 65535) {
                parseDouble(numberIndex, index3, true);
                int index4 = skipWhiteSpace(index3);
                if (index4 != this.msg.length()) {
                    char c = this.msg.charAt(index4);
                    if (c == '#' || c == '<' || c == 8804) {
                        addPart(Part.Type.ARG_SELECTOR, index4, 1, 0);
                        int index5 = parseMessage(index4 + 1, 0, nestingLevel + 1, ArgType.CHOICE);
                        if (index5 == this.msg.length()) {
                            return index5;
                        }
                        if (this.msg.charAt(index5) != '}') {
                            index2 = skipWhiteSpace(index5 + 1);
                        } else if (inMessageFormatPattern(nestingLevel)) {
                            return index5;
                        } else {
                            throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(start));
                        }
                    } else {
                        throw new IllegalArgumentException("Expected choice separator (#<≤) instead of '" + c + "' in choice pattern " + prefix(start));
                    }
                } else {
                    throw new IllegalArgumentException("Bad choice pattern syntax: " + prefix(start));
                }
            } else {
                throw new IndexOutOfBoundsException("Choice number too long: " + prefix(numberIndex));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x019f, code lost:
        throw new java.lang.IllegalArgumentException("No message fragment after " + r17.toString().toLowerCase(java.util.Locale.ENGLISH) + " selector: " + prefix(r11));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parsePluralOrSelectStyle(com.ibm.icu.text.MessagePattern.ArgType r17, int r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r19
            r2 = r18
            r3 = 1
            r4 = 0
            r5 = r4
            r4 = r3
            r3 = r18
        L_0x000c:
            int r3 = r0.skipWhiteSpace(r3)
            java.lang.String r6 = r0.msg
            int r6 = r6.length()
            r7 = 1
            r8 = 0
            if (r3 != r6) goto L_0x001c
            r6 = r7
            goto L_0x001d
        L_0x001c:
            r6 = r8
        L_0x001d:
            java.lang.String r9 = " pattern syntax: "
            java.lang.String r10 = "Bad "
            if (r6 != 0) goto L_0x01ec
            java.lang.String r11 = r0.msg
            char r11 = r11.charAt(r3)
            r12 = 125(0x7d, float:1.75E-43)
            if (r11 != r12) goto L_0x0031
            r14 = r17
            goto L_0x01ee
        L_0x0031:
            r11 = r3
            boolean r12 = r17.hasPluralStyle()
            java.lang.String r13 = "Argument selector too long: "
            r14 = 65535(0xffff, float:9.1834E-41)
            if (r12 == 0) goto L_0x00a7
            java.lang.String r12 = r0.msg
            char r12 = r12.charAt(r11)
            r15 = 61
            if (r12 != r15) goto L_0x00a7
            int r12 = r3 + 1
            int r3 = r0.skipDouble(r12)
            int r12 = r3 - r11
            if (r12 == r7) goto L_0x007a
            if (r12 > r14) goto L_0x005f
            com.ibm.icu.text.MessagePattern$Part$Type r9 = com.ibm.icu.text.MessagePattern.Part.Type.ARG_SELECTOR
            r0.addPart(r9, r11, r12, r8)
            int r9 = r11 + 1
            r0.parseDouble(r9, r3, r8)
            goto L_0x014c
        L_0x005f:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r13)
            java.lang.String r9 = r0.prefix((int) r11)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x007a:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.String r10 = r17.toString()
            java.util.Locale r13 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r13)
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r0.prefix((int) r2)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00a7:
            int r3 = r0.skipIdentifier(r3)
            int r12 = r3 - r11
            if (r12 == 0) goto L_0x01bd
            boolean r9 = r17.hasPluralStyle()
            if (r9 == 0) goto L_0x013a
            r9 = 6
            if (r12 != r9) goto L_0x013a
            java.lang.String r9 = r0.msg
            int r9 = r9.length()
            if (r3 >= r9) goto L_0x013a
            java.lang.String r9 = r0.msg
            r10 = 7
            java.lang.String r15 = "offset:"
            boolean r9 = r9.regionMatches(r11, r15, r8, r10)
            if (r9 == 0) goto L_0x013a
            if (r4 == 0) goto L_0x011d
            int r7 = r3 + 1
            int r7 = r0.skipWhiteSpace(r7)
            int r3 = r0.skipDouble(r7)
            if (r3 == r7) goto L_0x0100
            int r9 = r3 - r7
            if (r9 > r14) goto L_0x00e3
            r0.parseDouble(r7, r3, r8)
            r4 = 0
            goto L_0x000c
        L_0x00e3:
            java.lang.IndexOutOfBoundsException r8 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Plural offset value too long: "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = r0.prefix((int) r7)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0100:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Missing value for plural 'offset:' "
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = r0.prefix((int) r2)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x011d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Plural argument 'offset:' (if present) must precede key-message pairs: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r0.prefix((int) r2)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x013a:
            if (r12 > r14) goto L_0x01a0
            com.ibm.icu.text.MessagePattern$Part$Type r9 = com.ibm.icu.text.MessagePattern.Part.Type.ARG_SELECTOR
            r0.addPart(r9, r11, r12, r8)
            java.lang.String r9 = r0.msg
            java.lang.String r10 = "other"
            boolean r8 = r9.regionMatches(r11, r10, r8, r12)
            if (r8 == 0) goto L_0x014c
            r5 = 1
        L_0x014c:
            int r3 = r0.skipWhiteSpace(r3)
            java.lang.String r8 = r0.msg
            int r8 = r8.length()
            if (r3 == r8) goto L_0x016d
            java.lang.String r8 = r0.msg
            char r8 = r8.charAt(r3)
            r9 = 123(0x7b, float:1.72E-43)
            if (r8 != r9) goto L_0x016d
            int r8 = r1 + 1
            r14 = r17
            int r3 = r0.parseMessage(r3, r7, r8, r14)
            r4 = 0
            goto L_0x000c
        L_0x016d:
            r14 = r17
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "No message fragment after "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r17.toString()
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r9 = r9.toLowerCase(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = " selector: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r0.prefix((int) r11)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x01a0:
            r14 = r17
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r13)
            java.lang.String r9 = r0.prefix((int) r11)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x01bd:
            r14 = r17
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.String r10 = r17.toString()
            java.util.Locale r13 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r13)
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r0.prefix((int) r2)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x01ec:
            r14 = r17
        L_0x01ee:
            boolean r7 = r0.inMessageFormatPattern(r1)
            if (r6 == r7) goto L_0x0228
            if (r5 == 0) goto L_0x01f7
            return r3
        L_0x01f7:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Missing 'other' keyword in "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r17.toString()
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r9 = r9.toLowerCase(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = " pattern in "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r16.prefix()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x0228:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.String r10 = r17.toString()
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r11)
            java.lang.StringBuilder r8 = r8.append(r10)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = r0.prefix((int) r2)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.MessagePattern.parsePluralOrSelectStyle(com.ibm.icu.text.MessagePattern$ArgType, int, int):int");
    }

    private static int parseArgNumber(CharSequence s, int start, int limit) {
        boolean badNumber;
        int number;
        if (start >= limit) {
            return -2;
        }
        int start2 = start + 1;
        int start3 = s.charAt(start);
        if (start3 == 48) {
            if (start2 == limit) {
                return 0;
            }
            number = 0;
            badNumber = true;
        } else if (49 > start3 || start3 > 57) {
            return -1;
        } else {
            number = start3 - 48;
            badNumber = false;
        }
        while (start2 < limit) {
            int start4 = start2 + 1;
            char c = s.charAt(start2);
            if ('0' > c || c > '9') {
                return -1;
            }
            if (number >= 214748364) {
                badNumber = true;
            }
            number = (number * 10) + (c - '0');
            start2 = start4;
        }
        if (badNumber) {
            return -2;
        }
        return number;
    }

    private int parseArgNumber(int start, int limit) {
        return parseArgNumber(this.msg, start, limit);
    }

    private void parseDouble(int start, int limit, boolean allowInfinity) {
        if (start < limit) {
            int value = 0;
            int isNegative = 0;
            int index = start;
            int index2 = index + 1;
            char c = this.msg.charAt(index);
            if (c == '-') {
                isNegative = 1;
                if (index2 != limit) {
                    c = this.msg.charAt(index2);
                    index2++;
                }
                throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
            } else if (c == '+') {
                if (index2 != limit) {
                    c = this.msg.charAt(index2);
                    index2++;
                }
                throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
            }
            if (c == 8734) {
                if (allowInfinity && index2 == limit) {
                    addArgDoublePart(isNegative != 0 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY, start, limit - start);
                    return;
                }
                throw new NumberFormatException("Bad syntax for numeric value: " + this.msg.substring(start, limit));
            }
            while ('0' <= c && c <= '9' && (value = (value * 10) + (c - '0')) <= isNegative + 32767) {
                if (index2 == limit) {
                    addPart(Part.Type.ARG_INT, start, limit - start, isNegative != 0 ? -value : value);
                    return;
                } else {
                    c = this.msg.charAt(index2);
                    index2++;
                }
            }
            addArgDoublePart(Double.parseDouble(this.msg.substring(start, limit)), start, limit - start);
            return;
        }
        throw new AssertionError();
    }

    static void appendReducedApostrophes(String s, int start, int limit, StringBuilder sb) {
        int doubleApos = -1;
        while (true) {
            int i = s.indexOf(39, start);
            if (i < 0 || i >= limit) {
                sb.append(s, start, limit);
            } else if (i == doubleApos) {
                sb.append('\'');
                start++;
                doubleApos = -1;
            } else {
                sb.append(s, start, i);
                int i2 = i + 1;
                start = i2;
                doubleApos = i2;
            }
        }
        sb.append(s, start, limit);
    }

    private int skipWhiteSpace(int index) {
        return PatternProps.skipWhiteSpace(this.msg, index);
    }

    private int skipIdentifier(int index) {
        return PatternProps.skipIdentifier(this.msg, index);
    }

    private int skipDouble(int index) {
        while (index < this.msg.length() && (((c = this.msg.charAt(index)) >= '0' || "+-.".indexOf(c) >= 0) && (c <= '9' || c == 'e' || c == 'E' || c == 8734))) {
            index++;
        }
        return index;
    }

    private static boolean isArgTypeChar(int c) {
        return (97 <= c && c <= 122) || (65 <= c && c <= 90);
    }

    private boolean isChoice(int index) {
        int index2 = index + 1;
        char index3 = this.msg.charAt(index);
        char c = index3;
        if (index3 == 'c' || c == 'C') {
            int index4 = index2 + 1;
            char charAt = this.msg.charAt(index2);
            char c2 = charAt;
            if (charAt == 'h' || c2 == 'H') {
                int index5 = index4 + 1;
                char charAt2 = this.msg.charAt(index4);
                char c3 = charAt2;
                if (charAt2 == 'o' || c3 == 'O') {
                    int index6 = index5 + 1;
                    char charAt3 = this.msg.charAt(index5);
                    char c4 = charAt3;
                    if (charAt3 == 'i' || c4 == 'I') {
                        int index7 = index6 + 1;
                        char charAt4 = this.msg.charAt(index6);
                        char c5 = charAt4;
                        if (charAt4 == 'c' || c5 == 'C') {
                            char charAt5 = this.msg.charAt(index7);
                            char c6 = charAt5;
                            if (charAt5 == 'e' || c6 == 'E') {
                                return true;
                            }
                        }
                    } else {
                        int i = index6;
                    }
                }
            } else {
                int i2 = index4;
            }
        }
        return false;
    }

    private boolean isPlural(int index) {
        int index2 = index + 1;
        char index3 = this.msg.charAt(index);
        char c = index3;
        if (index3 == 'p' || c == 'P') {
            int index4 = index2 + 1;
            char charAt = this.msg.charAt(index2);
            char c2 = charAt;
            if (charAt == 'l' || c2 == 'L') {
                int index5 = index4 + 1;
                char charAt2 = this.msg.charAt(index4);
                char c3 = charAt2;
                if (charAt2 == 'u' || c3 == 'U') {
                    int index6 = index5 + 1;
                    char charAt3 = this.msg.charAt(index5);
                    char c4 = charAt3;
                    if (charAt3 == 'r' || c4 == 'R') {
                        int index7 = index6 + 1;
                        char charAt4 = this.msg.charAt(index6);
                        char c5 = charAt4;
                        if (charAt4 == 'a' || c5 == 'A') {
                            char charAt5 = this.msg.charAt(index7);
                            char c6 = charAt5;
                            if (charAt5 == 'l' || c6 == 'L') {
                                return true;
                            }
                            index2 = index7;
                        } else {
                            index2 = index7;
                        }
                    } else {
                        index2 = index6;
                    }
                } else {
                    index2 = index5;
                }
            } else {
                index2 = index4;
            }
        }
        int i = index2;
        return false;
    }

    private boolean isSelect(int index) {
        int index2 = index + 1;
        char index3 = this.msg.charAt(index);
        char c = index3;
        if (index3 == 's' || c == 'S') {
            int index4 = index2 + 1;
            char charAt = this.msg.charAt(index2);
            char c2 = charAt;
            if (charAt == 'e' || c2 == 'E') {
                int index5 = index4 + 1;
                char charAt2 = this.msg.charAt(index4);
                char c3 = charAt2;
                if (charAt2 == 'l' || c3 == 'L') {
                    int index6 = index5 + 1;
                    char charAt3 = this.msg.charAt(index5);
                    char c4 = charAt3;
                    if (charAt3 == 'e' || c4 == 'E') {
                        int index7 = index6 + 1;
                        char charAt4 = this.msg.charAt(index6);
                        char c5 = charAt4;
                        if (charAt4 == 'c' || c5 == 'C') {
                            char charAt5 = this.msg.charAt(index7);
                            char c6 = charAt5;
                            if (charAt5 == 't' || c6 == 'T') {
                                return true;
                            }
                        }
                    } else {
                        int i = index6;
                    }
                } else {
                    int i2 = index5;
                }
            } else {
                int i3 = index4;
            }
        }
        return false;
    }

    private boolean isOrdinal(int index) {
        int index2 = index + 1;
        char index3 = this.msg.charAt(index);
        char c = index3;
        if (index3 == 'o' || c == 'O') {
            int index4 = index2 + 1;
            char charAt = this.msg.charAt(index2);
            char c2 = charAt;
            if (charAt == 'r' || c2 == 'R') {
                index2 = index4 + 1;
                char charAt2 = this.msg.charAt(index4);
                char c3 = charAt2;
                if (charAt2 == 'd' || c3 == 'D') {
                    int index5 = index2 + 1;
                    char charAt3 = this.msg.charAt(index2);
                    char c4 = charAt3;
                    if (charAt3 == 'i' || c4 == 'I') {
                        index2 = index5 + 1;
                        char charAt4 = this.msg.charAt(index5);
                        char c5 = charAt4;
                        if (charAt4 == 'n' || c5 == 'N') {
                            int index6 = index2 + 1;
                            char charAt5 = this.msg.charAt(index2);
                            char c6 = charAt5;
                            if (charAt5 == 'a' || c6 == 'A') {
                                char charAt6 = this.msg.charAt(index6);
                                char c7 = charAt6;
                                if (charAt6 == 'l' || c7 == 'L') {
                                    return true;
                                }
                                index2 = index6;
                            } else {
                                index2 = index6;
                            }
                        }
                    } else {
                        index2 = index5;
                    }
                }
            } else {
                index2 = index4;
            }
        }
        int i = index2;
        return false;
    }

    private boolean inMessageFormatPattern(int nestingLevel) {
        return nestingLevel > 0 || this.parts.get(0).type == Part.Type.MSG_START;
    }

    private boolean inTopLevelChoiceMessage(int nestingLevel, ArgType parentType) {
        if (nestingLevel == 1 && parentType == ArgType.CHOICE && this.parts.get(0).type != Part.Type.MSG_START) {
            return true;
        }
        return false;
    }

    private void addPart(Part.Type type, int index, int length, int value) {
        this.parts.add(new Part(type, index, length, value));
    }

    private void addLimitPart(int start, Part.Type type, int index, int length, int value) {
        int unused = this.parts.get(start).limitPartIndex = this.parts.size();
        addPart(type, index, length, value);
    }

    private void addArgDoublePart(double numericValue, int start, int length) {
        int numericIndex;
        ArrayList<Double> arrayList = this.numericValues;
        if (arrayList == null) {
            this.numericValues = new ArrayList<>();
            numericIndex = 0;
        } else {
            numericIndex = arrayList.size();
            if (numericIndex > 32767) {
                throw new IndexOutOfBoundsException("Too many numeric values");
            }
        }
        this.numericValues.add(Double.valueOf(numericValue));
        addPart(Part.Type.ARG_DOUBLE, start, length, numericIndex);
    }

    private static String prefix(String s, int start) {
        StringBuilder prefix = new StringBuilder(44);
        if (start == 0) {
            prefix.append("\"");
        } else {
            prefix.append("[at pattern index ").append(start).append("] \"");
        }
        if (s.length() - start <= 24) {
            prefix.append(start == 0 ? s : s.substring(start));
        } else {
            int limit = (start + 24) - 4;
            if (Character.isHighSurrogate(s.charAt(limit - 1))) {
                limit--;
            }
            prefix.append(s, start, limit).append(" ...");
        }
        return prefix.append("\"").toString();
    }

    private static String prefix(String s) {
        return prefix(s, 0);
    }

    private String prefix(int start) {
        return prefix(this.msg, start);
    }

    private String prefix() {
        return prefix(this.msg, 0);
    }
}
