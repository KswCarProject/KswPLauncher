package com.chad.library.adapter.base;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public BaseQuickAdapter adapter;
    private Object associatedObject;
    private final LinkedHashSet<Integer> childClickViewIds = new LinkedHashSet<>();
    @Deprecated
    public View convertView;
    private final LinkedHashSet<Integer> itemChildLongClickViewIds = new LinkedHashSet<>();
    private final HashSet<Integer> nestViews = new HashSet<>();
    private final SparseArray<View> views = new SparseArray<>();

    public Set<Integer> getNestViews() {
        return this.nestViews;
    }

    public BaseViewHolder(View view) {
        super(view);
        this.convertView = view;
    }

    public HashSet<Integer> getItemChildLongClickViewIds() {
        return this.itemChildLongClickViewIds;
    }

    public HashSet<Integer> getChildClickViewIds() {
        return this.childClickViewIds;
    }

    @Deprecated
    public View getConvertView() {
        return this.convertView;
    }

    public BaseViewHolder setText(int viewId, CharSequence value) {
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

    public BaseViewHolder setText(int viewId, int strId) {
        ((TextView) getView(viewId)).setText(strId);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int imageResId) {
        ((ImageView) getView(viewId)).setImageResource(imageResId);
        return this;
    }

    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public BaseViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId, int textColor) {
        ((TextView) getView(viewId)).setTextColor(textColor);
        return this;
    }

    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ((ImageView) getView(viewId)).setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public BaseViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= 11) {
            getView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseViewHolder setGone(int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? 0 : 8);
        return this;
    }

    public BaseViewHolder setVisible(int viewId, boolean visible) {
        getView(viewId).setVisibility(visible ? 0 : 4);
        return this;
    }

    public BaseViewHolder linkify(int viewId) {
        Linkify.addLinks((TextView) getView(viewId), 15);
        return this;
    }

    public BaseViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = (TextView) getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | 128);
        return this;
    }

    public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = (TextView) getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | 128);
        }
        return this;
    }

    public BaseViewHolder setProgress(int viewId, int progress) {
        ((ProgressBar) getView(viewId)).setProgress(progress);
        return this;
    }

    public BaseViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = (ProgressBar) getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public BaseViewHolder setMax(int viewId, int max) {
        ((ProgressBar) getView(viewId)).setMax(max);
        return this;
    }

    public BaseViewHolder setRating(int viewId, float rating) {
        ((RatingBar) getView(viewId)).setRating(rating);
        return this;
    }

    public BaseViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = (RatingBar) getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder addOnClickListener(int... viewIds) {
        for (int viewId : viewIds) {
            this.childClickViewIds.add(Integer.valueOf(viewId));
            View view = getView(viewId);
            if (view != null) {
                if (!view.isClickable()) {
                    view.setClickable(true);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        int position;
                        if (BaseViewHolder.this.adapter.getOnItemChildClickListener() != null && (position = BaseViewHolder.this.getAdapterPosition()) != -1) {
                            BaseViewHolder.this.adapter.getOnItemChildClickListener().onItemChildClick(BaseViewHolder.this.adapter, v, position - BaseViewHolder.this.adapter.getHeaderLayoutCount());
                        }
                    }
                });
            }
        }
        return this;
    }

    public BaseViewHolder setNestView(int... viewIds) {
        for (int viewId : viewIds) {
            this.nestViews.add(Integer.valueOf(viewId));
        }
        addOnClickListener(viewIds);
        addOnLongClickListener(viewIds);
        return this;
    }

    public BaseViewHolder addOnLongClickListener(int... viewIds) {
        for (int viewId : viewIds) {
            this.itemChildLongClickViewIds.add(Integer.valueOf(viewId));
            View view = getView(viewId);
            if (view != null) {
                if (!view.isLongClickable()) {
                    view.setLongClickable(true);
                }
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        int position;
                        if (BaseViewHolder.this.adapter.getOnItemChildLongClickListener() == null || (position = BaseViewHolder.this.getAdapterPosition()) == -1) {
                            return false;
                        }
                        return BaseViewHolder.this.adapter.getOnItemChildLongClickListener().onItemChildLongClick(BaseViewHolder.this.adapter, v, position - BaseViewHolder.this.adapter.getHeaderLayoutCount());
                    }
                });
            }
        }
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        getView(viewId).setOnTouchListener(listener);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        getView(viewId).setOnLongClickListener(listener);
        return this;
    }

    @Deprecated
    public BaseViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        ((AdapterView) getView(viewId)).setOnItemClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        ((AdapterView) getView(viewId)).setOnItemLongClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        ((AdapterView) getView(viewId)).setOnItemSelectedListener(listener);
        return this;
    }

    public BaseViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        ((CompoundButton) getView(viewId)).setOnCheckedChangeListener(listener);
        return this;
    }

    public BaseViewHolder setTag(int viewId, Object tag) {
        getView(viewId).setTag(tag);
        return this;
    }

    public BaseViewHolder setTag(int viewId, int key, Object tag) {
        getView(viewId).setTag(key, tag);
        return this;
    }

    public BaseViewHolder setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(checked);
        }
        return this;
    }

    public BaseViewHolder setEnabled(int viewId, boolean enable) {
        getView(viewId).setEnabled(enable);
        return this;
    }

    public BaseViewHolder setAdapter(int viewId, Adapter adapter2) {
        ((AdapterView) getView(viewId)).setAdapter(adapter2);
        return this;
    }

    /* access modifiers changed from: protected */
    public BaseViewHolder setAdapter(BaseQuickAdapter adapter2) {
        this.adapter = adapter2;
        return this;
    }

    public <T extends View> T getView(int viewId) {
        View view = this.views.get(viewId);
        if (view != null) {
            return view;
        }
        View view2 = this.itemView.findViewById(viewId);
        this.views.put(viewId, view2);
        return view2;
    }

    public Object getAssociatedObject() {
        return this.associatedObject;
    }

    public void setAssociatedObject(Object associatedObject2) {
        this.associatedObject = associatedObject2;
    }
}
