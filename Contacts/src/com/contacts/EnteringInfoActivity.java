package com.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnteringInfoActivity extends Activity {
    public final static String EXTRA_NAME = "com.contacts.NAME";
    public final static String EXTRA_SURNAME = "com.contacts.SURNAME";
    public final static String EXTRA_PHONE = "com.contacts.PHONE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void sendInfo(View view) {
        Intent intent = new Intent(this, DisplayInfoActivity.class);
        EditText nameEditText = (EditText) findViewById(R.id.input_name);
        String name = nameEditText.getText().toString();
        EditText surnameEditText = (EditText) findViewById(R.id.input_surname);
        String surname = surnameEditText.getText().toString();
        EditText phoneEditText = (EditText) findViewById(R.id.input_phone_number);
        String phone = phoneEditText.getText().toString();
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_SURNAME, surname);
        intent.putExtra(EXTRA_PHONE, phone);
        startActivity(intent);
    }
}
