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
        setContentView(R.layout.displaying_contacts);
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
        String name = intent.getStringExtra(EnteringInfoActivity.EXTRA_NAME);
        String surname = intent.getStringExtra(EnteringInfoActivity.EXTRA_SURNAME);
        String phone = intent.getStringExtra(EnteringInfoActivity.EXTRA_PHONE);

        ActionsWithComponents.setTextInTextView(this, R.id.name, name);
        ActionsWithComponents.setTextInTextView(this, R.id.surname, surname);
        ActionsWithComponents.setTextInTextView(this, R.id.phone, phone);
    }
}