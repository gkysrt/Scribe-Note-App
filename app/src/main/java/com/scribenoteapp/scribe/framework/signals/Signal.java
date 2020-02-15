package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal {
    private Map<String, SignalItem> slots;

    public Signal() {
        slots = new HashMap<>();
    }

    public void connect(String key, Function<Void> f) {
        SignalItem signalItem = new SignalItem(f);
        slots.put(key, signalItem);
    }

    public void emit() {
        for (String key : slots.keySet()) {
            SignalItem item = slots.get(key);
            if (item.isActive()) {
                item.run();
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
        private Function<Void> function;

        SignalItem(Function<Void> function) {
            this.isActive = true;
            this.function = function;
        }

        boolean isActive() {
            return this.isActive;
        }

        void setActive(boolean isActive) {
            this.isActive = isActive;
        }

        void run() {
            this.function.function();
        }
    }
}
