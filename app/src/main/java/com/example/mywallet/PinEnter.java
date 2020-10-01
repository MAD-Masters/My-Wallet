package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class PinEnter extends AppCompatActivity {
    private EditText pinEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isPin =  preferences.getBoolean("isPin", false);

        if (!isPin) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        pinEnter = findViewById(R.id.pinEnter);

        pinEnter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (pinEnter.getText().length() == 4) {
                    if (isCorrect()) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public boolean isCorrect() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String pin =  preferences.getString("pinCode", "1234");

        if (pin.equals(pinEnter.getText().toString())) {
            return true;
        } else {
            ToastMessage toastMessage = new ToastMessage(this, View.inflate(getApplicationContext(), R.layout.activity_pin_enter, null));
            toastMessage.errorToast("Please Check the PIN Code");
            return false;
        }
    }
}