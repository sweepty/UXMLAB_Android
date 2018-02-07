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

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.assignment_listview_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        AssignmentListViewItem listViewItem = AsListViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
//        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getAsList_title());
        descTextView.setText(listViewItem.getAsList_content());

        return convertView;
    }
    public void addTitle(String title) {
        AssignmentListViewItem item = new AssignmentListViewItem();

        item.setAsList_title(title);

        AsListViewItemList.add(item);
    }
    public void addContent(String content) {
        AssignmentListViewItem item = new AssignmentListViewItem();

        item.setAsList_content(content);

        AsListViewItemList.add(item);
    }
}
