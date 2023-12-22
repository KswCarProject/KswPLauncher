package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.FragmentWeatherEditBinding;
import com.wits.ksw.launcher.bmw_id8_ui.ID8EditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.model.LauncherViewModel;

/* loaded from: classes14.dex */
public class ID8FragmentWeather extends Fragment {
    private static final String TAG = "ID8FragmentWeather";
    public static final int iconResId = 2131233455;
    public static final String name = "WEATHER";
    public static final int nameResId = 2131558827;
    private WeatherRemoveListener listener;
    private FragmentWeatherEditBinding mEditBinding;
    private LauncherViewModel mViewModel;

    /* loaded from: classes14.dex */
    public interface WeatherRemoveListener {
        void onWeatherAppRemove();
    }

    public ID8FragmentWeather(WeatherRemoveListener listener) {
        this.listener = listener;
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mViewModel = (LauncherViewModel) ViewModelProviders.m61of(this).get(LauncherViewModel.class);
        FragmentWeatherEditBinding fragmentWeatherEditBinding = (FragmentWeatherEditBinding) DataBindingUtil.inflate(inflater, C0899R.C0902layout.fragment_weather_edit, container, false);
        this.mEditBinding = fragmentWeatherEditBinding;
        fragmentWeatherEditBinding.setWeatherViewModel(this.mViewModel);
        View view = this.mEditBinding.getRoot();
        view.setOnDragListener(new CardOnDragListener(TAG, this, (ID8EditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, "WEATHER", C0899R.C0900drawable.id8_main_left_icon_weather, C0899R.string.ksw_id8_weather));
        view.setOnClickListener(new CardOnClickListener((ID8EditActivity) getActivity()));
        view.setTag("WEATHER");
        ImageView remove = (ImageView) view.findViewById(C0899R.C0901id.remove);
        remove.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8FragmentWeather.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ID8LauncherConstants.removeCard("WEATHER");
                if (ID8FragmentWeather.this.listener != null) {
                    ID8FragmentWeather.this.listener.onWeatherAppRemove();
                }
            }
        });
        return view;
    }
}