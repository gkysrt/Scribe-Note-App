package com.scribenoteapp.scribe.model;

import java.util.ArrayList;

/**
 * Created by ALLDe on 15/01/2020.
 */

public class NoteModel {
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

    public void addTagToItem(BaseNote item, String tag)
    {
        item.addTag(tag);
    }

    public void removeTagFromItem(BaseNote item, String tag)
    {
        item.removeTag(tag);
    }
}
