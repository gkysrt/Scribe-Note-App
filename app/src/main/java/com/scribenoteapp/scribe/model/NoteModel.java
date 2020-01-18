package com.scribenoteapp.scribe.model;

import com.scribenoteapp.scribe.framework.AbstractModel;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.NoteFolder;

import java.util.ArrayList;

/**
 * Created by ALLDe on 15/01/2020.
 */

public class NoteModel extends AbstractModel{
    private ArrayList<BaseNote> notes;
    private NoteFolder currentFolder;
    private BaseNote displayedItem;
    private ArrayList<String> tags;

    public NoteModel()
    {
        this.currentFolder = null;
        this.displayedItem = null;
        this.tags = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    @Override
    public ModelIndex index(int row, int column, ModelIndex parent) {
        return null;
    }

    @Override
    public ModelIndex index(int row, int column) {
        return null;
    }

    @Override
    public Object data(ModelIndex index, ItemDataModel role) {
        return null;
    }

    @Override
    public int rowCount() {
        return 0;
    }

    @Override
    public int columnCount() {
        return 0;
    }

    @Override
    public ModelIndex parent(ModelIndex index) {
        return null;
    }

    public void addTagToItem(BaseNote item, String tag)
    {
        item.addTag(tag);
    }

    public void removeTagFromItem(BaseNote item, String tag)
    {
        item.removeTag(tag);
    }
}
