package android.arch.persistence.room;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public class Room {
    private static final String CURSOR_CONV_SUFFIX = "_CursorConverter";
    static final String LOG_TAG = "ROOM";
    public static final String MASTER_TABLE_NAME = "room_master_table";

    public static <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(Context context, Class<T> klass, String name) {
        if (name != null && name.trim().length() != 0) {
            return new RoomDatabase.Builder<>(context, klass, name);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    public static <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(Context context, Class<T> klass) {
        return new RoomDatabase.Builder<>(context, klass, (String) null);
    }

    static <T, C> T getGeneratedImplementation(Class<C> klass, String suffix) {
        String postPackageName;
        String fullPackage = klass.getPackage().getName();
        String name = klass.getCanonicalName();
        if (fullPackage.isEmpty()) {
            postPackageName = name;
        } else {
            postPackageName = name.substring(fullPackage.length() + 1);
        }
        String implName = postPackageName.replace('.', '_') + suffix;
        try {
            return Class.forName(fullPackage.isEmpty() ? implName : fullPackage + "." + implName).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("cannot find implementation for " + klass.getCanonicalName() + ". " + implName + " does not exist");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot access the constructor" + klass.getCanonicalName());
        } catch (InstantiationException e3) {
            throw new RuntimeException("Failed to create an instance of " + klass.getCanonicalName());
        }
    }
}
