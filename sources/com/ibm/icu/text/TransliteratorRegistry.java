package com.ibm.icu.text;

import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.LocaleUtility;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.lang.UScript;
import com.ibm.icu.text.RuleBasedTransliterator;
import com.ibm.icu.text.Transliterator;
import com.ibm.icu.util.CaseInsensitiveString;
import com.ibm.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes.dex */
class TransliteratorRegistry {
    private static final String ANY = "Any";
    private static final boolean DEBUG = false;
    private static final char LOCALE_SEP = '_';
    private static final String NO_VARIANT = "";
    private Map<CaseInsensitiveString, Object[]> registry = Collections.synchronizedMap(new HashMap());
    private Map<CaseInsensitiveString, Map<CaseInsensitiveString, List<CaseInsensitiveString>>> specDAG = Collections.synchronizedMap(new HashMap());
    private List<CaseInsensitiveString> availableIDs = new ArrayList();

    /* loaded from: classes.dex */
    static class Spec {
        private boolean isNextLocale;
        private boolean isSpecLocale;
        private String nextSpec;
        private ICUResourceBundle res;
        private String scriptName;
        private String spec = null;
        private String top;

        public Spec(String theSpec) {
            this.top = theSpec;
            this.scriptName = null;
            try {
                int script = UScript.getCodeFromName(theSpec);
                int[] s = UScript.getCode(this.top);
                if (s != null) {
                    String name = UScript.getName(s[0]);
                    this.scriptName = name;
                    if (name.equalsIgnoreCase(this.top)) {
                        this.scriptName = null;
                    }
                }
                this.isSpecLocale = false;
                this.res = null;
                if (script == -1) {
                    Locale toploc = LocaleUtility.getLocaleFromName(this.top);
                    ICUResourceBundle bundleInstance = UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudt63b/translit", toploc);
                    this.res = bundleInstance;
                    if (bundleInstance != null && LocaleUtility.isFallbackOf(bundleInstance.getULocale().toString(), this.top)) {
                        this.isSpecLocale = true;
                    }
                }
            } catch (MissingResourceException e) {
                this.scriptName = null;
            }
            reset();
        }

        public boolean hasFallback() {
            return this.nextSpec != null;
        }

        public void reset() {
            if (!Utility.sameObjects(this.spec, this.top)) {
                this.spec = this.top;
                this.isSpecLocale = this.res != null;
                setupNext();
            }
        }

        private void setupNext() {
            this.isNextLocale = false;
            if (this.isSpecLocale) {
                String str = this.spec;
                this.nextSpec = str;
                int i = str.lastIndexOf(95);
                if (i > 0) {
                    this.nextSpec = this.spec.substring(0, i);
                    this.isNextLocale = true;
                    return;
                }
                this.nextSpec = this.scriptName;
            } else if (!Utility.sameObjects(this.nextSpec, this.scriptName)) {
                this.nextSpec = this.scriptName;
            } else {
                this.nextSpec = null;
            }
        }

        public String next() {
            this.spec = this.nextSpec;
            this.isSpecLocale = this.isNextLocale;
            setupNext();
            return this.spec;
        }

        public String get() {
            return this.spec;
        }

        public boolean isLocale() {
            return this.isSpecLocale;
        }

        public ResourceBundle getBundle() {
            ICUResourceBundle iCUResourceBundle = this.res;
            if (iCUResourceBundle != null && iCUResourceBundle.getULocale().toString().equals(this.spec)) {
                return this.res;
            }
            return null;
        }

        public String getTop() {
            return this.top;
        }
    }

    /* loaded from: classes.dex */
    static class ResourceEntry {
        public int direction;
        public String resource;

        public ResourceEntry(String n, int d) {
            this.resource = n;
            this.direction = d;
        }
    }

    /* loaded from: classes.dex */
    static class LocaleEntry {
        public int direction;
        public String rule;

        public LocaleEntry(String r, int d) {
            this.rule = r;
            this.direction = d;
        }
    }

    /* loaded from: classes.dex */
    static class AliasEntry {
        public String alias;

        public AliasEntry(String a) {
            this.alias = a;
        }
    }

    /* loaded from: classes.dex */
    static class CompoundRBTEntry {

        /* renamed from: ID */
        private String f169ID;
        private UnicodeSet compoundFilter;
        private List<RuleBasedTransliterator.Data> dataVector;
        private List<String> idBlockVector;

        public CompoundRBTEntry(String theID, List<String> theIDBlockVector, List<RuleBasedTransliterator.Data> theDataVector, UnicodeSet theCompoundFilter) {
            this.f169ID = theID;
            this.idBlockVector = theIDBlockVector;
            this.dataVector = theDataVector;
            this.compoundFilter = theCompoundFilter;
        }

        public Transliterator getInstance() {
            List<Transliterator> transliterators = new ArrayList<>();
            int passNumber = 1;
            int limit = Math.max(this.idBlockVector.size(), this.dataVector.size());
            for (int i = 0; i < limit; i++) {
                if (i < this.idBlockVector.size()) {
                    String idBlock = this.idBlockVector.get(i);
                    if (idBlock.length() > 0) {
                        transliterators.add(Transliterator.getInstance(idBlock));
                    }
                }
                if (i < this.dataVector.size()) {
                    RuleBasedTransliterator.Data data = this.dataVector.get(i);
                    transliterators.add(new RuleBasedTransliterator("%Pass" + passNumber, data, null));
                    passNumber++;
                }
            }
            Transliterator t = new CompoundTransliterator(transliterators, passNumber - 1);
            t.setID(this.f169ID);
            UnicodeFilter unicodeFilter = this.compoundFilter;
            if (unicodeFilter != null) {
                t.setFilter(unicodeFilter);
            }
            return t;
        }
    }

    public Transliterator get(String ID, StringBuffer aliasReturn) {
        Object[] entry = find(ID);
        if (entry == null) {
            return null;
        }
        return instantiateEntry(ID, entry, aliasReturn);
    }

    public void put(String ID, Class<? extends Transliterator> transliteratorSubclass, boolean visible) {
        registerEntry(ID, transliteratorSubclass, visible);
    }

    public void put(String ID, Transliterator.Factory factory, boolean visible) {
        registerEntry(ID, factory, visible);
    }

    public void put(String ID, String resourceName, int dir, boolean visible) {
        registerEntry(ID, new ResourceEntry(resourceName, dir), visible);
    }

    public void put(String ID, String alias, boolean visible) {
        registerEntry(ID, new AliasEntry(alias), visible);
    }

    public void put(String ID, Transliterator trans, boolean visible) {
        registerEntry(ID, trans, visible);
    }

    public void remove(String ID) {
        String[] stv = TransliteratorIDParser.IDtoSTV(ID);
        String id = TransliteratorIDParser.STVtoID(stv[0], stv[1], stv[2]);
        this.registry.remove(new CaseInsensitiveString(id));
        removeSTV(stv[0], stv[1], stv[2]);
        this.availableIDs.remove(new CaseInsensitiveString(id));
    }

    /* loaded from: classes.dex */
    private static class IDEnumeration implements Enumeration<String> {

        /* renamed from: en */
        Enumeration<CaseInsensitiveString> f170en;

        public IDEnumeration(Enumeration<CaseInsensitiveString> e) {
            this.f170en = e;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            Enumeration<CaseInsensitiveString> enumeration = this.f170en;
            return enumeration != null && enumeration.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public String nextElement() {
            return this.f170en.nextElement().getString();
        }
    }

    public Enumeration<String> getAvailableIDs() {
        return new IDEnumeration(Collections.enumeration(this.availableIDs));
    }

    public Enumeration<String> getAvailableSources() {
        return new IDEnumeration(Collections.enumeration(this.specDAG.keySet()));
    }

    public Enumeration<String> getAvailableTargets(String source) {
        CaseInsensitiveString cisrc = new CaseInsensitiveString(source);
        Map<CaseInsensitiveString, List<CaseInsensitiveString>> targets = this.specDAG.get(cisrc);
        if (targets == null) {
            return new IDEnumeration(null);
        }
        return new IDEnumeration(Collections.enumeration(targets.keySet()));
    }

    public Enumeration<String> getAvailableVariants(String source, String target) {
        CaseInsensitiveString cisrc = new CaseInsensitiveString(source);
        CaseInsensitiveString citrg = new CaseInsensitiveString(target);
        Map<CaseInsensitiveString, List<CaseInsensitiveString>> targets = this.specDAG.get(cisrc);
        if (targets == null) {
            return new IDEnumeration(null);
        }
        List<CaseInsensitiveString> variants = targets.get(citrg);
        if (variants == null) {
            return new IDEnumeration(null);
        }
        return new IDEnumeration(Collections.enumeration(variants));
    }

    private void registerEntry(String source, String target, String variant, Object entry, boolean visible) {
        String s = source;
        if (s.length() == 0) {
            s = ANY;
        }
        String ID = TransliteratorIDParser.STVtoID(source, target, variant);
        registerEntry(ID, s, target, variant, entry, visible);
    }

    private void registerEntry(String ID, Object entry, boolean visible) {
        String[] stv = TransliteratorIDParser.IDtoSTV(ID);
        String id = TransliteratorIDParser.STVtoID(stv[0], stv[1], stv[2]);
        registerEntry(id, stv[0], stv[1], stv[2], entry, visible);
    }

    private void registerEntry(String ID, String source, String target, String variant, Object entry, boolean visible) {
        CaseInsensitiveString ciID = new CaseInsensitiveString(ID);
        Object[] arrayOfObj = entry instanceof Object[] ? (Object[]) entry : new Object[]{entry};
        this.registry.put(ciID, arrayOfObj);
        if (visible) {
            registerSTV(source, target, variant);
            if (!this.availableIDs.contains(ciID)) {
                this.availableIDs.add(ciID);
                return;
            }
            return;
        }
        removeSTV(source, target, variant);
        this.availableIDs.remove(ciID);
    }

    private void registerSTV(String source, String target, String variant) {
        CaseInsensitiveString cisrc = new CaseInsensitiveString(source);
        CaseInsensitiveString citrg = new CaseInsensitiveString(target);
        CaseInsensitiveString civar = new CaseInsensitiveString(variant);
        Map<CaseInsensitiveString, List<CaseInsensitiveString>> targets = this.specDAG.get(cisrc);
        if (targets == null) {
            targets = Collections.synchronizedMap(new HashMap());
            this.specDAG.put(cisrc, targets);
        }
        List<CaseInsensitiveString> variants = targets.get(citrg);
        if (variants == null) {
            variants = new ArrayList();
            targets.put(citrg, variants);
        }
        if (!variants.contains(civar)) {
            if (variant.length() > 0) {
                variants.add(civar);
            } else {
                variants.add(0, civar);
            }
        }
    }

    private void removeSTV(String source, String target, String variant) {
        List<CaseInsensitiveString> variants;
        CaseInsensitiveString cisrc = new CaseInsensitiveString(source);
        CaseInsensitiveString citrg = new CaseInsensitiveString(target);
        CaseInsensitiveString civar = new CaseInsensitiveString(variant);
        Map<CaseInsensitiveString, List<CaseInsensitiveString>> targets = this.specDAG.get(cisrc);
        if (targets == null || (variants = targets.get(citrg)) == null) {
            return;
        }
        variants.remove(civar);
        if (variants.size() == 0) {
            targets.remove(citrg);
            if (targets.size() == 0) {
                this.specDAG.remove(cisrc);
            }
        }
    }

    private Object[] findInDynamicStore(Spec src, Spec trg, String variant) {
        String ID = TransliteratorIDParser.STVtoID(src.get(), trg.get(), variant);
        return this.registry.get(new CaseInsensitiveString(ID));
    }

    private Object[] findInStaticStore(Spec src, Spec trg, String variant) {
        Object[] entry = null;
        if (src.isLocale()) {
            entry = findInBundle(src, trg, variant, 0);
        } else if (trg.isLocale()) {
            entry = findInBundle(trg, src, variant, 1);
        }
        if (entry != null) {
            registerEntry(src.getTop(), trg.getTop(), variant, entry, false);
        }
        return entry;
    }

    private Object[] findInBundle(Spec specToOpen, Spec specToFind, String variant, int direction) {
        String[] subres;
        int i;
        ResourceBundle res = specToOpen.getBundle();
        if (res == null) {
            return null;
        }
        int pass = 0;
        while (pass < 2) {
            StringBuilder tag = new StringBuilder();
            if (pass == 0) {
                tag.append(direction == 0 ? "TransliterateTo" : "TransliterateFrom");
            } else {
                tag.append("Transliterate");
            }
            tag.append(specToFind.get().toUpperCase(Locale.ENGLISH));
            try {
                subres = res.getStringArray(tag.toString());
                i = 0;
                if (variant.length() != 0) {
                    i = 0;
                    while (i < subres.length && !subres[i].equalsIgnoreCase(variant)) {
                        i += 2;
                    }
                }
            } catch (MissingResourceException e) {
            }
            if (i >= subres.length) {
                continue;
                pass++;
            } else {
                int dir = pass == 0 ? 0 : direction;
                return new Object[]{new LocaleEntry(subres[i + 1], dir)};
            }
        }
        return null;
    }

    private Object[] find(String ID) {
        String[] stv = TransliteratorIDParser.IDtoSTV(ID);
        return find(stv[0], stv[1], stv[2]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r1.hasFallback() != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Object[] find(String source, String target, String variant) {
        Spec src = new Spec(source);
        Spec trg = new Spec(target);
        if (variant.length() != 0) {
            Object[] entry = findInDynamicStore(src, trg, variant);
            if (entry != null) {
                return entry;
            }
            Object[] entry2 = findInStaticStore(src, trg, variant);
            if (entry2 != null) {
                return entry2;
            }
        }
        while (true) {
            src.reset();
            while (true) {
                Object[] entry3 = findInDynamicStore(src, trg, "");
                if (entry3 != null) {
                    return entry3;
                }
                Object[] entry4 = findInStaticStore(src, trg, "");
                if (entry4 != null) {
                    return entry4;
                }
                if (!src.hasFallback()) {
                    break;
                }
                src.next();
            }
            trg.next();
        }
    }

    private Transliterator instantiateEntry(String ID, Object[] entryWrapper, StringBuffer aliasReturn) {
        while (true) {
            Object entry = entryWrapper[0];
            if (entry instanceof RuleBasedTransliterator.Data) {
                RuleBasedTransliterator.Data data = (RuleBasedTransliterator.Data) entry;
                return new RuleBasedTransliterator(ID, data, null);
            } else if (entry instanceof Class) {
                try {
                    return (Transliterator) ((Class) entry).newInstance();
                } catch (IllegalAccessException | InstantiationException e) {
                    return null;
                }
            } else if (entry instanceof AliasEntry) {
                aliasReturn.append(((AliasEntry) entry).alias);
                return null;
            } else if (entry instanceof Transliterator.Factory) {
                return ((Transliterator.Factory) entry).getInstance(ID);
            } else {
                if (entry instanceof CompoundRBTEntry) {
                    return ((CompoundRBTEntry) entry).getInstance();
                }
                if (entry instanceof AnyTransliterator) {
                    AnyTransliterator temp = (AnyTransliterator) entry;
                    return temp.safeClone();
                } else if (entry instanceof RuleBasedTransliterator) {
                    RuleBasedTransliterator temp2 = (RuleBasedTransliterator) entry;
                    return temp2.safeClone();
                } else if (entry instanceof CompoundTransliterator) {
                    CompoundTransliterator temp3 = (CompoundTransliterator) entry;
                    return temp3.safeClone();
                } else if (entry instanceof Transliterator) {
                    return (Transliterator) entry;
                } else {
                    TransliteratorParser parser = new TransliteratorParser();
                    try {
                        ResourceEntry re = (ResourceEntry) entry;
                        parser.parse(re.resource, re.direction);
                    } catch (ClassCastException e2) {
                        LocaleEntry le = (LocaleEntry) entry;
                        parser.parse(le.rule, le.direction);
                    }
                    if (parser.idBlockVector.size() == 0 && parser.dataVector.size() == 0) {
                        entryWrapper[0] = new AliasEntry("Any-Null");
                    } else if (parser.idBlockVector.size() == 0 && parser.dataVector.size() == 1) {
                        entryWrapper[0] = parser.dataVector.get(0);
                    } else if (parser.idBlockVector.size() != 1 || parser.dataVector.size() != 0) {
                        entryWrapper[0] = new CompoundRBTEntry(ID, parser.idBlockVector, parser.dataVector, parser.compoundFilter);
                    } else if (parser.compoundFilter != null) {
                        entryWrapper[0] = new AliasEntry(parser.compoundFilter.toPattern(false) + ";" + parser.idBlockVector.get(0));
                    } else {
                        entryWrapper[0] = new AliasEntry(parser.idBlockVector.get(0));
                    }
                }
            }
        }
    }
}
