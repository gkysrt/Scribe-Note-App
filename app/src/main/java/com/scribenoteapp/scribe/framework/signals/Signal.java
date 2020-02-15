package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal {
    private Map<String, Function<Void>> slots;

    public Signal() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function<Void> f) {
        slots.put(key, f);
    }

    public void emit() {
        for (String key : slots.keySet())
            slots.get(key).function();
    }

    public void disconnect(String key) {
        slots.remove(key);
    }

}
