package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.RestrictTo;
import android.util.SparseBooleanArray;
import android.widget.TableLayout;
import java.util.regex.Pattern;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class TableLayoutBindingAdapter {
    private static final int MAX_COLUMNS = 20;
    private static Pattern sColumnPattern = Pattern.compile("\\s*,\\s*");

    @BindingAdapter({"android:collapseColumns"})
    public static void setCollapseColumns(TableLayout view, CharSequence columnsStr) {
        SparseBooleanArray columns = parseColumns(columnsStr);
        for (int i = 0; i < 20; i++) {
            boolean isCollapsed = columns.get(i, false);
            if (isCollapsed != view.isColumnCollapsed(i)) {
                view.setColumnCollapsed(i, isCollapsed);
            }
        }
    }

    @BindingAdapter({"android:shrinkColumns"})
    public static void setShrinkColumns(TableLayout view, CharSequence columnsStr) {
        if (columnsStr == null || columnsStr.length() <= 0 || columnsStr.charAt(0) != '*') {
            view.setShrinkAllColumns(false);
            SparseBooleanArray columns = parseColumns(columnsStr);
            int columnCount = columns.size();
            for (int i = 0; i < columnCount; i++) {
                int column = columns.keyAt(i);
                boolean shrinkable = columns.valueAt(i);
                if (shrinkable) {
                    view.setColumnShrinkable(column, shrinkable);
                }
            }
            return;
        }
        view.setShrinkAllColumns(true);
    }

    @BindingAdapter({"android:stretchColumns"})
    public static void setStretchColumns(TableLayout view, CharSequence columnsStr) {
        if (columnsStr == null || columnsStr.length() <= 0 || columnsStr.charAt(0) != '*') {
            view.setStretchAllColumns(false);
            SparseBooleanArray columns = parseColumns(columnsStr);
            int columnCount = columns.size();
            for (int i = 0; i < columnCount; i++) {
                int column = columns.keyAt(i);
                boolean stretchable = columns.valueAt(i);
                if (stretchable) {
                    view.setColumnStretchable(column, stretchable);
                }
            }
            return;
        }
        view.setStretchAllColumns(true);
    }

    private static SparseBooleanArray parseColumns(CharSequence sequence) {
        SparseBooleanArray columns = new SparseBooleanArray();
        if (sequence == null) {
            return columns;
        }
        for (String columnIdentifier : sColumnPattern.split(sequence)) {
            try {
                int columnIndex = Integer.parseInt(columnIdentifier);
                if (columnIndex >= 0) {
                    columns.put(columnIndex, true);
                }
            } catch (NumberFormatException e) {
            }
        }
        return columns;
    }
}
