package com.wits.ksw.settings;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.p004v7.app.AppCompatActivity;
import android.support.p004v7.app.AppCompatDelegate;
import android.support.p004v7.app.SkinAppCompatDelegateImpl;
import android.util.Log;
import android.view.Window;
import java.lang.reflect.Method;

/* loaded from: classes3.dex */
public class BaseSkinActivity extends AppCompatActivity {
    @Override // android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public void sendToApp(String pckName, String acName) {
        try {
            forceStopPackage(this, "com.android.settings");
            Log.d("BaseActivity", "sendToApp ");
            Intent intent = new Intent("android.settings.SETTINGS");
            startActivity(intent);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void forceStopPackage(Context context, String pkgName) {
        Log.d("BaseActivity", "forceStopPackage " + pkgName);
        ActivityManager am = (ActivityManager) context.getSystemService("activity");
        try {
            Method forceStopPackage = Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", String.class);
            forceStopPackage.invoke(am, pkgName);
        } catch (Exception e) {
        }
    }

    public void setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    public void setFull() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
        }
    }

    public void setFullAndTranslucent() {
        setFull();
        setStatusBarTranslucent();
    }

    @Override // android.support.p004v7.app.AppCompatActivity
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }
}
