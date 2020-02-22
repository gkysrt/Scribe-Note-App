package com.scribenoteapp.scribe.model.note;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALLDe on 14/01/2020.
 */

public class NoteFolder extends BaseNote {
    private ArrayList<BaseNote> children;

    public NoteFolder(String title, NoteFolder parent)
    {
        super(title, parent);
        this.children = new ArrayList<>();
    }

    private NoteFolder(Parcel in)
    {
        super(null, null);

        boolean[] booleans = new boolean[1];
        String[] strings = new String[4];
        List<String> tags = new ArrayList<>();

        in.readStringArray(strings);
        in.readBooleanArray(booleans);
        in.readList(tags, String.class.getClassLoader());

        this.setTitle(strings[0]);
        this.setCreationDate(strings[1]);
        this.setUpdateDate(strings[2]);
        this.setTags(tags);

        this.setIsPinned(booleans[0]);
    }

    @Override
    public int compareTo(@NonNull BaseNote o) {
        return o.path().compareTo(this.path());
    }

    public int indexOf(BaseNote child)
    {
        return this.children.indexOf(child);
    }

    public int childCount()
    {
        return this.children.size();
    }

    public ArrayList<BaseNote> children() {
        return this.children;
    }

    public BaseNote child(int index)
    {
        return this.children.get(index);
    }

    public void addChild(BaseNote note)
    {
        this.children.add(note);
        this.updateDate();
    }

    public void removeChild(BaseNote note)
    {
        this.children.remove(note);
        this.updateDate();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String[] strings = {this.getTitle(), this.getCreationDate(), this.getUpdateDate()};
        boolean[] booleans = {this.getIsPinned()};

        parcel.writeStringArray(strings);
        parcel.writeBooleanArray(booleans);
        parcel.writeList(getTags());
//        parcel.writeParcelable(getParent(), i);

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NoteFolder createFromParcel(Parcel in) {
            return new NoteFolder(in);
        }

        public NoteFolder[] newArray(int size) {
            return new NoteFolder[size];
        }
    };

}
