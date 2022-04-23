package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.List;
import java.util.Map;

public class GlideContext extends ContextWrapper {
    static final TransitionOptions<?, ?> DEFAULT_TRANSITION_OPTIONS = new GenericTransitionOptions();
    private final ArrayPool arrayPool;
    private final List<RequestListener<Object>> defaultRequestListeners;
    private final RequestOptions defaultRequestOptions;
    private final Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions;
    private final Engine engine;
    private final ImageViewTargetFactory imageViewTargetFactory;
    private final boolean isLoggingRequestOriginsEnabled;
    private final int logLevel;
    private final Registry registry;

    public GlideContext(Context context, ArrayPool arrayPool2, Registry registry2, ImageViewTargetFactory imageViewTargetFactory2, RequestOptions defaultRequestOptions2, Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions2, List<RequestListener<Object>> defaultRequestListeners2, Engine engine2, boolean isLoggingRequestOriginsEnabled2, int logLevel2) {
        super(context.getApplicationContext());
        this.arrayPool = arrayPool2;
        this.registry = registry2;
        this.imageViewTargetFactory = imageViewTargetFactory2;
        this.defaultRequestOptions = defaultRequestOptions2;
        this.defaultRequestListeners = defaultRequestListeners2;
        this.defaultTransitionOptions = defaultTransitionOptions2;
        this.engine = engine2;
        this.isLoggingRequestOriginsEnabled = isLoggingRequestOriginsEnabled2;
        this.logLevel = logLevel2;
    }

    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.defaultRequestListeners;
    }

    public RequestOptions getDefaultRequestOptions() {
        return this.defaultRequestOptions;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.bumptech.glide.TransitionOptions<?, T>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.bumptech.glide.TransitionOptions<?, T> getDefaultTransitionOptions(java.lang.Class<T> r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.TransitionOptions<?, ?>> r0 = r4.defaultTransitionOptions
            java.lang.Object r0 = r0.get(r5)
            com.bumptech.glide.TransitionOptions r0 = (com.bumptech.glide.TransitionOptions) r0
            if (r0 != 0) goto L_0x0034
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.TransitionOptions<?, ?>> r1 = r4.defaultTransitionOptions
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0014:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0034
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r3 = r3.isAssignableFrom(r5)
            if (r3 == 0) goto L_0x0033
            java.lang.Object r3 = r2.getValue()
            r0 = r3
            com.bumptech.glide.TransitionOptions r0 = (com.bumptech.glide.TransitionOptions) r0
        L_0x0033:
            goto L_0x0014
        L_0x0034:
            if (r0 != 0) goto L_0x0038
            com.bumptech.glide.TransitionOptions<?, ?> r0 = DEFAULT_TRANSITION_OPTIONS
        L_0x0038:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.GlideContext.getDefaultTransitionOptions(java.lang.Class):com.bumptech.glide.TransitionOptions");
    }

    public <X> ViewTarget<ImageView, X> buildImageViewTarget(ImageView imageView, Class<X> transcodeClass) {
        return this.imageViewTargetFactory.buildTarget(imageView, transcodeClass);
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Registry getRegistry() {
        return this.registry;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public boolean isLoggingRequestOriginsEnabled() {
        return this.isLoggingRequestOriginsEnabled;
    }
}
