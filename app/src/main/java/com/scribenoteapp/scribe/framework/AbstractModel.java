package com.scribenoteapp.scribe.framework;

/**
 * Created by ALLDe on 16/01/2020.
 */

public abstract class AbstractModel {
    public AbstractModel()
    {

    }

    public abstract ModelIndex index(int row, int column, ModelIndex parent);

    public abstract ModelIndex index(int row, int column);

    public abstract Object data(ModelIndex index, ItemDataModel role);

    public abstract int rowCount();

    public abstract int columnCount();

    public abstract ModelIndex parent(ModelIndex index);

    public ModelIndex createIndex(int row, int column, Object ptr)
    {
        return new ModelIndex(row, column, ptr, this);
    }
}
