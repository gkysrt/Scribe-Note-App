package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by ALLDe on 14/01/2020.
 */

public class NoteFolder extends BaseNote {
    private String title;
    private ArrayList<BaseNote> children;
    private NoteFolder parent;

    public NoteFolder(String title, NoteFolder parent)
    {
        this.title = title;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChildren(ArrayList<BaseNote> children) {
        this.children = children;
    }

    public void setParent(NoteFolder parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
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
    public String filename() {
        return this.title;
    }

    public NoteFolder getParent() {
        return parent;
    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }
}
