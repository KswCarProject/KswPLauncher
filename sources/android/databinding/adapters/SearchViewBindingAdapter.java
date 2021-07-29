package android.databinding.adapters;

import android.os.Build;
import android.widget.SearchView;

public class SearchViewBindingAdapter {

    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    public static void setOnQueryTextListener(SearchView view, final OnQueryTextSubmit submit, final OnQueryTextChange change) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnQueryTextListener((SearchView.OnQueryTextListener) null);
        } else {
            view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                public boolean onQueryTextSubmit(String query) {
                    OnQueryTextSubmit onQueryTextSubmit = submit;
                    if (onQueryTextSubmit != null) {
                        return onQueryTextSubmit.onQueryTextSubmit(query);
                    }
                    return false;
                }

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

    public static void setOnSuggestListener(SearchView view, final OnSuggestionSelect submit, final OnSuggestionClick change) {
        if (Build.VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnSuggestionListener((SearchView.OnSuggestionListener) null);
        } else {
            view.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                public boolean onSuggestionSelect(int position) {
                    OnSuggestionSelect onSuggestionSelect = submit;
                    if (onSuggestionSelect != null) {
                        return onSuggestionSelect.onSuggestionSelect(position);
                    }
                    return false;
                }

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
