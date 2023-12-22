package com.ibm.icu.text;

import com.ibm.icu.lang.UCharacter;

/* loaded from: classes.dex */
public class BidiTransform {
    private Bidi bidi;
    private int reorderingOptions;
    private int shapingOptions;
    private String text;

    /* loaded from: classes.dex */
    public enum Mirroring {
        OFF,
        ON
    }

    /* loaded from: classes.dex */
    public enum Order {
        LOGICAL,
        VISUAL
    }

    /* loaded from: classes.dex */
    private enum ReorderingScheme {
        LOG_LTR_TO_VIS_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.1
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.shapeArabic(0, 0);
                transform.resolve((byte) 0, 0);
                transform.reorder();
            }
        },
        LOG_RTL_TO_VIS_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.2
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 1, 0);
                transform.reorder();
                transform.shapeArabic(0, 4);
            }
        },
        LOG_LTR_TO_VIS_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.3
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.shapeArabic(0, 0);
                transform.resolve((byte) 0, 0);
                transform.reorder();
                transform.reverse();
            }
        },
        LOG_RTL_TO_VIS_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.4
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 1, 0);
                transform.reorder();
                transform.shapeArabic(0, 4);
                transform.reverse();
            }
        },
        VIS_LTR_TO_LOG_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.5
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.shapeArabic(0, 4);
                transform.resolve((byte) 1, 5);
                transform.reorder();
            }
        },
        VIS_RTL_TO_LOG_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.6
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.reverse();
                transform.shapeArabic(0, 4);
                transform.resolve((byte) 1, 5);
                transform.reorder();
            }
        },
        VIS_LTR_TO_LOG_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.7
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 0, 5);
                transform.reorder();
                transform.shapeArabic(0, 0);
            }
        },
        VIS_RTL_TO_LOG_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.8
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.reverse();
                transform.resolve((byte) 0, 5);
                transform.reorder();
                transform.shapeArabic(0, 0);
            }
        },
        LOG_LTR_TO_LOG_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.9
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.shapeArabic(0, 0);
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.resolve((byte) 0, 3);
                transform.reorder();
            }
        },
        LOG_RTL_TO_LOG_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.10
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 1, 0);
                transform.mirror();
                transform.resolve((byte) 1, 3);
                transform.reorder();
                transform.shapeArabic(0, 0);
            }
        },
        VIS_LTR_TO_VIS_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.11
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.shapeArabic(0, 4);
                transform.reverse();
            }
        },
        VIS_RTL_TO_VIS_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.12
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.reverse();
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.shapeArabic(0, 4);
            }
        },
        LOG_LTR_TO_LOG_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.13
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.shapeArabic(0, 0);
            }
        },
        LOG_RTL_TO_LOG_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.14
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsLogical(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsLogical(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 1, 0);
                transform.mirror();
                transform.shapeArabic(4, 0);
            }
        },
        VIS_LTR_TO_VIS_LTR { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.15
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsLTR(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsLTR(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.shapeArabic(0, 4);
            }
        },
        VIS_RTL_TO_VIS_RTL { // from class: com.ibm.icu.text.BidiTransform.ReorderingScheme.16
            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            boolean matches(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
                return BidiTransform.IsRTL(inLevel) && BidiTransform.IsVisual(inOrder) && BidiTransform.IsRTL(outLevel) && BidiTransform.IsVisual(outOrder);
            }

            @Override // com.ibm.icu.text.BidiTransform.ReorderingScheme
            void doTransform(BidiTransform transform) {
                transform.reverse();
                transform.resolve((byte) 0, 0);
                transform.mirror();
                transform.shapeArabic(0, 4);
                transform.reverse();
            }
        };

        abstract void doTransform(BidiTransform bidiTransform);

        abstract boolean matches(byte b, Order order, byte b2, Order order2);
    }

    public String transform(CharSequence text, byte inParaLevel, Order inOrder, byte outParaLevel, Order outOrder, Mirroring doMirroring, int shapingOptions) {
        if (text == null || inOrder == null || outOrder == null || doMirroring == null) {
            throw new IllegalArgumentException();
        }
        this.text = text.toString();
        byte[] levels = {inParaLevel, outParaLevel};
        resolveBaseDirection(levels);
        ReorderingScheme currentScheme = findMatchingScheme(levels[0], inOrder, levels[1], outOrder);
        if (currentScheme != null) {
            this.bidi = new Bidi();
            this.reorderingOptions = Mirroring.ON.equals(doMirroring) ? 2 : 0;
            this.shapingOptions = shapingOptions & (-5);
            currentScheme.doTransform(this);
        }
        return this.text;
    }

    private void resolveBaseDirection(byte[] levels) {
        byte b;
        if (!Bidi.IsDefaultLevel(levels[0])) {
            levels[0] = (byte) (levels[0] & 1);
        } else {
            byte level = Bidi.getBaseDirection(this.text);
            if (level != 3) {
                b = level;
            } else {
                b = levels[0] == Byte.MAX_VALUE ? (byte) 1 : (byte) 0;
            }
            levels[0] = b;
        }
        if (Bidi.IsDefaultLevel(levels[1])) {
            levels[1] = levels[0];
        } else {
            levels[1] = (byte) (levels[1] & 1);
        }
    }

    private ReorderingScheme findMatchingScheme(byte inLevel, Order inOrder, byte outLevel, Order outOrder) {
        ReorderingScheme[] values;
        for (ReorderingScheme scheme : ReorderingScheme.values()) {
            if (scheme.matches(inLevel, inOrder, outLevel, outOrder)) {
                return scheme;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolve(byte level, int options) {
        this.bidi.setInverse((options & 5) != 0);
        this.bidi.setReorderingMode(options);
        this.bidi.setPara(this.text, level, (byte[]) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reorder() {
        this.text = this.bidi.writeReordered(this.reorderingOptions);
        this.reorderingOptions = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reverse() {
        this.text = Bidi.writeReverse(this.text, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mirror() {
        if ((this.reorderingOptions & 2) == 0) {
            return;
        }
        StringBuffer sb = new StringBuffer(this.text);
        byte[] levels = this.bidi.getLevels();
        int i = 0;
        int n = levels.length;
        while (i < n) {
            int ch = UTF16.charAt(sb, i);
            if ((levels[i] & 1) != 0) {
                UTF16.setCharAt(sb, i, UCharacter.getMirror(ch));
            }
            i += UTF16.getCharCount(ch);
        }
        this.text = sb.toString();
        this.reorderingOptions &= -3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shapeArabic(int digitsDir, int lettersDir) {
        if (digitsDir == lettersDir) {
            shapeArabic(this.shapingOptions | digitsDir);
            return;
        }
        shapeArabic((this.shapingOptions & (-25)) | digitsDir);
        shapeArabic((this.shapingOptions & (-225)) | lettersDir);
    }

    private void shapeArabic(int options) {
        if (options != 0) {
            ArabicShaping shaper = new ArabicShaping(options);
            try {
                this.text = shaper.shape(this.text);
            } catch (ArabicShapingException e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean IsLTR(byte level) {
        return (level & 1) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean IsRTL(byte level) {
        return (level & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean IsLogical(Order order) {
        return Order.LOGICAL.equals(order);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean IsVisual(Order order) {
        return Order.VISUAL.equals(order);
    }
}
