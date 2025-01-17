package com.scribenoteapp.scribe.model.note;

import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ALLDe on 14/01/2020.
 */

public abstract class BaseNote implements Comparable<BaseNote>, Parcelable {
    private NoteFolder parent;
    private String title;
    private String creationDate;
    private String updateDate;
    private boolean isPinned;
    private List<String> tags;

    BaseNote(String title, NoteFolder parent) {
        this.creationDate = SimpleDateFormat.getDateTimeInstance().format(new Date());
        this.updateDate = creationDate;
        this.title = title;
        this.isPinned = false;
        this.tags = new ArrayList<>();
        this.setParent(parent);
    }

    public String logicalPath() {
        if (this.getParent() != null) {
            return this.getParent().logicalPath() + '/' + this.getTitle();
        } else {
            return this.getTitle();
        }

    }

    // TODO: need to implement a factory class
    public BaseNote(String title, NoteFolder parent, String creationDate, String updateDate, List<String> tags) {

    }

    public List<String> getTags() {
        this.updateDate();
        return this.tags;

    }

    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public boolean getIsPinned() {
        return this.isPinned;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
        this.updateDate();
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
        this.updateDate();
    }

    public String filename() {
        return this.title;
    }

    public String path() {
        StringBuilder path = new StringBuilder();
        BaseNote parent = this.getParent();

        while (parent != null) {
            path.append("/").append(parent.filename());
            parent = parent.getParent();
        }
        path.append("/").append(this.filename());

        return path.toString();
    }

    public int getPosition() {
        // If item is not found inside the parent or does not have a parent, function returns -1
        return (this.getParent() != null) ? this.getParent().indexOf(this) : -1;
    }

    public void setParent(NoteFolder parent) {
        if (this.parent != null)
            this.parent.removeChild(this);

        this.parent = parent;
        if (this.parent != null)
            this.parent.addChild(this);
        this.updateDate();
    }

    public void setTitle(String title) {
        this.title = title;
        this.updateDate();
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public NoteFolder getParent() {
        return this.parent;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    void updateDate() {
        SimpleDateFormat.getDateTimeInstance().format(new Date());
    }

}
