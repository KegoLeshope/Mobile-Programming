package com.example.myexpensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Column_ListAdapter extends ArrayList<User> {

    private LayoutInflater mInflator;
    private ArrayList<User> users;
    private int mViewResourceId;

    public Column_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
       // super(context, textViewResourceId, users);
        this.users = users;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = mInflator.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView item = (TextView) convertView.findViewById(R.id.item);
            TextView category = (TextView) convertView.findViewById(R.id.category);
            TextView amount = (TextView) convertView.findViewById(R.id.amount);
            TextView date = (TextView) convertView.findViewById(R.id.etDate);

            if (item != null) {
                item.setText((user.getItem()));
            }
            if (category != null) {
                category.setText((user.getCategory()));
            }
            if (amount != null) {
                amount.setText((user.getAmount()));
            }
            if (date != null) {
                date.setText((user.getDate()));
            }
        }
        return convertView;
    }

}




