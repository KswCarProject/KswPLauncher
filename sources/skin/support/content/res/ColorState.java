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

/* loaded from: classes.dex */
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

    ColorState(String colorWindowFocused, String colorSelected, String colorFocused, String colorEnabled, String colorPressed, String colorChecked, String colorActivated, String colorAccelerated, String colorHovered, String colorDragCanAccept, String colorDragHovered, String colorDefault) {
        this.colorWindowFocused = colorWindowFocused;
        this.colorSelected = colorSelected;
        this.colorFocused = colorFocused;
        this.colorEnabled = colorEnabled;
        this.colorPressed = colorPressed;
        this.colorChecked = colorChecked;
        this.colorActivated = colorActivated;
        this.colorAccelerated = colorAccelerated;
        this.colorHovered = colorHovered;
        this.colorDragCanAccept = colorDragCanAccept;
        this.colorDragHovered = colorDragHovered;
        this.colorDefault = colorDefault;
        boolean z = TextUtils.isEmpty(colorWindowFocused) && TextUtils.isEmpty(colorSelected) && TextUtils.isEmpty(colorFocused) && TextUtils.isEmpty(colorEnabled) && TextUtils.isEmpty(colorPressed) && TextUtils.isEmpty(colorChecked) && TextUtils.isEmpty(colorActivated) && TextUtils.isEmpty(colorAccelerated) && TextUtils.isEmpty(colorHovered) && TextUtils.isEmpty(colorDragCanAccept) && TextUtils.isEmpty(colorDragHovered);
        this.onlyDefaultColor = z;
        if (z && !colorDefault.startsWith("#")) {
            throw new SkinCompatException("Default color cannot be a reference, when only default color is available!");
        }
    }

    ColorState(String colorName, String colorDefault) {
        this.colorName = colorName;
        this.colorDefault = colorDefault;
        this.onlyDefaultColor = true;
        if (!colorDefault.startsWith("#")) {
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

    ColorStateList parse() {
        if (this.onlyDefaultColor) {
            int defaultColor = Color.parseColor(this.colorDefault);
            return ColorStateList.valueOf(defaultColor);
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
            int[][] states = new int[stateColorCount];
            int[] colors = new int[stateColorCount];
            for (int index = 0; index < stateColorCount; index++) {
                states[index] = stateSetList.get(index);
                colors[index] = stateColorList.get(index).intValue();
            }
            return new ColorStateList(states, colors);
        } catch (Exception e12) {
            if (Slog.DEBUG) {
                Slog.m2i(TAG, this.colorName + " parse failure.");
            }
            SkinCompatUserThemeManager.get().removeColorState(this.colorName);
            return null;
        }
    }

    private String getColorStr(String colorName) {
        if (colorName.startsWith("#")) {
            return colorName;
        }
        ColorState stateRef = SkinCompatUserThemeManager.get().getColorState(colorName);
        if (stateRef != null) {
            if (stateRef.isOnlyDefaultColor()) {
                return stateRef.colorDefault;
            }
            if (Slog.DEBUG) {
                Slog.m2i(TAG, colorName + " cannot reference " + stateRef.colorName);
                return null;
            }
            return null;
        }
        return null;
    }

    static boolean checkColorValid(String name, String color) {
        boolean colorValid = !TextUtils.isEmpty(color) && (!color.startsWith("#") || color.length() == 7 || color.length() == 9);
        if (Slog.DEBUG && !colorValid) {
            Slog.m2i(TAG, "Invalid color -> " + name + PluralRules.KEYWORD_RULE_SEPARATOR + color);
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
        if (jsonObject.has("colorName") && jsonObject.has("colorDefault") && jsonObject.has("onlyDefaultColor")) {
            try {
                boolean onlyDefaultColor = jsonObject.getBoolean("onlyDefaultColor");
                String colorName = jsonObject.getString("colorName");
                String colorDefault = jsonObject.getString("colorDefault");
                if (onlyDefaultColor) {
                    return new ColorState(colorName, colorDefault);
                }
                ColorBuilder builder = new ColorBuilder();
                builder.setColorDefault(colorDefault);
                if (jsonObject.has("colorWindowFocused")) {
                    builder.setColorWindowFocused(jsonObject.getString("colorWindowFocused"));
                }
                if (jsonObject.has("colorSelected")) {
                    builder.setColorSelected(jsonObject.getString("colorSelected"));
                }
                if (jsonObject.has("colorFocused")) {
                    builder.setColorFocused(jsonObject.getString("colorFocused"));
                }
                if (jsonObject.has("colorEnabled")) {
                    builder.setColorEnabled(jsonObject.getString("colorEnabled"));
                }
                if (jsonObject.has("colorPressed")) {
                    builder.setColorPressed(jsonObject.getString("colorPressed"));
                }
                if (jsonObject.has("colorChecked")) {
                    builder.setColorChecked(jsonObject.getString("colorChecked"));
                }
                if (jsonObject.has("colorActivated")) {
                    builder.setColorActivated(jsonObject.getString("colorActivated"));
                }
                if (jsonObject.has("colorAccelerated")) {
                    builder.setColorAccelerated(jsonObject.getString("colorAccelerated"));
                }
                if (jsonObject.has("colorHovered")) {
                    builder.setColorHovered(jsonObject.getString("colorHovered"));
                }
                if (jsonObject.has("colorDragCanAccept")) {
                    builder.setColorDragCanAccept(jsonObject.getString("colorDragCanAccept"));
                }
                if (jsonObject.has("colorDragHovered")) {
                    builder.setColorDragHovered(jsonObject.getString("colorDragHovered"));
                }
                ColorState state = builder.build();
                state.colorName = colorName;
                return state;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* loaded from: classes.dex */
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

        public ColorBuilder setColorWindowFocused(String colorWindowFocused) {
            if (ColorState.checkColorValid("colorWindowFocused", colorWindowFocused)) {
                this.colorWindowFocused = colorWindowFocused;
            }
            return this;
        }

        public ColorBuilder setColorWindowFocused(Context context, int colorRes) {
            this.colorWindowFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorSelected(String colorSelected) {
            if (ColorState.checkColorValid("colorSelected", colorSelected)) {
                this.colorSelected = colorSelected;
            }
            return this;
        }

        public ColorBuilder setColorSelected(Context context, int colorRes) {
            this.colorSelected = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorFocused(String colorFocused) {
            if (ColorState.checkColorValid("colorFocused", colorFocused)) {
                this.colorFocused = colorFocused;
            }
            return this;
        }

        public ColorBuilder setColorFocused(Context context, int colorRes) {
            this.colorFocused = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorEnabled(String colorEnabled) {
            if (ColorState.checkColorValid("colorEnabled", colorEnabled)) {
                this.colorEnabled = colorEnabled;
            }
            return this;
        }

        public ColorBuilder setColorEnabled(Context context, int colorRes) {
            this.colorEnabled = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorChecked(String colorChecked) {
            if (ColorState.checkColorValid("colorChecked", colorChecked)) {
                this.colorChecked = colorChecked;
            }
            return this;
        }

        public ColorBuilder setColorChecked(Context context, int colorRes) {
            this.colorChecked = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorPressed(String colorPressed) {
            if (ColorState.checkColorValid("colorPressed", colorPressed)) {
                this.colorPressed = colorPressed;
            }
            return this;
        }

        public ColorBuilder setColorPressed(Context context, int colorRes) {
            this.colorPressed = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorActivated(String colorActivated) {
            if (ColorState.checkColorValid("colorActivated", colorActivated)) {
                this.colorActivated = colorActivated;
            }
            return this;
        }

        public ColorBuilder setColorActivated(Context context, int colorRes) {
            this.colorActivated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorAccelerated(String colorAccelerated) {
            if (ColorState.checkColorValid("colorAccelerated", colorAccelerated)) {
                this.colorAccelerated = colorAccelerated;
            }
            return this;
        }

        public ColorBuilder setColorAccelerated(Context context, int colorRes) {
            this.colorAccelerated = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorHovered(String colorHovered) {
            if (ColorState.checkColorValid("colorHovered", colorHovered)) {
                this.colorHovered = colorHovered;
            }
            return this;
        }

        public ColorBuilder setColorHovered(Context context, int colorRes) {
            this.colorHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragCanAccept(String colorDragCanAccept) {
            if (ColorState.checkColorValid("colorDragCanAccept", colorDragCanAccept)) {
                this.colorDragCanAccept = colorDragCanAccept;
            }
            return this;
        }

        public ColorBuilder setColorDragCanAccept(Context context, int colorRes) {
            this.colorDragCanAccept = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDragHovered(String colorDragHovered) {
            if (ColorState.checkColorValid("colorDragHovered", colorDragHovered)) {
                this.colorDragHovered = colorDragHovered;
            }
            return this;
        }

        public ColorBuilder setColorDragHovered(Context context, int colorRes) {
            this.colorDragHovered = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorBuilder setColorDefault(String colorDefault) {
            if (ColorState.checkColorValid("colorDefault", colorDefault)) {
                this.colorDefault = colorDefault;
            }
            return this;
        }

        public ColorBuilder setColorDefault(Context context, int colorRes) {
            this.colorDefault = context.getResources().getResourceEntryName(colorRes);
            return this;
        }

        public ColorState build() {
            if (TextUtils.isEmpty(this.colorDefault)) {
                throw new SkinCompatException("Default color can not empty!");
            }
            return new ColorState(this.colorWindowFocused, this.colorSelected, this.colorFocused, this.colorEnabled, this.colorPressed, this.colorChecked, this.colorActivated, this.colorAccelerated, this.colorHovered, this.colorDragCanAccept, this.colorDragHovered, this.colorDefault);
        }
    }
}
