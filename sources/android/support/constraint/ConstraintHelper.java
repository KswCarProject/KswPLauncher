package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Helper;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Arrays;
import java.util.HashMap;

public abstract class ConstraintHelper extends View {
    protected int mCount;
    protected Helper mHelperWidget;
    protected int[] mIds = new int[32];
    private HashMap<Integer, String> mMap = new HashMap<>();
    protected String mReferenceIds;
    protected boolean mUseViewMeasure = false;
    private View[] mViews = null;
    protected Context myContext;

    public ConstraintHelper(Context context) {
        super(context);
        this.myContext = context;
        init((AttributeSet) null);
    }

    public ConstraintHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.myContext = context;
        init(attrs);
    }

    public ConstraintHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.myContext = context;
        init(attrs);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
                    String string = a.getString(attr);
                    this.mReferenceIds = string;
                    setIds(string);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String str = this.mReferenceIds;
        if (str != null) {
            setIds(str);
        }
    }

    public void addView(View view) {
        if (view != this) {
            if (view.getId() == -1) {
                Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have an id");
            } else if (view.getParent() == null) {
                Log.e("ConstraintHelper", "Views added to a ConstraintHelper need to have a parent");
            } else {
                this.mReferenceIds = null;
                addRscID(view.getId());
                requestLayout();
            }
        }
    }

    public void removeView(View view) {
        int i;
        int id = view.getId();
        if (id != -1) {
            this.mReferenceIds = null;
            int i2 = 0;
            while (true) {
                if (i2 >= this.mCount) {
                    break;
                } else if (this.mIds[i2] == id) {
                    int j = i2;
                    while (true) {
                        i = this.mCount;
                        if (j >= i - 1) {
                            break;
                        }
                        int[] iArr = this.mIds;
                        iArr[j] = iArr[j + 1];
                        j++;
                    }
                    this.mIds[i - 1] = 0;
                    this.mCount = i - 1;
                } else {
                    i2++;
                }
            }
            requestLayout();
        }
    }

    public int[] getReferencedIds() {
        return Arrays.copyOf(this.mIds, this.mCount);
    }

    public void setReferencedIds(int[] ids) {
        this.mReferenceIds = null;
        this.mCount = 0;
        for (int addRscID : ids) {
            addRscID(addRscID);
        }
    }

    private void addRscID(int id) {
        if (id != getId()) {
            int i = this.mCount + 1;
            int[] iArr = this.mIds;
            if (i > iArr.length) {
                this.mIds = Arrays.copyOf(iArr, iArr.length * 2);
            }
            int[] iArr2 = this.mIds;
            int i2 = this.mCount;
            iArr2[i2] = id;
            this.mCount = i2 + 1;
        }
    }

    public void onDraw(Canvas canvas) {
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mUseViewMeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            setMeasuredDimension(0, 0);
        }
    }

    public void validateParams() {
        if (this.mHelperWidget != null) {
            ViewGroup.LayoutParams params = getLayoutParams();
            if (params instanceof ConstraintLayout.LayoutParams) {
                ((ConstraintLayout.LayoutParams) params).widget = (ConstraintWidget) this.mHelperWidget;
            }
        }
    }

    private void addID(String idString) {
        if (idString != null && idString.length() != 0 && this.myContext != null) {
            String idString2 = idString.trim();
            if (getParent() instanceof ConstraintLayout) {
                ConstraintLayout parent = (ConstraintLayout) getParent();
            }
            int rscId = findId(idString2);
            if (rscId != 0) {
                this.mMap.put(Integer.valueOf(rscId), idString2);
                addRscID(rscId);
                return;
            }
            Log.w("ConstraintHelper", "Could not find id of \"" + idString2 + "\"");
        }
    }

    /* JADX WARNING: type inference failed for: r1v10, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int findId(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            android.view.ViewParent r1 = r5.getParent()
            boolean r1 = r1 instanceof android.support.constraint.ConstraintLayout
            if (r1 == 0) goto L_0x0010
            android.view.ViewParent r1 = r5.getParent()
            r0 = r1
            android.support.constraint.ConstraintLayout r0 = (android.support.constraint.ConstraintLayout) r0
        L_0x0010:
            r1 = 0
            boolean r2 = r5.isInEditMode()
            if (r2 == 0) goto L_0x0029
            if (r0 == 0) goto L_0x0029
            r2 = 0
            java.lang.Object r2 = r0.getDesignInformation(r2, r6)
            boolean r3 = r2 instanceof java.lang.Integer
            if (r3 == 0) goto L_0x0029
            r3 = r2
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r1 = r3.intValue()
        L_0x0029:
            if (r1 != 0) goto L_0x0031
            if (r0 == 0) goto L_0x0031
            int r1 = r5.findId(r0, r6)
        L_0x0031:
            if (r1 != 0) goto L_0x0041
            java.lang.Class<android.support.constraint.R$id> r2 = android.support.constraint.R.id.class
            java.lang.reflect.Field r3 = r2.getField(r6)     // Catch:{ Exception -> 0x0040 }
            r4 = 0
            int r4 = r3.getInt(r4)     // Catch:{ Exception -> 0x0040 }
            r1 = r4
            goto L_0x0041
        L_0x0040:
            r2 = move-exception
        L_0x0041:
            if (r1 != 0) goto L_0x0055
            android.content.Context r2 = r5.myContext
            android.content.res.Resources r2 = r2.getResources()
            android.content.Context r3 = r5.myContext
            java.lang.String r3 = r3.getPackageName()
            java.lang.String r4 = "id"
            int r1 = r2.getIdentifier(r6, r4, r3)
        L_0x0055:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintHelper.findId(java.lang.String):int");
    }

    private int findId(ConstraintLayout container, String idString) {
        Resources resources;
        if (idString == null || container == null || (resources = this.myContext.getResources()) == null) {
            return 0;
        }
        int count = container.getChildCount();
        for (int j = 0; j < count; j++) {
            View child = container.getChildAt(j);
            if (child.getId() != -1) {
                String res = null;
                try {
                    res = resources.getResourceEntryName(child.getId());
                } catch (Resources.NotFoundException e) {
                }
                if (idString.equals(res)) {
                    return child.getId();
                }
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setIds(String idList) {
        this.mReferenceIds = idList;
        if (idList != null) {
            int begin = 0;
            this.mCount = 0;
            while (true) {
                int end = idList.indexOf(44, begin);
                if (end == -1) {
                    addID(idList.substring(begin));
                    return;
                } else {
                    addID(idList.substring(begin, end));
                    begin = end + 1;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void applyLayoutFeatures(ConstraintLayout container) {
        int visibility = getVisibility();
        float elevation = 0.0f;
        if (Build.VERSION.SDK_INT >= 21) {
            elevation = getElevation();
        }
        for (int i = 0; i < this.mCount; i++) {
            View view = container.getViewById(this.mIds[i]);
            if (view != null) {
                view.setVisibility(visibility);
                if (elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                    view.setTranslationZ(view.getTranslationZ() + elevation);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void applyLayoutFeatures() {
        ViewParent parent = getParent();
        if (parent != null && (parent instanceof ConstraintLayout)) {
            applyLayoutFeatures((ConstraintLayout) parent);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r3 = r7.mMap.get(java.lang.Integer.valueOf(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePreLayout(android.support.constraint.ConstraintLayout r8) {
        /*
            r7 = this;
            boolean r0 = r7.isInEditMode()
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = r7.mReferenceIds
            r7.setIds(r0)
        L_0x000b:
            android.support.constraint.solver.widgets.Helper r0 = r7.mHelperWidget
            if (r0 != 0) goto L_0x0010
            return
        L_0x0010:
            r0.removeAllIds()
            r0 = 0
        L_0x0014:
            int r1 = r7.mCount
            if (r0 >= r1) goto L_0x0053
            int[] r1 = r7.mIds
            r1 = r1[r0]
            android.view.View r2 = r8.getViewById(r1)
            if (r2 != 0) goto L_0x0045
            java.util.HashMap<java.lang.Integer, java.lang.String> r3 = r7.mMap
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r7.findId(r8, r3)
            if (r4 == 0) goto L_0x0045
            int[] r5 = r7.mIds
            r5[r0] = r4
            java.util.HashMap<java.lang.Integer, java.lang.String> r5 = r7.mMap
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            r5.put(r6, r3)
            android.view.View r2 = r8.getViewById(r4)
        L_0x0045:
            if (r2 == 0) goto L_0x0050
            android.support.constraint.solver.widgets.Helper r3 = r7.mHelperWidget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r8.getViewWidget(r2)
            r3.add(r4)
        L_0x0050:
            int r0 = r0 + 1
            goto L_0x0014
        L_0x0053:
            android.support.constraint.solver.widgets.Helper r0 = r7.mHelperWidget
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r1 = r8.mLayoutWidget
            r0.updateConstraints(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintHelper.updatePreLayout(android.support.constraint.ConstraintLayout):void");
    }

    public void updatePreLayout(ConstraintWidgetContainer container, Helper helper, SparseArray<ConstraintWidget> map) {
        helper.removeAllIds();
        for (int i = 0; i < this.mCount; i++) {
            helper.add(map.get(this.mIds[i]));
        }
    }

    /* access modifiers changed from: protected */
    public View[] getViews(ConstraintLayout layout) {
        View[] viewArr = this.mViews;
        if (viewArr == null || viewArr.length != this.mCount) {
            this.mViews = new View[this.mCount];
        }
        for (int i = 0; i < this.mCount; i++) {
            this.mViews[i] = layout.getViewById(this.mIds[i]);
        }
        return this.mViews;
    }

    public void updatePostLayout(ConstraintLayout container) {
    }

    public void updatePostMeasure(ConstraintLayout container) {
    }

    public void updatePostConstraints(ConstraintLayout constainer) {
    }

    public void updatePreDraw(ConstraintLayout container) {
    }

    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget child, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> mapIdToWidget) {
        if (constraint.layout.mReferenceIds != null) {
            setReferencedIds(constraint.layout.mReferenceIds);
        } else if (constraint.layout.mReferenceIdString != null && constraint.layout.mReferenceIdString.length() > 0) {
            constraint.layout.mReferenceIds = convertReferenceString(this, constraint.layout.mReferenceIdString);
        }
        child.removeAllIds();
        if (constraint.layout.mReferenceIds != null) {
            for (int id : constraint.layout.mReferenceIds) {
                ConstraintWidget widget = mapIdToWidget.get(id);
                if (widget != null) {
                    child.add(widget);
                }
            }
        }
    }

    private int[] convertReferenceString(View view, String referenceIdString) {
        String[] split = referenceIdString.split(",");
        Context context = view.getContext();
        int[] rscIds = new int[split.length];
        int count = 0;
        for (String idString : split) {
            int id = findId(idString.trim());
            if (id != 0) {
                rscIds[count] = id;
                count++;
            }
        }
        if (count != split.length) {
            return Arrays.copyOf(rscIds, count);
        }
        return rscIds;
    }

    public void resolveRtl(ConstraintWidget widget, boolean isRtl) {
    }
}
