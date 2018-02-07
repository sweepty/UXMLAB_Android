package com.example.seungyeonlee.uxmlab_assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by seungyeonlee on 2018. 2. 7..
 */

public class AssignmentListViewAdapter extends BaseAdapter {
    private ArrayList<AssignmentListViewItem> AsListViewItemList = new ArrayList<AssignmentListViewItem>();

    public AssignmentListViewAdapter() {

    }

    @Override
    public int getCount() {
        return AsListViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return AsListViewItemList.get(position) ;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        AssignmentListViewItem listViewItem = AsListViewItemList.get(position);

        titleTextView.setText(listViewItem.getAsList_title());
        descTextView.setText(listViewItem.getAsList_content());

        return convertView;
    }
    public void addAsList(String title,String content) {
        AssignmentListViewItem item = new AssignmentListViewItem();

        item.setAsList_title(title);
        item.setAsList_content(content);
        AsListViewItemList.add(item);
    }
    public void addAsTextView(String content) {
        AssignmentListViewItem item2 = new AssignmentListViewItem();

    }

}
