package com.scribenoteapp.scribe.model.note;

import java.util.ArrayList;

/**
 * Created by ALLDe on 14/01/2020.
 */

public abstract class BaseNote implements Comparable<BaseNote>{
    private NoteFolder parent;
    private String title;
    private String creationDate;
    private String updateDate;

    public BaseNote(String title, String creationDate, NoteFolder parent)
    {
        this.creationDate = creationDate;
        this.updateDate = creationDate;
        this.title = title;
        this.parent = parent;
    }

    public abstract ArrayList<BaseNote> getChildren();
    public abstract ArrayList<String> getTags();
    public abstract void addTag(String tag);
    public abstract void removeTag(String tag);

    public String filename()
    {
        return this.title;
    }

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

    public int getPosition()
    {
        return (this.getParent() != null ) ? this.getParent().indexOf(this) : -1;
    }

    public void setParent(NoteFolder parent) {
        this.parent = parent;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public NoteFolder getParent() {
        return this.parent;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

}
