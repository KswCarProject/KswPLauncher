package com.ibm.icu.text;

import com.ibm.icu.text.MessagePattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MessagePatternUtil {
    private MessagePatternUtil() {
    }

    public static MessageNode buildMessageNode(String patternString) {
        return buildMessageNode(new MessagePattern(patternString));
    }

    public static MessageNode buildMessageNode(MessagePattern pattern) {
        int limit = pattern.countParts() - 1;
        if (limit < 0) {
            throw new IllegalArgumentException("The MessagePattern is empty");
        } else if (pattern.getPartType(0) == MessagePattern.Part.Type.MSG_START) {
            return buildMessageNode(pattern, 0, limit);
        } else {
            throw new IllegalArgumentException("The MessagePattern does not represent a MessageFormat pattern");
        }
    }

    public static class Node {
        /* synthetic */ Node(AnonymousClass1 x0) {
            this();
        }

        private Node() {
        }
    }

    public static class MessageNode extends Node {
        private volatile List<MessageContentsNode> list;

        /* synthetic */ MessageNode(AnonymousClass1 x0) {
            this();
        }

        public List<MessageContentsNode> getContents() {
            return this.list;
        }

        public String toString() {
            return this.list.toString();
        }

        private MessageNode() {
            super((AnonymousClass1) null);
            this.list = new ArrayList();
        }

        /* access modifiers changed from: private */
        public void addContentsNode(MessageContentsNode node) {
            if ((node instanceof TextNode) && !this.list.isEmpty()) {
                MessageContentsNode lastNode = this.list.get(this.list.size() - 1);
                if (lastNode instanceof TextNode) {
                    TextNode textNode = (TextNode) lastNode;
                    String unused = textNode.text = textNode.text + ((TextNode) node).text;
                    return;
                }
            }
            this.list.add(node);
        }

        /* access modifiers changed from: private */
        public MessageNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    public static class MessageContentsNode extends Node {
        private Type type;

        public enum Type {
            TEXT,
            ARG,
            REPLACE_NUMBER
        }

        /* synthetic */ MessageContentsNode(Type x0, AnonymousClass1 x1) {
            this(x0);
        }

        public Type getType() {
            return this.type;
        }

        public String toString() {
            return "{REPLACE_NUMBER}";
        }

        private MessageContentsNode(Type type2) {
            super((AnonymousClass1) null);
            this.type = type2;
        }

        /* access modifiers changed from: private */
        public static MessageContentsNode createReplaceNumberNode() {
            return new MessageContentsNode(Type.REPLACE_NUMBER);
        }
    }

    public static class TextNode extends MessageContentsNode {
        /* access modifiers changed from: private */
        public String text;

        /* synthetic */ TextNode(String x0, AnonymousClass1 x1) {
            this(x0);
        }

        public String getText() {
            return this.text;
        }

        public String toString() {
            return "«" + this.text + "»";
        }

        private TextNode(String text2) {
            super(MessageContentsNode.Type.TEXT, (AnonymousClass1) null);
            this.text = text2;
        }
    }

    public static class ArgNode extends MessageContentsNode {
        /* access modifiers changed from: private */
        public MessagePattern.ArgType argType;
        /* access modifiers changed from: private */
        public ComplexArgStyleNode complexStyle;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public int number = -1;
        /* access modifiers changed from: private */
        public String style;
        /* access modifiers changed from: private */
        public String typeName;

        public MessagePattern.ArgType getArgType() {
            return this.argType;
        }

        public String getName() {
            return this.name;
        }

        public int getNumber() {
            return this.number;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public String getSimpleStyle() {
            return this.style;
        }

        public ComplexArgStyleNode getComplexStyle() {
            return this.complexStyle;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{').append(this.name);
            if (this.argType != MessagePattern.ArgType.NONE) {
                sb.append(',').append(this.typeName);
                if (this.argType != MessagePattern.ArgType.SIMPLE) {
                    sb.append(',').append(this.complexStyle.toString());
                } else if (this.style != null) {
                    sb.append(',').append(this.style);
                }
            }
            return sb.append('}').toString();
        }

        private ArgNode() {
            super(MessageContentsNode.Type.ARG, (AnonymousClass1) null);
        }

        /* access modifiers changed from: private */
        public static ArgNode createArgNode() {
            return new ArgNode();
        }
    }

    public static class ComplexArgStyleNode extends Node {
        private MessagePattern.ArgType argType;
        /* access modifiers changed from: private */
        public boolean explicitOffset;
        private volatile List<VariantNode> list;
        /* access modifiers changed from: private */
        public double offset;

        /* synthetic */ ComplexArgStyleNode(MessagePattern.ArgType x0, AnonymousClass1 x1) {
            this(x0);
        }

        public MessagePattern.ArgType getArgType() {
            return this.argType;
        }

        public boolean hasExplicitOffset() {
            return this.explicitOffset;
        }

        public double getOffset() {
            return this.offset;
        }

        public List<VariantNode> getVariants() {
            return this.list;
        }

        public VariantNode getVariantsByType(List<VariantNode> numericVariants, List<VariantNode> keywordVariants) {
            if (numericVariants != null) {
                numericVariants.clear();
            }
            keywordVariants.clear();
            VariantNode other = null;
            for (VariantNode variant : this.list) {
                if (variant.isSelectorNumeric()) {
                    numericVariants.add(variant);
                } else if (!PluralRules.KEYWORD_OTHER.equals(variant.getSelector())) {
                    keywordVariants.add(variant);
                } else if (other == null) {
                    other = variant;
                }
            }
            return other;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(this.argType.toString()).append(" style) ");
            if (hasExplicitOffset()) {
                sb.append("offset:").append(this.offset).append(' ');
            }
            return sb.append(this.list.toString()).toString();
        }

        private ComplexArgStyleNode(MessagePattern.ArgType argType2) {
            super((AnonymousClass1) null);
            this.list = new ArrayList();
            this.argType = argType2;
        }

        /* access modifiers changed from: private */
        public void addVariant(VariantNode variant) {
            this.list.add(variant);
        }

        /* access modifiers changed from: private */
        public ComplexArgStyleNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    public static class VariantNode extends Node {
        /* access modifiers changed from: private */
        public MessageNode msgNode;
        /* access modifiers changed from: private */
        public double numericValue;
        /* access modifiers changed from: private */
        public String selector;

        /* synthetic */ VariantNode(AnonymousClass1 x0) {
            this();
        }

        public String getSelector() {
            return this.selector;
        }

        public boolean isSelectorNumeric() {
            return this.numericValue != -1.23456789E8d;
        }

        public double getSelectorValue() {
            return this.numericValue;
        }

        public MessageNode getMessage() {
            return this.msgNode;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (isSelectorNumeric()) {
                sb.append(this.numericValue).append(" (").append(this.selector).append(") {");
            } else {
                sb.append(this.selector).append(" {");
            }
            return sb.append(this.msgNode.toString()).append('}').toString();
        }

        private VariantNode() {
            super((AnonymousClass1) null);
            this.numericValue = -1.23456789E8d;
        }
    }

    private static MessageNode buildMessageNode(MessagePattern pattern, int start, int limit) {
        int prevPatternIndex = pattern.getPart(start).getLimit();
        MessageNode node = new MessageNode((AnonymousClass1) null);
        int i = start + 1;
        while (true) {
            MessagePattern.Part part = pattern.getPart(i);
            int patternIndex = part.getIndex();
            if (prevPatternIndex < patternIndex) {
                node.addContentsNode(new TextNode(pattern.getPatternString().substring(prevPatternIndex, patternIndex), (AnonymousClass1) null));
            }
            if (i == limit) {
                return node.freeze();
            }
            MessagePattern.Part.Type partType = part.getType();
            if (partType == MessagePattern.Part.Type.ARG_START) {
                int argLimit = pattern.getLimitPartIndex(i);
                node.addContentsNode(buildArgNode(pattern, i, argLimit));
                i = argLimit;
                part = pattern.getPart(i);
            } else if (partType == MessagePattern.Part.Type.REPLACE_NUMBER) {
                node.addContentsNode(MessageContentsNode.createReplaceNumberNode());
            }
            prevPatternIndex = part.getLimit();
            i++;
        }
    }

    private static ArgNode buildArgNode(MessagePattern pattern, int start, int limit) {
        ArgNode node = ArgNode.createArgNode();
        MessagePattern.ArgType argType = node.argType = pattern.getPart(start).getArgType();
        int start2 = start + 1;
        MessagePattern.Part part = pattern.getPart(start2);
        String unused = node.name = pattern.getSubstring(part);
        if (part.getType() == MessagePattern.Part.Type.ARG_NUMBER) {
            int unused2 = node.number = part.getValue();
        }
        int start3 = start2 + 1;
        switch (AnonymousClass1.$SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[argType.ordinal()]) {
            case 1:
                int start4 = start3 + 1;
                String unused3 = node.typeName = pattern.getSubstring(pattern.getPart(start3));
                if (start4 < limit) {
                    String unused4 = node.style = pattern.getSubstring(pattern.getPart(start4));
                }
                int i = start4;
                break;
            case 2:
                String unused5 = node.typeName = "choice";
                ComplexArgStyleNode unused6 = node.complexStyle = buildChoiceStyleNode(pattern, start3, limit);
                break;
            case 3:
                String unused7 = node.typeName = "plural";
                ComplexArgStyleNode unused8 = node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
                break;
            case 4:
                String unused9 = node.typeName = "select";
                ComplexArgStyleNode unused10 = node.complexStyle = buildSelectStyleNode(pattern, start3, limit);
                break;
            case 5:
                String unused11 = node.typeName = "selectordinal";
                ComplexArgStyleNode unused12 = node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
                break;
        }
        return node;
    }

    /* renamed from: com.ibm.icu.text.MessagePatternUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType;

        static {
            int[] iArr = new int[MessagePattern.ArgType.values().length];
            $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType = iArr;
            try {
                iArr[MessagePattern.ArgType.SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.CHOICE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.PLURAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.SELECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[MessagePattern.ArgType.SELECTORDINAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private static ComplexArgStyleNode buildChoiceStyleNode(MessagePattern pattern, int start, int limit) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.CHOICE, (AnonymousClass1) null);
        while (start < limit) {
            int valueIndex = start;
            double value = pattern.getNumericValue(pattern.getPart(start));
            int start2 = start + 2;
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode((AnonymousClass1) null);
            String unused = variant.selector = pattern.getSubstring(pattern.getPart(valueIndex + 1));
            double unused2 = variant.numericValue = value;
            MessageNode unused3 = variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildPluralStyleNode(MessagePattern pattern, int start, int limit, MessagePattern.ArgType argType) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(argType, (AnonymousClass1) null);
        MessagePattern.Part offset = pattern.getPart(start);
        if (offset.getType().hasNumericValue()) {
            boolean unused = node.explicitOffset = true;
            double unused2 = node.offset = pattern.getNumericValue(offset);
            start++;
        }
        while (start < limit) {
            int start2 = start + 1;
            MessagePattern.Part selector = pattern.getPart(start);
            double value = -1.23456789E8d;
            MessagePattern.Part part = pattern.getPart(start2);
            if (part.getType().hasNumericValue()) {
                value = pattern.getNumericValue(part);
                start2++;
            }
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode((AnonymousClass1) null);
            String unused3 = variant.selector = pattern.getSubstring(selector);
            double unused4 = variant.numericValue = value;
            MessageNode unused5 = variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildSelectStyleNode(MessagePattern pattern, int start, int limit) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.SELECT, (AnonymousClass1) null);
        while (start < limit) {
            int start2 = start + 1;
            MessagePattern.Part selector = pattern.getPart(start);
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode((AnonymousClass1) null);
            String unused = variant.selector = pattern.getSubstring(selector);
            MessageNode unused2 = variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }
}
