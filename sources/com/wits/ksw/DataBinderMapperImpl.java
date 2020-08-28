package com.wits.ksw;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.databinding.ALSDasoardBindImpl;
import com.wits.ksw.databinding.ALSDasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiBindingImpl;
import com.wits.ksw.databinding.ActivityAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityBmwNbtBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardAudiBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardLcBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainAlsId6BindingImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBenzGsBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzMbuxBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzNtg5BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsugBindingImpl;
import com.wits.ksw.databinding.ActivityMainId6GsBindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiAuxBindingImpl;
import com.wits.ksw.databinding.AudiAuxBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiBrightnessBindingImpl;
import com.wits.ksw.databinding.AudiBrightnessBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiEqViewBindingImpl;
import com.wits.ksw.databinding.AudiEqViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiNaviBindingImpl;
import com.wits.ksw.databinding.AudiNaviBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiPasswordBindingImpl;
import com.wits.ksw.databinding.AudiPasswordBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiReverCameraBindingImpl;
import com.wits.ksw.databinding.AudiRightCarinfoBindingImpl;
import com.wits.ksw.databinding.AudiRightCarinfoBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiRightLogoBindingImpl;
import com.wits.ksw.databinding.AudiRightLogoBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiRightMediaBindingImpl;
import com.wits.ksw.databinding.AudiRightMediaBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiRightNaviBindingImpl;
import com.wits.ksw.databinding.AudiRightNaviBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiSpeedUnitBindingImpl;
import com.wits.ksw.databinding.AudiSysinfoBindingImpl;
import com.wits.ksw.databinding.AudiSystemSetBindingImpl;
import com.wits.ksw.databinding.AudiTempBindingImpl;
import com.wits.ksw.databinding.BcItemBindingImpl;
import com.wits.ksw.databinding.BcItemBindingSw600dpLandImpl;
import com.wits.ksw.databinding.BcNtg5ItemBindingImpl;
import com.wits.ksw.databinding.BcSubViewBindingImpl;
import com.wits.ksw.databinding.BcSubViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.BenzControlBindImpl;
import com.wits.ksw.databinding.BenzControlBindSw600dpLandImpl;
import com.wits.ksw.databinding.BenzMbuxItemBindingImpl;
import com.wits.ksw.databinding.CarInfoImpl;
import com.wits.ksw.databinding.CarInfoSw600dpLandImpl;
import com.wits.ksw.databinding.DasoardBindImpl;
import com.wits.ksw.databinding.DasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.FraBenzgsOneBindingImpl;
import com.wits.ksw.databinding.FraBenzgsTwoBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsOneBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsThreeBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsTwoBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ID5MaindBindImpl;
import com.wits.ksw.databinding.ID5MaindBindSw600dpLandImpl;
import com.wits.ksw.databinding.ID6FragmentFourImpl;
import com.wits.ksw.databinding.ID6FragmentFourSw600dpLandImpl;
import com.wits.ksw.databinding.ID6FragmentOneImpl;
import com.wits.ksw.databinding.ID6FragmentOneSw600dpLandImpl;
import com.wits.ksw.databinding.ID6FragmentThreeImpl;
import com.wits.ksw.databinding.ID6FragmentThreeSw600dpLandImpl;
import com.wits.ksw.databinding.ID6FragmentTowImpl;
import com.wits.ksw.databinding.ID6FragmentTowSw600dpLandImpl;
import com.wits.ksw.databinding.ID6OneImpl;
import com.wits.ksw.databinding.ID6OneSw600dpLandImpl;
import com.wits.ksw.databinding.Id7AppItemBindingImpl;
import com.wits.ksw.databinding.Id7AppItemBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubCarViewBindingImpl;
import com.wits.ksw.databinding.Id7SubCarViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubDashboardViewBindingImpl;
import com.wits.ksw.databinding.Id7SubDashboardViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubMusicViewBindingImpl;
import com.wits.ksw.databinding.Id7SubMusicViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubPhoneViewBindingImpl;
import com.wits.ksw.databinding.Id7SubPhoneViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubVideoViewBindingImpl;
import com.wits.ksw.databinding.Id7SubVideoViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.MainActivityImpl;
import com.wits.ksw.databinding.MainActivitySw600dpLandImpl;
import com.wits.ksw.databinding.MediaFragmentImpl;
import com.wits.ksw.databinding.MediaFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviFragmentImpl;
import com.wits.ksw.databinding.NaviFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviSubViewImpl;
import com.wits.ksw.databinding.NaviSubViewSw600dpLandImpl;
import com.wits.ksw.databinding.Ntg630ControlPopupBindingImpl;
import com.wits.ksw.databinding.SevenDasoardBindImpl;
import com.wits.ksw.databinding.SevenDasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.UgHomeFourBindingImpl;
import com.wits.ksw.databinding.UgHomeOneBindingImpl;
import com.wits.ksw.databinding.UgHomeThreeBindingImpl;
import com.wits.ksw.databinding.UgHomeTwoBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(66);
    private static final int LAYOUT_ACTIVITYAUDI = 1;
    private static final int LAYOUT_ACTIVITYAUDISOUND = 2;
    private static final int LAYOUT_ACTIVITYAUDITIME = 3;
    private static final int LAYOUT_ACTIVITYBMWNBT = 4;
    private static final int LAYOUT_ACTIVITYDASHBOARD = 5;
    private static final int LAYOUT_ACTIVITYDASHBOARDALS = 6;
    private static final int LAYOUT_ACTIVITYDASHBOARDAUDI = 7;
    private static final int LAYOUT_ACTIVITYDASHBOARDLC = 8;
    private static final int LAYOUT_ACTIVITYDASHBOARDSEVEN = 9;
    private static final int LAYOUT_ACTIVITYID7APPS = 10;
    private static final int LAYOUT_ACTIVITYMAINALSID6 = 11;
    private static final int LAYOUT_ACTIVITYMAINAUDI = 12;
    private static final int LAYOUT_ACTIVITYMAINBC = 13;
    private static final int LAYOUT_ACTIVITYMAINBENZGS = 14;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX = 15;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG5 = 16;
    private static final int LAYOUT_ACTIVITYMAINBMW = 17;
    private static final int LAYOUT_ACTIVITYMAINGSUG = 18;
    private static final int LAYOUT_ACTIVITYMAINID5 = 19;
    private static final int LAYOUT_ACTIVITYMAINID6GS = 20;
    private static final int LAYOUT_ACTIVITYNTG6DASHBOARD = 21;
    private static final int LAYOUT_AUDIAUX = 22;
    private static final int LAYOUT_AUDIBRIGHTNESS = 23;
    private static final int LAYOUT_AUDIEQVIEW = 24;
    private static final int LAYOUT_AUDINAVI = 25;
    private static final int LAYOUT_AUDIPASSWORD = 26;
    private static final int LAYOUT_AUDIREVERCAMERA = 27;
    private static final int LAYOUT_AUDIRIGHTCARINFO = 28;
    private static final int LAYOUT_AUDIRIGHTLOGO = 29;
    private static final int LAYOUT_AUDIRIGHTMEDIA = 30;
    private static final int LAYOUT_AUDIRIGHTNAVI = 31;
    private static final int LAYOUT_AUDISPEEDUNIT = 32;
    private static final int LAYOUT_AUDISYSINFO = 33;
    private static final int LAYOUT_AUDISYSTEMSET = 34;
    private static final int LAYOUT_AUDITEMP = 35;
    private static final int LAYOUT_BCITEM = 36;
    private static final int LAYOUT_BCNTG5ITEM = 37;
    private static final int LAYOUT_BCSUBVIEW = 38;
    private static final int LAYOUT_BENZMBUXITEM = 39;
    private static final int LAYOUT_FRABENZGSONE = 40;
    private static final int LAYOUT_FRABENZGSTWO = 41;
    private static final int LAYOUT_FRABMWEVOID6GSONE = 42;
    private static final int LAYOUT_FRABMWEVOID6GSTHREE = 43;
    private static final int LAYOUT_FRABMWEVOID6GSTWO = 44;
    private static final int LAYOUT_FRAGMENTID5TWO = 45;
    private static final int LAYOUT_ID6FRAGMENTFOUR = 46;
    private static final int LAYOUT_ID6FRAGMENTONE = 47;
    private static final int LAYOUT_ID6FRAGMENTTHREE = 48;
    private static final int LAYOUT_ID6FRAGMENTTOW = 49;
    private static final int LAYOUT_ID6ONE = 50;
    private static final int LAYOUT_ID7APPITEM = 51;
    private static final int LAYOUT_ID7FRAGMENTCAR = 52;
    private static final int LAYOUT_ID7FRAGMENTMEDIA = 53;
    private static final int LAYOUT_ID7FRAGMENTNAVI = 54;
    private static final int LAYOUT_ID7SUBCARVIEW = 55;
    private static final int LAYOUT_ID7SUBDASHBOARDVIEW = 56;
    private static final int LAYOUT_ID7SUBMUSICVIEW = 57;
    private static final int LAYOUT_ID7SUBNAVIVIEW = 58;
    private static final int LAYOUT_ID7SUBPHONEVIEW = 59;
    private static final int LAYOUT_ID7SUBVIDEOVIEW = 60;
    private static final int LAYOUT_NTG630CONTROLPOPUP = 61;
    private static final int LAYOUT_NTG6CONTROLPOPUP = 62;
    private static final int LAYOUT_UGHOMEFOUR = 63;
    private static final int LAYOUT_UGHOMEONE = 64;
    private static final int LAYOUT_UGHOMETHREE = 65;
    private static final int LAYOUT_UGHOMETWO = 66;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi_sound, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi_time, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_bmw_nbt, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_als, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_audi, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_lc, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_seven, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_id7_apps, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_als_id6, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_audi, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_bc, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_gs, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_mbux, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_ntg5, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_bmw, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_gsug, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_id5, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_id6_gs, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_ntg6_dash_board, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_aux, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_brightness, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_eq_view, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_navi, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_password, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_rever_camera, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_carinfo, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_logo, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_media, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_navi, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_speed_unit, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_sysinfo, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_system_set, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_temp, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_item, 36);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_ntg5_item, 37);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_sub_view, 38);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.benz_mbux_item, 39);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_benzgs_one, 40);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_benzgs_two, 41);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_one, 42);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_three, 43);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_two, 44);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_id5_two, 45);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_four, 46);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_one, 47);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_three, 48);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_tow, 49);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_one, 50);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_app_item, 51);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_car, 52);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_media, 53);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_navi, 54);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_car_view, 55);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_dashboard_view, 56);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_music_view, 57);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_navi_view, 58);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_phone_view, 59);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_video_view, 60);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ntg630_control_popup, 61);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ntg6_control_popup, 62);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_four, 63);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_one, 64);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_three, 65);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_two, 66);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi is invalid. Received: " + tag);
            case 2:
                if ("layout-sw600dp-land/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_sound is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_time is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_bmw_nbt_0".equals(tag)) {
                    return new ActivityBmwNbtBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bmw_nbt is invalid. Received: " + tag);
            case 5:
                if ("layout-sw600dp-land/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_als is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_dash_board_audi_0".equals(tag)) {
                    return new ActivityDashBoardAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_dash_board_lc_0".equals(tag)) {
                    return new ActivityDashBoardLcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lc is invalid. Received: " + tag);
            case 9:
                if ("layout-sw600dp-land/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_seven is invalid. Received: " + tag);
            case 10:
                if ("layout-sw600dp-land/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id7_apps is invalid. Received: " + tag);
            case 11:
                if ("layout/activity_main_als_id6_0".equals(tag)) {
                    return new ActivityMainAlsId6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id6 is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_audi is invalid. Received: " + tag);
            case 13:
                if ("layout-sw600dp-land/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bc is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_main_benz_gs_0".equals(tag)) {
                    return new ActivityMainBenzGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_gs is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_main_benz_mbux_0".equals(tag)) {
                    return new ActivityMainBenzMbuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_main_benz_ntg5_0".equals(tag)) {
                    return new ActivityMainBenzNtg5BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg5 is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_main_bmw_0".equals(tag)) {
                    return new MainActivityImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_bmw_0".equals(tag)) {
                    return new MainActivitySw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bmw is invalid. Received: " + tag);
            case 18:
                if ("layout/activity_main_gsug_0".equals(tag)) {
                    return new ActivityMainGsugBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id5 is invalid. Received: " + tag);
            case 20:
                if ("layout/activity_main_id6_gs_0".equals(tag)) {
                    return new ActivityMainId6GsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id6_gs is invalid. Received: " + tag);
            case 21:
                if ("layout/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ntg6_dash_board is invalid. Received: " + tag);
            case 22:
                if ("layout/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_aux is invalid. Received: " + tag);
            case 23:
                if ("layout-sw600dp-land/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_brightness is invalid. Received: " + tag);
            case 24:
                if ("layout/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_eq_view is invalid. Received: " + tag);
            case 25:
                if ("layout/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_navi is invalid. Received: " + tag);
            case 26:
                if ("layout/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_password is invalid. Received: " + tag);
            case 27:
                if ("layout/audi_rever_camera_0".equals(tag)) {
                    return new AudiReverCameraBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_rever_camera is invalid. Received: " + tag);
            case 28:
                if ("layout/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_carinfo is invalid. Received: " + tag);
            case 29:
                if ("layout/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_logo is invalid. Received: " + tag);
            case 30:
                if ("layout-sw600dp-land/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_media is invalid. Received: " + tag);
            case 31:
                if ("layout-sw600dp-land/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_navi is invalid. Received: " + tag);
            case 32:
                if ("layout/audi_speed_unit_0".equals(tag)) {
                    return new AudiSpeedUnitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_speed_unit is invalid. Received: " + tag);
            case 33:
                if ("layout/audi_sysinfo_0".equals(tag)) {
                    return new AudiSysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_sysinfo is invalid. Received: " + tag);
            case 34:
                if ("layout/audi_system_set_0".equals(tag)) {
                    return new AudiSystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_system_set is invalid. Received: " + tag);
            case 35:
                if ("layout/audi_temp_0".equals(tag)) {
                    return new AudiTempBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_temp is invalid. Received: " + tag);
            case 36:
                if ("layout-sw600dp-land/bc_item_0".equals(tag)) {
                    return new BcItemBindingSw600dpLandImpl(component, view);
                }
                if ("layout/bc_item_0".equals(tag)) {
                    return new BcItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_item is invalid. Received: " + tag);
            case 37:
                if ("layout/bc_ntg5_item_0".equals(tag)) {
                    return new BcNtg5ItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_ntg5_item is invalid. Received: " + tag);
            case 38:
                if ("layout/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_sub_view is invalid. Received: " + tag);
            case 39:
                if ("layout/benz_mbux_item_0".equals(tag)) {
                    return new BenzMbuxItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_item is invalid. Received: " + tag);
            case 40:
                if ("layout/fra_benzgs_one_0".equals(tag)) {
                    return new FraBenzgsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_one is invalid. Received: " + tag);
            case 41:
                if ("layout/fra_benzgs_two_0".equals(tag)) {
                    return new FraBenzgsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_two is invalid. Received: " + tag);
            case 42:
                if ("layout/fra_bmw_evo_id6_gs_one_0".equals(tag)) {
                    return new FraBmwEvoId6GsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_one is invalid. Received: " + tag);
            case 43:
                if ("layout/fra_bmw_evo_id6_gs_three_0".equals(tag)) {
                    return new FraBmwEvoId6GsThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_three is invalid. Received: " + tag);
            case 44:
                if ("layout/fra_bmw_evo_id6_gs_two_0".equals(tag)) {
                    return new FraBmwEvoId6GsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_two is invalid. Received: " + tag);
            case 45:
                if ("layout-sw600dp-land/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingSw600dpLandImpl(component, view);
                }
                if ("layout/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_id5_two is invalid. Received: " + tag);
            case 46:
                if ("layout/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_four is invalid. Received: " + tag);
            case 47:
                if ("layout/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_one is invalid. Received: " + tag);
            case 48:
                if ("layout-sw600dp-land/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeSw600dpLandImpl(component, view);
                }
                if ("layout/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_three is invalid. Received: " + tag);
            case 49:
                if ("layout/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_tow is invalid. Received: " + tag);
            case 50:
                if ("layout/id6_one_0".equals(tag)) {
                    return new ID6OneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_one_0".equals(tag)) {
                    return new ID6OneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_one is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_app_item is invalid. Received: " + tag);
            case 52:
                if ("layout-sw600dp-land/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car is invalid. Received: " + tag);
            case 53:
                if ("layout-sw600dp-land/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_media is invalid. Received: " + tag);
            case 54:
                if ("layout-sw600dp-land/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi is invalid. Received: " + tag);
            case 55:
                if ("layout/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_car_view is invalid. Received: " + tag);
            case 56:
                if ("layout/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_dashboard_view is invalid. Received: " + tag);
            case 57:
                if ("layout/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_music_view is invalid. Received: " + tag);
            case 58:
                if ("layout/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_navi_view is invalid. Received: " + tag);
            case 59:
                if ("layout/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view is invalid. Received: " + tag);
            case 60:
                if ("layout/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_video_view is invalid. Received: " + tag);
            case 61:
                if ("layout/ntg630_control_popup_0".equals(tag)) {
                    return new Ntg630ControlPopupBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg630_control_popup is invalid. Received: " + tag);
            case 62:
                if ("layout-sw600dp-land/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindSw600dpLandImpl(component, view);
                }
                if ("layout/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg6_control_popup is invalid. Received: " + tag);
            case 63:
                if ("layout/ug_home_four_0".equals(tag)) {
                    return new UgHomeFourBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four is invalid. Received: " + tag);
            case 64:
                if ("layout/ug_home_one_0".equals(tag)) {
                    return new UgHomeOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one is invalid. Received: " + tag);
            case 65:
                if ("layout/ug_home_three_0".equals(tag)) {
                    return new UgHomeThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three is invalid. Received: " + tag);
            case 66:
                if ("layout/ug_home_two_0".equals(tag)) {
                    return new UgHomeTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (localizedLayoutId <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch ((localizedLayoutId - 1) / 50) {
                case 0:
                    return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
                case 1:
                    return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId) <= 0 || views[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String tag) {
        Integer tmpVal;
        if (tag == null || (tmpVal = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return tmpVal.intValue();
    }

    public String convertBrIdToString(int localId) {
        return InnerBrLookup.sKeys.get(localId);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList<DataBinderMapper> result = new ArrayList<>(1);
        result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
        return result;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(18);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "listItem");
            sKeys.put(2, "mBcVieModel");
            sKeys.put(3, "naviViewModel");
            sKeys.put(4, "NaviViewModel");
            sKeys.put(5, "appViewModel");
            sKeys.put(6, "nbtModel");
            sKeys.put(7, "carViewModel");
            sKeys.put(8, "vieModel");
            sKeys.put(9, "mediaViewModel");
            sKeys.put(10, "vm");
            sKeys.put(11, "LauncherViewModel");
            sKeys.put(12, "viewModel");
            sKeys.put(13, "AppViewModel");
            sKeys.put(14, "CarViewModel");
            sKeys.put(15, "MediaViewModel");
            sKeys.put(16, "launcherViewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(106);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/activity_audi_0", Integer.valueOf(R.layout.activity_audi));
            sKeys.put("layout-sw600dp-land/activity_audi_0", Integer.valueOf(R.layout.activity_audi));
            sKeys.put("layout-sw600dp-land/activity_audi_sound_0", Integer.valueOf(R.layout.activity_audi_sound));
            sKeys.put("layout/activity_audi_sound_0", Integer.valueOf(R.layout.activity_audi_sound));
            sKeys.put("layout/activity_audi_time_0", Integer.valueOf(R.layout.activity_audi_time));
            sKeys.put("layout-sw600dp-land/activity_audi_time_0", Integer.valueOf(R.layout.activity_audi_time));
            sKeys.put("layout/activity_bmw_nbt_0", Integer.valueOf(R.layout.activity_bmw_nbt));
            sKeys.put("layout-sw600dp-land/activity_dash_board_0", Integer.valueOf(R.layout.activity_dash_board));
            sKeys.put("layout/activity_dash_board_0", Integer.valueOf(R.layout.activity_dash_board));
            sKeys.put("layout/activity_dash_board_als_0", Integer.valueOf(R.layout.activity_dash_board_als));
            sKeys.put("layout-sw600dp-land/activity_dash_board_als_0", Integer.valueOf(R.layout.activity_dash_board_als));
            sKeys.put("layout/activity_dash_board_audi_0", Integer.valueOf(R.layout.activity_dash_board_audi));
            sKeys.put("layout/activity_dash_board_lc_0", Integer.valueOf(R.layout.activity_dash_board_lc));
            sKeys.put("layout-sw600dp-land/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            sKeys.put("layout/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            sKeys.put("layout-sw600dp-land/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            sKeys.put("layout/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            sKeys.put("layout/activity_main_als_id6_0", Integer.valueOf(R.layout.activity_main_als_id6));
            sKeys.put("layout/activity_main_audi_0", Integer.valueOf(R.layout.activity_main_audi));
            sKeys.put("layout-sw600dp-land/activity_main_audi_0", Integer.valueOf(R.layout.activity_main_audi));
            sKeys.put("layout-sw600dp-land/activity_main_bc_0", Integer.valueOf(R.layout.activity_main_bc));
            sKeys.put("layout/activity_main_bc_0", Integer.valueOf(R.layout.activity_main_bc));
            sKeys.put("layout/activity_main_benz_gs_0", Integer.valueOf(R.layout.activity_main_benz_gs));
            sKeys.put("layout/activity_main_benz_mbux_0", Integer.valueOf(R.layout.activity_main_benz_mbux));
            sKeys.put("layout/activity_main_benz_ntg5_0", Integer.valueOf(R.layout.activity_main_benz_ntg5));
            sKeys.put("layout/activity_main_bmw_0", Integer.valueOf(R.layout.activity_main_bmw));
            sKeys.put("layout-sw600dp-land/activity_main_bmw_0", Integer.valueOf(R.layout.activity_main_bmw));
            sKeys.put("layout/activity_main_gsug_0", Integer.valueOf(R.layout.activity_main_gsug));
            sKeys.put("layout/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            sKeys.put("layout-sw600dp-land/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            sKeys.put("layout/activity_main_id6_gs_0", Integer.valueOf(R.layout.activity_main_id6_gs));
            sKeys.put("layout/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            sKeys.put("layout-sw600dp-land/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            sKeys.put("layout/audi_aux_0", Integer.valueOf(R.layout.audi_aux));
            sKeys.put("layout-sw600dp-land/audi_aux_0", Integer.valueOf(R.layout.audi_aux));
            sKeys.put("layout-sw600dp-land/audi_brightness_0", Integer.valueOf(R.layout.audi_brightness));
            sKeys.put("layout/audi_brightness_0", Integer.valueOf(R.layout.audi_brightness));
            sKeys.put("layout/audi_eq_view_0", Integer.valueOf(R.layout.audi_eq_view));
            sKeys.put("layout-sw600dp-land/audi_eq_view_0", Integer.valueOf(R.layout.audi_eq_view));
            sKeys.put("layout/audi_navi_0", Integer.valueOf(R.layout.audi_navi));
            sKeys.put("layout-sw600dp-land/audi_navi_0", Integer.valueOf(R.layout.audi_navi));
            sKeys.put("layout/audi_password_0", Integer.valueOf(R.layout.audi_password));
            sKeys.put("layout-sw600dp-land/audi_password_0", Integer.valueOf(R.layout.audi_password));
            sKeys.put("layout/audi_rever_camera_0", Integer.valueOf(R.layout.audi_rever_camera));
            sKeys.put("layout/audi_right_carinfo_0", Integer.valueOf(R.layout.audi_right_carinfo));
            sKeys.put("layout-sw600dp-land/audi_right_carinfo_0", Integer.valueOf(R.layout.audi_right_carinfo));
            sKeys.put("layout/audi_right_logo_0", Integer.valueOf(R.layout.audi_right_logo));
            sKeys.put("layout-sw600dp-land/audi_right_logo_0", Integer.valueOf(R.layout.audi_right_logo));
            sKeys.put("layout-sw600dp-land/audi_right_media_0", Integer.valueOf(R.layout.audi_right_media));
            sKeys.put("layout/audi_right_media_0", Integer.valueOf(R.layout.audi_right_media));
            sKeys.put("layout-sw600dp-land/audi_right_navi_0", Integer.valueOf(R.layout.audi_right_navi));
            sKeys.put("layout/audi_right_navi_0", Integer.valueOf(R.layout.audi_right_navi));
            sKeys.put("layout/audi_speed_unit_0", Integer.valueOf(R.layout.audi_speed_unit));
            sKeys.put("layout/audi_sysinfo_0", Integer.valueOf(R.layout.audi_sysinfo));
            sKeys.put("layout/audi_system_set_0", Integer.valueOf(R.layout.audi_system_set));
            sKeys.put("layout/audi_temp_0", Integer.valueOf(R.layout.audi_temp));
            sKeys.put("layout-sw600dp-land/bc_item_0", Integer.valueOf(R.layout.bc_item));
            sKeys.put("layout/bc_item_0", Integer.valueOf(R.layout.bc_item));
            sKeys.put("layout/bc_ntg5_item_0", Integer.valueOf(R.layout.bc_ntg5_item));
            sKeys.put("layout/bc_sub_view_0", Integer.valueOf(R.layout.bc_sub_view));
            sKeys.put("layout-sw600dp-land/bc_sub_view_0", Integer.valueOf(R.layout.bc_sub_view));
            sKeys.put("layout/benz_mbux_item_0", Integer.valueOf(R.layout.benz_mbux_item));
            sKeys.put("layout/fra_benzgs_one_0", Integer.valueOf(R.layout.fra_benzgs_one));
            sKeys.put("layout/fra_benzgs_two_0", Integer.valueOf(R.layout.fra_benzgs_two));
            sKeys.put("layout/fra_bmw_evo_id6_gs_one_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_one));
            sKeys.put("layout/fra_bmw_evo_id6_gs_three_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_three));
            sKeys.put("layout/fra_bmw_evo_id6_gs_two_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_two));
            sKeys.put("layout-sw600dp-land/fragment_id5_two_0", Integer.valueOf(R.layout.fragment_id5_two));
            sKeys.put("layout/fragment_id5_two_0", Integer.valueOf(R.layout.fragment_id5_two));
            sKeys.put("layout/id6_fragment_four_0", Integer.valueOf(R.layout.id6_fragment_four));
            sKeys.put("layout-sw600dp-land/id6_fragment_four_0", Integer.valueOf(R.layout.id6_fragment_four));
            sKeys.put("layout/id6_fragment_one_0", Integer.valueOf(R.layout.id6_fragment_one));
            sKeys.put("layout-sw600dp-land/id6_fragment_one_0", Integer.valueOf(R.layout.id6_fragment_one));
            sKeys.put("layout-sw600dp-land/id6_fragment_three_0", Integer.valueOf(R.layout.id6_fragment_three));
            sKeys.put("layout/id6_fragment_three_0", Integer.valueOf(R.layout.id6_fragment_three));
            sKeys.put("layout/id6_fragment_tow_0", Integer.valueOf(R.layout.id6_fragment_tow));
            sKeys.put("layout-sw600dp-land/id6_fragment_tow_0", Integer.valueOf(R.layout.id6_fragment_tow));
            sKeys.put("layout/id6_one_0", Integer.valueOf(R.layout.id6_one));
            sKeys.put("layout-sw600dp-land/id6_one_0", Integer.valueOf(R.layout.id6_one));
            sKeys.put("layout/id7_app_item_0", Integer.valueOf(R.layout.id7_app_item));
            sKeys.put("layout-sw600dp-land/id7_app_item_0", Integer.valueOf(R.layout.id7_app_item));
            sKeys.put("layout-sw600dp-land/id7_fragment_car_0", Integer.valueOf(R.layout.id7_fragment_car));
            sKeys.put("layout/id7_fragment_car_0", Integer.valueOf(R.layout.id7_fragment_car));
            sKeys.put("layout-sw600dp-land/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            sKeys.put("layout/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            sKeys.put("layout-sw600dp-land/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            sKeys.put("layout/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            sKeys.put("layout/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            sKeys.put("layout-sw600dp-land/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            sKeys.put("layout/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            sKeys.put("layout-sw600dp-land/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            sKeys.put("layout/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            sKeys.put("layout-sw600dp-land/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            sKeys.put("layout/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            sKeys.put("layout-sw600dp-land/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            sKeys.put("layout/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            sKeys.put("layout-sw600dp-land/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            sKeys.put("layout/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            sKeys.put("layout-sw600dp-land/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            sKeys.put("layout/ntg630_control_popup_0", Integer.valueOf(R.layout.ntg630_control_popup));
            sKeys.put("layout-sw600dp-land/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            sKeys.put("layout/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            sKeys.put("layout/ug_home_four_0", Integer.valueOf(R.layout.ug_home_four));
            sKeys.put("layout/ug_home_one_0", Integer.valueOf(R.layout.ug_home_one));
            sKeys.put("layout/ug_home_three_0", Integer.valueOf(R.layout.ug_home_three));
            sKeys.put("layout/ug_home_two_0", Integer.valueOf(R.layout.ug_home_two));
        }
    }
}
