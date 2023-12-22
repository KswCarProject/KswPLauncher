package android.arch.persistence.room.util;

import android.arch.persistence.p000db.SupportSQLiteDatabase;
import android.database.Cursor;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class TableInfo {
    public final Map<String, Column> columns;
    public final Set<ForeignKey> foreignKeys;
    public final Set<Index> indices;
    public final String name;

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys, Set<Index> indices) {
        this.name = name;
        this.columns = Collections.unmodifiableMap(columns);
        this.foreignKeys = Collections.unmodifiableSet(foreignKeys);
        this.indices = indices == null ? null : Collections.unmodifiableSet(indices);
    }

    public TableInfo(String name, Map<String, Column> columns, Set<ForeignKey> foreignKeys) {
        this(name, columns, foreignKeys, Collections.emptySet());
    }

    public boolean equals(Object o) {
        Set<Index> set;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) o;
        String str = this.name;
        if (str == null ? tableInfo.name != null : !str.equals(tableInfo.name)) {
            return false;
        }
        Map<String, Column> map = this.columns;
        if (map == null ? tableInfo.columns != null : !map.equals(tableInfo.columns)) {
            return false;
        }
        Set<ForeignKey> set2 = this.foreignKeys;
        if (set2 == null ? tableInfo.foreignKeys != null : !set2.equals(tableInfo.foreignKeys)) {
            return false;
        }
        Set<Index> set3 = this.indices;
        if (set3 == null || (set = tableInfo.indices) == null) {
            return true;
        }
        return set3.equals(set);
    }

    public int hashCode() {
        String str = this.name;
        int result = str != null ? str.hashCode() : 0;
        int i = result * 31;
        Map<String, Column> map = this.columns;
        int result2 = i + (map != null ? map.hashCode() : 0);
        int result3 = result2 * 31;
        Set<ForeignKey> set = this.foreignKeys;
        return result3 + (set != null ? set.hashCode() : 0);
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }

    public static TableInfo read(SupportSQLiteDatabase database, String tableName) {
        Map<String, Column> columns = readColumns(database, tableName);
        Set<ForeignKey> foreignKeys = readForeignKeys(database, tableName);
        Set<Index> indices = readIndices(database, tableName);
        return new TableInfo(tableName, columns, foreignKeys, indices);
    }

    private static Set<ForeignKey> readForeignKeys(SupportSQLiteDatabase database, String tableName) {
        int idColumnIndex;
        Set<ForeignKey> foreignKeys = new HashSet<>();
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
                int seq = cursor.getInt(seqColumnIndex);
                if (seq != 0) {
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
                    }
                    idColumnIndex = idColumnIndex2;
                    foreignKeys.add(new ForeignKey(cursor.getString(tableColumnIndex), cursor.getString(onDeleteColumnIndex), cursor.getString(onUpdateColumnIndex), myColumns, refColumns));
                }
                position++;
                idColumnIndex2 = idColumnIndex;
            }
            return foreignKeys;
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
        Map<String, Column> columns = new HashMap<>();
        try {
            if (cursor.getColumnCount() > 0) {
                int nameIndex = cursor.getColumnIndex("name");
                int typeIndex = cursor.getColumnIndex("type");
                int notNullIndex = cursor.getColumnIndex("notnull");
                int pkIndex = cursor.getColumnIndex("pk");
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameIndex);
                    String type = cursor.getString(typeIndex);
                    boolean notNull = cursor.getInt(notNullIndex) != 0;
                    int primaryKeyPosition = cursor.getInt(pkIndex);
                    columns.put(name, new Column(name, type, notNull, primaryKeyPosition));
                }
            }
            return columns;
        } finally {
            cursor.close();
        }
    }

    private static Set<Index> readIndices(SupportSQLiteDatabase database, String tableName) {
        Cursor cursor = database.query("PRAGMA index_list(`" + tableName + "`)");
        try {
            int nameColumnIndex = cursor.getColumnIndex("name");
            int originColumnIndex = cursor.getColumnIndex("origin");
            int uniqueIndex = cursor.getColumnIndex("unique");
            if (nameColumnIndex != -1 && originColumnIndex != -1 && uniqueIndex != -1) {
                HashSet<Index> indices = new HashSet<>();
                while (cursor.moveToNext()) {
                    String origin = cursor.getString(originColumnIndex);
                    if ("c".equals(origin)) {
                        String name = cursor.getString(nameColumnIndex);
                        boolean z = true;
                        if (cursor.getInt(uniqueIndex) != 1) {
                            z = false;
                        }
                        boolean unique = z;
                        Index index = readIndex(database, name, unique);
                        if (index == null) {
                            return null;
                        }
                        indices.add(index);
                    }
                }
                return indices;
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    private static Index readIndex(SupportSQLiteDatabase database, String name, boolean unique) {
        Cursor cursor = database.query("PRAGMA index_xinfo(`" + name + "`)");
        try {
            int seqnoColumnIndex = cursor.getColumnIndex("seqno");
            int cidColumnIndex = cursor.getColumnIndex("cid");
            int nameColumnIndex = cursor.getColumnIndex("name");
            if (seqnoColumnIndex != -1 && cidColumnIndex != -1 && nameColumnIndex != -1) {
                TreeMap<Integer, String> results = new TreeMap<>();
                while (cursor.moveToNext()) {
                    int cid = cursor.getInt(cidColumnIndex);
                    if (cid >= 0) {
                        int seq = cursor.getInt(seqnoColumnIndex);
                        String columnName = cursor.getString(nameColumnIndex);
                        results.put(Integer.valueOf(seq), columnName);
                    }
                }
                List<String> columns = new ArrayList<>(results.size());
                columns.addAll(results.values());
                return new Index(name, unique, columns);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    /* loaded from: classes.dex */
    public static class Column {
        public final int affinity;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String name, String type, boolean notNull, int primaryKeyPosition) {
            this.name = name;
            this.type = type;
            this.notNull = notNull;
            this.primaryKeyPosition = primaryKeyPosition;
            this.affinity = findAffinity(type);
        }

        private static int findAffinity(String type) {
            if (type == null) {
                return 5;
            }
            String uppercaseType = type.toUpperCase(Locale.US);
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
            if (this.name.equals(column.name) && this.notNull == column.notNull && this.affinity == column.affinity) {
                return true;
            }
            return false;
        }

        public boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((((result * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            return "Column{name='" + this.name + "', type='" + this.type + "', affinity='" + this.affinity + "', notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + '}';
        }
    }

    /* loaded from: classes.dex */
    public static class ForeignKey {
        public final List<String> columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List<String> referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String referenceTable, String onDelete, String onUpdate, List<String> columnNames, List<String> referenceColumnNames) {
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = Collections.unmodifiableList(columnNames);
            this.referenceColumnNames = Collections.unmodifiableList(referenceColumnNames);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ForeignKey that = (ForeignKey) o;
            if (!this.referenceTable.equals(that.referenceTable) || !this.onDelete.equals(that.onDelete) || !this.onUpdate.equals(that.onUpdate) || !this.columnNames.equals(that.columnNames)) {
                return false;
            }
            return this.referenceColumnNames.equals(that.referenceColumnNames);
        }

        public int hashCode() {
            int result = this.referenceTable.hashCode();
            return (((((((result * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + "', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    /* loaded from: classes.dex */
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

        @Override // java.lang.Comparable
        public int compareTo(ForeignKeyWithSequence o) {
            int idCmp = this.mId - o.mId;
            if (idCmp == 0) {
                return this.mSequence - o.mSequence;
            }
            return idCmp;
        }
    }

    /* loaded from: classes.dex */
    public static class Index {
        public static final String DEFAULT_PREFIX = "index_";
        public final List<String> columns;
        public final String name;
        public final boolean unique;

        public Index(String name, boolean unique, List<String> columns) {
            this.name = name;
            this.unique = unique;
            this.columns = columns;
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
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + '}';
        }
    }
}
