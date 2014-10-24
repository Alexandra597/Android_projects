package com.contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class EnteringInfoActivity extends Activity {
    private DBHelper dbHelper;
    private Contact personToEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entering_info);
        Intent intent = getIntent();
        personToEdit = (Contact) intent.getParcelableExtra(ActionsWithComponents.EXTRA_PERSON);
        if(personToEdit != null) {
            setFields();
        }
    }

    private void setFields() {
        ActionsWithComponents.setTextInEditText(this, R.id.input_name, personToEdit.getName());
        ActionsWithComponents.setTextInEditText(this, R.id.input_surname, personToEdit.getSurname());
        ActionsWithComponents.setTextInEditText(this, R.id.input_phone_number, personToEdit.getPhone());
    }

    public void sendInfo(View view) {
        Intent intent = new Intent(this, DisplayInfoActivity.class);
        String name = ActionsWithComponents.getTextFromEditText(this, R.id.input_name);
        String surname = ActionsWithComponents.getTextFromEditText(this, R.id.input_surname);
        String phone = ActionsWithComponents.getTextFromEditText(this, R.id.input_phone_number);
        Contact newPerson = new Contact(name, surname, phone);
        if(!(name.equals("") && surname.equals("")) && !phone.equals("")) {
            if(personToEdit == null) {
                addToContacts(newPerson);
            } else {
                updateContact(newPerson);
            }
            intent.putExtra(ActionsWithComponents.EXTRA_PERSON, newPerson);
            startActivity(intent);
        } else {
            ActionsWithComponents.showAlert(this, R.string.empty_fields_message);
        }
    }

    public void addToContacts(Contact person) {
        dbHelper = new DBHelper(this);
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, person.getName());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME, person.getSurname());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE, person.getPhone());
        //db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        resolver.insert(Constants.CONTENT_URI_CONTACT, values);
        dbHelper.close();
    }

    public void updateContact(Contact person) {
        dbHelper = new DBHelper(this);
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = personToEdit.getDbID();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, person.getName());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME, person.getSurname());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE, person.getPhone());
//        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values,
//                        FeedReaderContract.FeedEntry._ID + "=?",
//                        new String[] {String.valueOf(id)});
        resolver.update(Constants.CONTENT_URI_CONTACT,values,
                        FeedReaderContract.FeedEntry._ID + "=?",
                       new String[] {String.valueOf(id)});
        dbHelper.close();
    }
}
