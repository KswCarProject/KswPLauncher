package com.wits.ksw.launcher.bmw_id8_ui.fragment;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.p001v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.bmw_id8_ui.ID8PempEditActivity;
import com.wits.ksw.launcher.bmw_id8_ui.PempID8LauncherConstants;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnClickListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.CardId8PempOnDragListener;
import com.wits.ksw.launcher.bmw_id8_ui.listener.DragClickListener;
import com.wits.ksw.launcher.utils.AppInfoUtils;

/* loaded from: classes14.dex */
public class ID8PempFragment3rdApp extends Fragment {
    private static final String TAG = "ID8PempFragment3rdApp";
    public static final int iconResId = -1;
    public static final int nameResId = -1;
    private final String cls;
    private final TrdAppRemoveListener listener;
    private final String pkg;
    private final String tag;

    /* loaded from: classes14.dex */
    public interface TrdAppRemoveListener {
        void onTrdAppRemove();
    }

    public ID8PempFragment3rdApp(String tag, TrdAppRemoveListener listener) {
        this.listener = listener;
        this.tag = tag;
        String[] split = tag.substring(4).split(",");
        this.pkg = split[0];
        this.cls = split[1];
    }

    @Override // android.support.p001v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0899R.C0902layout.fragment_pemp_3rd_edit, container, false);
        view.setOnDragListener(new CardId8PempOnDragListener(TAG, this, (ID8PempEditActivity) getActivity()));
        view.setOnLongClickListener(new DragClickListener(view, this.tag, -1, -1));
        view.setOnClickListener(new CardId8PempOnClickListener((ID8PempEditActivity) getActivity()));
        view.setTag(this.tag);
        TextView tvName = (TextView) view.findViewById(C0899R.C0901id.tv_name);
        ImageView iv = (ImageView) view.findViewById(C0899R.C0901id.iv);
        ImageView remove = (ImageView) view.findViewById(C0899R.C0901id.remove);
        ResolveInfo resolveInfo = AppInfoUtils.findAppByPkgAndCls(KswApplication.appContext, this.pkg, this.cls);
        PackageManager pm = KswApplication.appContext.getPackageManager();
        Log.w(TAG, "get3rdAppView: resolveInfo == null" + (resolveInfo == null) + ", pkg : " + this.pkg + ", cls : " + this.cls);
        if (resolveInfo == null) {
            tvName.setText(this.cls);
            iv.setImageDrawable(null);
        } else {
            tvName.setText(resolveInfo.loadLabel(pm).toString());
            iv.setImageDrawable(resolveInfo.loadIcon(pm));
        }
        remove.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.bmw_id8_ui.fragment.ID8PempFragment3rdApp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PempID8LauncherConstants.removeCard(ID8PempFragment3rdApp.this.tag);
                if (ID8PempFragment3rdApp.this.listener != null) {
                    ID8PempFragment3rdApp.this.listener.onTrdAppRemove();
                }
            }
        });
        return view;
    }
}
