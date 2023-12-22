package android.arch.persistence.p000db;

/* renamed from: android.arch.persistence.db.SupportSQLiteQuery */
/* loaded from: classes.dex */
public interface SupportSQLiteQuery {
    void bindTo(SupportSQLiteProgram supportSQLiteProgram);

    int getArgCount();

    String getSql();
}
