package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import com.ibm.icu.text.PluralRules;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import skin.support.exception.SkinCompatException;
import skin.support.utils.Slog;

public final class ColorState {
    private static final String TAG = "ColorState";
    String colorAccelerated;
    String colorActivated;
    String colorChecked;
    String colorDefault;
    String colorDragCanAccept;
    String colorDragHovered;
    String colorEnabled;
    String colorFocused;
    String colorHovered;
    String colorName;
    String colorPressed;
    String colorSelected;
    String colorWindowFocused;
    boolean onlyDefaultColor;

    ColorState(String colorWindowFocused2, String colorSelected2, String colorFocused2, String colorEnabled2, String colorPressed2, String colorChecked2, String colorActivated2, String colorAccelerated2, String colorHovered2, String colorDragCanAccept2, String colorDragHovered2, String colorDefault2) {
        this.colorWindowFocused = colorWindowFocused2;
        this.colorSelected = colorSelected2;
        this.colorFocused = colorFocused2;
        this.colorEnabled = colorEnabled2;
        this.colorPressed = colorPressed2;
        this.colorChecked = colorChecked2;
        this.colorActivated = colorActivated2;
        this.colorAccelerated = colorAccelerated2;
        this.colorHovered = colorHovered2;
        this.colorDragCanAccept = colorDragCanAccept2;
        this.colorDragHovered = colorDragHovered2;
        this.colorDefault = colorDefault2;
        boolean z = TextUtils.isEmpty(colorWindowFocused2) && TextUtils.isEmpty(colorSelected2) && TextUtils.isEmpty(colorFocused2) && TextUtils.isEmpty(colorEnabled2) && TextUtils.isEmpty(colorPressed2) && TextUtils.isEmpty(colorChecked2) && TextUtils.isEmpty(colorActivated2) && TextUtils.isEmpty(colorAccelerated2) && TextUtils.isEmpty(colorHovered2) && TextUtils.isEmpty(colorDragCanAccept2) && TextUtils.isEmpty(colorDragHovered2);
        this.onlyDefaultColor = z;
        if (z && !colorDefault2.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    ColorState(String colorName2, String colorDefault2) {
        this.colorName = colorName2;
        this.colorDefault = colorDefault2;
        this.onlyDefaultColor = true;
        if (!colorDefault2.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    public boolean isOnlyDefaultColor() {
        return this.onlyDefaultColor;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getColorWindowFocused() {
        return this.colorWindowFocused;
    }

    public String getColorSelected() {
        return this.colorSelected;
    }

    public String getColorFocused() {
        return this.colorFocused;
    }

    public String getColorEnabled() {
        return this.colorEnabled;
    }

    public String getColorPressed() {
        return this.colorPressed;
    }

    public String getColorChecked() {
        return this.colorChecked;
    }

    public String getColorActivated() {
        return this.colorActivated;
    }

    public String getColorAccelerated() {
        return this.colorAccelerated;
    }

    public String getColorHovered() {
        return this.colorHovered;
    }

    public String getColorDragCanAccept() {
        return this.colorDragCanAccept;
    }

    public String getColorDragHovered() {
        return this.colorDragHovered;
    }

    public String getColorDefault() {
        return this.colorDefault;
    }

    /* access modifiers changed from: package-private */
    public ColorStateList parse() {
        if (this.onlyDefaultColor) {
            return ColorStateList.valueOf(Color.parseColor(this.colorDefault));
        }
        return parseAll();
    }

    private ColorStateList parseAll() {
        int stateColorCount = 0;
        List<int[]> stateSetList = new ArrayList<>();
        List<Integer> stateColorList = new ArrayList<>();
        if (!TextUtils.isEmpty(this.colorWindowFocused)) {
            try {
                String windowFocusedColorStr = getColorStr(this.colorWindowFocused);
                if (!TextUtils.isEmpty(windowFocusedColorStr)) {
                    int windowFocusedColorInt = Color.parseColor(windowFocusedColorStr);
                    stateSetList.add(SkinCompatThemeUtils.WINDOW_FOCUSED_STATE_SET);
                    stateColorList.add(Integer.valueOf(windowFocusedColorInt));
                    stateColorCount = 0 + 1;
                }
            } catch (Exception e) {
            }
        }
        if (!TextUtils.isEmpty(this.colorSelected)) {
            try {
                String colorSelectedStr = getColorStr(this.colorSelected);
                if (!TextUtils.isEmpty(colorSelectedStr)) {
                    int selectedColorInt = Color.parseColor(colorSelectedStr);
                    stateSetList.add(SkinCompatThemeUtils.SELECTED_STATE_SET);
                    stateColorList.add(Integer.valueOf(selectedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e2) {
            }
        }
        if (!TextUtils.isEmpty(this.colorFocused)) {
            try {
                String colorFocusedStr = getColorStr(this.colorFocused);
                if (!TextUtils.isEmpty(colorFocusedStr)) {
                    int focusedColorInt = Color.parseColor(colorFocusedStr);
                    stateSetList.add(SkinCompatThemeUtils.FOCUSED_STATE_SET);
                    stateColorList.add(Integer.valueOf(focusedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e3) {
            }
        }
        if (!TextUtils.isEmpty(this.colorEnabled)) {
            try {
                String colorEnabledStr = getColorStr(this.colorEnabled);
                if (!TextUtils.isEmpty(colorEnabledStr)) {
                    int enabledColorInt = Color.parseColor(colorEnabledStr);
                    stateSetList.add(SkinCompatThemeUtils.ENABLED_STATE_SET);
                    stateColorList.add(Integer.valueOf(enabledColorInt));
                    stateColorCount++;
                }
            } catch (Exception e4) {
            }
        }
        if (!TextUtils.isEmpty(this.colorPressed)) {
            try {
                String colorPressedStr = getColorStr(this.colorPressed);
                if (!TextUtils.isEmpty(colorPressedStr)) {
                    int pressedColorInt = Color.parseColor(colorPressedStr);
                    stateSetList.add(SkinCompatThemeUtils.PRESSED_STATE_SET);
                    stateColorList.add(Integer.valueOf(pressedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e5) {
            }
        }
        if (!TextUtils.isEmpty(this.colorChecked)) {
            try {
                String colorCheckedStr = getColorStr(this.colorChecked);
                if (!TextUtils.isEmpty(colorCheckedStr)) {
                    int checkedColorInt = Color.parseColor(colorCheckedStr);
                    stateSetList.add(SkinCompatThemeUtils.CHECKED_STATE_SET);
                    stateColorList.add(Integer.valueOf(checkedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e6) {
            }
        }
        if (!TextUtils.isEmpty(this.colorActivated)) {
            try {
                String colorActivatedStr = getColorStr(this.colorActivated);
                if (!TextUtils.isEmpty(colorActivatedStr)) {
                    int activatedColorInt = Color.parseColor(colorActivatedStr);
                    stateSetList.add(SkinCompatThemeUtils.ACTIVATED_STATE_SET);
                    stateColorList.add(Integer.valueOf(activatedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e7) {
            }
        }
        if (!TextUtils.isEmpty(this.colorAccelerated)) {
            try {
                String colorAcceleratedStr = getColorStr(this.colorAccelerated);
                if (!TextUtils.isEmpty(colorAcceleratedStr)) {
                    int acceleratedColorInt = Color.parseColor(colorAcceleratedStr);
                    stateSetList.add(SkinCompatThemeUtils.ACCELERATED_STATE_SET);
                    stateColorList.add(Integer.valueOf(acceleratedColorInt));
                    stateColorCount++;
                }
            } catch (Exception e8) {
            }
        }
        if (!TextUtils.isEmpty(this.colorHovered)) {
            try {
                String colorHoveredStr = getColorStr(this.colorHovered);
                if (!TextUtils.isEmpty(colorHoveredStr)) {
                    int hoveredColorInt = Color.parseColor(colorHoveredStr);
                    stateSetList.add(SkinCompatThemeUtils.HOVERED_STATE_SET);
                    stateColorList.add(Integer.valueOf(hoveredColorInt));
                    stateColorCount++;
                }
            } catch (Exception e9) {
            }
        }
        if (!TextUtils.isEmpty(this.colorDragCanAccept)) {
            try {
                String colorDragCanAcceptStr = getColorStr(this.colorDragCanAccept);
                if (!TextUtils.isEmpty(colorDragCanAcceptStr)) {
                    int dragCanAcceptColorInt = Color.parseColor(colorDragCanAcceptStr);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_CAN_ACCEPT_STATE_SET);
                    stateColorList.add(Integer.valueOf(dragCanAcceptColorInt));
                    stateColorCount++;
                }
            } catch (Exception e10) {
            }
        }
        if (!TextUtils.isEmpty(this.colorDragHovered)) {
            try {
                String colorDragHoveredStr = getColorStr(this.colorDragHovered);
                if (!TextUtils.isEmpty(colorDragHoveredStr)) {
                    int dragHoveredColorInt = Color.parseColor(colorDragHoveredStr);
                    stateSetList.add(SkinCompatThemeUtils.DRAG_HOVERED_STATE_SET);
                    stateColorList.add(Integer.valueOf(dragHoveredColorInt));
                    stateColorCount++;
                }
            } catch (Exception e11) {
            }
        }
        try {
            String colorDefaultStr = getColorStr(this.colorDefault);
            if (!TextUtils.isEmpty(colorDefaultStr)) {
                int baseColor = Color.parseColor(colorDefaultStr);
                stateSetList.add(SkinCompatThemeUtils.EMPTY_STATE_SET);
                stateColorList.add(Integer.valueOf(baseColor));
                stateColorCount++;
            }
            int[][] states = new int[stateColorCount][];
            int[] colors = new int[stateColorCount];
            for (int index = 0; index < stateColorCount; index++) {
                states[index] = stateSetList.get(index);
                colors[index] = stateColorList.get(index).intValue();
            }
            return new ColorStateList(states, colors);
        } catch (Exception e12) {
            if (Slog.DEBUG) {
                Slog.i(TAG, this.colorName + " parse failure.");
            }
            SkinCompatUserThemeManager.get().removeColorState(this.colorName);
            return null;
        }
    }

    private String getColorStr(String colorName2) {
        if (colorName2.startsWith("#")) {
            return colorName2;
        }
        ColorState stateRef = SkinCompatUserThemeManager.get().getColorState(colorName2);
        if (stateRef == null) {
            return null;
        }
        if (stateRef.isOnlyDefaultColor()) {
            return stateRef.colorDefault;
        }
        if (!Slog.DEBUG) {
            return null;
        }
        Slog.i(TAG, colorName2 + " cannot reference " + stateRef.colorName);
        return null;
    }

    static boolean checkColorValid(String name, String color) {
        boolean colorValid = !TextUtils.isEmpty(color) && (!color.startsWith("#") || color.length() == 7 || color.length() == 9);
        if (Slog.DEBUG && !colorValid) {
            Slog.i(TAG, "Invalid color -> " + name + PluralRules.KEYWORD_RULE_SEPARATOR + color);
        }
        return colorValid;
    }

    static JSONObject toJSONObject(ColorState state) throws JSONException {
        JSONObject object = new JSONObject();
        if (state.onlyDefaultColor) {
            object.putOpt("colorName", state.colorName).putOpt("colorDefault", state.colorDefault).putOpt("onlyDefaultColor", Boolean.valueOf(state.onlyDefaultColor));
        } else {
            object.putOpt("colorName", state.colorName).putOpt("colorWindowFocused", state.colorWindowFocused).putOpt("colorSelected", state.colorSelected).putOpt("colorFocused", state.colorFocused).putOpt("colorEnabled", state.colorEnabled).putOpt("colorPressed", state.colorPressed).putOpt("colorChecked", state.colorChecked).putOpt("colorActivated", state.colorActivated).putOpt("colorAccelerated", state.colorAccelerated).putOpt("colorHovered", state.colorHovered).putOpt("colorDragCanAccept", state.colorDragCanAccept).putOpt("colorDragHovered", state.colorDragHovered).putOpt("colorDefault", state.colorDefault).putOpt("onlyDefaultColor", Boolean.valueOf(state.onlyDefaultColor));
        }
        return object;
    }

    static ColorState fromJSONObject(JSONObject jsonObject) {
        JSONObject jSONObject = jsonObject;
        if (!jSONObject.has("colorName") || !jSONObject.has("colorDefault") || !jSONObject.has("onlyDefaultColor")) {
            return null;
        }
        try {
            boolean onlyDefaultColor2 = jSONObject.getBoolean("onlyDefaultColor");
            String colorName2 = jSONObject.getString("colorName");
            String colorDefault2 = jSONObject.getString("colorDefault");
            if (onlyDefaultColor2) {
                return new ColorState(colorName2, colorDefault2);
            }
            ColorBuilder builder = new ColorBuilder();
            builder.setColorDefault(colorDefault2);
            if (jSONObject.has("colorWindowFocused")) {
                builder.setColorWindowFocused(jSONObject.getString("colorWindowFocused"));
            }
            if (jSONObject.has("colorSelected")) {
                builder.setColorSelected(jSONObject.getString("colorSelected"));
            }
            if (jSONObject.has("colorFocused")) {
                builder.setColorFocused(jSONObject.getString("colorFocused"));
            }
            if (jSONObject.has("colorEnabled")) {
                builder.setColorEnabled(jSONObject.getString("colorEnabled"));
            }
            if (jSONObject.has("colorPressed")) {
                builder.setColorPressed(jSONObject.getString("colorPressed"));
            }
            if (jSONObject.has("colorChecked")) {
                builder.setColorChecked(jSONObject.getString("colorChecked"));
            }
            if (jSONObject.has("colorActivated")) {
                builder.setColorActivated(jSONObject.getString("colorActivated"));
            }
            if (jSONObject.has("colorAccelerated")) {
                builder.setColorAccelerated(jSONObject.getString("colorAccelerated"));
            }
            if (jSONObject.has("colorHovered")) {
                builder.setColorHovered(jSONObject.getString("colorHovered"));
            }
            if (jSONObject.has("colorDragCanAccept")) {
                builder.setColorDragCanAccept(jSONObject.getString("colorDragCanAccept"));
            }
            if (jSONObject.has("colorDragHovered")) {
                builder.setColorDragHovered(jSONObject.getString("colorDragHovered"));
            }
            ColorState state = builder.build();
            state.colorName = colorName2;
            return state;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class ColorBuilder {
        String colorAccelerated;
        String colorActivated;
        String colorChecked;
        String colorDefault;
        String colorDragCanAccept;
        String colorDragHovered;
        String colorEnabled;
        String colorFocused;
        String colorHovered;
        String colorPressed;
        String colorSelected;
        String colorWindowFocused;

        public ColorBuilder() {
        }

        public ColorBuilder(ColorState state) {
            this.colorWindowFocused = state.colorWindowFocused;
            this.colorSelected = state.colorSelected;
            this.colorFocused = state.colorFocused;
            this.colorEnabled = state.colorEnabled;
            this.colorPressed = state.colorPressed;
            this.colorChecked = state.colorChecked;
            this.colorActivated = state.colorActivated;
            this.colorAccelerated = state.colorAccelerated;
            this.colorHovered = state.colorHovered;
            this.colorDragCanAccept = state.colorDragCanAccept;
            this.colorDragHovered = state.colorDragHovered;
            this.colorDefault = state.colorDefault;
        }

        public ColorBuilder setColorWindowFocused(String colorWindowFocused2) {
            if (ColorState.checkColorValid("colorWindowFocused", colorWindowFocused2)) {
                this.colorWindowFocused = colorWindowFocused2;
            }
            return this;
        }

        public ColorBuilder setColorWindowFocused(Context context, int colorRes) {
            this.colorWindowFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorSelected(String colorSelected2) {
            if (ColorState.checkColorValid("colorSelected", colorSelected2)) {
                this.colorSelected = colorSelected2;
            }
            return this;
        }

        public ColorBuilder setColorSelected(Context context, int colorRes) {
            this.colorSelected = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorFocused(String colorFocused2) {
            if (ColorState.checkColorValid("colorFocused", colorFocused2)) {
                this.colorFocused = colorFocused2;
            }
            return this;
        }

        public ColorBuilder setColorFocused(Context context, int colorRes) {
            this.colorFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorEnabled(String colorEnabled2) {
            if (ColorState.checkColorValid("colorEnabled", colorEnabled2)) {
                this.colorEnabled = colorEnabled2;
            }
            return this;
        }

        public ColorBuilder setColorEnabled(Context context, int colorRes) {
            this.colorEnabled = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorChecked(String colorChecked2) {
            if (ColorState.checkColorValid("colorChecked", colorChecked2)) {
                this.colorChecked = colorChecked2;
            }
            return this;
        }

        public ColorBuilder setColorChecked(Context context, int colorRes) {
            this.colorChecked = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorPressed(String colorPressed2) {
            if (ColorState.checkColorValid("colorPressed", colorPressed2)) {
                this.colorPressed = colorPressed2;
            }
            return this;
        }

        public ColorBuilder setColorPressed(Context context, int colorRes) {
            this.colorPressed = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorActivated(String colorActivated2) {
            if (ColorState.checkColorValid("colorActivated", colorActivated2)) {
                this.colorActivated = colorActivated2;
            }
            return this;
        }

        public ColorBuilder setColorActivated(Context context, int colorRes) {
            this.colorActivated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorAccelerated(String colorAccelerated2) {
            if (ColorState.checkColorValid("colorAccelerated", colorAccelerated2)) {
                this.colorAccelerated = colorAccelerated2;
            }
            return this;
        }

        public ColorBuilder setColorAccelerated(Context context, int colorRes) {
            this.colorAccelerated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorHovered(String colorHovered2) {
            if (ColorState.checkColorValid("colorHovered", colorHovered2)) {
                this.colorHovered = colorHovered2;
            }
            return this;
        }

        public ColorBuilder setColorHovered(Context context, int colorRes) {
            this.colorHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String colorDragCanAccept2) {
            if (ColorState.checkColorValid("colorDragCanAccept", colorDragCanAccept2)) {
                this.colorDragCanAccept = colorDragCanAccept2;
            }
            return this;
        }

        public ColorBuilder setColorDragCanAccept(Context context, int colorRes) {
            this.colorDragCanAccept = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragHovered(String colorDragHovered2) {
            if (ColorState.checkColorValid("colorDragHovered", colorDragHovered2)) {
                this.colorDragHovered = colorDragHovered2;
            }
            return this;
        }

        public ColorBuilder setColorDragHovered(Context context, int colorRes) {
            this.colorDragHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDefault(String colorDefault2) {
            if (ColorState.checkColorValid("colorDefault", colorDefault2)) {
                this.colorDefault = colorDefault2;
            }
            return this;
        }

        public ColorBuilder setColorDefault(Context context, int colorRes) {
            this.colorDefault = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorState build() {
            if (!TextUtils.isEmpty(this.colorDefault)) {
                return new ColorState(this.colorWindowFocused, this.colorSelected, this.colorFocused, this.colorEnabled, this.colorPressed, this.colorChecked, this.colorActivated, this.colorAccelerated, this.colorHovered, this.colorDragCanAccept, this.colorDragHovered, this.colorDefault);
            }
            throw new SkinCompatException("Default color can not empty!");
        }
    }
}
