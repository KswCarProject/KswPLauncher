package android.arch.persistence.room;

import android.arch.persistence.db.SupportSQLiteProgram;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    private static final int BLOB = 5;
    @VisibleForTesting
    static final int DESIRED_POOL_SIZE = 10;
    private static final int DOUBLE = 3;
    private static final int LONG = 2;
    private static final int NULL = 1;
    @VisibleForTesting
    static final int POOL_LIMIT = 15;
    private static final int STRING = 4;
    @VisibleForTesting
    static final TreeMap<Integer, RoomSQLiteQuery> sQueryPool = new TreeMap<>();
    @VisibleForTesting
    int mArgCount;
    private final int[] mBindingTypes;
    @VisibleForTesting
    final byte[][] mBlobBindings;
    @VisibleForTesting
    final int mCapacity;
    @VisibleForTesting
    final double[] mDoubleBindings;
    @VisibleForTesting
    final long[] mLongBindings;
    private volatile String mQuery;
    @VisibleForTesting
    final String[] mStringBindings;

    public static RoomSQLiteQuery copyFrom(SupportSQLiteQuery supportSQLiteQuery) {
        RoomSQLiteQuery query = acquire(supportSQLiteQuery.getSql(), supportSQLiteQuery.getArgCount());
        supportSQLiteQuery.bindTo(new SupportSQLiteProgram(query) {
            final /* synthetic */ RoomSQLiteQuery val$query;

            {
                this.val$query = r1;
            }

            public void bindNull(int index) {
                this.val$query.bindNull(index);
            }

            public void bindLong(int index, long value) {
                this.val$query.bindLong(index, value);
            }

            public void bindDouble(int index, double value) {
                this.val$query.bindDouble(index, value);
            }

            public void bindString(int index, String value) {
                this.val$query.bindString(index, value);
            }

            public void bindBlob(int index, byte[] value) {
                this.val$query.bindBlob(index, value);
            }

            public void clearBindings() {
                this.val$query.clearBindings();
            }

            public void close() {
            }
        });
        return query;
    }

    public static RoomSQLiteQuery acquire(String query, int argumentCount) {
        synchronized (sQueryPool) {
            Map.Entry<Integer, RoomSQLiteQuery> entry = sQueryPool.ceilingEntry(Integer.valueOf(argumentCount));
            if (entry != null) {
                sQueryPool.remove(entry.getKey());
                RoomSQLiteQuery sqliteQuery = entry.getValue();
                sqliteQuery.init(query, argumentCount);
                return sqliteQuery;
            }
            RoomSQLiteQuery sqLiteQuery = new RoomSQLiteQuery(argumentCount);
            sqLiteQuery.init(query, argumentCount);
            return sqLiteQuery;
        }
    }

    private RoomSQLiteQuery(int capacity) {
        this.mCapacity = capacity;
        int limit = capacity + 1;
        this.mBindingTypes = new int[limit];
        this.mLongBindings = new long[limit];
        this.mDoubleBindings = new double[limit];
        this.mStringBindings = new String[limit];
        this.mBlobBindings = new byte[limit][];
    }

    /* access modifiers changed from: package-private */
    public void init(String query, int argCount) {
        this.mQuery = query;
        this.mArgCount = argCount;
    }

    public void release() {
        synchronized (sQueryPool) {
            sQueryPool.put(Integer.valueOf(this.mCapacity), this);
            prunePoolLocked();
        }
    }

    private static void prunePoolLocked() {
        if (sQueryPool.size() > 15) {
            int toBeRemoved = sQueryPool.size() - 10;
            Iterator<Integer> iterator = sQueryPool.descendingKeySet().iterator();
            while (true) {
                int toBeRemoved2 = toBeRemoved - 1;
                if (toBeRemoved > 0) {
                    iterator.next();
                    iterator.remove();
                    toBeRemoved = toBeRemoved2;
                } else {
                    return;
                }
            }
        }
    }

    public String getSql() {
        return this.mQuery;
    }

    public int getArgCount() {
        return this.mArgCount;
    }

    public void bindTo(SupportSQLiteProgram program) {
        for (int index = 1; index <= this.mArgCount; index++) {
            switch (this.mBindingTypes[index]) {
                case 1:
                    program.bindNull(index);
                    break;
                case 2:
                    program.bindLong(index, this.mLongBindings[index]);
                    break;
                case 3:
                    program.bindDouble(index, this.mDoubleBindings[index]);
                    break;
                case 4:
                    program.bindString(index, this.mStringBindings[index]);
                    break;
                case 5:
                    program.bindBlob(index, this.mBlobBindings[index]);
                    break;
            }
        }
    }

    public void bindNull(int index) {
        this.mBindingTypes[index] = 1;
    }

    public void bindLong(int index, long value) {
        this.mBindingTypes[index] = 2;
        this.mLongBindings[index] = value;
    }

    public void bindDouble(int index, double value) {
        this.mBindingTypes[index] = 3;
        this.mDoubleBindings[index] = value;
    }

    public void bindString(int index, String value) {
        this.mBindingTypes[index] = 4;
        this.mStringBindings[index] = value;
    }

    public void bindBlob(int index, byte[] value) {
        this.mBindingTypes[index] = 5;
        this.mBlobBindings[index] = value;
    }

    public void close() {
    }

    public void copyArgumentsFrom(RoomSQLiteQuery other) {
        int argCount = other.getArgCount() + 1;
        System.arraycopy(other.mBindingTypes, 0, this.mBindingTypes, 0, argCount);
        System.arraycopy(other.mLongBindings, 0, this.mLongBindings, 0, argCount);
        System.arraycopy(other.mStringBindings, 0, this.mStringBindings, 0, argCount);
        System.arraycopy(other.mBlobBindings, 0, this.mBlobBindings, 0, argCount);
        System.arraycopy(other.mDoubleBindings, 0, this.mDoubleBindings, 0, argCount);
    }

    public void clearBindings() {
        Arrays.fill(this.mBindingTypes, 1);
        Arrays.fill(this.mStringBindings, (Object) null);
        Arrays.fill(this.mBlobBindings, (Object) null);
        this.mQuery = null;
    }
}
