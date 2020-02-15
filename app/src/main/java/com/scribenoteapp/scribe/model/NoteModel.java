package com.scribenoteapp.scribe.model;


import com.scribenoteapp.scribe.R;

import com.scribenoteapp.scribe.framework.AbstractModel;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.namespace.ModelRole;
import com.scribenoteapp.scribe.framework.signals.Signal;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;
import com.scribenoteapp.scribe.model.note.NoteFolder;

import java.util.ArrayList;

/**
 * Created by ALLDe on 15/01/2020.
 */
public class NoteModel extends AbstractModel {
    private NoteFolder rootFolder;
    private NoteFolder currentFolder;
    private ArrayList<String> tags;
    private String[] headerData;

    public NoteModel() {
        super();
        this.rootFolder = new NoteFolder("/", null);
        this.currentFolder = this.rootFolder;
        this.tags = new ArrayList<>();
        this.headerData = new String[]{"Title", "Body", "Logo", "Pinned"};
        // TODO: SİLİNCEK
        this.init();
    }


    public void init() {
        rootFolder.addChild(new Note("kill meeeeeee", "PLEASE!!!!"));
        NoteFolder folder = new NoteFolder("asd", rootFolder);
        new Note("bu bir iç note", "İç Note", folder);
    }

    public void setCurrentFolder(ModelIndex index) {
        if (index.isValid()) {
            Object baseNote = index.data(ModelRole.USER_ROLE);
            if (baseNote instanceof NoteFolder) {
                this.currentFolder = (NoteFolder) baseNote;
                this.getModelResetSignal().emit();
            }
        }
    }

    @Override
    public ModelIndex index(int row, int column, ModelIndex parent) {
        if ((parent.isValid() && parent.column() != 0) || row >= rowCount())
            return new ModelIndex();

        NoteFolder parentItem = (NoteFolder) this.getItem(parent);
        BaseNote childItem = parentItem.child(row);

        if (childItem != null)
            return this.createIndex(row, column, childItem);

        return new ModelIndex();
    }

    @Override
    public ModelIndex index(int row, int column) {
        BaseNote childItem = this.currentFolder.child(row);

        if (childItem != null)
            return this.createIndex(row, column, childItem);

        return new ModelIndex();
    }

    @Override
    public Object data(ModelIndex index, int role) {

        BaseNote item = (BaseNote) index.internalPointer();

        if (role == ModelRole.DISPLAY_ROLE)
            return this.data(index);

        else if (role == ModelRole.USER_ROLE) {
            return item;
        }

        return null;
    }

    @Override
    public Object data(ModelIndex index) {
        BaseNote baseNote = (BaseNote) index.internalPointer();
        String body = baseNote instanceof Note ? ((Note) baseNote).getBody() : null;
        Integer logo = baseNote instanceof Note ? R.drawable.ic_note_icon : R.drawable.ic_folder_icon;
        boolean isPinned = baseNote.getIsPinned();

        Object[] data = {baseNote.filename(), body, baseNote.getUpdateDate(), logo, isPinned};

        return data[index.column()];
    }

    @Override
    public int rowCount(ModelIndex parent) {
        BaseNote baseNote = this.getItem(parent);
        if (baseNote instanceof NoteFolder) {
            NoteFolder folder = (NoteFolder) baseNote;
            return folder.childCount();
        } else {
            return 0;
        }

    }

    @Override
    public int rowCount() {
        return this.currentFolder.childCount();
    }

    @Override
    public int columnCount(ModelIndex parent) {
        return this.headerData.length;
    }

    @Override
    public int columnCount() {
        return this.headerData.length;
    }

    @Override
    public ModelIndex parent(ModelIndex index) {
//        if (!index.isValid())
//            return new ModelIndex();
//
//        BaseNote note = (BaseNote) index.data(ModelRole.USER_ROLE);
//        return createIndex(note.getParent().getPosition(), 0, note.getParent());

        return new ModelIndex();
    }

    public BaseNote getItem(ModelIndex index) {
        if (index == null)
            return null;

        if (index.isValid())
            return (BaseNote) index.data(ModelRole.USER_ROLE);

        return this.currentFolder;
    }

    public void addTagToItem(BaseNote item, String tag) {
        item.addTag(tag);
    }

    public void removeTagFromItem(BaseNote item, String tag) {
        item.removeTag(tag);
    }

    public NoteFolder currentFolder() {
        return this.currentFolder;
    }

    public NoteFolder rootFolder() {
        return this.rootFolder;
    }


}
