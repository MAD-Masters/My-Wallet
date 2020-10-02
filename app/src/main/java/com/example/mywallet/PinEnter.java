package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PinEnter extends AppCompatActivity {
    private EditText pinEnter;
    private AppCompatButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    private TextView cancel, remove;
    private ImageView i1, i2, i3, i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_enter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isPin =  preferences.getBoolean("isPin", false);

        b0 = findViewById(R.id.btn0);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);
        i1 = findViewById(R.id.c1);
        i2 = findViewById(R.id.c2);
        i3 = findViewById(R.id.c3);
        i4 = findViewById(R.id.c4);
        remove = findViewById(R.id.btnRemove);
        cancel = findViewById(R.id.btnClear);

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
                if (pinEnter.getText().length() == 0) {
                    i1.setImageResource(R.drawable.circle_white);
                    i2.setImageResource(R.drawable.circle_white);
                    i3.setImageResource(R.drawable.circle_white);
                    i4.setImageResource(R.drawable.circle_white);
                } else if (pinEnter.getText().length() == 1) {
                    i1.setImageResource(R.drawable.circle_black);
                    i2.setImageResource(R.drawable.circle_white);
                    i3.setImageResource(R.drawable.circle_white);
                    i4.setImageResource(R.drawable.circle_white);
                } else if (pinEnter.getText().length() == 2) {
                    i1.setImageResource(R.drawable.circle_black);
                    i2.setImageResource(R.drawable.circle_black);
                    i3.setImageResource(R.drawable.circle_white);
                    i4.setImageResource(R.drawable.circle_white);
                } else if (pinEnter.getText().length() == 3) {
                    i1.setImageResource(R.drawable.circle_black);
                    i2.setImageResource(R.drawable.circle_black);
                    i3.setImageResource(R.drawable.circle_black);
                    i4.setImageResource(R.drawable.circle_white);
                } else if (pinEnter.getText().length() == 4) {
                    i1.setImageResource(R.drawable.circle_black);
                    i2.setImageResource(R.drawable.circle_black);
                    i3.setImageResource(R.drawable.circle_black);
                    i4.setImageResource(R.drawable.circle_black);
                    if (isCorrect()) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(0);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(6);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(7);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(8);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(9);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinEnter.setText("");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pinEnter.getText().length() > 0) {
                    pinEnter.setText(pinEnter.getText().toString().substring(0, pinEnter.length() - 1));
                }
            }
        });








    }

    public void changeText(int n) {
        pinEnter.setText(pinEnter.getText().toString().trim() + n);
    }

    public boolean isCorrect() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String pin =  preferences.getString("pinCode", "1234");

        if (pin.equals(pinEnter.getText().toString())) {
            return true;
        } else {
            ToastMessage toastMessage = new ToastMessage(this, View.inflate(getApplicationContext(), R.layout.activity_pin_enter, null));
            toastMessage.errorToast("Please Check the PIN Code");
            pinEnter.setText("");
            return false;
        }
    }
}