package com.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        String name = ActionsWithComponents.getTextFromEditText(this, R.id.input_name);
        String surname = ActionsWithComponents.getTextFromEditText(this, R.id.input_surname);
        String phone = ActionsWithComponents.getTextFromEditText(this, R.id.input_phone_number);
        if(!(name.equals("") && surname.equals("")) && !phone.equals("")) {
            intent.putExtra(EXTRA_NAME, name);
            intent.putExtra(EXTRA_SURNAME, surname);
            intent.putExtra(EXTRA_PHONE, phone);
            startActivity(intent);
        } else {
            ActionsWithComponents.showAlert(this, R.string.empty_fields_message);
        }
    }
}
