package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function1;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal1<T> {
    private Map<String, Function1<T, Void>> slots;

    public Signal1() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function1<T, Void> f) {
        slots.put(key, f);
    }

    public void emit(T t) {
        for (String key : slots.keySet())
            slots.get(key).function(t);
    }

    public void disconnect(String key) {
        slots.remove(key);
    }
}
