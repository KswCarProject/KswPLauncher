package android.arch.persistence.room;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

public class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(@NonNull Context context, @NonNull Class<T> klass, @NonNull String name) {
        if (name != null && name.trim().length() != 0) {
            return new RoomDatabase.Builder<>(context, klass, name);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    @NonNull
    public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(@NonNull Context context, @NonNull Class<T> klass) {
        return new RoomDatabase.Builder<>(context, klass, (String) null);
    }

    @NonNull
    static <T, C> T getGeneratedImplementation(Class<C> klass, String suffix) {
        String str;
        String fullPackage = klass.getPackage().getName();
        String name = klass.getCanonicalName();
        String postPackageName = fullPackage.isEmpty() ? name : name.substring(fullPackage.length() + 1);
        String implName = postPackageName.replace('.', '_') + suffix;
        try {
            if (fullPackage.isEmpty()) {
                str = implName;
            } else {
                str = fullPackage + "." + implName;
            }
            return Class.forName(str).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find implementation for " + klass.getCanonicalName() + ". " + implName + " does not exist");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot access the constructor" + klass.getCanonicalName());
        } catch (InstantiationException e3) {
            throw new RuntimeException("Failed to create an instance of " + klass.getCanonicalName());
        }
    }
}
