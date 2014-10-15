package com.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 10.10.14
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class ListOfContacts {
    private ArrayList<Contact> contacts;
    private Activity activity;

    ListOfContacts(Activity act) {
        contacts = new ArrayList<Contact>();
        activity = act;
    }

    ListOfContacts(Activity act, ArrayList<Contact> people) {
        contacts = people;
        activity = act;
    }

    ListOfContacts(Activity act, Cursor cursor) {
        activity = act;
        contacts = new ArrayList<Contact>();
        int idColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID);
        int nameColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME);
        int surnameColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME);
        int phoneColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE);
        do {
            Contact person = new Contact(cursor.getString(nameColIndex),
                                         cursor.getString(surnameColIndex),
                                         cursor.getString(phoneColIndex));
            person.setDbID(cursor.getLong(idColIndex));
            contacts.add(person);
        } while (cursor.moveToNext());
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void showList() {
        ListView listView = (ListView) activity.findViewById(R.id.contactsListView);
        ContactsAdapter adapter = new ContactsAdapter(activity, contacts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Context ctx = adapter.getContext();
                Contact contact = (Contact)adapter.getItemAtPosition(position);
                Intent intent = new Intent(ctx, DisplayInfoActivity.class);
                intent.putExtra(ActionsWithComponents.EXTRA_PERSON, contact);
                ctx.startActivity(intent);
            }
        });
    }

    public void clearList() {
        contacts.clear();
    }
}
