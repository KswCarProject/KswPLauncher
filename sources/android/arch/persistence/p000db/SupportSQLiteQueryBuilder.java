package android.arch.persistence.p000db;

import java.util.regex.Pattern;

/* renamed from: android.arch.persistence.db.SupportSQLiteQueryBuilder */
/* loaded from: classes.dex */
public final class SupportSQLiteQueryBuilder {
    private static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    private Object[] mBindArgs;
    private String mSelection;
    private final String mTable;
    private boolean mDistinct = false;
    private String[] mColumns = null;
    private String mGroupBy = null;
    private String mHaving = null;
    private String mOrderBy = null;
    private String mLimit = null;

    public static SupportSQLiteQueryBuilder builder(String tableName) {
        return new SupportSQLiteQueryBuilder(tableName);
    }

    private SupportSQLiteQueryBuilder(String table) {
        this.mTable = table;
    }

    public SupportSQLiteQueryBuilder distinct() {
        this.mDistinct = true;
        return this;
    }

    public SupportSQLiteQueryBuilder columns(String[] columns) {
        this.mColumns = columns;
        return this;
    }

    public SupportSQLiteQueryBuilder selection(String selection, Object[] bindArgs) {
        this.mSelection = selection;
        this.mBindArgs = bindArgs;
        return this;
    }

    public SupportSQLiteQueryBuilder groupBy(String groupBy) {
        this.mGroupBy = groupBy;
        return this;
    }

    public SupportSQLiteQueryBuilder having(String having) {
        this.mHaving = having;
        return this;
    }

    public SupportSQLiteQueryBuilder orderBy(String orderBy) {
        this.mOrderBy = orderBy;
        return this;
    }

    public SupportSQLiteQueryBuilder limit(String limit) {
        if (!isEmpty(limit) && !sLimitPattern.matcher(limit).matches()) {
            throw new IllegalArgumentException("invalid LIMIT clauses:" + limit);
        }
        this.mLimit = limit;
        return this;
    }

    public SupportSQLiteQuery create() {
        if (isEmpty(this.mGroupBy) && !isEmpty(this.mHaving)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder query = new StringBuilder(120);
        query.append("SELECT ");
        if (this.mDistinct) {
            query.append("DISTINCT ");
        }
        String[] strArr = this.mColumns;
        if (strArr != null && strArr.length != 0) {
            appendColumns(query, strArr);
        } else {
            query.append(" * ");
        }
        query.append(" FROM ");
        query.append(this.mTable);
        appendClause(query, " WHERE ", this.mSelection);
        appendClause(query, " GROUP BY ", this.mGroupBy);
        appendClause(query, " HAVING ", this.mHaving);
        appendClause(query, " ORDER BY ", this.mOrderBy);
        appendClause(query, " LIMIT ", this.mLimit);
        return new SimpleSQLiteQuery(query.toString(), this.mBindArgs);
    }

    private static void appendClause(StringBuilder s, String name, String clause) {
        if (!isEmpty(clause)) {
            s.append(name);
            s.append(clause);
        }
    }

    private static void appendColumns(StringBuilder s, String[] columns) {
        int n = columns.length;
        for (int i = 0; i < n; i++) {
            String column = columns[i];
            if (i > 0) {
                s.append(", ");
            }
            s.append(column);
        }
        s.append(' ');
    }

    private static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }
}
