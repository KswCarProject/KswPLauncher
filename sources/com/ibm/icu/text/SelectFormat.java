package com.ibm.icu.text;

import com.ibm.icu.impl.PatternProps;
import com.ibm.icu.text.MessagePattern;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class SelectFormat extends Format {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = 2993154333257524984L;
    private transient MessagePattern msgPattern;
    private String pattern = null;

    public SelectFormat(String pattern2) {
        applyPattern(pattern2);
    }

    private void reset() {
        this.pattern = null;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern != null) {
            messagePattern.clear();
        }
    }

    public void applyPattern(String pattern2) {
        this.pattern = pattern2;
        if (this.msgPattern == null) {
            this.msgPattern = new MessagePattern();
        }
        try {
            this.msgPattern.parseSelectStyle(pattern2);
        } catch (RuntimeException e) {
            reset();
            throw e;
        }
    }

    public String toPattern() {
        return this.pattern;
    }

    static int findSubMessage(MessagePattern pattern2, int partIndex, String keyword) {
        int count = pattern2.countParts();
        int msgStart = 0;
        while (true) {
            int partIndex2 = partIndex + 1;
            MessagePattern.Part part = pattern2.getPart(partIndex);
            MessagePattern.Part.Type type = part.getType();
            if (type == MessagePattern.Part.Type.ARG_LIMIT) {
                break;
            } else if (type == MessagePattern.Part.Type.ARG_SELECTOR) {
                if (!pattern2.partSubstringMatches(part, keyword)) {
                    if (msgStart == 0 && pattern2.partSubstringMatches(part, PluralRules.KEYWORD_OTHER)) {
                        msgStart = partIndex2;
                    }
                    partIndex = pattern2.getLimitPartIndex(partIndex2) + 1;
                    if (partIndex >= count) {
                        int i = partIndex;
                        break;
                    }
                } else {
                    return partIndex2;
                }
            } else {
                throw new AssertionError();
            }
        }
        return msgStart;
    }

    public final String format(String keyword) {
        int index;
        if (PatternProps.isIdentifier(keyword)) {
            MessagePattern messagePattern = this.msgPattern;
            if (messagePattern == null || messagePattern.countParts() == 0) {
                throw new IllegalStateException("Invalid format error.");
            }
            int msgStart = findSubMessage(this.msgPattern, 0, keyword);
            if (!this.msgPattern.jdkAposMode()) {
                return this.msgPattern.getPatternString().substring(this.msgPattern.getPart(msgStart).getLimit(), this.msgPattern.getPatternIndex(this.msgPattern.getLimitPartIndex(msgStart)));
            }
            StringBuilder result = null;
            int prevIndex = this.msgPattern.getPart(msgStart).getLimit();
            int i = msgStart;
            while (true) {
                i++;
                MessagePattern.Part part = this.msgPattern.getPart(i);
                MessagePattern.Part.Type type = part.getType();
                index = part.getIndex();
                if (type == MessagePattern.Part.Type.MSG_LIMIT) {
                    break;
                } else if (type == MessagePattern.Part.Type.SKIP_SYNTAX) {
                    if (result == null) {
                        result = new StringBuilder();
                    }
                    result.append(this.pattern, prevIndex, index);
                    prevIndex = part.getLimit();
                } else if (type == MessagePattern.Part.Type.ARG_START) {
                    if (result == null) {
                        result = new StringBuilder();
                    }
                    result.append(this.pattern, prevIndex, index);
                    int prevIndex2 = index;
                    i = this.msgPattern.getLimitPartIndex(i);
                    int index2 = this.msgPattern.getPart(i).getLimit();
                    MessagePattern.appendReducedApostrophes(this.pattern, prevIndex2, index2, result);
                    prevIndex = index2;
                }
            }
            if (result == null) {
                return this.pattern.substring(prevIndex, index);
            }
            return result.append(this.pattern, prevIndex, index).toString();
        }
        throw new IllegalArgumentException("Invalid formatting argument.");
    }

    public StringBuffer format(Object keyword, StringBuffer toAppendTo, FieldPosition pos) {
        if (keyword instanceof String) {
            toAppendTo.append(format((String) keyword));
            return toAppendTo;
        }
        throw new IllegalArgumentException("'" + keyword + "' is not a String");
    }

    public Object parseObject(String source, ParsePosition pos) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SelectFormat sf = (SelectFormat) obj;
        MessagePattern messagePattern = this.msgPattern;
        if (messagePattern != null) {
            return messagePattern.equals(sf.msgPattern);
        }
        if (sf.msgPattern == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.pattern;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "pattern='" + this.pattern + "'";
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        String str = this.pattern;
        if (str != null) {
            applyPattern(str);
        }
    }
}
