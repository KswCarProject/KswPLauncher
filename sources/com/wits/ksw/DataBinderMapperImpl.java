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
import com.wits.ksw.databinding.ActivityAudiMib3BindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundAndroidBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundOemBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3TimeBindingImpl;
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
import com.wits.ksw.databinding.ActivityLandRoverSettingsBinding1280x660Impl;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBinding1920x660Impl;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBindingImpl;
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
import com.wits.ksw.databinding.ActivityMainLandRoverBinding1280x660Impl;
import com.wits.ksw.databinding.ActivityMainLandRoverBinding1920x660Impl;
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
import com.wits.ksw.databinding.AudiMib3AuxBindingImpl;
import com.wits.ksw.databinding.AudiMib3BrightnessBindingImpl;
import com.wits.ksw.databinding.AudiMib3EqCustomViewBindingImpl;
import com.wits.ksw.databinding.AudiMib3EqViewBindingImpl;
import com.wits.ksw.databinding.AudiMib3MainLayoutBindingImpl;
import com.wits.ksw.databinding.AudiMib3MainLeftBindingImpl;
import com.wits.ksw.databinding.AudiMib3NaviBindingImpl;
import com.wits.ksw.databinding.AudiMib3OneDataClsImpl;
import com.wits.ksw.databinding.AudiMib3PasswordBindingImpl;
import com.wits.ksw.databinding.AudiMib3ReverCameraBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightCarinfoBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightLogoBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightMediaBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightNaviBindingImpl;
import com.wits.ksw.databinding.AudiMib3SpeedUnitBindingImpl;
import com.wits.ksw.databinding.AudiMib3SysinfoBindingImpl;
import com.wits.ksw.databinding.AudiMib3SystemSetBindingImpl;
import com.wits.ksw.databinding.AudiMib3TempBindingImpl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsImpl;
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
import com.wits.ksw.databinding.Benz2021FragmentOne1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentOne1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentOneImpl;
import com.wits.ksw.databinding.Benz2021FragmentThree1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentThree1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentThreeImpl;
import com.wits.ksw.databinding.Benz2021FragmentTwo1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentTwo1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentTwoImpl;
import com.wits.ksw.databinding.BenzControlBindImpl;
import com.wits.ksw.databinding.BenzControlBindSw600dpLandImpl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding1920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding21024x600Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding21920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBindingImpl;
import com.wits.ksw.databinding.BenzMbux2021ItemBinding1024x600Impl;
import com.wits.ksw.databinding.BenzMbux2021ItemBinding1920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ItemBindingImpl;
import com.wits.ksw.databinding.BenzMbuxItemBindingImpl;
import com.wits.ksw.databinding.BenzNtg6FyBindCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyBindCls1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyBindClsImpl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneCls1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsImpl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsImpl;
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
import com.wits.ksw.databinding.ID6CuspFragmentFourImpl;
import com.wits.ksw.databinding.ID6CuspFragmentOneImpl;
import com.wits.ksw.databinding.ID6CuspFragmentThreeImpl;
import com.wits.ksw.databinding.ID6CuspFragmentTowImpl;
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
import com.wits.ksw.databinding.LandroverMainBinding1280x660Impl;
import com.wits.ksw.databinding.LandroverMainBinding1920x660Impl;
import com.wits.ksw.databinding.LandroverMainBindingImpl;
import com.wits.ksw.databinding.LandroverMainBottomLayBinding1280x660Impl;
import com.wits.ksw.databinding.LandroverMainBottomLayBinding1920x660Impl;
import com.wits.ksw.databinding.LandroverMainBottomLayBindingImpl;
import com.wits.ksw.databinding.LandroverOneFragment1280x660Impl;
import com.wits.ksw.databinding.LandroverOneFragment1920x660Impl;
import com.wits.ksw.databinding.LandroverOneFragmentImpl;
import com.wits.ksw.databinding.LandroverTwoFragment1280x660Impl;
import com.wits.ksw.databinding.LandroverTwoFragment1920x660Impl;
import com.wits.ksw.databinding.LandroverTwoFragmentImpl;
import com.wits.ksw.databinding.LexusLsBottomFragmentOneImpl;
import com.wits.ksw.databinding.LexusLsBottomFragmentTwoImpl;
import com.wits.ksw.databinding.LexusLsDragMainLayoutBindingImpl;
import com.wits.ksw.databinding.LexusLsMainLayoutBindingImpl;
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
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYALSID7APPS = 1;
    private static final int LAYOUT_ACTIVITYAUDI = 2;
    private static final int LAYOUT_ACTIVITYAUDIMIB3 = 3;
    private static final int LAYOUT_ACTIVITYAUDIMIB3SOUND = 4;
    private static final int LAYOUT_ACTIVITYAUDIMIB3SOUNDANDROID = 5;
    private static final int LAYOUT_ACTIVITYAUDIMIB3SOUNDOEM = 6;
    private static final int LAYOUT_ACTIVITYAUDIMIB3TIME = 7;
    private static final int LAYOUT_ACTIVITYAUDISOUND = 8;
    private static final int LAYOUT_ACTIVITYAUDITIME = 9;
    private static final int LAYOUT_ACTIVITYBMWNBT = 10;
    private static final int LAYOUT_ACTIVITYDASHBOARD = 11;
    private static final int LAYOUT_ACTIVITYDASHBOARDALS = 12;
    private static final int LAYOUT_ACTIVITYDASHBOARDAUDI = 13;
    private static final int LAYOUT_ACTIVITYDASHBOARDLC = 14;
    private static final int LAYOUT_ACTIVITYDASHBOARDLEXUS = 15;
    private static final int LAYOUT_ACTIVITYDASHBOARDSEVEN = 16;
    private static final int LAYOUT_ACTIVITYID7APPS = 17;
    private static final int LAYOUT_ACTIVITYLANDROVERSETTINGS = 18;
    private static final int LAYOUT_ACTIVITYLEXUSOEMFM = 19;
    private static final int LAYOUT_ACTIVITYMAINALSID6 = 20;
    private static final int LAYOUT_ACTIVITYMAINALSID7 = 21;
    private static final int LAYOUT_ACTIVITYMAINAUDI = 22;
    private static final int LAYOUT_ACTIVITYMAINBC = 23;
    private static final int LAYOUT_ACTIVITYMAINBENZGS = 24;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX = 25;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX2021 = 26;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX20212 = 27;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG5 = 28;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG6FY = 29;
    private static final int LAYOUT_ACTIVITYMAINBMW = 30;
    private static final int LAYOUT_ACTIVITYMAINGSUG = 31;
    private static final int LAYOUT_ACTIVITYMAINGSUG2 = 32;
    private static final int LAYOUT_ACTIVITYMAINID5 = 33;
    private static final int LAYOUT_ACTIVITYMAINID6GS = 34;
    private static final int LAYOUT_ACTIVITYMAINLANDROVER = 35;
    private static final int LAYOUT_ACTIVITYMAINLEXUS = 36;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE1 = 37;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE2 = 38;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE3 = 39;
    private static final int LAYOUT_ACTIVITYNTG6DASHBOARD = 40;
    private static final int LAYOUT_ACTIVITYROMEO = 41;
    private static final int LAYOUT_ACTIVITYROUNDAPPS = 42;
    private static final int LAYOUT_ALSID7FRAGMENTDASHVIDEO = 43;
    private static final int LAYOUT_ALSID7FRAGMENTMUSIC = 44;
    private static final int LAYOUT_ALSID7FRAGMENTNAVICAR = 45;
    private static final int LAYOUT_ALSID7SUBCARVIEW = 46;
    private static final int LAYOUT_ALSID7SUBDASHBOARDVIEW = 47;
    private static final int LAYOUT_ALSID7SUBMUSICVIEW = 48;
    private static final int LAYOUT_ALSID7SUBNAVIVIEW = 49;
    private static final int LAYOUT_ALSID7SUBPHONEVIEW = 50;
    private static final int LAYOUT_ALSID7SUBVIDEOVIEW = 51;
    private static final int LAYOUT_AUDIAUX = 52;
    private static final int LAYOUT_AUDIBRIGHTNESS = 53;
    private static final int LAYOUT_AUDIEQVIEW = 54;
    private static final int LAYOUT_AUDIMIB3AUX = 55;
    private static final int LAYOUT_AUDIMIB3BRIGHTNESS = 56;
    private static final int LAYOUT_AUDIMIB3EQCUSTOMVIEW = 57;
    private static final int LAYOUT_AUDIMIB3EQVIEW = 58;
    private static final int LAYOUT_AUDIMIB3FRAGMENTONE = 59;
    private static final int LAYOUT_AUDIMIB3FRAGMENTTWO = 60;
    private static final int LAYOUT_AUDIMIB3MAINLAYOUT = 61;
    private static final int LAYOUT_AUDIMIB3MAINLEFT = 62;
    private static final int LAYOUT_AUDIMIB3NAVI = 63;
    private static final int LAYOUT_AUDIMIB3PASSWORD = 64;
    private static final int LAYOUT_AUDIMIB3REVERCAMERA = 65;
    private static final int LAYOUT_AUDIMIB3RIGHTCARINFO = 66;
    private static final int LAYOUT_AUDIMIB3RIGHTLOGO = 67;
    private static final int LAYOUT_AUDIMIB3RIGHTMEDIA = 68;
    private static final int LAYOUT_AUDIMIB3RIGHTNAVI = 69;
    private static final int LAYOUT_AUDIMIB3SPEEDUNIT = 70;
    private static final int LAYOUT_AUDIMIB3SYSINFO = 71;
    private static final int LAYOUT_AUDIMIB3SYSTEMSET = 72;
    private static final int LAYOUT_AUDIMIB3TEMP = 73;
    private static final int LAYOUT_AUDINAVI = 74;
    private static final int LAYOUT_AUDIPASSWORD = 75;
    private static final int LAYOUT_AUDIREVERCAMERA = 76;
    private static final int LAYOUT_AUDIRIGHTCARINFO = 77;
    private static final int LAYOUT_AUDIRIGHTLOGO = 78;
    private static final int LAYOUT_AUDIRIGHTMEDIA = 79;
    private static final int LAYOUT_AUDIRIGHTNAVI = 80;
    private static final int LAYOUT_AUDISPEEDUNIT = 81;
    private static final int LAYOUT_AUDISYSINFO = 82;
    private static final int LAYOUT_AUDISYSTEMSET = 83;
    private static final int LAYOUT_AUDITEMP = 84;
    private static final int LAYOUT_BCITEM = 85;
    private static final int LAYOUT_BCNTG5ITEM = 86;
    private static final int LAYOUT_BCSUBVIEW = 87;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTONE = 88;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTTHREE = 89;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTTWO = 90;
    private static final int LAYOUT_BENZMBUX2021ITEM = 91;
    private static final int LAYOUT_BENZMBUXITEM = 92;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTONE = 93;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTTWO = 94;
    private static final int LAYOUT_FRABENZGSONE = 95;
    private static final int LAYOUT_FRABENZGSTWO = 96;
    private static final int LAYOUT_FRABMWEVOID6GSONE = 97;
    private static final int LAYOUT_FRABMWEVOID6GSTHREE = 98;
    private static final int LAYOUT_FRABMWEVOID6GSTWO = 99;
    private static final int LAYOUT_FRAGMENTID5TWO = 100;
    private static final int LAYOUT_ID6CUSPFRAGMENTFOUR = 101;
    private static final int LAYOUT_ID6CUSPFRAGMENTONE = 102;
    private static final int LAYOUT_ID6CUSPFRAGMENTTHREE = 103;
    private static final int LAYOUT_ID6CUSPFRAGMENTTOW = 104;
    private static final int LAYOUT_ID6FRAGMENTFOUR = 105;
    private static final int LAYOUT_ID6FRAGMENTONE = 106;
    private static final int LAYOUT_ID6FRAGMENTTHREE = 107;
    private static final int LAYOUT_ID6FRAGMENTTOW = 108;
    private static final int LAYOUT_ID6ONE = 109;
    private static final int LAYOUT_ID7APPITEM = 110;
    private static final int LAYOUT_ID7FRAGMENTCAR = 111;
    private static final int LAYOUT_ID7FRAGMENTCARHICAR = 112;
    private static final int LAYOUT_ID7FRAGMENTMEDIA = 113;
    private static final int LAYOUT_ID7FRAGMENTNAVI = 114;
    private static final int LAYOUT_ID7FRAGMENTNAVIHICAR = 115;
    private static final int LAYOUT_ID7SUBCARVIEW = 116;
    private static final int LAYOUT_ID7SUBDASHBOARDVIEW = 117;
    private static final int LAYOUT_ID7SUBHICARVIEW = 118;
    private static final int LAYOUT_ID7SUBMUSICVIEW = 119;
    private static final int LAYOUT_ID7SUBNAVIVIEW = 120;
    private static final int LAYOUT_ID7SUBPHONEVIEW = 121;
    private static final int LAYOUT_ID7SUBPHONEVIEWHICAR = 122;
    private static final int LAYOUT_ID7SUBVIDEOVIEW = 123;
    private static final int LAYOUT_LANDROVERMAIN = 124;
    private static final int LAYOUT_LANDROVERMAINBOTTOMLAY = 125;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTONE = 126;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTTWO = 127;
    private static final int LAYOUT_LEXUSLSBOTTOMFRAGMENTONE = 128;
    private static final int LAYOUT_LEXUSLSBOTTOMFRAGMENTTWO = 129;
    private static final int LAYOUT_LEXUSLSDRAGMAINLAYOUT = 130;
    private static final int LAYOUT_LEXUSLSMAINLAYOUT = 131;
    private static final int LAYOUT_NTG630CONTROLPOPUP = 132;
    private static final int LAYOUT_NTG6CONTROLPOPUP = 133;
    private static final int LAYOUT_ROUNDAPPITEM = 134;
    private static final int LAYOUT_UGHOMEFOUR = 135;
    private static final int LAYOUT_UGHOMEFOUR2 = 136;
    private static final int LAYOUT_UGHOMEONE = 137;
    private static final int LAYOUT_UGHOMEONE2 = 138;
    private static final int LAYOUT_UGHOMETHREE = 139;
    private static final int LAYOUT_UGHOMETHREE2 = 140;
    private static final int LAYOUT_UGHOMETWO = 141;
    private static final int LAYOUT_UGHOMETWO2 = 142;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(LAYOUT_UGHOMETWO2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_als_id7_apps, 1);
        sparseIntArray.put(R.layout.activity_audi, 2);
        sparseIntArray.put(R.layout.activity_audi_mib3, 3);
        sparseIntArray.put(R.layout.activity_audi_mib3_sound, 4);
        sparseIntArray.put(R.layout.activity_audi_mib3_sound_android, 5);
        sparseIntArray.put(R.layout.activity_audi_mib3_sound_oem, 6);
        sparseIntArray.put(R.layout.activity_audi_mib3_time, 7);
        sparseIntArray.put(R.layout.activity_audi_sound, 8);
        sparseIntArray.put(R.layout.activity_audi_time, 9);
        sparseIntArray.put(R.layout.activity_bmw_nbt, 10);
        sparseIntArray.put(R.layout.activity_dash_board, 11);
        sparseIntArray.put(R.layout.activity_dash_board_als, 12);
        sparseIntArray.put(R.layout.activity_dash_board_audi, 13);
        sparseIntArray.put(R.layout.activity_dash_board_lc, 14);
        sparseIntArray.put(R.layout.activity_dash_board_lexus, 15);
        sparseIntArray.put(R.layout.activity_dash_board_seven, 16);
        sparseIntArray.put(R.layout.activity_id7_apps, 17);
        sparseIntArray.put(R.layout.activity_land_rover_settings, 18);
        sparseIntArray.put(R.layout.activity_lexus_oem_fm, 19);
        sparseIntArray.put(R.layout.activity_main_als_id6, 20);
        sparseIntArray.put(R.layout.activity_main_als_id7, 21);
        sparseIntArray.put(R.layout.activity_main_audi, 22);
        sparseIntArray.put(R.layout.activity_main_bc, 23);
        sparseIntArray.put(R.layout.activity_main_benz_gs, 24);
        sparseIntArray.put(R.layout.activity_main_benz_mbux, 25);
        sparseIntArray.put(R.layout.activity_main_benz_mbux_2021, 26);
        sparseIntArray.put(R.layout.activity_main_benz_mbux_2021_2, 27);
        sparseIntArray.put(R.layout.activity_main_benz_ntg5, 28);
        sparseIntArray.put(R.layout.activity_main_benz_ntg6_fy, 29);
        sparseIntArray.put(R.layout.activity_main_bmw, 30);
        sparseIntArray.put(R.layout.activity_main_gsug, 31);
        sparseIntArray.put(R.layout.activity_main_gsug2, 32);
        sparseIntArray.put(R.layout.activity_main_id5, 33);
        sparseIntArray.put(R.layout.activity_main_id6_gs, 34);
        sparseIntArray.put(R.layout.activity_main_land_rover, 35);
        sparseIntArray.put(R.layout.activity_main_lexus, 36);
        sparseIntArray.put(R.layout.activity_main_lexus_page1, 37);
        sparseIntArray.put(R.layout.activity_main_lexus_page2, 38);
        sparseIntArray.put(R.layout.activity_main_lexus_page3, 39);
        sparseIntArray.put(R.layout.activity_ntg6_dash_board, 40);
        sparseIntArray.put(R.layout.activity_romeo, 41);
        sparseIntArray.put(R.layout.activity_round_apps, 42);
        sparseIntArray.put(R.layout.als_id7_fragment_dash_video, 43);
        sparseIntArray.put(R.layout.als_id7_fragment_music, 44);
        sparseIntArray.put(R.layout.als_id7_fragment_navi_car, 45);
        sparseIntArray.put(R.layout.als_id7_sub_car_view, 46);
        sparseIntArray.put(R.layout.als_id7_sub_dashboard_view, 47);
        sparseIntArray.put(R.layout.als_id7_sub_music_view, 48);
        sparseIntArray.put(R.layout.als_id7_sub_navi_view, 49);
        sparseIntArray.put(R.layout.als_id7_sub_phone_view, 50);
        sparseIntArray.put(R.layout.als_id7_sub_video_view, 51);
        sparseIntArray.put(R.layout.audi_aux, 52);
        sparseIntArray.put(R.layout.audi_brightness, 53);
        sparseIntArray.put(R.layout.audi_eq_view, 54);
        sparseIntArray.put(R.layout.audi_mib3_aux, 55);
        sparseIntArray.put(R.layout.audi_mib3_brightness, 56);
        sparseIntArray.put(R.layout.audi_mib3_eq_custom_view, 57);
        sparseIntArray.put(R.layout.audi_mib3_eq_view, 58);
        sparseIntArray.put(R.layout.audi_mib3_fragment_one, 59);
        sparseIntArray.put(R.layout.audi_mib3_fragment_two, 60);
        sparseIntArray.put(R.layout.audi_mib3_main_layout, 61);
        sparseIntArray.put(R.layout.audi_mib3_main_left, 62);
        sparseIntArray.put(R.layout.audi_mib3_navi, 63);
        sparseIntArray.put(R.layout.audi_mib3_password, 64);
        sparseIntArray.put(R.layout.audi_mib3_rever_camera, 65);
        sparseIntArray.put(R.layout.audi_mib3_right_carinfo, 66);
        sparseIntArray.put(R.layout.audi_mib3_right_logo, 67);
        sparseIntArray.put(R.layout.audi_mib3_right_media, 68);
        sparseIntArray.put(R.layout.audi_mib3_right_navi, 69);
        sparseIntArray.put(R.layout.audi_mib3_speed_unit, 70);
        sparseIntArray.put(R.layout.audi_mib3_sysinfo, 71);
        sparseIntArray.put(R.layout.audi_mib3_system_set, 72);
        sparseIntArray.put(R.layout.audi_mib3_temp, 73);
        sparseIntArray.put(R.layout.audi_navi, 74);
        sparseIntArray.put(R.layout.audi_password, 75);
        sparseIntArray.put(R.layout.audi_rever_camera, 76);
        sparseIntArray.put(R.layout.audi_right_carinfo, 77);
        sparseIntArray.put(R.layout.audi_right_logo, 78);
        sparseIntArray.put(R.layout.audi_right_media, 79);
        sparseIntArray.put(R.layout.audi_right_navi, 80);
        sparseIntArray.put(R.layout.audi_speed_unit, 81);
        sparseIntArray.put(R.layout.audi_sysinfo, 82);
        sparseIntArray.put(R.layout.audi_system_set, 83);
        sparseIntArray.put(R.layout.audi_temp, 84);
        sparseIntArray.put(R.layout.bc_item, 85);
        sparseIntArray.put(R.layout.bc_ntg5_item, 86);
        sparseIntArray.put(R.layout.bc_sub_view, 87);
        sparseIntArray.put(R.layout.benz_mbux_2021_fragment_one, 88);
        sparseIntArray.put(R.layout.benz_mbux_2021_fragment_three, 89);
        sparseIntArray.put(R.layout.benz_mbux_2021_fragment_two, 90);
        sparseIntArray.put(R.layout.benz_mbux_2021_item, 91);
        sparseIntArray.put(R.layout.benz_mbux_item, 92);
        sparseIntArray.put(R.layout.benz_ntg6_fy_fragment_one, 93);
        sparseIntArray.put(R.layout.benz_ntg6_fy_fragment_two, 94);
        sparseIntArray.put(R.layout.fra_benzgs_one, 95);
        sparseIntArray.put(R.layout.fra_benzgs_two, 96);
        sparseIntArray.put(R.layout.fra_bmw_evo_id6_gs_one, 97);
        sparseIntArray.put(R.layout.fra_bmw_evo_id6_gs_three, 98);
        sparseIntArray.put(R.layout.fra_bmw_evo_id6_gs_two, 99);
        sparseIntArray.put(R.layout.fragment_id5_two, 100);
        sparseIntArray.put(R.layout.id6_cusp_fragment_four, 101);
        sparseIntArray.put(R.layout.id6_cusp_fragment_one, 102);
        sparseIntArray.put(R.layout.id6_cusp_fragment_three, 103);
        sparseIntArray.put(R.layout.id6_cusp_fragment_tow, 104);
        sparseIntArray.put(R.layout.id6_fragment_four, 105);
        sparseIntArray.put(R.layout.id6_fragment_one, 106);
        sparseIntArray.put(R.layout.id6_fragment_three, 107);
        sparseIntArray.put(R.layout.id6_fragment_tow, 108);
        sparseIntArray.put(R.layout.id6_one, 109);
        sparseIntArray.put(R.layout.id7_app_item, 110);
        sparseIntArray.put(R.layout.id7_fragment_car, 111);
        sparseIntArray.put(R.layout.id7_fragment_car_hicar, 112);
        sparseIntArray.put(R.layout.id7_fragment_media, 113);
        sparseIntArray.put(R.layout.id7_fragment_navi, 114);
        sparseIntArray.put(R.layout.id7_fragment_navi_hicar, 115);
        sparseIntArray.put(R.layout.id7_sub_car_view, 116);
        sparseIntArray.put(R.layout.id7_sub_dashboard_view, 117);
        sparseIntArray.put(R.layout.id7_sub_hicar_view, 118);
        sparseIntArray.put(R.layout.id7_sub_music_view, 119);
        sparseIntArray.put(R.layout.id7_sub_navi_view, 120);
        sparseIntArray.put(R.layout.id7_sub_phone_view, 121);
        sparseIntArray.put(R.layout.id7_sub_phone_view_hicar, 122);
        sparseIntArray.put(R.layout.id7_sub_video_view, 123);
        sparseIntArray.put(R.layout.landrover_main, 124);
        sparseIntArray.put(R.layout.landrover_main_bottom_lay, 125);
        sparseIntArray.put(R.layout.landrover_main_fragment_one, 126);
        sparseIntArray.put(R.layout.landrover_main_fragment_two, 127);
        sparseIntArray.put(R.layout.lexus_ls_bottom_fragment_one, 128);
        sparseIntArray.put(R.layout.lexus_ls_bottom_fragment_two, 129);
        sparseIntArray.put(R.layout.lexus_ls_drag_main_layout, 130);
        sparseIntArray.put(R.layout.lexus_ls_main_layout, 131);
        sparseIntArray.put(R.layout.ntg630_control_popup, 132);
        sparseIntArray.put(R.layout.ntg6_control_popup, 133);
        sparseIntArray.put(R.layout.round_app_item, 134);
        sparseIntArray.put(R.layout.ug_home_four, 135);
        sparseIntArray.put(R.layout.ug_home_four2, 136);
        sparseIntArray.put(R.layout.ug_home_one, LAYOUT_UGHOMEONE);
        sparseIntArray.put(R.layout.ug_home_one2, LAYOUT_UGHOMEONE2);
        sparseIntArray.put(R.layout.ug_home_three, LAYOUT_UGHOMETHREE);
        sparseIntArray.put(R.layout.ug_home_three2, LAYOUT_UGHOMETHREE2);
        sparseIntArray.put(R.layout.ug_home_two, LAYOUT_UGHOMETWO);
        sparseIntArray.put(R.layout.ug_home_two2, LAYOUT_UGHOMETWO2);
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
                if ("layout-sw600dp-land/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_audi_0".equals(tag)) {
                    return new ActivityAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi is invalid. Received: " + tag);
            case 3:
                if ("layout/activity_audi_mib3_0".equals(tag)) {
                    return new ActivityAudiMib3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3 is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_audi_mib3_sound_0".equals(tag)) {
                    return new ActivityAudiMib3SoundBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_audi_mib3_sound_android_0".equals(tag)) {
                    return new ActivityAudiMib3SoundAndroidBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound_android is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_audi_mib3_sound_oem_0".equals(tag)) {
                    return new ActivityAudiMib3SoundOemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound_oem is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_audi_mib3_time_0".equals(tag)) {
                    return new ActivityAudiMib3TimeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_time is invalid. Received: " + tag);
            case 8:
                if ("layout/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_sound is invalid. Received: " + tag);
            case 9:
                if ("layout-sw600dp-land/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_audi_time_0".equals(tag)) {
                    return new ActivityAudiTimeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_time is invalid. Received: " + tag);
            case 10:
                if ("layout/activity_bmw_nbt_0".equals(tag)) {
                    return new ActivityBmwNbtBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_bmw_nbt is invalid. Received: " + tag);
            case 11:
                if ("layout-sw600dp-land/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board is invalid. Received: " + tag);
            case 12:
                if ("layout/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_als is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_dash_board_audi_0".equals(tag)) {
                    return new ActivityDashBoardAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi is invalid. Received: " + tag);
            case 14:
                if ("layout/activity_dash_board_lc_0".equals(tag)) {
                    return new ActivityDashBoardLcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lc is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_dash_board_lexus_0".equals(tag)) {
                    return new ActivityDashBoardLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lexus is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_seven is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id7_apps is invalid. Received: " + tag);
            case 18:
                if ("layout-1280x660/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBinding1280x660Impl(component, view);
                }
                if ("layout-1920x660/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBinding1920x660Impl(component, view);
                }
                if ("layout/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_land_rover_settings is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_lexus_oem_fm_0".equals(tag)) {
                    return new ActivityLexusOemFmBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_lexus_oem_fm is invalid. Received: " + tag);
            case 20:
                if ("layout/activity_main_als_id6_0".equals(tag)) {
                    return new ActivityMainAlsId6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id6 is invalid. Received: " + tag);
            case 21:
                if ("layout/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id7 is invalid. Received: " + tag);
            case 22:
                if ("layout-sw600dp-land/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_audi is invalid. Received: " + tag);
            case 23:
                if ("layout-sw600dp-land/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bc is invalid. Received: " + tag);
            case 24:
                if ("layout/activity_main_benz_gs_0".equals(tag)) {
                    return new ActivityMainBenzGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_gs is invalid. Received: " + tag);
            case 25:
                if ("layout/activity_main_benz_mbux_0".equals(tag)) {
                    return new ActivityMainBenzMbuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux is invalid. Received: " + tag);
            case 26:
                if ("layout-1920x720/activity_main_benz_mbux_2021_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding1920x720Impl(component, view);
                }
                if ("layout/activity_main_benz_mbux_2021_0".equals(tag)) {
                    return new BenzMbux2021ActivityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021 is invalid. Received: " + tag);
            case 27:
                if ("layout-1024x600/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding21024x600Impl(component, view);
                }
                if ("layout/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding2Impl(component, view);
                }
                if ("layout-1920x720/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding21920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021_2 is invalid. Received: " + tag);
            case 28:
                if ("layout/activity_main_benz_ntg5_0".equals(tag)) {
                    return new ActivityMainBenzNtg5BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg5 is invalid. Received: " + tag);
            case 29:
                if ("layout-1920x720/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindCls1920x720Impl(component, view);
                }
                if ("layout/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindClsImpl(component, view);
                }
                if ("layout-1024x600/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindCls1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg6_fy is invalid. Received: " + tag);
            case 30:
                if ("layout/activity_main_bmw_0".equals(tag)) {
                    return new MainActivityImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_bmw_0".equals(tag)) {
                    return new MainActivitySw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bmw is invalid. Received: " + tag);
            case 31:
                if ("layout/activity_main_gsug_0".equals(tag)) {
                    return new ActivityMainGsugBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug is invalid. Received: " + tag);
            case 32:
                if ("layout/activity_main_gsug2_0".equals(tag)) {
                    return new ActivityMainGsug2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug2 is invalid. Received: " + tag);
            case 33:
                if ("layout-sw600dp-land/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id5 is invalid. Received: " + tag);
            case 34:
                if ("layout/activity_main_id6_gs_0".equals(tag)) {
                    return new ActivityMainId6GsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id6_gs is invalid. Received: " + tag);
            case 35:
                if ("layout-1280x660/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBinding1280x660Impl(component, view);
                }
                if ("layout-1920x660/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBinding1920x660Impl(component, view);
                }
                if ("layout/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_land_rover is invalid. Received: " + tag);
            case 36:
                if ("layout/activity_main_lexus_0".equals(tag)) {
                    return new ActivityMainLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus is invalid. Received: " + tag);
            case 37:
                if ("layout/activity_main_lexus_page1_0".equals(tag)) {
                    return new ActivityMainLexusPage1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page1 is invalid. Received: " + tag);
            case 38:
                if ("layout/activity_main_lexus_page2_0".equals(tag)) {
                    return new ActivityMainLexusPage2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page2 is invalid. Received: " + tag);
            case 39:
                if ("layout/activity_main_lexus_page3_0".equals(tag)) {
                    return new ActivityMainLexusPage3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page3 is invalid. Received: " + tag);
            case 40:
                if ("layout-sw600dp-land/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ntg6_dash_board is invalid. Received: " + tag);
            case 41:
                if ("layout/activity_romeo_0".equals(tag)) {
                    return new ActivityRomeoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_romeo is invalid. Received: " + tag);
            case 42:
                if ("layout/activity_round_apps_0".equals(tag)) {
                    return new ActivityRoundAppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_round_apps is invalid. Received: " + tag);
            case 43:
                if ("layout/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_dash_video is invalid. Received: " + tag);
            case 44:
                if ("layout-sw600dp-land/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_music is invalid. Received: " + tag);
            case 45:
                if ("layout-sw600dp-land/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_navi_car is invalid. Received: " + tag);
            case 46:
                if ("layout-sw600dp-land/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_car_view is invalid. Received: " + tag);
            case 47:
                if ("layout/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_dashboard_view is invalid. Received: " + tag);
            case 48:
                if ("layout-sw600dp-land/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_music_view is invalid. Received: " + tag);
            case 49:
                if ("layout/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_navi_view is invalid. Received: " + tag);
            case 50:
                if ("layout/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_phone_view is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_video_view is invalid. Received: " + tag);
            case 52:
                if ("layout/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_aux is invalid. Received: " + tag);
            case 53:
                if ("layout/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_brightness is invalid. Received: " + tag);
            case 54:
                if ("layout-sw600dp-land/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_eq_view is invalid. Received: " + tag);
            case 55:
                if ("layout/audi_mib3_aux_0".equals(tag)) {
                    return new AudiMib3AuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_aux is invalid. Received: " + tag);
            case 56:
                if ("layout/audi_mib3_brightness_0".equals(tag)) {
                    return new AudiMib3BrightnessBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_brightness is invalid. Received: " + tag);
            case 57:
                if ("layout/audi_mib3_eq_custom_view_0".equals(tag)) {
                    return new AudiMib3EqCustomViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_eq_custom_view is invalid. Received: " + tag);
            case 58:
                if ("layout/audi_mib3_eq_view_0".equals(tag)) {
                    return new AudiMib3EqViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_eq_view is invalid. Received: " + tag);
            case 59:
                if ("layout/audi_mib3_fragment_one_0".equals(tag)) {
                    return new AudiMib3OneDataClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_one is invalid. Received: " + tag);
            case 60:
                if ("layout/audi_mib3_fragment_two_0".equals(tag)) {
                    return new AudiMib3TwoDataClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_two is invalid. Received: " + tag);
            case 61:
                if ("layout/audi_mib3_main_layout_0".equals(tag)) {
                    return new AudiMib3MainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_main_layout is invalid. Received: " + tag);
            case 62:
                if ("layout/audi_mib3_main_left_0".equals(tag)) {
                    return new AudiMib3MainLeftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_main_left is invalid. Received: " + tag);
            case 63:
                if ("layout/audi_mib3_navi_0".equals(tag)) {
                    return new AudiMib3NaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_navi is invalid. Received: " + tag);
            case 64:
                if ("layout/audi_mib3_password_0".equals(tag)) {
                    return new AudiMib3PasswordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_password is invalid. Received: " + tag);
            case 65:
                if ("layout/audi_mib3_rever_camera_0".equals(tag)) {
                    return new AudiMib3ReverCameraBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_rever_camera is invalid. Received: " + tag);
            case 66:
                if ("layout/audi_mib3_right_carinfo_0".equals(tag)) {
                    return new AudiMib3RightCarinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_carinfo is invalid. Received: " + tag);
            case 67:
                if ("layout/audi_mib3_right_logo_0".equals(tag)) {
                    return new AudiMib3RightLogoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_logo is invalid. Received: " + tag);
            case 68:
                if ("layout/audi_mib3_right_media_0".equals(tag)) {
                    return new AudiMib3RightMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_media is invalid. Received: " + tag);
            case 69:
                if ("layout/audi_mib3_right_navi_0".equals(tag)) {
                    return new AudiMib3RightNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_navi is invalid. Received: " + tag);
            case 70:
                if ("layout/audi_mib3_speed_unit_0".equals(tag)) {
                    return new AudiMib3SpeedUnitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_speed_unit is invalid. Received: " + tag);
            case 71:
                if ("layout/audi_mib3_sysinfo_0".equals(tag)) {
                    return new AudiMib3SysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_sysinfo is invalid. Received: " + tag);
            case 72:
                if ("layout/audi_mib3_system_set_0".equals(tag)) {
                    return new AudiMib3SystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_system_set is invalid. Received: " + tag);
            case 73:
                if ("layout/audi_mib3_temp_0".equals(tag)) {
                    return new AudiMib3TempBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_temp is invalid. Received: " + tag);
            case 74:
                if ("layout/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_navi is invalid. Received: " + tag);
            case 75:
                if ("layout/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_password is invalid. Received: " + tag);
            case 76:
                if ("layout/audi_rever_camera_0".equals(tag)) {
                    return new AudiReverCameraBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_rever_camera is invalid. Received: " + tag);
            case 77:
                if ("layout-sw600dp-land/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_carinfo is invalid. Received: " + tag);
            case 78:
                if ("layout/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_logo is invalid. Received: " + tag);
            case 79:
                if ("layout/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_media is invalid. Received: " + tag);
            case 80:
                if ("layout-sw600dp-land/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_navi is invalid. Received: " + tag);
            case 81:
                if ("layout/audi_speed_unit_0".equals(tag)) {
                    return new AudiSpeedUnitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_speed_unit is invalid. Received: " + tag);
            case 82:
                if ("layout/audi_sysinfo_0".equals(tag)) {
                    return new AudiSysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_sysinfo is invalid. Received: " + tag);
            case 83:
                if ("layout/audi_system_set_0".equals(tag)) {
                    return new AudiSystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_system_set is invalid. Received: " + tag);
            case 84:
                if ("layout/audi_temp_0".equals(tag)) {
                    return new AudiTempBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_temp is invalid. Received: " + tag);
            case 85:
                if ("layout/bc_item_0".equals(tag)) {
                    return new BcItemBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/bc_item_0".equals(tag)) {
                    return new BcItemBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_item is invalid. Received: " + tag);
            case 86:
                if ("layout/bc_ntg5_item_0".equals(tag)) {
                    return new BcNtg5ItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_ntg5_item is invalid. Received: " + tag);
            case 87:
                if ("layout-sw600dp-land/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_sub_view is invalid. Received: " + tag);
            case 88:
                if ("layout-1024x600/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOne1024x600Impl(component, view);
                }
                if ("layout/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOneImpl(component, view);
                }
                if ("layout-1920x720/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOne1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_one is invalid. Received: " + tag);
            case 89:
                if ("layout-1920x720/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThree1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThreeImpl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThree1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_three is invalid. Received: " + tag);
            case 90:
                if ("layout-1024x600/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwo1024x600Impl(component, view);
                }
                if ("layout-1920x720/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwo1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_two is invalid. Received: " + tag);
            case 91:
                if ("layout/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBindingImpl(component, view);
                }
                if ("layout-1920x720/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBinding1920x720Impl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBinding1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_item is invalid. Received: " + tag);
            case 92:
                if ("layout/benz_mbux_item_0".equals(tag)) {
                    return new BenzMbuxItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_item is invalid. Received: " + tag);
            case 93:
                if ("layout-1024x600/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneCls1024x600Impl(component, view);
                }
                if ("layout/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsImpl(component, view);
                }
                if ("layout-1920x720/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneCls1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_one is invalid. Received: " + tag);
            case 94:
                if ("layout-1024x600/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoCls1024x600Impl(component, view);
                }
                if ("layout/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsImpl(component, view);
                }
                if ("layout-1920x720/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoCls1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_two is invalid. Received: " + tag);
            case 95:
                if ("layout/fra_benzgs_one_0".equals(tag)) {
                    return new FraBenzgsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_one is invalid. Received: " + tag);
            case 96:
                if ("layout/fra_benzgs_two_0".equals(tag)) {
                    return new FraBenzgsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_two is invalid. Received: " + tag);
            case 97:
                if ("layout/fra_bmw_evo_id6_gs_one_0".equals(tag)) {
                    return new FraBmwEvoId6GsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_one is invalid. Received: " + tag);
            case 98:
                if ("layout/fra_bmw_evo_id6_gs_three_0".equals(tag)) {
                    return new FraBmwEvoId6GsThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_three is invalid. Received: " + tag);
            case 99:
                if ("layout/fra_bmw_evo_id6_gs_two_0".equals(tag)) {
                    return new FraBmwEvoId6GsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_two is invalid. Received: " + tag);
            case 100:
                if ("layout/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_id5_two is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding2(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 101:
                if ("layout/id6_cusp_fragment_four_0".equals(tag)) {
                    return new ID6CuspFragmentFourImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_four is invalid. Received: " + tag);
            case 102:
                if ("layout/id6_cusp_fragment_one_0".equals(tag)) {
                    return new ID6CuspFragmentOneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_one is invalid. Received: " + tag);
            case 103:
                if ("layout/id6_cusp_fragment_three_0".equals(tag)) {
                    return new ID6CuspFragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_three is invalid. Received: " + tag);
            case 104:
                if ("layout/id6_cusp_fragment_tow_0".equals(tag)) {
                    return new ID6CuspFragmentTowImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_tow is invalid. Received: " + tag);
            case 105:
                if ("layout/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_four is invalid. Received: " + tag);
            case 106:
                if ("layout/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_one is invalid. Received: " + tag);
            case 107:
                if ("layout-sw600dp-land/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeSw600dpLandImpl(component, view);
                }
                if ("layout/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_three is invalid. Received: " + tag);
            case 108:
                if ("layout-sw600dp-land/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowSw600dpLandImpl(component, view);
                }
                if ("layout/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_tow is invalid. Received: " + tag);
            case 109:
                if ("layout-sw600dp-land/id6_one_0".equals(tag)) {
                    return new ID6OneSw600dpLandImpl(component, view);
                }
                if ("layout/id6_one_0".equals(tag)) {
                    return new ID6OneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_one is invalid. Received: " + tag);
            case 110:
                if ("layout-sw600dp-land/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_app_item is invalid. Received: " + tag);
            case 111:
                if ("layout-sw600dp-land/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car is invalid. Received: " + tag);
            case 112:
                if ("layout/id7_fragment_car_hicar_0".equals(tag)) {
                    return new HicarCarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car_hicar is invalid. Received: " + tag);
            case 113:
                if ("layout-sw600dp-land/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_media is invalid. Received: " + tag);
            case 114:
                if ("layout/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi is invalid. Received: " + tag);
            case 115:
                if ("layout/id7_fragment_navi_hicar_0".equals(tag)) {
                    return new HicarNaviFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi_hicar is invalid. Received: " + tag);
            case 116:
                if ("layout/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_car_view is invalid. Received: " + tag);
            case 117:
                if ("layout-sw600dp-land/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_dashboard_view is invalid. Received: " + tag);
            case 118:
                if ("layout/id7_sub_hicar_view_0".equals(tag)) {
                    return new Id7SubHicarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_hicar_view is invalid. Received: " + tag);
            case 119:
                if ("layout-sw600dp-land/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_music_view is invalid. Received: " + tag);
            case 120:
                if ("layout/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_navi_view is invalid. Received: " + tag);
            case 121:
                if ("layout/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view is invalid. Received: " + tag);
            case 122:
                if ("layout/id7_sub_phone_view_hicar_0".equals(tag)) {
                    return new Id7SubPhoneViewHicarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view_hicar is invalid. Received: " + tag);
            case 123:
                if ("layout/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_video_view is invalid. Received: " + tag);
            case 124:
                if ("layout-1920x660/landrover_main_0".equals(tag)) {
                    return new LandroverMainBinding1920x660Impl(component, view);
                }
                if ("layout/landrover_main_0".equals(tag)) {
                    return new LandroverMainBindingImpl(component, view);
                }
                if ("layout-1280x660/landrover_main_0".equals(tag)) {
                    return new LandroverMainBinding1280x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main is invalid. Received: " + tag);
            case 125:
                if ("layout-1920x660/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBinding1920x660Impl(component, view);
                }
                if ("layout-1280x660/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBinding1280x660Impl(component, view);
                }
                if ("layout/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_bottom_lay is invalid. Received: " + tag);
            case 126:
                if ("layout-1280x660/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragment1280x660Impl(component, view);
                }
                if ("layout-1920x660/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragment1920x660Impl(component, view);
                }
                if ("layout/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_fragment_one is invalid. Received: " + tag);
            case 127:
                if ("layout/landrover_main_fragment_two_0".equals(tag)) {
                    return new LandroverTwoFragmentImpl(component, view);
                }
                if ("layout-1920x660/landrover_main_fragment_two_0".equals(tag)) {
                    return new LandroverTwoFragment1920x660Impl(component, view);
                }
                if ("layout-1280x660/landrover_main_fragment_two_0".equals(tag)) {
                    return new LandroverTwoFragment1280x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_fragment_two is invalid. Received: " + tag);
            case 128:
                if ("layout/lexus_ls_bottom_fragment_one_0".equals(tag)) {
                    return new LexusLsBottomFragmentOneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_bottom_fragment_one is invalid. Received: " + tag);
            case 129:
                if ("layout/lexus_ls_bottom_fragment_two_0".equals(tag)) {
                    return new LexusLsBottomFragmentTwoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_bottom_fragment_two is invalid. Received: " + tag);
            case 130:
                if ("layout/lexus_ls_drag_main_layout_0".equals(tag)) {
                    return new LexusLsDragMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_drag_main_layout is invalid. Received: " + tag);
            case 131:
                if ("layout/lexus_ls_main_layout_0".equals(tag)) {
                    return new LexusLsMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_main_layout is invalid. Received: " + tag);
            case 132:
                if ("layout/ntg630_control_popup_0".equals(tag)) {
                    return new Ntg630ControlPopupBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg630_control_popup is invalid. Received: " + tag);
            case 133:
                if ("layout/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindImpl(component, view);
                }
                if ("layout-sw600dp-land/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg6_control_popup is invalid. Received: " + tag);
            case 134:
                if ("layout/round_app_item_0".equals(tag)) {
                    return new RoundAppItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for round_app_item is invalid. Received: " + tag);
            case 135:
                if ("layout/ug_home_four_0".equals(tag)) {
                    return new UgHomeFourBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four is invalid. Received: " + tag);
            case 136:
                if ("layout/ug_home_four2_0".equals(tag)) {
                    return new UgHomeFour2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMEONE /*137*/:
                if ("layout/ug_home_one_0".equals(tag)) {
                    return new UgHomeOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one is invalid. Received: " + tag);
            case LAYOUT_UGHOMEONE2 /*138*/:
                if ("layout/ug_home_one2_0".equals(tag)) {
                    return new UgHomeOne2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMETHREE /*139*/:
                if ("layout/ug_home_three_0".equals(tag)) {
                    return new UgHomeThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three is invalid. Received: " + tag);
            case LAYOUT_UGHOMETHREE2 /*140*/:
                if ("layout/ug_home_three2_0".equals(tag)) {
                    return new UgHomeThree2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMETWO /*141*/:
                if ("layout/ug_home_two_0".equals(tag)) {
                    return new UgHomeTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two is invalid. Received: " + tag);
            case LAYOUT_UGHOMETWO2 /*142*/:
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
                case 2:
                    return internalGetViewDataBinding2(component, view, localizedLayoutId, tag);
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
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(18);
            sKeys = sparseArray;
            sparseArray.put(1, "AppViewModel");
            sparseArray.put(2, "CarViewModel");
            sparseArray.put(3, "DashVideoViewModel");
            sparseArray.put(4, "LauncherViewModel");
            sparseArray.put(5, "LexusLsViewModel");
            sparseArray.put(6, "MediaViewModel");
            sparseArray.put(7, "MusicPhoneViewModel");
            sparseArray.put(8, "NaviCarViewModel");
            sparseArray.put(9, "NaviViewModel");
            sparseArray.put(10, "ViewModel");
            sparseArray.put(0, "_all");
            sparseArray.put(11, "listItem");
            sparseArray.put(12, "mBcVieModel");
            sparseArray.put(13, "mUiParams");
            sparseArray.put(14, "nbtModel");
            sparseArray.put(15, "vieModel");
            sparseArray.put(16, "viewModel");
            sparseArray.put(17, "vm");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(222);
            sKeys = hashMap;
            Integer valueOf = Integer.valueOf(R.layout.activity_als_id7_apps);
            hashMap.put("layout/activity_als_id7_apps_0", valueOf);
            hashMap.put("layout-sw600dp-land/activity_als_id7_apps_0", valueOf);
            hashMap.put("layout-sw600dp-land/activity_audi_0", Integer.valueOf(R.layout.activity_audi));
            hashMap.put("layout/activity_audi_0", Integer.valueOf(R.layout.activity_audi));
            hashMap.put("layout/activity_audi_mib3_0", Integer.valueOf(R.layout.activity_audi_mib3));
            hashMap.put("layout/activity_audi_mib3_sound_0", Integer.valueOf(R.layout.activity_audi_mib3_sound));
            hashMap.put("layout/activity_audi_mib3_sound_android_0", Integer.valueOf(R.layout.activity_audi_mib3_sound_android));
            hashMap.put("layout/activity_audi_mib3_sound_oem_0", Integer.valueOf(R.layout.activity_audi_mib3_sound_oem));
            hashMap.put("layout/activity_audi_mib3_time_0", Integer.valueOf(R.layout.activity_audi_mib3_time));
            hashMap.put("layout/activity_audi_sound_0", Integer.valueOf(R.layout.activity_audi_sound));
            hashMap.put("layout-sw600dp-land/activity_audi_sound_0", Integer.valueOf(R.layout.activity_audi_sound));
            hashMap.put("layout-sw600dp-land/activity_audi_time_0", Integer.valueOf(R.layout.activity_audi_time));
            hashMap.put("layout/activity_audi_time_0", Integer.valueOf(R.layout.activity_audi_time));
            hashMap.put("layout/activity_bmw_nbt_0", Integer.valueOf(R.layout.activity_bmw_nbt));
            hashMap.put("layout-sw600dp-land/activity_dash_board_0", Integer.valueOf(R.layout.activity_dash_board));
            hashMap.put("layout/activity_dash_board_0", Integer.valueOf(R.layout.activity_dash_board));
            hashMap.put("layout/activity_dash_board_als_0", Integer.valueOf(R.layout.activity_dash_board_als));
            hashMap.put("layout-sw600dp-land/activity_dash_board_als_0", Integer.valueOf(R.layout.activity_dash_board_als));
            hashMap.put("layout/activity_dash_board_audi_0", Integer.valueOf(R.layout.activity_dash_board_audi));
            hashMap.put("layout/activity_dash_board_lc_0", Integer.valueOf(R.layout.activity_dash_board_lc));
            hashMap.put("layout/activity_dash_board_lexus_0", Integer.valueOf(R.layout.activity_dash_board_lexus));
            hashMap.put("layout/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            hashMap.put("layout-sw600dp-land/activity_dash_board_seven_0", Integer.valueOf(R.layout.activity_dash_board_seven));
            hashMap.put("layout/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            hashMap.put("layout-sw600dp-land/activity_id7_apps_0", Integer.valueOf(R.layout.activity_id7_apps));
            Integer valueOf2 = Integer.valueOf(R.layout.activity_land_rover_settings);
            hashMap.put("layout-1280x660/activity_land_rover_settings_0", valueOf2);
            hashMap.put("layout-1920x660/activity_land_rover_settings_0", valueOf2);
            hashMap.put("layout/activity_land_rover_settings_0", valueOf2);
            hashMap.put("layout/activity_lexus_oem_fm_0", Integer.valueOf(R.layout.activity_lexus_oem_fm));
            hashMap.put("layout/activity_main_als_id6_0", Integer.valueOf(R.layout.activity_main_als_id6));
            hashMap.put("layout/activity_main_als_id7_0", Integer.valueOf(R.layout.activity_main_als_id7));
            hashMap.put("layout-sw600dp-land/activity_main_als_id7_0", Integer.valueOf(R.layout.activity_main_als_id7));
            hashMap.put("layout-sw600dp-land/activity_main_audi_0", Integer.valueOf(R.layout.activity_main_audi));
            hashMap.put("layout/activity_main_audi_0", Integer.valueOf(R.layout.activity_main_audi));
            hashMap.put("layout-sw600dp-land/activity_main_bc_0", Integer.valueOf(R.layout.activity_main_bc));
            hashMap.put("layout/activity_main_bc_0", Integer.valueOf(R.layout.activity_main_bc));
            hashMap.put("layout/activity_main_benz_gs_0", Integer.valueOf(R.layout.activity_main_benz_gs));
            hashMap.put("layout/activity_main_benz_mbux_0", Integer.valueOf(R.layout.activity_main_benz_mbux));
            hashMap.put("layout-1920x720/activity_main_benz_mbux_2021_0", Integer.valueOf(R.layout.activity_main_benz_mbux_2021));
            hashMap.put("layout/activity_main_benz_mbux_2021_0", Integer.valueOf(R.layout.activity_main_benz_mbux_2021));
            Integer valueOf3 = Integer.valueOf(R.layout.activity_main_benz_mbux_2021_2);
            hashMap.put("layout-1024x600/activity_main_benz_mbux_2021_2_0", valueOf3);
            hashMap.put("layout/activity_main_benz_mbux_2021_2_0", valueOf3);
            hashMap.put("layout-1920x720/activity_main_benz_mbux_2021_2_0", valueOf3);
            hashMap.put("layout/activity_main_benz_ntg5_0", Integer.valueOf(R.layout.activity_main_benz_ntg5));
            Integer valueOf4 = Integer.valueOf(R.layout.activity_main_benz_ntg6_fy);
            hashMap.put("layout-1920x720/activity_main_benz_ntg6_fy_0", valueOf4);
            hashMap.put("layout/activity_main_benz_ntg6_fy_0", valueOf4);
            hashMap.put("layout-1024x600/activity_main_benz_ntg6_fy_0", valueOf4);
            hashMap.put("layout/activity_main_bmw_0", Integer.valueOf(R.layout.activity_main_bmw));
            hashMap.put("layout-sw600dp-land/activity_main_bmw_0", Integer.valueOf(R.layout.activity_main_bmw));
            hashMap.put("layout/activity_main_gsug_0", Integer.valueOf(R.layout.activity_main_gsug));
            hashMap.put("layout/activity_main_gsug2_0", Integer.valueOf(R.layout.activity_main_gsug2));
            hashMap.put("layout-sw600dp-land/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            hashMap.put("layout/activity_main_id5_0", Integer.valueOf(R.layout.activity_main_id5));
            hashMap.put("layout/activity_main_id6_gs_0", Integer.valueOf(R.layout.activity_main_id6_gs));
            Integer valueOf5 = Integer.valueOf(R.layout.activity_main_land_rover);
            hashMap.put("layout-1280x660/activity_main_land_rover_0", valueOf5);
            hashMap.put("layout-1920x660/activity_main_land_rover_0", valueOf5);
            hashMap.put("layout/activity_main_land_rover_0", valueOf5);
            hashMap.put("layout/activity_main_lexus_0", Integer.valueOf(R.layout.activity_main_lexus));
            hashMap.put("layout/activity_main_lexus_page1_0", Integer.valueOf(R.layout.activity_main_lexus_page1));
            hashMap.put("layout/activity_main_lexus_page2_0", Integer.valueOf(R.layout.activity_main_lexus_page2));
            hashMap.put("layout/activity_main_lexus_page3_0", Integer.valueOf(R.layout.activity_main_lexus_page3));
            hashMap.put("layout-sw600dp-land/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            hashMap.put("layout/activity_ntg6_dash_board_0", Integer.valueOf(R.layout.activity_ntg6_dash_board));
            hashMap.put("layout/activity_romeo_0", Integer.valueOf(R.layout.activity_romeo));
            hashMap.put("layout/activity_round_apps_0", Integer.valueOf(R.layout.activity_round_apps));
            hashMap.put("layout/als_id7_fragment_dash_video_0", Integer.valueOf(R.layout.als_id7_fragment_dash_video));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_dash_video_0", Integer.valueOf(R.layout.als_id7_fragment_dash_video));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_music_0", Integer.valueOf(R.layout.als_id7_fragment_music));
            hashMap.put("layout/als_id7_fragment_music_0", Integer.valueOf(R.layout.als_id7_fragment_music));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_navi_car_0", Integer.valueOf(R.layout.als_id7_fragment_navi_car));
            hashMap.put("layout/als_id7_fragment_navi_car_0", Integer.valueOf(R.layout.als_id7_fragment_navi_car));
            hashMap.put("layout-sw600dp-land/als_id7_sub_car_view_0", Integer.valueOf(R.layout.als_id7_sub_car_view));
            hashMap.put("layout/als_id7_sub_car_view_0", Integer.valueOf(R.layout.als_id7_sub_car_view));
            hashMap.put("layout/als_id7_sub_dashboard_view_0", Integer.valueOf(R.layout.als_id7_sub_dashboard_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_dashboard_view_0", Integer.valueOf(R.layout.als_id7_sub_dashboard_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_music_view_0", Integer.valueOf(R.layout.als_id7_sub_music_view));
            hashMap.put("layout/als_id7_sub_music_view_0", Integer.valueOf(R.layout.als_id7_sub_music_view));
            hashMap.put("layout/als_id7_sub_navi_view_0", Integer.valueOf(R.layout.als_id7_sub_navi_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_navi_view_0", Integer.valueOf(R.layout.als_id7_sub_navi_view));
            hashMap.put("layout/als_id7_sub_phone_view_0", Integer.valueOf(R.layout.als_id7_sub_phone_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_phone_view_0", Integer.valueOf(R.layout.als_id7_sub_phone_view));
            hashMap.put("layout/als_id7_sub_video_view_0", Integer.valueOf(R.layout.als_id7_sub_video_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_video_view_0", Integer.valueOf(R.layout.als_id7_sub_video_view));
            hashMap.put("layout/audi_aux_0", Integer.valueOf(R.layout.audi_aux));
            hashMap.put("layout-sw600dp-land/audi_aux_0", Integer.valueOf(R.layout.audi_aux));
            hashMap.put("layout/audi_brightness_0", Integer.valueOf(R.layout.audi_brightness));
            hashMap.put("layout-sw600dp-land/audi_brightness_0", Integer.valueOf(R.layout.audi_brightness));
            hashMap.put("layout-sw600dp-land/audi_eq_view_0", Integer.valueOf(R.layout.audi_eq_view));
            hashMap.put("layout/audi_eq_view_0", Integer.valueOf(R.layout.audi_eq_view));
            hashMap.put("layout/audi_mib3_aux_0", Integer.valueOf(R.layout.audi_mib3_aux));
            hashMap.put("layout/audi_mib3_brightness_0", Integer.valueOf(R.layout.audi_mib3_brightness));
            hashMap.put("layout/audi_mib3_eq_custom_view_0", Integer.valueOf(R.layout.audi_mib3_eq_custom_view));
            hashMap.put("layout/audi_mib3_eq_view_0", Integer.valueOf(R.layout.audi_mib3_eq_view));
            hashMap.put("layout/audi_mib3_fragment_one_0", Integer.valueOf(R.layout.audi_mib3_fragment_one));
            hashMap.put("layout/audi_mib3_fragment_two_0", Integer.valueOf(R.layout.audi_mib3_fragment_two));
            hashMap.put("layout/audi_mib3_main_layout_0", Integer.valueOf(R.layout.audi_mib3_main_layout));
            hashMap.put("layout/audi_mib3_main_left_0", Integer.valueOf(R.layout.audi_mib3_main_left));
            hashMap.put("layout/audi_mib3_navi_0", Integer.valueOf(R.layout.audi_mib3_navi));
            hashMap.put("layout/audi_mib3_password_0", Integer.valueOf(R.layout.audi_mib3_password));
            hashMap.put("layout/audi_mib3_rever_camera_0", Integer.valueOf(R.layout.audi_mib3_rever_camera));
            hashMap.put("layout/audi_mib3_right_carinfo_0", Integer.valueOf(R.layout.audi_mib3_right_carinfo));
            hashMap.put("layout/audi_mib3_right_logo_0", Integer.valueOf(R.layout.audi_mib3_right_logo));
            hashMap.put("layout/audi_mib3_right_media_0", Integer.valueOf(R.layout.audi_mib3_right_media));
            hashMap.put("layout/audi_mib3_right_navi_0", Integer.valueOf(R.layout.audi_mib3_right_navi));
            hashMap.put("layout/audi_mib3_speed_unit_0", Integer.valueOf(R.layout.audi_mib3_speed_unit));
            hashMap.put("layout/audi_mib3_sysinfo_0", Integer.valueOf(R.layout.audi_mib3_sysinfo));
            hashMap.put("layout/audi_mib3_system_set_0", Integer.valueOf(R.layout.audi_mib3_system_set));
            hashMap.put("layout/audi_mib3_temp_0", Integer.valueOf(R.layout.audi_mib3_temp));
            hashMap.put("layout/audi_navi_0", Integer.valueOf(R.layout.audi_navi));
            hashMap.put("layout-sw600dp-land/audi_navi_0", Integer.valueOf(R.layout.audi_navi));
            hashMap.put("layout/audi_password_0", Integer.valueOf(R.layout.audi_password));
            hashMap.put("layout-sw600dp-land/audi_password_0", Integer.valueOf(R.layout.audi_password));
            hashMap.put("layout/audi_rever_camera_0", Integer.valueOf(R.layout.audi_rever_camera));
            hashMap.put("layout-sw600dp-land/audi_right_carinfo_0", Integer.valueOf(R.layout.audi_right_carinfo));
            hashMap.put("layout/audi_right_carinfo_0", Integer.valueOf(R.layout.audi_right_carinfo));
            hashMap.put("layout/audi_right_logo_0", Integer.valueOf(R.layout.audi_right_logo));
            hashMap.put("layout-sw600dp-land/audi_right_logo_0", Integer.valueOf(R.layout.audi_right_logo));
            hashMap.put("layout/audi_right_media_0", Integer.valueOf(R.layout.audi_right_media));
            hashMap.put("layout-sw600dp-land/audi_right_media_0", Integer.valueOf(R.layout.audi_right_media));
            hashMap.put("layout-sw600dp-land/audi_right_navi_0", Integer.valueOf(R.layout.audi_right_navi));
            hashMap.put("layout/audi_right_navi_0", Integer.valueOf(R.layout.audi_right_navi));
            hashMap.put("layout/audi_speed_unit_0", Integer.valueOf(R.layout.audi_speed_unit));
            hashMap.put("layout/audi_sysinfo_0", Integer.valueOf(R.layout.audi_sysinfo));
            hashMap.put("layout/audi_system_set_0", Integer.valueOf(R.layout.audi_system_set));
            hashMap.put("layout/audi_temp_0", Integer.valueOf(R.layout.audi_temp));
            hashMap.put("layout/bc_item_0", Integer.valueOf(R.layout.bc_item));
            hashMap.put("layout-sw600dp-land/bc_item_0", Integer.valueOf(R.layout.bc_item));
            hashMap.put("layout/bc_ntg5_item_0", Integer.valueOf(R.layout.bc_ntg5_item));
            hashMap.put("layout-sw600dp-land/bc_sub_view_0", Integer.valueOf(R.layout.bc_sub_view));
            hashMap.put("layout/bc_sub_view_0", Integer.valueOf(R.layout.bc_sub_view));
            Integer valueOf6 = Integer.valueOf(R.layout.benz_mbux_2021_fragment_one);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_one_0", valueOf6);
            hashMap.put("layout/benz_mbux_2021_fragment_one_0", valueOf6);
            hashMap.put("layout-1920x720/benz_mbux_2021_fragment_one_0", valueOf6);
            Integer valueOf7 = Integer.valueOf(R.layout.benz_mbux_2021_fragment_three);
            hashMap.put("layout-1920x720/benz_mbux_2021_fragment_three_0", valueOf7);
            hashMap.put("layout/benz_mbux_2021_fragment_three_0", valueOf7);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_three_0", valueOf7);
            Integer valueOf8 = Integer.valueOf(R.layout.benz_mbux_2021_fragment_two);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_two_0", valueOf8);
            hashMap.put("layout-1920x720/benz_mbux_2021_fragment_two_0", valueOf8);
            hashMap.put("layout/benz_mbux_2021_fragment_two_0", valueOf8);
            Integer valueOf9 = Integer.valueOf(R.layout.benz_mbux_2021_item);
            hashMap.put("layout/benz_mbux_2021_item_0", valueOf9);
            hashMap.put("layout-1920x720/benz_mbux_2021_item_0", valueOf9);
            hashMap.put("layout-1024x600/benz_mbux_2021_item_0", valueOf9);
            hashMap.put("layout/benz_mbux_item_0", Integer.valueOf(R.layout.benz_mbux_item));
            Integer valueOf10 = Integer.valueOf(R.layout.benz_ntg6_fy_fragment_one);
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_one_0", valueOf10);
            hashMap.put("layout/benz_ntg6_fy_fragment_one_0", valueOf10);
            hashMap.put("layout-1920x720/benz_ntg6_fy_fragment_one_0", valueOf10);
            Integer valueOf11 = Integer.valueOf(R.layout.benz_ntg6_fy_fragment_two);
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_two_0", valueOf11);
            hashMap.put("layout/benz_ntg6_fy_fragment_two_0", valueOf11);
            hashMap.put("layout-1920x720/benz_ntg6_fy_fragment_two_0", valueOf11);
            hashMap.put("layout/fra_benzgs_one_0", Integer.valueOf(R.layout.fra_benzgs_one));
            hashMap.put("layout/fra_benzgs_two_0", Integer.valueOf(R.layout.fra_benzgs_two));
            hashMap.put("layout/fra_bmw_evo_id6_gs_one_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_one));
            hashMap.put("layout/fra_bmw_evo_id6_gs_three_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_three));
            hashMap.put("layout/fra_bmw_evo_id6_gs_two_0", Integer.valueOf(R.layout.fra_bmw_evo_id6_gs_two));
            hashMap.put("layout/fragment_id5_two_0", Integer.valueOf(R.layout.fragment_id5_two));
            hashMap.put("layout-sw600dp-land/fragment_id5_two_0", Integer.valueOf(R.layout.fragment_id5_two));
            hashMap.put("layout/id6_cusp_fragment_four_0", Integer.valueOf(R.layout.id6_cusp_fragment_four));
            hashMap.put("layout/id6_cusp_fragment_one_0", Integer.valueOf(R.layout.id6_cusp_fragment_one));
            hashMap.put("layout/id6_cusp_fragment_three_0", Integer.valueOf(R.layout.id6_cusp_fragment_three));
            hashMap.put("layout/id6_cusp_fragment_tow_0", Integer.valueOf(R.layout.id6_cusp_fragment_tow));
            hashMap.put("layout/id6_fragment_four_0", Integer.valueOf(R.layout.id6_fragment_four));
            hashMap.put("layout-sw600dp-land/id6_fragment_four_0", Integer.valueOf(R.layout.id6_fragment_four));
            hashMap.put("layout/id6_fragment_one_0", Integer.valueOf(R.layout.id6_fragment_one));
            hashMap.put("layout-sw600dp-land/id6_fragment_one_0", Integer.valueOf(R.layout.id6_fragment_one));
            hashMap.put("layout-sw600dp-land/id6_fragment_three_0", Integer.valueOf(R.layout.id6_fragment_three));
            hashMap.put("layout/id6_fragment_three_0", Integer.valueOf(R.layout.id6_fragment_three));
            hashMap.put("layout-sw600dp-land/id6_fragment_tow_0", Integer.valueOf(R.layout.id6_fragment_tow));
            hashMap.put("layout/id6_fragment_tow_0", Integer.valueOf(R.layout.id6_fragment_tow));
            hashMap.put("layout-sw600dp-land/id6_one_0", Integer.valueOf(R.layout.id6_one));
            hashMap.put("layout/id6_one_0", Integer.valueOf(R.layout.id6_one));
            hashMap.put("layout-sw600dp-land/id7_app_item_0", Integer.valueOf(R.layout.id7_app_item));
            hashMap.put("layout/id7_app_item_0", Integer.valueOf(R.layout.id7_app_item));
            hashMap.put("layout-sw600dp-land/id7_fragment_car_0", Integer.valueOf(R.layout.id7_fragment_car));
            hashMap.put("layout/id7_fragment_car_0", Integer.valueOf(R.layout.id7_fragment_car));
            hashMap.put("layout/id7_fragment_car_hicar_0", Integer.valueOf(R.layout.id7_fragment_car_hicar));
            hashMap.put("layout-sw600dp-land/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            hashMap.put("layout/id7_fragment_media_0", Integer.valueOf(R.layout.id7_fragment_media));
            hashMap.put("layout/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            hashMap.put("layout-sw600dp-land/id7_fragment_navi_0", Integer.valueOf(R.layout.id7_fragment_navi));
            hashMap.put("layout/id7_fragment_navi_hicar_0", Integer.valueOf(R.layout.id7_fragment_navi_hicar));
            hashMap.put("layout/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            hashMap.put("layout-sw600dp-land/id7_sub_car_view_0", Integer.valueOf(R.layout.id7_sub_car_view));
            hashMap.put("layout-sw600dp-land/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            hashMap.put("layout/id7_sub_dashboard_view_0", Integer.valueOf(R.layout.id7_sub_dashboard_view));
            hashMap.put("layout/id7_sub_hicar_view_0", Integer.valueOf(R.layout.id7_sub_hicar_view));
            hashMap.put("layout-sw600dp-land/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            hashMap.put("layout/id7_sub_music_view_0", Integer.valueOf(R.layout.id7_sub_music_view));
            hashMap.put("layout/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            hashMap.put("layout-sw600dp-land/id7_sub_navi_view_0", Integer.valueOf(R.layout.id7_sub_navi_view));
            hashMap.put("layout/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            hashMap.put("layout-sw600dp-land/id7_sub_phone_view_0", Integer.valueOf(R.layout.id7_sub_phone_view));
            hashMap.put("layout/id7_sub_phone_view_hicar_0", Integer.valueOf(R.layout.id7_sub_phone_view_hicar));
            hashMap.put("layout/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            hashMap.put("layout-sw600dp-land/id7_sub_video_view_0", Integer.valueOf(R.layout.id7_sub_video_view));
            Integer valueOf12 = Integer.valueOf(R.layout.landrover_main);
            hashMap.put("layout-1920x660/landrover_main_0", valueOf12);
            hashMap.put("layout/landrover_main_0", valueOf12);
            hashMap.put("layout-1280x660/landrover_main_0", valueOf12);
            Integer valueOf13 = Integer.valueOf(R.layout.landrover_main_bottom_lay);
            hashMap.put("layout-1920x660/landrover_main_bottom_lay_0", valueOf13);
            hashMap.put("layout-1280x660/landrover_main_bottom_lay_0", valueOf13);
            hashMap.put("layout/landrover_main_bottom_lay_0", valueOf13);
            Integer valueOf14 = Integer.valueOf(R.layout.landrover_main_fragment_one);
            hashMap.put("layout-1280x660/landrover_main_fragment_one_0", valueOf14);
            hashMap.put("layout-1920x660/landrover_main_fragment_one_0", valueOf14);
            hashMap.put("layout/landrover_main_fragment_one_0", valueOf14);
            Integer valueOf15 = Integer.valueOf(R.layout.landrover_main_fragment_two);
            hashMap.put("layout/landrover_main_fragment_two_0", valueOf15);
            hashMap.put("layout-1920x660/landrover_main_fragment_two_0", valueOf15);
            hashMap.put("layout-1280x660/landrover_main_fragment_two_0", valueOf15);
            hashMap.put("layout/lexus_ls_bottom_fragment_one_0", Integer.valueOf(R.layout.lexus_ls_bottom_fragment_one));
            hashMap.put("layout/lexus_ls_bottom_fragment_two_0", Integer.valueOf(R.layout.lexus_ls_bottom_fragment_two));
            hashMap.put("layout/lexus_ls_drag_main_layout_0", Integer.valueOf(R.layout.lexus_ls_drag_main_layout));
            hashMap.put("layout/lexus_ls_main_layout_0", Integer.valueOf(R.layout.lexus_ls_main_layout));
            hashMap.put("layout/ntg630_control_popup_0", Integer.valueOf(R.layout.ntg630_control_popup));
            hashMap.put("layout/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            hashMap.put("layout-sw600dp-land/ntg6_control_popup_0", Integer.valueOf(R.layout.ntg6_control_popup));
            hashMap.put("layout/round_app_item_0", Integer.valueOf(R.layout.round_app_item));
            hashMap.put("layout/ug_home_four_0", Integer.valueOf(R.layout.ug_home_four));
            hashMap.put("layout/ug_home_four2_0", Integer.valueOf(R.layout.ug_home_four2));
            hashMap.put("layout/ug_home_one_0", Integer.valueOf(R.layout.ug_home_one));
            hashMap.put("layout/ug_home_one2_0", Integer.valueOf(R.layout.ug_home_one2));
            hashMap.put("layout/ug_home_three_0", Integer.valueOf(R.layout.ug_home_three));
            hashMap.put("layout/ug_home_three2_0", Integer.valueOf(R.layout.ug_home_three2));
            hashMap.put("layout/ug_home_two_0", Integer.valueOf(R.layout.ug_home_two));
            hashMap.put("layout/ug_home_two2_0", Integer.valueOf(R.layout.ug_home_two2));
        }
    }
}
