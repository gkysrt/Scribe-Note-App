package com.scribenoteapp.scribe.framework;

import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.framework.namespace.ItemFlag;

/**
 * Created by ALLDe on 16/01/2020.
 */
// todo: burada sadece abstract model için görülen bir private constructor yapılabiliyor mu bakmak lazım.
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
        // todo: burada model yoksa exception atmalı null dönmesin. çünkü null dönmesi gereken data
        // todo: olabilir. onunla arasında fark olmalı.
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
    // todo burada valid değilse -1 dönmeli index
    public int row()
    {
        return this.indexRow;
    }
    // todo burada valid değilse -1 dönmeli index
    public int column()
    {
        return this.indexColumn;
    }

    public AbstractModel model()
    {
        return this.abstractModel;
    }

}
