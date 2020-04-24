package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.FraBmwEvoId6GsTwoBinding;
import java.util.ArrayList;
import java.util.List;

public class Bmwid6gsFragmentTwo extends Fragment {
    private FraBmwEvoId6GsTwoBinding binding;
    private List<View> childViews = null;
    /* access modifiers changed from: private */
    public BmwId6gsViewMode viewMode;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (FraBmwEvoId6GsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_bmw_evo_id6_gs_two, (ViewGroup) null, false);
        View view = this.binding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewMode = (BmwId6gsViewMode) ViewModelProviders.of(getActivity()).get(BmwId6gsViewMode.class);
        this.binding.setVm(this.viewMode);
        for (int i = 0; i < this.childViews.size(); i++) {
            final int finalI = i + 4;
            this.childViews.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Bmwid6gsFragmentTwo.this.viewMode.setIndex(finalI);
                    Bmwid6gsFragmentTwo.this.viewMode.onClick(v, finalI);
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
