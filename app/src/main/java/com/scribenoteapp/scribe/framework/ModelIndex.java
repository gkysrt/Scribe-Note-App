package com.scribenoteapp.scribe.framework;

import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.framework.namespace.ItemFlag;

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
        if (this.model() == null)
            return null;

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
        if (this.model() == null)
            return null;

        return this.model().parent(this);
    }

    public Object internalPointer()
    {
        if (this.model() == null)
            return null;

        return this.ptr;
    }

    public ItemFlag[] flags()
    {
        if (this.model() == null)
            return null;

        return this.model().flags();
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
