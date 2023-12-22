package android.databinding;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MergedDataBinderMapper extends DataBinderMapper {
    private static final String TAG = "MergedDataBinderMapper";
    private Set<Class<? extends DataBinderMapper>> mExistingMappers = new HashSet();
    private List<DataBinderMapper> mMappers = new CopyOnWriteArrayList();
    private List<String> mFeatureBindingMappers = new CopyOnWriteArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    public void addMapper(DataBinderMapper mapper) {
        if (this.mExistingMappers.add(mapper.getClass())) {
            this.mMappers.add(mapper);
            List<DataBinderMapper> dependencies = mapper.collectDependencies();
            for (DataBinderMapper dependency : dependencies) {
                addMapper(dependency);
            }
        }
    }

    protected void addMapper(String featureMapper) {
        this.mFeatureBindingMappers.add(featureMapper + ".DataBinderMapperImpl");
    }

    @Override // android.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View view, int layoutId) {
        for (DataBinderMapper mapper : this.mMappers) {
            ViewDataBinding result = mapper.getDataBinder(bindingComponent, view, layoutId);
            if (result != null) {
                return result;
            }
        }
        if (loadFeatures()) {
            return getDataBinder(bindingComponent, view, layoutId);
        }
        return null;
    }

    @Override // android.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent bindingComponent, View[] view, int layoutId) {
        for (DataBinderMapper mapper : this.mMappers) {
            ViewDataBinding result = mapper.getDataBinder(bindingComponent, view, layoutId);
            if (result != null) {
                return result;
            }
        }
        if (loadFeatures()) {
            return getDataBinder(bindingComponent, view, layoutId);
        }
        return null;
    }

    @Override // android.databinding.DataBinderMapper
    public int getLayoutId(String tag) {
        for (DataBinderMapper mapper : this.mMappers) {
            int result = mapper.getLayoutId(tag);
            if (result != 0) {
                return result;
            }
        }
        if (loadFeatures()) {
            return getLayoutId(tag);
        }
        return 0;
    }

    @Override // android.databinding.DataBinderMapper
    public String convertBrIdToString(int id) {
        for (DataBinderMapper mapper : this.mMappers) {
            String result = mapper.convertBrIdToString(id);
            if (result != null) {
                return result;
            }
        }
        if (loadFeatures()) {
            return convertBrIdToString(id);
        }
        return null;
    }

    private boolean loadFeatures() {
        boolean found = false;
        for (String mapper : this.mFeatureBindingMappers) {
            try {
                Class<?> aClass = Class.forName(mapper);
                if (DataBinderMapper.class.isAssignableFrom(aClass)) {
                    addMapper((DataBinderMapper) aClass.newInstance());
                    this.mFeatureBindingMappers.remove(mapper);
                    found = true;
                }
            } catch (ClassNotFoundException e) {
            } catch (IllegalAccessException exception) {
                Log.e(TAG, "unable to add feature mapper for " + mapper, exception);
            } catch (InstantiationException exception2) {
                Log.e(TAG, "unable to add feature mapper for " + mapper, exception2);
            }
        }
        return found;
    }
}
