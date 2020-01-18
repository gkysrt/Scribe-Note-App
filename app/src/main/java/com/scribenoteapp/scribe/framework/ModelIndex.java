package com.scribenoteapp.scribe.framework;

import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.framework.namespace.ItemFlag;

/**
 * Created by Gokay on 16/01/2020.
 */

/**  NOTE: Model eklenebilen constructor protected olduğu için
 *   şu anda sadece aynı package içindeki classlar erişebiliyor.
 *   Böylelikle framework package dışından valid index
 *   sadece AbstractModel.createIndex() func ile olabiliyor.
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

    public Object data(ItemDataModel role) throws Exception {
        if (this.isValid())
            return this.model().data(this, role);

        throw new Exception("Invalid index has no data");

//        return (this.isValid()) ? this.model().data(this, role) : new Exception("No model");
    }

    public Object data() throws Exception
    {
        if (this.isValid())
            return this.model().data(this, ItemDataModel.DISPLAY_ROLE);

        throw new Exception("Invalid index has no data");
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
        return this.isValid() ? this.model().parent(this) : null;
    }

    public Object internalPointer()
    {
        return this.isValid() ? this.ptr : null;
    }

    public ItemFlag[] flags()
    {
        return this.isValid() ? this.model().flags() : null;
    }

    public int row()
    {
        return this.isValid() ? this.indexRow : -1;
    }

    public int column()
    {
        return this.isValid() ? this.indexColumn : -1;
    }

    public AbstractModel model()
    {
        return this.abstractModel;
    }

    public ModelIndex sibling(int row, int column)
    {
        return this.isValid() ? this.model().createIndex(row, column, this.internalPointer()) : new ModelIndex();
    }

    public ModelIndex siblingAtRow(int row)
    {
        return this.isValid() ? this.model().createIndex(row, this.column(), this.internalPointer()) : new ModelIndex();
    }

    public ModelIndex siblingAtColumn(int column)
    {
        return this.isValid() ? this.model().createIndex(this.row(), column, this.internalPointer()) : new ModelIndex();
    }
}
