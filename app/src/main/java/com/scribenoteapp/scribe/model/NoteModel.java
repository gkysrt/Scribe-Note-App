package com.scribenoteapp.scribe.model;

import com.scribenoteapp.scribe.framework.AbstractModel;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;
import com.scribenoteapp.scribe.model.note.NoteFolder;

import java.util.ArrayList;

/**
 * Created by ALLDe on 15/01/2020.
 */
public class NoteModel extends AbstractModel{
    private NoteFolder rootFolder;
    private NoteFolder currentFolder;
    private BaseNote displayedItem;
    private ArrayList<String> tags;

    public NoteModel()
    {
        this.rootFolder = new NoteFolder("/", null);
        this.currentFolder = this.rootFolder;
        this.displayedItem = null;
        this.tags = new ArrayList<>();
        // TODO: SİLİNCEK
        this.init();
    }

    public void init()
    {
        rootFolder.addChild(new Note("kill meeeeeee", "PLEASE!!!!"));
        rootFolder.addChild(new Note("wooohoo", "WHO?"));
        rootFolder.addChild(new Note("Betty Botter bought some butter\n" +
                "But she said the butter’s bitter\n" +
                "If I put it in my batter, it will make my batter bitter\n" +
                "But a bit of better butter will make my batter better\n" +
                "So ‘twas better Betty Botter bought a bit of better butter", "DARE!!!!"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new Note("Fuzzy Wuzzy was a bear. Fuzzy Wuzzy had no hair. Fuzzy Wuzzy wasn’t fuzzy, was he?", "WHY NOT"));
        rootFolder.addChild(new NoteFolder("asd", rootFolder));
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

        if (role == ItemDataModel.DISPLAY_ROLE)
            return item.filename();

        else if (role == ItemDataModel.USER_ROLE)
        {
            return item;
        }

        return null;
    }

    @Override
    public Object data(ModelIndex index) {
        BaseNote item = (BaseNote) index.internalPointer();
        return item.filename();
    }

    @Override
    public int rowCount(ModelIndex parent)
    {
        NoteFolder parentItem = (NoteFolder) this.getItem(parent);
        return parentItem.childCount();
    }

    @Override
    public int rowCount() {
        return this.currentFolder.childCount();
    }

    @Override
    public int columnCount(ModelIndex parent) {
        return 0;
    }

    @Override
    public int columnCount() {
        return 0;
    }

    @Override
    public ModelIndex parent(ModelIndex index) {
        if (!index.isValid())
            return new ModelIndex();

        BaseNote note = (BaseNote) index.data(ItemDataModel.USER_ROLE);
        return createIndex(note.getParent().getPosition(), 0, note.getParent());
    }

    public BaseNote getItem(ModelIndex index)
    {
        if (index == null)
            return null;

        if (index.isValid())
            return (BaseNote) index.data(ItemDataModel.USER_ROLE);

        return this.currentFolder;
    }
    public void addTagToItem(BaseNote item, String tag)
    {
        item.addTag(tag);
    }

    public void removeTagFromItem(BaseNote item, String tag)
    {
        item.removeTag(tag);
    }

    public NoteFolder currentFolder()
    {
        return this.currentFolder;
    }

    public NoteFolder rootFolder()
    {
        return this.rootFolder;
    }


}
