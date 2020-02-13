package com.scribenoteapp.scribe.model.note;

import android.support.annotation.NonNull;

import com.scribenoteapp.scribe.model.attachment.Attachment;
import com.scribenoteapp.scribe.model.attachment.AttachmentTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class Note extends BaseNote {

    private String body;
    private Map<AttachmentTypes, ArrayList<Attachment>> attachments;

    public Note(String body, String title, NoteFolder parent) {
        super(title, parent);
        this.body = body;
        this.attachments = new HashMap<>();
    }

    // TODO: This is to be deleted
    public Note(String body, String title) {
        super(title, null);
        this.body = body;
        this.attachments = new HashMap<>();
    }

    public boolean hasAttachment(AttachmentTypes type) {
        return this.attachments.containsKey(type);
    }

    public void setBody(String body) {
        this.body = body;
        this.updateDate();
    }

    public void addAttachment(AttachmentTypes type, Attachment a) {
        if (attachments.get(type) == null)
            attachments.put(type, new ArrayList<Attachment>());

        attachments.get(type).add(a);
        this.updateDate();
    }

    public void removeAttachment(AttachmentTypes type, Attachment a) {
        attachments.get(type).remove(a);
    }

    public String getBody() {
        return body;
    }

    public List getAttachments(AttachmentTypes type) {
        return this.attachments.get(type);
    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }
}

