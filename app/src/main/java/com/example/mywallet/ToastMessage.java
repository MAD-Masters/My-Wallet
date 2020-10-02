package com.example.mywallet;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ToastMessage {
    private Activity activity;
    private View view;
    public ToastMessage(Activity activity, View view) {
        this.activity = activity;
        this.view = view;
    }

    public void errorToast(String message) {
        View toastView = activity.getLayoutInflater().inflate(R.layout.toast_red, (ViewGroup) view.findViewById(R.id.toast_lin_lay));
        TextView toastText = toastView.findViewById(R.id.toast_text_red);
        toastText.setText(message);

        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void successToast(String message) {
        View toastView = activity.getLayoutInflater().inflate(R.layout.toast_blue, (ViewGroup) view.findViewById(R.id.toast_lin_lay));
        TextView toastText = toastView.findViewById(R.id.toast_text_red);
        toastText.setText(message);

        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
        toast.show();
    }
}
