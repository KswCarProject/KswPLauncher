package android.arch.persistence.p000db.framework;

import android.arch.persistence.p000db.SupportSQLiteOpenHelper;

/* renamed from: android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory */
/* loaded from: classes.dex */
public final class FrameworkSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    @Override // android.arch.persistence.p000db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new FrameworkSQLiteOpenHelper(configuration.context, configuration.name, configuration.callback);
    }
}
