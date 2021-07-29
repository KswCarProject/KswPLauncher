package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

public class CustomGridView extends DragGridView {
    private static final String TAG = "KSWLauncher";
    int column;
    int row;

    public CustomGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("KSWLauncher", "onItemSelected: " + position);
                int count = CustomGridView.this.getAdapter().getCount();
                int maxIndex = count - 1;
                if (position >= count) {
                    CustomGridView.this.setSelection(maxIndex);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("KSWLauncher", "onNothingSelected: ");
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        this.column = getNumColumns();
        int count = getAdapter().getCount();
        int selectedpostion = getSelectedItemPosition();
        int maxIndex = count - 1;
        this.row = (int) Math.ceil((double) (count / this.column));
        Log.i("KSWLauncher", "onKeyDown: column= " + this.column + " row = " + this.row);
        Log.i("KSWLauncher", "onKeyDown: " + keyCode + "  SelectedPosition= " + selectedpostion + "   " + (this.column - 1));
        switch (keyCode) {
            case 19:
            case 21:
                if (selectedpostion < count) {
                    if (selectedpostion % this.column != 0) {
                        if (selectedpostion == 0) {
                            setSelection(0);
                            break;
                        }
                    } else if (selectedpostion > 0) {
                        setSelection(selectedpostion - 1);
                        break;
                    }
                } else {
                    setSelection(maxIndex);
                    break;
                }
                break;
            case 20:
            case 22:
                if (selectedpostion < count) {
                    for (int i = 0; i < this.row; i++) {
                        if (selectedpostion % ((this.column * (i + 1)) - 1) == 0) {
                            setSelection(selectedpostion + 1);
                        }
                    }
                    break;
                } else {
                    setSelection(maxIndex);
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
