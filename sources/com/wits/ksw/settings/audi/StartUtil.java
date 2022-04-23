package com.wits.ksw.settings.audi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wits.ksw.settings.id7.FactoryActivity;

public class StartUtil {
    public static final String TYPE_SEL = "TYPE_SEL";
    public static final String VALUE_MUSIC = "VALUE_MUSIC";
    public static final String VALUE_VIDEO = "VALUE_VIDEO";

    public static void AudiLanguageActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiLanguageActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiTimeActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiTimeActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSoundActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSoundActivity.class);
        activity.startActivity(intent);
    }

    public static void AudioSystemActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSystemActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiNaviActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiNaviActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSysinfoActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSystemInfoActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiEqActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiEqActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSpeedUnitActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSpeedUnitActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiReverCameraActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiReverCameraActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiBrightnessActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiBrightnessActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiAuxActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiAuxActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiTempActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiTempActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiPasswordActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiPasswordActivity.class);
        activity.startActivity(intent);
    }

    public static void SettingsActivity(Activity activity) {
        activity.startActivity(new Intent("android.settings.SETTINGS"));
    }

    public static void FactoryActivity(Context activity) {
        Intent intent = new Intent();
        intent.setClass(activity, FactoryActivity.class);
        activity.startActivity(intent);
    }

    public static void toAudiSelMusicApp(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSelThirdAppActivity.class);
        intent.putExtra("TYPE_SEL", "VALUE_MUSIC");
        activity.startActivity(intent);
    }

    public static void toAudiSelVideoApp(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiSelThirdAppActivity.class);
        intent.putExtra("TYPE_SEL", "VALUE_VIDEO");
        activity.startActivity(intent);
    }
}
