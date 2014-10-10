package com.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 07.10.14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class ContactsAdapter extends BaseAdapter {
    Context context;
    LayoutInflater lInflater;
    ArrayList<Contact> contacts;

    public ContactsAdapter(Context ctx, ArrayList<Contact> persons) {
        context = ctx;
        contacts = persons;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    public Contact getContact(int position) {
        return (Contact) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = lInflater.inflate(R.layout.contacts_item, parent, false);
        } else {
            view = convertView;
        }

        Contact person = getContact(position);

        ((TextView) view.findViewById(R.id.contacts_name)).setText(person.getName());
        ((TextView) view.findViewById(R.id.contacts_surname)).setText(person.getSurname());
        ((TextView) view.findViewById(R.id.contacts_phone)).setText(person.getPhone());;

        convertView = view;
        return view;
    }
}
