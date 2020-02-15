package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function1;
import com.scribenoteapp.scribe.framework.slots.Function3;
import com.scribenoteapp.scribe.framework.slots.Function5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal5<T, T1, T2, T3, T4> {
    private Map<String, Function5<T, T1, T2, T3, T4, Void>> slots;

    public Signal5() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function5<T, T1, T2, T3, T4, Void> f) {
        slots.put(key, f);
    }

    public void emit(T t, T1 t1, T2 t2, T3 t3, T4 t4) {
        for (String key : slots.keySet())
            slots.get(key).function(t, t1, t2, t3, t4);
    }

    public void disconnect(String key) {
        slots.remove(key);
    }
}
