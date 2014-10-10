package com.contacts;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class EnteringInfoActivity extends Activity {
    private DBHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entering_info);
    }

    public void sendInfo(View view) {
        Intent intent = new Intent(this, DisplayInfoActivity.class);
        String name = ActionsWithComponents.getTextFromEditText(this, R.id.input_name);
        String surname = ActionsWithComponents.getTextFromEditText(this, R.id.input_surname);
        String phone = ActionsWithComponents.getTextFromEditText(this, R.id.input_phone_number);
        Contact person = new Contact(name, surname, phone);
        if(!(name.equals("") && surname.equals("")) && !phone.equals("")) {
            addToContacts(person);
            intent.putExtra(ActionsWithComponents.EXTRA_PERSON, person);
            startActivity(intent);
        } else {
            ActionsWithComponents.showAlert(this, R.string.empty_fields_message);
        }
    }

    public void addToContacts(Contact person) {
        dbHelper = new DBHelper(this);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NAME, person.getName());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME, person.getSurname());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE, person.getPhone());
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        dbHelper.close();
    }
}
