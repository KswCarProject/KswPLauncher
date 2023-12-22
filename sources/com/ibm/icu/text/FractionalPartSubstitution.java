package com.ibm.icu.text;

import com.ibm.icu.impl.number.DecimalQuantity_DualStorageBCD;
import java.text.ParsePosition;
import kotlin.text.Typography;

/* compiled from: NFSubstitution.java */
/* loaded from: classes.dex */
class FractionalPartSubstitution extends NFSubstitution {
    private final boolean byDigits;
    private final boolean useSpaces;

    FractionalPartSubstitution(int pos, NFRuleSet ruleSet, String description) {
        super(pos, ruleSet, description);
        if (description.equals(">>") || description.equals(">>>") || ruleSet == this.ruleSet) {
            this.byDigits = true;
            this.useSpaces = !description.equals(">>>");
            return;
        }
        this.byDigits = false;
        this.useSpaces = true;
        this.ruleSet.makeIntoFractionRuleSet();
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public void doSubstitution(double number, StringBuilder toInsertInto, int position, int recursionCount) {
        if (!this.byDigits) {
            super.doSubstitution(number, toInsertInto, position, recursionCount);
            return;
        }
        DecimalQuantity_DualStorageBCD fq = new DecimalQuantity_DualStorageBCD(number);
        fq.roundToInfinity();
        boolean pad = false;
        for (int mag = fq.getLowerDisplayMagnitude(); mag < 0; mag++) {
            if (pad && this.useSpaces) {
                toInsertInto.insert(this.pos + position, ' ');
            } else {
                pad = true;
            }
            this.ruleSet.format(fq.getDigit(mag), toInsertInto, position + this.pos, recursionCount);
        }
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public long transformNumber(long number) {
        return 0L;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double transformNumber(double number) {
        return number - Math.floor(number);
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public Number doParse(String text, ParsePosition parsePosition, double baseValue, double upperBound, boolean lenientParse, int nonNumericalExecutedRuleMask) {
        Number n;
        if (!this.byDigits) {
            return super.doParse(text, parsePosition, baseValue, 0.0d, lenientParse, nonNumericalExecutedRuleMask);
        }
        String workText = text;
        ParsePosition workPos = new ParsePosition(1);
        DecimalQuantity_DualStorageBCD fq = new DecimalQuantity_DualStorageBCD();
        int totalDigits = 0;
        while (workText.length() > 0 && workPos.getIndex() != 0) {
            workPos.setIndex(0);
            int digit = this.ruleSet.parse(workText, workPos, 10.0d, nonNumericalExecutedRuleMask).intValue();
            if (lenientParse && workPos.getIndex() == 0 && (n = this.ruleSet.owner.getDecimalFormat().parse(workText, workPos)) != null) {
                digit = n.intValue();
            }
            if (workPos.getIndex() != 0) {
                fq.appendDigit((byte) digit, 0, true);
                totalDigits++;
                parsePosition.setIndex(parsePosition.getIndex() + workPos.getIndex());
                workText = workText.substring(workPos.getIndex());
                while (workText.length() > 0 && workText.charAt(0) == ' ') {
                    workText = workText.substring(1);
                    parsePosition.setIndex(parsePosition.getIndex() + 1);
                }
            }
        }
        fq.adjustMagnitude(-totalDigits);
        double result = fq.toDouble();
        return new Double(composeRuleValue(result, baseValue));
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double composeRuleValue(double newRuleValue, double oldRuleValue) {
        return newRuleValue + oldRuleValue;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    public double calcUpperBound(double oldUpperBound) {
        return 0.0d;
    }

    @Override // com.ibm.icu.text.NFSubstitution
    char tokenChar() {
        return Typography.greater;
    }
}
