package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    private static final boolean DBG = false;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE = 0;
    private boolean mClosed = false;
    private final int mCommitIconResId;
    private int mFlagsCol = -1;
    private int mIconName1Col = -1;
    private int mIconName2Col = -1;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement = 1;
    private final SearchManager mSearchManager = ((SearchManager) this.mContext.getSystemService("search"));
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col = -1;
    private int mText2Col = -1;
    private int mText2UrlCol = -1;
    private ColorStateList mUrlColor;

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchable, WeakHashMap<String, Drawable.ConstantState> outsideDrawablesCache) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.mSearchView = searchView;
        this.mSearchable = searchable;
        this.mCommitIconResId = searchView.getSuggestionCommitIconResId();
        this.mProviderContext = context;
        this.mOutsideDrawablesCache = outsideDrawablesCache;
    }

    public void setQueryRefinement(int refineWhat) {
        this.mQueryRefinement = refineWhat;
    }

    public int getQueryRefinement() {
        return this.mQueryRefinement;
    }

    public boolean hasStableIds() {
        return false;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        String query = constraint == null ? "" : constraint.toString();
        if (this.mSearchView.getVisibility() != 0 || this.mSearchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor cursor = getSearchManagerSuggestions(this.mSearchable, query, 50);
            if (cursor != null) {
                cursor.getCount();
                return cursor;
            }
        } catch (RuntimeException e) {
            Log.w(LOG_TAG, "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    public void close() {
        changeCursor((Cursor) null);
        this.mClosed = true;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateSpinnerState(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        updateSpinnerState(getCursor());
    }

    private void updateSpinnerState(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    public void changeCursor(Cursor c) {
        if (this.mClosed) {
            Log.w(LOG_TAG, "Tried to change cursor after adapter was closed.");
            if (c != null) {
                c.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(c);
            if (c != null) {
                this.mText1Col = c.getColumnIndex("suggest_text_1");
                this.mText2Col = c.getColumnIndex("suggest_text_2");
                this.mText2UrlCol = c.getColumnIndex("suggest_text_2_url");
                this.mIconName1Col = c.getColumnIndex("suggest_icon_1");
                this.mIconName2Col = c.getColumnIndex("suggest_icon_2");
                this.mFlagsCol = c.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "error changing cursor and caching columns", e);
        }
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = super.newView(context, cursor, parent);
        v.setTag(new ChildViewCache(v));
        ((ImageView) v.findViewById(R.id.edit_query)).setImageResource(this.mCommitIconResId);
        return v;
    }

    private static final class ChildViewCache {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View v) {
            this.mText1 = (TextView) v.findViewById(16908308);
            this.mText2 = (TextView) v.findViewById(16908309);
            this.mIcon1 = (ImageView) v.findViewById(16908295);
            this.mIcon2 = (ImageView) v.findViewById(16908296);
            this.mIconRefine = (ImageView) v.findViewById(R.id.edit_query);
        }
    }

    public void bindView(View view, Context context, Cursor cursor) {
        CharSequence text2;
        ChildViewCache views = (ChildViewCache) view.getTag();
        int flags = 0;
        if (this.mFlagsCol != -1) {
            flags = cursor.getInt(this.mFlagsCol);
        }
        if (views.mText1 != null) {
            setViewText(views.mText1, getStringOrNull(cursor, this.mText1Col));
        }
        if (views.mText2 != null) {
            CharSequence text22 = getStringOrNull(cursor, this.mText2UrlCol);
            if (text22 != null) {
                text2 = formatUrl(text22);
            } else {
                text2 = getStringOrNull(cursor, this.mText2Col);
            }
            if (TextUtils.isEmpty(text2)) {
                if (views.mText1 != null) {
                    views.mText1.setSingleLine(false);
                    views.mText1.setMaxLines(2);
                }
            } else if (views.mText1 != null) {
                views.mText1.setSingleLine(true);
                views.mText1.setMaxLines(1);
            }
            setViewText(views.mText2, text2);
        }
        if (views.mIcon1 != null) {
            setViewDrawable(views.mIcon1, getIcon1(cursor), 4);
        }
        if (views.mIcon2 != null) {
            setViewDrawable(views.mIcon2, getIcon2(cursor), 8);
        }
        if (this.mQueryRefinement == 2 || (this.mQueryRefinement == 1 && (flags & 1) != 0)) {
            views.mIconRefine.setVisibility(0);
            views.mIconRefine.setTag(views.mText1.getText());
            views.mIconRefine.setOnClickListener(this);
            return;
        }
        views.mIconRefine.setVisibility(8);
    }

    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence) tag);
        }
    }

    private CharSequence formatUrl(CharSequence url) {
        if (this.mUrlColor == null) {
            TypedValue colorValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, colorValue, true);
            this.mUrlColor = this.mContext.getResources().getColorStateList(colorValue.resourceId);
        }
        SpannableString text = new SpannableString(url);
        text.setSpan(new TextAppearanceSpan((String) null, 0, 0, this.mUrlColor, (ColorStateList) null), 0, url.length(), 33);
        return text;
    }

    private void setViewText(TextView v, CharSequence text) {
        v.setText(text);
        if (TextUtils.isEmpty(text)) {
            v.setVisibility(8);
        } else {
            v.setVisibility(0);
        }
    }

    private Drawable getIcon1(Cursor cursor) {
        if (this.mIconName1Col == -1) {
            return null;
        }
        Drawable drawable = getDrawableFromResourceValue(cursor.getString(this.mIconName1Col));
        if (drawable != null) {
            return drawable;
        }
        return getDefaultIcon1(cursor);
    }

    private Drawable getIcon2(Cursor cursor) {
        if (this.mIconName2Col == -1) {
            return null;
        }
        return getDrawableFromResourceValue(cursor.getString(this.mIconName2Col));
    }

    private void setViewDrawable(ImageView v, Drawable drawable, int nullVisibility) {
        v.setImageDrawable(drawable);
        if (drawable == null) {
            v.setVisibility(nullVisibility);
            return;
        }
        v.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    public CharSequence convertToString(Cursor cursor) {
        String text1;
        String data;
        if (cursor == null) {
            return null;
        }
        String query = getColumnString(cursor, "suggest_intent_query");
        if (query != null) {
            return query;
        }
        if (this.mSearchable.shouldRewriteQueryFromData() && (data = getColumnString(cursor, "suggest_intent_data")) != null) {
            return data;
        }
        if (!this.mSearchable.shouldRewriteQueryFromText() || (text1 = getColumnString(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return text1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            return super.getView(position, convertView, parent);
        } catch (RuntimeException e) {
            Log.w(LOG_TAG, "Search suggestions cursor threw exception.", e);
            View v = newView(this.mContext, this.mCursor, parent);
            if (v != null) {
                ((ChildViewCache) v.getTag()).mText1.setText(e.toString());
            }
            return v;
        }
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        try {
            return super.getDropDownView(position, convertView, parent);
        } catch (RuntimeException e) {
            Log.w(LOG_TAG, "Search suggestions cursor threw exception.", e);
            View v = newDropDownView(this.mContext, this.mCursor, parent);
            if (v != null) {
                ((ChildViewCache) v.getTag()).mText1.setText(e.toString());
            }
            return v;
        }
    }

    private Drawable getDrawableFromResourceValue(String drawableId) {
        if (drawableId == null || drawableId.isEmpty() || "0".equals(drawableId)) {
            return null;
        }
        try {
            int resourceId = Integer.parseInt(drawableId);
            String drawableUri = "android.resource://" + this.mProviderContext.getPackageName() + "/" + resourceId;
            Drawable drawable = checkIconCache(drawableUri);
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = ContextCompat.getDrawable(this.mProviderContext, resourceId);
            storeInIconCache(drawableUri, drawable2);
            return drawable2;
        } catch (NumberFormatException e) {
            Drawable drawable3 = checkIconCache(drawableId);
            if (drawable3 != null) {
                return drawable3;
            }
            Drawable drawable4 = getDrawable(Uri.parse(drawableId));
            storeInIconCache(drawableId, drawable4);
            return drawable4;
        } catch (Resources.NotFoundException e2) {
            Log.w(LOG_TAG, "Icon resource not found: " + drawableId);
            return null;
        }
    }

    private Drawable getDrawable(Uri uri) {
        InputStream stream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return getDrawableFromResourceUri(uri);
            }
            stream = this.mProviderContext.getContentResolver().openInputStream(uri);
            if (stream != null) {
                Drawable createFromStream = Drawable.createFromStream(stream, (String) null);
                try {
                    stream.close();
                } catch (IOException ex) {
                    Log.e(LOG_TAG, "Error closing icon stream for " + uri, ex);
                }
                return createFromStream;
            }
            throw new FileNotFoundException("Failed to open " + uri);
        } catch (Resources.NotFoundException e) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException fnfe) {
            Log.w(LOG_TAG, "Icon not found: " + uri + ", " + fnfe.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                stream.close();
            } catch (IOException ex2) {
                Log.e(LOG_TAG, "Error closing icon stream for " + uri, ex2);
            }
            throw th;
        }
    }

    private Drawable checkIconCache(String resourceUri) {
        Drawable.ConstantState cached = this.mOutsideDrawablesCache.get(resourceUri);
        if (cached == null) {
            return null;
        }
        return cached.newDrawable();
    }

    private void storeInIconCache(String resourceUri, Drawable drawable) {
        if (drawable != null) {
            this.mOutsideDrawablesCache.put(resourceUri, drawable.getConstantState());
        }
    }

    private Drawable getDefaultIcon1(Cursor cursor) {
        Drawable drawable = getActivityIconWithCache(this.mSearchable.getSearchActivity());
        if (drawable != null) {
            return drawable;
        }
        return this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable getActivityIconWithCache(ComponentName component) {
        String componentIconKey = component.flattenToShortString();
        Drawable.ConstantState toCache = null;
        if (this.mOutsideDrawablesCache.containsKey(componentIconKey)) {
            Drawable.ConstantState cached = this.mOutsideDrawablesCache.get(componentIconKey);
            if (cached == null) {
                return null;
            }
            return cached.newDrawable(this.mProviderContext.getResources());
        }
        Drawable drawable = getActivityIcon(component);
        if (drawable != null) {
            toCache = drawable.getConstantState();
        }
        this.mOutsideDrawablesCache.put(componentIconKey, toCache);
        return drawable;
    }

    private Drawable getActivityIcon(ComponentName component) {
        PackageManager pm = this.mContext.getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(component, 128);
            int iconId = activityInfo.getIconResource();
            if (iconId == 0) {
                return null;
            }
            Drawable drawable = pm.getDrawable(component.getPackageName(), iconId, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w(LOG_TAG, "Invalid icon resource " + iconId + " for " + component.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException ex) {
            Log.w(LOG_TAG, ex.toString());
            return null;
        }
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return getStringOrNull(cursor, cursor.getColumnIndex(columnName));
    }

    private static String getStringOrNull(Cursor cursor, int col) {
        if (col == -1) {
            return null;
        }
        try {
            return cursor.getString(col);
        } catch (Exception e) {
            Log.e(LOG_TAG, "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Drawable getDrawableFromResourceUri(Uri uri) throws FileNotFoundException {
        int id;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources r = this.mContext.getPackageManager().getResourcesForApplication(authority);
                List<String> path = uri.getPathSegments();
                if (path != null) {
                    int len = path.size();
                    if (len == 1) {
                        try {
                            id = Integer.parseInt(path.get(0));
                        } catch (NumberFormatException e) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (len == 2) {
                        id = r.getIdentifier(path.get(1), path.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (id != 0) {
                        return r.getDrawable(id);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException e2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor getSearchManagerSuggestions(SearchableInfo searchable, String query, int limit) {
        String authority;
        if (searchable == null || (authority = searchable.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder uriBuilder = new Uri.Builder().scheme("content").authority(authority).query("").fragment("");
        String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            uriBuilder.appendEncodedPath(contentPath);
        }
        uriBuilder.appendPath("search_suggest_query");
        String selection = searchable.getSuggestSelection();
        String[] selArgs = null;
        if (selection != null) {
            selArgs = new String[]{query};
        } else {
            uriBuilder.appendPath(query);
        }
        String[] selArgs2 = selArgs;
        if (limit > 0) {
            uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
        }
        return this.mContext.getContentResolver().query(uriBuilder.build(), (String[]) null, selection, selArgs2, (String) null);
    }
}
