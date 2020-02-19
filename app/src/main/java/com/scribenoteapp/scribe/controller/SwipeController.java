package com.scribenoteapp.scribe.controller;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.Gravity;

/**
 * <p>SwipeController extends Callback. Callback is an abstract static class inside "ItemTouchHelper" class.
 * Using the upper class ItemTouchHelper might prove useful in the future.
 * <p>See <a href ="https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper">ItemTouchHelper</a> documentation</p>
 * </p>
 */


public class SwipeController extends Callback {
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, Gravity.LEFT | Gravity.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
