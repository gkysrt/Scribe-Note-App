package com.scribenoteapp.scribe.framework.signals;

import com.scribenoteapp.scribe.framework.slots.Function1;
import java.util.ArrayList;

/**
 * Created by Gokay on 23/01/2020.
 */

public class Signal1<T>
{
    private ArrayList<Function1> slots;
    public Signal1()
    {
        slots = new ArrayList<>();
    }
    public void connect(Function1 f)
    {
        slots.add(f);
    }

    public void emit(T t)
    {
        for (Function1 f : slots)
            f.function(t);
    }

    public void disconnect(Function1 f)
    {
        slots.remove(f);
    }
}
