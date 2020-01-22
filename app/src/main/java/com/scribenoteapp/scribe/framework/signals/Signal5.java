package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function5;

import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal5 <T, T1, T2, T3, T4>{
    private ArrayList<Function5> slots;

    public Signal5()
    {
        slots = new ArrayList<>();
    }

    public void connect(Function5 f)
    {
        slots.add(f);
    }

    public void disconnect(Function5 f)
    {
        slots.remove(f);
    }

    public void emit(T t, T1 t1, T2 t2, T3 t3, T4 t4)
    {
        for (Function5 f : slots)
            f.function(t, t1, t2, t3, t4);
    }
}
