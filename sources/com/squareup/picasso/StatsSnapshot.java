package com.squareup.picasso;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class StatsSnapshot {
    public final long averageDownloadSize;
    public final long averageOriginalBitmapSize;
    public final long averageTransformedBitmapSize;
    public final long cacheHits;
    public final long cacheMisses;
    public final int downloadCount;
    public final int maxSize;
    public final int originalBitmapCount;
    public final int size;
    public final long timeStamp;
    public final long totalDownloadSize;
    public final long totalOriginalBitmapSize;
    public final long totalTransformedBitmapSize;
    public final int transformedBitmapCount;

    public StatsSnapshot(int maxSize2, int size2, long cacheHits2, long cacheMisses2, long totalDownloadSize2, long totalOriginalBitmapSize2, long totalTransformedBitmapSize2, long averageDownloadSize2, long averageOriginalBitmapSize2, long averageTransformedBitmapSize2, int downloadCount2, int originalBitmapCount2, int transformedBitmapCount2, long timeStamp2) {
        this.maxSize = maxSize2;
        this.size = size2;
        this.cacheHits = cacheHits2;
        this.cacheMisses = cacheMisses2;
        this.totalDownloadSize = totalDownloadSize2;
        this.totalOriginalBitmapSize = totalOriginalBitmapSize2;
        this.totalTransformedBitmapSize = totalTransformedBitmapSize2;
        this.averageDownloadSize = averageDownloadSize2;
        this.averageOriginalBitmapSize = averageOriginalBitmapSize2;
        this.averageTransformedBitmapSize = averageTransformedBitmapSize2;
        this.downloadCount = downloadCount2;
        this.originalBitmapCount = originalBitmapCount2;
        this.transformedBitmapCount = transformedBitmapCount2;
        this.timeStamp = timeStamp2;
    }

    public void dump() {
        StringWriter logWriter = new StringWriter();
        dump(new PrintWriter(logWriter));
        Log.i("Picasso", logWriter.toString());
    }

    public void dump(PrintWriter writer) {
        writer.println("===============BEGIN PICASSO STATS ===============");
        writer.println("Memory Cache Stats");
        writer.print("  Max Cache Size: ");
        writer.println(this.maxSize);
        writer.print("  Cache Size: ");
        writer.println(this.size);
        writer.print("  Cache % Full: ");
        writer.println((int) Math.ceil((double) ((((float) this.size) / ((float) this.maxSize)) * 100.0f)));
        writer.print("  Cache Hits: ");
        writer.println(this.cacheHits);
        writer.print("  Cache Misses: ");
        writer.println(this.cacheMisses);
        writer.println("Network Stats");
        writer.print("  Download Count: ");
        writer.println(this.downloadCount);
        writer.print("  Total Download Size: ");
        writer.println(this.totalDownloadSize);
        writer.print("  Average Download Size: ");
        writer.println(this.averageDownloadSize);
        writer.println("Bitmap Stats");
        writer.print("  Total Bitmaps Decoded: ");
        writer.println(this.originalBitmapCount);
        writer.print("  Total Bitmap Size: ");
        writer.println(this.totalOriginalBitmapSize);
        writer.print("  Total Transformed Bitmaps: ");
        writer.println(this.transformedBitmapCount);
        writer.print("  Total Transformed Bitmap Size: ");
        writer.println(this.totalTransformedBitmapSize);
        writer.print("  Average Bitmap Size: ");
        writer.println(this.averageOriginalBitmapSize);
        writer.print("  Average Transformed Bitmap Size: ");
        writer.println(this.averageTransformedBitmapSize);
        writer.println("===============END PICASSO STATS ===============");
        writer.flush();
    }

    public String toString() {
        return "StatsSnapshot{maxSize=" + this.maxSize + ", size=" + this.size + ", cacheHits=" + this.cacheHits + ", cacheMisses=" + this.cacheMisses + ", downloadCount=" + this.downloadCount + ", totalDownloadSize=" + this.totalDownloadSize + ", averageDownloadSize=" + this.averageDownloadSize + ", totalOriginalBitmapSize=" + this.totalOriginalBitmapSize + ", totalTransformedBitmapSize=" + this.totalTransformedBitmapSize + ", averageOriginalBitmapSize=" + this.averageOriginalBitmapSize + ", averageTransformedBitmapSize=" + this.averageTransformedBitmapSize + ", originalBitmapCount=" + this.originalBitmapCount + ", transformedBitmapCount=" + this.transformedBitmapCount + ", timeStamp=" + this.timeStamp + '}';
    }
}
