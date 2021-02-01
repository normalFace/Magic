package com.sssstar.magic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;

public class friendAdapter extends BaseAdapter{
    private LinkedList<friend> friendLinkedlist;
    private Context mContext;

    public friendAdapter(LinkedList<friend> friendLinkedlist,Context mContext){
        this.friendLinkedlist = friendLinkedlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return friendLinkedlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;

}
}
