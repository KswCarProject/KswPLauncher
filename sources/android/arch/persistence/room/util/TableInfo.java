package android.arch.persistence.room.util;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.ColumnInfo;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TableInfo {
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    @Nullable
    public final Set<Index> indices;
    public final String name;

    public TableInfo(String name2, Map<String, Column> columns2, Set<ForeignKey> foreignKeys2, Set<Index> indices2) {
        this.name = name2;
        this.columns = Collections.unmodifiableMap(columns2);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys2);
        this.indices = indices2 == null ? null : Collections.unmodifiableSet(indices2);
    }

    public TableInfo(String name2, Map<String, Column> columns2, Set<ForeignKey> foreignKeys2) {
        this(name2, columns2, foreignKeys2, Collections.emptySet());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        if (this.name == null ? tableInfo.name != null : !this.name.equals(tableInfo.name)) {
            return false;
        }
        if (this.columns == null ? tableInfo.columns != null : !this.columns.equals(tableInfo.columns)) {
            return false;
        }
        if (this.foreignKeys == null ? tableInfo.foreignKeys != null : !this.foreignKeys.equals(tableInfo.foreignKeys)) {
            return false;
        }
        if (this.indices == null || tableInfo.indices == null) {
            return true;
        }
        return this.indices.equals(tableInfo.indices);
    }

    public int hashCode() {
        int i = 0;
        int result = (((this.name != null ? this.name.hashCode() : 0) * 31) + (this.columns != null ? this.columns.hashCode() : 0)) * 31;
        if (this.foreignKeys != null) {
            i = this.foreignKeys.hashCode();
        }
        return result + i;
    }

    public String toString() {
        return "TableInfo{name='" + this.name + '\'' + ", columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName), readIndices(database, tableName));
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        int idColumnIndex;
        Set<ForeignKey> foreignKeys2 = new HashSet<>();
        Cursor cursor = database.query("PRAGMA foreign_key_list(`" + tableName + "`)");
        try {
            int idColumnIndex2 = cursor.getColumnIndex("id");
            int seqColumnIndex = cursor.getColumnIndex("seq");
            int tableColumnIndex = cursor.getColumnIndex("table");
            int onDeleteColumnIndex = cursor.getColumnIndex("on_delete");
            int onUpdateColumnIndex = cursor.getColumnIndex("on_update");
            List<ForeignKeyWithSequence> ordered = readForeignKeyFieldMappings(cursor);
            int count = cursor.getCount();
            int position = 0;
            while (position < count) {
                cursor.moveToPosition(position);
                if (cursor.getInt(seqColumnIndex) != 0) {
                    idColumnIndex = idColumnIndex2;
                } else {
                    int id = cursor.getInt(idColumnIndex2);
                    List<String> myColumns = new ArrayList<>();
                    List<String> refColumns = new ArrayList<>();
                    for (ForeignKeyWithSequence key : ordered) {
                        int idColumnIndex3 = idColumnIndex2;
                        if (key.mId == id) {
                            myColumns.add(key.mFrom);
                            refColumns.add(key.mTo);
                        }
                        idColumnIndex2 = idColumnIndex3;
                        String str = tableName;
                    }
                    idColumnIndex = idColumnIndex2;
                    foreignKeys2.add(new ForeignKey(cursor.getString(tableColumnIndex), cursor.getString(onDeleteColumnIndex), cursor.getString(onUpdateColumnIndex), myColumns, refColumns));
                }
                position++;
                idColumnIndex2 = idColumnIndex;
                String str2 = tableName;
            }
            return foreignKeys2;
        } finally {
            cursor.close();
        }
    }

    private static List<ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int idColumnIndex = cursor.getColumnIndex("id");
        int seqColumnIndex = cursor.getColumnIndex("seq");
        int fromColumnIndex = cursor.getColumnIndex("from");
        int toColumnIndex = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        List<ForeignKeyWithSequence> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            result.add(new ForeignKeyWithSequence(cursor.getInt(idColumnIndex), cursor.getInt(seqColumnIndex), cursor.getString(fromColumnIndex), cursor.getString(toColumnIndex)));
        }
        Collections.sort(result);
        return result;
    }

    private static Map<String, Column> readColumns(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA table_info(`" + tableName + "`)");
        Map<String, Column> columns2 = new HashMap<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int notNullIndex = cursor.getColumnIndex("notnull");
                int pkIndex = cursor.getColumnIndex("pk");
                while (cursor.moveToNext()) {
                    String name2 = cursor.getString(nameIndex);
                    columns2.put(name2, new Column(name2, cursor.getString(typeIndex), cursor.getInt(notNullIndex) != 0, cursor.getInt(pkIndex)));
                }
            }
            return columns2;
        } finally {
            cursor.close();
        }
    }

    @Nullable
    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA index_list(`" + tableName + "`)");
        try {
            int nameColumnIndex = cursor.getColumnIndex("name");
            int originColumnIndex = cursor.getColumnIndex("origin");
            int uniqueIndex = cursor.getColumnIndex("unique");
            if (!(nameColumnIndex == -1 || originColumnIndex == -1)) {
                if (uniqueIndex != -1) {
                    HashSet<Index> indices2 = new HashSet<>();
                    while (cursor.moveToNext()) {
                        if ("c".equals(cursor.getString(originColumnIndex))) {
                            String name2 = cursor.getString(nameColumnIndex);
                            boolean unique = true;
                            if (cursor.getInt(uniqueIndex) != 1) {
                                unique = false;
                            }
                            Index index = readIndex(database, name2, unique);
                            if (index != null) {
                                indices2.add(index);
                            }
                        }
                    }
                    cursor.close();
                    return indices2;
                }
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    @Nullable
    private static Index readIndex(SupportSQLiteDatabase database, String name2, boolean unique) {
        Cursor cursor = database.query("PRAGMA index_xinfo(`" + name2 + "`)");
        try {
            int seqnoColumnIndex = cursor.getColumnIndex("seqno");
            int cidColumnIndex = cursor.getColumnIndex("cid");
            int nameColumnIndex = cursor.getColumnIndex("name");
            if (!(seqnoColumnIndex == -1 || cidColumnIndex == -1)) {
                if (nameColumnIndex != -1) {
                    TreeMap<Integer, String> results = new TreeMap<>();
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(cidColumnIndex) >= 0) {
                            int seq = cursor.getInt(seqnoColumnIndex);
                            results.put(Integer.valueOf(seq), cursor.getString(nameColumnIndex));
                        }
                    }
                    List<String> columns2 = new ArrayList<>(results.size());
                    columns2.addAll(results.values());
                    Index index = new Index(name2, unique, columns2);
                    cursor.close();
                    return index;
                }
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    public static class Column {
        @ColumnInfo.SQLiteTypeAffinity
        public final int affinity;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String name2, String type2, boolean notNull2, int primaryKeyPosition2) {
            this.name = name2;
            this.type = type2;
            this.notNull = notNull2;
            this.primaryKeyPosition = primaryKeyPosition2;
            this.affinity = findAffinity(type2);
        }

        @ColumnInfo.SQLiteTypeAffinity
        private static int findAffinity(@Nullable String type2) {
            if (type2 == null) {
                return 5;
            }
            String uppercaseType = type2.toUpperCase(Locale.US);
            if (uppercaseType.contains("INT")) {
                return 3;
            }
            if (uppercaseType.contains("CHAR") || uppercaseType.contains("CLOB") || uppercaseType.contains("TEXT")) {
                return 2;
            }
            if (uppercaseType.contains("BLOB")) {
                return 5;
            }
            if (uppercaseType.contains("REAL") || uppercaseType.contains("FLOA") || uppercaseType.contains("DOUB")) {
                return 4;
            }
            return 1;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Column column = (Column) o;
            if (Build.VERSION.SDK_INT >= 20) {
                if (this.primaryKeyPosition != column.primaryKeyPosition) {
                    return false;
                }
            } else if (isPrimaryKey() != column.isPrimaryKey()) {
                return false;
            }
            if (!this.name.equals(column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.affinity == column.affinity) {
                return true;
            }
            return false;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            return "Column{name='" + this.name + '\'' + ", type='" + this.type + '\'' + ", affinity='" + this.affinity + '\'' + ", notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + '}';
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class ForeignKey {
        @NonNull
        public final List<String> columnNames;
        @NonNull
        public final String onDelete;
        @NonNull
        public final String onUpdate;
        @NonNull
        public final List<String> referenceColumnNames;
        @NonNull
        public final String referenceTable;

        public ForeignKey(@NonNull String referenceTable2, @NonNull String onDelete2, @NonNull String onUpdate2, @NonNull List<String> columnNames2, @NonNull List<String> referenceColumnNames2) {
            this.referenceTable = referenceTable2;
            this.onDelete = onDelete2;
            this.onUpdate = onUpdate2;
            this.columnNames = Collections.unmodifiableList(columnNames2);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames2);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ForeignKey that = (ForeignKey) o;
            if (this.referenceTable.equals(that.referenceTable) && this.onDelete.equals(that.onDelete) && this.onUpdate.equals(that.onUpdate) && this.columnNames.equals(that.columnNames)) {
                return this.referenceColumnNames.equals(that.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + '\'' + ", onDelete='" + this.onDelete + '\'' + ", onUpdate='" + this.onUpdate + '\'' + ", columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
        final String mFrom;
        final int mId;
        final int mSequence;
        final String mTo;

        ForeignKeyWithSequence(int id, int sequence, String from, String to) {
            this.mId = id;
            this.mSequence = sequence;
            this.mFrom = from;
            this.mTo = to;
        }

        public int compareTo(@NonNull ForeignKeyWithSequence o) {
            int idCmp = this.mId - o.mId;
            if (idCmp == 0) {
                return this.mSequence - o.mSequence;
            }
            return idCmp;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name2, boolean unique2, List<String> columns2) {
            this.name = name2;
            this.unique = unique2;
            this.columns = columns2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Index index = (Index) o;
            if (this.unique != index.unique || !this.columns.equals(index.columns)) {
                return false;
            }
            if (this.name.startsWith(DEFAULT_PREFIX)) {
                return index.name.startsWith(DEFAULT_PREFIX);
            }
            return this.name.equals(index.name);
        }

        public int hashCode() {
            int result;
            if (this.name.startsWith(DEFAULT_PREFIX)) {
                result = DEFAULT_PREFIX.hashCode();
            } else {
                result = this.name.hashCode();
            }
            return (((result * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + '\'' + ", unique=" + this.unique + ", columns=" + this.columns + '}';
        }
    }
}
