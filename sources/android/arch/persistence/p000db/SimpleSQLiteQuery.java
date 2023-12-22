package android.arch.persistence.p000db;

/* renamed from: android.arch.persistence.db.SimpleSQLiteQuery */
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    private final Object[] mBindArgs;
    private final String mQuery;

    public SimpleSQLiteQuery(String query, Object[] bindArgs) {
        this.mQuery = query;
        this.mBindArgs = bindArgs;
    }

    public SimpleSQLiteQuery(String query) {
        this(query, null);
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteQuery
    public String getSql() {
        return this.mQuery;
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram statement) {
        bind(statement, this.mBindArgs);
    }

    @Override // android.arch.persistence.p000db.SupportSQLiteQuery
    public int getArgCount() {
        Object[] objArr = this.mBindArgs;
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public static void bind(SupportSQLiteProgram statement, Object[] bindArgs) {
        if (bindArgs == null) {
            return;
        }
        int limit = bindArgs.length;
        for (int i = 0; i < limit; i++) {
            Object arg = bindArgs[i];
            bind(statement, i + 1, arg);
        }
    }

    private static void bind(SupportSQLiteProgram statement, int index, Object arg) {
        if (arg == null) {
            statement.bindNull(index);
        } else if (arg instanceof byte[]) {
            statement.bindBlob(index, (byte[]) arg);
        } else if (arg instanceof Float) {
            statement.bindDouble(index, ((Float) arg).floatValue());
        } else if (arg instanceof Double) {
            statement.bindDouble(index, ((Double) arg).doubleValue());
        } else if (arg instanceof Long) {
            statement.bindLong(index, ((Long) arg).longValue());
        } else if (arg instanceof Integer) {
            statement.bindLong(index, ((Integer) arg).intValue());
        } else if (arg instanceof Short) {
            statement.bindLong(index, ((Short) arg).shortValue());
        } else if (arg instanceof Byte) {
            statement.bindLong(index, ((Byte) arg).byteValue());
        } else if (arg instanceof String) {
            statement.bindString(index, (String) arg);
        } else if (arg instanceof Boolean) {
            statement.bindLong(index, ((Boolean) arg).booleanValue() ? 1L : 0L);
        } else {
            throw new IllegalArgumentException("Cannot bind " + arg + " at index " + index + " Supported types: null, byte[], float, double, long, int, short, byte, string");
        }
    }
}
