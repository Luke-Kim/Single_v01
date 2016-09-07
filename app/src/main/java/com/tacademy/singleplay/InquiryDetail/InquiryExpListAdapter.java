package com.tacademy.singleplay.inquirydetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.tacademy.singleplay.R;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class InquiryExpListAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> group = null;
    private ArrayList<ArrayList<String>> child = null;
    private LayoutInflater inflater = null;
    InqExpViewHolder inqExpViewHolder = null;

    public InquiryExpListAdapter(Context context, ArrayList<String> group, ArrayList<ArrayList<String>> child) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.group = group;
        this.child = child;
    }
    // 그룹 포지션을 반환한다.
    @Override
    public String getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    // 그룹 사이즈를 반환한다.
    @Override
    public int getGroupCount() {
        return group.size();
    }

    // 그룹 ID를 반환한다.
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 그룹뷰 각각의 ROW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            inqExpViewHolder = new InqExpViewHolder();
            v = inflater.inflate(R.layout.view_inquiry, parent, false);
            inqExpViewHolder.tv_groupName = (TextView) v.findViewById(R.id.inq_title);
            v.setTag(inqExpViewHolder);
        }else{
            inqExpViewHolder = (InqExpViewHolder) v.getTag();
        }

        inqExpViewHolder.tv_groupName.setText(getGroup(groupPosition));

        return v;
    }

    // 차일드뷰를 반환한다.
    @Override
    public String getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    // 차일드뷰 사이즈를 반환한다.
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    // 차일드뷰 ID를 반환한다.
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 차일드뷰 각각의 ROW
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            inqExpViewHolder = new InqExpViewHolder();
            v = inflater.inflate(R.layout.view_inq_child, null);
            inqExpViewHolder.tv_childName = (TextView) v.findViewById(R.id.inq_child);
            v.setTag(inqExpViewHolder);
        }else{
            inqExpViewHolder = (InqExpViewHolder) v.getTag();
        }

        inqExpViewHolder.tv_childName.setText(getChild(groupPosition, childPosition));

        return v;
    }

    @Override
    public boolean hasStableIds() {	return true; }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }

    class InqExpViewHolder {
        public TextView tv_groupName;
        public TextView tv_childName;
    }
}
