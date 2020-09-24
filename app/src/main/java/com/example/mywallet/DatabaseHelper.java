package com.example.mywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mywallet.Model.Category;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.IncomeModel;
import com.example.mywallet.Model.Wallet;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_EXPENSES = "EXPENSES";
    public static final String INCOME = "INCOME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "my_wallet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Wallet Table
        String createTable = "CREATE TABLE WALLET (ID INTEGER PRIMARY KEY AUTOINCREMENT, WALLET_NAME TEXT, BANK TEXT)";
        db.execSQL(createTable);
        Log.d("database", "Wallet Table Created");

        //Income Table
        createTable = "CREATE TABLE " + INCOME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, WALLET_ID INT, AMOUNT REAL, DATE TEXT, NOTE TEXT, FOREIGN KEY(WALLET_ID) REFERENCES WALLET(ID))";
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
        db.execSQL("DROP TABLE " + INCOME);
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

    //Get CategoriesList
    public ArrayList<Category> getCategories() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Category> arrayList = new ArrayList<>();
       Cursor cursor = db.rawQuery("SELECT * FROM CATEGORY", null);
       cursor.moveToFirst();
       while (cursor.isAfterLast() == false) {
           Category category = new Category();
           category.setCategoryId(cursor.getInt(cursor.getColumnIndex("ID")));
           category.setCategoryName(cursor.getString(cursor.getColumnIndex("NAME")));

           arrayList.add(category);
           cursor.moveToNext();
       }
        return arrayList;
    }

    //Get CategoriesList
    public ArrayList<Wallet> getWalletsList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Wallet> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM WALLET", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Wallet wallet = new Wallet();
            wallet.setWalletId(cursor.getInt(cursor.getColumnIndex("ID")));
            wallet.setWalletName(cursor.getString(cursor.getColumnIndex("WALLET_NAME")));
            wallet.setBank(cursor.getString(cursor.getColumnIndex("BANK")));

            arrayList.add(wallet);
            cursor.moveToNext();
        }
        return arrayList;
    }

    //Get Expenses ArrayList
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<DailyExpense> getMonthlyExpenses(int month, int year) throws ParseException {
        Format f = new SimpleDateFormat("MMM");
        Month mon = Month.of(month);
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0);
        String monthS = f.format(c.getTime());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DailyExpense> arrayList = new ArrayList<>();
        //Cursor cursor = db.rawQuery("SELECT * FROM EXPENSES WHERE DATE like '%" + monthS + "%" + year + "'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM EXPENSES WHERE DATE like '%Sep%2020'", null);

        Log.d("andfdfd", monthS);

        cursor.moveToFirst();
        System.out.println("Array Size " + arrayList.size());
        while (cursor.isAfterLast() == false) {
            DailyExpense dailyExpense = new DailyExpense();
            dailyExpense.setRecordId(cursor.getInt(cursor.getColumnIndex("ID")));
            dailyExpense.setAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));
            dailyExpense.setWalletID(cursor.getInt(cursor.getColumnIndex("WALLET_ID")));
            dailyExpense.setCategoryId(cursor.getInt(cursor.getColumnIndex("CATEGORY")));
            //dailyExpense.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
            String not = cursor.getString(cursor.getColumnIndex("NOTE"));

            arrayList.add(dailyExpense);
            cursor.moveToNext();
        }



        return arrayList;
    }


    //addincome
    public boolean addincome(IncomeModel incomeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT",incomeModel.getMoney());
        contentValues.put("DATE", String.valueOf(incomeModel.getDate()));
        contentValues.put("NOTE", incomeModel.getText());
        contentValues.put("WALLET_ID", incomeModel.getWalletid());

        long status = db.insert(INCOME, null, contentValues);

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

    //addwallet
    public boolean addwallet(Wallet wallet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("WALLET_NAME",wallet.getWalletName());
        contentValues.put("BANK ",wallet.getBank());

        long status = db.insert(INCOME, null, contentValues);

        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }


}
