package com.scribenoteapp.scribe.framework;

import com.scribenoteapp.scribe.framework.namespace.ItemDataModel;
import com.scribenoteapp.scribe.framework.namespace.ItemFlag;

/**
 * Created by ALLDe on 16/01/2020.
 */
// todo: bu generic class olsa daha iyi olabilir. içinde tutacağı data
public abstract class AbstractModel /*<T>*/ {

    public abstract ModelIndex index(int row, int column, ModelIndex parent);

    public abstract ModelIndex index(int row, int column);

    public abstract Object data(ModelIndex index, ItemDataModel role);

    public abstract int rowCount();

    public abstract int columnCount();

    public abstract ModelIndex parent(ModelIndex index);

    // Default implementation of flags()
    public ItemFlag[] flags()
    {
        return new ItemFlag[]{
                ItemFlag.ITEM_IS_DRAG_ENABLED,
                ItemFlag.ITEM_IS_DROP_ENABLED,
                ItemFlag.ITEM_IS_EDITABLE,
                ItemFlag.ITEM_IS_ENABLED,
                ItemFlag.ITEM_IS_SELECTABLE,
                ItemFlag.ITEM_IS_USER_CHECKABLE};
    }

    public ModelIndex createIndex(int row, int column, Object /*T*/ ptr)
    {
        return new ModelIndex(row, column, ptr, this);
    }
}
