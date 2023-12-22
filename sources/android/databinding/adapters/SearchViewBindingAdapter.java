package android.databinding.adapters;

import android.os.Build;
import android.widget.SearchView;

/* loaded from: classes.dex */
public class SearchViewBindingAdapter {

    /* loaded from: classes.dex */
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    /* loaded from: classes.dex */
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    /* loaded from: classes.dex */
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    /* loaded from: classes.dex */
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    public static void setOnQueryTextListener(SearchView view, final OnQueryTextSubmit submit, final OnQueryTextChange change) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (submit == null && change == null) {
                view.setOnQueryTextListener(null);
            } else {
                view.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: android.databinding.adapters.SearchViewBindingAdapter.1
                    @Override // android.widget.SearchView.OnQueryTextListener
                    public boolean onQueryTextSubmit(String query) {
                        OnQueryTextSubmit onQueryTextSubmit = OnQueryTextSubmit.this;
                        if (onQueryTextSubmit != null) {
                            return onQueryTextSubmit.onQueryTextSubmit(query);
                        }
                        return false;
                    }

                    @Override // android.widget.SearchView.OnQueryTextListener
                    public boolean onQueryTextChange(String newText) {
                        OnQueryTextChange onQueryTextChange = change;
                        if (onQueryTextChange != null) {
                            return onQueryTextChange.onQueryTextChange(newText);
                        }
                        return false;
                    }
                });
            }
        }
    }

    public static void setOnSuggestListener(SearchView view, final OnSuggestionSelect submit, final OnSuggestionClick change) {
        if (Build.VERSION.SDK_INT >= 11) {
            if (submit == null && change == null) {
                view.setOnSuggestionListener(null);
            } else {
                view.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: android.databinding.adapters.SearchViewBindingAdapter.2
                    @Override // android.widget.SearchView.OnSuggestionListener
                    public boolean onSuggestionSelect(int position) {
                        OnSuggestionSelect onSuggestionSelect = OnSuggestionSelect.this;
                        if (onSuggestionSelect != null) {
                            return onSuggestionSelect.onSuggestionSelect(position);
                        }
                        return false;
                    }

                    @Override // android.widget.SearchView.OnSuggestionListener
                    public boolean onSuggestionClick(int position) {
                        OnSuggestionClick onSuggestionClick = change;
                        if (onSuggestionClick != null) {
                            return onSuggestionClick.onSuggestionClick(position);
                        }
                        return false;
                    }
                });
            }
        }
    }
}
