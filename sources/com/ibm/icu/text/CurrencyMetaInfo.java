package com.ibm.icu.text;

import com.ibm.icu.impl.Grego;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.util.Currency;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CurrencyMetaInfo {
    @Deprecated
    protected static final CurrencyDigits defaultDigits = new CurrencyDigits(2, 0);
    private static final boolean hasData;
    private static final CurrencyMetaInfo impl;

    public static CurrencyMetaInfo getInstance() {
        return impl;
    }

    public static CurrencyMetaInfo getInstance(boolean noSubstitute) {
        if (hasData) {
            return impl;
        }
        return null;
    }

    @Deprecated
    public static boolean hasData() {
        return hasData;
    }

    @Deprecated
    protected CurrencyMetaInfo() {
    }

    public static final class CurrencyFilter {
        private static final CurrencyFilter ALL = new CurrencyFilter((String) null, (String) null, Long.MIN_VALUE, Long.MAX_VALUE, false);
        public final String currency;
        public final long from;
        public final String region;
        @Deprecated
        public final boolean tenderOnly;
        public final long to;

        private CurrencyFilter(String region2, String currency2, long from2, long to2, boolean tenderOnly2) {
            this.region = region2;
            this.currency = currency2;
            this.from = from2;
            this.to = to2;
            this.tenderOnly = tenderOnly2;
        }

        public static CurrencyFilter all() {
            return ALL;
        }

        public static CurrencyFilter now() {
            return ALL.withDate(new Date());
        }

        public static CurrencyFilter onRegion(String region2) {
            return ALL.withRegion(region2);
        }

        public static CurrencyFilter onCurrency(String currency2) {
            return ALL.withCurrency(currency2);
        }

        public static CurrencyFilter onDate(Date date) {
            return ALL.withDate(date);
        }

        public static CurrencyFilter onDateRange(Date from2, Date to2) {
            return ALL.withDateRange(from2, to2);
        }

        public static CurrencyFilter onDate(long date) {
            return ALL.withDate(date);
        }

        public static CurrencyFilter onDateRange(long from2, long to2) {
            return ALL.withDateRange(from2, to2);
        }

        public static CurrencyFilter onTender() {
            return ALL.withTender();
        }

        public CurrencyFilter withRegion(String region2) {
            return new CurrencyFilter(region2, this.currency, this.from, this.to, this.tenderOnly);
        }

        public CurrencyFilter withCurrency(String currency2) {
            return new CurrencyFilter(this.region, currency2, this.from, this.to, this.tenderOnly);
        }

        public CurrencyFilter withDate(Date date) {
            return new CurrencyFilter(this.region, this.currency, date.getTime(), date.getTime(), this.tenderOnly);
        }

        public CurrencyFilter withDateRange(Date from2, Date to2) {
            return new CurrencyFilter(this.region, this.currency, from2 == null ? Long.MIN_VALUE : from2.getTime(), to2 == null ? Long.MAX_VALUE : to2.getTime(), this.tenderOnly);
        }

        public CurrencyFilter withDate(long date) {
            return new CurrencyFilter(this.region, this.currency, date, date, this.tenderOnly);
        }

        public CurrencyFilter withDateRange(long from2, long to2) {
            return new CurrencyFilter(this.region, this.currency, from2, to2, this.tenderOnly);
        }

        public CurrencyFilter withTender() {
            return new CurrencyFilter(this.region, this.currency, this.from, this.to, true);
        }

        public boolean equals(Object rhs) {
            return (rhs instanceof CurrencyFilter) && equals((CurrencyFilter) rhs);
        }

        public boolean equals(CurrencyFilter rhs) {
            return Utility.sameObjects(this, rhs) || (rhs != null && equals(this.region, rhs.region) && equals(this.currency, rhs.currency) && this.from == rhs.from && this.to == rhs.to && this.tenderOnly == rhs.tenderOnly);
        }

        public int hashCode() {
            int hc = 0;
            String str = this.region;
            if (str != null) {
                hc = str.hashCode();
            }
            String str2 = this.currency;
            if (str2 != null) {
                hc = (hc * 31) + str2.hashCode();
            }
            long j = this.from;
            long j2 = this.to;
            return (((((((((hc * 31) + ((int) j)) * 31) + ((int) (j >>> 32))) * 31) + ((int) j2)) * 31) + ((int) (j2 >>> 32))) * 31) + (this.tenderOnly ? 1 : 0);
        }

        public String toString() {
            return CurrencyMetaInfo.debugString(this);
        }

        private static boolean equals(String lhs, String rhs) {
            return Utility.sameObjects(lhs, rhs) || (lhs != null && lhs.equals(rhs));
        }
    }

    public static final class CurrencyDigits {
        public final int fractionDigits;
        public final int roundingIncrement;

        public CurrencyDigits(int fractionDigits2, int roundingIncrement2) {
            this.fractionDigits = fractionDigits2;
            this.roundingIncrement = roundingIncrement2;
        }

        public String toString() {
            return CurrencyMetaInfo.debugString(this);
        }
    }

    public static final class CurrencyInfo {
        public final String code;
        public final long from;
        public final int priority;
        public final String region;
        private final boolean tender;
        public final long to;

        @Deprecated
        public CurrencyInfo(String region2, String code2, long from2, long to2, int priority2) {
            this(region2, code2, from2, to2, priority2, true);
        }

        @Deprecated
        public CurrencyInfo(String region2, String code2, long from2, long to2, int priority2, boolean tender2) {
            this.region = region2;
            this.code = code2;
            this.from = from2;
            this.to = to2;
            this.priority = priority2;
            this.tender = tender2;
        }

        public String toString() {
            return CurrencyMetaInfo.debugString(this);
        }

        public boolean isTender() {
            return this.tender;
        }
    }

    public List<CurrencyInfo> currencyInfo(CurrencyFilter filter) {
        return Collections.emptyList();
    }

    public List<String> currencies(CurrencyFilter filter) {
        return Collections.emptyList();
    }

    public List<String> regions(CurrencyFilter filter) {
        return Collections.emptyList();
    }

    public CurrencyDigits currencyDigits(String isoCode) {
        return currencyDigits(isoCode, Currency.CurrencyUsage.STANDARD);
    }

    public CurrencyDigits currencyDigits(String isoCode, Currency.CurrencyUsage currencyUsage) {
        return defaultDigits;
    }

    static {
        CurrencyMetaInfo temp;
        boolean tempHasData = false;
        try {
            temp = (CurrencyMetaInfo) Class.forName("com.ibm.icu.impl.ICUCurrencyMetaInfo").newInstance();
            tempHasData = true;
        } catch (Throwable th) {
            temp = new CurrencyMetaInfo();
        }
        impl = temp;
        hasData = tempHasData;
    }

    private static String dateString(long date) {
        if (date == Long.MAX_VALUE || date == Long.MIN_VALUE) {
            return null;
        }
        return Grego.timeToString(date);
    }

    /* access modifiers changed from: private */
    public static String debugString(Object o) {
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            for (Field f : o.getClass().getFields()) {
                Object v = f.get(o);
                if (v != null) {
                    if (v instanceof Date) {
                        s = dateString(((Date) v).getTime());
                    } else if (v instanceof Long) {
                        s = dateString(((Long) v).longValue());
                    } else {
                        s = String.valueOf(v);
                    }
                    if (s != null) {
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(f.getName()).append("='").append(s).append("'");
                    }
                }
            }
        } catch (Throwable th) {
        }
        sb.insert(0, o.getClass().getSimpleName() + "(");
        sb.append(")");
        return sb.toString();
    }
}
