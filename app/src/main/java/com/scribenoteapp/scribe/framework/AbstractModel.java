package com.scribenoteapp.scribe.framework;

import com.scribenoteapp.scribe.framework.namespace.ItemFlag;
import com.scribenoteapp.scribe.framework.signals.Signal;
import com.scribenoteapp.scribe.framework.signals.Signal3;

/**
 * Created by Gokay on 16/01/2020.
 */

public abstract class AbstractModel {
    public final Signal modelResetSignal;
    public final Signal3<ModelIndex, Integer, Integer> rowInsertedSignal;
    public final Signal3<ModelIndex, Integer, Integer> rowRemovedSignal;

    public AbstractModel() {
        this.modelResetSignal = new Signal();
        this.rowInsertedSignal = new Signal3<>();
        this.rowRemovedSignal = new Signal3<>();
    }

    public abstract ModelIndex index(int row, int column, ModelIndex parent);

    public abstract ModelIndex index(int row, int column);

    public abstract Object data(ModelIndex index, int role);

    public abstract Object data(ModelIndex index);

    public abstract int rowCount(ModelIndex parent);

    public abstract int rowCount();

    public abstract int columnCount(ModelIndex parent);

    public abstract int columnCount();

    public abstract ModelIndex parent(ModelIndex index);

    public int flags() {
        return ItemFlag.ITEM_IS_DRAG_ENABLED |
                ItemFlag.ITEM_IS_DROP_ENABLED |
                ItemFlag.ITEM_IS_EDITABLE |
                ItemFlag.ITEM_IS_ENABLED |
                ItemFlag.ITEM_IS_SELECTABLE |
                ItemFlag.ITEM_IS_USER_CHECKABLE;
    }

    public ModelIndex createIndex(int row, int column, Object ptr) {

        return new ModelIndex(row, column, ptr, this);
    }
}
