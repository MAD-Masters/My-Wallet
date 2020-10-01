package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class UserDetails extends AppCompatActivity {
    private EditText name, email, pin;
    private CheckBox checkBox;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        pin = findViewById(R.id.pin);
        checkBox = findViewById(R.id.checkBox);
        btnSubmit = findViewById(R.id.bnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputFields()) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pinCode", pin.getText().toString());
                    editor.putString("name", name.getText().toString().trim());
                    editor.putString("email", email.getText().toString().trim());
                    editor.putBoolean("isPin", checkBox.isChecked());
                    editor.apply();

                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }


        });
    }

    private boolean checkInputFields() {
        ToastMessage toastMessage = new ToastMessage(this, View.inflate(this, R.layout.activity_user_details, null));
        if(name.getText().length() < 3) {
            toastMessage.errorToast("Enter a valid name");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            toastMessage.errorToast("Enter a valid email");
            return false;
        } else if (pin.length() < 4) {
            toastMessage.errorToast("Enter 4 digits pin");
            return false;
        }
        return true;
    }
}