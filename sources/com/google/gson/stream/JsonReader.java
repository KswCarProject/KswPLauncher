package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import kotlin.text.Typography;

/* loaded from: classes.dex */
public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;

    /* renamed from: in */
    private final Reader f93in;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int[] stack;
    private int stackSize;
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    int peeked = 0;

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.google.gson.stream.JsonReader.1
            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader reader) throws IOException {
                if (reader instanceof JsonTreeReader) {
                    ((JsonTreeReader) reader).promoteNameToValue();
                    return;
                }
                int p = reader.peeked;
                if (p == 0) {
                    p = reader.doPeek();
                }
                if (p == 13) {
                    reader.peeked = 9;
                } else if (p == 12) {
                    reader.peeked = 8;
                } else if (p == 14) {
                    reader.peeked = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + reader.peek() + reader.locationString());
                }
            }
        };
    }

    public JsonReader(Reader in) {
        int[] iArr = new int[32];
        this.stack = iArr;
        this.stackSize = 0;
        this.stackSize = 0 + 1;
        iArr[0] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (in == null) {
            throw new NullPointerException("in == null");
        }
        this.f93in = in;
    }

    public final void setLenient(boolean lenient) {
        this.lenient = lenient;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public void beginArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + locationString());
    }

    public void endArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 4) {
            int i = this.stackSize - 1;
            this.stackSize = i;
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + peek() + locationString());
    }

    public void beginObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + locationString());
    }

    public void endObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 2) {
            int i = this.stackSize - 1;
            this.stackSize = i;
            this.pathNames[i] = null;
            int[] iArr = this.pathIndices;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + peek() + locationString());
    }

    public boolean hasNext() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        return (p == 2 || p == 4) ? false : true;
    }

    public JsonToken peek() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        switch (p) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    int doPeek() throws IOException {
        int[] iArr = this.stack;
        int i = this.stackSize;
        int peekStack = iArr[i - 1];
        if (peekStack == 1) {
            iArr[i - 1] = 2;
        } else if (peekStack == 2) {
            switch (nextNonWhitespace(true)) {
                case 44:
                    break;
                case 59:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            iArr[i - 1] = 4;
            if (peekStack == 5) {
                switch (nextNonWhitespace(true)) {
                    case 44:
                        break;
                    case 59:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            int c = nextNonWhitespace(true);
            switch (c) {
                case 34:
                    this.peeked = 13;
                    return 13;
                case 39:
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    this.pos--;
                    if (isLiteral((char) c)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (peekStack == 4) {
            iArr[i - 1] = 5;
            switch (nextNonWhitespace(true)) {
                case 58:
                    break;
                default:
                    throw syntaxError("Expected ':'");
                case 61:
                    checkLenient();
                    if (this.pos < this.limit || fillBuffer(1)) {
                        char[] cArr = this.buffer;
                        int i2 = this.pos;
                        if (cArr[i2] == '>') {
                            this.pos = i2 + 1;
                            break;
                        }
                    }
                    break;
            }
        } else if (peekStack == 6) {
            if (this.lenient) {
                consumeNonExecutePrefix();
            }
            this.stack[this.stackSize - 1] = 7;
        } else if (peekStack == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
            this.pos--;
        } else if (peekStack == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (nextNonWhitespace(true)) {
            case 34:
                this.peeked = 9;
                return 9;
            case 39:
                checkLenient();
                this.peeked = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.peeked = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.peeked = 4;
                    return 4;
                }
                break;
            case 123:
                this.peeked = 1;
                return 1;
            default:
                this.pos--;
                int result = peekKeyword();
                if (result != 0) {
                    return result;
                }
                int result2 = peekNumber();
                if (result2 != 0) {
                    return result2;
                }
                if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
        }
        if (peekStack == 1 || peekStack == 2) {
            checkLenient();
            this.pos--;
            this.peeked = 7;
            return 7;
        }
        throw syntaxError("Unexpected value");
    }

    private int peekKeyword() throws IOException {
        String keyword;
        String keywordUpper;
        int peeking;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 'f' || c == 'F') {
            keyword = "false";
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        for (int i = 1; i < length; i++) {
            if (this.pos + i >= this.limit && !fillBuffer(i + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i];
            if (c2 != keyword.charAt(i) && c2 != keywordUpper.charAt(i)) {
                return 0;
            }
        }
        int i2 = this.pos;
        if ((i2 + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = peeking;
        return peeking;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00d5, code lost:
        if (isLiteral(r10) != false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d9, code lost:
        if (r8 != 2) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00db, code lost:
        if (r7 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00e1, code lost:
        if (r4 != Long.MIN_VALUE) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00e3, code lost:
        if (r6 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e9, code lost:
        if (r4 != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00eb, code lost:
        if (r6 != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00ed, code lost:
        if (r6 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ef, code lost:
        r10 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f1, code lost:
        r10 = -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00f2, code lost:
        r18.peekedLong = r10;
        r18.pos += r9;
        r18.peeked = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00fd, code lost:
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00fe, code lost:
        if (r8 == 2) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0100, code lost:
        if (r8 == 4) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0103, code lost:
        if (r8 != 7) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0106, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0107, code lost:
        r18.peekedNumberLength = r9;
        r18.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x010d, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010e, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int peekNumber() throws IOException {
        char[] buffer;
        int p;
        int p2;
        char[] buffer2 = this.buffer;
        int p3 = this.pos;
        int l = this.limit;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            boolean z = false;
            if (p3 + i == l) {
                if (i == buffer2.length) {
                    return 0;
                }
                if (fillBuffer(i + 1)) {
                    p3 = this.pos;
                    l = this.limit;
                }
            }
            char c = buffer2[p3 + i];
            switch (c) {
                case '+':
                    if (last == 5) {
                        last = 6;
                        buffer = buffer2;
                        p = p3;
                        break;
                    } else {
                        return 0;
                    }
                case '-':
                    if (last == 0) {
                        negative = true;
                        last = 1;
                        buffer = buffer2;
                        p = p3;
                        break;
                    } else if (last == 5) {
                        last = 6;
                        buffer = buffer2;
                        p = p3;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (last == 2) {
                        last = 3;
                        buffer = buffer2;
                        p = p3;
                        break;
                    } else {
                        return 0;
                    }
                case 'E':
                case 'e':
                    if (last == 2 || last == 4) {
                        last = 5;
                        buffer = buffer2;
                        p = p3;
                        break;
                    } else {
                        return 0;
                    }
                default:
                    if (c < '0') {
                        p2 = p3;
                        break;
                    } else if (c <= '9') {
                        if (last == 1) {
                            buffer = buffer2;
                            p = p3;
                        } else if (last != 0) {
                            if (last != 2) {
                                buffer = buffer2;
                                p = p3;
                                if (last == 3) {
                                    last = 4;
                                    break;
                                } else if (last != 5 && last != 6) {
                                    break;
                                } else {
                                    last = 7;
                                    break;
                                }
                            } else if (value != 0) {
                                buffer = buffer2;
                                p = p3;
                                long newValue = (10 * value) - (c - '0');
                                if (value > MIN_INCOMPLETE_INTEGER || (value == MIN_INCOMPLETE_INTEGER && newValue < value)) {
                                    z = true;
                                }
                                value = newValue;
                                fitsInLong &= z;
                                break;
                            } else {
                                return 0;
                            }
                        } else {
                            buffer = buffer2;
                            p = p3;
                        }
                        long value2 = -(c - '0');
                        last = 2;
                        value = value2;
                        break;
                    } else {
                        p2 = p3;
                        break;
                    }
                    break;
            }
            i++;
            buffer2 = buffer;
            p3 = p;
        }
    }

    private boolean isLiteral(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                checkLenient();
                return false;
            default:
                return true;
        }
    }

    public String nextName() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 14) {
            result = nextUnquotedValue();
        } else if (p == 12) {
            result = nextQuotedValue('\'');
        } else if (p == 13) {
            result = nextQuotedValue(Typography.quote);
        } else {
            throw new IllegalStateException("Expected a name but was " + peek() + locationString());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = result;
        return result;
    }

    public String nextString() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 10) {
            result = nextUnquotedValue();
        } else if (p == 8) {
            result = nextQuotedValue('\'');
        } else if (p == 9) {
            result = nextQuotedValue(Typography.quote);
        } else if (p == 11) {
            result = this.peekedString;
            this.peekedString = null;
        } else if (p == 15) {
            result = Long.toString(this.peekedLong);
        } else if (p == 16) {
            result = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            throw new IllegalStateException("Expected a string but was " + peek() + locationString());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean nextBoolean() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + peek() + locationString());
        }
    }

    public void nextNull() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 7) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + peek() + locationString());
    }

    public double nextDouble() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9) {
            this.peekedString = nextQuotedValue(p == 8 ? '\'' : Typography.quote);
        } else if (p == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (p != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + locationString());
        }
        this.peeked = 11;
        double result = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(result) || Double.isInfinite(result))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + result + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr2 = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return result;
    }

    public long nextLong() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : Typography.quote);
            }
            try {
                long result = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + peek() + locationString());
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        long result2 = (long) asDouble;
        if (result2 != asDouble) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result2;
    }

    private String nextQuotedValue(char quote) throws IOException {
        char[] buffer = this.buffer;
        StringBuilder builder = null;
        do {
            int c = this.pos;
            int l = this.limit;
            int start = c;
            while (c < l) {
                int p = c + 1;
                char c2 = buffer[c];
                if (c2 == quote) {
                    this.pos = p;
                    int len = (p - start) - 1;
                    if (builder == null) {
                        return new String(buffer, start, len);
                    }
                    builder.append(buffer, start, len);
                    return builder.toString();
                } else if (c2 == '\\') {
                    this.pos = p;
                    int len2 = (p - start) - 1;
                    if (builder == null) {
                        int estimatedLength = (len2 + 1) * 2;
                        builder = new StringBuilder(Math.max(estimatedLength, 16));
                    }
                    builder.append(buffer, start, len2);
                    builder.append(readEscapeCharacter());
                    int p2 = this.pos;
                    l = this.limit;
                    start = p2;
                    c = p2;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = p;
                    }
                    c = p;
                }
            }
            if (builder == null) {
                int estimatedLength2 = (c - start) * 2;
                builder = new StringBuilder(Math.max(estimatedLength2, 16));
            }
            builder.append(buffer, start, c - start);
            this.pos = c;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() throws IOException {
        StringBuilder builder = null;
        int i = 0;
        while (true) {
            int i2 = this.pos;
            if (i2 + i < this.limit) {
                switch (this.buffer[i2 + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                }
            } else if (i < this.buffer.length) {
                if (fillBuffer(i + 1)) {
                }
            } else {
                if (builder == null) {
                    builder = new StringBuilder(Math.max(i, 16));
                }
                builder.append(this.buffer, this.pos, i);
                this.pos += i;
                i = 0;
                if (!fillBuffer(1)) {
                }
            }
        }
        String result = builder == null ? new String(this.buffer, this.pos, i) : builder.append(this.buffer, this.pos, i).toString();
        this.pos += i;
        return result;
    }

    private void skipQuotedValue(char quote) throws IOException {
        char[] buffer = this.buffer;
        do {
            int c = this.pos;
            int l = this.limit;
            while (c < l) {
                int p = c + 1;
                char c2 = buffer[c];
                if (c2 == quote) {
                    this.pos = p;
                    return;
                } else if (c2 == '\\') {
                    this.pos = p;
                    readEscapeCharacter();
                    int p2 = this.pos;
                    l = this.limit;
                    c = p2;
                } else {
                    if (c2 == '\n') {
                        this.lineNumber++;
                        this.lineStart = p;
                    }
                    c = p;
                }
            }
            this.pos = c;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int i = 0;
            while (true) {
                int i2 = this.pos;
                if (i2 + i < this.limit) {
                    switch (this.buffer[i2 + i]) {
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ':
                        case ',':
                        case ':':
                        case '[':
                        case ']':
                        case '{':
                        case '}':
                            break;
                        case '#':
                        case '/':
                        case ';':
                        case '=':
                        case '\\':
                            checkLenient();
                            break;
                        default:
                            i++;
                    }
                } else {
                    this.pos = i2 + i;
                }
            }
            this.pos += i;
            return;
        } while (fillBuffer(1));
    }

    public int nextInt() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 15) {
            long j = this.peekedLong;
            int result = (int) j;
            if (j != result) {
                throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return result;
        }
        if (p == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (p == 8 || p == 9 || p == 10) {
            if (p == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(p == 8 ? '\'' : Typography.quote);
            }
            try {
                int result2 = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return result2;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + peek() + locationString());
        }
        this.peeked = 11;
        double asDouble = Double.parseDouble(this.peekedString);
        int result3 = (int) asDouble;
        if (result3 != asDouble) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return result3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.f93in.close();
    }

    public void skipValue() throws IOException {
        int count = 0;
        do {
            int p = this.peeked;
            if (p == 0) {
                p = doPeek();
            }
            if (p == 3) {
                push(1);
                count++;
            } else if (p == 1) {
                push(3);
                count++;
            } else if (p == 4) {
                this.stackSize--;
                count--;
            } else if (p == 2) {
                this.stackSize--;
                count--;
            } else if (p == 14 || p == 10) {
                skipUnquotedValue();
            } else if (p == 8 || p == 12) {
                skipQuotedValue('\'');
            } else if (p == 9 || p == 13) {
                skipQuotedValue(Typography.quote);
            } else if (p == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (count != 0);
        int[] iArr = this.pathIndices;
        int i = this.stackSize;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.pathNames[i - 1] = "null";
    }

    private void push(int newTop) {
        int i = this.stackSize;
        int[] iArr = this.stack;
        if (i == iArr.length) {
            int newLength = i * 2;
            this.stack = Arrays.copyOf(iArr, newLength);
            this.pathIndices = Arrays.copyOf(this.pathIndices, newLength);
            this.pathNames = (String[]) Arrays.copyOf(this.pathNames, newLength);
        }
        int[] iArr2 = this.stack;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        iArr2[i2] = newTop;
    }

    private boolean fillBuffer(int minimum) throws IOException {
        int i;
        int i2;
        char[] buffer = this.buffer;
        int i3 = this.lineStart;
        int i4 = this.pos;
        this.lineStart = i3 - i4;
        int i5 = this.limit;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.limit = i6;
            System.arraycopy(buffer, i4, buffer, 0, i6);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            Reader reader = this.f93in;
            int i7 = this.limit;
            int total = reader.read(buffer, i7, buffer.length - i7);
            if (total == -1) {
                return false;
            }
            i = this.limit + total;
            this.limit = i;
            if (this.lineNumber == 0 && (i2 = this.lineStart) == 0 && i > 0 && buffer[0] == '\ufeff') {
                this.pos++;
                this.lineStart = i2 + 1;
                minimum++;
                continue;
            }
        } while (i < minimum);
        return true;
    }

    private int nextNonWhitespace(boolean throwOnEof) throws IOException {
        char[] buffer = this.buffer;
        int p = this.pos;
        int l = this.limit;
        while (true) {
            if (p == l) {
                this.pos = p;
                if (fillBuffer(1)) {
                    p = this.pos;
                    l = this.limit;
                } else if (throwOnEof) {
                    throw new EOFException("End of input" + locationString());
                } else {
                    return -1;
                }
            }
            int p2 = p + 1;
            char c = buffer[p];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = p2;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.pos = p2;
                    if (p2 == l) {
                        this.pos = p2 - 1;
                        boolean charsLoaded = fillBuffer(2);
                        this.pos++;
                        if (!charsLoaded) {
                            return c;
                        }
                    }
                    checkLenient();
                    int p3 = this.pos;
                    char peek = buffer[p3];
                    switch (peek) {
                        case '*':
                            this.pos = p3 + 1;
                            if (skipTo("*/")) {
                                int p4 = this.pos + 2;
                                l = this.limit;
                                p = p4;
                                continue;
                            } else {
                                throw syntaxError("Unterminated comment");
                            }
                        case '/':
                            this.pos = p3 + 1;
                            skipToEndOfLine();
                            int p5 = this.pos;
                            l = this.limit;
                            p = p5;
                            continue;
                        default:
                            return c;
                    }
                } else if (c == '#') {
                    this.pos = p2;
                    checkLenient();
                    skipToEndOfLine();
                    int p6 = this.pos;
                    l = this.limit;
                    p = p6;
                } else {
                    this.pos = p2;
                    return c;
                }
            }
            p = p2;
        }
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos < this.limit || fillBuffer(1)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                int i2 = i + 1;
                this.pos = i2;
                c = cArr[i];
                if (c == '\n') {
                    this.lineNumber++;
                    this.lineStart = i2;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean skipTo(String toFind) throws IOException {
        int length = toFind.length();
        while (true) {
            if (this.pos + length <= this.limit || fillBuffer(length)) {
                char[] cArr = this.buffer;
                int i = this.pos;
                if (cArr[i] == '\n') {
                    this.lineNumber++;
                    this.lineStart = i + 1;
                } else {
                    for (int c = 0; c < length; c++) {
                        if (this.buffer[this.pos + c] != toFind.charAt(c)) {
                            break;
                        }
                    }
                    return true;
                }
                int c2 = this.pos;
                this.pos = c2 + 1;
            } else {
                return false;
            }
        }
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }

    String locationString() {
        int line = this.lineNumber + 1;
        int column = (this.pos - this.lineStart) + 1;
        return " at line " + line + " column " + column + " path " + getPath();
    }

    public String getPath() {
        StringBuilder result = new StringBuilder().append('$');
        int size = this.stackSize;
        for (int i = 0; i < size; i++) {
            switch (this.stack[i]) {
                case 1:
                case 2:
                    result.append('[').append(this.pathIndices[i]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    result.append('.');
                    String[] strArr = this.pathNames;
                    if (strArr[i] != null) {
                        result.append(strArr[i]);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return result.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private char readEscapeCharacter() throws IOException {
        int i;
        if (this.pos == this.limit && !fillBuffer(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char[] cArr = this.buffer;
        int i2 = this.pos;
        int i3 = i2 + 1;
        this.pos = i3;
        char escaped = cArr[i2];
        switch (escaped) {
            case '\n':
                this.lineNumber++;
                this.lineStart = i3;
                break;
            case '\"':
            case '\'':
            case '/':
            case '\\':
                break;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                if (i3 + 4 > this.limit && !fillBuffer(4)) {
                    throw syntaxError("Unterminated escape sequence");
                }
                char result = 0;
                int i4 = this.pos;
                int end = i4 + 4;
                while (i4 < end) {
                    char c = this.buffer[i4];
                    char result2 = (char) (result << 4);
                    if (c >= '0' && c <= '9') {
                        i = c - '0';
                    } else if (c >= 'a' && c <= 'f') {
                        i = (c - 'a') + 10;
                    } else if (c >= 'A' && c <= 'F') {
                        i = (c - 'A') + 10;
                    } else {
                        throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                    }
                    result = (char) (i + result2);
                    i4++;
                }
                this.pos += 4;
                return result;
            default:
                throw syntaxError("Invalid escape sequence");
        }
        return escaped;
    }

    private IOException syntaxError(String message) throws IOException {
        throw new MalformedJsonException(message + locationString());
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        int i = this.pos - 1;
        this.pos = i;
        char[] cArr = NON_EXECUTE_PREFIX;
        if (i + cArr.length > this.limit && !fillBuffer(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = NON_EXECUTE_PREFIX;
            if (i2 < cArr2.length) {
                if (this.buffer[this.pos + i2] == cArr2[i2]) {
                    i2++;
                } else {
                    return;
                }
            } else {
                int i3 = this.pos;
                this.pos = i3 + cArr2.length;
                return;
            }
        }
    }
}
