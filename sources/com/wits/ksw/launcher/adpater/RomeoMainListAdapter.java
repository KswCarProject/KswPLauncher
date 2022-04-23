package com.wits.ksw.launcher.adpater;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wits.ksw.R;
import com.wits.ksw.databinding.ActivityRomeoBinding;
import com.wits.ksw.launcher.model.RomeoViewModel;

public class RomeoMainListAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    /* access modifiers changed from: private */
    public String TAG = RomeoMainListAdapter.class.getSimpleName();
    private RomeoViewModel bViewModel;
    /* access modifiers changed from: private */
    public ActivityRomeoBinding binding;
    int count = 0;
    /* access modifiers changed from: private */
    public int downPosition;
    private int firstPosition;
    private int lastPosition;
    /* access modifiers changed from: private */
    public int lastSelectPosition = -1;
    private LinearLayoutManager layoutManager;
    private Context mContext;
    protected LayoutInflater mInflater;

    public void setBinding(ActivityRomeoBinding binding2) {
        this.binding = binding2;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager2) {
        this.layoutManager = layoutManager2;
    }

    public RomeoMainListAdapter(Context context, RomeoViewModel bViewModel2) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.bViewModel = bViewModel2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.romeo_main_adapter, viewGroup, false));
    }

    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        switch (position) {
            case 0:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_gps);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.ksw_id7_navi));
                break;
            case 1:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_music);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.ksw_id7_music));
                break;
            case 2:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_video);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.video));
                break;
            case 3:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_bt);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.id6_phone));
                break;
            case 4:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_app);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.ksw_id7_apps));
                break;
            case 5:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_set);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.ksw_id7_setting));
                break;
            case 6:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_car);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.ksw_id7_car_info));
                break;
            case 7:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_dashboard);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.romeo_id7_dashboard_lable));
                break;
            case 8:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_dvr);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.function_text8));
                break;
            case 9:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_phonelink);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.id6_shouj_hulian));
                break;
            case 10:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_eq);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.item3));
                break;
            case 11:
                viewHolder.romeo_main_iv.setImageResource(R.drawable.romeo_main_icon_file);
                viewHolder.romeo_main_tv.setText(this.mContext.getResources().getString(R.string.id6_filemanager_mess));
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(RomeoMainListAdapter.this.TAG, "onClick " + position + " top " + viewHolder.itemView.getTop());
                int unused = RomeoMainListAdapter.this.lastSelectPosition = position;
            }
        });
        viewHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                    int unused = RomeoMainListAdapter.this.lastSelectPosition = position;
                }
            }
        });
        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(RomeoMainListAdapter.this.TAG, "onTouch " + position + " action " + event.getAction() + " pressed " + viewHolder.itemView.isPressed());
                if (event.getAction() == 0) {
                    int unused = RomeoMainListAdapter.this.downPosition = position;
                    RomeoMainListAdapter.this.changeItemSelect(viewHolder.itemView.getTop(), 2);
                    return false;
                } else if (event.getAction() == 1) {
                    RomeoMainListAdapter.this.clearSelect(1);
                    RomeoMainListAdapter.this.openApp(position);
                    return false;
                } else if (event.getAction() == 2) {
                    return false;
                } else {
                    event.getAction();
                    return false;
                }
            }
        });
        viewHolder.itemView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(RomeoMainListAdapter.this.TAG, "onKey " + keyEvent.getKeyCode());
                if (keyEvent.getKeyCode() != 23 && keyEvent.getKeyCode() != 66) {
                    return false;
                }
                RomeoMainListAdapter.this.clearSelect(2);
                RomeoMainListAdapter.this.openApp(position);
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void openApp(int index) {
        switch (index) {
            case 0:
                this.bViewModel.openNaviApp();
                return;
            case 1:
                this.bViewModel.openMusicMulti((View) null);
                return;
            case 2:
                this.bViewModel.openVideoMulti((View) null);
                return;
            case 3:
                this.bViewModel.openBtApp((View) null);
                return;
            case 4:
                this.bViewModel.openAllApp((View) null);
                return;
            case 5:
                this.bViewModel.openSettings((View) null);
                return;
            case 6:
                this.bViewModel.openCar((View) null);
                return;
            case 7:
                this.bViewModel.openDashboard((View) null);
                return;
            case 8:
                this.bViewModel.openDvr((View) null);
                return;
            case 9:
                this.bViewModel.openShouJiHuLian((View) null);
                return;
            case 10:
                this.bViewModel.openEq((View) null);
                return;
            case 11:
                this.bViewModel.openFileManager((View) null);
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

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemCount() {
        return 12;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView romeo_main_iv;
        public TextView romeo_main_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.romeo_main_tv = (TextView) itemView.findViewById(R.id.romeo_main_tv);
            this.romeo_main_iv = (ImageView) itemView.findViewById(R.id.romeo_main_iv);
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
        if (activityRomeoBinding != null) {
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
            if (top >= 0) {
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
    }
}
