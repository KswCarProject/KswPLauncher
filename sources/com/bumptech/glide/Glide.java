package com.bumptech.glide;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.data.InputStreamRewinder;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.BitmapPreFiller;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferEncoder;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.DataUrlLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.MediaStoreFileLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.MediaStoreImageThumbLoader;
import com.bumptech.glide.load.model.stream.MediaStoreVideoThumbLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableDecoder;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableEncoder;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.ByteBufferBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.ResourceBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.UnitBitmapDecoder;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.drawable.UnitDrawableDecoder;
import com.bumptech.glide.load.resource.file.FileDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableEncoder;
import com.bumptech.glide.load.resource.gif.GifFrameResourceDecoder;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.BitmapDrawableTranscoder;
import com.bumptech.glide.load.resource.transcode.DrawableBytesTranscoder;
import com.bumptech.glide.load.resource.transcode.GifDrawableBytesTranscoder;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.module.ManifestParser;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Glide implements ComponentCallbacks2 {
    private static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";
    private static final String TAG = "Glide";
    private static volatile Glide glide;
    private static volatile boolean isInitializing;
    private final ArrayPool arrayPool;
    private final BitmapPool bitmapPool;
    private final BitmapPreFiller bitmapPreFiller;
    private final ConnectivityMonitorFactory connectivityMonitorFactory;
    private final Engine engine;
    private final GlideContext glideContext;
    private final List<RequestManager> managers = new ArrayList();
    private final MemoryCache memoryCache;
    private MemoryCategory memoryCategory = MemoryCategory.NORMAL;
    private final Registry registry;
    private final RequestManagerRetriever requestManagerRetriever;

    public static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, "image_manager_disk_cache");
    }

    public static File getPhotoCacheDir(Context context, String cacheName) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File result = new File(cacheDir, cacheName);
            if (result.mkdirs() || (result.exists() && result.isDirectory())) {
                return result;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    public static Glide get(Context context) {
        if (glide == null) {
            synchronized (Glide.class) {
                if (glide == null) {
                    checkAndInitializeGlide(context);
                }
            }
        }
        return glide;
    }

    private static void checkAndInitializeGlide(Context context) {
        if (!isInitializing) {
            isInitializing = true;
            initializeGlide(context);
            isInitializing = false;
            return;
        }
        throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
    }

    @Deprecated
    public static synchronized void init(Glide glide2) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            glide = glide2;
        }
    }

    public static synchronized void init(Context context, GlideBuilder builder) {
        synchronized (Glide.class) {
            if (glide != null) {
                tearDown();
            }
            initializeGlide(context, builder);
        }
    }

    public static synchronized void tearDown() {
        synchronized (Glide.class) {
            if (glide != null) {
                glide.getContext().getApplicationContext().unregisterComponentCallbacks(glide);
                glide.engine.shutdown();
            }
            glide = null;
        }
    }

    private static void initializeGlide(Context context) {
        initializeGlide(context, new GlideBuilder());
    }

    private static void initializeGlide(Context context, GlideBuilder builder) {
        Context applicationContext = context.getApplicationContext();
        GeneratedAppGlideModule annotationGeneratedModule = getAnnotationGeneratedGlideModules();
        List<GlideModule> manifestModules = Collections.emptyList();
        if (annotationGeneratedModule == null || annotationGeneratedModule.isManifestParsingEnabled()) {
            manifestModules = new ManifestParser(applicationContext).parse();
        }
        if (annotationGeneratedModule != null && !annotationGeneratedModule.getExcludedModuleClasses().isEmpty()) {
            Set<Class<?>> excludedModuleClasses = annotationGeneratedModule.getExcludedModuleClasses();
            Iterator<GlideModule> iterator = manifestModules.iterator();
            while (iterator.hasNext()) {
                GlideModule current = iterator.next();
                if (excludedModuleClasses.contains(current.getClass())) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "AppGlideModule excludes manifest GlideModule: " + current);
                    }
                    iterator.remove();
                }
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            for (GlideModule glideModule : manifestModules) {
                Log.d(TAG, "Discovered GlideModule from manifest: " + glideModule.getClass());
            }
        }
        builder.setRequestManagerFactory(annotationGeneratedModule != null ? annotationGeneratedModule.getRequestManagerFactory() : null);
        for (GlideModule module : manifestModules) {
            module.applyOptions(applicationContext, builder);
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.applyOptions(applicationContext, builder);
        }
        Glide glide2 = builder.build(applicationContext);
        for (GlideModule module2 : manifestModules) {
            module2.registerComponents(applicationContext, glide2, glide2.registry);
        }
        if (annotationGeneratedModule != null) {
            annotationGeneratedModule.registerComponents(applicationContext, glide2, glide2.registry);
        }
        applicationContext.registerComponentCallbacks(glide2);
        glide = glide2;
    }

    private static GeneratedAppGlideModule getAnnotationGeneratedGlideModules() {
        try {
            return (GeneratedAppGlideModule) Class.forName("com.bumptech.glide.GeneratedAppGlideModuleImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            if (!Log.isLoggable(TAG, 5)) {
                return null;
            }
            Log.w(TAG, "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            return null;
        } catch (InstantiationException e2) {
            throwIncorrectGlideModule(e2);
            return null;
        } catch (IllegalAccessException e3) {
            throwIncorrectGlideModule(e3);
            return null;
        } catch (NoSuchMethodException e4) {
            throwIncorrectGlideModule(e4);
            return null;
        } catch (InvocationTargetException e5) {
            throwIncorrectGlideModule(e5);
            return null;
        }
    }

    private static void throwIncorrectGlideModule(Exception e) {
        throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e);
    }

    Glide(Context context, Engine engine2, MemoryCache memoryCache2, BitmapPool bitmapPool2, ArrayPool arrayPool2, RequestManagerRetriever requestManagerRetriever2, ConnectivityMonitorFactory connectivityMonitorFactory2, int logLevel, RequestOptions defaultRequestOptions, Map<Class<?>, TransitionOptions<?, ?>> defaultTransitionOptions, List<RequestListener<Object>> defaultRequestListeners, boolean isLoggingRequestOriginsEnabled) {
        Context context2 = context;
        MemoryCache memoryCache3 = memoryCache2;
        BitmapPool bitmapPool3 = bitmapPool2;
        ArrayPool arrayPool3 = arrayPool2;
        Class<byte[]> cls = byte[].class;
        this.engine = engine2;
        this.bitmapPool = bitmapPool3;
        this.arrayPool = arrayPool3;
        this.memoryCache = memoryCache3;
        this.requestManagerRetriever = requestManagerRetriever2;
        this.connectivityMonitorFactory = connectivityMonitorFactory2;
        DecodeFormat decodeFormat = (DecodeFormat) defaultRequestOptions.getOptions().get(Downsampler.DECODE_FORMAT);
        this.bitmapPreFiller = new BitmapPreFiller(memoryCache3, bitmapPool3, decodeFormat);
        Resources resources = context.getResources();
        Registry registry2 = new Registry();
        this.registry = registry2;
        registry2.register((ImageHeaderParser) new DefaultImageHeaderParser());
        if (Build.VERSION.SDK_INT >= 27) {
            registry2.register((ImageHeaderParser) new ExifInterfaceImageHeaderParser());
        }
        List<ImageHeaderParser> imageHeaderParsers = registry2.getImageHeaderParsers();
        Downsampler downsampler = new Downsampler(imageHeaderParsers, resources.getDisplayMetrics(), bitmapPool3, arrayPool3);
        ByteBufferGifDecoder byteBufferGifDecoder = new ByteBufferGifDecoder(context2, imageHeaderParsers, bitmapPool3, arrayPool3);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> parcelFileDescriptorVideoDecoder = VideoDecoder.parcel(bitmapPool2);
        DecodeFormat decodeFormat2 = decodeFormat;
        ByteBufferBitmapDecoder byteBufferBitmapDecoder = new ByteBufferBitmapDecoder(downsampler);
        StreamBitmapDecoder streamBitmapDecoder = new StreamBitmapDecoder(downsampler, arrayPool3);
        Downsampler downsampler2 = downsampler;
        ResourceDrawableDecoder resourceDrawableDecoder = new ResourceDrawableDecoder(context2);
        ResourceLoader.StreamFactory resourceLoaderStreamFactory = new ResourceLoader.StreamFactory(resources);
        ResourceLoader.UriFactory resourceLoaderUriFactory = new ResourceLoader.UriFactory(resources);
        ResourceLoader.FileDescriptorFactory resourceLoaderFileDescriptorFactory = new ResourceLoader.FileDescriptorFactory(resources);
        ResourceLoader.AssetFileDescriptorFactory resourceLoaderAssetFileDescriptorFactory = new ResourceLoader.AssetFileDescriptorFactory(resources);
        Class<byte[]> cls2 = cls;
        BitmapEncoder bitmapEncoder = new BitmapEncoder(arrayPool3);
        BitmapBytesTranscoder bitmapBytesTranscoder = new BitmapBytesTranscoder();
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder = new GifDrawableBytesTranscoder();
        Registry registry3 = registry2;
        ByteBufferBitmapDecoder byteBufferBitmapDecoder2 = byteBufferBitmapDecoder;
        ResourceLoader.FileDescriptorFactory resourceLoaderFileDescriptorFactory2 = resourceLoaderFileDescriptorFactory;
        ResourceLoader.UriFactory resourceLoaderUriFactory2 = resourceLoaderUriFactory;
        ResourceLoader.AssetFileDescriptorFactory resourceLoaderAssetFileDescriptorFactory2 = resourceLoaderAssetFileDescriptorFactory;
        Context context3 = context;
        ContentResolver contentResolver = context.getContentResolver();
        Class<byte[]> cls3 = cls2;
        ResourceDrawableDecoder resourceDrawableDecoder2 = resourceDrawableDecoder;
        BitmapBytesTranscoder bitmapBytesTranscoder2 = bitmapBytesTranscoder;
        GifDrawableBytesTranscoder gifDrawableBytesTranscoder2 = gifDrawableBytesTranscoder;
        registry2.append(ByteBuffer.class, new ByteBufferEncoder()).append(InputStream.class, new StreamEncoder(arrayPool3)).append(Registry.BUCKET_BITMAP, ByteBuffer.class, Bitmap.class, byteBufferBitmapDecoder).append(Registry.BUCKET_BITMAP, InputStream.class, Bitmap.class, streamBitmapDecoder).append(Registry.BUCKET_BITMAP, ParcelFileDescriptor.class, Bitmap.class, parcelFileDescriptorVideoDecoder).append(Registry.BUCKET_BITMAP, AssetFileDescriptor.class, Bitmap.class, VideoDecoder.asset(bitmapPool2)).append(Bitmap.class, Bitmap.class, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, Bitmap.class, Bitmap.class, new UnitBitmapDecoder()).append(Bitmap.class, bitmapEncoder).append(Registry.BUCKET_BITMAP_DRAWABLE, ByteBuffer.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, byteBufferBitmapDecoder)).append(Registry.BUCKET_BITMAP_DRAWABLE, InputStream.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, streamBitmapDecoder)).append(Registry.BUCKET_BITMAP_DRAWABLE, ParcelFileDescriptor.class, BitmapDrawable.class, new BitmapDrawableDecoder(resources, parcelFileDescriptorVideoDecoder)).append(BitmapDrawable.class, new BitmapDrawableEncoder(bitmapPool3, bitmapEncoder)).append(Registry.BUCKET_GIF, InputStream.class, GifDrawable.class, new StreamGifDecoder(imageHeaderParsers, byteBufferGifDecoder, arrayPool3)).append(Registry.BUCKET_GIF, ByteBuffer.class, GifDrawable.class, byteBufferGifDecoder).append(GifDrawable.class, new GifDrawableEncoder()).append(GifDecoder.class, GifDecoder.class, UnitModelLoader.Factory.getInstance()).append(Registry.BUCKET_BITMAP, GifDecoder.class, Bitmap.class, new GifFrameResourceDecoder(bitmapPool3)).append(Uri.class, Drawable.class, resourceDrawableDecoder).append(Uri.class, Bitmap.class, new ResourceBitmapDecoder(resourceDrawableDecoder, bitmapPool3)).register((DataRewinder.Factory<?>) new ByteBufferRewinder.Factory()).append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory()).append(File.class, InputStream.class, new FileLoader.StreamFactory()).append(File.class, File.class, new FileDecoder()).append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory()).append(File.class, File.class, UnitModelLoader.Factory.getInstance()).register((DataRewinder.Factory<?>) new InputStreamRewinder.Factory(arrayPool3)).append(Integer.TYPE, InputStream.class, resourceLoaderStreamFactory).append(Integer.TYPE, ParcelFileDescriptor.class, resourceLoaderFileDescriptorFactory2).append(Integer.class, InputStream.class, resourceLoaderStreamFactory).append(Integer.class, ParcelFileDescriptor.class, resourceLoaderFileDescriptorFactory2).append(Integer.class, Uri.class, resourceLoaderUriFactory2).append(Integer.TYPE, AssetFileDescriptor.class, resourceLoaderAssetFileDescriptorFactory2).append(Integer.class, AssetFileDescriptor.class, resourceLoaderAssetFileDescriptorFactory2).append(Integer.TYPE, Uri.class, resourceLoaderUriFactory2).append(String.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(Uri.class, InputStream.class, new DataUrlLoader.StreamFactory()).append(String.class, InputStream.class, new StringLoader.StreamFactory()).append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory()).append(String.class, AssetFileDescriptor.class, new StringLoader.AssetFileDescriptorFactory()).append(Uri.class, InputStream.class, new HttpUriLoader.Factory()).append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets())).append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets())).append(Uri.class, InputStream.class, new MediaStoreImageThumbLoader.Factory(context3)).append(Uri.class, InputStream.class, new MediaStoreVideoThumbLoader.Factory(context3)).append(Uri.class, InputStream.class, new UriLoader.StreamFactory(contentResolver)).append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(contentResolver)).append(Uri.class, AssetFileDescriptor.class, new UriLoader.AssetFileDescriptorFactory(contentResolver)).append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory()).append(URL.class, InputStream.class, new UrlLoader.StreamFactory()).append(Uri.class, File.class, new MediaStoreFileLoader.Factory(context3)).append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory()).append(cls3, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory()).append(cls3, InputStream.class, new ByteArrayLoader.StreamFactory()).append(Uri.class, Uri.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, UnitModelLoader.Factory.getInstance()).append(Drawable.class, Drawable.class, new UnitDrawableDecoder()).register(Bitmap.class, BitmapDrawable.class, new BitmapDrawableTranscoder(resources)).register(Bitmap.class, cls3, bitmapBytesTranscoder2).register(Drawable.class, cls3, new DrawableBytesTranscoder(bitmapPool3, bitmapBytesTranscoder2, gifDrawableBytesTranscoder2)).register(GifDrawable.class, cls3, gifDrawableBytesTranscoder2);
        ResourceDecoder<ParcelFileDescriptor, Bitmap> resourceDecoder = parcelFileDescriptorVideoDecoder;
        BitmapEncoder bitmapEncoder2 = bitmapEncoder;
        ByteBufferGifDecoder byteBufferGifDecoder2 = byteBufferGifDecoder;
        BitmapBytesTranscoder bitmapBytesTranscoder3 = bitmapBytesTranscoder2;
        Registry registry4 = registry3;
        List<ImageHeaderParser> list = imageHeaderParsers;
        Resources resources2 = resources;
        StreamBitmapDecoder streamBitmapDecoder2 = streamBitmapDecoder;
        ResourceLoader.StreamFactory streamFactory = resourceLoaderStreamFactory;
        ResourceLoader.UriFactory uriFactory = resourceLoaderUriFactory2;
        this.glideContext = new GlideContext(context, arrayPool2, registry4, new ImageViewTargetFactory(), defaultRequestOptions, defaultTransitionOptions, defaultRequestListeners, engine2, isLoggingRequestOriginsEnabled, logLevel);
    }

    public BitmapPool getBitmapPool() {
        return this.bitmapPool;
    }

    public ArrayPool getArrayPool() {
        return this.arrayPool;
    }

    public Context getContext() {
        return this.glideContext.getBaseContext();
    }

    /* access modifiers changed from: package-private */
    public ConnectivityMonitorFactory getConnectivityMonitorFactory() {
        return this.connectivityMonitorFactory;
    }

    /* access modifiers changed from: package-private */
    public GlideContext getGlideContext() {
        return this.glideContext;
    }

    public void preFillBitmapPool(PreFillType.Builder... bitmapAttributeBuilders) {
        this.bitmapPreFiller.preFill(bitmapAttributeBuilders);
    }

    public void clearMemory() {
        Util.assertMainThread();
        this.memoryCache.clearMemory();
        this.bitmapPool.clearMemory();
        this.arrayPool.clearMemory();
    }

    public void trimMemory(int level) {
        Util.assertMainThread();
        this.memoryCache.trimMemory(level);
        this.bitmapPool.trimMemory(level);
        this.arrayPool.trimMemory(level);
    }

    public void clearDiskCache() {
        Util.assertBackgroundThread();
        this.engine.clearDiskCache();
    }

    public RequestManagerRetriever getRequestManagerRetriever() {
        return this.requestManagerRetriever;
    }

    public MemoryCategory setMemoryCategory(MemoryCategory memoryCategory2) {
        Util.assertMainThread();
        this.memoryCache.setSizeMultiplier(memoryCategory2.getMultiplier());
        this.bitmapPool.setSizeMultiplier(memoryCategory2.getMultiplier());
        MemoryCategory oldCategory = this.memoryCategory;
        this.memoryCategory = memoryCategory2;
        return oldCategory;
    }

    private static RequestManagerRetriever getRetriever(Context context) {
        Preconditions.checkNotNull(context, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return get(context).getRequestManagerRetriever();
    }

    public static RequestManager with(Context context) {
        return getRetriever(context).get(context);
    }

    public static RequestManager with(Activity activity) {
        return getRetriever(activity).get(activity);
    }

    public static RequestManager with(FragmentActivity activity) {
        return getRetriever(activity).get(activity);
    }

    public static RequestManager with(Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    @Deprecated
    public static RequestManager with(android.app.Fragment fragment) {
        return getRetriever(fragment.getActivity()).get(fragment);
    }

    public static RequestManager with(View view) {
        return getRetriever(view.getContext()).get(view);
    }

    public Registry getRegistry() {
        return this.registry;
    }

    /* access modifiers changed from: package-private */
    public boolean removeFromManagers(Target<?> target) {
        synchronized (this.managers) {
            for (RequestManager requestManager : this.managers) {
                if (requestManager.untrack(target)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void registerRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (!this.managers.contains(requestManager)) {
                this.managers.add(requestManager);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterRequestManager(RequestManager requestManager) {
        synchronized (this.managers) {
            if (this.managers.contains(requestManager)) {
                this.managers.remove(requestManager);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public void onTrimMemory(int level) {
        trimMemory(level);
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
        clearMemory();
    }
}
