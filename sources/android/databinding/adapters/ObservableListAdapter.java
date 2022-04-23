package android.databinding.adapters;

import android.content.Context;
import android.databinding.ObservableList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

class ObservableListAdapter<T> extends BaseAdapter {
    private final Context mContext;
    private final int mDropDownResourceId;
    private final LayoutInflater mLayoutInflater;
    private List<T> mList;
    private ObservableList.OnListChangedCallback mListChangedCallback;
    private final int mResourceId;
    private final int mTextViewResourceId;

    public ObservableListAdapter(Context context, List<T> list, int resourceId, int dropDownResourceId, int textViewResourceId) {
        LayoutInflater layoutInflater;
        this.mContext = context;
        this.mResourceId = resourceId;
        this.mDropDownResourceId = dropDownResourceId;
        this.mTextViewResourceId = textViewResourceId;
        if (resourceId == 0) {
            layoutInflater = null;
        } else {
            layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }
        this.mLayoutInflater = layoutInflater;
        setList(list);
    }

    public void setList(List<T> list) {
        List<T> list2 = this.mList;
        if (list2 != list) {
            if (list2 instanceof ObservableList) {
                ((ObservableList) list2).removeOnListChangedCallback(this.mListChangedCallback);
            }
            this.mList = list;
            if (list instanceof ObservableList) {
                if (this.mListChangedCallback == null) {
                    this.mListChangedCallback = new ObservableList.OnListChangedCallback() {
                        public void onChanged(ObservableList observableList) {
                            ObservableListAdapter.this.notifyDataSetChanged();
                        }

                        public void onItemRangeChanged(ObservableList observableList, int i, int i1) {
                            ObservableListAdapter.this.notifyDataSetChanged();
                        }

                        public void onItemRangeInserted(ObservableList observableList, int i, int i1) {
                            ObservableListAdapter.this.notifyDataSetChanged();
                        }

                        public void onItemRangeMoved(ObservableList observableList, int i, int i1, int i2) {
                            ObservableListAdapter.this.notifyDataSetChanged();
                        }

                        public void onItemRangeRemoved(ObservableList observableList, int i, int i1) {
                            ObservableListAdapter.this.notifyDataSetChanged();
                        }
                    };
                }
                ((ObservableList) this.mList).addOnListChangedCallback(this.mListChangedCallback);
            }
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.mList.size();
    }

    public Object getItem(int position) {
        return this.mList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getViewForResource(this.mResourceId, position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getViewForResource(this.mDropDownResourceId, position, convertView, parent);
    }

    public View getViewForResource(int resourceId, int position, View convertView, ViewGroup parent) {
        View view;
        CharSequence value;
        if (convertView == null) {
            if (resourceId == 0) {
                convertView = new TextView(this.mContext);
            } else {
                convertView = this.mLayoutInflater.inflate(resourceId, parent, false);
            }
        }
        int i = this.mTextViewResourceId;
        if (i == 0) {
            view = convertView;
        } else {
            view = convertView.findViewById(i);
        }
        TextView text = (TextView) view;
        T item = this.mList.get(position);
        if (item instanceof CharSequence) {
            value = (CharSequence) item;
        } else {
            value = String.valueOf(item);
        }
        text.setText(value);
        return convertView;
    }
}
