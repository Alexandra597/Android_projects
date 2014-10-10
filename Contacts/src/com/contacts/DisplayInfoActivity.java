package com.contacts;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 02.10.14
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class DisplayInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaying_single_contact);
        showContactsInfo();
    }

    public void makeCall(View view) {
        TextView phoneNumberView = (TextView) findViewById(R.id.phone);
        String phoneNumber = phoneNumberView.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    private void showContactsInfo() {
        Intent intent = getIntent();
        Contact person = (Contact) intent.getParcelableExtra(ActionsWithComponents.EXTRA_PERSON);

        ActionsWithComponents.setTextInTextView(this, R.id.name, person.getName());
        ActionsWithComponents.setTextInTextView(this, R.id.surname, person.getSurname());
        ActionsWithComponents.setTextInTextView(this, R.id.phone, person.getPhone());
    }

    public void showAll(View view) {
        Intent intent = new Intent(this, DisplayContactsListActivity.class);
        startActivity(intent);
    }
}