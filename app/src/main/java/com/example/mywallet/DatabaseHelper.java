package com.example.mywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Trace;

import androidx.annotation.Nullable;

import com.example.mywallet.UI.Expenses.Model.DailyExpense;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_EXPENSES = "EXPENSES";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "my_wallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createExpensesTable = "CREATE TABLE " + TABLE_EXPENSES + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT REAL, DATE TEXT, CATEGORY INTEGER, NOTE TEXT, WALLET_ID INT)";
        db.execSQL(createExpensesTable);
        System.out.println("Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_EXPENSES);
        onCreate(db);
    }

    public boolean addExpense(DailyExpense dailyExpense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT", dailyExpense.getAmount());
        contentValues.put("DATE", String.valueOf(dailyExpense.getDate()));
        contentValues.put("CATEGORY", dailyExpense.getCategoryId());
        contentValues.put("NOTE", dailyExpense.getNote());
        contentValues.put("WALLET_ID", dailyExpense.getWalletID());

        long status = db.insert(TABLE_EXPENSES, null, contentValues);
        System.out.println("Database Record Added");

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }
}
