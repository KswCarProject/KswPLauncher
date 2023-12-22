package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

/* loaded from: classes16.dex */
public class CustomGridView extends DragGridView {
    private static final String TAG = "KswApplication";
    int column;
    int row;

    public CustomGridView(Context context) {
        this(context, null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.wits.ksw.launcher.view.CustomGridView.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("KswApplication", "onItemSelected: " + position);
                int count = CustomGridView.this.getAdapter().getCount();
                int maxIndex = count - 1;
                if (position >= count) {
                    CustomGridView.this.setSelection(maxIndex);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("KswApplication", "onNothingSelected: ");
            }
        });
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.column = getNumColumns();
        int count = getAdapter().getCount();
        int selectedpostion = getSelectedItemPosition();
        int maxIndex = count - 1;
        this.row = (int) Math.ceil(count / this.column);
        Log.i("KswApplication", "onKeyDown: column= " + this.column + " row = " + this.row);
        Log.i("KswApplication", "onKeyDown: " + keyCode + "  SelectedPosition= " + selectedpostion + "   " + (this.column - 1));
        switch (keyCode) {
            case 19:
            case 21:
                if (selectedpostion >= count) {
                    setSelection(maxIndex);
                    break;
                } else if (selectedpostion % this.column == 0) {
                    if (selectedpostion > 0) {
                        setSelection(selectedpostion - 1);
                        break;
                    }
                } else if (selectedpostion == 0) {
                    setSelection(0);
                    break;
                }
                break;
            case 20:
            case 22:
                if (selectedpostion >= count) {
                    setSelection(maxIndex);
                    break;
                } else {
                    for (int i = 0; i < this.row; i++) {
                        if (selectedpostion % ((this.column * (i + 1)) - 1) == 0) {
                            setSelection(selectedpostion + 1);
                        }
                    }
                    break;
                }
            case 66:
                if (selectedpostion >= count) {
                    setSelection(maxIndex);
                    break;
                }
                break;
        }
        return false;
    }
}
