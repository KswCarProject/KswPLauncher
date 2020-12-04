package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.R;
import com.wits.ksw.launcher.model.DashboardViewModel;

public abstract class ActivityDashBoardLexusBinding extends ViewDataBinding {
    @NonNull
    public final ImageView brakeTextView;
    @NonNull
    public final ImageView lexusIvLeft;
    @NonNull
    public final ImageView lexusIvRight;
    @Bindable
    protected DashboardViewModel mViewModel;
    @NonNull
    public final ImageView seatBeltTextView;

    public abstract void setViewModel(@Nullable DashboardViewModel dashboardViewModel);

    protected ActivityDashBoardLexusBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount, ImageView brakeTextView2, ImageView lexusIvLeft2, ImageView lexusIvRight2, ImageView seatBeltTextView2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView2;
        this.lexusIvLeft = lexusIvLeft2;
        this.lexusIvRight = lexusIvRight2;
        this.seatBeltTextView = seatBeltTextView2;
    }

    @Nullable
    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    @NonNull
    public static ActivityDashBoardLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDashBoardLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
        return (ActivityDashBoardLexusBinding) DataBindingUtil.inflate(inflater, R.layout.activity_dash_board_lexus, root, attachToRoot, component);
    }

    @NonNull
    public static ActivityDashBoardLexusBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityDashBoardLexusBinding inflate(@NonNull LayoutInflater inflater, @Nullable DataBindingComponent component) {
        return (ActivityDashBoardLexusBinding) DataBindingUtil.inflate(inflater, R.layout.activity_dash_board_lexus, (ViewGroup) null, false, component);
    }

    public static ActivityDashBoardLexusBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityDashBoardLexusBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
        return (ActivityDashBoardLexusBinding) bind(component, view, R.layout.activity_dash_board_lexus);
    }
}
