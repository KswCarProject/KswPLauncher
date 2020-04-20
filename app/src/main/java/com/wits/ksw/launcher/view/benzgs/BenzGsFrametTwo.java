package com.wits.ksw.launcher.view.benzgs;

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
import com.wits.ksw.databinding.FraBenzgsTwoBinding;
import java.util.ArrayList;
import java.util.List;

public class BenzGsFrametTwo extends Fragment {
    private FraBenzgsTwoBinding binding;
    private List<View> childViews = null;
    /* access modifiers changed from: private */
    public BenzGsViewMoel viewModel;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = (FraBenzgsTwoBinding) DataBindingUtil.inflate(inflater, R.layout.fra_benzgs_two, (ViewGroup) null, false);
        View view = this.binding.getRoot();
        this.childViews = getAllChildViews(view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel = (BenzGsViewMoel) ViewModelProviders.of(getActivity()).get(BenzGsViewMoel.class);
        this.binding.setVm(this.viewModel);
        for (int i = 0; i < this.childViews.size(); i++) {
            final int finalI = i;
            this.childViews.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    BenzGsFrametTwo.this.viewModel.onClick(finalI + 5);
                    BenzGsFrametTwo.this.setSelected(finalI);
                }
            });
        }
    }

    public void setSelected(int index) {
        int i = 0;
        while (i < this.childViews.size()) {
            this.childViews.get(i).setSelected(i == index);
            i++;
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
