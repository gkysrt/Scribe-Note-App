package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import com.scribenoteapp.scribe.model.attachment.Attachment;

import java.util.ArrayList;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class Note extends BaseNote {

    private String body;
    private ArrayList<String> tags;
    private ArrayList<Attachment> attachments;

    public Note(String body, String title, String creationDate, NoteFolder parent) {
        super(title, creationDate, parent);
        this.body = body;
        this.attachments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    // TODO: This is to be deleted
    public Note(String body, String title, String date) {
        super("", date, null);
        this.body = body;
        this.attachments = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getBody() {
        return body;
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
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }
}

