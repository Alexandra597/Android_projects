package com.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

        Intent intent = getIntent();
        String name = intent.getStringExtra(EnteringInfoActivity.EXTRA_NAME);
        String surname = intent.getStringExtra(EnteringInfoActivity.EXTRA_SURNAME);
        String phone = intent.getStringExtra(EnteringInfoActivity.EXTRA_PHONE);

        TextView nameTextView = (TextView)findViewById(R.id.name);
        nameTextView.setText(name);
        TextView surnameTextView = (TextView)findViewById(R.id.surname);
        surnameTextView.setText(surname);
        TextView phoneTextView = (TextView)findViewById(R.id.phone);
        phoneTextView.setText(phone);
    }
}