package com.example.andreavalenziano.samplejsonparser.adapters;

/**
 * Created by AndreaValenziano on 28/02/17.
 */

public interface ItemTouchHelperAdapter {


    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}