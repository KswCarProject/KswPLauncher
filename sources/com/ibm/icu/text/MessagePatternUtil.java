package com.ibm.icu.text;

import com.ibm.icu.text.MessagePattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
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
        }
        if (pattern.getPartType(0) != MessagePattern.Part.Type.MSG_START) {
            throw new IllegalArgumentException("The MessagePattern does not represent a MessageFormat pattern");
        }
        return buildMessageNode(pattern, 0, limit);
    }

    /* loaded from: classes.dex */
    public static class Node {
        /* synthetic */ Node(C07321 x0) {
            this();
        }

        private Node() {
        }
    }

    /* loaded from: classes.dex */
    public static class MessageNode extends Node {
        private volatile List<MessageContentsNode> list;

        /* synthetic */ MessageNode(C07321 x0) {
            this();
        }

        public List<MessageContentsNode> getContents() {
            return this.list;
        }

        public String toString() {
            return this.list.toString();
        }

        private MessageNode() {
            super(null);
            this.list = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addContentsNode(MessageContentsNode node) {
            if ((node instanceof TextNode) && !this.list.isEmpty()) {
                MessageContentsNode lastNode = this.list.get(this.list.size() - 1);
                if (lastNode instanceof TextNode) {
                    TextNode textNode = (TextNode) lastNode;
                    textNode.text += ((TextNode) node).text;
                    return;
                }
            }
            this.list.add(node);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MessageNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class MessageContentsNode extends Node {
        private Type type;

        /* loaded from: classes.dex */
        public enum Type {
            TEXT,
            ARG,
            REPLACE_NUMBER
        }

        /* synthetic */ MessageContentsNode(Type x0, C07321 x1) {
            this(x0);
        }

        static /* synthetic */ MessageContentsNode access$600() {
            return createReplaceNumberNode();
        }

        public Type getType() {
            return this.type;
        }

        public String toString() {
            return "{REPLACE_NUMBER}";
        }

        private MessageContentsNode(Type type) {
            super(null);
            this.type = type;
        }

        private static MessageContentsNode createReplaceNumberNode() {
            return new MessageContentsNode(Type.REPLACE_NUMBER);
        }
    }

    /* loaded from: classes.dex */
    public static class TextNode extends MessageContentsNode {
        private String text;

        /* synthetic */ TextNode(String x0, C07321 x1) {
            this(x0);
        }

        public String getText() {
            return this.text;
        }

        @Override // com.ibm.icu.text.MessagePatternUtil.MessageContentsNode
        public String toString() {
            return "\u00ab" + this.text + "\u00bb";
        }

        private TextNode(String text) {
            super(MessageContentsNode.Type.TEXT, null);
            this.text = text;
        }
    }

    /* loaded from: classes.dex */
    public static class ArgNode extends MessageContentsNode {
        private MessagePattern.ArgType argType;
        private ComplexArgStyleNode complexStyle;
        private String name;
        private int number;
        private String style;
        private String typeName;

        static /* synthetic */ ArgNode access$800() {
            return createArgNode();
        }

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

        @Override // com.ibm.icu.text.MessagePatternUtil.MessageContentsNode
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{').append(this.name);
            if (this.argType != MessagePattern.ArgType.NONE) {
                sb.append(',').append(this.typeName);
                if (this.argType == MessagePattern.ArgType.SIMPLE) {
                    if (this.style != null) {
                        sb.append(',').append(this.style);
                    }
                } else {
                    sb.append(',').append(this.complexStyle.toString());
                }
            }
            return sb.append('}').toString();
        }

        private ArgNode() {
            super(MessageContentsNode.Type.ARG, null);
            this.number = -1;
        }

        private static ArgNode createArgNode() {
            return new ArgNode();
        }
    }

    /* loaded from: classes.dex */
    public static class ComplexArgStyleNode extends Node {
        private MessagePattern.ArgType argType;
        private boolean explicitOffset;
        private volatile List<VariantNode> list;
        private double offset;

        /* synthetic */ ComplexArgStyleNode(MessagePattern.ArgType x0, C07321 x1) {
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
                } else if (PluralRules.KEYWORD_OTHER.equals(variant.getSelector())) {
                    if (other == null) {
                        other = variant;
                    }
                } else {
                    keywordVariants.add(variant);
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

        private ComplexArgStyleNode(MessagePattern.ArgType argType) {
            super(null);
            this.list = new ArrayList();
            this.argType = argType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addVariant(VariantNode variant) {
            this.list.add(variant);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ComplexArgStyleNode freeze() {
            this.list = Collections.unmodifiableList(this.list);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class VariantNode extends Node {
        private MessageNode msgNode;
        private double numericValue;
        private String selector;

        /* synthetic */ VariantNode(C07321 x0) {
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
            super(null);
            this.numericValue = -1.23456789E8d;
        }
    }

    private static MessageNode buildMessageNode(MessagePattern pattern, int start, int limit) {
        int prevPatternIndex = pattern.getPart(start).getLimit();
        MessageNode node = new MessageNode(null);
        int i = start + 1;
        while (true) {
            MessagePattern.Part part = pattern.getPart(i);
            int patternIndex = part.getIndex();
            if (prevPatternIndex < patternIndex) {
                node.addContentsNode(new TextNode(pattern.getPatternString().substring(prevPatternIndex, patternIndex), null));
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
                node.addContentsNode(MessageContentsNode.access$600());
            }
            prevPatternIndex = part.getLimit();
            i++;
        }
    }

    private static ArgNode buildArgNode(MessagePattern pattern, int start, int limit) {
        ArgNode node = ArgNode.access$800();
        MessagePattern.ArgType argType = node.argType = pattern.getPart(start).getArgType();
        int start2 = start + 1;
        MessagePattern.Part part = pattern.getPart(start2);
        node.name = pattern.getSubstring(part);
        if (part.getType() == MessagePattern.Part.Type.ARG_NUMBER) {
            node.number = part.getValue();
        }
        int start3 = start2 + 1;
        switch (C07321.$SwitchMap$com$ibm$icu$text$MessagePattern$ArgType[argType.ordinal()]) {
            case 1:
                int start4 = start3 + 1;
                node.typeName = pattern.getSubstring(pattern.getPart(start3));
                if (start4 < limit) {
                    node.style = pattern.getSubstring(pattern.getPart(start4));
                }
                break;
            case 2:
                node.typeName = "choice";
                node.complexStyle = buildChoiceStyleNode(pattern, start3, limit);
                break;
            case 3:
                node.typeName = "plural";
                node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
                break;
            case 4:
                node.typeName = "select";
                node.complexStyle = buildSelectStyleNode(pattern, start3, limit);
                break;
            case 5:
                node.typeName = "selectordinal";
                node.complexStyle = buildPluralStyleNode(pattern, start3, limit, argType);
                break;
        }
        return node;
    }

    /* renamed from: com.ibm.icu.text.MessagePatternUtil$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C07321 {
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
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.CHOICE, null);
        while (start < limit) {
            int valueIndex = start;
            MessagePattern.Part part = pattern.getPart(start);
            double value = pattern.getNumericValue(part);
            int start2 = start + 2;
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(pattern.getPart(valueIndex + 1));
            variant.numericValue = value;
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildPluralStyleNode(MessagePattern pattern, int start, int limit, MessagePattern.ArgType argType) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(argType, null);
        MessagePattern.Part offset = pattern.getPart(start);
        if (offset.getType().hasNumericValue()) {
            node.explicitOffset = true;
            node.offset = pattern.getNumericValue(offset);
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
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(selector);
            variant.numericValue = value;
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }

    private static ComplexArgStyleNode buildSelectStyleNode(MessagePattern pattern, int start, int limit) {
        ComplexArgStyleNode node = new ComplexArgStyleNode(MessagePattern.ArgType.SELECT, null);
        while (start < limit) {
            int start2 = start + 1;
            MessagePattern.Part selector = pattern.getPart(start);
            int msgLimit = pattern.getLimitPartIndex(start2);
            VariantNode variant = new VariantNode(null);
            variant.selector = pattern.getSubstring(selector);
            variant.msgNode = buildMessageNode(pattern, start2, msgLimit);
            node.addVariant(variant);
            start = msgLimit + 1;
        }
        return node.freeze();
    }
}
