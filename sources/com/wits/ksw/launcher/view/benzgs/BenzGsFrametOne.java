package com.wits.ksw.launcher.view.benzgs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.FraBenzgsOneBinding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BenzGsFrametOne extends Fragment {
    private static final String TAG = "KswApplication." + BenzGsFrametOne.class.getSimpleName();
    private FraBenzgsOneBinding binding;
    private List<View> childViews = null;
    private BenzGsViewMoel viewModel;

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FraBenzgsOneBinding fraBenzgsOneBinding = (FraBenzgsOneBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fra_benzgs_one, null, false);
        this.binding = fraBenzgsOneBinding;
        View view = fraBenzgsOneBinding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    @Override // android.support.p001v4.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BenzGsViewMoel benzGsViewMoel = (BenzGsViewMoel) ViewModelProviders.m59of(getActivity()).get(BenzGsViewMoel.class);
        this.viewModel = benzGsViewMoel;
        this.binding.setVm(benzGsViewMoel);
        for (int i = 0; i < this.childViews.size(); i++) {
            View childView = this.childViews.get(i);
            final int finalI = i;
            childView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.benzgs.BenzGsFrametOne.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    BenzGsFrametOne.this.viewModel.onClick(finalI);
                    BenzGsFrametOne.this.setSelected(finalI);
                }
            });
        }
    }

    public void setSelected(View view) {
        Iterator<View> it = this.childViews.iterator();
        while (it.hasNext()) {
            View childView = it.next();
            childView.setSelected(childView == view);
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

    public void setSelected(int index) {
        int i = 0;
        while (i < this.childViews.size()) {
            View childView = this.childViews.get(i);
            childView.setSelected(i == index);
            i++;
        }
    }
}
