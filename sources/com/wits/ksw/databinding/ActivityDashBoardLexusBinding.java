package com.wits.ksw.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.model.DashboardViewModel;

/* loaded from: classes7.dex */
public abstract class ActivityDashBoardLexusBinding extends ViewDataBinding {
    public final ImageView brakeTextView;
    public final ImageView lexusIvLeft;
    public final ImageView lexusIvRight;
    @Bindable
    protected DashboardViewModel mViewModel;
    public final ImageView seatBeltTextView;

    public abstract void setViewModel(DashboardViewModel viewModel);

    protected ActivityDashBoardLexusBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView brakeTextView, ImageView lexusIvLeft, ImageView lexusIvRight, ImageView seatBeltTextView) {
        super(_bindingComponent, _root, _localFieldCount);
        this.brakeTextView = brakeTextView;
        this.lexusIvLeft = lexusIvLeft;
        this.lexusIvRight = lexusIvRight;
        this.seatBeltTextView = seatBeltTextView;
    }

    public DashboardViewModel getViewModel() {
        return this.mViewModel;
    }

    public static ActivityDashBoardLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLexusBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDashBoardLexusBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_lexus, root, attachToRoot, component);
    }

    public static ActivityDashBoardLexusBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLexusBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDashBoardLexusBinding) ViewDataBinding.inflateInternal(inflater, C0899R.C0902layout.activity_dash_board_lexus, null, false, component);
    }

    public static ActivityDashBoardLexusBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDashBoardLexusBinding bind(View view, Object component) {
        return (ActivityDashBoardLexusBinding) bind(component, view, C0899R.C0902layout.activity_dash_board_lexus);
    }
}
