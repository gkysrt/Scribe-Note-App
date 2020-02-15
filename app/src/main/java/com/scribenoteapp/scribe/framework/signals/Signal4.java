package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function4;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal4<T, T1, T2, T3> {
    private Map<String, Function4<T, T1, T2, T3, Void>> slots;

    public Signal4() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function4<T, T1, T2, T3, Void> f) {
        slots.put(key, f);
    }

    public void emit(T t, T1 t1, T2 t2, T3 t3) {
        for (String key : slots.keySet())
            slots.get(key).function(t, t1, t2, t3);
    }

    public void disconnect(String key) {
        slots.remove(key);
    }
}
