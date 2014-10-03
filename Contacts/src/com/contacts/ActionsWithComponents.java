package com.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 03.10.14
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class ActionsWithComponents {

    public static void showAlert(Activity activity, int messageId) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setMessage(messageId);
        alertDialog.setPositiveButton(R.string.ok_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        alertDialog.show();
    }

    public static String getTextFromEditText(Activity activity, int editTextId) {
        EditText editText = (EditText) activity.findViewById(editTextId);
        String text = editText.getText().toString();
        return text;
    }

    public static void setTextInTextView(Activity activity, int textViewId, String text) {
        TextView textView = (TextView) activity.findViewById(textViewId);
        textView.setText(text);
    }
}
