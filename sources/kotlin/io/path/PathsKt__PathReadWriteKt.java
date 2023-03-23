package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a:\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\u0015\u001a:\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\u0018\u001a=\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\bø\u0001\u0000\u001a&\u0010 \u001a\u00020!*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010\"\u001a&\u0010#\u001a\u00020$*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010%\u001a\r\u0010&\u001a\u00020\u0004*\u00020\u0002H\b\u001a\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0016\u0010)\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a0\u0010*\u001a\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010,\u001a?\u0010-\u001a\u0002H.\"\u0004\b\u0000\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b\u0012\u0004\u0012\u0002H.0\u001bH\bø\u0001\u0000¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00104\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u00105\u001a7\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007¢\u0006\u0002\u00107\u001a0\u00108\u001a\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\b¢\u0006\u0002\u0010:\u0002\u0007\n\u0005\b20\u0001¨\u0006;"}, d2 = {"appendBytes", "", "Ljava/nio/file/Path;", "array", "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", "T", "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"}, k = 5, mv = {1, 6, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
/* compiled from: PathReadWrite.kt */
class PathsKt__PathReadWriteKt {
    static /* synthetic */ InputStreamReader reader$default(Path $this$reader_u24default, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$reader_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new InputStreamReader(Files.newInputStream($this$reader_u24default, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    private static final InputStreamReader reader(Path $this$reader, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$reader, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new InputStreamReader(Files.newInputStream($this$reader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(Path $this$bufferedReader_u24default, Charset charset, int bufferSize, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Intrinsics.checkNotNullParameter($this$bufferedReader_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream($this$bufferedReader_u24default, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    private static final BufferedReader bufferedReader(Path $this$bufferedReader, Charset charset, int bufferSize, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$bufferedReader, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedReader(new InputStreamReader(Files.newInputStream($this$bufferedReader, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    static /* synthetic */ OutputStreamWriter writer$default(Path $this$writer_u24default, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$writer_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new OutputStreamWriter(Files.newOutputStream($this$writer_u24default, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    private static final OutputStreamWriter writer(Path $this$writer, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$writer, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new OutputStreamWriter(Files.newOutputStream($this$writer, (OpenOption[]) Arrays.copyOf(options, options.length)), charset);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(Path $this$bufferedWriter_u24default, Charset charset, int bufferSize, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Intrinsics.checkNotNullParameter($this$bufferedWriter_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter_u24default, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    private static final BufferedWriter bufferedWriter(Path $this$bufferedWriter, Charset charset, int bufferSize, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$bufferedWriter, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream($this$bufferedWriter, (OpenOption[]) Arrays.copyOf(options, options.length)), charset), bufferSize);
    }

    private static final byte[] readBytes(Path $this$readBytes) throws IOException {
        Intrinsics.checkNotNullParameter($this$readBytes, "<this>");
        byte[] readAllBytes = Files.readAllBytes($this$readBytes);
        Intrinsics.checkNotNullExpressionValue(readAllBytes, "readAllBytes(this)");
        return readAllBytes;
    }

    private static final void writeBytes(Path $this$writeBytes, byte[] array, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeBytes, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        Intrinsics.checkNotNullParameter(options, "options");
        Files.write($this$writeBytes, array, (OpenOption[]) Arrays.copyOf(options, options.length));
    }

    private static final void appendBytes(Path $this$appendBytes, byte[] array) throws IOException {
        Intrinsics.checkNotNullParameter($this$appendBytes, "<this>");
        Intrinsics.checkNotNullParameter(array, "array");
        Files.write($this$appendBytes, array, new OpenOption[]{StandardOpenOption.APPEND});
    }

    public static /* synthetic */ String readText$default(Path path, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return PathsKt.readText(path, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readText(java.nio.file.Path r4, java.nio.charset.Charset r5) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 0
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r0]
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r1, r0)
            java.nio.file.OpenOption[] r0 = (java.nio.file.OpenOption[]) r0
            java.io.InputStream r0 = java.nio.file.Files.newInputStream(r4, r0)
            r2.<init>(r0, r5)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0 = r2
            java.io.InputStreamReader r0 = (java.io.InputStreamReader) r0     // Catch:{ all -> 0x002e }
            r1 = 0
            r3 = r0
            java.io.Reader r3 = (java.io.Reader) r3     // Catch:{ all -> 0x002e }
            java.lang.String r3 = kotlin.io.TextStreamsKt.readText(r3)     // Catch:{ all -> 0x002e }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            return r3
        L_0x002e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.readText(java.nio.file.Path, java.nio.charset.Charset):java.lang.String");
    }

    public static /* synthetic */ void writeText$default(Path path, CharSequence charSequence, Charset charset, OpenOption[] openOptionArr, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.writeText(path, charSequence, charset, openOptionArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void writeText(java.nio.file.Path r3, java.lang.CharSequence r4, java.nio.charset.Charset r5, java.nio.file.OpenOption... r6) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            int r0 = r6.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r6, r0)
            java.nio.file.OpenOption[] r0 = (java.nio.file.OpenOption[]) r0
            java.io.OutputStream r0 = java.nio.file.Files.newOutputStream(r3, r0)
            java.lang.String r1 = "newOutputStream(this, *options)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
            r1.<init>(r0, r5)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r0 = r1
            java.io.OutputStreamWriter r0 = (java.io.OutputStreamWriter) r0     // Catch:{ all -> 0x0038 }
            r2 = 0
            r0.append(r4)     // Catch:{ all -> 0x0038 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            return
        L_0x0038:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003a }
        L_0x003a:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.writeText(java.nio.file.Path, java.lang.CharSequence, java.nio.charset.Charset, java.nio.file.OpenOption[]):void");
    }

    public static /* synthetic */ void appendText$default(Path path, CharSequence charSequence, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        PathsKt.appendText(path, charSequence, charset);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0039, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void appendText(java.nio.file.Path r3, java.lang.CharSequence r4, java.nio.charset.Charset r5) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "text"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 1
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r1 = java.nio.file.StandardOpenOption.APPEND
            java.nio.file.OpenOption r1 = (java.nio.file.OpenOption) r1
            r2 = 0
            r0[r2] = r1
            java.io.OutputStream r0 = java.nio.file.Files.newOutputStream(r3, r0)
            java.lang.String r1 = "newOutputStream(this, StandardOpenOption.APPEND)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
            r1.<init>(r0, r5)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r0 = r1
            java.io.OutputStreamWriter r0 = (java.io.OutputStreamWriter) r0     // Catch:{ all -> 0x0036 }
            r2 = 0
            r0.append(r4)     // Catch:{ all -> 0x0036 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            return
        L_0x0036:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.appendText(java.nio.file.Path, java.lang.CharSequence, java.nio.charset.Charset):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r1, r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void forEachLine$default(java.nio.file.Path r11, java.nio.charset.Charset r12, kotlin.jvm.functions.Function1 r13, int r14, java.lang.Object r15) throws java.io.IOException {
        /*
            r15 = 1
            r14 = r14 & r15
            if (r14 == 0) goto L_0x0006
            java.nio.charset.Charset r12 = kotlin.text.Charsets.UTF_8
        L_0x0006:
            java.lang.String r14 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r14)
            java.lang.String r14 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r14)
            java.lang.String r14 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r14)
            java.io.BufferedReader r14 = java.nio.file.Files.newBufferedReader(r11, r12)
            java.lang.String r0 = "newBufferedReader(this, charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r0)
            java.io.Reader r14 = (java.io.Reader) r14
            r0 = 0
            r1 = r14
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = 0
            r3 = r1
            java.io.BufferedReader r3 = (java.io.BufferedReader) r3     // Catch:{ all -> 0x0052 }
            r4 = 0
            kotlin.sequences.Sequence r5 = kotlin.io.TextStreamsKt.lineSequence(r3)     // Catch:{ all -> 0x0052 }
            r6 = 0
            r7 = r5
            r8 = 0
            java.util.Iterator r9 = r7.iterator()     // Catch:{ all -> 0x0052 }
        L_0x0036:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0052 }
            if (r10 == 0) goto L_0x0044
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0052 }
            r13.invoke(r10)     // Catch:{ all -> 0x0052 }
            goto L_0x0036
        L_0x0044:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0052 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r15)
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r15)
            return
        L_0x0052:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r3 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r15)
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r15)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.forEachLine$default(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0050, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r2, r4);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0059, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void forEachLine(java.nio.file.Path r12, java.nio.charset.Charset r13, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r14) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.io.BufferedReader r0 = java.nio.file.Files.newBufferedReader(r12, r13)
            java.lang.String r1 = "newBufferedReader(this, charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.io.Reader r0 = (java.io.Reader) r0
            r1 = 0
            r2 = r0
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 1
            r4 = r2
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4     // Catch:{ all -> 0x004d }
            r5 = 0
            kotlin.sequences.Sequence r6 = kotlin.io.TextStreamsKt.lineSequence(r4)     // Catch:{ all -> 0x004d }
            r7 = 0
            r8 = r6
            r9 = 0
            java.util.Iterator r10 = r8.iterator()     // Catch:{ all -> 0x004d }
        L_0x0030:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x004d }
            if (r11 == 0) goto L_0x003e
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x004d }
            r14.invoke(r11)     // Catch:{ all -> 0x004d }
            goto L_0x0030
        L_0x003e:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004d }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            r4 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            return
        L_0x004d:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004f }
        L_0x004f:
            r5 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlin.io.CloseableKt.closeFinally(r2, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.forEachLine(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1):void");
    }

    private static final InputStream inputStream(Path $this$inputStream, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$inputStream, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        InputStream newInputStream = Files.newInputStream($this$inputStream, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(newInputStream, "newInputStream(this, *options)");
        return newInputStream;
    }

    private static final OutputStream outputStream(Path $this$outputStream, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$outputStream, "<this>");
        Intrinsics.checkNotNullParameter(options, "options");
        OutputStream newOutputStream = Files.newOutputStream($this$outputStream, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(newOutputStream, "newOutputStream(this, *options)");
        return newOutputStream;
    }

    static /* synthetic */ List readLines$default(Path $this$readLines_u24default, Charset charset, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$readLines_u24default, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        List<String> readAllLines = Files.readAllLines($this$readLines_u24default, charset);
        Intrinsics.checkNotNullExpressionValue(readAllLines, "readAllLines(this, charset)");
        return readAllLines;
    }

    private static final List<String> readLines(Path $this$readLines, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter($this$readLines, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        List<String> readAllLines = Files.readAllLines($this$readLines, charset);
        Intrinsics.checkNotNullExpressionValue(readAllLines, "readAllLines(this, charset)");
        return readAllLines;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r7, r0);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object useLines$default(java.nio.file.Path r4, java.nio.charset.Charset r5, kotlin.jvm.functions.Function1 r6, int r7, java.lang.Object r8) throws java.io.IOException {
        /*
            r8 = 1
            r7 = r7 & r8
            if (r7 == 0) goto L_0x0006
            java.nio.charset.Charset r5 = kotlin.text.Charsets.UTF_8
        L_0x0006:
            java.lang.String r7 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r7)
            java.lang.String r7 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r7)
            java.lang.String r7 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r7)
            java.io.BufferedReader r7 = java.nio.file.Files.newBufferedReader(r4, r5)
            java.io.Closeable r7 = (java.io.Closeable) r7
            r0 = 0
            r1 = r7
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x0037 }
            r2 = 0
            java.lang.String r3 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)     // Catch:{ all -> 0x0037 }
            kotlin.sequences.Sequence r3 = kotlin.io.TextStreamsKt.lineSequence(r1)     // Catch:{ all -> 0x0037 }
            java.lang.Object r3 = r6.invoke(r3)     // Catch:{ all -> 0x0037 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            kotlin.io.CloseableKt.closeFinally(r7, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            return r3
        L_0x0037:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r1 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r8)
            kotlin.io.CloseableKt.closeFinally(r7, r0)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r8)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.useLines$default(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003e, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
        kotlin.io.CloseableKt.closeFinally(r0, r2);
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <T> T useLines(java.nio.file.Path r5, java.nio.charset.Charset r6, kotlin.jvm.functions.Function1<? super kotlin.sequences.Sequence<java.lang.String>, ? extends T> r7) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "block"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.io.BufferedReader r0 = java.nio.file.Files.newBufferedReader(r5, r6)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 1
            r2 = r0
            java.io.BufferedReader r2 = (java.io.BufferedReader) r2     // Catch:{ all -> 0x0032 }
            r3 = 0
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ all -> 0x0032 }
            kotlin.sequences.Sequence r4 = kotlin.io.TextStreamsKt.lineSequence(r2)     // Catch:{ all -> 0x0032 }
            java.lang.Object r4 = r7.invoke(r4)     // Catch:{ all -> 0x0032 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r4
        L_0x0032:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r3 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathsKt__PathReadWriteKt.useLines(java.nio.file.Path, java.nio.charset.Charset, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    static /* synthetic */ Path writeLines$default(Path $this$writeLines_u24default, Iterable lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$writeLines_u24default, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path write = Files.write($this$writeLines_u24default, lines, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines, charset, *options)");
        return write;
    }

    private static final Path writeLines(Path $this$writeLines, Iterable<? extends CharSequence> lines, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeLines, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path write = Files.write($this$writeLines, lines, charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines, charset, *options)");
        return write;
    }

    static /* synthetic */ Path writeLines$default(Path $this$writeLines_u24default, Sequence lines, Charset charset, OpenOption[] options, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$writeLines_u24default, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path write = Files.write($this$writeLines_u24default, SequencesKt.asIterable(lines), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines.asIterable(), charset, *options)");
        return write;
    }

    private static final Path writeLines(Path $this$writeLines, Sequence<? extends CharSequence> lines, Charset charset, OpenOption... options) throws IOException {
        Intrinsics.checkNotNullParameter($this$writeLines, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Intrinsics.checkNotNullParameter(options, "options");
        Path write = Files.write($this$writeLines, SequencesKt.asIterable(lines), charset, (OpenOption[]) Arrays.copyOf(options, options.length));
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines.asIterable(), charset, *options)");
        return write;
    }

    static /* synthetic */ Path appendLines$default(Path $this$appendLines_u24default, Iterable lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$appendLines_u24default, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path write = Files.write($this$appendLines_u24default, lines, charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines, chars…tandardOpenOption.APPEND)");
        return write;
    }

    private static final Path appendLines(Path $this$appendLines, Iterable<? extends CharSequence> lines, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter($this$appendLines, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path write = Files.write($this$appendLines, lines, charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines, chars…tandardOpenOption.APPEND)");
        return write;
    }

    static /* synthetic */ Path appendLines$default(Path $this$appendLines_u24default, Sequence lines, Charset charset, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkNotNullParameter($this$appendLines_u24default, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path write = Files.write($this$appendLines_u24default, SequencesKt.asIterable(lines), charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines.asIter…tandardOpenOption.APPEND)");
        return write;
    }

    private static final Path appendLines(Path $this$appendLines, Sequence<? extends CharSequence> lines, Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter($this$appendLines, "<this>");
        Intrinsics.checkNotNullParameter(lines, "lines");
        Intrinsics.checkNotNullParameter(charset, "charset");
        Path write = Files.write($this$appendLines, SequencesKt.asIterable(lines), charset, new OpenOption[]{StandardOpenOption.APPEND});
        Intrinsics.checkNotNullExpressionValue(write, "write(this, lines.asIter…tandardOpenOption.APPEND)");
        return write;
    }
}
