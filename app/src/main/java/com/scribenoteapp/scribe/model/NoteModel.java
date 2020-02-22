package com.scribenoteapp.scribe.model;


import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.framework.AbstractModel;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.namespace.ModelRole;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;
import com.scribenoteapp.scribe.model.note.NoteFolder;

import java.util.ArrayList;

/**
 * Created by ALLDe on 15/01/2020.
 */
public class NoteModel extends AbstractModel {
    private NoteFolder currentFolder;
    private ArrayList<String> tags;
    private String[] headerData;

    public NoteModel() {
        super();
        this.currentFolder = new NoteFolder("/", null);
        this.tags = new ArrayList<>();
        this.headerData = new String[]{"Title", "Body", "Logo", "Pinned"};
        // TODO: SİLİNCEK
        this.init();
    }

    public void init() {
        this.rootFolder().addChild(new Note("kill meeeeeee", "PLEASE!!!!"));
        NoteFolder folder = new NoteFolder("asd", this.rootFolder());
        new Note("bu bir iç note", "İç Note", folder);
    }

    public void setCurrentFolder(NoteFolder folder) {
        // Input parameter should be of NoteFolder or we shouldn't check for validness of the index
        this.currentFolder = folder;
        this.modelResetSignal.emit();
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
    public boolean setData(ModelIndex index, Object note, int role) {
        if (role == ModelRole.EDIT_ROLE)
        {
            if (note instanceof Note)
            {
                Note noteToUpdate = (Note) this.getItem(index);
                noteToUpdate.setPinned(((Note) note).getIsPinned());
                noteToUpdate.setTitle(((Note) note).getTitle());
                noteToUpdate.setBody(((Note) note).getBody());
                noteToUpdate.setTags(((Note) note).getTags());
            }
            else if (note instanceof NoteFolder)
            {
                NoteFolder folderToUpdate = (NoteFolder) this.getItem(index);
                folderToUpdate.setPinned(((NoteFolder) note).getIsPinned());
                folderToUpdate.setTags(((NoteFolder) note).getTags());
                folderToUpdate.setTitle(((NoteFolder) note).getTitle());
            }

            this.modelResetSignal.emit();
            return true;
        }
        return false;
    }

    @Override
    public boolean setData(ModelIndex index, Object note) {
        if (note instanceof Note)
        {
            Note noteToUpdate = (Note) this.getItem(index);
            noteToUpdate.setPinned(((Note) note).getIsPinned());
            noteToUpdate.setTitle(((Note) note).getTitle());
            noteToUpdate.setBody(((Note) note).getBody());
            noteToUpdate.setTags(((Note) note).getTags());
        }
        else if (note instanceof NoteFolder)
        {
            NoteFolder folderToUpdate = (NoteFolder) this.getItem(index);
            folderToUpdate.setPinned(((NoteFolder) note).getIsPinned());
            folderToUpdate.setTags(((NoteFolder) note).getTags());
            folderToUpdate.setTitle(((NoteFolder) note).getTitle());
        }

        this.modelResetSignal.emit();
        return true;

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
        else
            return this.currentFolder;
    }

    public ModelIndex itemToIndex(BaseNote note)
    {
        if (note == null)
            return new ModelIndex();

        else if(note == this.currentFolder)
            return new ModelIndex();

        else
        {
            int position = note.getPosition();
            if (position != -1)
                return this.createIndex(position, 0, note);

            return new ModelIndex();
        }
    }

    public void addItem(BaseNote baseNote) {
        this.getCurrentFolder().addChild(baseNote);
        this.rowInsertedSignal.emit(new ModelIndex(), this.rowCount() - 1, this.rowCount() - 1);
    }

    public void addTagToItem(BaseNote item, String tag) {
        item.addTag(tag);
    }

    public void removeTagFromItem(BaseNote item, String tag) {
        item.removeTag(tag);
    }

    public NoteFolder getCurrentFolder() {
        return this.currentFolder;
    }

    public NoteFolder rootFolder()
    {
        NoteFolder folder = this.getCurrentFolder();
        while (folder.getParent() != null)
        {
            folder = folder.getParent();
        }

        return folder;
    }


}
