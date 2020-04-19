package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.R;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AlertController {
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View.OnClickListener mButtonHandler = new View.OnClickListener() {
        public void onClick(View v) {
            Message m;
            if (v == AlertController.this.mButtonPositive && AlertController.this.mButtonPositiveMessage != null) {
                m = Message.obtain(AlertController.this.mButtonPositiveMessage);
            } else if (v == AlertController.this.mButtonNegative && AlertController.this.mButtonNegativeMessage != null) {
                m = Message.obtain(AlertController.this.mButtonNegativeMessage);
            } else if (v != AlertController.this.mButtonNeutral || AlertController.this.mButtonNeutralMessage == null) {
                m = null;
            } else {
                m = Message.obtain(AlertController.this.mButtonNeutralMessage);
            }
            if (m != null) {
                m.sendToTarget();
            }
            AlertController.this.mHandler.obtainMessage(1, AlertController.this.mDialog).sendToTarget();
        }
    };
    private final int mButtonIconDimen;
    Button mButtonNegative;
    private Drawable mButtonNegativeIcon;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    private Drawable mButtonNeutralIcon;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint = 0;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    private Drawable mButtonPositiveIcon;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem = -1;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId = 0;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    private boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified = false;
    private int mViewSpacingTop;
    private final Window mWindow;

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialog) {
            this.mDialog = new WeakReference<>(dialog);
        }

        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i != 1) {
                switch (i) {
                    case FontsContractCompat.FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) msg.obj).onClick((DialogInterface) this.mDialog.get(), msg.what);
                        return;
                    default:
                        return;
                }
            } else {
                ((DialogInterface) msg.obj).dismiss();
            }
        }
    }

    private static boolean shouldCenterSingleButton(Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, outValue, true);
        if (outValue.data != 0) {
            return true;
        }
        return false;
    }

    public AlertController(Context context, AppCompatDialog di, Window window) {
        this.mContext = context;
        this.mDialog = di;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(di);
        TypedArray a = context.obtainStyledAttributes((AttributeSet) null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = a.getResourceId(R.styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = a.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = a.getResourceId(R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = a.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = a.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = a.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = a.getBoolean(R.styleable.AlertDialog_showTitle, true);
        this.mButtonIconDimen = a.getDimensionPixelSize(R.styleable.AlertDialog_buttonIconDimen, 0);
        a.recycle();
        di.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View v) {
        if (v.onCheckIsTextEditor()) {
            return true;
        }
        if (!(v instanceof ViewGroup)) {
            return false;
        }
        ViewGroup vg = (ViewGroup) v;
        int i = vg.getChildCount();
        while (i > 0) {
            i--;
            if (canTextInput(vg.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        this.mDialog.setContentView(selectContentView());
        setupView();
    }

    private int selectContentView() {
        if (this.mButtonPanelSideLayout == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return this.mButtonPanelSideLayout;
        }
        return this.mAlertDialogLayout;
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        if (this.mTitleView != null) {
            this.mTitleView.setText(title);
        }
    }

    public void setCustomTitle(View customTitleView) {
        this.mCustomTitleView = customTitleView;
    }

    public void setMessage(CharSequence message) {
        this.mMessage = message;
        if (this.mMessageView != null) {
            this.mMessageView.setText(message);
        }
    }

    public void setView(int layoutResId) {
        this.mView = null;
        this.mViewLayoutResId = layoutResId;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }

    public void setView(View view, int viewSpacingLeft, int viewSpacingTop, int viewSpacingRight, int viewSpacingBottom) {
        this.mView = view;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = viewSpacingLeft;
        this.mViewSpacingTop = viewSpacingTop;
        this.mViewSpacingRight = viewSpacingRight;
        this.mViewSpacingBottom = viewSpacingBottom;
    }

    public void setButtonPanelLayoutHint(int layoutHint) {
        this.mButtonPanelLayoutHint = layoutHint;
    }

    public void setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener, Message msg, Drawable icon) {
        if (msg == null && listener != null) {
            msg = this.mHandler.obtainMessage(whichButton, listener);
        }
        switch (whichButton) {
            case FontsContractCompat.FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR:
                this.mButtonNeutralText = text;
                this.mButtonNeutralMessage = msg;
                this.mButtonNeutralIcon = icon;
                return;
            case -2:
                this.mButtonNegativeText = text;
                this.mButtonNegativeMessage = msg;
                this.mButtonNegativeIcon = icon;
                return;
            case -1:
                this.mButtonPositiveText = text;
                this.mButtonPositiveMessage = msg;
                this.mButtonPositiveIcon = icon;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void setIcon(int resId) {
        this.mIcon = null;
        this.mIconId = resId;
        if (this.mIconView == null) {
            return;
        }
        if (resId != 0) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
        this.mIconId = 0;
        if (this.mIconView == null) {
            return;
        }
        if (icon != null) {
            this.mIconView.setVisibility(0);
            this.mIconView.setImageDrawable(icon);
            return;
        }
        this.mIconView.setVisibility(8);
    }

    public int getIconAttributeResId(int attrId) {
        TypedValue out = new TypedValue();
        this.mContext.getTheme().resolveAttribute(attrId, out, true);
        return out.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public Button getButton(int whichButton) {
        switch (whichButton) {
            case FontsContractCompat.FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case -1:
                return this.mButtonPositive;
            default:
                return null;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(event);
    }

    @Nullable
    private ViewGroup resolvePanel(@Nullable View customPanel, @Nullable View defaultPanel) {
        if (customPanel == null) {
            if (defaultPanel instanceof ViewStub) {
                defaultPanel = ((ViewStub) defaultPanel).inflate();
            }
            return (ViewGroup) defaultPanel;
        }
        if (defaultPanel != null) {
            ViewParent parent = defaultPanel.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(defaultPanel);
            }
        }
        if (customPanel instanceof ViewStub) {
            customPanel = ((ViewStub) customPanel).inflate();
        }
        return (ViewGroup) customPanel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupView() {
        /*
            r19 = this;
            r0 = r19
            android.view.Window r1 = r0.mWindow
            int r2 = android.support.v7.appcompat.R.id.parentPanel
            android.view.View r1 = r1.findViewById(r2)
            int r2 = android.support.v7.appcompat.R.id.topPanel
            android.view.View r2 = r1.findViewById(r2)
            int r3 = android.support.v7.appcompat.R.id.contentPanel
            android.view.View r3 = r1.findViewById(r3)
            int r4 = android.support.v7.appcompat.R.id.buttonPanel
            android.view.View r4 = r1.findViewById(r4)
            int r5 = android.support.v7.appcompat.R.id.customPanel
            android.view.View r5 = r1.findViewById(r5)
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            r0.setupCustomContent(r5)
            int r6 = android.support.v7.appcompat.R.id.topPanel
            android.view.View r6 = r5.findViewById(r6)
            int r7 = android.support.v7.appcompat.R.id.contentPanel
            android.view.View r7 = r5.findViewById(r7)
            int r8 = android.support.v7.appcompat.R.id.buttonPanel
            android.view.View r8 = r5.findViewById(r8)
            android.view.ViewGroup r9 = r0.resolvePanel(r6, r2)
            android.view.ViewGroup r10 = r0.resolvePanel(r7, r3)
            android.view.ViewGroup r11 = r0.resolvePanel(r8, r4)
            r0.setupContent(r10)
            r0.setupButtons(r11)
            r0.setupTitle(r9)
            r12 = 8
            if (r5 == 0) goto L_0x005a
            int r15 = r5.getVisibility()
            if (r15 == r12) goto L_0x005a
            r15 = 1
            goto L_0x005b
        L_0x005a:
            r15 = 0
        L_0x005b:
            if (r9 == 0) goto L_0x0065
            int r13 = r9.getVisibility()
            if (r13 == r12) goto L_0x0065
            r13 = 1
            goto L_0x0066
        L_0x0065:
            r13 = 0
        L_0x0066:
            if (r11 == 0) goto L_0x0070
            int r14 = r11.getVisibility()
            if (r14 == r12) goto L_0x0070
            r12 = 1
            goto L_0x0071
        L_0x0070:
            r12 = 0
        L_0x0071:
            if (r12 != 0) goto L_0x0084
            if (r10 == 0) goto L_0x0084
            int r14 = android.support.v7.appcompat.R.id.textSpacerNoButtons
            android.view.View r14 = r10.findViewById(r14)
            if (r14 == 0) goto L_0x0084
            r17 = r1
            r1 = 0
            r14.setVisibility(r1)
            goto L_0x0086
        L_0x0084:
            r17 = r1
        L_0x0086:
            if (r13 == 0) goto L_0x00a8
            android.support.v4.widget.NestedScrollView r1 = r0.mScrollView
            if (r1 == 0) goto L_0x0092
            android.support.v4.widget.NestedScrollView r1 = r0.mScrollView
            r14 = 1
            r1.setClipToPadding(r14)
        L_0x0092:
            r1 = 0
            java.lang.CharSequence r14 = r0.mMessage
            if (r14 != 0) goto L_0x009b
            android.widget.ListView r14 = r0.mListView
            if (r14 == 0) goto L_0x00a1
        L_0x009b:
            int r14 = android.support.v7.appcompat.R.id.titleDividerNoCustom
            android.view.View r1 = r9.findViewById(r14)
        L_0x00a1:
            if (r1 == 0) goto L_0x00a7
            r14 = 0
            r1.setVisibility(r14)
        L_0x00a7:
            goto L_0x00b7
        L_0x00a8:
            if (r10 == 0) goto L_0x00b7
            int r1 = android.support.v7.appcompat.R.id.textSpacerNoTitle
            android.view.View r1 = r10.findViewById(r1)
            if (r1 == 0) goto L_0x00b7
            r14 = 0
            r1.setVisibility(r14)
            goto L_0x00b8
        L_0x00b7:
            r14 = 0
        L_0x00b8:
            android.widget.ListView r1 = r0.mListView
            boolean r1 = r1 instanceof android.support.v7.app.AlertController.RecycleListView
            if (r1 == 0) goto L_0x00c5
            android.widget.ListView r1 = r0.mListView
            android.support.v7.app.AlertController$RecycleListView r1 = (android.support.v7.app.AlertController.RecycleListView) r1
            r1.setHasDecor(r13, r12)
        L_0x00c5:
            if (r15 != 0) goto L_0x00e6
            android.widget.ListView r1 = r0.mListView
            if (r1 == 0) goto L_0x00ce
            android.widget.ListView r1 = r0.mListView
            goto L_0x00d0
        L_0x00ce:
            android.support.v4.widget.NestedScrollView r1 = r0.mScrollView
        L_0x00d0:
            if (r1 == 0) goto L_0x00e6
            if (r13 == 0) goto L_0x00d7
            r16 = 1
            goto L_0x00d9
        L_0x00d7:
            r16 = r14
        L_0x00d9:
            if (r12 == 0) goto L_0x00dd
            r14 = 2
        L_0x00dd:
            r14 = r16 | r14
            r18 = r2
            r2 = 3
            r0.setScrollIndicators(r10, r1, r14, r2)
            goto L_0x00e8
        L_0x00e6:
            r18 = r2
        L_0x00e8:
            android.widget.ListView r1 = r0.mListView
            if (r1 == 0) goto L_0x0101
            android.widget.ListAdapter r2 = r0.mAdapter
            if (r2 == 0) goto L_0x0101
            android.widget.ListAdapter r2 = r0.mAdapter
            r1.setAdapter(r2)
            int r2 = r0.mCheckedItem
            r14 = -1
            if (r2 <= r14) goto L_0x0101
            r14 = 1
            r1.setItemChecked(r2, r14)
            r1.setSelection(r2)
        L_0x0101:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AlertController.setupView():void");
    }

    private void setScrollIndicators(ViewGroup contentPanel, View content, int indicators, int mask) {
        View indicatorUp = this.mWindow.findViewById(R.id.scrollIndicatorUp);
        View indicatorDown = this.mWindow.findViewById(R.id.scrollIndicatorDown);
        if (Build.VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(content, indicators, mask);
            if (indicatorUp != null) {
                contentPanel.removeView(indicatorUp);
            }
            if (indicatorDown != null) {
                contentPanel.removeView(indicatorDown);
                return;
            }
            return;
        }
        if (indicatorUp != null && (indicators & 1) == 0) {
            contentPanel.removeView(indicatorUp);
            indicatorUp = null;
        }
        if (indicatorDown != null && (indicators & 2) == 0) {
            contentPanel.removeView(indicatorDown);
            indicatorDown = null;
        }
        if (indicatorUp != null || indicatorDown != null) {
            final View top = indicatorUp;
            final View bottom = indicatorDown;
            if (this.mMessage != null) {
                this.mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        AlertController.manageScrollIndicators(v, top, bottom);
                    }
                });
                this.mScrollView.post(new Runnable() {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mScrollView, top, bottom);
                    }
                });
            } else if (this.mListView != null) {
                this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                    }

                    public void onScroll(AbsListView v, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        AlertController.manageScrollIndicators(v, top, bottom);
                    }
                });
                this.mListView.post(new Runnable() {
                    public void run() {
                        AlertController.manageScrollIndicators(AlertController.this.mListView, top, bottom);
                    }
                });
            } else {
                if (top != null) {
                    contentPanel.removeView(top);
                }
                if (bottom != null) {
                    contentPanel.removeView(bottom);
                }
            }
        }
    }

    private void setupCustomContent(ViewGroup customPanel) {
        View customView;
        boolean hasCustomView = false;
        if (this.mView != null) {
            customView = this.mView;
        } else if (this.mViewLayoutResId != 0) {
            customView = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, customPanel, false);
        } else {
            customView = null;
        }
        if (customView != null) {
            hasCustomView = true;
        }
        if (!hasCustomView || !canTextInput(customView)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (hasCustomView) {
            FrameLayout custom = (FrameLayout) this.mWindow.findViewById(R.id.custom);
            custom.addView(customView, new ViewGroup.LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                custom.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayoutCompat.LayoutParams) customPanel.getLayoutParams()).weight = 0.0f;
                return;
            }
            return;
        }
        customPanel.setVisibility(8);
    }

    private void setupTitle(ViewGroup topPanel) {
        if (this.mCustomTitleView != null) {
            topPanel.addView(this.mCustomTitleView, 0, new ViewGroup.LayoutParams(-1, -2));
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
            return;
        }
        this.mIconView = (ImageView) this.mWindow.findViewById(16908294);
        if (!(!TextUtils.isEmpty(this.mTitle)) || !this.mShowTitle) {
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
            this.mIconView.setVisibility(8);
            topPanel.setVisibility(8);
            return;
        }
        this.mTitleView = (TextView) this.mWindow.findViewById(R.id.alertTitle);
        this.mTitleView.setText(this.mTitle);
        if (this.mIconId != 0) {
            this.mIconView.setImageResource(this.mIconId);
        } else if (this.mIcon != null) {
            this.mIconView.setImageDrawable(this.mIcon);
        } else {
            this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
            this.mIconView.setVisibility(8);
        }
    }

    private void setupContent(ViewGroup contentPanel) {
        this.mScrollView = (NestedScrollView) this.mWindow.findViewById(R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView) contentPanel.findViewById(16908299);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
                return;
            }
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
                ViewGroup scrollParent = (ViewGroup) this.mScrollView.getParent();
                int childIndex = scrollParent.indexOfChild(this.mScrollView);
                scrollParent.removeViewAt(childIndex);
                scrollParent.addView(this.mListView, childIndex, new ViewGroup.LayoutParams(-1, -1));
                return;
            }
            contentPanel.setVisibility(8);
        }
    }

    static void manageScrollIndicators(View v, View upIndicator, View downIndicator) {
        int i = 4;
        if (upIndicator != null) {
            upIndicator.setVisibility(v.canScrollVertically(-1) ? 0 : 4);
        }
        if (downIndicator != null) {
            if (v.canScrollVertically(1)) {
                i = 0;
            }
            downIndicator.setVisibility(i);
        }
    }

    private void setupButtons(ViewGroup buttonPanel) {
        int whichButtons = 0;
        this.mButtonPositive = (Button) buttonPanel.findViewById(16908313);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        boolean hasButtons = false;
        if (!TextUtils.isEmpty(this.mButtonPositiveText) || this.mButtonPositiveIcon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonPositive.setVisibility(0);
            whichButtons = 0 | 1;
        } else {
            this.mButtonPositive.setVisibility(8);
        }
        this.mButtonNegative = (Button) buttonPanel.findViewById(16908314);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNegativeText) || this.mButtonNegativeIcon != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            if (this.mButtonNegativeIcon != null) {
                this.mButtonNegativeIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNegative.setVisibility(0);
            whichButtons |= 2;
        } else {
            this.mButtonNegative.setVisibility(8);
        }
        this.mButtonNeutral = (Button) buttonPanel.findViewById(16908315);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNeutralText) || this.mButtonNeutralIcon != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            if (this.mButtonPositiveIcon != null) {
                this.mButtonPositiveIcon.setBounds(0, 0, this.mButtonIconDimen, this.mButtonIconDimen);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNeutral.setVisibility(0);
            whichButtons |= 4;
        } else {
            this.mButtonNeutral.setVisibility(8);
        }
        if (shouldCenterSingleButton(this.mContext)) {
            if (whichButtons == 1) {
                centerButton(this.mButtonPositive);
            } else if (whichButtons == 2) {
                centerButton(this.mButtonNegative);
            } else if (whichButtons == 4) {
                centerButton(this.mButtonNeutral);
            }
        }
        if (whichButtons != 0) {
            hasButtons = true;
        }
        if (!hasButtons) {
            buttonPanel.setVisibility(8);
        }
    }

    private void centerButton(Button button) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.gravity = 1;
        params.weight = 0.5f;
        button.setLayoutParams(params);
    }

    public static class RecycleListView extends ListView {
        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;

        public RecycleListView(Context context) {
            this(context, (AttributeSet) null);
        }

        public RecycleListView(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RecycleListView);
            this.mPaddingBottomNoButtons = ta.getDimensionPixelOffset(R.styleable.RecycleListView_paddingBottomNoButtons, -1);
            this.mPaddingTopNoTitle = ta.getDimensionPixelOffset(R.styleable.RecycleListView_paddingTopNoTitle, -1);
        }

        public void setHasDecor(boolean hasTitle, boolean hasButtons) {
            if (!hasButtons || !hasTitle) {
                setPadding(getPaddingLeft(), hasTitle ? getPaddingTop() : this.mPaddingTopNoTitle, getPaddingRight(), hasButtons ? getPaddingBottom() : this.mPaddingBottomNoButtons);
            }
        }
    }

    public static class AlertParams {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId = 0;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public Drawable mNegativeButtonIcon;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public Drawable mNeutralButtonIcon;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public Drawable mPositiveButtonIcon;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void apply(AlertController dialog) {
            if (this.mCustomTitleView != null) {
                dialog.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    dialog.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    dialog.setIcon(this.mIcon);
                }
                if (this.mIconId != 0) {
                    dialog.setIcon(this.mIconId);
                }
                if (this.mIconAttrId != 0) {
                    dialog.setIcon(dialog.getIconAttributeResId(this.mIconAttrId));
                }
            }
            if (this.mMessage != null) {
                dialog.setMessage(this.mMessage);
            }
            if (!(this.mPositiveButtonText == null && this.mPositiveButtonIcon == null)) {
                dialog.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message) null, this.mPositiveButtonIcon);
            }
            if (!(this.mNegativeButtonText == null && this.mNegativeButtonIcon == null)) {
                dialog.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message) null, this.mNegativeButtonIcon);
            }
            if (!(this.mNeutralButtonText == null && this.mNeutralButtonIcon == null)) {
                dialog.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, (Message) null, this.mNeutralButtonIcon);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(dialog);
            }
            if (this.mView != null) {
                if (this.mViewSpacingSpecified) {
                    dialog.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                    return;
                }
                dialog.setView(this.mView);
            } else if (this.mViewLayoutResId != 0) {
                dialog.setView(this.mViewLayoutResId);
            }
        }

        private void createListView(final AlertController dialog) {
            ListAdapter adapter;
            int layout;
            ListAdapter r1;
            final RecycleListView listView = (RecycleListView) this.mInflater.inflate(dialog.mListLayout, (ViewGroup) null);
            if (this.mIsMultiChoice) {
                if (this.mCursor == null) {
                    final RecycleListView recycleListView = listView;
                    r1 = new ArrayAdapter<CharSequence>(this.mContext, dialog.mMultiChoiceItemLayout, 16908308, this.mItems) {
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            if (AlertParams.this.mCheckedItems != null && AlertParams.this.mCheckedItems[position]) {
                                recycleListView.setItemChecked(position, true);
                            }
                            return view;
                        }
                    };
                } else {
                    final RecycleListView recycleListView2 = listView;
                    final AlertController alertController = dialog;
                    r1 = new CursorAdapter(this.mContext, this.mCursor, false) {
                        private final int mIsCheckedIndex;
                        private final int mLabelIndex;

                        {
                            Cursor cursor = getCursor();
                            this.mLabelIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                            this.mIsCheckedIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                        }

                        public void bindView(View view, Context context, Cursor cursor) {
                            ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.mLabelIndex));
                            RecycleListView recycleListView = recycleListView2;
                            int position = cursor.getPosition();
                            boolean z = true;
                            if (cursor.getInt(this.mIsCheckedIndex) != 1) {
                                z = false;
                            }
                            recycleListView.setItemChecked(position, z);
                        }

                        public View newView(Context context, Cursor cursor, ViewGroup parent) {
                            return AlertParams.this.mInflater.inflate(alertController.mMultiChoiceItemLayout, parent, false);
                        }
                    };
                }
                adapter = r1;
            } else {
                if (this.mIsSingleChoice) {
                    layout = dialog.mSingleChoiceItemLayout;
                } else {
                    layout = dialog.mListItemLayout;
                }
                if (this.mCursor != null) {
                    adapter = new SimpleCursorAdapter(this.mContext, layout, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
                } else {
                    adapter = this.mAdapter != null ? this.mAdapter : new CheckedItemAdapter(this.mContext, layout, 16908308, this.mItems);
                }
            }
            ListAdapter adapter2 = adapter;
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(listView);
            }
            dialog.mAdapter = adapter2;
            dialog.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                        AlertParams.this.mOnClickListener.onClick(dialog.mDialog, position);
                        if (!AlertParams.this.mIsSingleChoice) {
                            dialog.mDialog.dismiss();
                        }
                    }
                });
            } else if (this.mOnCheckboxClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                        if (AlertParams.this.mCheckedItems != null) {
                            AlertParams.this.mCheckedItems[position] = listView.isItemChecked(position);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick(dialog.mDialog, position, listView.isItemChecked(position));
                    }
                });
            }
            if (this.mOnItemSelectedListener != null) {
                listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                listView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                listView.setChoiceMode(2);
            }
            dialog.mListView = listView;
        }
    }

    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int resource, int textViewResourceId, CharSequence[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        public boolean hasStableIds() {
            return true;
        }

        public long getItemId(int position) {
            return (long) position;
        }
    }
}
