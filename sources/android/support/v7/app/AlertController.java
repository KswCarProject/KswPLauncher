package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
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
            switch (msg.what) {
                case FontsContractCompat.FontRequestCallback.FAIL_REASON_FONT_LOAD_ERROR:
                case -2:
                case -1:
                    ((DialogInterface.OnClickListener) msg.obj).onClick((DialogInterface) this.mDialog.get(), msg.what);
                    return;
                case 1:
                    ((DialogInterface) msg.obj).dismiss();
                    return;
                default:
                    return;
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
        int i = this.mButtonPanelSideLayout;
        if (i == 0) {
            return this.mAlertDialogLayout;
        }
        if (this.mButtonPanelLayoutHint == 1) {
            return i;
        }
        return this.mAlertDialogLayout;
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(title);
        }
    }

    public void setCustomTitle(View customTitleView) {
        this.mCustomTitleView = customTitleView;
    }

    public void setMessage(CharSequence message) {
        this.mMessage = message;
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(message);
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
        ImageView imageView = this.mIconView;
        if (imageView == null) {
            return;
        }
        if (resId != 0) {
            imageView.setVisibility(0);
            this.mIconView.setImageResource(this.mIconId);
            return;
        }
        imageView.setVisibility(8);
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
        this.mIconId = 0;
        ImageView imageView = this.mIconView;
        if (imageView == null) {
            return;
        }
        if (icon != null) {
            imageView.setVisibility(0);
            this.mIconView.setImageDrawable(icon);
            return;
        }
        imageView.setVisibility(8);
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
        NestedScrollView nestedScrollView = this.mScrollView;
        return nestedScrollView != null && nestedScrollView.executeKeyEvent(event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        NestedScrollView nestedScrollView = this.mScrollView;
        return nestedScrollView != null && nestedScrollView.executeKeyEvent(event);
    }

    private ViewGroup resolvePanel(View customPanel, View defaultPanel) {
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

    private void setupView() {
        ListAdapter listAdapter;
        View parentPanel = this.mWindow.findViewById(R.id.parentPanel);
        View defaultTopPanel = parentPanel.findViewById(R.id.topPanel);
        View defaultContentPanel = parentPanel.findViewById(R.id.contentPanel);
        View defaultButtonPanel = parentPanel.findViewById(R.id.buttonPanel);
        ViewGroup customPanel = (ViewGroup) parentPanel.findViewById(R.id.customPanel);
        setupCustomContent(customPanel);
        View customTopPanel = customPanel.findViewById(R.id.topPanel);
        View customContentPanel = customPanel.findViewById(R.id.contentPanel);
        View customButtonPanel = customPanel.findViewById(R.id.buttonPanel);
        ViewGroup topPanel = resolvePanel(customTopPanel, defaultTopPanel);
        ViewGroup contentPanel = resolvePanel(customContentPanel, defaultContentPanel);
        ViewGroup buttonPanel = resolvePanel(customButtonPanel, defaultButtonPanel);
        setupContent(contentPanel);
        setupButtons(buttonPanel);
        setupTitle(topPanel);
        boolean hasCustomPanel = (customPanel == null || customPanel.getVisibility() == 8) ? false : true;
        boolean hasTopPanel = (topPanel == null || topPanel.getVisibility() == 8) ? false : true;
        boolean hasButtonPanel = (buttonPanel == null || buttonPanel.getVisibility() == 8) ? false : true;
        if (hasButtonPanel) {
        } else if (contentPanel != null) {
            View spacer = contentPanel.findViewById(R.id.textSpacerNoButtons);
            if (spacer != null) {
                View view = parentPanel;
                spacer.setVisibility(0);
            }
        }
        if (hasTopPanel) {
            NestedScrollView nestedScrollView = this.mScrollView;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View divider = null;
            if (!(this.mMessage == null && this.mListView == null)) {
                divider = topPanel.findViewById(R.id.titleDividerNoCustom);
            }
            if (divider != null) {
                divider.setVisibility(0);
            }
        } else if (contentPanel != null) {
            View spacer2 = contentPanel.findViewById(R.id.textSpacerNoTitle);
            if (spacer2 != null) {
                spacer2.setVisibility(0);
            }
        }
        ListView listView = this.mListView;
        if (listView instanceof RecycleListView) {
            ((RecycleListView) listView).setHasDecor(hasTopPanel, hasButtonPanel);
        }
        if (!hasCustomPanel) {
            View content = this.mListView;
            if (content == null) {
                content = this.mScrollView;
            }
            if (content != null) {
                View view2 = defaultTopPanel;
                setScrollIndicators(contentPanel, content, (hasTopPanel ? 1 : 0) | (hasButtonPanel ? 2 : 0), 3);
            }
        }
        ListView listView2 = this.mListView;
        if (listView2 != null && (listAdapter = this.mAdapter) != null) {
            listView2.setAdapter(listAdapter);
            int checkedItem = this.mCheckedItem;
            if (checkedItem > -1) {
                listView2.setItemChecked(checkedItem, true);
                listView2.setSelection(checkedItem);
            }
        }
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
                return;
            }
            ListView listView = this.mListView;
            if (listView != null) {
                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
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
                return;
            }
            if (top != null) {
                contentPanel.removeView(top);
            }
            if (bottom != null) {
                contentPanel.removeView(bottom);
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
        TextView textView = (TextView) this.mWindow.findViewById(R.id.alertTitle);
        this.mTitleView = textView;
        textView.setText(this.mTitle);
        int i = this.mIconId;
        if (i != 0) {
            this.mIconView.setImageResource(i);
            return;
        }
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            this.mIconView.setImageDrawable(drawable);
            return;
        }
        this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
        this.mIconView.setVisibility(8);
    }

    private void setupContent(ViewGroup contentPanel) {
        NestedScrollView nestedScrollView = (NestedScrollView) this.mWindow.findViewById(R.id.scrollView);
        this.mScrollView = nestedScrollView;
        nestedScrollView.setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        TextView textView = (TextView) contentPanel.findViewById(16908299);
        this.mMessageView = textView;
        if (textView != null) {
            CharSequence charSequence = this.mMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
                return;
            }
            textView.setVisibility(8);
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
        int i = 0;
        if (upIndicator != null) {
            upIndicator.setVisibility(v.canScrollVertically(-1) ? 0 : 4);
        }
        if (downIndicator != null) {
            if (!v.canScrollVertically(1)) {
                i = 4;
            }
            downIndicator.setVisibility(i);
        }
    }

    private void setupButtons(ViewGroup buttonPanel) {
        int whichButtons = 0;
        Button button = (Button) buttonPanel.findViewById(16908313);
        this.mButtonPositive = button;
        button.setOnClickListener(this.mButtonHandler);
        boolean hasButtons = false;
        if (!TextUtils.isEmpty(this.mButtonPositiveText) || this.mButtonPositiveIcon != null) {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            Drawable drawable = this.mButtonPositiveIcon;
            if (drawable != null) {
                int i = this.mButtonIconDimen;
                drawable.setBounds(0, 0, i, i);
                this.mButtonPositive.setCompoundDrawables(this.mButtonPositiveIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonPositive.setVisibility(0);
            whichButtons = 0 | 1;
        } else {
            this.mButtonPositive.setVisibility(8);
        }
        Button button2 = (Button) buttonPanel.findViewById(16908314);
        this.mButtonNegative = button2;
        button2.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNegativeText) || this.mButtonNegativeIcon != null) {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            Drawable drawable2 = this.mButtonNegativeIcon;
            if (drawable2 != null) {
                int i2 = this.mButtonIconDimen;
                drawable2.setBounds(0, 0, i2, i2);
                this.mButtonNegative.setCompoundDrawables(this.mButtonNegativeIcon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
            this.mButtonNegative.setVisibility(0);
            whichButtons |= 2;
        } else {
            this.mButtonNegative.setVisibility(8);
        }
        Button button3 = (Button) buttonPanel.findViewById(16908315);
        this.mButtonNeutral = button3;
        button3.setOnClickListener(this.mButtonHandler);
        if (!TextUtils.isEmpty(this.mButtonNeutralText) || this.mButtonNeutralIcon != null) {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            Drawable drawable3 = this.mButtonPositiveIcon;
            if (drawable3 != null) {
                int i3 = this.mButtonIconDimen;
                drawable3.setBounds(0, 0, i3, i3);
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
            View view = this.mCustomTitleView;
            if (view != null) {
                dialog.setCustomTitle(view);
            } else {
                CharSequence charSequence = this.mTitle;
                if (charSequence != null) {
                    dialog.setTitle(charSequence);
                }
                Drawable drawable = this.mIcon;
                if (drawable != null) {
                    dialog.setIcon(drawable);
                }
                int i = this.mIconId;
                if (i != 0) {
                    dialog.setIcon(i);
                }
                int i2 = this.mIconAttrId;
                if (i2 != 0) {
                    dialog.setIcon(dialog.getIconAttributeResId(i2));
                }
            }
            CharSequence charSequence2 = this.mMessage;
            if (charSequence2 != null) {
                dialog.setMessage(charSequence2);
            }
            CharSequence charSequence3 = this.mPositiveButtonText;
            if (!(charSequence3 == null && this.mPositiveButtonIcon == null)) {
                dialog.setButton(-1, charSequence3, this.mPositiveButtonListener, (Message) null, this.mPositiveButtonIcon);
            }
            CharSequence charSequence4 = this.mNegativeButtonText;
            if (!(charSequence4 == null && this.mNegativeButtonIcon == null)) {
                dialog.setButton(-2, charSequence4, this.mNegativeButtonListener, (Message) null, this.mNegativeButtonIcon);
            }
            CharSequence charSequence5 = this.mNeutralButtonText;
            if (!(charSequence5 == null && this.mNeutralButtonIcon == null)) {
                dialog.setButton(-3, charSequence5, this.mNeutralButtonListener, (Message) null, this.mNeutralButtonIcon);
            }
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(dialog);
            }
            View view2 = this.mView;
            if (view2 == null) {
                int i3 = this.mViewLayoutResId;
                if (i3 != 0) {
                    dialog.setView(i3);
                }
            } else if (this.mViewSpacingSpecified) {
                dialog.setView(view2, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            } else {
                dialog.setView(view2);
            }
        }

        private void createListView(final AlertController dialog) {
            ListAdapter adapter;
            int layout;
            final RecycleListView listView = (RecycleListView) this.mInflater.inflate(dialog.mListLayout, (ViewGroup) null);
            if (!this.mIsMultiChoice) {
                if (this.mIsSingleChoice) {
                    layout = dialog.mSingleChoiceItemLayout;
                } else {
                    layout = dialog.mListItemLayout;
                }
                if (this.mCursor != null) {
                    adapter = new SimpleCursorAdapter(this.mContext, layout, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
                } else if (this.mAdapter != null) {
                    adapter = this.mAdapter;
                } else {
                    adapter = new CheckedItemAdapter(this.mContext, layout, 16908308, this.mItems);
                }
            } else if (this.mCursor == null) {
                final RecycleListView recycleListView = listView;
                adapter = new ArrayAdapter<CharSequence>(this.mContext, dialog.mMultiChoiceItemLayout, 16908308, this.mItems) {
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
                adapter = new CursorAdapter(this.mContext, this.mCursor, false) {
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
            OnPrepareListViewListener onPrepareListViewListener = this.mOnPrepareListViewListener;
            if (onPrepareListViewListener != null) {
                onPrepareListViewListener.onPrepareListView(listView);
            }
            dialog.mAdapter = adapter;
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
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
            if (onItemSelectedListener != null) {
                listView.setOnItemSelectedListener(onItemSelectedListener);
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
