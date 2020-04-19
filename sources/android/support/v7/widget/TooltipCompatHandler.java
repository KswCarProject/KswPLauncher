package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TooltipCompatHandler implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static final long HOVER_HIDE_TIMEOUT_MS = 15000;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500;
    private static final String TAG = "TooltipCompatHandler";
    private static TooltipCompatHandler sActiveHandler;
    private static TooltipCompatHandler sPendingHandler;
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mFromTouch;
    private final Runnable mHideRunnable = new Runnable() {
        public void run() {
            TooltipCompatHandler.this.hide();
        }
    };
    private final int mHoverSlop;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable = new Runnable() {
        public void run() {
            TooltipCompatHandler.this.show(false);
        }
    };
    private final CharSequence mTooltipText;

    public static void setTooltipText(View view, CharSequence tooltipText) {
        if (sPendingHandler != null && sPendingHandler.mAnchor == view) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        if (TextUtils.isEmpty(tooltipText)) {
            if (sActiveHandler != null && sActiveHandler.mAnchor == view) {
                sActiveHandler.hide();
            }
            view.setOnLongClickListener((View.OnLongClickListener) null);
            view.setLongClickable(false);
            view.setOnHoverListener((View.OnHoverListener) null);
            return;
        }
        new TooltipCompatHandler(view, tooltipText);
    }

    private TooltipCompatHandler(View anchor, CharSequence tooltipText) {
        this.mAnchor = anchor;
        this.mTooltipText = tooltipText;
        this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(this.mAnchor.getContext()));
        clearAnchorPos();
        this.mAnchor.setOnLongClickListener(this);
        this.mAnchor.setOnHoverListener(this);
    }

    public boolean onLongClick(View v) {
        this.mAnchorX = v.getWidth() / 2;
        this.mAnchorY = v.getHeight() / 2;
        show(true);
        return true;
    }

    public boolean onHover(View v, MotionEvent event) {
        if (this.mPopup != null && this.mFromTouch) {
            return false;
        }
        AccessibilityManager manager = (AccessibilityManager) this.mAnchor.getContext().getSystemService("accessibility");
        if (manager.isEnabled() && manager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action != 7) {
            if (action == 10) {
                clearAnchorPos();
                hide();
            }
        } else if (this.mAnchor.isEnabled() && this.mPopup == null && updateAnchorPos(event)) {
            setPendingHandler(this);
        }
        return false;
    }

    public void onViewAttachedToWindow(View v) {
    }

    public void onViewDetachedFromWindow(View v) {
        hide();
    }

    /* access modifiers changed from: package-private */
    public void show(boolean fromTouch) {
        long timeout;
        if (ViewCompat.isAttachedToWindow(this.mAnchor)) {
            setPendingHandler((TooltipCompatHandler) null);
            if (sActiveHandler != null) {
                sActiveHandler.hide();
            }
            sActiveHandler = this;
            this.mFromTouch = fromTouch;
            this.mPopup = new TooltipPopup(this.mAnchor.getContext());
            this.mPopup.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
            this.mAnchor.addOnAttachStateChangeListener(this);
            if (this.mFromTouch) {
                timeout = LONG_CLICK_HIDE_TIMEOUT_MS;
            } else if ((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
                timeout = HOVER_HIDE_TIMEOUT_SHORT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            } else {
                timeout = HOVER_HIDE_TIMEOUT_MS - ((long) ViewConfiguration.getLongPressTimeout());
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
            this.mAnchor.postDelayed(this.mHideRunnable, timeout);
        }
    }

    /* access modifiers changed from: package-private */
    public void hide() {
        if (sActiveHandler == this) {
            sActiveHandler = null;
            if (this.mPopup != null) {
                this.mPopup.hide();
                this.mPopup = null;
                clearAnchorPos();
                this.mAnchor.removeOnAttachStateChangeListener(this);
            } else {
                Log.e(TAG, "sActiveHandler.mPopup == null");
            }
        }
        if (sPendingHandler == this) {
            setPendingHandler((TooltipCompatHandler) null);
        }
        this.mAnchor.removeCallbacks(this.mHideRunnable);
    }

    private static void setPendingHandler(TooltipCompatHandler handler) {
        if (sPendingHandler != null) {
            sPendingHandler.cancelPendingShow();
        }
        sPendingHandler = handler;
        if (sPendingHandler != null) {
            sPendingHandler.scheduleShow();
        }
    }

    private void scheduleShow() {
        this.mAnchor.postDelayed(this.mShowRunnable, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void cancelPendingShow() {
        this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private boolean updateAnchorPos(MotionEvent event) {
        int newAnchorX = (int) event.getX();
        int newAnchorY = (int) event.getY();
        if (Math.abs(newAnchorX - this.mAnchorX) <= this.mHoverSlop && Math.abs(newAnchorY - this.mAnchorY) <= this.mHoverSlop) {
            return false;
        }
        this.mAnchorX = newAnchorX;
        this.mAnchorY = newAnchorY;
        return true;
    }

    private void clearAnchorPos() {
        this.mAnchorX = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mAnchorY = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }
}
