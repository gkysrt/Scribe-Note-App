package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function;
import com.scribenoteapp.scribe.framework.slots.Function1;

import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal {
    private ArrayList<Function> slots;

    public Signal()
    {
        slots = new ArrayList<>();
    }

    public void connect(Function f)
    {
        slots.add(f);
    }

    public void emit()
    {
        for (Function f : slots)
            f.function();
    }

    public void disconnect(Function f)
    {
        slots.remove(f);
    }

}
