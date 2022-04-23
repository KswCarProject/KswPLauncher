package com.wits.ksw.settings.audi_mib3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.wits.ksw.settings.id7.FactoryActivity;

public class AudiMib3StartUtil {
    public static final String TYPE_SEL = "TYPE_SEL";
    public static final String VALUE_MUSIC = "VALUE_MUSIC";
    public static final String VALUE_VIDEO = "VALUE_VIDEO";

    public static void AudiLanguageActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3LanguageActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiTimeActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3TimeActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSoundActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3SoundActivity.class);
        activity.startActivity(intent);
    }

    public static void AudioSystemActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3SystemActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiNaviActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3NaviActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSysinfoActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3SystemInfoActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiEqActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3EqActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiSpeedUnitActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3SpeedUnitActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiReverCameraActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3ReverCameraActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiBrightnessActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3BrightnessActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiAuxActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3AuxActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiTempActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3TempActivity.class);
        activity.startActivity(intent);
    }

    public static void AudiPasswordActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMib3PasswordActivity.class);
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
        intent.setClass(activity, AudiMbi3SelThirdAppActivity.class);
        intent.putExtra("TYPE_SEL", "VALUE_MUSIC");
        activity.startActivity(intent);
    }

    public static void toAudiSelVideoApp(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AudiMbi3SelThirdAppActivity.class);
        intent.putExtra("TYPE_SEL", "VALUE_VIDEO");
        activity.startActivity(intent);
    }
}
