package com.wits.ksw;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.wits.ksw.databinding.ALSDasoardBindHdpi1280x720Impl;
import com.wits.ksw.databinding.ALSDasoardBindHdpi1920x720Impl;
import com.wits.ksw.databinding.ALSDasoardBindImpl;
import com.wits.ksw.databinding.ALSDasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAlsId7AppsBindingImpl;
import com.wits.ksw.databinding.ActivityAlsId7AppsBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiBindingImpl;
import com.wits.ksw.databinding.ActivityAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiMib3BindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityAudiMib3BindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundAndroidBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundOemBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityAudiMib3SoundOemBindingImpl;
import com.wits.ksw.databinding.ActivityAudiMib3TimeBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityAudiMib3TimeBindingImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingImpl;
import com.wits.ksw.databinding.ActivityAudiSoundBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingImpl;
import com.wits.ksw.databinding.ActivityAudiTimeBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityBmwNbtBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardAudiBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3BindingHdpi1560x700Impl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3BindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3BindingMdpi1280x480Impl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3FyBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3FyBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardAudiMib3FyBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.ActivityDashBoardLcBindingImpl;
import com.wits.ksw.databinding.ActivityDashBoardLexusBindingImpl;
import com.wits.ksw.databinding.ActivityGsId8LauncherMainBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.ActivityId7AppsBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityId7AppsBindingImpl;
import com.wits.ksw.databinding.ActivityId7AppsBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityId8AppsBindingImpl;
import com.wits.ksw.databinding.ActivityId8LauncherMainBindingImpl;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBinding1280x660Impl;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBinding1920x660Impl;
import com.wits.ksw.databinding.ActivityLandRoverSettingsBindingImpl;
import com.wits.ksw.databinding.ActivityLexusOemFmBindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId6BindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId7BindingImpl;
import com.wits.ksw.databinding.ActivityMainAlsId7BindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainAlsId7V2BindingImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingImpl;
import com.wits.ksw.databinding.ActivityMainAudiBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.ActivityMainBcBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityMainBcBindingImpl;
import com.wits.ksw.databinding.ActivityMainBcBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityMainBenzGsBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzMbuxBindingImpl;
import com.wits.ksw.databinding.ActivityMainBenzNtg5BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsug2BindingImpl;
import com.wits.ksw.databinding.ActivityMainGsugBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityMainGsugBindingImpl;
import com.wits.ksw.databinding.ActivityMainId6GsBindingImpl;
import com.wits.ksw.databinding.ActivityMainKswmbuxBindingImpl;
import com.wits.ksw.databinding.ActivityMainLandRoverBinding1280x660Impl;
import com.wits.ksw.databinding.ActivityMainLandRoverBinding1920x660Impl;
import com.wits.ksw.databinding.ActivityMainLandRoverBindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusBindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage1BindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage2BindingImpl;
import com.wits.ksw.databinding.ActivityMainLexusPage3BindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingImpl;
import com.wits.ksw.databinding.ActivityNtg6DashBoardBindingSw600dpLandImpl;
import com.wits.ksw.databinding.ActivityPempId8LauncherMainBindingImpl;
import com.wits.ksw.databinding.ActivityRomeoBindingImpl;
import com.wits.ksw.databinding.ActivityRomeoV2BindingImpl;
import com.wits.ksw.databinding.ActivityRoundAppsBindingImpl;
import com.wits.ksw.databinding.AlsId7SubCarViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubCarViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubDashboardViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubDashboardViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubMusicThirdLayoutBindingImpl;
import com.wits.ksw.databinding.AlsId7SubMusicViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubMusicViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubNaviViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubNaviViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubPhoneViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubPhoneViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubVideoViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubVideoViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AlsId7SubVideoViewV2BindingImpl;
import com.wits.ksw.databinding.AlsId7SubWeatherViewBindingImpl;
import com.wits.ksw.databinding.AlsId7SubZlinkViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiCarInfoBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiCarInfoBindingImpl;
import com.wits.ksw.databinding.AlsId7UiCarInfoSubViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiCarInfoSubViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiDashBoardSubViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiDashBoardSubViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiMainBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiMainBindingImpl;
import com.wits.ksw.databinding.AlsId7UiMediaBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiMediaBindingImpl;
import com.wits.ksw.databinding.AlsId7UiMusicSubViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiMusicSubViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiNaviBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiNaviBindingImpl;
import com.wits.ksw.databinding.AlsId7UiSubNaviViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiSubNaviViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiSubPhoneViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiSubPhoneViewBindingImpl;
import com.wits.ksw.databinding.AlsId7UiVideoSubViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AlsId7UiVideoSubViewBindingImpl;
import com.wits.ksw.databinding.AppThirdItemAlsId7UiBindingImpl;
import com.wits.ksw.databinding.AppThirdItemAudiBindingImpl;
import com.wits.ksw.databinding.AppThirdItemAudimbi31BindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AppThirdItemAudimbi31BindingImpl;
import com.wits.ksw.databinding.AppThirdItemAudimbi3BindingImpl;
import com.wits.ksw.databinding.AppThirdItemBenzNtg6BindingImpl;
import com.wits.ksw.databinding.AppThirdItemEvoId7BindingImpl;
import com.wits.ksw.databinding.AppThirdItemId6BindingImpl;
import com.wits.ksw.databinding.AppThirdItemId7BindingImpl;
import com.wits.ksw.databinding.AppThirdItemLandroverBindingImpl;
import com.wits.ksw.databinding.AppThirdItemLexusBindingImpl;
import com.wits.ksw.databinding.AppThirdItemRomeoBindingImpl;
import com.wits.ksw.databinding.AudiAuxBindingImpl;
import com.wits.ksw.databinding.AudiAuxBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiBrightnessBindingImpl;
import com.wits.ksw.databinding.AudiBrightnessBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiEqViewBindingImpl;
import com.wits.ksw.databinding.AudiEqViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiMbi3SelThirdClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMbi3SelThirdClsImpl;
import com.wits.ksw.databinding.AudiMib3AuxBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3AuxBindingImpl;
import com.wits.ksw.databinding.AudiMib3BrightnessBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3BrightnessBindingImpl;
import com.wits.ksw.databinding.AudiMib3EqCustomViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3EqCustomViewBindingImpl;
import com.wits.ksw.databinding.AudiMib3EqViewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3EqViewBindingImpl;
import com.wits.ksw.databinding.AudiMib3FyMainLayoutBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyMainLayoutBindingImpl;
import com.wits.ksw.databinding.AudiMib3FyMainLayoutBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3FyMainLeftBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyMainLeftBindingImpl;
import com.wits.ksw.databinding.AudiMib3FyMainLeftBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3FyOneDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyOneDataClsImpl;
import com.wits.ksw.databinding.AudiMib3FyOneDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3FyTwoDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyTwoDataClsImpl;
import com.wits.ksw.databinding.AudiMib3FyTwoDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3FyV2OneDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyV2OneDataClsImpl;
import com.wits.ksw.databinding.AudiMib3FyV2OneDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3FyV2TwoDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3FyV2TwoDataClsImpl;
import com.wits.ksw.databinding.AudiMib3FyV2TwoDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3MainLayoutBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3MainLayoutBindingImpl;
import com.wits.ksw.databinding.AudiMib3MainLayoutBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3MainLeftBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3MainLeftBindingImpl;
import com.wits.ksw.databinding.AudiMib3MainLeftBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3NaviBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3NaviBindingImpl;
import com.wits.ksw.databinding.AudiMib3OneDataClsHdpi1560x700Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsImpl;
import com.wits.ksw.databinding.AudiMib3OneDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsV2Hdpi1560x700Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsV2Hdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsV2Impl;
import com.wits.ksw.databinding.AudiMib3OneDataClsV2Mdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3PasswordBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3PasswordBindingImpl;
import com.wits.ksw.databinding.AudiMib3ReverCameraBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3ReverCameraBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightCarinfoBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightLogoBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightMediaBindingImpl;
import com.wits.ksw.databinding.AudiMib3RightNaviBindingImpl;
import com.wits.ksw.databinding.AudiMib3SpeedUnitBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3SpeedUnitBindingImpl;
import com.wits.ksw.databinding.AudiMib3SysinfoBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3SysinfoBindingImpl;
import com.wits.ksw.databinding.AudiMib3SystemSetBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3SystemSetBindingImpl;
import com.wits.ksw.databinding.AudiMib3TempBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TempBindingImpl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsHdpi1560x700Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsImpl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsV2Hdpi1560x700Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsV2Hdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsV2Impl;
import com.wits.ksw.databinding.AudiMib3TwoDataClsV2Mdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3TyMainLayoutBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TyMainLayoutBindingImpl;
import com.wits.ksw.databinding.AudiMib3TyMainLayoutBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3TyMainLeftBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TyMainLeftBindingImpl;
import com.wits.ksw.databinding.AudiMib3TyMainLeftBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3TyOneBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TyOneBindingImpl;
import com.wits.ksw.databinding.AudiMib3TyOneBindingMdpi1280x480Impl;
import com.wits.ksw.databinding.AudiMib3TyTwoBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.AudiMib3TyTwoBindingImpl;
import com.wits.ksw.databinding.AudiMib3TyTwoBindingMdpi1280x480Impl;
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
import com.wits.ksw.databinding.AudiRightWeatherBindingImpl;
import com.wits.ksw.databinding.AudiRightWeatherBindingSw600dpLandImpl;
import com.wits.ksw.databinding.AudiSelThirdClsImpl;
import com.wits.ksw.databinding.AudiSpeedUnitBindingImpl;
import com.wits.ksw.databinding.AudiSysinfoBindingImpl;
import com.wits.ksw.databinding.AudiSystemSetBindingImpl;
import com.wits.ksw.databinding.AudiTempBindingImpl;
import com.wits.ksw.databinding.BcItemBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.BcItemBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BcItemBindingImpl;
import com.wits.ksw.databinding.BcItemBindingSw600dpLandImpl;
import com.wits.ksw.databinding.BcNtg5ItemBindingImpl;
import com.wits.ksw.databinding.BcSubViewBindingImpl;
import com.wits.ksw.databinding.BcSubViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Benz2021FragmentOne1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentOneHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentOneHdpi1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentOneImpl;
import com.wits.ksw.databinding.Benz2021FragmentThree1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentThreeHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentThreeHdpi1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentThreeImpl;
import com.wits.ksw.databinding.Benz2021FragmentTwo1024x600Impl;
import com.wits.ksw.databinding.Benz2021FragmentTwoHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentTwoHdpi1920x720Impl;
import com.wits.ksw.databinding.Benz2021FragmentTwoImpl;
import com.wits.ksw.databinding.Benz2021KswFragmentOneHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswFragmentOneImpl;
import com.wits.ksw.databinding.Benz2021KswFragmentThreeHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswFragmentThreeImpl;
import com.wits.ksw.databinding.Benz2021KswFragmentTwoHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswFragmentTwoImpl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentOneHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentOneImpl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentThreeHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentThreeImpl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentTwoHdpi1280x720Impl;
import com.wits.ksw.databinding.Benz2021KswV2FragmentTwoImpl;
import com.wits.ksw.databinding.BenzControlBindImpl;
import com.wits.ksw.databinding.BenzControlBindSw600dpLandImpl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding21024x600Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2Hdpi1280x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2Hdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBinding2Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ActivityBindingImpl;
import com.wits.ksw.databinding.BenzMbux2021ItemBinding1024x600Impl;
import com.wits.ksw.databinding.BenzMbux2021ItemBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ItemBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbux2021ItemBindingImpl;
import com.wits.ksw.databinding.BenzMbux2021KswActivityBindingImpl;
import com.wits.ksw.databinding.BenzMbux2021KswItemBindingImpl;
import com.wits.ksw.databinding.BenzMbux2021KswV2ActivityBindingImpl;
import com.wits.ksw.databinding.BenzMbuxAppsCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxAppsCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxBtCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxBtCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxCarinfoCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxCarinfoCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxDashboardCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxDashboardCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxDvrCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxDvrCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxItemBindingImpl;
import com.wits.ksw.databinding.BenzMbuxKswActivityNewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxKswActivityNewBindingImpl;
import com.wits.ksw.databinding.BenzMbuxMusicCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxMusicCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxNaviCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxNaviCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxSetCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxSetCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxThirdCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxThirdCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxVideoCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxVideoCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxWeatherCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxWeatherCardBindingImpl;
import com.wits.ksw.databinding.BenzMbuxZlinkCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzMbuxZlinkCardBindingImpl;
import com.wits.ksw.databinding.BenzNtg6FyBindCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyBindClsHdpi1280x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyBindClsHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyBindClsImpl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsHdpi1280x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsImpl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsV21024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsV2Hdpi1280x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsV2Hdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentOneClsV2Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoCls1024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsHdpi1280x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsImpl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsV21024x600Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsV2Hdpi1280x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsV2Hdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtg6FyFragmentTwoClsV2Impl;
import com.wits.ksw.databinding.BenzNtgFyActivityNewBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyActivityNewBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyAppsCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyAppsCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyBtCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyBtCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyCarinfoCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyCarinfoCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyDashboardCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyDashboardCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyDvrCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyDvrCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyMusicCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyMusicCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyNaviCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyNaviCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFySetCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFySetCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyThirdCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyThirdCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyVideoCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyVideoCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyWeatherCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyWeatherCardBindingImpl;
import com.wits.ksw.databinding.BenzNtgFyZlinkCardBindingHdpi1920x720Impl;
import com.wits.ksw.databinding.BenzNtgFyZlinkCardBindingImpl;
import com.wits.ksw.databinding.BmwId8DashboardLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8DashboardLayoutNewBindingImpl;
import com.wits.ksw.databinding.BmwId8DashboardMusicLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8DashboardWeatherLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8ModusLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8PempModusLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsAudioAndroidLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsAudioLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsAudioOemLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsAudioSoundLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsFactoryLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsInfoLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsLanguageLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsMainLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsNaviLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemBrightnessLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemCameraLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemFuelLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemMusicLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemTempLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsSystemVideoLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8SettingsTimeLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8gsModusLayoutBindingImpl;
import com.wits.ksw.databinding.BmwId8gsSettingsMainLayoutBindingImpl;
import com.wits.ksw.databinding.BmwPempId8SettingsMainLayoutBindingImpl;
import com.wits.ksw.databinding.CarInfoDataBindingImpl;
import com.wits.ksw.databinding.CarInfoDataGsBindingImpl;
import com.wits.ksw.databinding.CarInfoImpl;
import com.wits.ksw.databinding.CarInfoPempDataBindingImpl;
import com.wits.ksw.databinding.CarInfoSw600dpLandImpl;
import com.wits.ksw.databinding.CarInfoV2Impl;
import com.wits.ksw.databinding.CarInfoV2Sw600dpLandImpl;
import com.wits.ksw.databinding.DashVideoFragmentImpl;
import com.wits.ksw.databinding.DashVideoFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.DashVideoFragment_V2Impl;
import com.wits.ksw.databinding.DashboardDataBindingImpl;
import com.wits.ksw.databinding.DashboardDataGsBindingImpl;
import com.wits.ksw.databinding.DashboardPempDataBindingImpl;
import com.wits.ksw.databinding.DasoardBindHdpi1280x720Impl;
import com.wits.ksw.databinding.DasoardBindHdpi1920x720Impl;
import com.wits.ksw.databinding.DasoardBindImpl;
import com.wits.ksw.databinding.DasoardBindSw600dpLandImpl;
import com.wits.ksw.databinding.FraBenzgsOneBindingImpl;
import com.wits.ksw.databinding.FraBenzgsTwoBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsOneBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsThreeBindingImpl;
import com.wits.ksw.databinding.FraBmwEvoId6GsTwoBindingImpl;
import com.wits.ksw.databinding.FragmentDashboardEditBindingImpl;
import com.wits.ksw.databinding.FragmentDashboardgsEditBindingImpl;
import com.wits.ksw.databinding.FragmentDashboardgsGsEditBindingImpl;
import com.wits.ksw.databinding.FragmentGsWeatherEditBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingImpl;
import com.wits.ksw.databinding.FragmentId5TwoBindingSw600dpLandImpl;
import com.wits.ksw.databinding.FragmentPempDashboardEditBindingImpl;
import com.wits.ksw.databinding.FragmentPempWeatherEditBindingImpl;
import com.wits.ksw.databinding.FragmentWeatherEditBindingImpl;
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
import com.wits.ksw.databinding.Id7AppItemBindingHdpi1280x720Impl;
import com.wits.ksw.databinding.Id7AppItemBindingHdpi1920x720Impl;
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
import com.wits.ksw.databinding.Id7SubWeatherViewBindingImpl;
import com.wits.ksw.databinding.Id7V2SubDashboardViewBindingImpl;
import com.wits.ksw.databinding.Id7V2SubDashboardViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7V2SubMusicViewBindingImpl;
import com.wits.ksw.databinding.Id7V2SubMusicViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7V2SubPhoneViewBindingImpl;
import com.wits.ksw.databinding.Id7V2SubPhoneViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id7V2SubVideoViewBindingImpl;
import com.wits.ksw.databinding.Id7V2SubVideoViewBindingSw600dpLandImpl;
import com.wits.ksw.databinding.Id8GsLauncherLeftBarBindingImpl;
import com.wits.ksw.databinding.Id8LauncherLeftBarBindingImpl;
import com.wits.ksw.databinding.Id8PempLauncherLeftBarBindingImpl;
import com.wits.ksw.databinding.IncludeNearWeatherBindingImpl;
import com.wits.ksw.databinding.IncludeNearWeatherEditBindingImpl;
import com.wits.ksw.databinding.KswId7MainPage1FragmentImpl;
import com.wits.ksw.databinding.KswId7MainPage2FragmentImpl;
import com.wits.ksw.databinding.KswmbuxHomeOneBindingImpl;
import com.wits.ksw.databinding.KswmbuxHomeThreeBindingImpl;
import com.wits.ksw.databinding.KswmbuxHomeTwoBindingImpl;
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
import com.wits.ksw.databinding.LexusLsBottomFragmentTwo_V2Impl;
import com.wits.ksw.databinding.LexusLsDragMainLayoutBindingImpl;
import com.wits.ksw.databinding.LexusLsMainLayoutBindingImpl;
import com.wits.ksw.databinding.MainActivityImpl;
import com.wits.ksw.databinding.MainActivitySw600dpLandImpl;
import com.wits.ksw.databinding.MainKswID7BindingImpl;
import com.wits.ksw.databinding.MediaFragmentBindingV2Impl;
import com.wits.ksw.databinding.MediaFragmentBindingV2Sw600dpLandImpl;
import com.wits.ksw.databinding.MediaFragmentImpl;
import com.wits.ksw.databinding.MediaFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.ModusDataBindingImpl;
import com.wits.ksw.databinding.ModusDataGsBindingImpl;
import com.wits.ksw.databinding.ModusPempDataBindingImpl;
import com.wits.ksw.databinding.MusicDataBindingImpl;
import com.wits.ksw.databinding.MusicDataGsBindingImpl;
import com.wits.ksw.databinding.MusicEditorDataBindingImpl;
import com.wits.ksw.databinding.MusicEditorPempDataBindingImpl;
import com.wits.ksw.databinding.MusicGsEditorDataBindingImpl;
import com.wits.ksw.databinding.MusicPempDataBindingImpl;
import com.wits.ksw.databinding.MusicPhoneFragmentImpl;
import com.wits.ksw.databinding.MusicPhoneFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviCarFragmentImpl;
import com.wits.ksw.databinding.NaviCarFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviFragmentBindingV2Impl;
import com.wits.ksw.databinding.NaviFragmentBindingV2Sw600dpLandImpl;
import com.wits.ksw.databinding.NaviFragmentImpl;
import com.wits.ksw.databinding.NaviFragmentSw600dpLandImpl;
import com.wits.ksw.databinding.NaviSubViewImpl;
import com.wits.ksw.databinding.NaviSubViewSw600dpLandImpl;
import com.wits.ksw.databinding.NavigateDataBindingImpl;
import com.wits.ksw.databinding.NavigateDataGsBindingImpl;
import com.wits.ksw.databinding.NavigatePempDataBindingImpl;
import com.wits.ksw.databinding.Ntg630ControlPopupBindingImpl;
import com.wits.ksw.databinding.Ntg630ControlPopupBindingSw600dpLandImpl;
import com.wits.ksw.databinding.PhoneDataBindingImpl;
import com.wits.ksw.databinding.PhoneDataGsBindingImpl;
import com.wits.ksw.databinding.PhoneEditorDataBindingImpl;
import com.wits.ksw.databinding.PhoneEditorPempDataBindingImpl;
import com.wits.ksw.databinding.PhoneGsEditorDataBindingImpl;
import com.wits.ksw.databinding.PhonePempDataBindingImpl;
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
import com.wits.ksw.databinding.VideoDataBindingImpl;
import com.wits.ksw.databinding.VideoDataGsBindingImpl;
import com.wits.ksw.databinding.VideoEditorDataBindingImpl;
import com.wits.ksw.databinding.VideoEditorPempDataBindingImpl;
import com.wits.ksw.databinding.VideoGsEditorDataBindingImpl;
import com.wits.ksw.databinding.VideoPempDataBindingImpl;
import com.wits.ksw.databinding.WeatherDataBindingImpl;
import com.wits.ksw.databinding.WeatherDataGsBindingImpl;
import com.wits.ksw.databinding.WeatherPempDataBindingImpl;
import com.wits.ksw.databinding.ZlinkWeatherFragmentImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes17.dex */
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
    private static final int LAYOUT_ACTIVITYDASHBOARDAUDIMIB3 = 14;
    private static final int LAYOUT_ACTIVITYDASHBOARDAUDIMIB3FY = 15;
    private static final int LAYOUT_ACTIVITYDASHBOARDLC = 16;
    private static final int LAYOUT_ACTIVITYDASHBOARDLEXUS = 17;
    private static final int LAYOUT_ACTIVITYDASHBOARDSEVEN = 18;
    private static final int LAYOUT_ACTIVITYGSID8LAUNCHERMAIN = 19;
    private static final int LAYOUT_ACTIVITYID7APPS = 20;
    private static final int LAYOUT_ACTIVITYID8APPS = 21;
    private static final int LAYOUT_ACTIVITYID8GSMODUSLAYOUT = 22;
    private static final int LAYOUT_ACTIVITYID8LAUNCHERMAIN = 23;
    private static final int LAYOUT_ACTIVITYID8MODUSLAYOUT = 24;
    private static final int LAYOUT_ACTIVITYID8PEMPMODUSLAYOUT = 25;
    private static final int LAYOUT_ACTIVITYLANDROVERSETTINGS = 26;
    private static final int LAYOUT_ACTIVITYLEXUSOEMFM = 27;
    private static final int LAYOUT_ACTIVITYMAINALSID6 = 28;
    private static final int LAYOUT_ACTIVITYMAINALSID7 = 29;
    private static final int LAYOUT_ACTIVITYMAINALSID7UI = 30;
    private static final int LAYOUT_ACTIVITYMAINALSID7V2 = 31;
    private static final int LAYOUT_ACTIVITYMAINAUDI = 32;
    private static final int LAYOUT_ACTIVITYMAINBC = 33;
    private static final int LAYOUT_ACTIVITYMAINBENZGS = 34;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX = 35;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX2021 = 36;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX20212 = 37;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX2021KSW = 38;
    private static final int LAYOUT_ACTIVITYMAINBENZMBUX2021KSWV2 = 39;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG5 = 40;
    private static final int LAYOUT_ACTIVITYMAINBENZNTG6FY = 41;
    private static final int LAYOUT_ACTIVITYMAINBMW = 42;
    private static final int LAYOUT_ACTIVITYMAINGSUG = 43;
    private static final int LAYOUT_ACTIVITYMAINGSUG2 = 44;
    private static final int LAYOUT_ACTIVITYMAINID5 = 45;
    private static final int LAYOUT_ACTIVITYMAINID6GS = 46;
    private static final int LAYOUT_ACTIVITYMAINKSWID7 = 47;
    private static final int LAYOUT_ACTIVITYMAINKSWMBUX = 48;
    private static final int LAYOUT_ACTIVITYMAINLANDROVER = 49;
    private static final int LAYOUT_ACTIVITYMAINLEXUS = 50;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE1 = 51;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE2 = 52;
    private static final int LAYOUT_ACTIVITYMAINLEXUSPAGE3 = 53;
    private static final int LAYOUT_ACTIVITYNTG6DASHBOARD = 54;
    private static final int LAYOUT_ACTIVITYPEMPID8LAUNCHERMAIN = 55;
    private static final int LAYOUT_ACTIVITYROMEO = 56;
    private static final int LAYOUT_ACTIVITYROMEOV2 = 57;
    private static final int LAYOUT_ACTIVITYROUNDAPPS = 58;
    private static final int LAYOUT_ALSID7FRAGMENTDASHVIDEO = 59;
    private static final int LAYOUT_ALSID7FRAGMENTDASHVIDEOV2 = 60;
    private static final int LAYOUT_ALSID7FRAGMENTMUSIC = 61;
    private static final int LAYOUT_ALSID7FRAGMENTNAVICAR = 62;
    private static final int LAYOUT_ALSID7FRAGMENTZLINKWEATHER = 63;
    private static final int LAYOUT_ALSID7SUBCARVIEW = 64;
    private static final int LAYOUT_ALSID7SUBDASHBOARDVIEW = 65;
    private static final int LAYOUT_ALSID7SUBMUSICTHIRDLAYOUT = 66;
    private static final int LAYOUT_ALSID7SUBMUSICVIEW = 67;
    private static final int LAYOUT_ALSID7SUBNAVIVIEW = 68;
    private static final int LAYOUT_ALSID7SUBPHONEVIEW = 69;
    private static final int LAYOUT_ALSID7SUBVIDEOVIEW = 70;
    private static final int LAYOUT_ALSID7SUBVIDEOVIEWV2 = 71;
    private static final int LAYOUT_ALSID7SUBWEATHERVIEW = 72;
    private static final int LAYOUT_ALSID7SUBZLINKVIEW = 73;
    private static final int LAYOUT_ALSID7UISUBCARVIEW = 74;
    private static final int LAYOUT_ALSID7UISUBDASHBOARDVIEW = 75;
    private static final int LAYOUT_ALSID7UISUBMUSICVIEW = 76;
    private static final int LAYOUT_ALSID7UISUBNAVIVIEW = 77;
    private static final int LAYOUT_ALSID7UISUBPHONEVIEW = 78;
    private static final int LAYOUT_ALSID7UISUBVIDEOVIEW = 79;
    private static final int LAYOUT_APPTHIRDITEMALSID7UI = 80;
    private static final int LAYOUT_APPTHIRDITEMAUDI = 81;
    private static final int LAYOUT_APPTHIRDITEMAUDIMBI3 = 82;
    private static final int LAYOUT_APPTHIRDITEMAUDIMBI31 = 83;
    private static final int LAYOUT_APPTHIRDITEMBENZNTG6 = 84;
    private static final int LAYOUT_APPTHIRDITEMEVOID7 = 85;
    private static final int LAYOUT_APPTHIRDITEMID6 = 86;
    private static final int LAYOUT_APPTHIRDITEMID7 = 87;
    private static final int LAYOUT_APPTHIRDITEMLANDROVER = 88;
    private static final int LAYOUT_APPTHIRDITEMLEXUS = 89;
    private static final int LAYOUT_APPTHIRDITEMROMEO = 90;
    private static final int LAYOUT_AUDIAUX = 91;
    private static final int LAYOUT_AUDIBRIGHTNESS = 92;
    private static final int LAYOUT_AUDIEQVIEW = 93;
    private static final int LAYOUT_AUDIMBI3SELTHIRDAPPLAYOUT = 132;
    private static final int LAYOUT_AUDIMIB3AUX = 94;
    private static final int LAYOUT_AUDIMIB3BRIGHTNESS = 95;
    private static final int LAYOUT_AUDIMIB3EQCUSTOMVIEW = 96;
    private static final int LAYOUT_AUDIMIB3EQVIEW = 97;
    private static final int LAYOUT_AUDIMIB3FRAGMENTONE = 98;
    private static final int LAYOUT_AUDIMIB3FRAGMENTONEV2 = 99;
    private static final int LAYOUT_AUDIMIB3FRAGMENTTWO = 100;
    private static final int LAYOUT_AUDIMIB3FRAGMENTTWOV2 = 101;
    private static final int LAYOUT_AUDIMIB3FYMAINLAYOUT = 102;
    private static final int LAYOUT_AUDIMIB3FYMAINLEFT = 103;
    private static final int LAYOUT_AUDIMIB3MAINLAYOUT = 104;
    private static final int LAYOUT_AUDIMIB3MAINLEFT = 105;
    private static final int LAYOUT_AUDIMIB3NAVI = 106;
    private static final int LAYOUT_AUDIMIB3PASSWORD = 107;
    private static final int LAYOUT_AUDIMIB3REVERCAMERA = 108;
    private static final int LAYOUT_AUDIMIB3RIGHTCARINFO = 109;
    private static final int LAYOUT_AUDIMIB3RIGHTLOGO = 110;
    private static final int LAYOUT_AUDIMIB3RIGHTMEDIA = 111;
    private static final int LAYOUT_AUDIMIB3RIGHTNAVI = 112;
    private static final int LAYOUT_AUDIMIB3SPEEDUNIT = 113;
    private static final int LAYOUT_AUDIMIB3SYSINFO = 114;
    private static final int LAYOUT_AUDIMIB3SYSTEMSET = 115;
    private static final int LAYOUT_AUDIMIB3TEMP = 116;
    private static final int LAYOUT_AUDIMIB3TYMAINLAYOUT = 117;
    private static final int LAYOUT_AUDIMIB3TYMAINLEFT = 118;
    private static final int LAYOUT_AUDINAVI = 119;
    private static final int LAYOUT_AUDIPASSWORD = 120;
    private static final int LAYOUT_AUDIREVERCAMERA = 121;
    private static final int LAYOUT_AUDIRIGHTCARINFO = 122;
    private static final int LAYOUT_AUDIRIGHTLOGO = 123;
    private static final int LAYOUT_AUDIRIGHTMEDIA = 124;
    private static final int LAYOUT_AUDIRIGHTNAVI = 125;
    private static final int LAYOUT_AUDIRIGHTWEATHER = 126;
    private static final int LAYOUT_AUDISELTHIRDAPPLAYOUT = 127;
    private static final int LAYOUT_AUDISPEEDUNIT = 128;
    private static final int LAYOUT_AUDISYSINFO = 129;
    private static final int LAYOUT_AUDISYSTEMSET = 130;
    private static final int LAYOUT_AUDITEMP = 131;
    private static final int LAYOUT_BCITEM = 133;
    private static final int LAYOUT_BCNTG5ITEM = 134;
    private static final int LAYOUT_BCSUBVIEW = 135;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTONE = 136;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTTHREE = 137;
    private static final int LAYOUT_BENZMBUX2021FRAGMENTTWO = 138;
    private static final int LAYOUT_BENZMBUX2021ITEM = 139;
    private static final int LAYOUT_BENZMBUX2021KSWITEM = 140;
    private static final int LAYOUT_BENZMBUXAPPSCARD = 141;
    private static final int LAYOUT_BENZMBUXBTCARD = 142;
    private static final int LAYOUT_BENZMBUXCARINFOCARD = 143;
    private static final int LAYOUT_BENZMBUXDASHBOARDCARD = 144;
    private static final int LAYOUT_BENZMBUXDVRCARD = 145;
    private static final int LAYOUT_BENZMBUXITEM = 146;
    private static final int LAYOUT_BENZMBUXKSWACTIVITYNEW = 147;
    private static final int LAYOUT_BENZMBUXMUSICCARD = 148;
    private static final int LAYOUT_BENZMBUXNAVICARD = 149;
    private static final int LAYOUT_BENZMBUXSETCARD = 150;
    private static final int LAYOUT_BENZMBUXTHIRDCARD = 151;
    private static final int LAYOUT_BENZMBUXVIDEOCARD = 152;
    private static final int LAYOUT_BENZMBUXWEATHERCARD = 153;
    private static final int LAYOUT_BENZMBUXZLINKCARD = 154;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTONE = 155;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTONEV2 = 156;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTTWO = 157;
    private static final int LAYOUT_BENZNTG6FYFRAGMENTTWOV2 = 158;
    private static final int LAYOUT_BENZNTGFYACTIVITYNEW = 159;
    private static final int LAYOUT_BENZNTGFYAPPSCARD = 160;
    private static final int LAYOUT_BENZNTGFYBTCARD = 161;
    private static final int LAYOUT_BENZNTGFYCARINFOCARD = 162;
    private static final int LAYOUT_BENZNTGFYDASHBOARDCARD = 163;
    private static final int LAYOUT_BENZNTGFYDVRCARD = 164;
    private static final int LAYOUT_BENZNTGFYMUSICCARD = 165;
    private static final int LAYOUT_BENZNTGFYNAVICARD = 166;
    private static final int LAYOUT_BENZNTGFYSETCARD = 167;
    private static final int LAYOUT_BENZNTGFYTHIRDCARD = 168;
    private static final int LAYOUT_BENZNTGFYVIDEOCARD = 169;
    private static final int LAYOUT_BENZNTGFYWEATHERCARD = 170;
    private static final int LAYOUT_BENZNTGFYZLINKCARD = 171;
    private static final int LAYOUT_BMWID8DASHBOARDLAYOUT = 172;
    private static final int LAYOUT_BMWID8DASHBOARDLAYOUTNEW = 173;
    private static final int LAYOUT_BMWID8DASHBOARDMUSICLAYOUT = 174;
    private static final int LAYOUT_BMWID8DASHBOARDWEATHERLAYOUT = 175;
    private static final int LAYOUT_BMWID8GSSETTINGSMAINLAYOUT = 193;
    private static final int LAYOUT_BMWID8SETTINGSAUDIOANDROIDLAYOUT = 176;
    private static final int LAYOUT_BMWID8SETTINGSAUDIOLAYOUT = 177;
    private static final int LAYOUT_BMWID8SETTINGSAUDIOOEMLAYOUT = 178;
    private static final int LAYOUT_BMWID8SETTINGSAUDIOSOUNDLAYOUT = 179;
    private static final int LAYOUT_BMWID8SETTINGSFACTORYLAYOUT = 180;
    private static final int LAYOUT_BMWID8SETTINGSINFOLAYOUT = 181;
    private static final int LAYOUT_BMWID8SETTINGSLANGUAGELAYOUT = 182;
    private static final int LAYOUT_BMWID8SETTINGSMAINLAYOUT = 183;
    private static final int LAYOUT_BMWID8SETTINGSNAVILAYOUT = 184;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMBRIGHTNESSLAYOUT = 185;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMCAMERALAYOUT = 186;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMFUELLAYOUT = 187;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMLAYOUT = 188;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMMUSICLAYOUT = 189;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMTEMPLAYOUT = 190;
    private static final int LAYOUT_BMWID8SETTINGSSYSTEMVIDEOLAYOUT = 191;
    private static final int LAYOUT_BMWID8SETTINGSTIMELAYOUT = 192;
    private static final int LAYOUT_BMWPEMPID8SETTINGSMAINLAYOUT = 194;
    private static final int LAYOUT_FRABENZGSONE = 195;
    private static final int LAYOUT_FRABENZGSTWO = 196;
    private static final int LAYOUT_FRABMWEVOID6GSONE = 197;
    private static final int LAYOUT_FRABMWEVOID6GSTHREE = 198;
    private static final int LAYOUT_FRABMWEVOID6GSTWO = 199;
    private static final int LAYOUT_FRAGMENTALSID7UICAR = 200;
    private static final int LAYOUT_FRAGMENTALSID7UIMEDIA = 201;
    private static final int LAYOUT_FRAGMENTALSID7UINAVI = 202;
    private static final int LAYOUT_FRAGMENTAUDIMIB3FYONE = 203;
    private static final int LAYOUT_FRAGMENTAUDIMIB3FYTWO = 204;
    private static final int LAYOUT_FRAGMENTAUDIMIB3FYV2ONE = 205;
    private static final int LAYOUT_FRAGMENTAUDIMIB3FYV2TWO = 206;
    private static final int LAYOUT_FRAGMENTAUDIMIB3TYONE = 207;
    private static final int LAYOUT_FRAGMENTAUDIMIB3TYTWO = 208;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWONE = 209;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWTHREE = 210;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWTWO = 211;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWV2ONE = 212;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWV2THREE = 213;
    private static final int LAYOUT_FRAGMENTBENZMBUX2021KSWV2TWO = 214;
    private static final int LAYOUT_FRAGMENTCARINFO = 215;
    private static final int LAYOUT_FRAGMENTCARINFOGS = 216;
    private static final int LAYOUT_FRAGMENTDASHBOARD = 217;
    private static final int LAYOUT_FRAGMENTDASHBOARDEDIT = 218;
    private static final int LAYOUT_FRAGMENTDASHBOARDGS = 219;
    private static final int LAYOUT_FRAGMENTDASHBOARDGSEDIT = 220;
    private static final int LAYOUT_FRAGMENTGSDASHBOARDGSEDIT = 221;
    private static final int LAYOUT_FRAGMENTGSMUSICEDIT = 222;
    private static final int LAYOUT_FRAGMENTGSPHONEEDIT = 223;
    private static final int LAYOUT_FRAGMENTGSVIDEOEDIT = 224;
    private static final int LAYOUT_FRAGMENTGSWEATHEREDIT = 225;
    private static final int LAYOUT_FRAGMENTID5TWO = 226;
    private static final int LAYOUT_FRAGMENTMODUS = 227;
    private static final int LAYOUT_FRAGMENTMODUSGS = 228;
    private static final int LAYOUT_FRAGMENTMUSIC = 229;
    private static final int LAYOUT_FRAGMENTMUSICEDIT = 230;
    private static final int LAYOUT_FRAGMENTMUSICGS = 231;
    private static final int LAYOUT_FRAGMENTNAVIGATE = 232;
    private static final int LAYOUT_FRAGMENTNAVIGATEGS = 233;
    private static final int LAYOUT_FRAGMENTPEMPCARINFO = 234;
    private static final int LAYOUT_FRAGMENTPEMPDASHBOARD = 235;
    private static final int LAYOUT_FRAGMENTPEMPDASHBOARDEDIT = 236;
    private static final int LAYOUT_FRAGMENTPEMPMODUS = 237;
    private static final int LAYOUT_FRAGMENTPEMPMUSIC = 238;
    private static final int LAYOUT_FRAGMENTPEMPMUSICEDIT = 239;
    private static final int LAYOUT_FRAGMENTPEMPNAVIGATE = 240;
    private static final int LAYOUT_FRAGMENTPEMPPHONE = 241;
    private static final int LAYOUT_FRAGMENTPEMPPHONEEDIT = 242;
    private static final int LAYOUT_FRAGMENTPEMPVIDEO = 243;
    private static final int LAYOUT_FRAGMENTPEMPVIDEOEDIT = 244;
    private static final int LAYOUT_FRAGMENTPEMPWEATHER = 245;
    private static final int LAYOUT_FRAGMENTPEMPWEATHEREDIT = 246;
    private static final int LAYOUT_FRAGMENTPHONE = 247;
    private static final int LAYOUT_FRAGMENTPHONEEDIT = 248;
    private static final int LAYOUT_FRAGMENTPHONEGS = 249;
    private static final int LAYOUT_FRAGMENTVIDEO = 250;
    private static final int LAYOUT_FRAGMENTVIDEOEDIT = 251;
    private static final int LAYOUT_FRAGMENTVIDEOGS = 252;
    private static final int LAYOUT_FRAGMENTWEATHER = 253;
    private static final int LAYOUT_FRAGMENTWEATHEREDIT = 254;
    private static final int LAYOUT_FRAGMENTWEATHERGS = 255;
    private static final int LAYOUT_ID6CUSPFRAGMENTFOUR = 256;
    private static final int LAYOUT_ID6CUSPFRAGMENTONE = 257;
    private static final int LAYOUT_ID6CUSPFRAGMENTTHREE = 258;
    private static final int LAYOUT_ID6CUSPFRAGMENTTOW = 259;
    private static final int LAYOUT_ID6FRAGMENTFOUR = 260;
    private static final int LAYOUT_ID6FRAGMENTONE = 261;
    private static final int LAYOUT_ID6FRAGMENTTHREE = 262;
    private static final int LAYOUT_ID6FRAGMENTTOW = 263;
    private static final int LAYOUT_ID6ONE = 264;
    private static final int LAYOUT_ID7APPITEM = 265;
    private static final int LAYOUT_ID7FRAGMENTCAR = 266;
    private static final int LAYOUT_ID7FRAGMENTCARHICAR = 267;
    private static final int LAYOUT_ID7FRAGMENTMEDIA = 268;
    private static final int LAYOUT_ID7FRAGMENTNAVI = 269;
    private static final int LAYOUT_ID7FRAGMENTNAVIHICAR = 270;
    private static final int LAYOUT_ID7SUBCARVIEW = 271;
    private static final int LAYOUT_ID7SUBDASHBOARDVIEW = 272;
    private static final int LAYOUT_ID7SUBHICARVIEW = 273;
    private static final int LAYOUT_ID7SUBMUSICVIEW = 274;
    private static final int LAYOUT_ID7SUBNAVIVIEW = 275;
    private static final int LAYOUT_ID7SUBPHONEVIEW = 276;
    private static final int LAYOUT_ID7SUBPHONEVIEWHICAR = 277;
    private static final int LAYOUT_ID7SUBVIDEOVIEW = 278;
    private static final int LAYOUT_ID7SUBWEATHERVIEW = 279;
    private static final int LAYOUT_ID7V2FRAGMENTCAR = 280;
    private static final int LAYOUT_ID7V2FRAGMENTMEDIA = 281;
    private static final int LAYOUT_ID7V2FRAGMENTNAVI = 282;
    private static final int LAYOUT_ID7V2SUBDASHBOARDVIEW = 283;
    private static final int LAYOUT_ID7V2SUBMUSICVIEW = 284;
    private static final int LAYOUT_ID7V2SUBPHONEVIEW = 285;
    private static final int LAYOUT_ID7V2SUBVIDEOVIEW = 286;
    private static final int LAYOUT_ID8GSLAUNCHERLEFTBAR = 287;
    private static final int LAYOUT_ID8LAUNCHERLEFTBAR = 288;
    private static final int LAYOUT_ID8PEMPLAUNCHERLEFTBAR = 289;
    private static final int LAYOUT_INCLUDENEARWEATHER = 290;
    private static final int LAYOUT_INCLUDENEARWEATHEREDIT = 291;
    private static final int LAYOUT_KSWID7MAINPAGE1 = 292;
    private static final int LAYOUT_KSWID7MAINPAGE2 = 293;
    private static final int LAYOUT_KSWMBUXHOMEONE = 294;
    private static final int LAYOUT_KSWMBUXHOMETHREE = 295;
    private static final int LAYOUT_KSWMBUXHOMETWO = 296;
    private static final int LAYOUT_LANDROVERMAIN = 297;
    private static final int LAYOUT_LANDROVERMAINBOTTOMLAY = 298;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTONE = 299;
    private static final int LAYOUT_LANDROVERMAINFRAGMENTTWO = 300;
    private static final int LAYOUT_LEXUSLSBOTTOMFRAGMENTONE = 301;
    private static final int LAYOUT_LEXUSLSBOTTOMFRAGMENTTWO = 302;
    private static final int LAYOUT_LEXUSLSBOTTOMFRAGMENTTWOV2 = 303;
    private static final int LAYOUT_LEXUSLSDRAGMAINLAYOUT = 304;
    private static final int LAYOUT_LEXUSLSMAINLAYOUT = 305;
    private static final int LAYOUT_NTG630CONTROLPOPUP = 306;
    private static final int LAYOUT_NTG6CONTROLPOPUP = 307;
    private static final int LAYOUT_ROUNDAPPITEM = 308;
    private static final int LAYOUT_UGHOMEFOUR = 309;
    private static final int LAYOUT_UGHOMEFOUR2 = 310;
    private static final int LAYOUT_UGHOMEONE = 311;
    private static final int LAYOUT_UGHOMEONE2 = 312;
    private static final int LAYOUT_UGHOMETHREE = 313;
    private static final int LAYOUT_UGHOMETHREE2 = 314;
    private static final int LAYOUT_UGHOMETWO = 315;
    private static final int LAYOUT_UGHOMETWO2 = 316;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(LAYOUT_UGHOMETWO2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(C0899R.C0902layout.activity_als_id7_apps, 1);
        sparseIntArray.put(C0899R.C0902layout.activity_audi, 2);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_mib3, 3);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_mib3_sound, 4);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_mib3_sound_android, 5);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_mib3_sound_oem, 6);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_mib3_time, 7);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_sound, 8);
        sparseIntArray.put(C0899R.C0902layout.activity_audi_time, 9);
        sparseIntArray.put(C0899R.C0902layout.activity_bmw_nbt, 10);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board, 11);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_als, 12);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_audi, 13);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_audi_mib3, 14);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_audi_mib3_fy, 15);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_lc, 16);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_lexus, 17);
        sparseIntArray.put(C0899R.C0902layout.activity_dash_board_seven, 18);
        sparseIntArray.put(C0899R.C0902layout.activity_gs_id8_launcher_main, 19);
        sparseIntArray.put(C0899R.C0902layout.activity_id7_apps, 20);
        sparseIntArray.put(C0899R.C0902layout.activity_id8_apps, 21);
        sparseIntArray.put(C0899R.C0902layout.activity_id8_gs_modus_layout, 22);
        sparseIntArray.put(C0899R.C0902layout.activity_id8_launcher_main, 23);
        sparseIntArray.put(C0899R.C0902layout.activity_id8_modus_layout, 24);
        sparseIntArray.put(C0899R.C0902layout.activity_id8_pemp_modus_layout, 25);
        sparseIntArray.put(C0899R.C0902layout.activity_land_rover_settings, 26);
        sparseIntArray.put(C0899R.C0902layout.activity_lexus_oem_fm, 27);
        sparseIntArray.put(C0899R.C0902layout.activity_main_als_id6, 28);
        sparseIntArray.put(C0899R.C0902layout.activity_main_als_id7, 29);
        sparseIntArray.put(C0899R.C0902layout.activity_main_als_id7_ui, 30);
        sparseIntArray.put(C0899R.C0902layout.activity_main_als_id7_v2, 31);
        sparseIntArray.put(C0899R.C0902layout.activity_main_audi, 32);
        sparseIntArray.put(C0899R.C0902layout.activity_main_bc, 33);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_gs, 34);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_mbux, 35);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_mbux_2021, 36);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_mbux_2021_2, 37);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_mbux_2021_ksw, 38);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_mbux_2021_ksw_v2, 39);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_ntg5, 40);
        sparseIntArray.put(C0899R.C0902layout.activity_main_benz_ntg6_fy, 41);
        sparseIntArray.put(C0899R.C0902layout.activity_main_bmw, 42);
        sparseIntArray.put(C0899R.C0902layout.activity_main_gsug, 43);
        sparseIntArray.put(C0899R.C0902layout.activity_main_gsug2, 44);
        sparseIntArray.put(C0899R.C0902layout.activity_main_id5, 45);
        sparseIntArray.put(C0899R.C0902layout.activity_main_id6_gs, 46);
        sparseIntArray.put(C0899R.C0902layout.activity_main_ksw_id7, 47);
        sparseIntArray.put(C0899R.C0902layout.activity_main_kswmbux, 48);
        sparseIntArray.put(C0899R.C0902layout.activity_main_land_rover, 49);
        sparseIntArray.put(C0899R.C0902layout.activity_main_lexus, 50);
        sparseIntArray.put(C0899R.C0902layout.activity_main_lexus_page1, 51);
        sparseIntArray.put(C0899R.C0902layout.activity_main_lexus_page2, 52);
        sparseIntArray.put(C0899R.C0902layout.activity_main_lexus_page3, 53);
        sparseIntArray.put(C0899R.C0902layout.activity_ntg6_dash_board, 54);
        sparseIntArray.put(C0899R.C0902layout.activity_pemp_id8_launcher_main, 55);
        sparseIntArray.put(C0899R.C0902layout.activity_romeo, 56);
        sparseIntArray.put(C0899R.C0902layout.activity_romeo_v2, 57);
        sparseIntArray.put(C0899R.C0902layout.activity_round_apps, 58);
        sparseIntArray.put(C0899R.C0902layout.als_id7_fragment_dash_video, 59);
        sparseIntArray.put(C0899R.C0902layout.als_id7_fragment_dash_video_v2, 60);
        sparseIntArray.put(C0899R.C0902layout.als_id7_fragment_music, 61);
        sparseIntArray.put(C0899R.C0902layout.als_id7_fragment_navi_car, 62);
        sparseIntArray.put(C0899R.C0902layout.als_id7_fragment_zlink_weather, 63);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_car_view, 64);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_dashboard_view, 65);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_music_third_layout, 66);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_music_view, 67);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_navi_view, 68);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_phone_view, 69);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_video_view, 70);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_video_view_v2, 71);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_weather_view, 72);
        sparseIntArray.put(C0899R.C0902layout.als_id7_sub_zlink_view, 73);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_car_view, 74);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_dashboard_view, 75);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_music_view, 76);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_navi_view, 77);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_phone_view, 78);
        sparseIntArray.put(C0899R.C0902layout.als_id7_ui_sub_video_view, 79);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_als_id7_ui, 80);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_audi, 81);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_audimbi3, 82);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_audimbi3_1, 83);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_benz_ntg6, 84);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_evo_id7, 85);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_id6, 86);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_id7, 87);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_landrover, 88);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_lexus, 89);
        sparseIntArray.put(C0899R.C0902layout.app_third_item_romeo, 90);
        sparseIntArray.put(C0899R.C0902layout.audi_aux, 91);
        sparseIntArray.put(C0899R.C0902layout.audi_brightness, 92);
        sparseIntArray.put(C0899R.C0902layout.audi_eq_view, 93);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_aux, 94);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_brightness, 95);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_eq_custom_view, 96);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_eq_view, 97);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fragment_one, 98);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fragment_one_v2, 99);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fragment_two, 100);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fragment_two_v2, 101);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fy_main_layout, 102);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_fy_main_left, 103);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_main_layout, 104);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_main_left, 105);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_navi, 106);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_password, 107);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_rever_camera, 108);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_right_carinfo, 109);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_right_logo, 110);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_right_media, 111);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_right_navi, 112);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_speed_unit, 113);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_sysinfo, 114);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_system_set, 115);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_temp, 116);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_ty_main_layout, 117);
        sparseIntArray.put(C0899R.C0902layout.audi_mib3_ty_main_left, 118);
        sparseIntArray.put(C0899R.C0902layout.audi_navi, 119);
        sparseIntArray.put(C0899R.C0902layout.audi_password, 120);
        sparseIntArray.put(C0899R.C0902layout.audi_rever_camera, 121);
        sparseIntArray.put(C0899R.C0902layout.audi_right_carinfo, 122);
        sparseIntArray.put(C0899R.C0902layout.audi_right_logo, 123);
        sparseIntArray.put(C0899R.C0902layout.audi_right_media, 124);
        sparseIntArray.put(C0899R.C0902layout.audi_right_navi, 125);
        sparseIntArray.put(C0899R.C0902layout.audi_right_weather, 126);
        sparseIntArray.put(C0899R.C0902layout.audi_sel_third_app_layout, 127);
        sparseIntArray.put(C0899R.C0902layout.audi_speed_unit, 128);
        sparseIntArray.put(C0899R.C0902layout.audi_sysinfo, 129);
        sparseIntArray.put(C0899R.C0902layout.audi_system_set, 130);
        sparseIntArray.put(C0899R.C0902layout.audi_temp, 131);
        sparseIntArray.put(C0899R.C0902layout.audimbi3_sel_third_app_layout, 132);
        sparseIntArray.put(C0899R.C0902layout.bc_item, 133);
        sparseIntArray.put(C0899R.C0902layout.bc_ntg5_item, 134);
        sparseIntArray.put(C0899R.C0902layout.bc_sub_view, 135);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_2021_fragment_one, 136);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_2021_fragment_three, LAYOUT_BENZMBUX2021FRAGMENTTHREE);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_2021_fragment_two, LAYOUT_BENZMBUX2021FRAGMENTTWO);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_2021_item, LAYOUT_BENZMBUX2021ITEM);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_2021_ksw_item, LAYOUT_BENZMBUX2021KSWITEM);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_apps_card, LAYOUT_BENZMBUXAPPSCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_bt_card, LAYOUT_BENZMBUXBTCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_carinfo_card, LAYOUT_BENZMBUXCARINFOCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_dashboard_card, LAYOUT_BENZMBUXDASHBOARDCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_dvr_card, LAYOUT_BENZMBUXDVRCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_item, LAYOUT_BENZMBUXITEM);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_ksw_activity_new, LAYOUT_BENZMBUXKSWACTIVITYNEW);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_music_card, LAYOUT_BENZMBUXMUSICCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_navi_card, LAYOUT_BENZMBUXNAVICARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_set_card, LAYOUT_BENZMBUXSETCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_third_card, LAYOUT_BENZMBUXTHIRDCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_video_card, LAYOUT_BENZMBUXVIDEOCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_weather_card, LAYOUT_BENZMBUXWEATHERCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_mbux_zlink_card, LAYOUT_BENZMBUXZLINKCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg6_fy_fragment_one, LAYOUT_BENZNTG6FYFRAGMENTONE);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2, LAYOUT_BENZNTG6FYFRAGMENTONEV2);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg6_fy_fragment_two, LAYOUT_BENZNTG6FYFRAGMENTTWO);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2, LAYOUT_BENZNTG6FYFRAGMENTTWOV2);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_activity_new, LAYOUT_BENZNTGFYACTIVITYNEW);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_apps_card, LAYOUT_BENZNTGFYAPPSCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_bt_card, LAYOUT_BENZNTGFYBTCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_carinfo_card, LAYOUT_BENZNTGFYCARINFOCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_dashboard_card, LAYOUT_BENZNTGFYDASHBOARDCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_dvr_card, LAYOUT_BENZNTGFYDVRCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_music_card, LAYOUT_BENZNTGFYMUSICCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_navi_card, LAYOUT_BENZNTGFYNAVICARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_set_card, LAYOUT_BENZNTGFYSETCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_third_card, LAYOUT_BENZNTGFYTHIRDCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_video_card, LAYOUT_BENZNTGFYVIDEOCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_weather_card, LAYOUT_BENZNTGFYWEATHERCARD);
        sparseIntArray.put(C0899R.C0902layout.benz_ntg_fy_zlink_card, LAYOUT_BENZNTGFYZLINKCARD);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_dashboard_layout, LAYOUT_BMWID8DASHBOARDLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_dashboard_layout_new, LAYOUT_BMWID8DASHBOARDLAYOUTNEW);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_dashboard_music_layout, LAYOUT_BMWID8DASHBOARDMUSICLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_dashboard_weather_layout, LAYOUT_BMWID8DASHBOARDWEATHERLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_audio_android_layout, LAYOUT_BMWID8SETTINGSAUDIOANDROIDLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_audio_layout, LAYOUT_BMWID8SETTINGSAUDIOLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_audio_oem_layout, LAYOUT_BMWID8SETTINGSAUDIOOEMLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_audio_sound_layout, LAYOUT_BMWID8SETTINGSAUDIOSOUNDLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_factory_layout, LAYOUT_BMWID8SETTINGSFACTORYLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_info_layout, LAYOUT_BMWID8SETTINGSINFOLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_language_layout, LAYOUT_BMWID8SETTINGSLANGUAGELAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_main_layout, LAYOUT_BMWID8SETTINGSMAINLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_navi_layout, LAYOUT_BMWID8SETTINGSNAVILAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_brightness_layout, LAYOUT_BMWID8SETTINGSSYSTEMBRIGHTNESSLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_camera_layout, LAYOUT_BMWID8SETTINGSSYSTEMCAMERALAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_fuel_layout, LAYOUT_BMWID8SETTINGSSYSTEMFUELLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_layout, LAYOUT_BMWID8SETTINGSSYSTEMLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_music_layout, LAYOUT_BMWID8SETTINGSSYSTEMMUSICLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_temp_layout, LAYOUT_BMWID8SETTINGSSYSTEMTEMPLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_system_video_layout, LAYOUT_BMWID8SETTINGSSYSTEMVIDEOLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8_settings_time_layout, LAYOUT_BMWID8SETTINGSTIMELAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_id8gs_settings_main_layout, LAYOUT_BMWID8GSSETTINGSMAINLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.bmw_pemp_id8_settings_main_layout, LAYOUT_BMWPEMPID8SETTINGSMAINLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.fra_benzgs_one, LAYOUT_FRABENZGSONE);
        sparseIntArray.put(C0899R.C0902layout.fra_benzgs_two, LAYOUT_FRABENZGSTWO);
        sparseIntArray.put(C0899R.C0902layout.fra_bmw_evo_id6_gs_one, LAYOUT_FRABMWEVOID6GSONE);
        sparseIntArray.put(C0899R.C0902layout.fra_bmw_evo_id6_gs_three, LAYOUT_FRABMWEVOID6GSTHREE);
        sparseIntArray.put(C0899R.C0902layout.fra_bmw_evo_id6_gs_two, LAYOUT_FRABMWEVOID6GSTWO);
        sparseIntArray.put(C0899R.C0902layout.fragment_als_id7_ui_car, 200);
        sparseIntArray.put(C0899R.C0902layout.fragment_als_id7_ui_media, 201);
        sparseIntArray.put(C0899R.C0902layout.fragment_als_id7_ui_navi, 202);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_fy_one, 203);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_fy_two, 204);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_fy_v2_one, 205);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_fy_v2_two, 206);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_ty_one, 207);
        sparseIntArray.put(C0899R.C0902layout.fragment_audi_mib3_ty_two, 208);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_one, 209);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_three, 210);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_two, LAYOUT_FRAGMENTBENZMBUX2021KSWTWO);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one, LAYOUT_FRAGMENTBENZMBUX2021KSWV2ONE);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three, LAYOUT_FRAGMENTBENZMBUX2021KSWV2THREE);
        sparseIntArray.put(C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_two, LAYOUT_FRAGMENTBENZMBUX2021KSWV2TWO);
        sparseIntArray.put(C0899R.C0902layout.fragment_car_info, LAYOUT_FRAGMENTCARINFO);
        sparseIntArray.put(C0899R.C0902layout.fragment_car_info_gs, LAYOUT_FRAGMENTCARINFOGS);
        sparseIntArray.put(C0899R.C0902layout.fragment_dashboard, LAYOUT_FRAGMENTDASHBOARD);
        sparseIntArray.put(C0899R.C0902layout.fragment_dashboard_edit, LAYOUT_FRAGMENTDASHBOARDEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_dashboard_gs, LAYOUT_FRAGMENTDASHBOARDGS);
        sparseIntArray.put(C0899R.C0902layout.fragment_dashboardgs_edit, 220);
        sparseIntArray.put(C0899R.C0902layout.fragment_gs_dashboardgs_edit, LAYOUT_FRAGMENTGSDASHBOARDGSEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_gs_music_edit, LAYOUT_FRAGMENTGSMUSICEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_gs_phone_edit, LAYOUT_FRAGMENTGSPHONEEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_gs_video_edit, 224);
        sparseIntArray.put(C0899R.C0902layout.fragment_gs_weather_edit, 225);
        sparseIntArray.put(C0899R.C0902layout.fragment_id5_two, 226);
        sparseIntArray.put(C0899R.C0902layout.fragment_modus, 227);
        sparseIntArray.put(C0899R.C0902layout.fragment_modus_gs, 228);
        sparseIntArray.put(C0899R.C0902layout.fragment_music, 229);
        sparseIntArray.put(C0899R.C0902layout.fragment_music_edit, 230);
        sparseIntArray.put(C0899R.C0902layout.fragment_music_gs, 231);
        sparseIntArray.put(C0899R.C0902layout.fragment_navigate, 232);
        sparseIntArray.put(C0899R.C0902layout.fragment_navigate_gs, 233);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_car_info, 234);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_dashboard, 235);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_dashboard_edit, 236);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_modus, 237);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_music, 238);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_music_edit, 239);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_navigate, 240);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_phone, 241);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_phone_edit, 242);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_video, LAYOUT_FRAGMENTPEMPVIDEO);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_video_edit, LAYOUT_FRAGMENTPEMPVIDEOEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_weather, LAYOUT_FRAGMENTPEMPWEATHER);
        sparseIntArray.put(C0899R.C0902layout.fragment_pemp_weather_edit, LAYOUT_FRAGMENTPEMPWEATHEREDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_phone, LAYOUT_FRAGMENTPHONE);
        sparseIntArray.put(C0899R.C0902layout.fragment_phone_edit, LAYOUT_FRAGMENTPHONEEDIT);
        sparseIntArray.put(C0899R.C0902layout.fragment_phone_gs, 249);
        sparseIntArray.put(C0899R.C0902layout.fragment_video, 250);
        sparseIntArray.put(C0899R.C0902layout.fragment_video_edit, 251);
        sparseIntArray.put(C0899R.C0902layout.fragment_video_gs, 252);
        sparseIntArray.put(C0899R.C0902layout.fragment_weather, 253);
        sparseIntArray.put(C0899R.C0902layout.fragment_weather_edit, 254);
        sparseIntArray.put(C0899R.C0902layout.fragment_weather_gs, 255);
        sparseIntArray.put(C0899R.C0902layout.id6_cusp_fragment_four, 256);
        sparseIntArray.put(C0899R.C0902layout.id6_cusp_fragment_one, 257);
        sparseIntArray.put(C0899R.C0902layout.id6_cusp_fragment_three, LAYOUT_ID6CUSPFRAGMENTTHREE);
        sparseIntArray.put(C0899R.C0902layout.id6_cusp_fragment_tow, LAYOUT_ID6CUSPFRAGMENTTOW);
        sparseIntArray.put(C0899R.C0902layout.id6_fragment_four, LAYOUT_ID6FRAGMENTFOUR);
        sparseIntArray.put(C0899R.C0902layout.id6_fragment_one, LAYOUT_ID6FRAGMENTONE);
        sparseIntArray.put(C0899R.C0902layout.id6_fragment_three, LAYOUT_ID6FRAGMENTTHREE);
        sparseIntArray.put(C0899R.C0902layout.id6_fragment_tow, 263);
        sparseIntArray.put(C0899R.C0902layout.id6_one, LAYOUT_ID6ONE);
        sparseIntArray.put(C0899R.C0902layout.id7_app_item, LAYOUT_ID7APPITEM);
        sparseIntArray.put(C0899R.C0902layout.id7_fragment_car, LAYOUT_ID7FRAGMENTCAR);
        sparseIntArray.put(C0899R.C0902layout.id7_fragment_car_hicar, LAYOUT_ID7FRAGMENTCARHICAR);
        sparseIntArray.put(C0899R.C0902layout.id7_fragment_media, LAYOUT_ID7FRAGMENTMEDIA);
        sparseIntArray.put(C0899R.C0902layout.id7_fragment_navi, LAYOUT_ID7FRAGMENTNAVI);
        sparseIntArray.put(C0899R.C0902layout.id7_fragment_navi_hicar, LAYOUT_ID7FRAGMENTNAVIHICAR);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_car_view, LAYOUT_ID7SUBCARVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_dashboard_view, LAYOUT_ID7SUBDASHBOARDVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_hicar_view, 273);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_music_view, LAYOUT_ID7SUBMUSICVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_navi_view, LAYOUT_ID7SUBNAVIVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_phone_view, LAYOUT_ID7SUBPHONEVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_phone_view_hicar, LAYOUT_ID7SUBPHONEVIEWHICAR);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_video_view, LAYOUT_ID7SUBVIDEOVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_sub_weather_view, LAYOUT_ID7SUBWEATHERVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_fragment_car, LAYOUT_ID7V2FRAGMENTCAR);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_fragment_media, LAYOUT_ID7V2FRAGMENTMEDIA);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_fragment_navi, LAYOUT_ID7V2FRAGMENTNAVI);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_sub_dashboard_view, LAYOUT_ID7V2SUBDASHBOARDVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_sub_music_view, LAYOUT_ID7V2SUBMUSICVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_sub_phone_view, LAYOUT_ID7V2SUBPHONEVIEW);
        sparseIntArray.put(C0899R.C0902layout.id7_v2_sub_video_view, LAYOUT_ID7V2SUBVIDEOVIEW);
        sparseIntArray.put(C0899R.C0902layout.id8_gs_launcher_left_bar, LAYOUT_ID8GSLAUNCHERLEFTBAR);
        sparseIntArray.put(C0899R.C0902layout.id8_launcher_left_bar, LAYOUT_ID8LAUNCHERLEFTBAR);
        sparseIntArray.put(C0899R.C0902layout.id8_pemp_launcher_left_bar, LAYOUT_ID8PEMPLAUNCHERLEFTBAR);
        sparseIntArray.put(C0899R.C0902layout.include_near_weather, LAYOUT_INCLUDENEARWEATHER);
        sparseIntArray.put(C0899R.C0902layout.include_near_weather_edit, LAYOUT_INCLUDENEARWEATHEREDIT);
        sparseIntArray.put(C0899R.C0902layout.ksw_id7_main_page1, LAYOUT_KSWID7MAINPAGE1);
        sparseIntArray.put(C0899R.C0902layout.ksw_id7_main_page2, LAYOUT_KSWID7MAINPAGE2);
        sparseIntArray.put(C0899R.C0902layout.kswmbux_home_one, LAYOUT_KSWMBUXHOMEONE);
        sparseIntArray.put(C0899R.C0902layout.kswmbux_home_three, LAYOUT_KSWMBUXHOMETHREE);
        sparseIntArray.put(C0899R.C0902layout.kswmbux_home_two, LAYOUT_KSWMBUXHOMETWO);
        sparseIntArray.put(C0899R.C0902layout.landrover_main, LAYOUT_LANDROVERMAIN);
        sparseIntArray.put(C0899R.C0902layout.landrover_main_bottom_lay, LAYOUT_LANDROVERMAINBOTTOMLAY);
        sparseIntArray.put(C0899R.C0902layout.landrover_main_fragment_one, LAYOUT_LANDROVERMAINFRAGMENTONE);
        sparseIntArray.put(C0899R.C0902layout.landrover_main_fragment_two, 300);
        sparseIntArray.put(C0899R.C0902layout.lexus_ls_bottom_fragment_one, LAYOUT_LEXUSLSBOTTOMFRAGMENTONE);
        sparseIntArray.put(C0899R.C0902layout.lexus_ls_bottom_fragment_two, LAYOUT_LEXUSLSBOTTOMFRAGMENTTWO);
        sparseIntArray.put(C0899R.C0902layout.lexus_ls_bottom_fragment_two_v2, LAYOUT_LEXUSLSBOTTOMFRAGMENTTWOV2);
        sparseIntArray.put(C0899R.C0902layout.lexus_ls_drag_main_layout, LAYOUT_LEXUSLSDRAGMAINLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.lexus_ls_main_layout, LAYOUT_LEXUSLSMAINLAYOUT);
        sparseIntArray.put(C0899R.C0902layout.ntg630_control_popup, LAYOUT_NTG630CONTROLPOPUP);
        sparseIntArray.put(C0899R.C0902layout.ntg6_control_popup, LAYOUT_NTG6CONTROLPOPUP);
        sparseIntArray.put(C0899R.C0902layout.round_app_item, LAYOUT_ROUNDAPPITEM);
        sparseIntArray.put(C0899R.C0902layout.ug_home_four, LAYOUT_UGHOMEFOUR);
        sparseIntArray.put(C0899R.C0902layout.ug_home_four2, LAYOUT_UGHOMEFOUR2);
        sparseIntArray.put(C0899R.C0902layout.ug_home_one, LAYOUT_UGHOMEONE);
        sparseIntArray.put(C0899R.C0902layout.ug_home_one2, LAYOUT_UGHOMEONE2);
        sparseIntArray.put(C0899R.C0902layout.ug_home_three, LAYOUT_UGHOMETHREE);
        sparseIntArray.put(C0899R.C0902layout.ug_home_three2, LAYOUT_UGHOMETHREE2);
        sparseIntArray.put(C0899R.C0902layout.ug_home_two, LAYOUT_UGHOMETWO);
        sparseIntArray.put(C0899R.C0902layout.ug_home_two2, LAYOUT_UGHOMETWO2);
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
                if ("layout-hdpi-1920x720/activity_audi_mib3_0".equals(tag)) {
                    return new ActivityAudiMib3BindingHdpi1920x720Impl(component, view);
                }
                if ("layout/activity_audi_mib3_0".equals(tag)) {
                    return new ActivityAudiMib3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3 is invalid. Received: " + tag);
            case 4:
                if ("layout/activity_audi_mib3_sound_0".equals(tag)) {
                    return new ActivityAudiMib3SoundBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_audi_mib3_sound_0".equals(tag)) {
                    return new ActivityAudiMib3SoundBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound is invalid. Received: " + tag);
            case 5:
                if ("layout/activity_audi_mib3_sound_android_0".equals(tag)) {
                    return new ActivityAudiMib3SoundAndroidBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_audi_mib3_sound_android_0".equals(tag)) {
                    return new ActivityAudiMib3SoundAndroidBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound_android is invalid. Received: " + tag);
            case 6:
                if ("layout/activity_audi_mib3_sound_oem_0".equals(tag)) {
                    return new ActivityAudiMib3SoundOemBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_audi_mib3_sound_oem_0".equals(tag)) {
                    return new ActivityAudiMib3SoundOemBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_sound_oem is invalid. Received: " + tag);
            case 7:
                if ("layout/activity_audi_mib3_time_0".equals(tag)) {
                    return new ActivityAudiMib3TimeBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_audi_mib3_time_0".equals(tag)) {
                    return new ActivityAudiMib3TimeBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_audi_mib3_time is invalid. Received: " + tag);
            case 8:
                if ("layout-sw600dp-land/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_audi_sound_0".equals(tag)) {
                    return new ActivityAudiSoundBindingImpl(component, view);
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
                if ("layout-hdpi-1920x720/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindHdpi1920x720Impl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindImpl(component, view);
                }
                if ("layout-hdpi-1280x720/activity_dash_board_0".equals(tag)) {
                    return new DasoardBindHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board is invalid. Received: " + tag);
            case 12:
                if ("layout-hdpi-1280x720/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindHdpi1280x720Impl(component, view);
                }
                if ("layout/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_dash_board_als_0".equals(tag)) {
                    return new ALSDasoardBindHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_als is invalid. Received: " + tag);
            case 13:
                if ("layout/activity_dash_board_audi_0".equals(tag)) {
                    return new ActivityDashBoardAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi is invalid. Received: " + tag);
            case 14:
                if ("layout-mdpi-1280x480/activity_dash_board_audi_mib3_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3BindingMdpi1280x480Impl(component, view);
                }
                if ("layout-hdpi-1560x700/activity_dash_board_audi_mib3_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3BindingHdpi1560x700Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_dash_board_audi_mib3_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3BindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi_mib3 is invalid. Received: " + tag);
            case 15:
                if ("layout/activity_dash_board_audi_mib3_fy_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3FyBindingImpl(component, view);
                }
                if ("layout-mdpi-1280x480/activity_dash_board_audi_mib3_fy_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3FyBindingMdpi1280x480Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_dash_board_audi_mib3_fy_0".equals(tag)) {
                    return new ActivityDashBoardAudiMib3FyBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_audi_mib3_fy is invalid. Received: " + tag);
            case 16:
                if ("layout/activity_dash_board_lc_0".equals(tag)) {
                    return new ActivityDashBoardLcBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lc is invalid. Received: " + tag);
            case 17:
                if ("layout/activity_dash_board_lexus_0".equals(tag)) {
                    return new ActivityDashBoardLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_lexus is invalid. Received: " + tag);
            case 18:
                if ("layout-sw600dp-land/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_dash_board_seven_0".equals(tag)) {
                    return new SevenDasoardBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_dash_board_seven is invalid. Received: " + tag);
            case 19:
                if ("layout/activity_gs_id8_launcher_main_0".equals(tag)) {
                    return new ActivityGsId8LauncherMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_gs_id8_launcher_main is invalid. Received: " + tag);
            case 20:
                if ("layout-hdpi-1920x720/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1280x720/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-sw600dp-land/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_id7_apps_0".equals(tag)) {
                    return new ActivityId7AppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id7_apps is invalid. Received: " + tag);
            case 21:
                if ("layout/activity_id8_apps_0".equals(tag)) {
                    return new ActivityId8AppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id8_apps is invalid. Received: " + tag);
            case 22:
                if ("layout/activity_id8_gs_modus_layout_0".equals(tag)) {
                    return new BmwId8gsModusLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id8_gs_modus_layout is invalid. Received: " + tag);
            case 23:
                if ("layout/activity_id8_launcher_main_0".equals(tag)) {
                    return new ActivityId8LauncherMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id8_launcher_main is invalid. Received: " + tag);
            case 24:
                if ("layout/activity_id8_modus_layout_0".equals(tag)) {
                    return new BmwId8ModusLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id8_modus_layout is invalid. Received: " + tag);
            case 25:
                if ("layout/activity_id8_pemp_modus_layout_0".equals(tag)) {
                    return new BmwId8PempModusLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_id8_pemp_modus_layout is invalid. Received: " + tag);
            case 26:
                if ("layout/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBindingImpl(component, view);
                }
                if ("layout-1280x660/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBinding1280x660Impl(component, view);
                }
                if ("layout-1920x660/activity_land_rover_settings_0".equals(tag)) {
                    return new ActivityLandRoverSettingsBinding1920x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_land_rover_settings is invalid. Received: " + tag);
            case 27:
                if ("layout/activity_lexus_oem_fm_0".equals(tag)) {
                    return new ActivityLexusOemFmBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_lexus_oem_fm is invalid. Received: " + tag);
            case 28:
                if ("layout/activity_main_als_id6_0".equals(tag)) {
                    return new ActivityMainAlsId6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id6 is invalid. Received: " + tag);
            case 29:
                if ("layout-sw600dp-land/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_als_id7_0".equals(tag)) {
                    return new ActivityMainAlsId7BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id7 is invalid. Received: " + tag);
            case 30:
                if ("layout/activity_main_als_id7_ui_0".equals(tag)) {
                    return new AlsId7UiMainBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_main_als_id7_ui_0".equals(tag)) {
                    return new AlsId7UiMainBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id7_ui is invalid. Received: " + tag);
            case 31:
                if ("layout/activity_main_als_id7_v2_0".equals(tag)) {
                    return new ActivityMainAlsId7V2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_als_id7_v2 is invalid. Received: " + tag);
            case 32:
                if ("layout/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_audi_0".equals(tag)) {
                    return new ActivityMainAudiBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_audi is invalid. Received: " + tag);
            case 33:
                if ("layout-hdpi-1920x720/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingImpl(component, view);
                }
                if ("layout-hdpi-1280x720/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_bc_0".equals(tag)) {
                    return new ActivityMainBcBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bc is invalid. Received: " + tag);
            case 34:
                if ("layout/activity_main_benz_gs_0".equals(tag)) {
                    return new ActivityMainBenzGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_gs is invalid. Received: " + tag);
            case 35:
                if ("layout/activity_main_benz_mbux_0".equals(tag)) {
                    return new ActivityMainBenzMbuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux is invalid. Received: " + tag);
            case 36:
                if ("layout/activity_main_benz_mbux_2021_0".equals(tag)) {
                    return new BenzMbux2021ActivityBindingImpl(component, view);
                }
                if ("layout-hdpi-1280x720/activity_main_benz_mbux_2021_0".equals(tag)) {
                    return new BenzMbux2021ActivityBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_main_benz_mbux_2021_0".equals(tag)) {
                    return new BenzMbux2021ActivityBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021 is invalid. Received: " + tag);
            case 37:
                if ("layout/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding2Impl(component, view);
                }
                if ("layout-hdpi-1280x720/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding2Hdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding2Hdpi1920x720Impl(component, view);
                }
                if ("layout-1024x600/activity_main_benz_mbux_2021_2_0".equals(tag)) {
                    return new BenzMbux2021ActivityBinding21024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021_2 is invalid. Received: " + tag);
            case 38:
                if ("layout/activity_main_benz_mbux_2021_ksw_0".equals(tag)) {
                    return new BenzMbux2021KswActivityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021_ksw is invalid. Received: " + tag);
            case 39:
                if ("layout/activity_main_benz_mbux_2021_ksw_v2_0".equals(tag)) {
                    return new BenzMbux2021KswV2ActivityBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_mbux_2021_ksw_v2 is invalid. Received: " + tag);
            case 40:
                if ("layout/activity_main_benz_ntg5_0".equals(tag)) {
                    return new ActivityMainBenzNtg5BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg5 is invalid. Received: " + tag);
            case 41:
                if ("layout-hdpi-1280x720/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindClsHdpi1280x720Impl(component, view);
                }
                if ("layout/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindClsImpl(component, view);
                }
                if ("layout-1024x600/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindCls1024x600Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_main_benz_ntg6_fy_0".equals(tag)) {
                    return new BenzNtg6FyBindClsHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_benz_ntg6_fy is invalid. Received: " + tag);
            case 42:
                if ("layout/activity_main_bmw_0".equals(tag)) {
                    return new MainActivityImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_main_bmw_0".equals(tag)) {
                    return new MainActivitySw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_bmw is invalid. Received: " + tag);
            case 43:
                if ("layout-hdpi-1920x720/activity_main_gsug_0".equals(tag)) {
                    return new ActivityMainGsugBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/activity_main_gsug_0".equals(tag)) {
                    return new ActivityMainGsugBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug is invalid. Received: " + tag);
            case 44:
                if ("layout/activity_main_gsug2_0".equals(tag)) {
                    return new ActivityMainGsug2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_gsug2 is invalid. Received: " + tag);
            case 45:
                if ("layout-sw600dp-land/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindSw600dpLandImpl(component, view);
                }
                if ("layout/activity_main_id5_0".equals(tag)) {
                    return new ID5MaindBindImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id5 is invalid. Received: " + tag);
            case 46:
                if ("layout/activity_main_id6_gs_0".equals(tag)) {
                    return new ActivityMainId6GsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_id6_gs is invalid. Received: " + tag);
            case 47:
                if ("layout/activity_main_ksw_id7_0".equals(tag)) {
                    return new MainKswID7BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_ksw_id7 is invalid. Received: " + tag);
            case 48:
                if ("layout/activity_main_kswmbux_0".equals(tag)) {
                    return new ActivityMainKswmbuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_kswmbux is invalid. Received: " + tag);
            case 49:
                if ("layout/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBindingImpl(component, view);
                }
                if ("layout-1280x660/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBinding1280x660Impl(component, view);
                }
                if ("layout-1920x660/activity_main_land_rover_0".equals(tag)) {
                    return new ActivityMainLandRoverBinding1920x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_land_rover is invalid. Received: " + tag);
            case 50:
                if ("layout/activity_main_lexus_0".equals(tag)) {
                    return new ActivityMainLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 51:
                if ("layout/activity_main_lexus_page1_0".equals(tag)) {
                    return new ActivityMainLexusPage1BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page1 is invalid. Received: " + tag);
            case 52:
                if ("layout/activity_main_lexus_page2_0".equals(tag)) {
                    return new ActivityMainLexusPage2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page2 is invalid. Received: " + tag);
            case 53:
                if ("layout/activity_main_lexus_page3_0".equals(tag)) {
                    return new ActivityMainLexusPage3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_main_lexus_page3 is invalid. Received: " + tag);
            case 54:
                if ("layout-hdpi-1280x720/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/activity_ntg6_dash_board_0".equals(tag)) {
                    return new ActivityNtg6DashBoardBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_ntg6_dash_board is invalid. Received: " + tag);
            case 55:
                if ("layout/activity_pemp_id8_launcher_main_0".equals(tag)) {
                    return new ActivityPempId8LauncherMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_pemp_id8_launcher_main is invalid. Received: " + tag);
            case 56:
                if ("layout/activity_romeo_0".equals(tag)) {
                    return new ActivityRomeoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_romeo is invalid. Received: " + tag);
            case 57:
                if ("layout/activity_romeo_v2_0".equals(tag)) {
                    return new ActivityRomeoV2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_romeo_v2 is invalid. Received: " + tag);
            case 58:
                if ("layout/activity_round_apps_0".equals(tag)) {
                    return new ActivityRoundAppsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for activity_round_apps is invalid. Received: " + tag);
            case 59:
                if ("layout-sw600dp-land/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_dash_video_0".equals(tag)) {
                    return new DashVideoFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_dash_video is invalid. Received: " + tag);
            case 60:
                if ("layout/als_id7_fragment_dash_video_v2_0".equals(tag)) {
                    return new DashVideoFragment_V2Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_dash_video_v2 is invalid. Received: " + tag);
            case 61:
                if ("layout-sw600dp-land/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_music_0".equals(tag)) {
                    return new MusicPhoneFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_music is invalid. Received: " + tag);
            case 62:
                if ("layout-sw600dp-land/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_fragment_navi_car_0".equals(tag)) {
                    return new NaviCarFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_navi_car is invalid. Received: " + tag);
            case 63:
                if ("layout/als_id7_fragment_zlink_weather_0".equals(tag)) {
                    return new ZlinkWeatherFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_fragment_zlink_weather is invalid. Received: " + tag);
            case 64:
                if ("layout-sw600dp-land/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_car_view_0".equals(tag)) {
                    return new AlsId7SubCarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_car_view is invalid. Received: " + tag);
            case 65:
                if ("layout/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_dashboard_view is invalid. Received: " + tag);
            case 66:
                if ("layout/als_id7_sub_music_third_layout_0".equals(tag)) {
                    return new AlsId7SubMusicThirdLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_music_third_layout is invalid. Received: " + tag);
            case 67:
                if ("layout/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_music_view_0".equals(tag)) {
                    return new AlsId7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_music_view is invalid. Received: " + tag);
            case 68:
                if ("layout-sw600dp-land/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/als_id7_sub_navi_view_0".equals(tag)) {
                    return new AlsId7SubNaviViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_navi_view is invalid. Received: " + tag);
            case 69:
                if ("layout/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_phone_view_0".equals(tag)) {
                    return new AlsId7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_phone_view is invalid. Received: " + tag);
            case 70:
                if ("layout/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/als_id7_sub_video_view_0".equals(tag)) {
                    return new AlsId7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_video_view is invalid. Received: " + tag);
            case 71:
                if ("layout/als_id7_sub_video_view_v2_0".equals(tag)) {
                    return new AlsId7SubVideoViewV2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_video_view_v2 is invalid. Received: " + tag);
            case 72:
                if ("layout/als_id7_sub_weather_view_0".equals(tag)) {
                    return new AlsId7SubWeatherViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_weather_view is invalid. Received: " + tag);
            case 73:
                if ("layout/als_id7_sub_zlink_view_0".equals(tag)) {
                    return new AlsId7SubZlinkViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_sub_zlink_view is invalid. Received: " + tag);
            case 74:
                if ("layout-hdpi-1920x720/als_id7_ui_sub_car_view_0".equals(tag)) {
                    return new AlsId7UiCarInfoSubViewBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/als_id7_ui_sub_car_view_0".equals(tag)) {
                    return new AlsId7UiCarInfoSubViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_car_view is invalid. Received: " + tag);
            case 75:
                if ("layout-hdpi-1920x720/als_id7_ui_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7UiDashBoardSubViewBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/als_id7_ui_sub_dashboard_view_0".equals(tag)) {
                    return new AlsId7UiDashBoardSubViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_dashboard_view is invalid. Received: " + tag);
            case 76:
                if ("layout/als_id7_ui_sub_music_view_0".equals(tag)) {
                    return new AlsId7UiMusicSubViewBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/als_id7_ui_sub_music_view_0".equals(tag)) {
                    return new AlsId7UiMusicSubViewBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_music_view is invalid. Received: " + tag);
            case 77:
                if ("layout/als_id7_ui_sub_navi_view_0".equals(tag)) {
                    return new AlsId7UiSubNaviViewBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/als_id7_ui_sub_navi_view_0".equals(tag)) {
                    return new AlsId7UiSubNaviViewBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_navi_view is invalid. Received: " + tag);
            case 78:
                if ("layout-hdpi-1920x720/als_id7_ui_sub_phone_view_0".equals(tag)) {
                    return new AlsId7UiSubPhoneViewBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/als_id7_ui_sub_phone_view_0".equals(tag)) {
                    return new AlsId7UiSubPhoneViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_phone_view is invalid. Received: " + tag);
            case 79:
                if ("layout/als_id7_ui_sub_video_view_0".equals(tag)) {
                    return new AlsId7UiVideoSubViewBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/als_id7_ui_sub_video_view_0".equals(tag)) {
                    return new AlsId7UiVideoSubViewBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for als_id7_ui_sub_video_view is invalid. Received: " + tag);
            case 80:
                if ("layout/app_third_item_als_id7_ui_0".equals(tag)) {
                    return new AppThirdItemAlsId7UiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_als_id7_ui is invalid. Received: " + tag);
            case 81:
                if ("layout/app_third_item_audi_0".equals(tag)) {
                    return new AppThirdItemAudiBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_audi is invalid. Received: " + tag);
            case 82:
                if ("layout/app_third_item_audimbi3_0".equals(tag)) {
                    return new AppThirdItemAudimbi3BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_audimbi3 is invalid. Received: " + tag);
            case 83:
                if ("layout-hdpi-1920x720/app_third_item_audimbi3_1_0".equals(tag)) {
                    return new AppThirdItemAudimbi31BindingHdpi1920x720Impl(component, view);
                }
                if ("layout/app_third_item_audimbi3_1_0".equals(tag)) {
                    return new AppThirdItemAudimbi31BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_audimbi3_1 is invalid. Received: " + tag);
            case 84:
                if ("layout/app_third_item_benz_ntg6_0".equals(tag)) {
                    return new AppThirdItemBenzNtg6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_benz_ntg6 is invalid. Received: " + tag);
            case 85:
                if ("layout/app_third_item_evo_id7_0".equals(tag)) {
                    return new AppThirdItemEvoId7BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_evo_id7 is invalid. Received: " + tag);
            case 86:
                if ("layout/app_third_item_id6_0".equals(tag)) {
                    return new AppThirdItemId6BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_id6 is invalid. Received: " + tag);
            case 87:
                if ("layout/app_third_item_id7_0".equals(tag)) {
                    return new AppThirdItemId7BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_id7 is invalid. Received: " + tag);
            case 88:
                if ("layout/app_third_item_landrover_0".equals(tag)) {
                    return new AppThirdItemLandroverBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_landrover is invalid. Received: " + tag);
            case 89:
                if ("layout/app_third_item_lexus_0".equals(tag)) {
                    return new AppThirdItemLexusBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_lexus is invalid. Received: " + tag);
            case 90:
                if ("layout/app_third_item_romeo_0".equals(tag)) {
                    return new AppThirdItemRomeoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for app_third_item_romeo is invalid. Received: " + tag);
            case 91:
                if ("layout-sw600dp-land/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_aux_0".equals(tag)) {
                    return new AudiAuxBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_aux is invalid. Received: " + tag);
            case 92:
                if ("layout/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_brightness_0".equals(tag)) {
                    return new AudiBrightnessBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_brightness is invalid. Received: " + tag);
            case 93:
                if ("layout-sw600dp-land/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_eq_view_0".equals(tag)) {
                    return new AudiEqViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_eq_view is invalid. Received: " + tag);
            case 94:
                if ("layout/audi_mib3_aux_0".equals(tag)) {
                    return new AudiMib3AuxBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_aux_0".equals(tag)) {
                    return new AudiMib3AuxBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_aux is invalid. Received: " + tag);
            case 95:
                if ("layout/audi_mib3_brightness_0".equals(tag)) {
                    return new AudiMib3BrightnessBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_brightness_0".equals(tag)) {
                    return new AudiMib3BrightnessBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_brightness is invalid. Received: " + tag);
            case 96:
                if ("layout/audi_mib3_eq_custom_view_0".equals(tag)) {
                    return new AudiMib3EqCustomViewBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_eq_custom_view_0".equals(tag)) {
                    return new AudiMib3EqCustomViewBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_eq_custom_view is invalid. Received: " + tag);
            case 97:
                if ("layout-hdpi-1920x720/audi_mib3_eq_view_0".equals(tag)) {
                    return new AudiMib3EqViewBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_eq_view_0".equals(tag)) {
                    return new AudiMib3EqViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_eq_view is invalid. Received: " + tag);
            case 98:
                if ("layout-hdpi-1920x720/audi_mib3_fragment_one_0".equals(tag)) {
                    return new AudiMib3OneDataClsHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1560x700/audi_mib3_fragment_one_0".equals(tag)) {
                    return new AudiMib3OneDataClsHdpi1560x700Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fragment_one_0".equals(tag)) {
                    return new AudiMib3OneDataClsMdpi1280x480Impl(component, view);
                }
                if ("layout/audi_mib3_fragment_one_0".equals(tag)) {
                    return new AudiMib3OneDataClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_one is invalid. Received: " + tag);
            case 99:
                if ("layout/audi_mib3_fragment_one_v2_0".equals(tag)) {
                    return new AudiMib3OneDataClsV2Impl(component, view);
                }
                if ("layout-hdpi-1560x700/audi_mib3_fragment_one_v2_0".equals(tag)) {
                    return new AudiMib3OneDataClsV2Hdpi1560x700Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fragment_one_v2_0".equals(tag)) {
                    return new AudiMib3OneDataClsV2Mdpi1280x480Impl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_fragment_one_v2_0".equals(tag)) {
                    return new AudiMib3OneDataClsV2Hdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_one_v2 is invalid. Received: " + tag);
            case 100:
                if ("layout-hdpi-1560x700/audi_mib3_fragment_two_0".equals(tag)) {
                    return new AudiMib3TwoDataClsHdpi1560x700Impl(component, view);
                }
                if ("layout/audi_mib3_fragment_two_0".equals(tag)) {
                    return new AudiMib3TwoDataClsImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_fragment_two_0".equals(tag)) {
                    return new AudiMib3TwoDataClsHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fragment_two_0".equals(tag)) {
                    return new AudiMib3TwoDataClsMdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_two is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding2(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 101:
                if ("layout-hdpi-1560x700/audi_mib3_fragment_two_v2_0".equals(tag)) {
                    return new AudiMib3TwoDataClsV2Hdpi1560x700Impl(component, view);
                }
                if ("layout/audi_mib3_fragment_two_v2_0".equals(tag)) {
                    return new AudiMib3TwoDataClsV2Impl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_fragment_two_v2_0".equals(tag)) {
                    return new AudiMib3TwoDataClsV2Hdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fragment_two_v2_0".equals(tag)) {
                    return new AudiMib3TwoDataClsV2Mdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fragment_two_v2 is invalid. Received: " + tag);
            case 102:
                if ("layout-hdpi-1920x720/audi_mib3_fy_main_layout_0".equals(tag)) {
                    return new AudiMib3FyMainLayoutBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fy_main_layout_0".equals(tag)) {
                    return new AudiMib3FyMainLayoutBindingMdpi1280x480Impl(component, view);
                }
                if ("layout/audi_mib3_fy_main_layout_0".equals(tag)) {
                    return new AudiMib3FyMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fy_main_layout is invalid. Received: " + tag);
            case 103:
                if ("layout-hdpi-1920x720/audi_mib3_fy_main_left_0".equals(tag)) {
                    return new AudiMib3FyMainLeftBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_fy_main_left_0".equals(tag)) {
                    return new AudiMib3FyMainLeftBindingImpl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_fy_main_left_0".equals(tag)) {
                    return new AudiMib3FyMainLeftBindingMdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_fy_main_left is invalid. Received: " + tag);
            case 104:
                if ("layout/audi_mib3_main_layout_0".equals(tag)) {
                    return new AudiMib3MainLayoutBindingImpl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_main_layout_0".equals(tag)) {
                    return new AudiMib3MainLayoutBindingMdpi1280x480Impl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_main_layout_0".equals(tag)) {
                    return new AudiMib3MainLayoutBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_main_layout is invalid. Received: " + tag);
            case 105:
                if ("layout-mdpi-1280x480/audi_mib3_main_left_0".equals(tag)) {
                    return new AudiMib3MainLeftBindingMdpi1280x480Impl(component, view);
                }
                if ("layout/audi_mib3_main_left_0".equals(tag)) {
                    return new AudiMib3MainLeftBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_main_left_0".equals(tag)) {
                    return new AudiMib3MainLeftBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_main_left is invalid. Received: " + tag);
            case 106:
                if ("layout-hdpi-1920x720/audi_mib3_navi_0".equals(tag)) {
                    return new AudiMib3NaviBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_navi_0".equals(tag)) {
                    return new AudiMib3NaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_navi is invalid. Received: " + tag);
            case 107:
                if ("layout/audi_mib3_password_0".equals(tag)) {
                    return new AudiMib3PasswordBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_password_0".equals(tag)) {
                    return new AudiMib3PasswordBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_password is invalid. Received: " + tag);
            case 108:
                if ("layout/audi_mib3_rever_camera_0".equals(tag)) {
                    return new AudiMib3ReverCameraBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_rever_camera_0".equals(tag)) {
                    return new AudiMib3ReverCameraBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_rever_camera is invalid. Received: " + tag);
            case 109:
                if ("layout/audi_mib3_right_carinfo_0".equals(tag)) {
                    return new AudiMib3RightCarinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_carinfo is invalid. Received: " + tag);
            case 110:
                if ("layout/audi_mib3_right_logo_0".equals(tag)) {
                    return new AudiMib3RightLogoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_logo is invalid. Received: " + tag);
            case 111:
                if ("layout/audi_mib3_right_media_0".equals(tag)) {
                    return new AudiMib3RightMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_media is invalid. Received: " + tag);
            case 112:
                if ("layout/audi_mib3_right_navi_0".equals(tag)) {
                    return new AudiMib3RightNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_right_navi is invalid. Received: " + tag);
            case 113:
                if ("layout/audi_mib3_speed_unit_0".equals(tag)) {
                    return new AudiMib3SpeedUnitBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_speed_unit_0".equals(tag)) {
                    return new AudiMib3SpeedUnitBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_speed_unit is invalid. Received: " + tag);
            case 114:
                if ("layout-hdpi-1920x720/audi_mib3_sysinfo_0".equals(tag)) {
                    return new AudiMib3SysinfoBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_sysinfo_0".equals(tag)) {
                    return new AudiMib3SysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_sysinfo is invalid. Received: " + tag);
            case 115:
                if ("layout-hdpi-1920x720/audi_mib3_system_set_0".equals(tag)) {
                    return new AudiMib3SystemSetBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_system_set_0".equals(tag)) {
                    return new AudiMib3SystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_system_set is invalid. Received: " + tag);
            case 116:
                if ("layout/audi_mib3_temp_0".equals(tag)) {
                    return new AudiMib3TempBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/audi_mib3_temp_0".equals(tag)) {
                    return new AudiMib3TempBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_temp is invalid. Received: " + tag);
            case 117:
                if ("layout-hdpi-1920x720/audi_mib3_ty_main_layout_0".equals(tag)) {
                    return new AudiMib3TyMainLayoutBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/audi_mib3_ty_main_layout_0".equals(tag)) {
                    return new AudiMib3TyMainLayoutBindingImpl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_ty_main_layout_0".equals(tag)) {
                    return new AudiMib3TyMainLayoutBindingMdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_ty_main_layout is invalid. Received: " + tag);
            case 118:
                if ("layout-hdpi-1920x720/audi_mib3_ty_main_left_0".equals(tag)) {
                    return new AudiMib3TyMainLeftBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/audi_mib3_ty_main_left_0".equals(tag)) {
                    return new AudiMib3TyMainLeftBindingMdpi1280x480Impl(component, view);
                }
                if ("layout/audi_mib3_ty_main_left_0".equals(tag)) {
                    return new AudiMib3TyMainLeftBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_mib3_ty_main_left is invalid. Received: " + tag);
            case 119:
                if ("layout-sw600dp-land/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_navi_0".equals(tag)) {
                    return new AudiNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_navi is invalid. Received: " + tag);
            case 120:
                if ("layout-sw600dp-land/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_password_0".equals(tag)) {
                    return new AudiPasswordBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_password is invalid. Received: " + tag);
            case 121:
                if ("layout/audi_rever_camera_0".equals(tag)) {
                    return new AudiReverCameraBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_rever_camera is invalid. Received: " + tag);
            case 122:
                if ("layout-sw600dp-land/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_carinfo_0".equals(tag)) {
                    return new AudiRightCarinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_carinfo is invalid. Received: " + tag);
            case 123:
                if ("layout/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_logo_0".equals(tag)) {
                    return new AudiRightLogoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_logo is invalid. Received: " + tag);
            case 124:
                if ("layout-sw600dp-land/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_media_0".equals(tag)) {
                    return new AudiRightMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_media is invalid. Received: " + tag);
            case 125:
                if ("layout/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/audi_right_navi_0".equals(tag)) {
                    return new AudiRightNaviBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_navi is invalid. Received: " + tag);
            case 126:
                if ("layout-sw600dp-land/audi_right_weather_0".equals(tag)) {
                    return new AudiRightWeatherBindingSw600dpLandImpl(component, view);
                }
                if ("layout/audi_right_weather_0".equals(tag)) {
                    return new AudiRightWeatherBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_right_weather is invalid. Received: " + tag);
            case 127:
                if ("layout/audi_sel_third_app_layout_0".equals(tag)) {
                    return new AudiSelThirdClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_sel_third_app_layout is invalid. Received: " + tag);
            case 128:
                if ("layout/audi_speed_unit_0".equals(tag)) {
                    return new AudiSpeedUnitBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_speed_unit is invalid. Received: " + tag);
            case 129:
                if ("layout/audi_sysinfo_0".equals(tag)) {
                    return new AudiSysinfoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_sysinfo is invalid. Received: " + tag);
            case 130:
                if ("layout/audi_system_set_0".equals(tag)) {
                    return new AudiSystemSetBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_system_set is invalid. Received: " + tag);
            case 131:
                if ("layout/audi_temp_0".equals(tag)) {
                    return new AudiTempBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audi_temp is invalid. Received: " + tag);
            case 132:
                if ("layout-hdpi-1920x720/audimbi3_sel_third_app_layout_0".equals(tag)) {
                    return new AudiMbi3SelThirdClsHdpi1920x720Impl(component, view);
                }
                if ("layout/audimbi3_sel_third_app_layout_0".equals(tag)) {
                    return new AudiMbi3SelThirdClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for audimbi3_sel_third_app_layout is invalid. Received: " + tag);
            case 133:
                if ("layout/bc_item_0".equals(tag)) {
                    return new BcItemBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/bc_item_0".equals(tag)) {
                    return new BcItemBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1280x720/bc_item_0".equals(tag)) {
                    return new BcItemBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-sw600dp-land/bc_item_0".equals(tag)) {
                    return new BcItemBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_item is invalid. Received: " + tag);
            case 134:
                if ("layout/bc_ntg5_item_0".equals(tag)) {
                    return new BcNtg5ItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_ntg5_item is invalid. Received: " + tag);
            case 135:
                if ("layout-sw600dp-land/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/bc_sub_view_0".equals(tag)) {
                    return new BcSubViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bc_sub_view is invalid. Received: " + tag);
            case 136:
                if ("layout/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOneImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOneHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOneHdpi1280x720Impl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_fragment_one_0".equals(tag)) {
                    return new Benz2021FragmentOne1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_one is invalid. Received: " + tag);
            case LAYOUT_BENZMBUX2021FRAGMENTTHREE /* 137 */:
                if ("layout/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThreeImpl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThreeHdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThreeHdpi1920x720Impl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_fragment_three_0".equals(tag)) {
                    return new Benz2021FragmentThree1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_three is invalid. Received: " + tag);
            case LAYOUT_BENZMBUX2021FRAGMENTTWO /* 138 */:
                if ("layout-hdpi-1920x720/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwoHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwoImpl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwo1024x600Impl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_mbux_2021_fragment_two_0".equals(tag)) {
                    return new Benz2021FragmentTwoHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_fragment_two is invalid. Received: " + tag);
            case LAYOUT_BENZMBUX2021ITEM /* 139 */:
                if ("layout/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBindingImpl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-1024x600/benz_mbux_2021_item_0".equals(tag)) {
                    return new BenzMbux2021ItemBinding1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_item is invalid. Received: " + tag);
            case LAYOUT_BENZMBUX2021KSWITEM /* 140 */:
                if ("layout/benz_mbux_2021_ksw_item_0".equals(tag)) {
                    return new BenzMbux2021KswItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_2021_ksw_item is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXAPPSCARD /* 141 */:
                if ("layout/benz_mbux_apps_card_0".equals(tag)) {
                    return new BenzMbuxAppsCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_apps_card_0".equals(tag)) {
                    return new BenzMbuxAppsCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_apps_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXBTCARD /* 142 */:
                if ("layout-hdpi-1920x720/benz_mbux_bt_card_0".equals(tag)) {
                    return new BenzMbuxBtCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_bt_card_0".equals(tag)) {
                    return new BenzMbuxBtCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_bt_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXCARINFOCARD /* 143 */:
                if ("layout/benz_mbux_carinfo_card_0".equals(tag)) {
                    return new BenzMbuxCarinfoCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_carinfo_card_0".equals(tag)) {
                    return new BenzMbuxCarinfoCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_carinfo_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXDASHBOARDCARD /* 144 */:
                if ("layout-hdpi-1920x720/benz_mbux_dashboard_card_0".equals(tag)) {
                    return new BenzMbuxDashboardCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_dashboard_card_0".equals(tag)) {
                    return new BenzMbuxDashboardCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_dashboard_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXDVRCARD /* 145 */:
                if ("layout/benz_mbux_dvr_card_0".equals(tag)) {
                    return new BenzMbuxDvrCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_dvr_card_0".equals(tag)) {
                    return new BenzMbuxDvrCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_dvr_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXITEM /* 146 */:
                if ("layout/benz_mbux_item_0".equals(tag)) {
                    return new BenzMbuxItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_item is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXKSWACTIVITYNEW /* 147 */:
                if ("layout-hdpi-1920x720/benz_mbux_ksw_activity_new_0".equals(tag)) {
                    return new BenzMbuxKswActivityNewBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_ksw_activity_new_0".equals(tag)) {
                    return new BenzMbuxKswActivityNewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_ksw_activity_new is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXMUSICCARD /* 148 */:
                if ("layout-hdpi-1920x720/benz_mbux_music_card_0".equals(tag)) {
                    return new BenzMbuxMusicCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_music_card_0".equals(tag)) {
                    return new BenzMbuxMusicCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_music_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXNAVICARD /* 149 */:
                if ("layout-hdpi-1920x720/benz_mbux_navi_card_0".equals(tag)) {
                    return new BenzMbuxNaviCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_navi_card_0".equals(tag)) {
                    return new BenzMbuxNaviCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_navi_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXSETCARD /* 150 */:
                if ("layout/benz_mbux_set_card_0".equals(tag)) {
                    return new BenzMbuxSetCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_set_card_0".equals(tag)) {
                    return new BenzMbuxSetCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_set_card is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding3(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case LAYOUT_BENZMBUXTHIRDCARD /* 151 */:
                if ("layout/benz_mbux_third_card_0".equals(tag)) {
                    return new BenzMbuxThirdCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_mbux_third_card_0".equals(tag)) {
                    return new BenzMbuxThirdCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_third_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXVIDEOCARD /* 152 */:
                if ("layout-hdpi-1920x720/benz_mbux_video_card_0".equals(tag)) {
                    return new BenzMbuxVideoCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_video_card_0".equals(tag)) {
                    return new BenzMbuxVideoCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_video_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXWEATHERCARD /* 153 */:
                if ("layout-hdpi-1920x720/benz_mbux_weather_card_0".equals(tag)) {
                    return new BenzMbuxWeatherCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_weather_card_0".equals(tag)) {
                    return new BenzMbuxWeatherCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_weather_card is invalid. Received: " + tag);
            case LAYOUT_BENZMBUXZLINKCARD /* 154 */:
                if ("layout-hdpi-1920x720/benz_mbux_zlink_card_0".equals(tag)) {
                    return new BenzMbuxZlinkCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_mbux_zlink_card_0".equals(tag)) {
                    return new BenzMbuxZlinkCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_mbux_zlink_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTG6FYFRAGMENTONE /* 155 */:
                if ("layout-1024x600/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneCls1024x600Impl(component, view);
                }
                if ("layout/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_ntg6_fy_fragment_one_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_one is invalid. Received: " + tag);
            case LAYOUT_BENZNTG6FYFRAGMENTONEV2 /* 156 */:
                if ("layout-hdpi-1280x720/benz_ntg6_fy_fragment_one_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsV2Hdpi1280x720Impl(component, view);
                }
                if ("layout/benz_ntg6_fy_fragment_one_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsV2Impl(component, view);
                }
                if ("layout-1024x600/benz_ntg6_fy_fragment_one_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsV21024x600Impl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg6_fy_fragment_one_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentOneClsV2Hdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_one_v2 is invalid. Received: " + tag);
            case LAYOUT_BENZNTG6FYFRAGMENTTWO /* 157 */:
                if ("layout-hdpi-1280x720/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsHdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsImpl(component, view);
                }
                if ("layout-1024x600/benz_ntg6_fy_fragment_two_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoCls1024x600Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_two is invalid. Received: " + tag);
            case LAYOUT_BENZNTG6FYFRAGMENTTWOV2 /* 158 */:
                if ("layout/benz_ntg6_fy_fragment_two_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsV2Impl(component, view);
                }
                if ("layout-1024x600/benz_ntg6_fy_fragment_two_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsV21024x600Impl(component, view);
                }
                if ("layout-hdpi-1280x720/benz_ntg6_fy_fragment_two_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsV2Hdpi1280x720Impl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg6_fy_fragment_two_v2_0".equals(tag)) {
                    return new BenzNtg6FyFragmentTwoClsV2Hdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg6_fy_fragment_two_v2 is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYACTIVITYNEW /* 159 */:
                if ("layout/benz_ntg_fy_activity_new_0".equals(tag)) {
                    return new BenzNtgFyActivityNewBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_activity_new_0".equals(tag)) {
                    return new BenzNtgFyActivityNewBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_activity_new is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYAPPSCARD /* 160 */:
                if ("layout/benz_ntg_fy_apps_card_0".equals(tag)) {
                    return new BenzNtgFyAppsCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_apps_card_0".equals(tag)) {
                    return new BenzNtgFyAppsCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_apps_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYBTCARD /* 161 */:
                if ("layout/benz_ntg_fy_bt_card_0".equals(tag)) {
                    return new BenzNtgFyBtCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_bt_card_0".equals(tag)) {
                    return new BenzNtgFyBtCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_bt_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYCARINFOCARD /* 162 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_carinfo_card_0".equals(tag)) {
                    return new BenzNtgFyCarinfoCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_carinfo_card_0".equals(tag)) {
                    return new BenzNtgFyCarinfoCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_carinfo_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYDASHBOARDCARD /* 163 */:
                if ("layout/benz_ntg_fy_dashboard_card_0".equals(tag)) {
                    return new BenzNtgFyDashboardCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_dashboard_card_0".equals(tag)) {
                    return new BenzNtgFyDashboardCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_dashboard_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYDVRCARD /* 164 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_dvr_card_0".equals(tag)) {
                    return new BenzNtgFyDvrCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_dvr_card_0".equals(tag)) {
                    return new BenzNtgFyDvrCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_dvr_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYMUSICCARD /* 165 */:
                if ("layout/benz_ntg_fy_music_card_0".equals(tag)) {
                    return new BenzNtgFyMusicCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_music_card_0".equals(tag)) {
                    return new BenzNtgFyMusicCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_music_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYNAVICARD /* 166 */:
                if ("layout/benz_ntg_fy_navi_card_0".equals(tag)) {
                    return new BenzNtgFyNaviCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_navi_card_0".equals(tag)) {
                    return new BenzNtgFyNaviCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_navi_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYSETCARD /* 167 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_set_card_0".equals(tag)) {
                    return new BenzNtgFySetCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_set_card_0".equals(tag)) {
                    return new BenzNtgFySetCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_set_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYTHIRDCARD /* 168 */:
                if ("layout/benz_ntg_fy_third_card_0".equals(tag)) {
                    return new BenzNtgFyThirdCardBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/benz_ntg_fy_third_card_0".equals(tag)) {
                    return new BenzNtgFyThirdCardBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_third_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYVIDEOCARD /* 169 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_video_card_0".equals(tag)) {
                    return new BenzNtgFyVideoCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_video_card_0".equals(tag)) {
                    return new BenzNtgFyVideoCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_video_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYWEATHERCARD /* 170 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_weather_card_0".equals(tag)) {
                    return new BenzNtgFyWeatherCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_weather_card_0".equals(tag)) {
                    return new BenzNtgFyWeatherCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_weather_card is invalid. Received: " + tag);
            case LAYOUT_BENZNTGFYZLINKCARD /* 171 */:
                if ("layout-hdpi-1920x720/benz_ntg_fy_zlink_card_0".equals(tag)) {
                    return new BenzNtgFyZlinkCardBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/benz_ntg_fy_zlink_card_0".equals(tag)) {
                    return new BenzNtgFyZlinkCardBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for benz_ntg_fy_zlink_card is invalid. Received: " + tag);
            case LAYOUT_BMWID8DASHBOARDLAYOUT /* 172 */:
                if ("layout/bmw_id8_dashboard_layout_0".equals(tag)) {
                    return new BmwId8DashboardLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_dashboard_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8DASHBOARDLAYOUTNEW /* 173 */:
                if ("layout/bmw_id8_dashboard_layout_new_0".equals(tag)) {
                    return new BmwId8DashboardLayoutNewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_dashboard_layout_new is invalid. Received: " + tag);
            case LAYOUT_BMWID8DASHBOARDMUSICLAYOUT /* 174 */:
                if ("layout/bmw_id8_dashboard_music_layout_0".equals(tag)) {
                    return new BmwId8DashboardMusicLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_dashboard_music_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8DASHBOARDWEATHERLAYOUT /* 175 */:
                if ("layout/bmw_id8_dashboard_weather_layout_0".equals(tag)) {
                    return new BmwId8DashboardWeatherLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_dashboard_weather_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSAUDIOANDROIDLAYOUT /* 176 */:
                if ("layout/bmw_id8_settings_audio_android_layout_0".equals(tag)) {
                    return new BmwId8SettingsAudioAndroidLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_audio_android_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSAUDIOLAYOUT /* 177 */:
                if ("layout/bmw_id8_settings_audio_layout_0".equals(tag)) {
                    return new BmwId8SettingsAudioLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_audio_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSAUDIOOEMLAYOUT /* 178 */:
                if ("layout/bmw_id8_settings_audio_oem_layout_0".equals(tag)) {
                    return new BmwId8SettingsAudioOemLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_audio_oem_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSAUDIOSOUNDLAYOUT /* 179 */:
                if ("layout/bmw_id8_settings_audio_sound_layout_0".equals(tag)) {
                    return new BmwId8SettingsAudioSoundLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_audio_sound_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSFACTORYLAYOUT /* 180 */:
                if ("layout/bmw_id8_settings_factory_layout_0".equals(tag)) {
                    return new BmwId8SettingsFactoryLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_factory_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSINFOLAYOUT /* 181 */:
                if ("layout/bmw_id8_settings_info_layout_0".equals(tag)) {
                    return new BmwId8SettingsInfoLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_info_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSLANGUAGELAYOUT /* 182 */:
                if ("layout/bmw_id8_settings_language_layout_0".equals(tag)) {
                    return new BmwId8SettingsLanguageLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_language_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSMAINLAYOUT /* 183 */:
                if ("layout/bmw_id8_settings_main_layout_0".equals(tag)) {
                    return new BmwId8SettingsMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_main_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSNAVILAYOUT /* 184 */:
                if ("layout/bmw_id8_settings_navi_layout_0".equals(tag)) {
                    return new BmwId8SettingsNaviLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_navi_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMBRIGHTNESSLAYOUT /* 185 */:
                if ("layout/bmw_id8_settings_system_brightness_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemBrightnessLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_brightness_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMCAMERALAYOUT /* 186 */:
                if ("layout/bmw_id8_settings_system_camera_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemCameraLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_camera_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMFUELLAYOUT /* 187 */:
                if ("layout/bmw_id8_settings_system_fuel_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemFuelLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_fuel_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMLAYOUT /* 188 */:
                if ("layout/bmw_id8_settings_system_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMMUSICLAYOUT /* 189 */:
                if ("layout/bmw_id8_settings_system_music_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemMusicLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_music_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMTEMPLAYOUT /* 190 */:
                if ("layout/bmw_id8_settings_system_temp_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemTempLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_temp_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSSYSTEMVIDEOLAYOUT /* 191 */:
                if ("layout/bmw_id8_settings_system_video_layout_0".equals(tag)) {
                    return new BmwId8SettingsSystemVideoLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_system_video_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8SETTINGSTIMELAYOUT /* 192 */:
                if ("layout/bmw_id8_settings_time_layout_0".equals(tag)) {
                    return new BmwId8SettingsTimeLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8_settings_time_layout is invalid. Received: " + tag);
            case LAYOUT_BMWID8GSSETTINGSMAINLAYOUT /* 193 */:
                if ("layout/bmw_id8gs_settings_main_layout_0".equals(tag)) {
                    return new BmwId8gsSettingsMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_id8gs_settings_main_layout is invalid. Received: " + tag);
            case LAYOUT_BMWPEMPID8SETTINGSMAINLAYOUT /* 194 */:
                if ("layout/bmw_pemp_id8_settings_main_layout_0".equals(tag)) {
                    return new BmwPempId8SettingsMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for bmw_pemp_id8_settings_main_layout is invalid. Received: " + tag);
            case LAYOUT_FRABENZGSONE /* 195 */:
                if ("layout/fra_benzgs_one_0".equals(tag)) {
                    return new FraBenzgsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_one is invalid. Received: " + tag);
            case LAYOUT_FRABENZGSTWO /* 196 */:
                if ("layout/fra_benzgs_two_0".equals(tag)) {
                    return new FraBenzgsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_benzgs_two is invalid. Received: " + tag);
            case LAYOUT_FRABMWEVOID6GSONE /* 197 */:
                if ("layout/fra_bmw_evo_id6_gs_one_0".equals(tag)) {
                    return new FraBmwEvoId6GsOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_one is invalid. Received: " + tag);
            case LAYOUT_FRABMWEVOID6GSTHREE /* 198 */:
                if ("layout/fra_bmw_evo_id6_gs_three_0".equals(tag)) {
                    return new FraBmwEvoId6GsThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_three is invalid. Received: " + tag);
            case LAYOUT_FRABMWEVOID6GSTWO /* 199 */:
                if ("layout/fra_bmw_evo_id6_gs_two_0".equals(tag)) {
                    return new FraBmwEvoId6GsTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fra_bmw_evo_id6_gs_two is invalid. Received: " + tag);
            case 200:
                if ("layout/fragment_als_id7_ui_car_0".equals(tag)) {
                    return new AlsId7UiCarInfoBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_als_id7_ui_car_0".equals(tag)) {
                    return new AlsId7UiCarInfoBindingHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_als_id7_ui_car is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding4(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 201:
                if ("layout-hdpi-1920x720/fragment_als_id7_ui_media_0".equals(tag)) {
                    return new AlsId7UiMediaBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/fragment_als_id7_ui_media_0".equals(tag)) {
                    return new AlsId7UiMediaBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_als_id7_ui_media is invalid. Received: " + tag);
            case 202:
                if ("layout-hdpi-1920x720/fragment_als_id7_ui_navi_0".equals(tag)) {
                    return new AlsId7UiNaviBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/fragment_als_id7_ui_navi_0".equals(tag)) {
                    return new AlsId7UiNaviBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_als_id7_ui_navi is invalid. Received: " + tag);
            case 203:
                if ("layout-mdpi-1280x480/fragment_audi_mib3_fy_one_0".equals(tag)) {
                    return new AudiMib3FyOneDataClsMdpi1280x480Impl(component, view);
                }
                if ("layout/fragment_audi_mib3_fy_one_0".equals(tag)) {
                    return new AudiMib3FyOneDataClsImpl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_audi_mib3_fy_one_0".equals(tag)) {
                    return new AudiMib3FyOneDataClsHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_fy_one is invalid. Received: " + tag);
            case 204:
                if ("layout-hdpi-1920x720/fragment_audi_mib3_fy_two_0".equals(tag)) {
                    return new AudiMib3FyTwoDataClsHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/fragment_audi_mib3_fy_two_0".equals(tag)) {
                    return new AudiMib3FyTwoDataClsMdpi1280x480Impl(component, view);
                }
                if ("layout/fragment_audi_mib3_fy_two_0".equals(tag)) {
                    return new AudiMib3FyTwoDataClsImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_fy_two is invalid. Received: " + tag);
            case 205:
                if ("layout-mdpi-1280x480/fragment_audi_mib3_fy_v2_one_0".equals(tag)) {
                    return new AudiMib3FyV2OneDataClsMdpi1280x480Impl(component, view);
                }
                if ("layout/fragment_audi_mib3_fy_v2_one_0".equals(tag)) {
                    return new AudiMib3FyV2OneDataClsImpl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_audi_mib3_fy_v2_one_0".equals(tag)) {
                    return new AudiMib3FyV2OneDataClsHdpi1920x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_fy_v2_one is invalid. Received: " + tag);
            case 206:
                if ("layout/fragment_audi_mib3_fy_v2_two_0".equals(tag)) {
                    return new AudiMib3FyV2TwoDataClsImpl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_audi_mib3_fy_v2_two_0".equals(tag)) {
                    return new AudiMib3FyV2TwoDataClsHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/fragment_audi_mib3_fy_v2_two_0".equals(tag)) {
                    return new AudiMib3FyV2TwoDataClsMdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_fy_v2_two is invalid. Received: " + tag);
            case 207:
                if ("layout/fragment_audi_mib3_ty_one_0".equals(tag)) {
                    return new AudiMib3TyOneBindingImpl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_audi_mib3_ty_one_0".equals(tag)) {
                    return new AudiMib3TyOneBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-mdpi-1280x480/fragment_audi_mib3_ty_one_0".equals(tag)) {
                    return new AudiMib3TyOneBindingMdpi1280x480Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_ty_one is invalid. Received: " + tag);
            case 208:
                if ("layout-mdpi-1280x480/fragment_audi_mib3_ty_two_0".equals(tag)) {
                    return new AudiMib3TyTwoBindingMdpi1280x480Impl(component, view);
                }
                if ("layout-hdpi-1920x720/fragment_audi_mib3_ty_two_0".equals(tag)) {
                    return new AudiMib3TyTwoBindingHdpi1920x720Impl(component, view);
                }
                if ("layout/fragment_audi_mib3_ty_two_0".equals(tag)) {
                    return new AudiMib3TyTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_audi_mib3_ty_two is invalid. Received: " + tag);
            case 209:
                if ("layout/fragment_benz_mbux2021_ksw_one_0".equals(tag)) {
                    return new Benz2021KswFragmentOneImpl(component, view);
                }
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_one_0".equals(tag)) {
                    return new Benz2021KswFragmentOneHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_one is invalid. Received: " + tag);
            case 210:
                if ("layout/fragment_benz_mbux2021_ksw_three_0".equals(tag)) {
                    return new Benz2021KswFragmentThreeImpl(component, view);
                }
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_three_0".equals(tag)) {
                    return new Benz2021KswFragmentThreeHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_three is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBENZMBUX2021KSWTWO /* 211 */:
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_two_0".equals(tag)) {
                    return new Benz2021KswFragmentTwoHdpi1280x720Impl(component, view);
                }
                if ("layout/fragment_benz_mbux2021_ksw_two_0".equals(tag)) {
                    return new Benz2021KswFragmentTwoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_two is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBENZMBUX2021KSWV2ONE /* 212 */:
                if ("layout/fragment_benz_mbux2021_ksw_v2_one_0".equals(tag)) {
                    return new Benz2021KswV2FragmentOneImpl(component, view);
                }
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_one_0".equals(tag)) {
                    return new Benz2021KswV2FragmentOneHdpi1280x720Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_v2_one is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBENZMBUX2021KSWV2THREE /* 213 */:
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_three_0".equals(tag)) {
                    return new Benz2021KswV2FragmentThreeHdpi1280x720Impl(component, view);
                }
                if ("layout/fragment_benz_mbux2021_ksw_v2_three_0".equals(tag)) {
                    return new Benz2021KswV2FragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_v2_three is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTBENZMBUX2021KSWV2TWO /* 214 */:
                if ("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_two_0".equals(tag)) {
                    return new Benz2021KswV2FragmentTwoHdpi1280x720Impl(component, view);
                }
                if ("layout/fragment_benz_mbux2021_ksw_v2_two_0".equals(tag)) {
                    return new Benz2021KswV2FragmentTwoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_benz_mbux2021_ksw_v2_two is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCARINFO /* 215 */:
                if ("layout/fragment_car_info_0".equals(tag)) {
                    return new CarInfoDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_car_info is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTCARINFOGS /* 216 */:
                if ("layout/fragment_car_info_gs_0".equals(tag)) {
                    return new CarInfoDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_car_info_gs is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTDASHBOARD /* 217 */:
                if ("layout/fragment_dashboard_0".equals(tag)) {
                    return new DashboardDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dashboard is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTDASHBOARDEDIT /* 218 */:
                if ("layout/fragment_dashboard_edit_0".equals(tag)) {
                    return new FragmentDashboardEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dashboard_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTDASHBOARDGS /* 219 */:
                if ("layout/fragment_dashboard_gs_0".equals(tag)) {
                    return new DashboardDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dashboard_gs is invalid. Received: " + tag);
            case 220:
                if ("layout/fragment_dashboardgs_edit_0".equals(tag)) {
                    return new FragmentDashboardgsEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_dashboardgs_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTGSDASHBOARDGSEDIT /* 221 */:
                if ("layout/fragment_gs_dashboardgs_edit_0".equals(tag)) {
                    return new FragmentDashboardgsGsEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_gs_dashboardgs_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTGSMUSICEDIT /* 222 */:
                if ("layout/fragment_gs_music_edit_0".equals(tag)) {
                    return new MusicGsEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_gs_music_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTGSPHONEEDIT /* 223 */:
                if ("layout/fragment_gs_phone_edit_0".equals(tag)) {
                    return new PhoneGsEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_gs_phone_edit is invalid. Received: " + tag);
            case 224:
                if ("layout/fragment_gs_video_edit_0".equals(tag)) {
                    return new VideoGsEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_gs_video_edit is invalid. Received: " + tag);
            case 225:
                if ("layout/fragment_gs_weather_edit_0".equals(tag)) {
                    return new FragmentGsWeatherEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_gs_weather_edit is invalid. Received: " + tag);
            case 226:
                if ("layout/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/fragment_id5_two_0".equals(tag)) {
                    return new FragmentId5TwoBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_id5_two is invalid. Received: " + tag);
            case 227:
                if ("layout/fragment_modus_0".equals(tag)) {
                    return new ModusDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_modus is invalid. Received: " + tag);
            case 228:
                if ("layout/fragment_modus_gs_0".equals(tag)) {
                    return new ModusDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_modus_gs is invalid. Received: " + tag);
            case 229:
                if ("layout/fragment_music_0".equals(tag)) {
                    return new MusicDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_music is invalid. Received: " + tag);
            case 230:
                if ("layout/fragment_music_edit_0".equals(tag)) {
                    return new MusicEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_music_edit is invalid. Received: " + tag);
            case 231:
                if ("layout/fragment_music_gs_0".equals(tag)) {
                    return new MusicDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_music_gs is invalid. Received: " + tag);
            case 232:
                if ("layout/fragment_navigate_0".equals(tag)) {
                    return new NavigateDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_navigate is invalid. Received: " + tag);
            case 233:
                if ("layout/fragment_navigate_gs_0".equals(tag)) {
                    return new NavigateDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_navigate_gs is invalid. Received: " + tag);
            case 234:
                if ("layout/fragment_pemp_car_info_0".equals(tag)) {
                    return new CarInfoPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_car_info is invalid. Received: " + tag);
            case 235:
                if ("layout/fragment_pemp_dashboard_0".equals(tag)) {
                    return new DashboardPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_dashboard is invalid. Received: " + tag);
            case 236:
                if ("layout/fragment_pemp_dashboard_edit_0".equals(tag)) {
                    return new FragmentPempDashboardEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_dashboard_edit is invalid. Received: " + tag);
            case 237:
                if ("layout/fragment_pemp_modus_0".equals(tag)) {
                    return new ModusPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_modus is invalid. Received: " + tag);
            case 238:
                if ("layout/fragment_pemp_music_0".equals(tag)) {
                    return new MusicPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_music is invalid. Received: " + tag);
            case 239:
                if ("layout/fragment_pemp_music_edit_0".equals(tag)) {
                    return new MusicEditorPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_music_edit is invalid. Received: " + tag);
            case 240:
                if ("layout/fragment_pemp_navigate_0".equals(tag)) {
                    return new NavigatePempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_navigate is invalid. Received: " + tag);
            case 241:
                if ("layout/fragment_pemp_phone_0".equals(tag)) {
                    return new PhonePempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_phone is invalid. Received: " + tag);
            case 242:
                if ("layout/fragment_pemp_phone_edit_0".equals(tag)) {
                    return new PhoneEditorPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_phone_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPEMPVIDEO /* 243 */:
                if ("layout/fragment_pemp_video_0".equals(tag)) {
                    return new VideoPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_video is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPEMPVIDEOEDIT /* 244 */:
                if ("layout/fragment_pemp_video_edit_0".equals(tag)) {
                    return new VideoEditorPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_video_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPEMPWEATHER /* 245 */:
                if ("layout/fragment_pemp_weather_0".equals(tag)) {
                    return new WeatherPempDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_weather is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPEMPWEATHEREDIT /* 246 */:
                if ("layout/fragment_pemp_weather_edit_0".equals(tag)) {
                    return new FragmentPempWeatherEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pemp_weather_edit is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPHONE /* 247 */:
                if ("layout/fragment_phone_0".equals(tag)) {
                    return new PhoneDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_phone is invalid. Received: " + tag);
            case LAYOUT_FRAGMENTPHONEEDIT /* 248 */:
                if ("layout/fragment_phone_edit_0".equals(tag)) {
                    return new PhoneEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_phone_edit is invalid. Received: " + tag);
            case 249:
                if ("layout/fragment_phone_gs_0".equals(tag)) {
                    return new PhoneDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_phone_gs is invalid. Received: " + tag);
            case 250:
                if ("layout/fragment_video_0".equals(tag)) {
                    return new VideoDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_video is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding5(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case 251:
                if ("layout/fragment_video_edit_0".equals(tag)) {
                    return new VideoEditorDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_video_edit is invalid. Received: " + tag);
            case 252:
                if ("layout/fragment_video_gs_0".equals(tag)) {
                    return new VideoDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_video_gs is invalid. Received: " + tag);
            case 253:
                if ("layout/fragment_weather_0".equals(tag)) {
                    return new WeatherDataBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_weather is invalid. Received: " + tag);
            case 254:
                if ("layout/fragment_weather_edit_0".equals(tag)) {
                    return new FragmentWeatherEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_weather_edit is invalid. Received: " + tag);
            case 255:
                if ("layout/fragment_weather_gs_0".equals(tag)) {
                    return new WeatherDataGsBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for fragment_weather_gs is invalid. Received: " + tag);
            case 256:
                if ("layout/id6_cusp_fragment_four_0".equals(tag)) {
                    return new ID6CuspFragmentFourImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_four is invalid. Received: " + tag);
            case 257:
                if ("layout/id6_cusp_fragment_one_0".equals(tag)) {
                    return new ID6CuspFragmentOneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_one is invalid. Received: " + tag);
            case LAYOUT_ID6CUSPFRAGMENTTHREE /* 258 */:
                if ("layout/id6_cusp_fragment_three_0".equals(tag)) {
                    return new ID6CuspFragmentThreeImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_three is invalid. Received: " + tag);
            case LAYOUT_ID6CUSPFRAGMENTTOW /* 259 */:
                if ("layout/id6_cusp_fragment_tow_0".equals(tag)) {
                    return new ID6CuspFragmentTowImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_cusp_fragment_tow is invalid. Received: " + tag);
            case LAYOUT_ID6FRAGMENTFOUR /* 260 */:
                if ("layout/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_four_0".equals(tag)) {
                    return new ID6FragmentFourSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_four is invalid. Received: " + tag);
            case LAYOUT_ID6FRAGMENTONE /* 261 */:
                if ("layout/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_one_0".equals(tag)) {
                    return new ID6FragmentOneSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_one is invalid. Received: " + tag);
            case LAYOUT_ID6FRAGMENTTHREE /* 262 */:
                if ("layout/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeImpl(component, view);
                }
                if ("layout-sw600dp-land/id6_fragment_three_0".equals(tag)) {
                    return new ID6FragmentThreeSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_three is invalid. Received: " + tag);
            case 263:
                if ("layout-sw600dp-land/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowSw600dpLandImpl(component, view);
                }
                if ("layout/id6_fragment_tow_0".equals(tag)) {
                    return new ID6FragmentTowImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_fragment_tow is invalid. Received: " + tag);
            case LAYOUT_ID6ONE /* 264 */:
                if ("layout-sw600dp-land/id6_one_0".equals(tag)) {
                    return new ID6OneSw600dpLandImpl(component, view);
                }
                if ("layout/id6_one_0".equals(tag)) {
                    return new ID6OneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id6_one is invalid. Received: " + tag);
            case LAYOUT_ID7APPITEM /* 265 */:
                if ("layout-hdpi-1920x720/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingHdpi1920x720Impl(component, view);
                }
                if ("layout-hdpi-1280x720/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingHdpi1280x720Impl(component, view);
                }
                if ("layout-sw600dp-land/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_app_item_0".equals(tag)) {
                    return new Id7AppItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_app_item is invalid. Received: " + tag);
            case LAYOUT_ID7FRAGMENTCAR /* 266 */:
                if ("layout/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_fragment_car_0".equals(tag)) {
                    return new CarInfoSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car is invalid. Received: " + tag);
            case LAYOUT_ID7FRAGMENTCARHICAR /* 267 */:
                if ("layout/id7_fragment_car_hicar_0".equals(tag)) {
                    return new HicarCarInfoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_car_hicar is invalid. Received: " + tag);
            case LAYOUT_ID7FRAGMENTMEDIA /* 268 */:
                if ("layout-sw600dp-land/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentSw600dpLandImpl(component, view);
                }
                if ("layout/id7_fragment_media_0".equals(tag)) {
                    return new MediaFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_media is invalid. Received: " + tag);
            case LAYOUT_ID7FRAGMENTNAVI /* 269 */:
                if ("layout/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi is invalid. Received: " + tag);
            case LAYOUT_ID7FRAGMENTNAVIHICAR /* 270 */:
                if ("layout/id7_fragment_navi_hicar_0".equals(tag)) {
                    return new HicarNaviFragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_fragment_navi_hicar is invalid. Received: " + tag);
            case LAYOUT_ID7SUBCARVIEW /* 271 */:
                if ("layout/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_car_view_0".equals(tag)) {
                    return new Id7SubCarViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_car_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBDASHBOARDVIEW /* 272 */:
                if ("layout-sw600dp-land/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_sub_dashboard_view_0".equals(tag)) {
                    return new Id7SubDashboardViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_dashboard_view is invalid. Received: " + tag);
            case 273:
                if ("layout/id7_sub_hicar_view_0".equals(tag)) {
                    return new Id7SubHicarViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_hicar_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBMUSICVIEW /* 274 */:
                if ("layout-sw600dp-land/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_sub_music_view_0".equals(tag)) {
                    return new Id7SubMusicViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_music_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBNAVIVIEW /* 275 */:
                if ("layout/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_navi_view_0".equals(tag)) {
                    return new NaviSubViewSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_navi_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBPHONEVIEW /* 276 */:
                if ("layout/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_phone_view_0".equals(tag)) {
                    return new Id7SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBPHONEVIEWHICAR /* 277 */:
                if ("layout/id7_sub_phone_view_hicar_0".equals(tag)) {
                    return new Id7SubPhoneViewHicarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_phone_view_hicar is invalid. Received: " + tag);
            case LAYOUT_ID7SUBVIDEOVIEW /* 278 */:
                if ("layout/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_sub_video_view_0".equals(tag)) {
                    return new Id7SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_video_view is invalid. Received: " + tag);
            case LAYOUT_ID7SUBWEATHERVIEW /* 279 */:
                if ("layout/id7_sub_weather_view_0".equals(tag)) {
                    return new Id7SubWeatherViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_sub_weather_view is invalid. Received: " + tag);
            case LAYOUT_ID7V2FRAGMENTCAR /* 280 */:
                if ("layout/id7_v2_fragment_car_0".equals(tag)) {
                    return new CarInfoV2Impl(component, view);
                }
                if ("layout-sw600dp-land/id7_v2_fragment_car_0".equals(tag)) {
                    return new CarInfoV2Sw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_fragment_car is invalid. Received: " + tag);
            case LAYOUT_ID7V2FRAGMENTMEDIA /* 281 */:
                if ("layout/id7_v2_fragment_media_0".equals(tag)) {
                    return new MediaFragmentBindingV2Impl(component, view);
                }
                if ("layout-sw600dp-land/id7_v2_fragment_media_0".equals(tag)) {
                    return new MediaFragmentBindingV2Sw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_fragment_media is invalid. Received: " + tag);
            case LAYOUT_ID7V2FRAGMENTNAVI /* 282 */:
                if ("layout/id7_v2_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentBindingV2Impl(component, view);
                }
                if ("layout-sw600dp-land/id7_v2_fragment_navi_0".equals(tag)) {
                    return new NaviFragmentBindingV2Sw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_fragment_navi is invalid. Received: " + tag);
            case LAYOUT_ID7V2SUBDASHBOARDVIEW /* 283 */:
                if ("layout/id7_v2_sub_dashboard_view_0".equals(tag)) {
                    return new Id7V2SubDashboardViewBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/id7_v2_sub_dashboard_view_0".equals(tag)) {
                    return new Id7V2SubDashboardViewBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_sub_dashboard_view is invalid. Received: " + tag);
            case LAYOUT_ID7V2SUBMUSICVIEW /* 284 */:
                if ("layout-sw600dp-land/id7_v2_sub_music_view_0".equals(tag)) {
                    return new Id7V2SubMusicViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_v2_sub_music_view_0".equals(tag)) {
                    return new Id7V2SubMusicViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_sub_music_view is invalid. Received: " + tag);
            case LAYOUT_ID7V2SUBPHONEVIEW /* 285 */:
                if ("layout-sw600dp-land/id7_v2_sub_phone_view_0".equals(tag)) {
                    return new Id7V2SubPhoneViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_v2_sub_phone_view_0".equals(tag)) {
                    return new Id7V2SubPhoneViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_sub_phone_view is invalid. Received: " + tag);
            case LAYOUT_ID7V2SUBVIDEOVIEW /* 286 */:
                if ("layout-sw600dp-land/id7_v2_sub_video_view_0".equals(tag)) {
                    return new Id7V2SubVideoViewBindingSw600dpLandImpl(component, view);
                }
                if ("layout/id7_v2_sub_video_view_0".equals(tag)) {
                    return new Id7V2SubVideoViewBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id7_v2_sub_video_view is invalid. Received: " + tag);
            case LAYOUT_ID8GSLAUNCHERLEFTBAR /* 287 */:
                if ("layout/id8_gs_launcher_left_bar_0".equals(tag)) {
                    return new Id8GsLauncherLeftBarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id8_gs_launcher_left_bar is invalid. Received: " + tag);
            case LAYOUT_ID8LAUNCHERLEFTBAR /* 288 */:
                if ("layout/id8_launcher_left_bar_0".equals(tag)) {
                    return new Id8LauncherLeftBarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id8_launcher_left_bar is invalid. Received: " + tag);
            case LAYOUT_ID8PEMPLAUNCHERLEFTBAR /* 289 */:
                if ("layout/id8_pemp_launcher_left_bar_0".equals(tag)) {
                    return new Id8PempLauncherLeftBarBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for id8_pemp_launcher_left_bar is invalid. Received: " + tag);
            case LAYOUT_INCLUDENEARWEATHER /* 290 */:
                if ("layout/include_near_weather_0".equals(tag)) {
                    return new IncludeNearWeatherBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for include_near_weather is invalid. Received: " + tag);
            case LAYOUT_INCLUDENEARWEATHEREDIT /* 291 */:
                if ("layout/include_near_weather_edit_0".equals(tag)) {
                    return new IncludeNearWeatherEditBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for include_near_weather_edit is invalid. Received: " + tag);
            case LAYOUT_KSWID7MAINPAGE1 /* 292 */:
                if ("layout/ksw_id7_main_page1_0".equals(tag)) {
                    return new KswId7MainPage1FragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ksw_id7_main_page1 is invalid. Received: " + tag);
            case LAYOUT_KSWID7MAINPAGE2 /* 293 */:
                if ("layout/ksw_id7_main_page2_0".equals(tag)) {
                    return new KswId7MainPage2FragmentImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ksw_id7_main_page2 is invalid. Received: " + tag);
            case LAYOUT_KSWMBUXHOMEONE /* 294 */:
                if ("layout/kswmbux_home_one_0".equals(tag)) {
                    return new KswmbuxHomeOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for kswmbux_home_one is invalid. Received: " + tag);
            case LAYOUT_KSWMBUXHOMETHREE /* 295 */:
                if ("layout/kswmbux_home_three_0".equals(tag)) {
                    return new KswmbuxHomeThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for kswmbux_home_three is invalid. Received: " + tag);
            case LAYOUT_KSWMBUXHOMETWO /* 296 */:
                if ("layout/kswmbux_home_two_0".equals(tag)) {
                    return new KswmbuxHomeTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for kswmbux_home_two is invalid. Received: " + tag);
            case LAYOUT_LANDROVERMAIN /* 297 */:
                if ("layout-1280x660/landrover_main_0".equals(tag)) {
                    return new LandroverMainBinding1280x660Impl(component, view);
                }
                if ("layout-1920x660/landrover_main_0".equals(tag)) {
                    return new LandroverMainBinding1920x660Impl(component, view);
                }
                if ("layout/landrover_main_0".equals(tag)) {
                    return new LandroverMainBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main is invalid. Received: " + tag);
            case LAYOUT_LANDROVERMAINBOTTOMLAY /* 298 */:
                if ("layout-1280x660/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBinding1280x660Impl(component, view);
                }
                if ("layout/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBindingImpl(component, view);
                }
                if ("layout-1920x660/landrover_main_bottom_lay_0".equals(tag)) {
                    return new LandroverMainBottomLayBinding1920x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_bottom_lay is invalid. Received: " + tag);
            case LAYOUT_LANDROVERMAINFRAGMENTONE /* 299 */:
                if ("layout-1280x660/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragment1280x660Impl(component, view);
                }
                if ("layout/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragmentImpl(component, view);
                }
                if ("layout-1920x660/landrover_main_fragment_one_0".equals(tag)) {
                    return new LandroverOneFragment1920x660Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for landrover_main_fragment_one is invalid. Received: " + tag);
            case 300:
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
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding6(DataBindingComponent component, View view, int internalId, Object tag) {
        switch (internalId) {
            case LAYOUT_LEXUSLSBOTTOMFRAGMENTONE /* 301 */:
                if ("layout/lexus_ls_bottom_fragment_one_0".equals(tag)) {
                    return new LexusLsBottomFragmentOneImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_bottom_fragment_one is invalid. Received: " + tag);
            case LAYOUT_LEXUSLSBOTTOMFRAGMENTTWO /* 302 */:
                if ("layout/lexus_ls_bottom_fragment_two_0".equals(tag)) {
                    return new LexusLsBottomFragmentTwoImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_bottom_fragment_two is invalid. Received: " + tag);
            case LAYOUT_LEXUSLSBOTTOMFRAGMENTTWOV2 /* 303 */:
                if ("layout/lexus_ls_bottom_fragment_two_v2_0".equals(tag)) {
                    return new LexusLsBottomFragmentTwo_V2Impl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_bottom_fragment_two_v2 is invalid. Received: " + tag);
            case LAYOUT_LEXUSLSDRAGMAINLAYOUT /* 304 */:
                if ("layout/lexus_ls_drag_main_layout_0".equals(tag)) {
                    return new LexusLsDragMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_drag_main_layout is invalid. Received: " + tag);
            case LAYOUT_LEXUSLSMAINLAYOUT /* 305 */:
                if ("layout/lexus_ls_main_layout_0".equals(tag)) {
                    return new LexusLsMainLayoutBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for lexus_ls_main_layout is invalid. Received: " + tag);
            case LAYOUT_NTG630CONTROLPOPUP /* 306 */:
                if ("layout/ntg630_control_popup_0".equals(tag)) {
                    return new Ntg630ControlPopupBindingImpl(component, view);
                }
                if ("layout-sw600dp-land/ntg630_control_popup_0".equals(tag)) {
                    return new Ntg630ControlPopupBindingSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg630_control_popup is invalid. Received: " + tag);
            case LAYOUT_NTG6CONTROLPOPUP /* 307 */:
                if ("layout/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindImpl(component, view);
                }
                if ("layout-sw600dp-land/ntg6_control_popup_0".equals(tag)) {
                    return new BenzControlBindSw600dpLandImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ntg6_control_popup is invalid. Received: " + tag);
            case LAYOUT_ROUNDAPPITEM /* 308 */:
                if ("layout/round_app_item_0".equals(tag)) {
                    return new RoundAppItemBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for round_app_item is invalid. Received: " + tag);
            case LAYOUT_UGHOMEFOUR /* 309 */:
                if ("layout/ug_home_four_0".equals(tag)) {
                    return new UgHomeFourBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four is invalid. Received: " + tag);
            case LAYOUT_UGHOMEFOUR2 /* 310 */:
                if ("layout/ug_home_four2_0".equals(tag)) {
                    return new UgHomeFour2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_four2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMEONE /* 311 */:
                if ("layout/ug_home_one_0".equals(tag)) {
                    return new UgHomeOneBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one is invalid. Received: " + tag);
            case LAYOUT_UGHOMEONE2 /* 312 */:
                if ("layout/ug_home_one2_0".equals(tag)) {
                    return new UgHomeOne2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_one2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMETHREE /* 313 */:
                if ("layout/ug_home_three_0".equals(tag)) {
                    return new UgHomeThreeBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three is invalid. Received: " + tag);
            case LAYOUT_UGHOMETHREE2 /* 314 */:
                if ("layout/ug_home_three2_0".equals(tag)) {
                    return new UgHomeThree2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_three2 is invalid. Received: " + tag);
            case LAYOUT_UGHOMETWO /* 315 */:
                if ("layout/ug_home_two_0".equals(tag)) {
                    return new UgHomeTwoBindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two is invalid. Received: " + tag);
            case LAYOUT_UGHOMETWO2 /* 316 */:
                if ("layout/ug_home_two2_0".equals(tag)) {
                    return new UgHomeTwo2BindingImpl(component, view);
                }
                throw new IllegalArgumentException("The tag for ug_home_two2 is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // android.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
        int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (localizedLayoutId > 0) {
            Object tag = view.getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
            int methodIndex = (localizedLayoutId - 1) / 50;
            switch (methodIndex) {
                case 0:
                    return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
                case 1:
                    return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
                case 2:
                    return internalGetViewDataBinding2(component, view, localizedLayoutId, tag);
                case 3:
                    return internalGetViewDataBinding3(component, view, localizedLayoutId, tag);
                case 4:
                    return internalGetViewDataBinding4(component, view, localizedLayoutId, tag);
                case 5:
                    return internalGetViewDataBinding5(component, view, localizedLayoutId, tag);
                case 6:
                    return internalGetViewDataBinding6(component, view, localizedLayoutId, tag);
                default:
                    return null;
            }
        }
        return null;
    }

    @Override // android.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
        if (views == null || views.length == 0) {
            return null;
        }
        int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
        if (localizedLayoutId > 0) {
            Object tag = views[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            }
        }
        return null;
    }

    @Override // android.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        Integer tmpVal;
        if (tag == null || (tmpVal = InnerLayoutIdLookup.sKeys.get(tag)) == null) {
            return 0;
        }
        return tmpVal.intValue();
    }

    @Override // android.databinding.DataBinderMapper
    public String convertBrIdToString(int localId) {
        String tmpVal = InnerBrLookup.sKeys.get(localId);
        return tmpVal;
    }

    @Override // android.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList<DataBinderMapper> result = new ArrayList<>(1);
        result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
        return result;
    }

    /* loaded from: classes17.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(27);
            sKeys = sparseArray;
            sparseArray.put(1, "AppViewModel");
            sparseArray.put(2, "BtViewModel");
            sparseArray.put(3, "CarViewModel");
            sparseArray.put(4, "DashVideoViewModel");
            sparseArray.put(5, "DashboardGsViewModel");
            sparseArray.put(6, "DashboardViewModel");
            sparseArray.put(7, "LauncherViewModel");
            sparseArray.put(8, "LeftViewModel");
            sparseArray.put(9, "LexusLsViewModel");
            sparseArray.put(10, "MediaViewModel");
            sparseArray.put(11, "ModusViewModel");
            sparseArray.put(12, "MusicPhoneViewModel");
            sparseArray.put(13, "NaviCarViewModel");
            sparseArray.put(14, "NaviViewModel");
            sparseArray.put(15, "NavigateViewModel");
            sparseArray.put(16, "ViewModel");
            sparseArray.put(17, "WeatherViewModel");
            sparseArray.put(18, "ZlinkWeatherViewModel");
            sparseArray.put(0, "_all");
            sparseArray.put(19, "launcherViewModel");
            sparseArray.put(20, "listItem");
            sparseArray.put(21, "mBcVieModel");
            sparseArray.put(22, "mUiParams");
            sparseArray.put(23, "nbtModel");
            sparseArray.put(24, "vieModel");
            sparseArray.put(25, "viewModel");
            sparseArray.put(26, "vm");
        }
    }

    /* loaded from: classes17.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(535);

        private InnerLayoutIdLookup() {
        }

        static {
            internalPopulateLayoutIdLookup0();
            internalPopulateLayoutIdLookup1();
        }

        private static void internalPopulateLayoutIdLookup0() {
            HashMap<String, Integer> hashMap = sKeys;
            hashMap.put("layout/activity_als_id7_apps_0", Integer.valueOf((int) C0899R.C0902layout.activity_als_id7_apps));
            hashMap.put("layout-sw600dp-land/activity_als_id7_apps_0", Integer.valueOf((int) C0899R.C0902layout.activity_als_id7_apps));
            hashMap.put("layout-sw600dp-land/activity_audi_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi));
            hashMap.put("layout/activity_audi_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi));
            hashMap.put("layout-hdpi-1920x720/activity_audi_mib3_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3));
            hashMap.put("layout/activity_audi_mib3_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3));
            hashMap.put("layout/activity_audi_mib3_sound_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound));
            hashMap.put("layout-hdpi-1920x720/activity_audi_mib3_sound_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound));
            hashMap.put("layout/activity_audi_mib3_sound_android_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound_android));
            hashMap.put("layout-hdpi-1920x720/activity_audi_mib3_sound_android_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound_android));
            hashMap.put("layout/activity_audi_mib3_sound_oem_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound_oem));
            hashMap.put("layout-hdpi-1920x720/activity_audi_mib3_sound_oem_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_sound_oem));
            hashMap.put("layout/activity_audi_mib3_time_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_time));
            hashMap.put("layout-hdpi-1920x720/activity_audi_mib3_time_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_mib3_time));
            hashMap.put("layout-sw600dp-land/activity_audi_sound_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_sound));
            hashMap.put("layout/activity_audi_sound_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_sound));
            hashMap.put("layout-sw600dp-land/activity_audi_time_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_time));
            hashMap.put("layout/activity_audi_time_0", Integer.valueOf((int) C0899R.C0902layout.activity_audi_time));
            hashMap.put("layout/activity_bmw_nbt_0", Integer.valueOf((int) C0899R.C0902layout.activity_bmw_nbt));
            Integer valueOf = Integer.valueOf((int) C0899R.C0902layout.activity_dash_board);
            hashMap.put("layout-hdpi-1920x720/activity_dash_board_0", valueOf);
            hashMap.put("layout-sw600dp-land/activity_dash_board_0", valueOf);
            hashMap.put("layout/activity_dash_board_0", valueOf);
            hashMap.put("layout-hdpi-1280x720/activity_dash_board_0", valueOf);
            Integer valueOf2 = Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_als);
            hashMap.put("layout-hdpi-1280x720/activity_dash_board_als_0", valueOf2);
            hashMap.put("layout/activity_dash_board_als_0", valueOf2);
            hashMap.put("layout-sw600dp-land/activity_dash_board_als_0", valueOf2);
            hashMap.put("layout-hdpi-1920x720/activity_dash_board_als_0", valueOf2);
            hashMap.put("layout/activity_dash_board_audi_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi));
            hashMap.put("layout-mdpi-1280x480/activity_dash_board_audi_mib3_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3));
            hashMap.put("layout-hdpi-1560x700/activity_dash_board_audi_mib3_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3));
            hashMap.put("layout-hdpi-1920x720/activity_dash_board_audi_mib3_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3));
            hashMap.put("layout/activity_dash_board_audi_mib3_fy_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3_fy));
            hashMap.put("layout-mdpi-1280x480/activity_dash_board_audi_mib3_fy_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3_fy));
            hashMap.put("layout-hdpi-1920x720/activity_dash_board_audi_mib3_fy_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_audi_mib3_fy));
            hashMap.put("layout/activity_dash_board_lc_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_lc));
            hashMap.put("layout/activity_dash_board_lexus_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_lexus));
            hashMap.put("layout-sw600dp-land/activity_dash_board_seven_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_seven));
            hashMap.put("layout/activity_dash_board_seven_0", Integer.valueOf((int) C0899R.C0902layout.activity_dash_board_seven));
            hashMap.put("layout/activity_gs_id8_launcher_main_0", Integer.valueOf((int) C0899R.C0902layout.activity_gs_id8_launcher_main));
            Integer valueOf3 = Integer.valueOf((int) C0899R.C0902layout.activity_id7_apps);
            hashMap.put("layout-hdpi-1920x720/activity_id7_apps_0", valueOf3);
            hashMap.put("layout-hdpi-1280x720/activity_id7_apps_0", valueOf3);
            hashMap.put("layout-sw600dp-land/activity_id7_apps_0", valueOf3);
            hashMap.put("layout/activity_id7_apps_0", valueOf3);
            hashMap.put("layout/activity_id8_apps_0", Integer.valueOf((int) C0899R.C0902layout.activity_id8_apps));
            hashMap.put("layout/activity_id8_gs_modus_layout_0", Integer.valueOf((int) C0899R.C0902layout.activity_id8_gs_modus_layout));
            hashMap.put("layout/activity_id8_launcher_main_0", Integer.valueOf((int) C0899R.C0902layout.activity_id8_launcher_main));
            hashMap.put("layout/activity_id8_modus_layout_0", Integer.valueOf((int) C0899R.C0902layout.activity_id8_modus_layout));
            hashMap.put("layout/activity_id8_pemp_modus_layout_0", Integer.valueOf((int) C0899R.C0902layout.activity_id8_pemp_modus_layout));
            hashMap.put("layout/activity_land_rover_settings_0", Integer.valueOf((int) C0899R.C0902layout.activity_land_rover_settings));
            hashMap.put("layout-1280x660/activity_land_rover_settings_0", Integer.valueOf((int) C0899R.C0902layout.activity_land_rover_settings));
            hashMap.put("layout-1920x660/activity_land_rover_settings_0", Integer.valueOf((int) C0899R.C0902layout.activity_land_rover_settings));
            hashMap.put("layout/activity_lexus_oem_fm_0", Integer.valueOf((int) C0899R.C0902layout.activity_lexus_oem_fm));
            hashMap.put("layout/activity_main_als_id6_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id6));
            hashMap.put("layout-sw600dp-land/activity_main_als_id7_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id7));
            hashMap.put("layout/activity_main_als_id7_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id7));
            hashMap.put("layout/activity_main_als_id7_ui_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id7_ui));
            hashMap.put("layout-hdpi-1920x720/activity_main_als_id7_ui_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id7_ui));
            hashMap.put("layout/activity_main_als_id7_v2_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_als_id7_v2));
            hashMap.put("layout/activity_main_audi_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_audi));
            hashMap.put("layout-sw600dp-land/activity_main_audi_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_audi));
            Integer valueOf4 = Integer.valueOf((int) C0899R.C0902layout.activity_main_bc);
            hashMap.put("layout-hdpi-1920x720/activity_main_bc_0", valueOf4);
            hashMap.put("layout/activity_main_bc_0", valueOf4);
            hashMap.put("layout-hdpi-1280x720/activity_main_bc_0", valueOf4);
            hashMap.put("layout-sw600dp-land/activity_main_bc_0", valueOf4);
            hashMap.put("layout/activity_main_benz_gs_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_gs));
            hashMap.put("layout/activity_main_benz_mbux_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux));
            hashMap.put("layout/activity_main_benz_mbux_2021_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021));
            hashMap.put("layout-hdpi-1280x720/activity_main_benz_mbux_2021_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021));
            hashMap.put("layout-hdpi-1920x720/activity_main_benz_mbux_2021_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021));
            Integer valueOf5 = Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021_2);
            hashMap.put("layout/activity_main_benz_mbux_2021_2_0", valueOf5);
            hashMap.put("layout-hdpi-1280x720/activity_main_benz_mbux_2021_2_0", valueOf5);
            hashMap.put("layout-hdpi-1920x720/activity_main_benz_mbux_2021_2_0", valueOf5);
            hashMap.put("layout-1024x600/activity_main_benz_mbux_2021_2_0", valueOf5);
            hashMap.put("layout/activity_main_benz_mbux_2021_ksw_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021_ksw));
            hashMap.put("layout/activity_main_benz_mbux_2021_ksw_v2_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_mbux_2021_ksw_v2));
            hashMap.put("layout/activity_main_benz_ntg5_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_ntg5));
            Integer valueOf6 = Integer.valueOf((int) C0899R.C0902layout.activity_main_benz_ntg6_fy);
            hashMap.put("layout-hdpi-1280x720/activity_main_benz_ntg6_fy_0", valueOf6);
            hashMap.put("layout/activity_main_benz_ntg6_fy_0", valueOf6);
            hashMap.put("layout-1024x600/activity_main_benz_ntg6_fy_0", valueOf6);
            hashMap.put("layout-hdpi-1920x720/activity_main_benz_ntg6_fy_0", valueOf6);
            hashMap.put("layout/activity_main_bmw_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_bmw));
            hashMap.put("layout-sw600dp-land/activity_main_bmw_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_bmw));
            hashMap.put("layout-hdpi-1920x720/activity_main_gsug_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_gsug));
            hashMap.put("layout/activity_main_gsug_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_gsug));
            hashMap.put("layout/activity_main_gsug2_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_gsug2));
            hashMap.put("layout-sw600dp-land/activity_main_id5_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_id5));
            hashMap.put("layout/activity_main_id5_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_id5));
            hashMap.put("layout/activity_main_id6_gs_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_id6_gs));
            hashMap.put("layout/activity_main_ksw_id7_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_ksw_id7));
            hashMap.put("layout/activity_main_kswmbux_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_kswmbux));
            hashMap.put("layout/activity_main_land_rover_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_land_rover));
            hashMap.put("layout-1280x660/activity_main_land_rover_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_land_rover));
            hashMap.put("layout-1920x660/activity_main_land_rover_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_land_rover));
            hashMap.put("layout/activity_main_lexus_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_lexus));
            hashMap.put("layout/activity_main_lexus_page1_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_lexus_page1));
            hashMap.put("layout/activity_main_lexus_page2_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_lexus_page2));
            hashMap.put("layout/activity_main_lexus_page3_0", Integer.valueOf((int) C0899R.C0902layout.activity_main_lexus_page3));
            Integer valueOf7 = Integer.valueOf((int) C0899R.C0902layout.activity_ntg6_dash_board);
            hashMap.put("layout-hdpi-1280x720/activity_ntg6_dash_board_0", valueOf7);
            hashMap.put("layout-hdpi-1920x720/activity_ntg6_dash_board_0", valueOf7);
            hashMap.put("layout/activity_ntg6_dash_board_0", valueOf7);
            hashMap.put("layout-sw600dp-land/activity_ntg6_dash_board_0", valueOf7);
            hashMap.put("layout/activity_pemp_id8_launcher_main_0", Integer.valueOf((int) C0899R.C0902layout.activity_pemp_id8_launcher_main));
            hashMap.put("layout/activity_romeo_0", Integer.valueOf((int) C0899R.C0902layout.activity_romeo));
            hashMap.put("layout/activity_romeo_v2_0", Integer.valueOf((int) C0899R.C0902layout.activity_romeo_v2));
            hashMap.put("layout/activity_round_apps_0", Integer.valueOf((int) C0899R.C0902layout.activity_round_apps));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_dash_video_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_dash_video));
            hashMap.put("layout/als_id7_fragment_dash_video_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_dash_video));
            hashMap.put("layout/als_id7_fragment_dash_video_v2_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_dash_video_v2));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_music_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_music));
            hashMap.put("layout/als_id7_fragment_music_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_music));
            hashMap.put("layout-sw600dp-land/als_id7_fragment_navi_car_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_navi_car));
            hashMap.put("layout/als_id7_fragment_navi_car_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_navi_car));
            hashMap.put("layout/als_id7_fragment_zlink_weather_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_fragment_zlink_weather));
            hashMap.put("layout-sw600dp-land/als_id7_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_car_view));
            hashMap.put("layout/als_id7_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_car_view));
            hashMap.put("layout/als_id7_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_dashboard_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_dashboard_view));
            hashMap.put("layout/als_id7_sub_music_third_layout_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_music_third_layout));
            hashMap.put("layout/als_id7_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_music_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_music_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_navi_view));
            hashMap.put("layout/als_id7_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_navi_view));
            hashMap.put("layout/als_id7_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_phone_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_phone_view));
            hashMap.put("layout/als_id7_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_video_view));
            hashMap.put("layout-sw600dp-land/als_id7_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_video_view));
            hashMap.put("layout/als_id7_sub_video_view_v2_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_video_view_v2));
            hashMap.put("layout/als_id7_sub_weather_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_weather_view));
            hashMap.put("layout/als_id7_sub_zlink_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_sub_zlink_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_car_view));
            hashMap.put("layout/als_id7_ui_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_car_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_dashboard_view));
            hashMap.put("layout/als_id7_ui_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_dashboard_view));
            hashMap.put("layout/als_id7_ui_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_music_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_music_view));
            hashMap.put("layout/als_id7_ui_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_navi_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_navi_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_phone_view));
            hashMap.put("layout/als_id7_ui_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_phone_view));
            hashMap.put("layout/als_id7_ui_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_video_view));
            hashMap.put("layout-hdpi-1920x720/als_id7_ui_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.als_id7_ui_sub_video_view));
            hashMap.put("layout/app_third_item_als_id7_ui_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_als_id7_ui));
            hashMap.put("layout/app_third_item_audi_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_audi));
            hashMap.put("layout/app_third_item_audimbi3_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_audimbi3));
            hashMap.put("layout-hdpi-1920x720/app_third_item_audimbi3_1_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_audimbi3_1));
            hashMap.put("layout/app_third_item_audimbi3_1_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_audimbi3_1));
            hashMap.put("layout/app_third_item_benz_ntg6_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_benz_ntg6));
            hashMap.put("layout/app_third_item_evo_id7_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_evo_id7));
            hashMap.put("layout/app_third_item_id6_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_id6));
            hashMap.put("layout/app_third_item_id7_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_id7));
            hashMap.put("layout/app_third_item_landrover_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_landrover));
            hashMap.put("layout/app_third_item_lexus_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_lexus));
            hashMap.put("layout/app_third_item_romeo_0", Integer.valueOf((int) C0899R.C0902layout.app_third_item_romeo));
            hashMap.put("layout-sw600dp-land/audi_aux_0", Integer.valueOf((int) C0899R.C0902layout.audi_aux));
            hashMap.put("layout/audi_aux_0", Integer.valueOf((int) C0899R.C0902layout.audi_aux));
            hashMap.put("layout/audi_brightness_0", Integer.valueOf((int) C0899R.C0902layout.audi_brightness));
            hashMap.put("layout-sw600dp-land/audi_brightness_0", Integer.valueOf((int) C0899R.C0902layout.audi_brightness));
            hashMap.put("layout-sw600dp-land/audi_eq_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_eq_view));
            hashMap.put("layout/audi_eq_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_eq_view));
            hashMap.put("layout/audi_mib3_aux_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_aux));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_aux_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_aux));
            hashMap.put("layout/audi_mib3_brightness_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_brightness));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_brightness_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_brightness));
            hashMap.put("layout/audi_mib3_eq_custom_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_eq_custom_view));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_eq_custom_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_eq_custom_view));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_eq_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_eq_view));
            hashMap.put("layout/audi_mib3_eq_view_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_eq_view));
            Integer valueOf8 = Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fragment_one);
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fragment_one_0", valueOf8);
            hashMap.put("layout-hdpi-1560x700/audi_mib3_fragment_one_0", valueOf8);
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fragment_one_0", valueOf8);
            hashMap.put("layout/audi_mib3_fragment_one_0", valueOf8);
            Integer valueOf9 = Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fragment_one_v2);
            hashMap.put("layout/audi_mib3_fragment_one_v2_0", valueOf9);
            hashMap.put("layout-hdpi-1560x700/audi_mib3_fragment_one_v2_0", valueOf9);
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fragment_one_v2_0", valueOf9);
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fragment_one_v2_0", valueOf9);
            Integer valueOf10 = Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fragment_two);
            hashMap.put("layout-hdpi-1560x700/audi_mib3_fragment_two_0", valueOf10);
            hashMap.put("layout/audi_mib3_fragment_two_0", valueOf10);
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fragment_two_0", valueOf10);
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fragment_two_0", valueOf10);
            Integer valueOf11 = Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fragment_two_v2);
            hashMap.put("layout-hdpi-1560x700/audi_mib3_fragment_two_v2_0", valueOf11);
            hashMap.put("layout/audi_mib3_fragment_two_v2_0", valueOf11);
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fragment_two_v2_0", valueOf11);
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fragment_two_v2_0", valueOf11);
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fy_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_layout));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fy_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_layout));
            hashMap.put("layout/audi_mib3_fy_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_layout));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_fy_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_left));
            hashMap.put("layout/audi_mib3_fy_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_left));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_fy_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_fy_main_left));
            hashMap.put("layout/audi_mib3_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_layout));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_layout));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_layout));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_left));
            hashMap.put("layout/audi_mib3_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_left));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_main_left));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_navi));
            hashMap.put("layout/audi_mib3_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_navi));
            hashMap.put("layout/audi_mib3_password_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_password));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_password_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_password));
            hashMap.put("layout/audi_mib3_rever_camera_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_rever_camera));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_rever_camera_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_rever_camera));
            hashMap.put("layout/audi_mib3_right_carinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_right_carinfo));
            hashMap.put("layout/audi_mib3_right_logo_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_right_logo));
            hashMap.put("layout/audi_mib3_right_media_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_right_media));
            hashMap.put("layout/audi_mib3_right_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_right_navi));
            hashMap.put("layout/audi_mib3_speed_unit_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_speed_unit));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_speed_unit_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_speed_unit));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_sysinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_sysinfo));
            hashMap.put("layout/audi_mib3_sysinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_sysinfo));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_system_set_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_system_set));
            hashMap.put("layout/audi_mib3_system_set_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_system_set));
            hashMap.put("layout/audi_mib3_temp_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_temp));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_temp_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_temp));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_ty_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_layout));
            hashMap.put("layout/audi_mib3_ty_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_layout));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_ty_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_layout));
            hashMap.put("layout-hdpi-1920x720/audi_mib3_ty_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_left));
            hashMap.put("layout-mdpi-1280x480/audi_mib3_ty_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_left));
            hashMap.put("layout/audi_mib3_ty_main_left_0", Integer.valueOf((int) C0899R.C0902layout.audi_mib3_ty_main_left));
            hashMap.put("layout-sw600dp-land/audi_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_navi));
            hashMap.put("layout/audi_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_navi));
            hashMap.put("layout-sw600dp-land/audi_password_0", Integer.valueOf((int) C0899R.C0902layout.audi_password));
            hashMap.put("layout/audi_password_0", Integer.valueOf((int) C0899R.C0902layout.audi_password));
            hashMap.put("layout/audi_rever_camera_0", Integer.valueOf((int) C0899R.C0902layout.audi_rever_camera));
            hashMap.put("layout-sw600dp-land/audi_right_carinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_carinfo));
            hashMap.put("layout/audi_right_carinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_carinfo));
            hashMap.put("layout/audi_right_logo_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_logo));
            hashMap.put("layout-sw600dp-land/audi_right_logo_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_logo));
            hashMap.put("layout-sw600dp-land/audi_right_media_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_media));
            hashMap.put("layout/audi_right_media_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_media));
            hashMap.put("layout/audi_right_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_navi));
            hashMap.put("layout-sw600dp-land/audi_right_navi_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_navi));
            hashMap.put("layout-sw600dp-land/audi_right_weather_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_weather));
            hashMap.put("layout/audi_right_weather_0", Integer.valueOf((int) C0899R.C0902layout.audi_right_weather));
            hashMap.put("layout/audi_sel_third_app_layout_0", Integer.valueOf((int) C0899R.C0902layout.audi_sel_third_app_layout));
            hashMap.put("layout/audi_speed_unit_0", Integer.valueOf((int) C0899R.C0902layout.audi_speed_unit));
            hashMap.put("layout/audi_sysinfo_0", Integer.valueOf((int) C0899R.C0902layout.audi_sysinfo));
            hashMap.put("layout/audi_system_set_0", Integer.valueOf((int) C0899R.C0902layout.audi_system_set));
            hashMap.put("layout/audi_temp_0", Integer.valueOf((int) C0899R.C0902layout.audi_temp));
            hashMap.put("layout-hdpi-1920x720/audimbi3_sel_third_app_layout_0", Integer.valueOf((int) C0899R.C0902layout.audimbi3_sel_third_app_layout));
            hashMap.put("layout/audimbi3_sel_third_app_layout_0", Integer.valueOf((int) C0899R.C0902layout.audimbi3_sel_third_app_layout));
            Integer valueOf12 = Integer.valueOf((int) C0899R.C0902layout.bc_item);
            hashMap.put("layout/bc_item_0", valueOf12);
            hashMap.put("layout-hdpi-1920x720/bc_item_0", valueOf12);
            hashMap.put("layout-hdpi-1280x720/bc_item_0", valueOf12);
            hashMap.put("layout-sw600dp-land/bc_item_0", valueOf12);
            hashMap.put("layout/bc_ntg5_item_0", Integer.valueOf((int) C0899R.C0902layout.bc_ntg5_item));
            hashMap.put("layout-sw600dp-land/bc_sub_view_0", Integer.valueOf((int) C0899R.C0902layout.bc_sub_view));
            hashMap.put("layout/bc_sub_view_0", Integer.valueOf((int) C0899R.C0902layout.bc_sub_view));
            Integer valueOf13 = Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_fragment_one);
            hashMap.put("layout/benz_mbux_2021_fragment_one_0", valueOf13);
            hashMap.put("layout-hdpi-1920x720/benz_mbux_2021_fragment_one_0", valueOf13);
            hashMap.put("layout-hdpi-1280x720/benz_mbux_2021_fragment_one_0", valueOf13);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_one_0", valueOf13);
            Integer valueOf14 = Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_fragment_three);
            hashMap.put("layout/benz_mbux_2021_fragment_three_0", valueOf14);
            hashMap.put("layout-hdpi-1280x720/benz_mbux_2021_fragment_three_0", valueOf14);
            hashMap.put("layout-hdpi-1920x720/benz_mbux_2021_fragment_three_0", valueOf14);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_three_0", valueOf14);
            Integer valueOf15 = Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_fragment_two);
            hashMap.put("layout-hdpi-1920x720/benz_mbux_2021_fragment_two_0", valueOf15);
            hashMap.put("layout/benz_mbux_2021_fragment_two_0", valueOf15);
            hashMap.put("layout-1024x600/benz_mbux_2021_fragment_two_0", valueOf15);
            hashMap.put("layout-hdpi-1280x720/benz_mbux_2021_fragment_two_0", valueOf15);
            hashMap.put("layout/benz_mbux_2021_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_item));
            hashMap.put("layout-hdpi-1280x720/benz_mbux_2021_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_item));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_2021_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_item));
            hashMap.put("layout-1024x600/benz_mbux_2021_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_item));
            hashMap.put("layout/benz_mbux_2021_ksw_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_2021_ksw_item));
            hashMap.put("layout/benz_mbux_apps_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_apps_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_apps_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_apps_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_bt_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_bt_card));
            hashMap.put("layout/benz_mbux_bt_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_bt_card));
            hashMap.put("layout/benz_mbux_carinfo_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_carinfo_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_carinfo_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_carinfo_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_dashboard_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_dashboard_card));
            hashMap.put("layout/benz_mbux_dashboard_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_dashboard_card));
            hashMap.put("layout/benz_mbux_dvr_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_dvr_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_dvr_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_dvr_card));
            hashMap.put("layout/benz_mbux_item_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_item));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_ksw_activity_new_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_ksw_activity_new));
            hashMap.put("layout/benz_mbux_ksw_activity_new_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_ksw_activity_new));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_music_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_music_card));
            hashMap.put("layout/benz_mbux_music_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_music_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_navi_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_navi_card));
            hashMap.put("layout/benz_mbux_navi_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_navi_card));
            hashMap.put("layout/benz_mbux_set_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_set_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_set_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_set_card));
            hashMap.put("layout/benz_mbux_third_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_third_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_third_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_third_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_video_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_video_card));
            hashMap.put("layout/benz_mbux_video_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_video_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_weather_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_weather_card));
            hashMap.put("layout/benz_mbux_weather_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_weather_card));
            hashMap.put("layout-hdpi-1920x720/benz_mbux_zlink_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_zlink_card));
            hashMap.put("layout/benz_mbux_zlink_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_mbux_zlink_card));
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one));
            hashMap.put("layout/benz_ntg6_fy_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one));
            hashMap.put("layout-hdpi-1920x720/benz_ntg6_fy_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one));
            hashMap.put("layout-hdpi-1280x720/benz_ntg6_fy_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one));
            hashMap.put("layout-hdpi-1280x720/benz_ntg6_fy_fragment_one_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2));
            hashMap.put("layout/benz_ntg6_fy_fragment_one_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2));
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_one_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2));
            hashMap.put("layout-hdpi-1920x720/benz_ntg6_fy_fragment_one_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_one_v2));
            hashMap.put("layout-hdpi-1280x720/benz_ntg6_fy_fragment_two_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two));
            hashMap.put("layout-hdpi-1920x720/benz_ntg6_fy_fragment_two_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two));
            hashMap.put("layout/benz_ntg6_fy_fragment_two_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two));
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_two_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two));
            hashMap.put("layout/benz_ntg6_fy_fragment_two_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2));
            hashMap.put("layout-1024x600/benz_ntg6_fy_fragment_two_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2));
            hashMap.put("layout-hdpi-1280x720/benz_ntg6_fy_fragment_two_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2));
            hashMap.put("layout-hdpi-1920x720/benz_ntg6_fy_fragment_two_v2_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg6_fy_fragment_two_v2));
            hashMap.put("layout/benz_ntg_fy_activity_new_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_activity_new));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_activity_new_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_activity_new));
            hashMap.put("layout/benz_ntg_fy_apps_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_apps_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_apps_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_apps_card));
            hashMap.put("layout/benz_ntg_fy_bt_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_bt_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_bt_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_bt_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_carinfo_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_carinfo_card));
            hashMap.put("layout/benz_ntg_fy_carinfo_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_carinfo_card));
            hashMap.put("layout/benz_ntg_fy_dashboard_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_dashboard_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_dashboard_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_dashboard_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_dvr_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_dvr_card));
            hashMap.put("layout/benz_ntg_fy_dvr_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_dvr_card));
            hashMap.put("layout/benz_ntg_fy_music_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_music_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_music_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_music_card));
            hashMap.put("layout/benz_ntg_fy_navi_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_navi_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_navi_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_navi_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_set_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_set_card));
            hashMap.put("layout/benz_ntg_fy_set_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_set_card));
            hashMap.put("layout/benz_ntg_fy_third_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_third_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_third_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_third_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_video_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_video_card));
            hashMap.put("layout/benz_ntg_fy_video_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_video_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_weather_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_weather_card));
            hashMap.put("layout/benz_ntg_fy_weather_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_weather_card));
            hashMap.put("layout-hdpi-1920x720/benz_ntg_fy_zlink_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_zlink_card));
            hashMap.put("layout/benz_ntg_fy_zlink_card_0", Integer.valueOf((int) C0899R.C0902layout.benz_ntg_fy_zlink_card));
            hashMap.put("layout/bmw_id8_dashboard_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_dashboard_layout));
            hashMap.put("layout/bmw_id8_dashboard_layout_new_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_dashboard_layout_new));
            hashMap.put("layout/bmw_id8_dashboard_music_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_dashboard_music_layout));
            hashMap.put("layout/bmw_id8_dashboard_weather_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_dashboard_weather_layout));
            hashMap.put("layout/bmw_id8_settings_audio_android_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_audio_android_layout));
            hashMap.put("layout/bmw_id8_settings_audio_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_audio_layout));
            hashMap.put("layout/bmw_id8_settings_audio_oem_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_audio_oem_layout));
            hashMap.put("layout/bmw_id8_settings_audio_sound_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_audio_sound_layout));
            hashMap.put("layout/bmw_id8_settings_factory_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_factory_layout));
            hashMap.put("layout/bmw_id8_settings_info_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_info_layout));
            hashMap.put("layout/bmw_id8_settings_language_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_language_layout));
            hashMap.put("layout/bmw_id8_settings_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_main_layout));
            hashMap.put("layout/bmw_id8_settings_navi_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_navi_layout));
            hashMap.put("layout/bmw_id8_settings_system_brightness_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_brightness_layout));
            hashMap.put("layout/bmw_id8_settings_system_camera_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_camera_layout));
            hashMap.put("layout/bmw_id8_settings_system_fuel_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_fuel_layout));
            hashMap.put("layout/bmw_id8_settings_system_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_layout));
            hashMap.put("layout/bmw_id8_settings_system_music_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_music_layout));
            hashMap.put("layout/bmw_id8_settings_system_temp_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_temp_layout));
            hashMap.put("layout/bmw_id8_settings_system_video_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_system_video_layout));
            hashMap.put("layout/bmw_id8_settings_time_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8_settings_time_layout));
            hashMap.put("layout/bmw_id8gs_settings_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_id8gs_settings_main_layout));
            hashMap.put("layout/bmw_pemp_id8_settings_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.bmw_pemp_id8_settings_main_layout));
            hashMap.put("layout/fra_benzgs_one_0", Integer.valueOf((int) C0899R.C0902layout.fra_benzgs_one));
            hashMap.put("layout/fra_benzgs_two_0", Integer.valueOf((int) C0899R.C0902layout.fra_benzgs_two));
            hashMap.put("layout/fra_bmw_evo_id6_gs_one_0", Integer.valueOf((int) C0899R.C0902layout.fra_bmw_evo_id6_gs_one));
            hashMap.put("layout/fra_bmw_evo_id6_gs_three_0", Integer.valueOf((int) C0899R.C0902layout.fra_bmw_evo_id6_gs_three));
            hashMap.put("layout/fra_bmw_evo_id6_gs_two_0", Integer.valueOf((int) C0899R.C0902layout.fra_bmw_evo_id6_gs_two));
            hashMap.put("layout/fragment_als_id7_ui_car_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_car));
            hashMap.put("layout-hdpi-1920x720/fragment_als_id7_ui_car_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_car));
            hashMap.put("layout-hdpi-1920x720/fragment_als_id7_ui_media_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_media));
            hashMap.put("layout/fragment_als_id7_ui_media_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_media));
            hashMap.put("layout-hdpi-1920x720/fragment_als_id7_ui_navi_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_navi));
            hashMap.put("layout/fragment_als_id7_ui_navi_0", Integer.valueOf((int) C0899R.C0902layout.fragment_als_id7_ui_navi));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_fy_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_one));
            hashMap.put("layout/fragment_audi_mib3_fy_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_one));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_fy_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_one));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_fy_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_two));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_fy_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_two));
            hashMap.put("layout/fragment_audi_mib3_fy_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_two));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_fy_v2_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_one));
            hashMap.put("layout/fragment_audi_mib3_fy_v2_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_one));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_fy_v2_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_one));
            hashMap.put("layout/fragment_audi_mib3_fy_v2_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_two));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_fy_v2_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_two));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_fy_v2_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_fy_v2_two));
            hashMap.put("layout/fragment_audi_mib3_ty_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_one));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_ty_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_one));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_ty_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_one));
            hashMap.put("layout-mdpi-1280x480/fragment_audi_mib3_ty_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_two));
            hashMap.put("layout-hdpi-1920x720/fragment_audi_mib3_ty_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_two));
            hashMap.put("layout/fragment_audi_mib3_ty_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_audi_mib3_ty_two));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_one));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_one));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_three_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_three));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_three_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_three));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_two));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_two));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_v2_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_one_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_one));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_three_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_v2_three_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_three));
            hashMap.put("layout-hdpi-1280x720/fragment_benz_mbux2021_ksw_v2_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_two));
            hashMap.put("layout/fragment_benz_mbux2021_ksw_v2_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_benz_mbux2021_ksw_v2_two));
            hashMap.put("layout/fragment_car_info_0", Integer.valueOf((int) C0899R.C0902layout.fragment_car_info));
            hashMap.put("layout/fragment_car_info_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_car_info_gs));
            hashMap.put("layout/fragment_dashboard_0", Integer.valueOf((int) C0899R.C0902layout.fragment_dashboard));
            hashMap.put("layout/fragment_dashboard_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_dashboard_edit));
            hashMap.put("layout/fragment_dashboard_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_dashboard_gs));
            hashMap.put("layout/fragment_dashboardgs_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_dashboardgs_edit));
            hashMap.put("layout/fragment_gs_dashboardgs_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_gs_dashboardgs_edit));
            hashMap.put("layout/fragment_gs_music_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_gs_music_edit));
            hashMap.put("layout/fragment_gs_phone_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_gs_phone_edit));
            hashMap.put("layout/fragment_gs_video_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_gs_video_edit));
            hashMap.put("layout/fragment_gs_weather_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_gs_weather_edit));
            hashMap.put("layout/fragment_id5_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_id5_two));
            hashMap.put("layout-sw600dp-land/fragment_id5_two_0", Integer.valueOf((int) C0899R.C0902layout.fragment_id5_two));
            hashMap.put("layout/fragment_modus_0", Integer.valueOf((int) C0899R.C0902layout.fragment_modus));
            hashMap.put("layout/fragment_modus_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_modus_gs));
            hashMap.put("layout/fragment_music_0", Integer.valueOf((int) C0899R.C0902layout.fragment_music));
            hashMap.put("layout/fragment_music_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_music_edit));
            hashMap.put("layout/fragment_music_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_music_gs));
            hashMap.put("layout/fragment_navigate_0", Integer.valueOf((int) C0899R.C0902layout.fragment_navigate));
            hashMap.put("layout/fragment_navigate_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_navigate_gs));
            hashMap.put("layout/fragment_pemp_car_info_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_car_info));
            hashMap.put("layout/fragment_pemp_dashboard_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_dashboard));
            hashMap.put("layout/fragment_pemp_dashboard_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_dashboard_edit));
            hashMap.put("layout/fragment_pemp_modus_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_modus));
            hashMap.put("layout/fragment_pemp_music_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_music));
            hashMap.put("layout/fragment_pemp_music_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_music_edit));
            hashMap.put("layout/fragment_pemp_navigate_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_navigate));
            hashMap.put("layout/fragment_pemp_phone_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_phone));
            hashMap.put("layout/fragment_pemp_phone_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_phone_edit));
            hashMap.put("layout/fragment_pemp_video_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_video));
            hashMap.put("layout/fragment_pemp_video_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_video_edit));
            hashMap.put("layout/fragment_pemp_weather_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_weather));
            hashMap.put("layout/fragment_pemp_weather_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_pemp_weather_edit));
            hashMap.put("layout/fragment_phone_0", Integer.valueOf((int) C0899R.C0902layout.fragment_phone));
            hashMap.put("layout/fragment_phone_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_phone_edit));
            hashMap.put("layout/fragment_phone_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_phone_gs));
            hashMap.put("layout/fragment_video_0", Integer.valueOf((int) C0899R.C0902layout.fragment_video));
            hashMap.put("layout/fragment_video_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_video_edit));
            hashMap.put("layout/fragment_video_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_video_gs));
            hashMap.put("layout/fragment_weather_0", Integer.valueOf((int) C0899R.C0902layout.fragment_weather));
            hashMap.put("layout/fragment_weather_edit_0", Integer.valueOf((int) C0899R.C0902layout.fragment_weather_edit));
            hashMap.put("layout/fragment_weather_gs_0", Integer.valueOf((int) C0899R.C0902layout.fragment_weather_gs));
            hashMap.put("layout/id6_cusp_fragment_four_0", Integer.valueOf((int) C0899R.C0902layout.id6_cusp_fragment_four));
            hashMap.put("layout/id6_cusp_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.id6_cusp_fragment_one));
            hashMap.put("layout/id6_cusp_fragment_three_0", Integer.valueOf((int) C0899R.C0902layout.id6_cusp_fragment_three));
            hashMap.put("layout/id6_cusp_fragment_tow_0", Integer.valueOf((int) C0899R.C0902layout.id6_cusp_fragment_tow));
            hashMap.put("layout/id6_fragment_four_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_four));
            hashMap.put("layout-sw600dp-land/id6_fragment_four_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_four));
            hashMap.put("layout/id6_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_one));
            hashMap.put("layout-sw600dp-land/id6_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_one));
            hashMap.put("layout/id6_fragment_three_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_three));
            hashMap.put("layout-sw600dp-land/id6_fragment_three_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_three));
            hashMap.put("layout-sw600dp-land/id6_fragment_tow_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_tow));
            hashMap.put("layout/id6_fragment_tow_0", Integer.valueOf((int) C0899R.C0902layout.id6_fragment_tow));
            hashMap.put("layout-sw600dp-land/id6_one_0", Integer.valueOf((int) C0899R.C0902layout.id6_one));
            hashMap.put("layout/id6_one_0", Integer.valueOf((int) C0899R.C0902layout.id6_one));
            hashMap.put("layout-hdpi-1920x720/id7_app_item_0", Integer.valueOf((int) C0899R.C0902layout.id7_app_item));
            hashMap.put("layout-hdpi-1280x720/id7_app_item_0", Integer.valueOf((int) C0899R.C0902layout.id7_app_item));
            hashMap.put("layout-sw600dp-land/id7_app_item_0", Integer.valueOf((int) C0899R.C0902layout.id7_app_item));
            hashMap.put("layout/id7_app_item_0", Integer.valueOf((int) C0899R.C0902layout.id7_app_item));
            hashMap.put("layout/id7_fragment_car_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_car));
            hashMap.put("layout-sw600dp-land/id7_fragment_car_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_car));
            hashMap.put("layout/id7_fragment_car_hicar_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_car_hicar));
            hashMap.put("layout-sw600dp-land/id7_fragment_media_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_media));
            hashMap.put("layout/id7_fragment_media_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_media));
            hashMap.put("layout/id7_fragment_navi_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_navi));
            hashMap.put("layout-sw600dp-land/id7_fragment_navi_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_navi));
            hashMap.put("layout/id7_fragment_navi_hicar_0", Integer.valueOf((int) C0899R.C0902layout.id7_fragment_navi_hicar));
            hashMap.put("layout/id7_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_car_view));
            hashMap.put("layout-sw600dp-land/id7_sub_car_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_car_view));
            hashMap.put("layout-sw600dp-land/id7_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_dashboard_view));
            hashMap.put("layout/id7_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_dashboard_view));
            hashMap.put("layout/id7_sub_hicar_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_hicar_view));
            hashMap.put("layout-sw600dp-land/id7_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_music_view));
            hashMap.put("layout/id7_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_music_view));
            hashMap.put("layout/id7_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_navi_view));
            hashMap.put("layout-sw600dp-land/id7_sub_navi_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_navi_view));
            hashMap.put("layout/id7_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_phone_view));
            hashMap.put("layout-sw600dp-land/id7_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_phone_view));
            hashMap.put("layout/id7_sub_phone_view_hicar_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_phone_view_hicar));
            hashMap.put("layout/id7_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_video_view));
            hashMap.put("layout-sw600dp-land/id7_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_video_view));
            hashMap.put("layout/id7_sub_weather_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_sub_weather_view));
            hashMap.put("layout/id7_v2_fragment_car_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_car));
            hashMap.put("layout-sw600dp-land/id7_v2_fragment_car_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_car));
            hashMap.put("layout/id7_v2_fragment_media_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_media));
            hashMap.put("layout-sw600dp-land/id7_v2_fragment_media_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_media));
            hashMap.put("layout/id7_v2_fragment_navi_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_navi));
            hashMap.put("layout-sw600dp-land/id7_v2_fragment_navi_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_fragment_navi));
            hashMap.put("layout/id7_v2_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_dashboard_view));
            hashMap.put("layout-sw600dp-land/id7_v2_sub_dashboard_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_dashboard_view));
            hashMap.put("layout-sw600dp-land/id7_v2_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_music_view));
            hashMap.put("layout/id7_v2_sub_music_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_music_view));
            hashMap.put("layout-sw600dp-land/id7_v2_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_phone_view));
            hashMap.put("layout/id7_v2_sub_phone_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_phone_view));
            hashMap.put("layout-sw600dp-land/id7_v2_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_video_view));
            hashMap.put("layout/id7_v2_sub_video_view_0", Integer.valueOf((int) C0899R.C0902layout.id7_v2_sub_video_view));
            hashMap.put("layout/id8_gs_launcher_left_bar_0", Integer.valueOf((int) C0899R.C0902layout.id8_gs_launcher_left_bar));
            hashMap.put("layout/id8_launcher_left_bar_0", Integer.valueOf((int) C0899R.C0902layout.id8_launcher_left_bar));
            hashMap.put("layout/id8_pemp_launcher_left_bar_0", Integer.valueOf((int) C0899R.C0902layout.id8_pemp_launcher_left_bar));
            hashMap.put("layout/include_near_weather_0", Integer.valueOf((int) C0899R.C0902layout.include_near_weather));
            hashMap.put("layout/include_near_weather_edit_0", Integer.valueOf((int) C0899R.C0902layout.include_near_weather_edit));
        }

        private static void internalPopulateLayoutIdLookup1() {
            HashMap<String, Integer> hashMap = sKeys;
            hashMap.put("layout/ksw_id7_main_page1_0", Integer.valueOf((int) C0899R.C0902layout.ksw_id7_main_page1));
            hashMap.put("layout/ksw_id7_main_page2_0", Integer.valueOf((int) C0899R.C0902layout.ksw_id7_main_page2));
            hashMap.put("layout/kswmbux_home_one_0", Integer.valueOf((int) C0899R.C0902layout.kswmbux_home_one));
            hashMap.put("layout/kswmbux_home_three_0", Integer.valueOf((int) C0899R.C0902layout.kswmbux_home_three));
            hashMap.put("layout/kswmbux_home_two_0", Integer.valueOf((int) C0899R.C0902layout.kswmbux_home_two));
            Integer valueOf = Integer.valueOf((int) C0899R.C0902layout.landrover_main);
            hashMap.put("layout-1280x660/landrover_main_0", valueOf);
            hashMap.put("layout-1920x660/landrover_main_0", valueOf);
            hashMap.put("layout/landrover_main_0", valueOf);
            Integer valueOf2 = Integer.valueOf((int) C0899R.C0902layout.landrover_main_bottom_lay);
            hashMap.put("layout-1280x660/landrover_main_bottom_lay_0", valueOf2);
            hashMap.put("layout/landrover_main_bottom_lay_0", valueOf2);
            hashMap.put("layout-1920x660/landrover_main_bottom_lay_0", valueOf2);
            Integer valueOf3 = Integer.valueOf((int) C0899R.C0902layout.landrover_main_fragment_one);
            hashMap.put("layout-1280x660/landrover_main_fragment_one_0", valueOf3);
            hashMap.put("layout/landrover_main_fragment_one_0", valueOf3);
            hashMap.put("layout-1920x660/landrover_main_fragment_one_0", valueOf3);
            Integer valueOf4 = Integer.valueOf((int) C0899R.C0902layout.landrover_main_fragment_two);
            hashMap.put("layout/landrover_main_fragment_two_0", valueOf4);
            hashMap.put("layout-1920x660/landrover_main_fragment_two_0", valueOf4);
            hashMap.put("layout-1280x660/landrover_main_fragment_two_0", valueOf4);
            hashMap.put("layout/lexus_ls_bottom_fragment_one_0", Integer.valueOf((int) C0899R.C0902layout.lexus_ls_bottom_fragment_one));
            hashMap.put("layout/lexus_ls_bottom_fragment_two_0", Integer.valueOf((int) C0899R.C0902layout.lexus_ls_bottom_fragment_two));
            hashMap.put("layout/lexus_ls_bottom_fragment_two_v2_0", Integer.valueOf((int) C0899R.C0902layout.lexus_ls_bottom_fragment_two_v2));
            hashMap.put("layout/lexus_ls_drag_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.lexus_ls_drag_main_layout));
            hashMap.put("layout/lexus_ls_main_layout_0", Integer.valueOf((int) C0899R.C0902layout.lexus_ls_main_layout));
            Integer valueOf5 = Integer.valueOf((int) C0899R.C0902layout.ntg630_control_popup);
            hashMap.put("layout/ntg630_control_popup_0", valueOf5);
            hashMap.put("layout-sw600dp-land/ntg630_control_popup_0", valueOf5);
            Integer valueOf6 = Integer.valueOf((int) C0899R.C0902layout.ntg6_control_popup);
            hashMap.put("layout/ntg6_control_popup_0", valueOf6);
            hashMap.put("layout-sw600dp-land/ntg6_control_popup_0", valueOf6);
            hashMap.put("layout/round_app_item_0", Integer.valueOf((int) C0899R.C0902layout.round_app_item));
            hashMap.put("layout/ug_home_four_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_four));
            hashMap.put("layout/ug_home_four2_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_four2));
            hashMap.put("layout/ug_home_one_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_one));
            hashMap.put("layout/ug_home_one2_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_one2));
            hashMap.put("layout/ug_home_three_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_three));
            hashMap.put("layout/ug_home_three2_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_three2));
            hashMap.put("layout/ug_home_two_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_two));
            hashMap.put("layout/ug_home_two2_0", Integer.valueOf((int) C0899R.C0902layout.ug_home_two2));
        }
    }
}
