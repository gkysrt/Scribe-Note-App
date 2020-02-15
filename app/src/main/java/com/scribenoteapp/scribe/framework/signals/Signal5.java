package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function5;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal5<T, T1, T2, T3, T4> {
    private Map<String, SignalItem> slots;

    public Signal5() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function5<T, T1, T2, T3, T4, Void> f) {
        SignalItem signalItem = new SignalItem(f);
        slots.put(key, signalItem);
    }

    public void emit(T t, T1 t1, T2 t2, T3 t3, T4 t4) {
        for (String key : slots.keySet()) {
            SignalItem item = slots.get(key);
            if (item.isActive()) {
                item.run(t, t1, t2, t3, t4);
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
        private Function5<T, T1, T2, T3, T4, Void> function;

        SignalItem(Function5<T, T1, T2, T3, T4, Void> function) {
            this.isActive = true;
            this.function = function;
        }

        boolean isActive() {
            return this.isActive;
        }

        void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        void run(T t, T1 t1, T2 t2, T3 t3, T4 t4) {
            this.function.function(t, t1, t2, t3, t4);
        }
    }
}
