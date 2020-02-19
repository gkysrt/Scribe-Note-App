package com.scribenoteapp.scribe;

import com.scribenoteapp.scribe.framework.slots.Function1;

public class Static {
    /**
     *
     * @param list
     * @param function
     * @param <T>
     * @return
     */
    public static <T> boolean all(T[] list, Function1<T, Boolean> function) {
        for (T item : list) {
            if (!function.function(item)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean exists(T[] list, Function1<T, Boolean> function) {
        for (T item : list) {
            if (function.function(item)) {
                return true;
            }
        }
        return false;
    }
}
