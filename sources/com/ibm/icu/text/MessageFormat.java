package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.MessagePattern;
import com.ibm.icu.text.PluralFormat;
import com.ibm.icu.text.PluralRules;
import com.ibm.icu.util.ICUUncheckedIOException;
import com.ibm.icu.util.ULocale;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;
import java.text.ChoiceFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MessageFormat extends UFormat {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final char CURLY_BRACE_LEFT = '{';
    private static final char CURLY_BRACE_RIGHT = '}';
    private static final int DATE_MODIFIER_EMPTY = 0;
    private static final int DATE_MODIFIER_FULL = 4;
    private static final int DATE_MODIFIER_LONG = 3;
    private static final int DATE_MODIFIER_MEDIUM = 2;
    private static final int DATE_MODIFIER_SHORT = 1;
    private static final int MODIFIER_CURRENCY = 1;
    private static final int MODIFIER_EMPTY = 0;
    private static final int MODIFIER_INTEGER = 3;
    private static final int MODIFIER_PERCENT = 2;
    private static final char SINGLE_QUOTE = '\'';
    private static final int STATE_INITIAL = 0;
    private static final int STATE_IN_QUOTE = 2;
    private static final int STATE_MSG_ELEMENT = 3;
    private static final int STATE_SINGLE_QUOTE = 1;
    private static final int TYPE_DATE = 1;
    private static final int TYPE_DURATION = 5;
    private static final int TYPE_NUMBER = 0;
    private static final int TYPE_ORDINAL = 4;
    private static final int TYPE_SPELLOUT = 3;
    private static final int TYPE_TIME = 2;
    private static final String[] dateModifierList = {"", "short", "medium", "long", "full"};
    private static final String[] modifierList = {"", "currency", "percent", "integer"};
    private static final Locale rootLocale = new Locale("");
    static final long serialVersionUID = 7136212545847378652L;
    private static final String[] typeList = {"number", "date", "time", "spellout", "ordinal", "duration"};
    /* access modifiers changed from: private */
    public transient Map<Integer, Format> cachedFormatters;
    private transient Set<Integer> customFormatArgStarts;
    private transient MessagePattern msgPattern;
    private transient PluralSelectorProvider ordinalProvider;
    private transient PluralSelectorProvider pluralProvider;
    private transient DateFormat stockDateFormatter;
    private transient NumberFormat stockNumberFormatter;
    /* access modifiers changed from: private */
    public transient ULocale ulocale;

    public MessageFormat(String pattern) {
        this.ulocale = ULocale.getDefault(ULocale.Category.FORMAT);
        applyPattern(pattern);
    }

    public MessageFormat(String pattern, Locale locale) {
        this(pattern, ULocale.forLocale(locale));
    }

    public MessageFormat(String pattern, ULocale locale) {
        this.ulocale = locale;
        applyPattern(pattern);
    }

    public void setLocale(Locale locale) {
        setLocale(ULocale.forLocale(locale));
    }

    public void setLocale(ULocale locale) {
        String existingPattern = toPattern();
        this.ulocale = locale;
        this.stockDateFormatter = null;
        this.stockNumberFormatter = null;
        this.pluralProvider = null;
        this.ordinalProvider = null;
        applyPattern(existingPattern);
    }

    public Locale getLocale() {
        return this.ulocale.toLocale();
    }

    public ULocale getULocale() {
        return this.ulocale;
    }

    public void applyPattern(String pttrn) {
        try {
            MessagePattern messagePattern = this.msgPattern;
            if (messagePattern == null) {
                this.msgPattern = new MessagePattern(pttrn);
            } else {
                messagePattern.parse(pttrn);
            }
            cacheExplicitFormats();
        } catch (RuntimeException e) {
            resetPattern();
            throw e;
        }
    }

    public void applyPattern(String pattern, MessagePattern.ApostropheMode aposMode) {
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern == null) {
            this.msgPattern = new MessagePattern(aposMode);
        } else if (aposMode != messagePattern.getApostropheMode()) {
            this.msgPattern.clearPatternAndSetApostropheMode(aposMode);
        }
        applyPattern(pattern);
    }

    public MessagePattern.ApostropheMode getApostropheMode() {
        if (this.msgPattern == null) {
            this.msgPattern = new MessagePattern();
        }
        return this.msgPattern.getApostropheMode();
    }

    public String toPattern() {
        String originalPattern;
        if (this.customFormatArgStarts == null) {
            MessagePattern messagePattern = this.msgPattern;
            if (messagePattern == null || (originalPattern = messagePattern.getPatternString()) == null) {
                return "";
            }
            return originalPattern;
        }
        throw new IllegalStateException("toPattern() is not supported after custom Format objects have been set via setFormat() or similar APIs");
    }

    private int nextTopLevelArgStart(int partIndex) {
        MessagePattern.Part.Type type;
        if (partIndex != 0) {
            partIndex = this.msgPattern.getLimitPartIndex(partIndex);
        }
        do {
            partIndex++;
            type = this.msgPattern.getPartType(partIndex);
            if (type == MessagePattern.Part.Type.ARG_START) {
                return partIndex;
            }
        } while (type != MessagePattern.Part.Type.MSG_LIMIT);
        return -1;
    }

    private boolean argNameMatches(int partIndex, String argName, int argNumber) {
        MessagePattern.Part part = this.msgPattern.getPart(partIndex);
        if (part.getType() == MessagePattern.Part.Type.ARG_NAME) {
            return this.msgPattern.partSubstringMatches(part, argName);
        }
        return part.getValue() == argNumber;
    }

    private String getArgName(int partIndex) {
        MessagePattern.Part part = this.msgPattern.getPart(partIndex);
        if (part.getType() == MessagePattern.Part.Type.ARG_NAME) {
            return this.msgPattern.getSubstring(part);
        }
        return Integer.toString(part.getValue());
    }

    public void setFormatsByArgumentIndex(Format[] newFormats) {
        if (!this.msgPattern.hasNamedArguments()) {
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart >= 0) {
                    int argNumber = this.msgPattern.getPart(partIndex + 1).getValue();
                    if (argNumber < newFormats.length) {
                        setCustomArgStartFormat(partIndex, newFormats[argNumber]);
                    }
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("This method is not available in MessageFormat objects that use alphanumeric argument names.");
        }
    }

    public void setFormatsByArgumentName(Map<String, Format> newFormats) {
        int partIndex = 0;
        while (true) {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart >= 0) {
                String key = getArgName(partIndex + 1);
                if (newFormats.containsKey(key)) {
                    setCustomArgStartFormat(partIndex, newFormats.get(key));
                }
            } else {
                return;
            }
        }
    }

    public void setFormats(Format[] newFormats) {
        int formatNumber = 0;
        int partIndex = 0;
        while (formatNumber < newFormats.length) {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart >= 0) {
                setCustomArgStartFormat(partIndex, newFormats[formatNumber]);
                formatNumber++;
            } else {
                return;
            }
        }
    }

    public void setFormatByArgumentIndex(int argumentIndex, Format newFormat) {
        if (!this.msgPattern.hasNamedArguments()) {
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart < 0) {
                    return;
                }
                if (this.msgPattern.getPart(partIndex + 1).getValue() == argumentIndex) {
                    setCustomArgStartFormat(partIndex, newFormat);
                }
            }
        } else {
            throw new IllegalArgumentException("This method is not available in MessageFormat objects that use alphanumeric argument names.");
        }
    }

    public void setFormatByArgumentName(String argumentName, Format newFormat) {
        int argNumber = MessagePattern.validateArgumentName(argumentName);
        if (argNumber >= -1) {
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart < 0) {
                    return;
                }
                if (argNameMatches(partIndex + 1, argumentName, argNumber)) {
                    setCustomArgStartFormat(partIndex, newFormat);
                }
            }
        }
    }

    public void setFormat(int formatElementIndex, Format newFormat) {
        int formatNumber = 0;
        int partIndex = 0;
        while (true) {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart < 0) {
                throw new ArrayIndexOutOfBoundsException(formatElementIndex);
            } else if (formatNumber == formatElementIndex) {
                setCustomArgStartFormat(partIndex, newFormat);
                return;
            } else {
                formatNumber++;
            }
        }
    }

    public Format[] getFormatsByArgumentIndex() {
        Format format;
        if (!this.msgPattern.hasNamedArguments()) {
            ArrayList<Format> list = new ArrayList<>();
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart < 0) {
                    return (Format[]) list.toArray(new Format[list.size()]);
                }
                int argNumber = this.msgPattern.getPart(partIndex + 1).getValue();
                while (true) {
                    format = null;
                    if (argNumber < list.size()) {
                        break;
                    }
                    list.add((Object) null);
                }
                Map<Integer, Format> map = this.cachedFormatters;
                if (map != null) {
                    format = map.get(Integer.valueOf(partIndex));
                }
                list.set(argNumber, format);
            }
        } else {
            throw new IllegalArgumentException("This method is not available in MessageFormat objects that use alphanumeric argument names.");
        }
    }

    public Format[] getFormats() {
        ArrayList<Format> list = new ArrayList<>();
        int partIndex = 0;
        while (true) {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart < 0) {
                return (Format[]) list.toArray(new Format[list.size()]);
            }
            Map<Integer, Format> map = this.cachedFormatters;
            list.add(map == null ? null : map.get(Integer.valueOf(partIndex)));
        }
    }

    public Set<String> getArgumentNames() {
        Set<String> result = new HashSet<>();
        int partIndex = 0;
        while (true) {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart < 0) {
                return result;
            }
            result.add(getArgName(partIndex + 1));
        }
    }

    public Format getFormatByArgumentName(String argumentName) {
        int argNumber;
        if (this.cachedFormatters == null || (argNumber = MessagePattern.validateArgumentName(argumentName)) < -1) {
            return null;
        }
        int partIndex = 0;
        do {
            int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
            partIndex = nextTopLevelArgStart;
            if (nextTopLevelArgStart < 0) {
                return null;
            }
        } while (!argNameMatches(partIndex + 1, argumentName, argNumber));
        return this.cachedFormatters.get(Integer.valueOf(partIndex));
    }

    public final StringBuffer format(Object[] arguments, StringBuffer result, FieldPosition pos) {
        format(arguments, (Map<String, Object>) null, new AppendableWrapper(result), pos);
        return result;
    }

    public final StringBuffer format(Map<String, Object> arguments, StringBuffer result, FieldPosition pos) {
        format((Object[]) null, arguments, new AppendableWrapper(result), pos);
        return result;
    }

    public static String format(String pattern, Object... arguments) {
        return new MessageFormat(pattern).format(arguments);
    }

    public static String format(String pattern, Map<String, Object> arguments) {
        return new MessageFormat(pattern).format(arguments);
    }

    public boolean usesNamedArguments() {
        return this.msgPattern.hasNamedArguments();
    }

    public final StringBuffer format(Object arguments, StringBuffer result, FieldPosition pos) {
        format(arguments, new AppendableWrapper(result), pos);
        return result;
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object arguments) {
        if (arguments != null) {
            StringBuilder result = new StringBuilder();
            AppendableWrapper wrapper = new AppendableWrapper(result);
            wrapper.useAttributes();
            format(arguments, wrapper, (FieldPosition) null);
            AttributedString as = new AttributedString(result.toString());
            for (AttributeAndPosition a : wrapper.attributes) {
                as.addAttribute(a.key, a.value, a.start, a.limit);
            }
            return as.getIterator();
        }
        throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
    }

    public Object[] parse(String source, ParsePosition pos) {
        if (!this.msgPattern.hasNamedArguments()) {
            int maxArgId = -1;
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart < 0) {
                    break;
                }
                int argNumber = this.msgPattern.getPart(partIndex + 1).getValue();
                if (argNumber > maxArgId) {
                    maxArgId = argNumber;
                }
            }
            Object[] resultArray = new Object[(maxArgId + 1)];
            int backupStartPos = pos.getIndex();
            parse(0, source, pos, resultArray, (Map<String, Object>) null);
            if (pos.getIndex() == backupStartPos) {
                return null;
            }
            return resultArray;
        }
        throw new IllegalArgumentException("This method is not available in MessageFormat objects that use named argument.");
    }

    public Map<String, Object> parseToMap(String source, ParsePosition pos) {
        HashMap hashMap = new HashMap();
        int backupStartPos = pos.getIndex();
        parse(0, source, pos, (Object[]) null, hashMap);
        if (pos.getIndex() == backupStartPos) {
            return null;
        }
        return hashMap;
    }

    public Object[] parse(String source) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        Object[] result = parse(source, pos);
        if (pos.getIndex() != 0) {
            return result;
        }
        throw new ParseException("MessageFormat parse error!", pos.getErrorIndex());
    }

    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parse(int r27, java.lang.String r28, java.text.ParsePosition r29, java.lang.Object[] r30, java.util.Map<java.lang.String, java.lang.Object> r31) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            r2 = r28
            r3 = r29
            r4 = r31
            if (r2 != 0) goto L_0x000d
            return
        L_0x000d:
            com.ibm.icu.text.MessagePattern r5 = r0.msgPattern
            java.lang.String r5 = r5.getPatternString()
            com.ibm.icu.text.MessagePattern r6 = r0.msgPattern
            com.ibm.icu.text.MessagePattern$Part r6 = r6.getPart(r1)
            int r6 = r6.getLimit()
            int r7 = r29.getIndex()
            java.text.ParsePosition r8 = new java.text.ParsePosition
            r9 = 0
            r8.<init>(r9)
            int r9 = r1 + 1
        L_0x0029:
            com.ibm.icu.text.MessagePattern r10 = r0.msgPattern
            com.ibm.icu.text.MessagePattern$Part r10 = r10.getPart(r9)
            com.ibm.icu.text.MessagePattern$Part$Type r11 = r10.getType()
            int r12 = r10.getIndex()
            int r13 = r12 - r6
            if (r13 == 0) goto L_0x0046
            boolean r14 = r5.regionMatches(r6, r2, r7, r13)
            if (r14 == 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            r3.setErrorIndex(r7)
            return
        L_0x0046:
            int r7 = r7 + r13
            int r6 = r6 + r13
            com.ibm.icu.text.MessagePattern$Part$Type r14 = com.ibm.icu.text.MessagePattern.Part.Type.MSG_LIMIT
            if (r11 != r14) goto L_0x0050
            r3.setIndex(r7)
            return
        L_0x0050:
            com.ibm.icu.text.MessagePattern$Part$Type r14 = com.ibm.icu.text.MessagePattern.Part.Type.SKIP_SYNTAX
            if (r11 == r14) goto L_0x01ed
            com.ibm.icu.text.MessagePattern$Part$Type r14 = com.ibm.icu.text.MessagePattern.Part.Type.INSERT_CHAR
            if (r11 != r14) goto L_0x0060
            r18 = r5
            r19 = r6
            r22 = r8
            goto L_0x01f3
        L_0x0060:
            com.ibm.icu.text.MessagePattern$Part$Type r14 = com.ibm.icu.text.MessagePattern.Part.Type.ARG_START
            if (r11 != r14) goto L_0x01cc
            com.ibm.icu.text.MessagePattern r14 = r0.msgPattern
            int r14 = r14.getLimitPartIndex(r9)
            com.ibm.icu.text.MessagePattern$ArgType r15 = r10.getArgType()
            com.ibm.icu.text.MessagePattern r1 = r0.msgPattern
            int r9 = r9 + 1
            com.ibm.icu.text.MessagePattern$Part r1 = r1.getPart(r9)
            r10 = 0
            r16 = 0
            r17 = 0
            if (r30 == 0) goto L_0x008c
            int r16 = r1.getValue()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r16)
            r18 = r5
            r19 = r6
            r5 = r17
            goto L_0x00af
        L_0x008c:
            r18 = r5
            com.ibm.icu.text.MessagePattern$Part$Type r5 = r1.getType()
            r19 = r6
            com.ibm.icu.text.MessagePattern$Part$Type r6 = com.ibm.icu.text.MessagePattern.Part.Type.ARG_NAME
            if (r5 != r6) goto L_0x00a1
            com.ibm.icu.text.MessagePattern r5 = r0.msgPattern
            java.lang.String r5 = r5.getSubstring(r1)
            r17 = r5
            goto L_0x00ab
        L_0x00a1:
            int r5 = r1.getValue()
            java.lang.String r5 = java.lang.Integer.toString(r5)
            r17 = r5
        L_0x00ab:
            r10 = r17
            r5 = r17
        L_0x00af:
            int r9 = r9 + 1
            r6 = 0
            r17 = 0
            r20 = 0
            r21 = r1
            java.util.Map<java.lang.Integer, java.text.Format> r1 = r0.cachedFormatters
            if (r1 == 0) goto L_0x00eb
            int r22 = r9 + -2
            r23 = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r22)
            java.lang.Object r1 = r1.get(r6)
            java.text.Format r1 = (java.text.Format) r1
            r6 = r1
            if (r1 == 0) goto L_0x00ed
            r8.setIndex(r7)
            java.lang.Object r1 = r6.parseObject(r2, r8)
            r20 = r1
            int r1 = r8.getIndex()
            if (r1 != r7) goto L_0x00e0
            r3.setErrorIndex(r7)
            return
        L_0x00e0:
            r1 = 1
            int r7 = r8.getIndex()
            r22 = r8
            r2 = r20
            goto L_0x01b3
        L_0x00eb:
            r23 = r6
        L_0x00ed:
            com.ibm.icu.text.MessagePattern$ArgType r1 = com.ibm.icu.text.MessagePattern.ArgType.NONE
            if (r15 == r1) goto L_0x0162
            java.util.Map<java.lang.Integer, java.text.Format> r1 = r0.cachedFormatters
            if (r1 == 0) goto L_0x0106
            int r22 = r9 + -2
            r23 = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r22)
            boolean r1 = r1.containsKey(r6)
            if (r1 == 0) goto L_0x0108
            r22 = r8
            goto L_0x0166
        L_0x0106:
            r23 = r6
        L_0x0108:
            com.ibm.icu.text.MessagePattern$ArgType r1 = com.ibm.icu.text.MessagePattern.ArgType.CHOICE
            if (r15 != r1) goto L_0x0130
            r8.setIndex(r7)
            com.ibm.icu.text.MessagePattern r1 = r0.msgPattern
            double r24 = parseChoiceArgument(r1, r9, r2, r8)
            int r1 = r8.getIndex()
            if (r1 != r7) goto L_0x011f
            r3.setErrorIndex(r7)
            return
        L_0x011f:
            java.lang.Double r1 = java.lang.Double.valueOf(r24)
            r6 = 1
            int r7 = r8.getIndex()
            r2 = r1
            r1 = r6
            r22 = r8
            r6 = r23
            goto L_0x01b3
        L_0x0130:
            boolean r1 = r15.hasPluralStyle()
            if (r1 != 0) goto L_0x0158
            com.ibm.icu.text.MessagePattern$ArgType r1 = com.ibm.icu.text.MessagePattern.ArgType.SELECT
            if (r15 != r1) goto L_0x013d
            r22 = r8
            goto L_0x015a
        L_0x013d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r22 = r8
            java.lang.String r8 = "unexpected argType "
            java.lang.StringBuilder r6 = r6.append(r8)
            java.lang.StringBuilder r6 = r6.append(r15)
            java.lang.String r6 = r6.toString()
            r1.<init>(r6)
            throw r1
        L_0x0158:
            r22 = r8
        L_0x015a:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.String r6 = "Parsing of plural/select/selectordinal argument is not supported."
            r1.<init>(r6)
            throw r1
        L_0x0162:
            r23 = r6
            r22 = r8
        L_0x0166:
            java.lang.String r1 = r0.getLiteralStringUntilNextArgument(r14)
            int r6 = r1.length()
            if (r6 == 0) goto L_0x0175
            int r6 = r2.indexOf(r1, r7)
            goto L_0x0179
        L_0x0175:
            int r6 = r28.length()
        L_0x0179:
            if (r6 >= 0) goto L_0x017f
            r3.setErrorIndex(r7)
            return
        L_0x017f:
            java.lang.String r8 = r2.substring(r7, r6)
            r24 = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "{"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r10.toString()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "}"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            boolean r1 = r8.equals(r1)
            if (r1 != 0) goto L_0x01ac
            r17 = 1
            r20 = r8
        L_0x01ac:
            r7 = r6
            r1 = r17
            r2 = r20
            r6 = r23
        L_0x01b3:
            if (r1 == 0) goto L_0x01bf
            if (r30 == 0) goto L_0x01ba
            r30[r16] = r2
            goto L_0x01bf
        L_0x01ba:
            if (r4 == 0) goto L_0x01bf
            r4.put(r5, r2)
        L_0x01bf:
            com.ibm.icu.text.MessagePattern r8 = r0.msgPattern
            com.ibm.icu.text.MessagePattern$Part r8 = r8.getPart(r14)
            int r8 = r8.getLimit()
            r9 = r14
            r6 = r8
            goto L_0x01f8
        L_0x01cc:
            r18 = r5
            java.lang.AssertionError r1 = new java.lang.AssertionError
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "Unexpected Part "
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r5 = " in parsed message."
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x01ed:
            r18 = r5
            r19 = r6
            r22 = r8
        L_0x01f3:
            int r1 = r10.getLimit()
            r6 = r1
        L_0x01f8:
            int r9 = r9 + 1
            r1 = r27
            r2 = r28
            r5 = r18
            r8 = r22
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ibm.icu.text.MessageFormat.parse(int, java.lang.String, java.text.ParsePosition, java.lang.Object[], java.util.Map):void");
    }

    public Map<String, Object> parseToMap(String source) throws ParseException {
        ParsePosition pos = new ParsePosition(0);
        Map<String, Object> result = new HashMap<>();
        parse(0, source, pos, (Object[]) null, result);
        if (pos.getIndex() != 0) {
            return result;
        }
        throw new ParseException("MessageFormat parse error!", pos.getErrorIndex());
    }

    public Object parseObject(String source, ParsePosition pos) {
        if (!this.msgPattern.hasNamedArguments()) {
            return parse(source, pos);
        }
        return parseToMap(source, pos);
    }

    public Object clone() {
        DateFormat dateFormat;
        NumberFormat numberFormat;
        MessageFormat other = (MessageFormat) super.clone();
        if (this.customFormatArgStarts != null) {
            other.customFormatArgStarts = new HashSet();
            for (Integer key : this.customFormatArgStarts) {
                other.customFormatArgStarts.add(key);
            }
        } else {
            other.customFormatArgStarts = null;
        }
        if (this.cachedFormatters != null) {
            other.cachedFormatters = new HashMap();
            for (Map.Entry<Integer, Format> entry : this.cachedFormatters.entrySet()) {
                other.cachedFormatters.put(entry.getKey(), entry.getValue());
            }
        } else {
            other.cachedFormatters = null;
        }
        MessagePattern messagePattern = this.msgPattern;
        other.msgPattern = messagePattern == null ? null : (MessagePattern) messagePattern.clone();
        DateFormat dateFormat2 = this.stockDateFormatter;
        if (dateFormat2 == null) {
            dateFormat = null;
        } else {
            dateFormat = (DateFormat) dateFormat2.clone();
        }
        other.stockDateFormatter = dateFormat;
        NumberFormat numberFormat2 = this.stockNumberFormatter;
        if (numberFormat2 == null) {
            numberFormat = null;
        } else {
            numberFormat = (NumberFormat) numberFormat2.clone();
        }
        other.stockNumberFormatter = numberFormat;
        other.pluralProvider = null;
        other.ordinalProvider = null;
        return other;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageFormat other = (MessageFormat) obj;
        if (!Objects.equals(this.ulocale, other.ulocale) || !Objects.equals(this.msgPattern, other.msgPattern) || !Objects.equals(this.cachedFormatters, other.cachedFormatters) || !Objects.equals(this.customFormatArgStarts, other.customFormatArgStarts)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.msgPattern.getPatternString().hashCode();
    }

    public static class Field extends Format.Field {
        public static final Field ARGUMENT = new Field("message argument field");
        private static final long serialVersionUID = 7510380454602616157L;

        protected Field(String name) {
            super(name);
        }

        /* access modifiers changed from: protected */
        public Object readResolve() throws InvalidObjectException {
            if (getClass() == Field.class) {
                String name = getName();
                Field field = ARGUMENT;
                if (name.equals(field.getName())) {
                    return field;
                }
                throw new InvalidObjectException("Unknown attribute name.");
            }
            throw new InvalidObjectException("A subclass of MessageFormat.Field must implement readResolve.");
        }
    }

    private DateFormat getStockDateFormatter() {
        if (this.stockDateFormatter == null) {
            this.stockDateFormatter = DateFormat.getDateTimeInstance(3, 3, this.ulocale);
        }
        return this.stockDateFormatter;
    }

    /* access modifiers changed from: private */
    public NumberFormat getStockNumberFormatter() {
        if (this.stockNumberFormatter == null) {
            this.stockNumberFormatter = NumberFormat.getInstance(this.ulocale);
        }
        return this.stockNumberFormatter;
    }

    private void format(int msgStart, PluralSelectorContext pluralNumber, Object[] args, Map<String, Object> argsMap, AppendableWrapper dest, FieldPosition fp) {
        String msgString;
        AppendableWrapper appendableWrapper;
        boolean noArg;
        String msgString2;
        FieldPosition fp2;
        Object arg;
        Object argId;
        int prevDestLength;
        int argLimit;
        Object argId2;
        FieldPosition fp3;
        String argName;
        Object argId3;
        MessagePattern.ArgType argType;
        int i;
        Format formatter;
        Object arg2;
        PluralSelectorProvider selector;
        int prevDestLength2;
        MessagePattern.ArgType argType2;
        int i2;
        int i3 = msgStart;
        PluralSelectorContext pluralSelectorContext = pluralNumber;
        Object[] objArr = args;
        Map<String, Object> map = argsMap;
        AppendableWrapper appendableWrapper2 = dest;
        String msgString3 = this.msgPattern.getPatternString();
        int prevDestLength3 = this.msgPattern.getPart(i3).getLimit();
        int i4 = i3 + 1;
        FieldPosition fp4 = fp;
        while (true) {
            MessagePattern.Part part = this.msgPattern.getPart(i4);
            MessagePattern.Part.Type type = part.getType();
            int index = part.getIndex();
            appendableWrapper2.append(msgString3, prevDestLength3, index);
            if (type != MessagePattern.Part.Type.MSG_LIMIT) {
                int prevIndex = part.getLimit();
                if (type == MessagePattern.Part.Type.REPLACE_NUMBER) {
                    if (pluralSelectorContext.forReplaceNumber) {
                        appendableWrapper2.formatAndAppend(pluralSelectorContext.formatter, pluralSelectorContext.number, pluralSelectorContext.numberString);
                    } else {
                        appendableWrapper2.formatAndAppend(getStockNumberFormatter(), pluralSelectorContext.number);
                    }
                } else if (type == MessagePattern.Part.Type.ARG_START) {
                    int argLimit2 = this.msgPattern.getLimitPartIndex(i4);
                    MessagePattern.ArgType argType3 = part.getArgType();
                    int i5 = i4 + 1;
                    MessagePattern.Part part2 = this.msgPattern.getPart(i5);
                    boolean noArg2 = false;
                    Object argId4 = null;
                    int argLimit3 = argLimit2;
                    String argName2 = this.msgPattern.getSubstring(part2);
                    if (objArr != null) {
                        fp2 = fp4;
                        int argNumber = part2.getValue();
                        if (dest.attributes != null) {
                            argId4 = Integer.valueOf(argNumber);
                        }
                        if (argNumber >= 0) {
                            msgString2 = msgString3;
                            if (argNumber < objArr.length) {
                                arg = objArr[argNumber];
                                noArg = noArg2;
                                argId = argId4;
                            }
                        } else {
                            msgString2 = msgString3;
                        }
                        arg = null;
                        noArg2 = true;
                        noArg = noArg2;
                        argId = argId4;
                    } else {
                        fp2 = fp4;
                        msgString2 = msgString3;
                        Object argId5 = argName2;
                        if (map == null || !map.containsKey(argName2)) {
                            arg = null;
                            noArg = true;
                            argId = argId5;
                        } else {
                            arg = map.get(argName2);
                            noArg = false;
                            argId = argId5;
                        }
                    }
                    int i6 = i5 + 1;
                    int prevDestLength4 = dest.length;
                    Format formatter2 = null;
                    if (noArg) {
                        argId3 = argId;
                        appendableWrapper2.append((CharSequence) "{" + argName2 + "}");
                        argName = argName2;
                    } else {
                        argId3 = argId;
                        if (arg == null) {
                            appendableWrapper2.append((CharSequence) "null");
                            argName = argName2;
                        } else if (pluralSelectorContext == null || pluralSelectorContext.numberArgIndex != i6 - 2) {
                            String argName3 = argName2;
                            Map<Integer, Format> map2 = this.cachedFormatters;
                            if (map2 != null) {
                                Format format = map2.get(Integer.valueOf(i6 - 2));
                                Format formatter3 = format;
                                if (format != null) {
                                    if ((formatter3 instanceof ChoiceFormat) || (formatter3 instanceof PluralFormat) || (formatter3 instanceof SelectFormat)) {
                                        String subMsgString = formatter3.format(arg);
                                        if (subMsgString.indexOf(123) >= 0 || (subMsgString.indexOf(39) >= 0 && !this.msgPattern.jdkAposMode())) {
                                            prevDestLength = prevDestLength4;
                                            i2 = i6;
                                            MessagePattern.Part part3 = part2;
                                            argType2 = argType3;
                                            int i7 = index;
                                            MessagePattern.Part.Type type2 = type;
                                            new MessageFormat(subMsgString, this.ulocale).format(0, (PluralSelectorContext) null, args, argsMap, dest, (FieldPosition) null);
                                        } else if (dest.attributes == null) {
                                            appendableWrapper2.append((CharSequence) subMsgString);
                                            prevDestLength = prevDestLength4;
                                            i2 = i6;
                                            MessagePattern.Part part4 = part2;
                                            argType2 = argType3;
                                            int i8 = index;
                                            MessagePattern.Part.Type type3 = type;
                                        } else {
                                            appendableWrapper2.formatAndAppend(formatter3, arg);
                                            prevDestLength = prevDestLength4;
                                            i2 = i6;
                                            MessagePattern.Part part5 = part2;
                                            argType2 = argType3;
                                            int i9 = index;
                                            MessagePattern.Part.Type type4 = type;
                                        }
                                    } else {
                                        appendableWrapper2.formatAndAppend(formatter3, arg);
                                        prevDestLength = prevDestLength4;
                                        i2 = i6;
                                        MessagePattern.Part part6 = part2;
                                        argType2 = argType3;
                                        int i10 = index;
                                        MessagePattern.Part.Type type5 = type;
                                    }
                                    argLimit = argLimit3;
                                    Object obj = arg;
                                    appendableWrapper = appendableWrapper2;
                                    int i11 = i2;
                                    fp3 = fp2;
                                    msgString = msgString2;
                                    argId2 = argId3;
                                    MessagePattern.ArgType argType4 = argType2;
                                    String str = argName3;
                                    fp4 = updateMetaData(appendableWrapper, prevDestLength, fp3, argId2);
                                    prevDestLength3 = this.msgPattern.getPart(argLimit).getLimit();
                                    i4 = argLimit;
                                    i4++;
                                    int i12 = msgStart;
                                    pluralSelectorContext = pluralNumber;
                                    objArr = args;
                                    map = argsMap;
                                    appendableWrapper2 = appendableWrapper;
                                    msgString3 = msgString;
                                } else {
                                    prevDestLength2 = prevDestLength4;
                                    i = i6;
                                    MessagePattern.Part part7 = part2;
                                    argType = argType3;
                                    int i13 = index;
                                    MessagePattern.Part.Type type6 = type;
                                    formatter2 = formatter3;
                                }
                            } else {
                                prevDestLength2 = prevDestLength4;
                                i = i6;
                                MessagePattern.Part part8 = part2;
                                argType = argType3;
                                int i14 = index;
                                MessagePattern.Part.Type type7 = type;
                            }
                            MessagePattern.ArgType argType5 = argType;
                            if (argType5 != MessagePattern.ArgType.NONE) {
                                Map<Integer, Format> map3 = this.cachedFormatters;
                                if (map3 == null || !map3.containsKey(Integer.valueOf(i - 2))) {
                                    if (argType5 != MessagePattern.ArgType.CHOICE) {
                                        argLimit = argLimit3;
                                        appendableWrapper = appendableWrapper2;
                                        int i15 = i;
                                        formatter = formatter2;
                                        fp3 = fp2;
                                        msgString = msgString2;
                                        argId2 = argId3;
                                        String argName4 = argName3;
                                        Object arg3 = arg;
                                        if (!argType5.hasPluralStyle()) {
                                            int i16 = i15;
                                            if (argType5 == MessagePattern.ArgType.SELECT) {
                                                formatComplexSubMessage(SelectFormat.findSubMessage(this.msgPattern, i16, arg3.toString()), (PluralSelectorContext) null, args, argsMap, dest);
                                            } else {
                                                throw new IllegalStateException("unexpected argType " + argType5);
                                            }
                                        } else if (arg3 instanceof Number) {
                                            if (argType5 == MessagePattern.ArgType.PLURAL) {
                                                if (this.pluralProvider == null) {
                                                    this.pluralProvider = new PluralSelectorProvider(this, PluralRules.PluralType.CARDINAL);
                                                }
                                                selector = this.pluralProvider;
                                            } else {
                                                if (this.ordinalProvider == null) {
                                                    this.ordinalProvider = new PluralSelectorProvider(this, PluralRules.PluralType.ORDINAL);
                                                }
                                                selector = this.ordinalProvider;
                                            }
                                            Number number = (Number) arg3;
                                            int i17 = i15;
                                            PluralSelectorContext pluralSelectorContext2 = new PluralSelectorContext(i17, argName4, number, this.msgPattern.getPluralOffset(i17));
                                            formatComplexSubMessage(PluralFormat.findSubMessage(this.msgPattern, i17, selector, pluralSelectorContext2, number.doubleValue()), pluralSelectorContext2, args, argsMap, dest);
                                        } else {
                                            throw new IllegalArgumentException("'" + arg3 + "' is not a Number");
                                        }
                                    } else if (arg instanceof Number) {
                                        argLimit = argLimit3;
                                        String str2 = argName3;
                                        fp3 = fp2;
                                        argId2 = argId3;
                                        formatter = formatter2;
                                        msgString = msgString2;
                                        Object obj2 = arg;
                                        appendableWrapper = appendableWrapper2;
                                        formatComplexSubMessage(findChoiceSubMessage(this.msgPattern, i, ((Number) arg).doubleValue()), (PluralSelectorContext) null, args, argsMap, dest);
                                        int subMsgStart = i;
                                    } else {
                                        Format format2 = formatter2;
                                        throw new IllegalArgumentException("'" + arg + "' is not a Number");
                                    }
                                    Format format3 = formatter;
                                    fp4 = updateMetaData(appendableWrapper, prevDestLength, fp3, argId2);
                                    prevDestLength3 = this.msgPattern.getPart(argLimit).getLimit();
                                    i4 = argLimit;
                                    i4++;
                                    int i122 = msgStart;
                                    pluralSelectorContext = pluralNumber;
                                    objArr = args;
                                    map = argsMap;
                                    appendableWrapper2 = appendableWrapper;
                                    msgString3 = msgString;
                                } else {
                                    argLimit = argLimit3;
                                    appendableWrapper = appendableWrapper2;
                                    int i18 = i;
                                    formatter = formatter2;
                                    fp3 = fp2;
                                    msgString = msgString2;
                                    argId2 = argId3;
                                    String str3 = argName3;
                                    arg2 = arg;
                                }
                            } else {
                                argLimit = argLimit3;
                                appendableWrapper = appendableWrapper2;
                                int i19 = i;
                                formatter = formatter2;
                                fp3 = fp2;
                                msgString = msgString2;
                                argId2 = argId3;
                                String str4 = argName3;
                                arg2 = arg;
                            }
                            if (arg2 instanceof Number) {
                                appendableWrapper.formatAndAppend(getStockNumberFormatter(), arg2);
                            } else if (arg2 instanceof Date) {
                                appendableWrapper.formatAndAppend(getStockDateFormatter(), arg2);
                            } else {
                                appendableWrapper.append((CharSequence) arg2.toString());
                            }
                            Format format32 = formatter;
                            fp4 = updateMetaData(appendableWrapper, prevDestLength, fp3, argId2);
                            prevDestLength3 = this.msgPattern.getPart(argLimit).getLimit();
                            i4 = argLimit;
                            i4++;
                            int i1222 = msgStart;
                            pluralSelectorContext = pluralNumber;
                            objArr = args;
                            map = argsMap;
                            appendableWrapper2 = appendableWrapper;
                            msgString3 = msgString;
                        } else {
                            String argName5 = argName2;
                            if (pluralSelectorContext.offset == 0.0d) {
                                argName = argName5;
                                appendableWrapper2.formatAndAppend(pluralSelectorContext.formatter, pluralSelectorContext.number, pluralSelectorContext.numberString);
                            } else {
                                argName = argName5;
                                appendableWrapper2.formatAndAppend(pluralSelectorContext.formatter, arg);
                            }
                        }
                    }
                    prevDestLength = prevDestLength4;
                    MessagePattern.Part part9 = part2;
                    MessagePattern.ArgType argType6 = argType3;
                    int i20 = index;
                    MessagePattern.Part.Type type8 = type;
                    fp3 = fp2;
                    msgString = msgString2;
                    argId2 = argId3;
                    String str5 = argName;
                    argLimit = argLimit3;
                    Object obj3 = arg;
                    appendableWrapper = appendableWrapper2;
                    int i21 = i6;
                    fp4 = updateMetaData(appendableWrapper, prevDestLength, fp3, argId2);
                    prevDestLength3 = this.msgPattern.getPart(argLimit).getLimit();
                    i4 = argLimit;
                    i4++;
                    int i12222 = msgStart;
                    pluralSelectorContext = pluralNumber;
                    objArr = args;
                    map = argsMap;
                    appendableWrapper2 = appendableWrapper;
                    msgString3 = msgString;
                }
                msgString = msgString3;
                appendableWrapper = appendableWrapper2;
                prevDestLength3 = prevIndex;
                i4++;
                int i122222 = msgStart;
                pluralSelectorContext = pluralNumber;
                objArr = args;
                map = argsMap;
                appendableWrapper2 = appendableWrapper;
                msgString3 = msgString;
            } else {
                return;
            }
        }
    }

    private void formatComplexSubMessage(int msgStart, PluralSelectorContext pluralNumber, Object[] args, Map<String, Object> argsMap, AppendableWrapper dest) {
        int index;
        String subMsgString;
        PluralSelectorContext pluralSelectorContext = pluralNumber;
        if (!this.msgPattern.jdkAposMode()) {
            format(msgStart, pluralNumber, args, argsMap, dest, (FieldPosition) null);
            return;
        }
        String msgString = this.msgPattern.getPatternString();
        StringBuilder sb = null;
        int prevIndex = this.msgPattern.getPart(msgStart).getLimit();
        int i = msgStart;
        while (true) {
            i++;
            MessagePattern.Part part = this.msgPattern.getPart(i);
            MessagePattern.Part.Type type = part.getType();
            index = part.getIndex();
            if (type == MessagePattern.Part.Type.MSG_LIMIT) {
                break;
            }
            AppendableWrapper appendableWrapper = dest;
            if (type == MessagePattern.Part.Type.REPLACE_NUMBER || type == MessagePattern.Part.Type.SKIP_SYNTAX) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(msgString, prevIndex, index);
                if (type == MessagePattern.Part.Type.REPLACE_NUMBER) {
                    if (pluralSelectorContext.forReplaceNumber) {
                        sb.append(pluralSelectorContext.numberString);
                    } else {
                        sb.append(getStockNumberFormatter().format(pluralSelectorContext.number));
                    }
                }
                prevIndex = part.getLimit();
            } else if (type == MessagePattern.Part.Type.ARG_START) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(msgString, prevIndex, index);
                int prevIndex2 = index;
                i = this.msgPattern.getLimitPartIndex(i);
                int index2 = this.msgPattern.getPart(i).getLimit();
                MessagePattern.appendReducedApostrophes(msgString, prevIndex2, index2, sb);
                prevIndex = index2;
            }
        }
        if (sb == null) {
            subMsgString = msgString.substring(prevIndex, index);
        } else {
            subMsgString = sb.append(msgString, prevIndex, index).toString();
        }
        if (subMsgString.indexOf(123) >= 0) {
            MessageFormat subMsgFormat = new MessageFormat("", this.ulocale);
            subMsgFormat.applyPattern(subMsgString, MessagePattern.ApostropheMode.DOUBLE_REQUIRED);
            subMsgFormat.format(0, (PluralSelectorContext) null, args, argsMap, dest, (FieldPosition) null);
            AppendableWrapper appendableWrapper2 = dest;
            return;
        }
        dest.append((CharSequence) subMsgString);
    }

    private String getLiteralStringUntilNextArgument(int from) {
        StringBuilder b = new StringBuilder();
        String msgString = this.msgPattern.getPatternString();
        int prevIndex = this.msgPattern.getPart(from).getLimit();
        int i = from + 1;
        while (true) {
            MessagePattern.Part part = this.msgPattern.getPart(i);
            MessagePattern.Part.Type type = part.getType();
            b.append(msgString, prevIndex, part.getIndex());
            if (type != MessagePattern.Part.Type.ARG_START && type != MessagePattern.Part.Type.MSG_LIMIT) {
                if (type == MessagePattern.Part.Type.SKIP_SYNTAX || type == MessagePattern.Part.Type.INSERT_CHAR) {
                    prevIndex = part.getLimit();
                    i++;
                } else {
                    throw new AssertionError("Unexpected Part " + part + " in parsed message.");
                }
            }
        }
        return b.toString();
    }

    private FieldPosition updateMetaData(AppendableWrapper dest, int prevLength, FieldPosition fp, Object argId) {
        if (dest.attributes != null && prevLength < dest.length) {
            dest.attributes.add(new AttributeAndPosition(argId, prevLength, dest.length));
        }
        if (fp == null || !Field.ARGUMENT.equals(fp.getFieldAttribute())) {
            return fp;
        }
        fp.setBeginIndex(prevLength);
        fp.setEndIndex(dest.length);
        return null;
    }

    private static int findChoiceSubMessage(MessagePattern pattern, int partIndex, double number) {
        int msgStart;
        int partIndex2;
        int count = pattern.countParts();
        int partIndex3 = partIndex + 2;
        while (true) {
            msgStart = partIndex3;
            int partIndex4 = pattern.getLimitPartIndex(partIndex3) + 1;
            if (partIndex4 >= count) {
                break;
            }
            int partIndex5 = partIndex4 + 1;
            MessagePattern.Part part = pattern.getPart(partIndex4);
            MessagePattern.Part.Type type = part.getType();
            if (type == MessagePattern.Part.Type.ARG_LIMIT) {
                int i = partIndex5;
                break;
            } else if (type.hasNumericValue()) {
                double boundary = pattern.getNumericValue(part);
                partIndex2 = partIndex5 + 1;
                if (pattern.getPatternString().charAt(pattern.getPatternIndex(partIndex5)) == '<') {
                    if (number <= boundary) {
                        break;
                    }
                    partIndex3 = partIndex2;
                } else if (number < boundary) {
                    break;
                } else {
                    partIndex3 = partIndex2;
                }
            } else {
                throw new AssertionError();
            }
        }
        int i2 = partIndex2;
        return msgStart;
    }

    private static double parseChoiceArgument(MessagePattern pattern, int partIndex, String source, ParsePosition pos) {
        int newIndex;
        int start = pos.getIndex();
        int furthest = start;
        double bestNumber = Double.NaN;
        while (pattern.getPartType(partIndex) != MessagePattern.Part.Type.ARG_LIMIT) {
            double tempNumber = pattern.getNumericValue(pattern.getPart(partIndex));
            int partIndex2 = partIndex + 2;
            int msgLimit = pattern.getLimitPartIndex(partIndex2);
            int len = matchStringUntilLimitPart(pattern, partIndex2, msgLimit, source, start);
            if (len >= 0 && (newIndex = start + len) > furthest) {
                furthest = newIndex;
                bestNumber = tempNumber;
                if (furthest == source.length()) {
                    break;
                }
            }
            partIndex = msgLimit + 1;
        }
        if (furthest == start) {
            pos.setErrorIndex(start);
        } else {
            pos.setIndex(furthest);
        }
        return bestNumber;
    }

    private static int matchStringUntilLimitPart(MessagePattern pattern, int partIndex, int limitPartIndex, String source, int sourceOffset) {
        int matchingSourceLength = 0;
        String msgString = pattern.getPatternString();
        int prevIndex = pattern.getPart(partIndex).getLimit();
        while (true) {
            partIndex++;
            MessagePattern.Part part = pattern.getPart(partIndex);
            if (partIndex == limitPartIndex || part.getType() == MessagePattern.Part.Type.SKIP_SYNTAX) {
                int length = part.getIndex() - prevIndex;
                if (length != 0 && !source.regionMatches(sourceOffset, msgString, prevIndex, length)) {
                    return -1;
                }
                matchingSourceLength += length;
                if (partIndex == limitPartIndex) {
                    return matchingSourceLength;
                }
                prevIndex = part.getLimit();
            }
        }
    }

    /* access modifiers changed from: private */
    public int findOtherSubMessage(int partIndex) {
        int count = this.msgPattern.countParts();
        if (this.msgPattern.getPart(partIndex).getType().hasNumericValue()) {
            partIndex++;
        }
        do {
            int partIndex2 = partIndex + 1;
            MessagePattern.Part part = this.msgPattern.getPart(partIndex);
            MessagePattern.Part.Type type = part.getType();
            if (type == MessagePattern.Part.Type.ARG_LIMIT) {
                return 0;
            }
            if (type != MessagePattern.Part.Type.ARG_SELECTOR) {
                throw new AssertionError();
            } else if (this.msgPattern.partSubstringMatches(part, PluralRules.KEYWORD_OTHER)) {
                return partIndex2;
            } else {
                if (this.msgPattern.getPartType(partIndex2).hasNumericValue()) {
                    partIndex2++;
                }
                partIndex = this.msgPattern.getLimitPartIndex(partIndex2) + 1;
            }
        } while (partIndex < count);
        int i = partIndex;
        return 0;
    }

    /* access modifiers changed from: private */
    public int findFirstPluralNumberArg(int msgStart, String argName) {
        int i = msgStart + 1;
        while (true) {
            MessagePattern.Part part = this.msgPattern.getPart(i);
            MessagePattern.Part.Type type = part.getType();
            if (type == MessagePattern.Part.Type.MSG_LIMIT) {
                return 0;
            }
            if (type == MessagePattern.Part.Type.REPLACE_NUMBER) {
                return -1;
            }
            if (type == MessagePattern.Part.Type.ARG_START) {
                MessagePattern.ArgType argType = part.getArgType();
                if (argName.length() != 0 && (argType == MessagePattern.ArgType.NONE || argType == MessagePattern.ArgType.SIMPLE)) {
                    if (this.msgPattern.partSubstringMatches(this.msgPattern.getPart(i + 1), argName)) {
                        return i;
                    }
                }
                i = this.msgPattern.getLimitPartIndex(i);
            }
            i++;
        }
    }

    private static final class PluralSelectorContext {
        String argName;
        boolean forReplaceNumber;
        Format formatter;
        Number number;
        int numberArgIndex;
        String numberString;
        double offset;
        int startIndex;

        private PluralSelectorContext(int start, String name, Number num, double off) {
            this.startIndex = start;
            this.argName = name;
            if (off == 0.0d) {
                this.number = num;
            } else {
                this.number = Double.valueOf(num.doubleValue() - off);
            }
            this.offset = off;
        }

        public String toString() {
            throw new AssertionError("PluralSelectorContext being formatted, rather than its number");
        }
    }

    private static final class PluralSelectorProvider implements PluralFormat.PluralSelector {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private MessageFormat msgFormat;
        private PluralRules rules;
        private PluralRules.PluralType type;

        static {
            Class<MessageFormat> cls = MessageFormat.class;
        }

        public PluralSelectorProvider(MessageFormat mf, PluralRules.PluralType type2) {
            this.msgFormat = mf;
            this.type = type2;
        }

        public String select(Object ctx, double number) {
            if (this.rules == null) {
                this.rules = PluralRules.forLocale(this.msgFormat.ulocale, this.type);
            }
            PluralSelectorContext context = (PluralSelectorContext) ctx;
            context.numberArgIndex = this.msgFormat.findFirstPluralNumberArg(this.msgFormat.findOtherSubMessage(context.startIndex), context.argName);
            if (context.numberArgIndex > 0 && this.msgFormat.cachedFormatters != null) {
                context.formatter = (Format) this.msgFormat.cachedFormatters.get(Integer.valueOf(context.numberArgIndex));
            }
            if (context.formatter == null) {
                context.formatter = this.msgFormat.getStockNumberFormatter();
                context.forReplaceNumber = true;
            }
            if (context.number.doubleValue() == number) {
                context.numberString = context.formatter.format(context.number);
                if (!(context.formatter instanceof DecimalFormat)) {
                    return this.rules.select(number);
                }
                return this.rules.select(((DecimalFormat) context.formatter).getFixedDecimal(number));
            }
            throw new AssertionError();
        }
    }

    private void format(Object arguments, AppendableWrapper result, FieldPosition fp) {
        if (arguments == null || (arguments instanceof Map)) {
            format((Object[]) null, (Map) arguments, result, fp);
        } else {
            format((Object[]) arguments, (Map<String, Object>) null, result, fp);
        }
    }

    private void format(Object[] arguments, Map<String, Object> argsMap, AppendableWrapper dest, FieldPosition fp) {
        if (arguments == null || !this.msgPattern.hasNamedArguments()) {
            format(0, (PluralSelectorContext) null, arguments, argsMap, dest, fp);
            return;
        }
        throw new IllegalArgumentException("This method is not available in MessageFormat objects that use alphanumeric argument names.");
    }

    private void resetPattern() {
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern != null) {
            messagePattern.clear();
        }
        Map<Integer, Format> map = this.cachedFormatters;
        if (map != null) {
            map.clear();
        }
        this.customFormatArgStarts = null;
    }

    private Format createAppropriateFormat(String type, String style) {
        switch (findKeyword(type, typeList)) {
            case 0:
                switch (findKeyword(style, modifierList)) {
                    case 0:
                        return NumberFormat.getInstance(this.ulocale);
                    case 1:
                        return NumberFormat.getCurrencyInstance(this.ulocale);
                    case 2:
                        return NumberFormat.getPercentInstance(this.ulocale);
                    case 3:
                        return NumberFormat.getIntegerInstance(this.ulocale);
                    default:
                        int i = 0;
                        while (PatternProps.isWhiteSpace(style.charAt(i))) {
                            i++;
                        }
                        if (style.regionMatches(i, "::", 0, 2)) {
                            return NumberFormatter.forSkeleton(style.substring(i + 2)).locale(this.ulocale).toFormat();
                        }
                        return new DecimalFormat(style, new DecimalFormatSymbols(this.ulocale));
                }
            case 1:
                switch (findKeyword(style, dateModifierList)) {
                    case 0:
                        return DateFormat.getDateInstance(2, this.ulocale);
                    case 1:
                        return DateFormat.getDateInstance(3, this.ulocale);
                    case 2:
                        return DateFormat.getDateInstance(2, this.ulocale);
                    case 3:
                        return DateFormat.getDateInstance(1, this.ulocale);
                    case 4:
                        return DateFormat.getDateInstance(0, this.ulocale);
                    default:
                        return new SimpleDateFormat(style, this.ulocale);
                }
            case 2:
                switch (findKeyword(style, dateModifierList)) {
                    case 0:
                        return DateFormat.getTimeInstance(2, this.ulocale);
                    case 1:
                        return DateFormat.getTimeInstance(3, this.ulocale);
                    case 2:
                        return DateFormat.getTimeInstance(2, this.ulocale);
                    case 3:
                        return DateFormat.getTimeInstance(1, this.ulocale);
                    case 4:
                        return DateFormat.getTimeInstance(0, this.ulocale);
                    default:
                        return new SimpleDateFormat(style, this.ulocale);
                }
            case 3:
                RuleBasedNumberFormat rbnf = new RuleBasedNumberFormat(this.ulocale, 1);
                String ruleset = style.trim();
                if (ruleset.length() != 0) {
                    try {
                        rbnf.setDefaultRuleSet(ruleset);
                    } catch (Exception e) {
                    }
                }
                return rbnf;
            case 4:
                RuleBasedNumberFormat rbnf2 = new RuleBasedNumberFormat(this.ulocale, 2);
                String ruleset2 = style.trim();
                if (ruleset2.length() != 0) {
                    try {
                        rbnf2.setDefaultRuleSet(ruleset2);
                    } catch (Exception e2) {
                    }
                }
                return rbnf2;
            case 5:
                RuleBasedNumberFormat rbnf3 = new RuleBasedNumberFormat(this.ulocale, 3);
                String ruleset3 = style.trim();
                if (ruleset3.length() != 0) {
                    try {
                        rbnf3.setDefaultRuleSet(ruleset3);
                    } catch (Exception e3) {
                    }
                }
                return rbnf3;
            default:
                throw new IllegalArgumentException("Unknown format type \"" + type + "\"");
        }
    }

    private static final int findKeyword(String s, String[] list) {
        String s2 = PatternProps.trimWhiteSpace(s).toLowerCase(rootLocale);
        for (int i = 0; i < list.length; i++) {
            if (s2.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.ulocale.toLanguageTag());
        if (this.msgPattern == null) {
            this.msgPattern = new MessagePattern();
        }
        out.writeObject(this.msgPattern.getApostropheMode());
        out.writeObject(this.msgPattern.getPatternString());
        Set<Integer> set = this.customFormatArgStarts;
        if (set != null && !set.isEmpty()) {
            out.writeInt(this.customFormatArgStarts.size());
            int formatIndex = 0;
            int partIndex = 0;
            while (true) {
                int nextTopLevelArgStart = nextTopLevelArgStart(partIndex);
                partIndex = nextTopLevelArgStart;
                if (nextTopLevelArgStart < 0) {
                    break;
                }
                if (this.customFormatArgStarts.contains(Integer.valueOf(partIndex))) {
                    out.writeInt(formatIndex);
                    out.writeObject(this.cachedFormatters.get(Integer.valueOf(partIndex)));
                }
                formatIndex++;
            }
        } else {
            out.writeInt(0);
        }
        out.writeInt(0);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.ulocale = ULocale.forLanguageTag((String) in.readObject());
        MessagePattern.ApostropheMode aposMode = (MessagePattern.ApostropheMode) in.readObject();
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern == null || aposMode != messagePattern.getApostropheMode()) {
            this.msgPattern = new MessagePattern(aposMode);
        }
        String msg = (String) in.readObject();
        if (msg != null) {
            applyPattern(msg);
        }
        for (int numFormatters = in.readInt(); numFormatters > 0; numFormatters--) {
            setFormat(in.readInt(), (Format) in.readObject());
        }
        for (int numPairs = in.readInt(); numPairs > 0; numPairs--) {
            in.readInt();
            in.readObject();
        }
    }

    private void cacheExplicitFormats() {
        Map<Integer, Format> map = this.cachedFormatters;
        if (map != null) {
            map.clear();
        }
        this.customFormatArgStarts = null;
        int limit = this.msgPattern.countParts() - 2;
        int i = 1;
        while (i < limit) {
            MessagePattern.Part part = this.msgPattern.getPart(i);
            if (part.getType() == MessagePattern.Part.Type.ARG_START && part.getArgType() == MessagePattern.ArgType.SIMPLE) {
                int index = i;
                int i2 = i + 2;
                MessagePattern messagePattern = this.msgPattern;
                int i3 = i2 + 1;
                String explicitType = messagePattern.getSubstring(messagePattern.getPart(i2));
                String style = "";
                MessagePattern.Part part2 = this.msgPattern.getPart(i3);
                MessagePattern.Part part3 = part2;
                if (part2.getType() == MessagePattern.Part.Type.ARG_STYLE) {
                    style = this.msgPattern.getSubstring(part3);
                    i3++;
                }
                setArgStartFormat(index, createAppropriateFormat(explicitType, style));
                i = i3;
            }
            i++;
        }
    }

    private void setArgStartFormat(int argStart, Format formatter) {
        if (this.cachedFormatters == null) {
            this.cachedFormatters = new HashMap();
        }
        this.cachedFormatters.put(Integer.valueOf(argStart), formatter);
    }

    private void setCustomArgStartFormat(int argStart, Format formatter) {
        setArgStartFormat(argStart, formatter);
        if (this.customFormatArgStarts == null) {
            this.customFormatArgStarts = new HashSet();
        }
        this.customFormatArgStarts.add(Integer.valueOf(argStart));
    }

    public static String autoQuoteApostrophe(String pattern) {
        StringBuilder buf = new StringBuilder(pattern.length() * 2);
        int state = 0;
        int braceCount = 0;
        int j = pattern.length();
        for (int i = 0; i < j; i++) {
            char c = pattern.charAt(i);
            switch (state) {
                case 0:
                    switch (c) {
                        case '\'':
                            state = 1;
                            break;
                        case '{':
                            state = 3;
                            braceCount++;
                            break;
                    }
                case 1:
                    switch (c) {
                        case '\'':
                            state = 0;
                            break;
                        case '{':
                        case '}':
                            state = 2;
                            break;
                        default:
                            buf.append(SINGLE_QUOTE);
                            state = 0;
                            break;
                    }
                case 2:
                    switch (c) {
                        case '\'':
                            state = 0;
                            break;
                    }
                case 3:
                    switch (c) {
                        case '{':
                            braceCount++;
                            break;
                        case '}':
                            braceCount--;
                            if (braceCount != 0) {
                                break;
                            } else {
                                state = 0;
                                break;
                            }
                    }
            }
            buf.append(c);
        }
        if (state == 1 || state == 2) {
            buf.append(SINGLE_QUOTE);
        }
        return new String(buf);
    }

    private static final class AppendableWrapper {
        private Appendable app;
        /* access modifiers changed from: private */
        public List<AttributeAndPosition> attributes = null;
        /* access modifiers changed from: private */
        public int length;

        public AppendableWrapper(StringBuilder sb) {
            this.app = sb;
            this.length = sb.length();
        }

        public AppendableWrapper(StringBuffer sb) {
            this.app = sb;
            this.length = sb.length();
        }

        public void useAttributes() {
            this.attributes = new ArrayList();
        }

        public void append(CharSequence s) {
            try {
                this.app.append(s);
                this.length += s.length();
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }

        public void append(CharSequence s, int start, int limit) {
            try {
                this.app.append(s, start, limit);
                this.length += limit - start;
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }

        public void append(CharacterIterator iterator) {
            this.length += append(this.app, iterator);
        }

        public static int append(Appendable result, CharacterIterator iterator) {
            try {
                int start = iterator.getBeginIndex();
                int limit = iterator.getEndIndex();
                int length2 = limit - start;
                if (start < limit) {
                    result.append(iterator.first());
                    while (true) {
                        start++;
                        if (start >= limit) {
                            break;
                        }
                        result.append(iterator.next());
                    }
                }
                return length2;
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }

        public void formatAndAppend(Format formatter, Object arg) {
            if (this.attributes == null) {
                append((CharSequence) formatter.format(arg));
                return;
            }
            AttributedCharacterIterator formattedArg = formatter.formatToCharacterIterator(arg);
            int prevLength = this.length;
            append((CharacterIterator) formattedArg);
            formattedArg.first();
            int start = formattedArg.getIndex();
            int limit = formattedArg.getEndIndex();
            int offset = prevLength - start;
            while (start < limit) {
                Map<AttributedCharacterIterator.Attribute, Object> map = formattedArg.getAttributes();
                int runLimit = formattedArg.getRunLimit();
                if (map.size() != 0) {
                    for (Map.Entry<AttributedCharacterIterator.Attribute, Object> entry : map.entrySet()) {
                        this.attributes.add(new AttributeAndPosition(entry.getKey(), entry.getValue(), offset + start, offset + runLimit));
                    }
                }
                start = runLimit;
                formattedArg.setIndex(start);
            }
        }

        public void formatAndAppend(Format formatter, Object arg, String argString) {
            if (this.attributes != null || argString == null) {
                formatAndAppend(formatter, arg);
            } else {
                append((CharSequence) argString);
            }
        }
    }

    private static final class AttributeAndPosition {
        /* access modifiers changed from: private */
        public AttributedCharacterIterator.Attribute key;
        /* access modifiers changed from: private */
        public int limit;
        /* access modifiers changed from: private */
        public int start;
        /* access modifiers changed from: private */
        public Object value;

        public AttributeAndPosition(Object fieldValue, int startIndex, int limitIndex) {
            init(Field.ARGUMENT, fieldValue, startIndex, limitIndex);
        }

        public AttributeAndPosition(AttributedCharacterIterator.Attribute field, Object fieldValue, int startIndex, int limitIndex) {
            init(field, fieldValue, startIndex, limitIndex);
        }

        public void init(AttributedCharacterIterator.Attribute field, Object fieldValue, int startIndex, int limitIndex) {
            this.key = field;
            this.value = fieldValue;
            this.start = startIndex;
            this.limit = limitIndex;
        }
    }
}
