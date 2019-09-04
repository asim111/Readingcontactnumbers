package com.example.readingcontactnumbers;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.hbb20.GThumb;

import java.util.ArrayList;

class CustomAdapter implements ListAdapter {
    Context context;
    ArrayList<Contact> arrayList;
    public CustomAdapter(Context mainActivity, ArrayList<Contact> phonelist) {
        this.context = mainActivity;
        this.arrayList = phonelist;
    }

    public CustomAdapter() {
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = arrayList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listitem, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            TextView textViewName = convertView.findViewById(R.id.tvName);
            TextView textViewNumber = convertView.findViewById(R.id.tvNumber);
            GThumb gthumb=(GThumb)convertView.findViewById(R.id.gthumb);
            textViewName.setText(contact.getName());
            textViewNumber.setText(contact.getPhone());
            gthumb.loadThumbForName(null,contact.getName());
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
