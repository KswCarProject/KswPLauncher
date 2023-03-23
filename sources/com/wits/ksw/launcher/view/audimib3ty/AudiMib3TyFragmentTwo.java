package com.wits.ksw.launcher.view.audimib3ty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wits.ksw.R;
import com.wits.ksw.databinding.AudiMib3TyTwoBinding;
import com.wits.ksw.launcher.model.BcVieModel;
import com.wits.ksw.launcher.model.McuImpl;
import com.wits.ksw.launcher.view.benzmbux.BenzMbuxItemView;

public class AudiMib3TyFragmentTwo extends AudiMib3TyBaseFragment implements View.OnKeyListener {
    public static final String TAG = "AudiMib3TyFragmentTwo - Two";
    public static AudiMib3TyTwoBinding bindingTwo;
    private View.OnClickListener mItemClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.app_itemview /*2131296342*/:
                    BcVieModel bcVieModel = AudiMib3TyFragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentTwo.bindingTwo.appItemview;
                    AudiMib3TyFragmentTwo.setItemSelected(AudiMib3TyFragmentTwo.bindingTwo.appItemview);
                    AudiMib3TyFragmentTwo.this.viewModel.openApps(v);
                    return;
                case R.id.car_itemview /*2131296693*/:
                    BcVieModel bcVieModel2 = AudiMib3TyFragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentTwo.bindingTwo.carItemview;
                    AudiMib3TyFragmentTwo.setItemSelected(AudiMib3TyFragmentTwo.bindingTwo.carItemview);
                    AudiMib3TyFragmentTwo.this.viewModel.openCar(v);
                    return;
                case R.id.dashboard_itemview /*2131296776*/:
                    BcVieModel bcVieModel3 = AudiMib3TyFragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentTwo.bindingTwo.dashboardItemview;
                    AudiMib3TyFragmentTwo.setItemSelected(AudiMib3TyFragmentTwo.bindingTwo.dashboardItemview);
                    AudiMib3TyFragmentTwo.this.viewModel.openDashboard(v);
                    return;
                case R.id.dvr_itemview /*2131296820*/:
                    BcVieModel bcVieModel4 = AudiMib3TyFragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentTwo.bindingTwo.dvrItemview;
                    AudiMib3TyFragmentTwo.setItemSelected(AudiMib3TyFragmentTwo.bindingTwo.dvrItemview);
                    AudiMib3TyFragmentTwo.this.viewModel.openDvr(v);
                    return;
                case R.id.set_itemview /*2131297701*/:
                    BcVieModel bcVieModel5 = AudiMib3TyFragmentTwo.this.viewModel;
                    BcVieModel.viewLastSel = AudiMib3TyFragmentTwo.bindingTwo.setItemview;
                    AudiMib3TyFragmentTwo.setItemSelected(AudiMib3TyFragmentTwo.bindingTwo.setItemview);
                    AudiMib3TyFragmentTwo.this.viewModel.openSettings(v);
                    return;
                default:
                    return;
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AudiMib3TyTwoBinding audiMib3TyTwoBinding = (AudiMib3TyTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_audi_mib3_ty_two, (ViewGroup) null, false);
        bindingTwo = audiMib3TyTwoBinding;
        return audiMib3TyTwoBinding.getRoot();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingTwo.setViewModel(this.viewModel);
        bindingTwo.carItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.carIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.carTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dashboardTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.appItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.appIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.appTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.dvrTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.setItemview.setOnClickListener(this.mItemClickListener);
        bindingTwo.setIv.setOnClickListener(this.mItemClickListener);
        bindingTwo.setTv.setOnClickListener(this.mItemClickListener);
        bindingTwo.carItemview.setOnKeyListener(this);
        bindingTwo.dashboardItemview.setOnKeyListener(this);
        bindingTwo.appItemview.setOnKeyListener(this);
        bindingTwo.dvrItemview.setOnKeyListener(this);
        bindingTwo.setItemview.setOnKeyListener(this);
    }

    public static void setItemSelected(View view) {
        boolean z = true;
        bindingTwo.dvrItemview.setSelected(bindingTwo.dvrItemview == view);
        bindingTwo.dashboardItemview.setSelected(bindingTwo.dashboardItemview == view);
        bindingTwo.carItemview.setSelected(bindingTwo.carItemview == view);
        bindingTwo.appItemview.setSelected(bindingTwo.appItemview == view);
        BenzMbuxItemView benzMbuxItemView = bindingTwo.setItemview;
        if (bindingTwo.setItemview != view) {
            z = false;
        }
        benzMbuxItemView.setSelected(z);
    }

    public void setDefaultSelected() {
        try {
            setItemSelected(bindingTwo.carItemview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onResume() {
        super.onResume();
        Log.d("AudiMib3TyFragmentTwo", "onResume() 2222222222 ");
        this.viewModel.refreshLastSel();
        if (bindingTwo != null) {
            bindingTwo.dashboardContent.setText(String.format(getResources().getString(R.string.oil_size), new Object[]{McuImpl.getInstance().getCarInfo().oilValue.get()}));
        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == 0) {
            Log.i(TAG, "onKey: " + keyCode);
            if (keyCode == 20 || keyCode == 22) {
                if (v == bindingTwo.carItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.appItemview);
                } else if (v == bindingTwo.appItemview) {
                    setItemSelected(bindingTwo.dvrItemview);
                } else if (v == bindingTwo.dvrItemview) {
                    setItemSelected(bindingTwo.setItemview);
                } else if (v == bindingTwo.setItemview) {
                    setItemSelected(bindingTwo.setItemview);
                    return true;
                }
            } else if (keyCode == 19 || keyCode == 21) {
                if (v == bindingTwo.setItemview) {
                    setItemSelected(bindingTwo.dvrItemview);
                } else if (v == bindingTwo.dvrItemview) {
                    setItemSelected(bindingTwo.appItemview);
                } else if (v == bindingTwo.appItemview) {
                    setItemSelected(bindingTwo.dashboardItemview);
                } else if (v == bindingTwo.dashboardItemview) {
                    setItemSelected(bindingTwo.carItemview);
                } else if (v == bindingTwo.carItemview && this.mainActivity.audiMib3TyViewPager.getCurrentItem() != 0) {
                    this.mainActivity.audiMib3TyViewPager.setCurrentItem(0);
                    AudiMib3TyFragmentOne.bindingOne.phonelinkItemview.requestFocus();
                    isSmooth = true;
                    return true;
                }
            }
        }
        return false;
    }
}
