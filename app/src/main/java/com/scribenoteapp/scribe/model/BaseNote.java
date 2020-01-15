package com.scribenoteapp.scribe.model;

import java.util.ArrayList;

/**
 * Created by ALLDe on 14/01/2020.
 */

public abstract class BaseNote implements Comparable<BaseNote>{
    public abstract String filename();
    public abstract BaseNote getParent();
    public abstract ArrayList<BaseNote> getChildren();
    public abstract ArrayList<String> getTags();
    public abstract void addTag(String tag);
    public abstract void removeTag(String tag);

    public String path()
    {
        StringBuilder path = new StringBuilder();
        BaseNote parent = this.getParent();

        while (parent != null)
        {
            path.append("/").append(parent.filename());
            parent = parent.getParent();
        }
        path.append("/").append(this.filename());

        return path.toString();
    }
}
