package skin.support.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SkinPreference {
    private static final String FILE_NAME = "meta-data";
    private static final String KEY_SKIN_NAME = "skin-name";
    private static final String KEY_SKIN_STRATEGY = "skin-strategy";
    private static final String KEY_SKIN_USER_THEME = "skin-user-theme-json";
    private static SkinPreference sInstance;
    private final Context mApp;
    private final SharedPreferences.Editor mEditor;
    private final SharedPreferences mPref;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SkinPreference.class) {
                if (sInstance == null) {
                    sInstance = new SkinPreference(context.getApplicationContext());
                }
            }
        }
    }

    public static SkinPreference getInstance() {
        return sInstance;
    }

    private SkinPreference(Context applicationContext) {
        this.mApp = applicationContext;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(FILE_NAME, 0);
        this.mPref = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public SkinPreference setSkinName(String skinName) {
        this.mEditor.putString(KEY_SKIN_NAME, skinName);
        return this;
    }

    public String getSkinName() {
        return this.mPref.getString(KEY_SKIN_NAME, "");
    }

    public SkinPreference setSkinStrategy(int strategy) {
        this.mEditor.putInt(KEY_SKIN_STRATEGY, strategy);
        return this;
    }

    public int getSkinStrategy() {
        return this.mPref.getInt(KEY_SKIN_STRATEGY, -1);
    }

    public SkinPreference setUserTheme(String themeJson) {
        this.mEditor.putString(KEY_SKIN_USER_THEME, themeJson);
        return this;
    }

    public String getUserTheme() {
        return this.mPref.getString(KEY_SKIN_USER_THEME, "");
    }

    public void commitEditor() {
        this.mEditor.apply();
    }
}
