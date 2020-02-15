package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal2<T, T1> {
    private Map<String, SignalItem> slots;

    public Signal2() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function2<T, T1, Void> f) {
        SignalItem signalItem = new SignalItem(f);
        slots.put(key, signalItem);
    }

    public void emit(T t, T1 t1) {
        for (String key : slots.keySet()) {
            SignalItem item = slots.get(key);
            if (item.isActive()) {
                item.run(t, t1);
            }
        }
    }

    public void reconnect(String key) {
        if (this.slots.containsKey(key)) {
            slots.get(key).setActive(true);
        }
    }

    public void disconnect(String key) {
        if (this.slots.containsKey(key)) {
            slots.get(key).setActive(false);
        }

    }

    public void remove(String key) {
        slots.remove(key);
    }

    class SignalItem {
        private boolean isActive;
        private Function2<T, T1, Void> function;

        SignalItem(Function2<T, T1, Void> function) {
            this.isActive = true;
            this.function = function;
        }

        boolean isActive() {
            return this.isActive;
        }

        void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        void run(T t, T1 t1) {
            this.function.function(t, t1);
        }
    }
}
