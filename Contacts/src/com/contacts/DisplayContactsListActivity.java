package com.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 03.10.14
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class DisplayContactsListActivity extends Activity {
    private DBHelper dbHelper;
    ListOfContacts listOfContacts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaying_contacts_list);
        readDataFromContactsDB();
    }

    private void readDataFromContactsDB() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE,
        };
        String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + " ASC";
        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,
                                 projection,
                                 null, null, null, null,
                                 sortOrder);
        if (cursor.moveToFirst()) {
            listOfContacts = new ListOfContacts(this, cursor);
        } else {
            listOfContacts = new ListOfContacts(this);
            ActionsWithComponents.showAlert(this, R.string.no_contacts_message);
        }
        listOfContacts.showList();
        cursor.close();
        dbHelper.close();
    }

    public void addContact(View view) {
        Intent intent = new Intent(this, EnteringInfoActivity.class);
        startActivity(intent);
    }

    public void clearList(View view) {
        AlertDialog.Builder alertDialog = ActionsWithComponents.createConfirmationAlert(this, R.string.deletion_confirmation_message, R.string.clear_contacts_title);
        alertDialog.setPositiveButton(R.string.erase_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, null, null);
                        listOfContacts.clearList();
                        listOfContacts.showList();
                        dialog.cancel();
                    }
                }
        );
        alertDialog.show();
    }
}