package com.wits.ksw.launcher.base;

import android.databinding.BindingAdapter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.DragGridView;

public class BaseBindingModel {
    @BindingAdapter({"srcImage"})
    public static void srcImage(ImageView imageView, Drawable resid) {
        imageView.setImageDrawable(resid);
    }

    @BindingAdapter({"srcImage"})
    public static void srcImage(ImageView imageView, int resid) {
        imageView.setImageResource(resid);
    }

    @BindingAdapter({"setAdpater"})
    public static void setAdpater(GridView gridView, BaseListAdpater adapter) {
        gridView.setAdapter(adapter);
    }

    @BindingAdapter({"setID6MusicAlbumIcon"})
    public static void setID6MusicAlbumIcon(ImageView imageView, BitmapDrawable data) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load((Drawable) data).placeholder((int) R.drawable.id6_music_album_default)).error((int) R.drawable.id6_music_album_default)).into(imageView);
    }

    @BindingAdapter({"setMusicAlbumIcon"})
    public static void setMusicAlbumIcon(ImageView imageView, BitmapDrawable data) {
        imageView.setAlpha(data != null ? 0.2f : 1.0f);
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load((Drawable) data).placeholder((int) R.drawable.id7_album_bk_big)).error((int) R.drawable.id7_album_bk_big)).into(imageView);
    }

    @BindingAdapter({"setMusicAlbum"})
    public static void setAlbumIcon(ImageView imageView, BitmapDrawable data) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load((Drawable) data).placeholder((int) R.drawable.id7_album_default)).error((int) R.drawable.id7_album_default)).into(imageView);
    }

    @BindingAdapter({"setAlsId7MusicAlbum"})
    public static void setAlsID7AlbumIcon(ImageView imageView, BitmapDrawable data) {
        ((RequestBuilder) ((RequestBuilder) Glide.with(imageView.getContext()).load((Drawable) data).placeholder((int) R.color.transparent)).error((int) R.color.transparent)).into(imageView);
    }

    @BindingAdapter({"setOnItemChangerListener"})
    public static void setOnItemChangerListener(DragGridView gridView, DragGridView.onItemChangerListener onItemChangerListener) {
        gridView.setOnItemChangeListener(onItemChangerListener);
    }
}
