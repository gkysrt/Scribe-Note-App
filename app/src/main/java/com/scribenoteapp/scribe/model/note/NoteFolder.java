package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by ALLDe on 14/01/2020.
 */

public class NoteFolder extends BaseNote {
    private ArrayList<BaseNote> children;

    public NoteFolder(String title, String creationDate, NoteFolder parent)
    {
        super(title, creationDate, parent);
        this.children = new ArrayList<>();
    }

    public void setChildren(ArrayList<BaseNote> children) {
        this.children = children;
    }

    public ArrayList<BaseNote> getChildren() {
        return this.children;
    }

    @Override
    public ArrayList<String> getTags() {
        return null;
    }

    @Override
    public void addTag(String tag) {
    }

    @Override
    public void removeTag(String tag) {

    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }

    public int indexOf(BaseNote child)
    {
        return this.children.indexOf(child);
    }
}
