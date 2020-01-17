package com.scribenoteapp.scribe.framework;

/**
 * Created by ALLDe on 16/01/2020.
 */

public class ModelIndex {
    private AbstractModel abstractModel;
    private int indexRow;
    private int indexColumn;
    private Object ptr;

    public ModelIndex()
    {
        this.indexRow = -1;
        this.indexColumn = -1;
        this.ptr = null;
        this.abstractModel = null;
    }

    protected ModelIndex(int row, int column, Object ptr, AbstractModel model)
    {
        this.indexRow = row;
        this.indexColumn = column;
        this.ptr = ptr;
        this.abstractModel = model;
    }

    public Object data(ItemDataModel role)
    {
        return this.model().data(this, role);
    }

    public boolean isValid()
    {
        return (this.abstractModel != null &&
                this.indexRow >= 0 &&
                this.indexColumn >= 0
        );
    }

    public ModelIndex parent()
    {
        return this.model().parent(this);
    }

    public int row()
    {
        return this.indexRow;
    }

    public int column()
    {
        return this.indexColumn;
    }

    public AbstractModel model()
    {
        return this.abstractModel;
    }

}
