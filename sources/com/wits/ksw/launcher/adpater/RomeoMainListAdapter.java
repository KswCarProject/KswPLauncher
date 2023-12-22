package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.p004v7.widget.LinearLayoutManager;
import android.support.p004v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.C0899R;
import com.wits.ksw.databinding.ActivityRomeoBinding;
import com.wits.ksw.launcher.model.RomeoViewModel;

/* loaded from: classes11.dex */
public class RomeoMainListAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private RomeoViewModel bViewModel;
    private ActivityRomeoBinding binding;
    private int downPosition;
    private int firstPosition;
    private int lastPosition;
    private LinearLayoutManager layoutManager;
    private Context mContext;
    protected LayoutInflater mInflater;
    private String TAG = RomeoMainListAdapter.class.getSimpleName();
    private int lastSelectPosition = -1;
    int count = 0;

    public void setBinding(ActivityRomeoBinding binding) {
        this.binding = binding;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RomeoMainListAdapter(Context context, RomeoViewModel bViewModel) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.bViewModel = bViewModel;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0899R.C0902layout.romeo_main_adapter, viewGroup, false));
        return holder;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        switch (position) {
            case 0:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_gps);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.ksw_id7_navi));
                break;
            case 1:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_music);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.ksw_id7_music));
                break;
            case 2:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_video);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.video));
                break;
            case 3:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_bt);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.id6_phone));
                break;
            case 4:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_app);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.ksw_id7_apps));
                break;
            case 5:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_set);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.ksw_id7_setting));
                break;
            case 6:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_car);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.ksw_id7_car_info));
                break;
            case 7:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_dashboard);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.romeo_id7_dashboard_lable));
                break;
            case 8:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_dvr);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.function_text8));
                break;
            case 9:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_phonelink);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.id6_shouj_hulian));
                break;
            case 10:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_eq);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.item3));
                break;
            case 11:
                viewHolder.romeo_main_iv.setImageResource(C0899R.C0900drawable.romeo_main_icon_file);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(C0899R.string.id6_filemanager_mess));
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.adpater.RomeoMainListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Log.d(RomeoMainListAdapter.this.TAG, "onClick " + position + " top " + viewHolder.itemView.getTop());
                RomeoMainListAdapter.this.lastSelectPosition = position;
            }
        });
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.adpater.RomeoMainListAdapter.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("RomeoMain", "onFocusChange " + hasFocus + " position=" + position);
                if (hasFocus) {
                    RomeoMainListAdapter.this.changeItemSelect(viewHolder.itemView.getTop(), 1);
                    int i = position;
                    if (i == 6) {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) RomeoMainListAdapter.this.binding.romeoMainRv.getLayoutManager();
                        layoutManager.scrollToPositionWithOffset(6, 0);
                        layoutManager.setStackFromEnd(true);
                    } else if (i == 5) {
                        LinearLayoutManager layoutManager2 = (LinearLayoutManager) RomeoMainListAdapter.this.binding.romeoMainRv.getLayoutManager();
                        layoutManager2.scrollToPositionWithOffset(0, 0);
                        layoutManager2.setStackFromEnd(true);
                    }
                    Log.d(RomeoMainListAdapter.this.TAG, "onFocusChange/ " + position + " top " + viewHolder.itemView.getTop());
                    RomeoMainListAdapter.this.lastSelectPosition = position;
                }
            }
        });
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: com.wits.ksw.launcher.adpater.RomeoMainListAdapter.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(RomeoMainListAdapter.this.TAG, "onTouch " + position + " action " + event.getAction() + " pressed " + viewHolder.itemView.isPressed());
                if (event.getAction() == 0) {
                    RomeoMainListAdapter.this.downPosition = position;
                    RomeoMainListAdapter.this.changeItemSelect(viewHolder.itemView.getTop(), 2);
                    return false;
                } else if (event.getAction() == 1) {
                    RomeoMainListAdapter.this.clearSelect(1);
                    RomeoMainListAdapter.this.openApp(position);
                    return false;
                } else if (event.getAction() != 2) {
                    event.getAction();
                    return false;
                } else {
                    return false;
                }
            }
        });
        viewHolder.itemView.setOnKeyListener(new View.OnKeyListener() { // from class: com.wits.ksw.launcher.adpater.RomeoMainListAdapter.4
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(RomeoMainListAdapter.this.TAG, "onKey " + keyEvent.getKeyCode());
                if (keyEvent.getKeyCode() == 23 || keyEvent.getKeyCode() == 66) {
                    RomeoMainListAdapter.this.clearSelect(2);
                    RomeoMainListAdapter.this.openApp(position);
                    return false;
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openApp(int index) {
        switch (index) {
            case 0:
                this.bViewModel.openNaviApp();
                return;
            case 1:
                this.bViewModel.openMusicMulti(null);
                return;
            case 2:
                this.bViewModel.openVideoMulti(null);
                return;
            case 3:
                this.bViewModel.openBtApp(null);
                return;
            case 4:
                this.bViewModel.openAllApp(null);
                return;
            case 5:
                this.bViewModel.openSettings(null);
                return;
            case 6:
                this.bViewModel.openCar(null);
                return;
            case 7:
                this.bViewModel.openDashboard(null);
                return;
            case 8:
                this.bViewModel.openDvr(null);
                return;
            case 9:
                this.bViewModel.openShouJiHuLian(null);
                return;
            case 10:
                this.bViewModel.openEq(null);
                return;
            case 11:
                this.bViewModel.openFileManager(null);
                return;
            default:
                return;
        }
    }

    public void changeSelectItem() {
        LinearLayoutManager linearLayoutManager;
        if (this.lastSelectPosition >= 0 && (linearLayoutManager = this.layoutManager) != null) {
            this.firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
            this.lastPosition = this.layoutManager.findLastVisibleItemPosition();
            Log.d(this.TAG, "firstPosition " + this.firstPosition + " lastPosition: " + this.lastPosition + " lastSelect " + this.lastSelectPosition);
            int i = this.lastPosition;
            int i2 = this.firstPosition;
            if (i - i2 == 5) {
                int i3 = this.lastSelectPosition;
                if (i3 <= i && i3 >= i2) {
                    this.layoutManager.getChildAt(i3 - i2).getTop();
                    return;
                }
                return;
            }
            int i4 = this.lastSelectPosition;
        }
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.support.p004v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 12;
    }

    /* loaded from: classes11.dex */
    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView romeo_main_iv;
        public TextView romeo_main_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.romeo_main_tv = (TextView) itemView.findViewById(C0899R.C0901id.romeo_main_tv);
            this.romeo_main_iv = (ImageView) itemView.findViewById(C0899R.C0901id.romeo_main_iv);
        }

        public ViewDataBinding getBinding() {
            return RomeoMainListAdapter.this.binding;
        }
    }

    private void resetItemBg() {
        this.binding.romeoIndicator1.setVisibility(8);
        this.binding.romeoIndicator2.setVisibility(8);
        this.binding.romeoIndicator3.setVisibility(8);
        this.binding.romeoIndicator4.setVisibility(8);
        this.binding.romeoIndicator5.setVisibility(8);
        this.binding.romeoIndicator6.setVisibility(8);
        this.binding.romeoNavi.getDrawable().setLevel(0);
        this.binding.romeoMusic.getDrawable().setLevel(0);
        this.binding.romeoVideo.getDrawable().setLevel(0);
        this.binding.romeoPhone.getDrawable().setLevel(0);
        this.binding.romeoApp.getDrawable().setLevel(0);
        this.binding.romeoSetting.getDrawable().setLevel(0);
    }

    private void changeItemPress(int position) {
    }

    public void clearSelect(int type) {
        if (this.binding.romeoNavi.getDrawable().getLevel() == type) {
            this.binding.romeoNavi.getDrawable().setLevel(0);
            this.binding.romeoIndicator1.setVisibility(8);
        }
        if (this.binding.romeoMusic.getDrawable().getLevel() == type) {
            this.binding.romeoMusic.getDrawable().setLevel(0);
            this.binding.romeoIndicator2.setVisibility(8);
        }
        if (this.binding.romeoVideo.getDrawable().getLevel() == type) {
            this.binding.romeoVideo.getDrawable().setLevel(0);
            this.binding.romeoIndicator3.setVisibility(8);
        }
        if (this.binding.romeoPhone.getDrawable().getLevel() == type) {
            this.binding.romeoPhone.getDrawable().setLevel(0);
            this.binding.romeoIndicator4.setVisibility(8);
        }
        if (this.binding.romeoApp.getDrawable().getLevel() == type) {
            this.binding.romeoApp.getDrawable().setLevel(0);
            this.binding.romeoIndicator5.setVisibility(8);
        }
        if (this.binding.romeoSetting.getDrawable().getLevel() == type) {
            this.binding.romeoSetting.getDrawable().setLevel(0);
            this.binding.romeoIndicator6.setVisibility(8);
        }
    }

    public void changeItemSelect(int top, int type) {
        Log.d(this.TAG, " lastSelectPosition " + this.lastSelectPosition + " top " + top + " type " + type);
        ActivityRomeoBinding activityRomeoBinding = this.binding;
        if (activityRomeoBinding == null) {
            return;
        }
        activityRomeoBinding.romeoIndicator1.setVisibility(8);
        this.binding.romeoIndicator2.setVisibility(8);
        this.binding.romeoIndicator3.setVisibility(8);
        this.binding.romeoIndicator4.setVisibility(8);
        this.binding.romeoIndicator5.setVisibility(8);
        this.binding.romeoIndicator6.setVisibility(8);
        Log.d(this.TAG, "binding.romeoNavi=" + this.binding.romeoNavi + " getDrawable=" + this.binding.romeoNavi.getDrawable());
        if (this.binding.romeoNavi.getDrawable().getLevel() == type) {
            this.binding.romeoNavi.getDrawable().setLevel(0);
        }
        if (this.binding.romeoMusic.getDrawable().getLevel() == type) {
            this.binding.romeoMusic.getDrawable().setLevel(0);
        }
        if (this.binding.romeoVideo.getDrawable().getLevel() == type) {
            this.binding.romeoVideo.getDrawable().setLevel(0);
        }
        if (this.binding.romeoPhone.getDrawable().getLevel() == type) {
            this.binding.romeoPhone.getDrawable().setLevel(0);
        }
        if (this.binding.romeoApp.getDrawable().getLevel() == type) {
            this.binding.romeoApp.getDrawable().setLevel(0);
        }
        if (this.binding.romeoSetting.getDrawable().getLevel() == type) {
            this.binding.romeoSetting.getDrawable().setLevel(0);
        }
        if (top < 0) {
            return;
        }
        if (top < 107) {
            this.binding.romeoNavi.getDrawable().setLevel(type);
            this.binding.romeoIndicator1.setVisibility(0);
        } else if (top < 214) {
            this.binding.romeoMusic.getDrawable().setLevel(type);
            this.binding.romeoIndicator2.setVisibility(0);
        } else if (top < 321) {
            this.binding.romeoVideo.getDrawable().setLevel(type);
            this.binding.romeoIndicator3.setVisibility(0);
        } else if (top < 428) {
            this.binding.romeoPhone.getDrawable().setLevel(type);
            this.binding.romeoIndicator4.setVisibility(0);
        } else if (top < 535) {
            this.binding.romeoApp.getDrawable().setLevel(type);
            this.binding.romeoIndicator5.setVisibility(0);
        } else if (top < 642) {
            this.binding.romeoSetting.getDrawable().setLevel(type);
            this.binding.romeoIndicator6.setVisibility(0);
        }
    }
}
