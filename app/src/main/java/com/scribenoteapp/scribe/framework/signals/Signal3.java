package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function3;

import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal3 <T, T1, T2> {
    private ArrayList<Function3> slots;

    public Signal3()
    {
        slots = new ArrayList<>();
    }

    public void connect(Function3 f)
    {
        slots.add(f);
    }

    public void disconnect(Function3 f)
    {
        slots.remove(f);
    }

    public void emit(T t, T1 t1, T2 t2)
    {
        for (Function3 f : slots)
            f.function(t, t1, t2);

    }
}
