package com.wits.ksw;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.databinding.ALSDasoardBindImpl;
import com.wits.ksw.databinding.ALSDasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAlsId7AppsBindingImpl;
import com.wits.ksw.databinding.ActivityAlsId7AppsBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiBindingImpl;
import com.wits.ksw.databinding.ActivityAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityBmwNbtBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardAudiBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardLcBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardLexusBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityLexusOemFmBindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId6BindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId7BindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId7BindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBenzGsBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzMbuxBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzNtg5BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsug2BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsugBindingImpl;
import com.wits.ksw.databinding.ActivityMainId6GsBindingImpl;
import com.wits.ksw.databinding.ActivityMainLandRoverBindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusBindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage1BindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage2BindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage3BindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityRomeoBindingImpl;
import com.wits.ksw.databinding.ActivityRoundAppsBindingImpl;
import com.wits.ksw.databinding.AlsId7SubCarViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubCarViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubDashboardViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubDashboardViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubMusicViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubMusicViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubNaviViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubNaviViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubPhoneViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubPhoneViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubVideoViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubVideoViewBindingSw600dpLandImpl;
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
import com.wits.ksw.databinding.DashVideoFragmentImpl;
import com.wits.ksw.databinding.DashVideoFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.DasoardBindImpl;
import com.wits.ksw.databinding.DasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.FraBenzgsOneBindingImpl;
import com.wits.ksw.databinding.FraBenzgsTwoBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsOneBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsThreeBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsTwoBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingSw600dpLandImpl;
import com.wits.ksw.databinding.HicarCarInfoImpl;
import com.wits.ksw.databinding.HicarNaviFragmentImpl;
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
import com.wits.ksw.databinding.Id7SubHicarViewBindingImpl;
import com.wits.ksw.databinding.Id7SubMusicViewBindingImpl;
import com.wits.ksw.databinding.Id7SubMusicViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubPhoneViewBindingImpl;
import com.wits.ksw.databinding.Id7SubPhoneViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7SubPhoneViewHicarBindingImpl;
import com.wits.ksw.databinding.Id7SubVideoViewBindingImpl;
import com.wits.ksw.databinding.Id7SubVideoViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.LandroverMainBindingImpl;
import com.wits.ksw.databinding.LandroverMainBottomLayBindingImpl;
import com.wits.ksw.databinding.LandroverOneFragmentImpl;
import com.wits.ksw.databinding.LandroverTwoFragmentImpl;
import com.wits.ksw.databinding.MainActivityImpl;
import com.wits.ksw.databinding.MainActivitySw600dpLandImpl;
import com.wits.ksw.databinding.MediaFragmentImpl;
import com.wits.ksw.databinding.MediaFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.MusicPhoneFragmentImpl;
import com.wits.ksw.databinding.MusicPhoneFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviCarFragmentImpl;
import com.wits.ksw.databinding.NaviCarFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviFragmentImpl;
import com.wits.ksw.databinding.NaviFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviSubViewImpl;
import com.wits.ksw.databinding.NaviSubViewSw600dpLandImpl;
import com.wits.ksw.databinding.Ntg630ControlPopupBindingImpl;
import com.wits.ksw.databinding.RoundAppItemBindingImpl;
import com.wits.ksw.databinding.SevenDasoardBindImpl;
import com.wits.ksw.databinding.SevenDasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.UgHomeFour2BindingImpl;
import com.wits.ksw.databinding.UgHomeFourBindingImpl;
import com.wits.ksw.databinding.UgHomeOne2BindingImpl;
import com.wits.ksw.databinding.UgHomeOneBindingImpl;
import com.wits.ksw.databinding.UgHomeThree2BindingImpl;
import com.wits.ksw.databinding.UgHomeThreeBindingImpl;
import com.wits.ksw.databinding.UgHomeTwo2BindingImpl;
import com.wits.ksw.databinding.UgHomeTwoBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(100);
    private static final int LAYOUT_ACTIVITYALSID7APPS = 1;
    private static final int LAYOUT_ACTIVITYAUDI = 2;
    private static final int LAYOUT_ACTIVITYAUDISOUND = 3;
    private static final int LAYOUT_ACTIVITYAUDITIME = 4;
    private static final int LAYOUT_ACTIVITYBMWNBT = 5;
    private static final int LAYOUT_ACTIVITYDASHBOARD = 6;
    private static final int LAYOUT_ACTIVITYDASHBOARDALS = 7;
    private static final int LAYOUT_ACTIVITYDASHBOARDAUDI = 8;
    private static final int LAYOUT_ACTIVITYDASHBOARDLC = 9;
    private static final int LAYOUT_ACTIVITYDASHBOARDLEXUS = 10;
    private static final int LAYOUT_ACTIVITYDASHBOARDSEVEN = 11;
    private static final int LAYOUT_ACTIVITYID7APPS = 12;
    private static final int LAYOUT_ACTIVITYLEXUSOEMFM = 13;
    private static final int LAYOUT_ACTIVITYMAINALSID6 = 14;
    private static final int LAYOUT_ACTIVITYMAINALSID7 = 15;
    private static final int LAYOUT_ACTIVITYMAINAUDI = 16;
    private static final int LAYOUT_ACTIVITYMAINBC = 17;
    private static final int LAYOUT_ACTIVITYMAINBENZGS = 18;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX = 19;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG5 = 20;
    private static final int LAYOUT_ACTIVITYMAINBMW = 21;
    private static final int LAYOUT_ACTIVITYMAINGSUG = 22;
    private static final int LAYOUT_ACTIVITYMAINGSUG2 = 23;
    private static final int LAYOUT_ACTIVITYMAINID5 = 24;
    private static final int LAYOUT_ACTIVITYMAINID6GS = 25;
    private static final int LAYOUT_ACTIVITYMAINLANDROVER = 26;
    private static final int LAYOUT_ACTIVITYMAINLEXUS = 27;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE1 = 28;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE2 = 29;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE3 = 30;
    private static final int LAYOUT_ACTIVITYNTG6DASHBOARD = 31;
    private static final int LAYOUT_ACTIVITYROMEO = 32;
    private static final int LAYOUT_ACTIVITYROUNDAPPS = 33;
    private static final int LAYOUT_ALSID7FRAGMENTDASHVIDEO = 34;
    private static final int LAYOUT_ALSID7FRAGMENTMUSIC = 35;
    private static final int LAYOUT_ALSID7FRAGMENTNAVICAR = 36;
    private static final int LAYOUT_ALSID7SUBCARVIEW = 37;
    private static final int LAYOUT_ALSID7SUBDASHBOARDVIEW = 38;
    private static final int LAYOUT_ALSID7SUBMUSICVIEW = 39;
    private static final int LAYOUT_ALSID7SUBNAVIVIEW = 40;
    private static final int LAYOUT_ALSID7SUBPHONEVIEW = 41;
    private static final int LAYOUT_ALSID7SUBVIDEOVIEW = 42;
    private static final int LAYOUT_AUDIAUX = 43;
    private static final int LAYOUT_AUDIBRIGHTNESS = 44;
    private static final int LAYOUT_AUDIEQVIEW = 45;
    private static final int LAYOUT_AUDINAVI = 46;
    private static final int LAYOUT_AUDIPASSWORD = 47;
    private static final int LAYOUT_AUDIREVERCAMERA = 48;
    private static final int LAYOUT_AUDIRIGHTCARINFO = 49;
    private static final int LAYOUT_AUDIRIGHTLOGO = 50;
    private static final int LAYOUT_AUDIRIGHTMEDIA = 51;
    private static final int LAYOUT_AUDIRIGHTNAVI = 52;
    private static final int LAYOUT_AUDISPEEDUNIT = 53;
    private static final int LAYOUT_AUDISYSINFO = 54;
    private static final int LAYOUT_AUDISYSTEMSET = 55;
    private static final int LAYOUT_AUDITEMP = 56;
    private static final int LAYOUT_BCITEM = 57;
    private static final int LAYOUT_BCNTG5ITEM = 58;
    private static final int LAYOUT_BCSUBVIEW = 59;
    private static final int LAYOUT_BENZMBUXITEM = 60;
    private static final int LAYOUT_FRABENZGSONE = 61;
    private static final int LAYOUT_FRABENZGSTWO = 62;
    private static final int LAYOUT_FRABMWEVOID6GSONE = 63;
    private static final int LAYOUT_FRABMWEVOID6GSTHREE = 64;
    private static final int LAYOUT_FRABMWEVOID6GSTWO = 65;
    private static final int LAYOUT_FRAGMENTID5TWO = 66;
    private static final int LAYOUT_ID6FRAGMENTFOUR = 67;
    private static final int LAYOUT_ID6FRAGMENTONE = 68;
    private static final int LAYOUT_ID6FRAGMENTTHREE = 69;
    private static final int LAYOUT_ID6FRAGMENTTOW = 70;
    private static final int LAYOUT_ID6ONE = 71;
    private static final int LAYOUT_ID7APPITEM = 72;
    private static final int LAYOUT_ID7FRAGMENTCAR = 73;
    private static final int LAYOUT_ID7FRAGMENTCARHICAR = 74;
    private static final int LAYOUT_ID7FRAGMENTMEDIA = 75;
    private static final int LAYOUT_ID7FRAGMENTNAVI = 76;
    private static final int LAYOUT_ID7FRAGMENTNAVIHICAR = 77;
    private static final int LAYOUT_ID7SUBCARVIEW = 78;
    private static final int LAYOUT_ID7SUBDASHBOARDVIEW = 79;
    private static final int LAYOUT_ID7SUBHICARVIEW = 80;
    private static final int LAYOUT_ID7SUBMUSICVIEW = 81;
    private static final int LAYOUT_ID7SUBNAVIVIEW = 82;
    private static final int LAYOUT_ID7SUBPHONEVIEW = 83;
    private static final int LAYOUT_ID7SUBPHONEVIEWHICAR = 84;
    private static final int LAYOUT_ID7SUBVIDEOVIEW = 85;
    private static final int LAYOUT_LANDROVERMAIN = 86;
    private static final int LAYOUT_LANDROVERMAINBOTTOMLAY = 87;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTONE = 88;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTTWO = 89;
    private static final int LAYOUT_NTG630CONTROLPOPUP = 90;
    private static final int LAYOUT_NTG6CONTROLPOPUP = 91;
    private static final int LAYOUT_ROUNDAPPITEM = 92;
    private static final int LAYOUT_UGHOMEFOUR = 93;
    private static final int LAYOUT_UGHOMEFOUR2 = 94;
    private static final int LAYOUT_UGHOMEONE = 95;
    private static final int LAYOUT_UGHOMEONE2 = 96;
    private static final int LAYOUT_UGHOMETHREE = 97;
    private static final int LAYOUT_UGHOMETHREE2 = 98;
    private static final int LAYOUT_UGHOMETWO = 99;
    private static final int LAYOUT_UGHOMETWO2 = 100;

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_als_id7_apps, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi_sound, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_audi_time, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_bmw_nbt, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_als, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_audi, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_lc, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_lexus, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_dash_board_seven, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_id7_apps, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_lexus_oem_fm, 13);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_als_id6, 14);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_als_id7, 15);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_audi, 16);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_bc, 17);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_gs, 18);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_mbux, 19);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_benz_ntg5, 20);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_bmw, 21);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_gsug, 22);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_gsug2, 23);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_id5, 24);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_id6_gs, 25);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_land_rover, 26);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_lexus, 27);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_lexus_page1, 28);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_lexus_page2, 29);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_main_lexus_page3, 30);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_ntg6_dash_board, 31);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_romeo, 32);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.activity_round_apps, 33);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_fragment_dash_video, 34);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_fragment_music, 35);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_fragment_navi_car, 36);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_car_view, 37);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_dashboard_view, 38);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_music_view, 39);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_navi_view, 40);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_phone_view, 41);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.als_id7_sub_video_view, 42);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_aux, 43);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_brightness, 44);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_eq_view, 45);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_navi, 46);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_password, 47);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_rever_camera, 48);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_carinfo, 49);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_logo, 50);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_media, 51);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_right_navi, 52);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_speed_unit, 53);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_sysinfo, 54);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_system_set, 55);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.audi_temp, 56);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_item, 57);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_ntg5_item, 58);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.bc_sub_view, 59);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.benz_mbux_item, 60);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_benzgs_one, 61);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_benzgs_two, 62);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_one, 63);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_three, 64);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fra_bmw_evo_id6_gs_two, 65);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.fragment_id5_two, 66);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_four, 67);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_one, 68);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_three, 69);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_fragment_tow, 70);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id6_one, 71);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_app_item, 72);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_car, 73);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_car_hicar, 74);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_media, 75);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_navi, 76);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_fragment_navi_hicar, 77);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_car_view, 78);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_dashboard_view, 79);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_hicar_view, 80);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_music_view, 81);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_navi_view, 82);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_phone_view, 83);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_phone_view_hicar, 84);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.id7_sub_video_view, 85);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.landrover_main, 86);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.landrover_main_bottom_lay, 87);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.landrover_main_fragment_one, 88);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.landrover_main_fragment_two, 89);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ntg630_control_popup, 90);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ntg6_control_popup, 91);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.round_app_item, 92);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_four, 93);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_four2, 94);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_one, 95);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_one2, 96);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_three, 97);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_three2, 98);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_two, 99);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.ug_home_two2, 100);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 1:
                if ("layout/activity_als_id7_apps_0".equals(tag)) {
                    return new ActivityAlsId7AppsBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_als_id7_apps_0".equals(tag)) {
                    return new ActivityAlsId7AppsBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_als_id7_apps is invalid. Received: " + tag);
            case 2:
                if ("layout/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi is invalid. Received: " + tag);
            case 3:
                if ("layout-sw600dp-land/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_sound is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_time is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_bmw_nbt_0".equals(tag)) {
                    return new ActivityBmwNbtBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bmw_nbt is invalid. Received: " + tag);
            case 6:
                if ("layout-sw600dp-land/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_als is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_dash_board_audi_0".equals(tag)) {
                    return new ActivityDashBoardAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi is invalid. Received: " + tag);
            case 9:
                if ("layout/activity_dash_board_lc_0".equals(tag)) {
                    return new ActivityDashBoardLcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lc is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_dash_board_lexus_0".equals(tag)) {
                    return new ActivityDashBoardLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lexus is invalid. Received: " + tag);
            case 11:
                if ("layout-sw600dp-land/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_seven is invalid. Received: " + tag);
            case 12:
                if ("layout-sw600dp-land/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id7_apps is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_lexus_oem_fm_0".equals(tag)) {
                    return new ActivityLexusOemFmBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_lexus_oem_fm is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_main_als_id6_0".equals(tag)) {
                    return new ActivityMainAlsId6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id6 is invalid. Received: " + tag);
            case 15:
                if ("layout-sw600dp-land/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id7 is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_audi is invalid. Received: " + tag);
            case 17:
                if ("layout-sw600dp-land/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bc is invalid. Received: " + tag);
            case 18:
                if ("layout/activity_main_benz_gs_0".equals(tag)) {
                    return new ActivityMainBenzGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_gs is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_main_benz_mbux_0".equals(tag)) {
                    return new ActivityMainBenzMbuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux is invalid. Received: " + tag);
            case 20:
                if ("layout/activity_main_benz_ntg5_0".equals(tag)) {
                    return new ActivityMainBenzNtg5BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg5 is invalid. Received: " + tag);
            case 21:
                if ("layout/activity_main_bmw_0".equals(tag)) {
                    return new MainActivityImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_bmw_0".equals(tag)) {
                    return new MainActivitySw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bmw is invalid. Received: " + tag);
            case 22:
                if ("layout/activity_main_gsug_0".equals(tag)) {
                    return new ActivityMainGsugBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug is invalid. Received: " + tag);
            case 23:
                if ("layout/activity_main_gsug2_0".equals(tag)) {
                    return new ActivityMainGsug2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug2 is invalid. Received: " + tag);
            case 24:
                if ("layout/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id5 is invalid. Received: " + tag);
            case 25:
                if ("layout/activity_main_id6_gs_0".equals(tag)) {
                    return new ActivityMainId6GsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id6_gs is invalid. Received: " + tag);
            case 26:
                if ("layout/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_land_rover is invalid. Received: " + tag);
            case 27:
                if ("layout/activity_main_lexus_0".equals(tag)) {
                    return new ActivityMainLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus is invalid. Received: " + tag);
            case 28:
                if ("layout/activity_main_lexus_page1_0".equals(tag)) {
                    return new ActivityMainLexusPage1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page1 is invalid. Received: " + tag);
            case 29:
                if ("layout/activity_main_lexus_page2_0".equals(tag)) {
                    return new ActivityMainLexusPage2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page2 is invalid. Received: " + tag);
            case 30:
                if ("layout/activity_main_lexus_page3_0".equals(tag)) {
                    return new ActivityMainLexusPage3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page3 is invalid. Received: " + tag);
            case 31:
                if ("layout/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ntg6_dash_board is invalid. Received: " + tag);
            case 32:
                if ("layout/activity_romeo_0".equals(tag)) {
                    return new ActivityRomeoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_romeo is invalid. Received: " + tag);
            case 33:
                if ("layout/activity_round_apps_0".equals(tag)) {
                    return new ActivityRoundAppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_round_apps is invalid. Received: " + tag);
            case 34:
                if ("layout/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_dash_video is invalid. Received: " + tag);
            case 35:
                if ("layout-sw600dp-land/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_music is invalid. Received: " + tag);
            case 36:
                if ("layout/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_navi_car is invalid. Received: " + tag);
            case 37:
                if ("layout-sw600dp-land/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_car_view is invalid. Received: " + tag);
            case 38:
                if ("layout-sw600dp-land/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_dashboard_view is invalid. Received: " + tag);
            case 39:
                if ("layout-sw600dp-land/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_music_view is invalid. Received: " + tag);
            case 40:
                if ("layout-sw600dp-land/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_navi_view is invalid. Received: " + tag);
            case 41:
                if ("layout-sw600dp-land/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_phone_view is invalid. Received: " + tag);
            case 42:
                if ("layout/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_video_view is invalid. Received: " + tag);
            case 43:
                if ("layout/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_aux is invalid. Received: " + tag);
            case 44:
                if ("layout-sw600dp-land/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_brightness is invalid. Received: " + tag);
            case 45:
                if ("layout/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_eq_view is invalid. Received: " + tag);
            case 46:
                if ("layout/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_navi is invalid. Received: " + tag);
            case 47:
                if ("layout/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_password is invalid. Received: " + tag);
            case 48:
                if ("layout/audi_rever_camera_0".equals(tag)) {
                    return new AudiReverCameraBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_rever_camera is invalid. Received: " + tag);
            case 49:
                if ("layout/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_carinfo is invalid. Received: " + tag);
            case 50:
                if ("layout/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_logo is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout-sw600dp-land/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_media is invalid. Received: " + tag);
            case 52:
                if ("layout-sw600dp-land/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_navi is invalid. Received: " + tag);
            case 53:
                if ("layout/audi_speed_unit_0".equals(tag)) {
                    return new AudiSpeedUnitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_speed_unit is invalid. Received: " + tag);
            case 54:
                if ("layout/audi_sysinfo_0".equals(tag)) {
                    return new AudiSysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_sysinfo is invalid. Received: " + tag);
            case 55:
                if ("layout/audi_system_set_0".equals(tag)) {
                    return new AudiSystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_system_set is invalid. Received: " + tag);
            case 56:
                if ("layout/audi_temp_0".equals(tag)) {
                    return new AudiTempBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_temp is invalid. Received: " + tag);
            case 57:
                if ("layout-sw600dp-land/bc_item_0".equals(tag)) {
                    return new BcItemBindingSw600dpLandImpl(component, view);
                }
                if ("layout/bc_item_0".equals(tag)) {
                    return new BcItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_item is invalid. Received: " + tag);
            case 58:
                if ("layout/bc_ntg5_item_0".equals(tag)) {
                    return new BcNtg5ItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_ntg5_item is invalid. Received: " + tag);
            case 59:
                if ("layout/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_sub_view is invalid. Received: " + tag);
            case 60:
                if ("layout/benz_mbux_item_0".equals(tag)) {
                    return new BenzMbuxItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_item is invalid. Received: " + tag);
            case 61:
                if ("layout/fra_benzgs_one_0".equals(tag)) {
                    return new FraBenzgsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_one is invalid. Received: " + tag);
            case 62:
                if ("layout/fra_benzgs_two_0".equals(tag)) {
                    return new FraBenzgsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_two is invalid. Received: " + tag);
            case 63:
                if ("layout/fra_bmw_evo_id6_gs_one_0".equals(tag)) {
                    return new FraBmwEvoId6GsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_one is invalid. Received: " + tag);
            case 64:
                if ("layout/fra_bmw_evo_id6_gs_three_0".equals(tag)) {
                    return new FraBmwEvoId6GsThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_three is invalid. Received: " + tag);
            case 65:
                if ("layout/fra_bmw_evo_id6_gs_two_0".equals(tag)) {
                    return new FraBmwEvoId6GsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_two is invalid. Received: " + tag);
            case 66:
                if ("layout-sw600dp-land/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingSw600dpLandImpl(component, view);
                }
                if ("layout/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_id5_two is invalid. Received: " + tag);
            case 67:
                if ("layout/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_four is invalid. Received: " + tag);
            case 68:
                if ("layout/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_one is invalid. Received: " + tag);
            case 69:
                if ("layout-sw600dp-land/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeSw600dpLandImpl(component, view);
                }
                if ("layout/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_three is invalid. Received: " + tag);
            case 70:
                if ("layout/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_tow is invalid. Received: " + tag);
            case 71:
                if ("layout/id6_one_0".equals(tag)) {
                    return new ID6OneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_one_0".equals(tag)) {
                    return new ID6OneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_one is invalid. Received: " + tag);
            case 72:
                if ("layout/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_app_item is invalid. Received: " + tag);
            case 73:
                if ("layout-sw600dp-land/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car is invalid. Received: " + tag);
            case 74:
                if ("layout/id7_fragment_car_hicar_0".equals(tag)) {
                    return new HicarCarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car_hicar is invalid. Received: " + tag);
            case 75:
                if ("layout-sw600dp-land/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_media is invalid. Received: " + tag);
            case 76:
                if ("layout-sw600dp-land/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi is invalid. Received: " + tag);
            case 77:
                if ("layout/id7_fragment_navi_hicar_0".equals(tag)) {
                    return new HicarNaviFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi_hicar is invalid. Received: " + tag);
            case 78:
                if ("layout/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_car_view is invalid. Received: " + tag);
            case 79:
                if ("layout/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_dashboard_view is invalid. Received: " + tag);
            case 80:
                if ("layout/id7_sub_hicar_view_0".equals(tag)) {
                    return new Id7SubHicarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_hicar_view is invalid. Received: " + tag);
            case 81:
                if ("layout/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_music_view is invalid. Received: " + tag);
            case 82:
                if ("layout/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_navi_view is invalid. Received: " + tag);
            case 83:
                if ("layout/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view is invalid. Received: " + tag);
            case 84:
                if ("layout/id7_sub_phone_view_hicar_0".equals(tag)) {
                    return new Id7SubPhoneViewHicarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view_hicar is invalid. Received: " + tag);
            case 85:
                if ("layout/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_video_view is invalid. Received: " + tag);
            case 86:
                if ("layout/landrover_main_0".equals(tag)) {
                    return new LandroverMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main is invalid. Received: " + tag);
            case 87:
                if ("layout/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_bottom_lay is invalid. Received: " + tag);
            case 88:
                if ("layout/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_fragment_one is invalid. Received: " + tag);
            case 89:
                if ("layout/landrover_main_fragment_two_0".equals(tag)) {
                    return new LandroverTwoFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_fragment_two is invalid. Received: " + tag);
            case 90:
                if ("layout/ntg630_control_popup_0".equals(tag)) {
                    return new Ntg630ControlPopupBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg630_control_popup is invalid. Received: " + tag);
            case 91:
                if ("layout-sw600dp-land/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindSw600dpLandImpl(component, view);
                }
                if ("layout/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg6_control_popup is invalid. Received: " + tag);
            case 92:
                if ("layout/round_app_item_0".equals(tag)) {
                    return new RoundAppItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for round_app_item is invalid. Received: " + tag);
            case 93:
                if ("layout/ug_home_four_0".equals(tag)) {
                    return new UgHomeFourBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four is invalid. Received: " + tag);
            case 94:
                if ("layout/ug_home_four2_0".equals(tag)) {
                    return new UgHomeFour2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four2 is invalid. Received: " + tag);
            case 95:
                if ("layout/ug_home_one_0".equals(tag)) {
                    return new UgHomeOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one is invalid. Received: " + tag);
            case 96:
                if ("layout/ug_home_one2_0".equals(tag)) {
                    return new UgHomeOne2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one2 is invalid. Received: " + tag);
            case 97:
                if ("layout/ug_home_three_0".equals(tag)) {
                    return new UgHomeThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three is invalid. Received: " + tag);
            case 98:
                if ("layout/ug_home_three2_0".equals(tag)) {
                    return new UgHomeThree2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three2 is invalid. Received: " + tag);
            case 99:
                if ("layout/ug_home_two_0".equals(tag)) {
                    return new UgHomeTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two is invalid. Received: " + tag);
            case 100:
                if ("layout/ug_home_two2_0".equals(tag)) {
                    return new UgHomeTwo2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two2 is invalid. Received: " + tag);
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
        static final SparseArray<String> sKeys = new SparseArray<>(26);

        private InnerBrLookup() {
        }

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "listItem");
            sKeys.put(2, "mBcVieModel");
            sKeys.put(3, "NaviCarViewModel");
            sKeys.put(4, "mUiParams");
            sKeys.put(5, "naviCarViewModel");
            sKeys.put(6, "musicPhoneViewModel");
            sKeys.put(7, "naviViewModel");
            sKeys.put(8, "NaviViewModel");
            sKeys.put(9, "ViewModel");
            sKeys.put(10, "appViewModel");
            sKeys.put(11, "nbtModel");
            sKeys.put(12, "carViewModel");
            sKeys.put(13, "dashVideoViewModel");
            sKeys.put(14, "vieModel");
            sKeys.put(15, "DashVideoViewModel");
            sKeys.put(16, "mediaViewModel");
            sKeys.put(17, "vm");
            sKeys.put(18, "LauncherViewModel");
            sKeys.put(19, "viewModel");
            sKeys.put(20, "AppViewModel");
            sKeys.put(21, "CarViewModel");
            sKeys.put(22, "MediaViewModel");
            sKeys.put(23, "launcherViewModel");
            sKeys.put(24, "MusicPhoneViewModel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(151);

        private InnerLayoutIdLookup() {
        }

        static {
            sKeys.put("layout/activity_als_id7_apps_0", Integer.valueOf(R.layout.activity_als_id7_apps));
            sKeys.put("layout-sw600dp-land/activity_als_id7_apps_0", Integer.valueOf(R.layout.activity_als_id7_apps));
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
            sKeys.put("layout/activity_dash_board_lexus_0", Integer.valueOf(R.layout.activity_dash_board_lexus));
            sKeys.put("layout-sw600dp-land/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            sKeys.put("layout/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            sKeys.put("layout-sw600dp-land/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            sKeys.put("layout/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            sKeys.put("layout/activity_lexus_oem_fm_0", Integer.valueOf(R.layout.activity_lexus_oem_fm));
            sKeys.put("layout/activity_main_als_id6_0", Integer.valueOf(R.layout.activity_main_als_id6));
            sKeys.put("layout-sw600dp-land/activity_main_als_id7_0", Integer.valueOf(R.layout.activity_main_als_id7));
            sKeys.put("layout/activity_main_als_id7_0", Integer.valueOf(R.layout.activity_main_als_id7));
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
            sKeys.put("layout/activity_main_gsug2_0", Integer.valueOf(R.layout.activity_main_gsug2));
            sKeys.put("layout/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            sKeys.put("layout-sw600dp-land/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            sKeys.put("layout/activity_main_id6_gs_0", Integer.valueOf(R.layout.activity_main_id6_gs));
            sKeys.put("layout/activity_main_land_rover_0", Integer.valueOf(R.layout.activity_main_land_rover));
            sKeys.put("layout/activity_main_lexus_0", Integer.valueOf(R.layout.activity_main_lexus));
            sKeys.put("layout/activity_main_lexus_page1_0", Integer.valueOf(R.layout.activity_main_lexus_page1));
            sKeys.put("layout/activity_main_lexus_page2_0", Integer.valueOf(R.layout.activity_main_lexus_page2));
            sKeys.put("layout/activity_main_lexus_page3_0", Integer.valueOf(R.layout.activity_main_lexus_page3));
            sKeys.put("layout/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            sKeys.put("layout-sw600dp-land/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            sKeys.put("layout/activity_romeo_0", Integer.valueOf(R.layout.activity_romeo));
            sKeys.put("layout/activity_round_apps_0", Integer.valueOf(R.layout.activity_round_apps));
            sKeys.put("layout/als_id7_fragment_dash_video_0", Integer.valueOf(R.layout.als_id7_fragment_dash_video));
            sKeys.put("layout-sw600dp-land/als_id7_fragment_dash_video_0", Integer.valueOf(R.layout.als_id7_fragment_dash_video));
            sKeys.put("layout-sw600dp-land/als_id7_fragment_music_0", Integer.valueOf(R.layout.als_id7_fragment_music));
            sKeys.put("layout/als_id7_fragment_music_0", Integer.valueOf(R.layout.als_id7_fragment_music));
            sKeys.put("layout/als_id7_fragment_navi_car_0", Integer.valueOf(R.layout.als_id7_fragment_navi_car));
            sKeys.put("layout-sw600dp-land/als_id7_fragment_navi_car_0", Integer.valueOf(R.layout.als_id7_fragment_navi_car));
            sKeys.put("layout-sw600dp-land/als_id7_sub_car_view_0", Integer.valueOf(R.layout.als_id7_sub_car_view));
            sKeys.put("layout/als_id7_sub_car_view_0", Integer.valueOf(R.layout.als_id7_sub_car_view));
            sKeys.put("layout-sw600dp-land/als_id7_sub_dashboard_view_0", Integer.valueOf(R.layout.als_id7_sub_dashboard_view));
            sKeys.put("layout/als_id7_sub_dashboard_view_0", Integer.valueOf(R.layout.als_id7_sub_dashboard_view));
            sKeys.put("layout-sw600dp-land/als_id7_sub_music_view_0", Integer.valueOf(R.layout.als_id7_sub_music_view));
            sKeys.put("layout/als_id7_sub_music_view_0", Integer.valueOf(R.layout.als_id7_sub_music_view));
            sKeys.put("layout-sw600dp-land/als_id7_sub_navi_view_0", Integer.valueOf(R.layout.als_id7_sub_navi_view));
            sKeys.put("layout/als_id7_sub_navi_view_0", Integer.valueOf(R.layout.als_id7_sub_navi_view));
            sKeys.put("layout-sw600dp-land/als_id7_sub_phone_view_0", Integer.valueOf(R.layout.als_id7_sub_phone_view));
            sKeys.put("layout/als_id7_sub_phone_view_0", Integer.valueOf(R.layout.als_id7_sub_phone_view));
            sKeys.put("layout/als_id7_sub_video_view_0", Integer.valueOf(R.layout.als_id7_sub_video_view));
            sKeys.put("layout-sw600dp-land/als_id7_sub_video_view_0", Integer.valueOf(R.layout.als_id7_sub_video_view));
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
            sKeys.put("layout/id7_fragment_car_hicar_0", Integer.valueOf(R.layout.id7_fragment_car_hicar));
            sKeys.put("layout-sw600dp-land/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            sKeys.put("layout/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            sKeys.put("layout-sw600dp-land/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            sKeys.put("layout/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            sKeys.put("layout/id7_fragment_navi_hicar_0", Integer.valueOf(R.layout.id7_fragment_navi_hicar));
            sKeys.put("layout/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            sKeys.put("layout-sw600dp-land/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            sKeys.put("layout/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            sKeys.put("layout-sw600dp-land/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            sKeys.put("layout/id7_sub_hicar_view_0", Integer.valueOf(R.layout.id7_sub_hicar_view));
            sKeys.put("layout/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            sKeys.put("layout-sw600dp-land/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            sKeys.put("layout/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            sKeys.put("layout-sw600dp-land/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            sKeys.put("layout/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            sKeys.put("layout-sw600dp-land/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            sKeys.put("layout/id7_sub_phone_view_hicar_0", Integer.valueOf(R.layout.id7_sub_phone_view_hicar));
            sKeys.put("layout/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            sKeys.put("layout-sw600dp-land/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            sKeys.put("layout/landrover_main_0", Integer.valueOf(R.layout.landrover_main));
            sKeys.put("layout/landrover_main_bottom_lay_0", Integer.valueOf(R.layout.landrover_main_bottom_lay));
            sKeys.put("layout/landrover_main_fragment_one_0", Integer.valueOf(R.layout.landrover_main_fragment_one));
            sKeys.put("layout/landrover_main_fragment_two_0", Integer.valueOf(R.layout.landrover_main_fragment_two));
            sKeys.put("layout/ntg630_control_popup_0", Integer.valueOf(R.layout.ntg630_control_popup));
            sKeys.put("layout-sw600dp-land/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            sKeys.put("layout/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            sKeys.put("layout/round_app_item_0", Integer.valueOf(R.layout.round_app_item));
            sKeys.put("layout/ug_home_four_0", Integer.valueOf(R.layout.ug_home_four));
            sKeys.put("layout/ug_home_four2_0", Integer.valueOf(R.layout.ug_home_four2));
            sKeys.put("layout/ug_home_one_0", Integer.valueOf(R.layout.ug_home_one));
            sKeys.put("layout/ug_home_one2_0", Integer.valueOf(R.layout.ug_home_one2));
            sKeys.put("layout/ug_home_three_0", Integer.valueOf(R.layout.ug_home_three));
            sKeys.put("layout/ug_home_three2_0", Integer.valueOf(R.layout.ug_home_three2));
            sKeys.put("layout/ug_home_two_0", Integer.valueOf(R.layout.ug_home_two));
            sKeys.put("layout/ug_home_two2_0", Integer.valueOf(R.layout.ug_home_two2));
        }
    }
}
