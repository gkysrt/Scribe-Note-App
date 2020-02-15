package com.scribenoteapp.scribe.framework.signals;
import com.scribenoteapp.scribe.framework.slots.Function2;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal2<T, T1> {
    private Map<String, Function2<T, T1, Void>> slots;

    public Signal2() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function2<T, T1, Void> f) {
        slots.put(key, f);
    }

    public void emit(T t, T1 t1) {
        for (String key : slots.keySet())
            slots.get(key).function(t, t1);
    }

    public void disconnect(String key) {
        slots.remove(key);
    }

}
