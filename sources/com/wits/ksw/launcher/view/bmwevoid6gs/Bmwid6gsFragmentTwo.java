package com.wits.ksw.launcher.view.bmwevoid6gs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.FraBmwEvoId6GsTwoBinding;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class Bmwid6gsFragmentTwo extends Fragment {
    private FraBmwEvoId6GsTwoBinding binding;
    private List<View> childViews = null;
    private BmwId6gsViewMode viewMode;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FraBmwEvoId6GsTwoBinding fraBmwEvoId6GsTwoBinding = (FraBmwEvoId6GsTwoBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fra_bmw_evo_id6_gs_two, null, false);
        this.binding = fraBmwEvoId6GsTwoBinding;
        View view = fraBmwEvoId6GsTwoBinding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BmwId6gsViewMode bmwId6gsViewMode = (BmwId6gsViewMode) ViewModelProviders.m59of(getActivity()).get(BmwId6gsViewMode.class);
        this.viewMode = bmwId6gsViewMode;
        this.binding.setVm(bmwId6gsViewMode);
        for (int i = 0; i < this.childViews.size(); i++) {
            View childView = this.childViews.get(i);
            final int finalI = i + 4;
            childView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentTwo.1
                @Override // android.view.View.OnClickListener
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
