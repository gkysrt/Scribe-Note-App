package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function2;

import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal2 <T, T1>{
    private ArrayList<Function2> slots;
    public Signal2()
    {
        slots = new ArrayList<>();
    }
    public void connect(Function2 f)
    {
        slots.add(f);
    }

    public void emit(T t, T1 t1)
    {
        for (Function2 f : slots)
            f.function(t, t1);
    }

    public void disconnect(Function2 f)
    {
        slots.remove(f);
    }

}
