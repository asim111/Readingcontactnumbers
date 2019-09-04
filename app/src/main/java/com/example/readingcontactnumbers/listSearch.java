package com.example.readingcontactnumbers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hbb20.GThumb;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class listSearch extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private List<Contact> contactList = null;
    private ArrayList<Contact> arrayList;

    public listSearch(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        layoutInflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Contact>();
        this.arrayList.addAll(contactList);
    }

    public class ViewHolder {
        TextView name;
        TextView number;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.listitem, null);
            GThumb gthumb = (GThumb) view.findViewById(R.id.gthumb);
            holder.name = view.findViewById(R.id.tvName);
            holder.number = view.findViewById(R.id.tvNumber);
            gthumb.loadThumbForName(null, contactList.get(position).getName());
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(contactList.get(position).getName());
        holder.number.setText(contactList.get(position).getPhone());
        return view;
    }

    public void filter(String text) {
        text = text.toLowerCase(Locale.getDefault());
        contactList.clear();
        if (text.length() == 0) {
            contactList.addAll(arrayList);
        } else {
            for (Contact wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(text)) {
                    contactList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
