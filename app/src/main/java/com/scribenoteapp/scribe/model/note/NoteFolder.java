package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by ALLDe on 14/01/2020.
 */

public class NoteFolder extends BaseNote {
    private ArrayList<BaseNote> children;

    public NoteFolder(String title, NoteFolder parent)
    {
        super(title, parent);
        this.children = new ArrayList<>();
    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }

    public int indexOf(BaseNote child)
    {
        return this.children.indexOf(child);
    }

    public int childCount()
    {
        return this.children.size();
    }

    public ArrayList<BaseNote> children() {
        return this.children;
    }

    public BaseNote child(int index)
    {
        return this.children.get(index);
    }

    public void addChild(BaseNote note)
    {
        this.children.add(note);
        this.updateDate();
    }

    public void removeChild(BaseNote note)
    {
        this.children.remove(note);
        this.updateDate();
    }

}
