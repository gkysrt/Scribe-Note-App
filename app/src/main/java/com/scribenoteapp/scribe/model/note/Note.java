package com.scribenoteapp.scribe.model.note;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.scribenoteapp.scribe.model.attachment.Attachment;
import com.scribenoteapp.scribe.model.attachment.AttachmentTypes;

import java.io.Serializable;
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

    private Note(Parcel in)
    {
        super(null,  null);
        boolean[] booleans = new boolean[1];
        String[] strings = new String[4];
        List<String> tags = new ArrayList<>();

        in.readStringArray(strings);
        in.readBooleanArray(booleans);
        in.readList(tags, String.class.getClassLoader());

        this.setTitle(strings[0]);
        this.setBody(strings[1]);
        this.setCreationDate(strings[2]);
        this.setUpdateDate(strings[3]);
        this.setTags(tags);

        this.setIsPinned(booleans[0]);

        this.attachments = (Map<AttachmentTypes, ArrayList<Attachment>>) in.readSerializable();
//        this.setParent((NoteFolder) in.readParcelable(NoteFolder.class.getClassLoader()));
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String[] strings = {this.getTitle(), this.getBody(), this.getCreationDate(), this.getUpdateDate()};
        boolean[] booleans = {this.getIsPinned()};

        parcel.writeStringArray(strings);
        parcel.writeBooleanArray(booleans);
        parcel.writeList(getTags());
        parcel.writeSerializable((Serializable) this.attachments);
//        parcel.writeParcelable(getParent(), i);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

}

