package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import com.scribenoteapp.scribe.model.attachment.Attachment;

import java.util.ArrayList;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class Note extends BaseNote {

    private String body;
    private String title;
    private String creationDate;
    private String updateDate;
    private NoteFolder parent;
    private ArrayList<String> tags;
    private ArrayList<Attachment> attachments;

    public Note(String body, String title, String date, NoteFolder parent) {
        this.body = body;
        this.title = title;
        this.creationDate = date;
        this.updateDate = date;
        this.parent = parent;
        this.attachments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    // TODO: This is to be deleted
    public Note(String body, String title, String date) {
        this.body = body;
        this.title = title;
        this.creationDate = date;
        this.updateDate = date;
        this.parent = null;
        this.attachments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParent(NoteFolder parent) {
        this.parent = parent;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public BaseNote getParent() {
        return this.parent;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void addTag(String tag)
    {
        this.tags.add(tag);
    }

    public void removeTag(String tag)
    {
        this.tags.remove(tag);
    }

    @Override
    public ArrayList<BaseNote> getChildren() {
        return null;
    }

    @Override
    public String filename() {
        return this.title;
    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }
}

