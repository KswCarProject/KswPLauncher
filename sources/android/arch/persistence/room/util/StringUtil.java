package android.arch.persistence.room.util;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static void appendPlaceholders(StringBuilder builder, int count) {
        for (int i = 0; i < count; i++) {
            builder.append("?");
            if (i < count - 1) {
                builder.append(",");
            }
        }
    }

    public static List<Integer> splitToIntList(String input) {
        if (input == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        while (tokenizer.hasMoreElements()) {
            try {
                result.add(Integer.valueOf(Integer.parseInt(tokenizer.nextToken())));
            } catch (NumberFormatException ex) {
                Log.e("ROOM", "Malformed integer list", ex);
            }
        }
        return result;
    }

    public static String joinIntoString(List<Integer> input) {
        if (input == null) {
            return null;
        }
        int size = input.size();
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(Integer.toString(input.get(i).intValue()));
            if (i < size - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
