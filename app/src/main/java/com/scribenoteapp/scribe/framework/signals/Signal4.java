package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function4;

import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal4 <T, T1, T2, T3>{
    private ArrayList<Function4> slots;

    public Signal4()
    {
        slots = new ArrayList<>();
    }

    public void connect(Function4 f)
    {
        slots.add(f);
    }

    public void disconnect(Function4 f)
    {
        slots.remove(f);
    }

    public void emit(T t, T1 t1, T2 t2, T3 t3)
    {
        for (Function4 f : slots)
        {
            f.function(t, t1, t2 , t3);
        }
    }
}
