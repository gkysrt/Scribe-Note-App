package com.scribenoteapp.scribe.model.note;

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
        // todo: path eklerken append / yerine python da os.path.join gibi bir fonksyion var mı diye
        // todo: bakmak lazım
        // todo: pin var mı yok mu onu da eklemek gerekli
        // todo color eklenebilir.

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
