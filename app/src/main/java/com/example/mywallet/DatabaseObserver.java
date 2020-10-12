package com.example.mywallet;

import java.text.ParseException;

public interface DatabaseObserver {
    void onDatabaseChanged() throws ParseException;
}