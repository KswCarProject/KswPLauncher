package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.FraBmwEvoId6GsThreeBinding;
import java.util.ArrayList;
import java.util.List;

public class Bmwid6gsFragmentThree extends Fragment {
    private FraBmwEvoId6GsThreeBinding binding;
    private List<View> childViews;
    /* access modifiers changed from: private */
    public BmwId6gsViewMode viewMode;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FraBmwEvoId6GsThreeBinding fraBmwEvoId6GsThreeBinding = (FraBmwEvoId6GsThreeBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_three, (ViewGroup) null, false);
        this.binding = fraBmwEvoId6GsThreeBinding;
        View view = fraBmwEvoId6GsThreeBinding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BmwId6gsViewMode bmwId6gsViewMode = (BmwId6gsViewMode) ViewModelProviders.of(getActivity()).get(BmwId6gsViewMode.class);
        this.viewMode = bmwId6gsViewMode;
        this.binding.setVm(bmwId6gsViewMode);
        for (int i = 0; i < this.childViews.size(); i++) {
            final int finalI = i + 8;
            this.childViews.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Bmwid6gsFragmentThree.this.viewMode.setIndex(finalI);
                    Bmwid6gsFragmentThree.this.viewMode.onClick(v, finalI);
                }
            });
        }
    }

    private List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList<>();
        if (view instanceof LinearLayout) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }
}
