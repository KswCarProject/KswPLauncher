package com.wits.ksw.launcher.view.benzgs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wits.ksw.R;
import com.wits.ksw.databinding.FraBenzgsOneBinding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BenzGsFrametOne extends Fragment {
    private static final String TAG = ("KswApplication." + BenzGsFrametOne.class.getSimpleName());
    private FraBenzgsOneBinding binding;
    private List<View> childViews = null;
    /* access modifiers changed from: private */
    public BenzGsViewMoel viewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FraBenzgsOneBinding fraBenzgsOneBinding = (FraBenzgsOneBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_one, (ViewGroup) null, false);
        this.binding = fraBenzgsOneBinding;
        View view = fraBenzgsOneBinding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BenzGsViewMoel benzGsViewMoel = (BenzGsViewMoel) ViewModelProviders.of(getActivity()).get(BenzGsViewMoel.class);
        this.viewModel = benzGsViewMoel;
        this.binding.setVm(benzGsViewMoel);
        for (int i = 0; i < this.childViews.size(); i++) {
            final int finalI = i;
            this.childViews.get(i).setOnClickListener(new View.OnClickListener() {
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
            this.childViews.get(i).setSelected(i == index);
            i++;
        }
    }
}
