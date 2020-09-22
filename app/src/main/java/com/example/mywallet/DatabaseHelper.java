package com.example.mywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mywallet.UI.Expenses.Model.DailyExpense;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_EXPENSES = "EXPENSES";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "my_wallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Expense Table
        String createTable = "CREATE TABLE WALLET (ID INTEGER PRIMARY KEY AUTOINCREMENT, WALLET_NAME TEXT, BANK TEXT)";
        db.execSQL(createTable);
        Log.d("database", "Wallet Table Created");

        //Income Table
        createTable = "CREATE TABLE INCOME (ID INTEGER PRIMARY KEY AUTOINCREMENT, WALLET_ID INT, AMOUNT REAL, DATE TEXT, NOTE TEXT, FOREIGN KEY(WALLET_ID) REFERENCES WALLET(ID))";
        db.execSQL(createTable);
        Log.d("database", "Income Table Created");

        //Category Table
        createTable = "CREATE TABLE CATEGORY (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)";
        db.execSQL(createTable);
        Log.d("database", "Income Table Created");

        //Expense Table
        createTable = "CREATE TABLE " + TABLE_EXPENSES + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT REAL, DATE TEXT, CATEGORY INTEGER, NOTE TEXT, WALLET_ID INT, FOREIGN KEY(WALLET_ID) REFERENCES WALLET(ID))";
        db.execSQL(createTable);
        Log.d("database", "Expense Table Created");

        //Goal Table
        createTable = "CREATE TABLE GOAL (ID INTEGER PRIMARY KEY AUTOINCREMENT, GOAL_NAME TEXT, DATE TEXT, AMOUNT REAL)";
        db.execSQL(createTable);
        Log.d("database", "Goal Table Created");

        //Goal Money
        createTable = "CREATE TABLE GOAL_MONEY (ID INTEGER PRIMARY KEY AUTOINCREMENT, GOAL_ID INT, AMOUNT REAL, FOREIGN KEY(GOAL_ID) REFERENCES GOAL(ID))";
        db.execSQL(createTable);
        Log.d("database", "Goal Money Table Created");

        //Budget Table
        createTable = "CREATE TABLE BUDGET (ID INTEGER PRIMARY KEY AUTOINCREMENT, CAT_ID INT, AMOUNT REAL, FOREIGN KEY(CAT_ID) REFERENCES CATEGORY(ID))";
        db.execSQL(createTable);
        Log.d("database", "Budget Table Created");

        addCategoryItems(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE INCOME");
        db.execSQL("DROP TABLE CATEGORY");
        db.execSQL("DROP TABLE WALLET");
        db.execSQL("DROP TABLE GOAL");
        db.execSQL("DROP TABLE GOAL_MONEY");
        db.execSQL("DROP TABLE BUDGET");
        onCreate(db);
    }

    //Category Items Insert
    public void addCategoryItems(SQLiteDatabase db) {

        String sql = "INSERT INTO CATEGORY(NAME) VALUES ('Bills'), ('Education'), ('Family'), ('Gifts'), ('Food'), ('Loan')";
        db.execSQL(sql);
        Log.d("database", "Category Items Inserted Successfully");

        sql = "INSERT INTO WALLET(WALLET_NAME, BANK) VALUES ('BOC Account', 'BOC')";
        db.execSQL(sql);

    }

    //Add Expenses
    public boolean addExpense(DailyExpense dailyExpense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT", dailyExpense.getAmount());
        contentValues.put("DATE", String.valueOf(dailyExpense.getDate()));
        contentValues.put("CATEGORY", dailyExpense.getCategoryId());
        contentValues.put("NOTE", dailyExpense.getNote());
        contentValues.put("WALLET_ID", dailyExpense.getWalletID());

        long status = db.insert(TABLE_EXPENSES, null, contentValues);

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }
}
