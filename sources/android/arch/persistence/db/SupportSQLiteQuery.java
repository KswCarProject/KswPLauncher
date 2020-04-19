package android.arch.persistence.db;

public interface SupportSQLiteQuery {
    void bindTo(SupportSQLiteProgram supportSQLiteProgram);

    int getArgCount();

    String getSql();
}
