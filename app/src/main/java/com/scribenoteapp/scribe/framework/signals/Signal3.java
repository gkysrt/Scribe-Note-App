package com.scribenoteapp.scribe.framework.signals;
import com.scribenoteapp.scribe.framework.slots.Function3;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal3<T, T1, T2> {
    private Map<String, Function3<T, T1, T2, Void>> slots;

    public Signal3() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function3<T, T1, T2, Void> f) {
        slots.put(key, f);
    }

    public void emit(T t, T1 t1, T2 t2) {
        for (String key : slots.keySet())
            slots.get(key).function(t, t1, t2);
    }

    public void disconnect(String key) {
        slots.remove(key);
    }
}
