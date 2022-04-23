package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.ibm.icu.text.PluralRules;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GlideException extends Exception {
    private static final StackTraceElement[] EMPTY_ELEMENTS = new StackTraceElement[0];
    private static final long serialVersionUID = 1;
    private final List<Throwable> causes;
    private Class<?> dataClass;
    private DataSource dataSource;
    private String detailMessage;
    private Exception exception;
    private Key key;

    public GlideException(String message) {
        this(message, (List<Throwable>) Collections.emptyList());
    }

    public GlideException(String detailMessage2, Throwable cause) {
        this(detailMessage2, (List<Throwable>) Collections.singletonList(cause));
    }

    public GlideException(String detailMessage2, List<Throwable> causes2) {
        this.detailMessage = detailMessage2;
        setStackTrace(EMPTY_ELEMENTS);
        this.causes = causes2;
    }

    /* access modifiers changed from: package-private */
    public void setLoggingDetails(Key key2, DataSource dataSource2) {
        setLoggingDetails(key2, dataSource2, (Class<?>) null);
    }

    /* access modifiers changed from: package-private */
    public void setLoggingDetails(Key key2, DataSource dataSource2, Class<?> dataClass2) {
        this.key = key2;
        this.dataSource = dataSource2;
        this.dataClass = dataClass2;
    }

    public void setOrigin(Exception exception2) {
        this.exception = exception2;
    }

    public Exception getOrigin() {
        return this.exception;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public List<Throwable> getCauses() {
        return this.causes;
    }

    public List<Throwable> getRootCauses() {
        List<Throwable> rootCauses = new ArrayList<>();
        addRootCauses(this, rootCauses);
        return rootCauses;
    }

    public void logRootCauses(String tag) {
        List<Throwable> causes2 = getRootCauses();
        int size = causes2.size();
        for (int i = 0; i < size; i++) {
            Log.i(tag, "Root cause (" + (i + 1) + " of " + size + ")", causes2.get(i));
        }
    }

    private void addRootCauses(Throwable throwable, List<Throwable> rootCauses) {
        if (throwable instanceof GlideException) {
            for (Throwable t : ((GlideException) throwable).getCauses()) {
                addRootCauses(t, rootCauses);
            }
            return;
        }
        rootCauses.add(throwable);
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream err) {
        printStackTrace((Appendable) err);
    }

    public void printStackTrace(PrintWriter err) {
        printStackTrace((Appendable) err);
    }

    private void printStackTrace(Appendable appendable) {
        appendExceptionMessage(this, appendable);
        appendCauses(getCauses(), new IndentedAppendable(appendable));
    }

    public String getMessage() {
        String str = "";
        StringBuilder append = new StringBuilder(71).append(this.detailMessage).append(this.dataClass != null ? ", " + this.dataClass : str).append(this.dataSource != null ? ", " + this.dataSource : str);
        if (this.key != null) {
            str = ", " + this.key;
        }
        StringBuilder result = append.append(str);
        List<Throwable> rootCauses = getRootCauses();
        if (rootCauses.isEmpty()) {
            return result.toString();
        }
        if (rootCauses.size() == 1) {
            result.append("\nThere was 1 cause:");
        } else {
            result.append("\nThere were ").append(rootCauses.size()).append(" causes:");
        }
        for (Throwable cause : rootCauses) {
            result.append(10).append(cause.getClass().getName()).append('(').append(cause.getMessage()).append(')');
        }
        result.append("\n call GlideException#logRootCauses(String) for more detail");
        return result.toString();
    }

    private static void appendExceptionMessage(Throwable t, Appendable appendable) {
        try {
            appendable.append(t.getClass().toString()).append(PluralRules.KEYWORD_RULE_SEPARATOR).append(t.getMessage()).append(10);
        } catch (IOException e) {
            throw new RuntimeException(t);
        }
    }

    private static void appendCauses(List<Throwable> causes2, Appendable appendable) {
        try {
            appendCausesWrapped(causes2, appendable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendCausesWrapped(List<Throwable> causes2, Appendable appendable) throws IOException {
        int size = causes2.size();
        for (int i = 0; i < size; i++) {
            appendable.append("Cause (").append(String.valueOf(i + 1)).append(" of ").append(String.valueOf(size)).append("): ");
            Throwable cause = causes2.get(i);
            if (cause instanceof GlideException) {
                ((GlideException) cause).printStackTrace(appendable);
            } else {
                appendExceptionMessage(cause, appendable);
            }
        }
    }

    private static final class IndentedAppendable implements Appendable {
        private static final String EMPTY_SEQUENCE = "";
        private static final String INDENT = "  ";
        private final Appendable appendable;
        private boolean printedNewLine = true;

        IndentedAppendable(Appendable appendable2) {
            this.appendable = appendable2;
        }

        public Appendable append(char c) throws IOException {
            boolean z = false;
            if (this.printedNewLine) {
                this.printedNewLine = false;
                this.appendable.append(INDENT);
            }
            if (c == 10) {
                z = true;
            }
            this.printedNewLine = z;
            this.appendable.append(c);
            return this;
        }

        public Appendable append(CharSequence charSequence) throws IOException {
            CharSequence charSequence2 = safeSequence(charSequence);
            return append(charSequence2, 0, charSequence2.length());
        }

        public Appendable append(CharSequence charSequence, int start, int end) throws IOException {
            CharSequence charSequence2 = safeSequence(charSequence);
            boolean z = false;
            if (this.printedNewLine) {
                this.printedNewLine = false;
                this.appendable.append(INDENT);
            }
            if (charSequence2.length() > 0 && charSequence2.charAt(end - 1) == 10) {
                z = true;
            }
            this.printedNewLine = z;
            this.appendable.append(charSequence2, start, end);
            return this;
        }

        private CharSequence safeSequence(CharSequence sequence) {
            if (sequence == null) {
                return "";
            }
            return sequence;
        }
    }
}
