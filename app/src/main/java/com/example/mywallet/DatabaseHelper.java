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

import com.example.mywallet.Model.Budgetmodel;
import com.example.mywallet.Model.Category;
import com.example.mywallet.Model.DailyExpense;
import com.example.mywallet.Model.FutureGoal;
import com.example.mywallet.Model.IncomeModel;
import com.example.mywallet.Model.IncomeToWallet;
import com.example.mywallet.Model.Wallet;

import java.text.DateFormat;


import java.text.Format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseObservable {
    public static final String TABLE_EXPENSES = "EXPENSES";

    public static final String ID_TABLE = "ID";
    public static final String TABLE_BUDGET = "BUDGET";

    public static final String INCOME = "INCOME";
    public static final String WALLET = "WALLET";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "my_wallet.db", null, 1);
    }

    static DatabaseHelper databaseHelper;
    static ArrayList<DatabaseObserver> observerArrayList;

    //make it Singleton
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
            observerArrayList = new ArrayList<>();
        }
        return databaseHelper;
    }

    @Override
    public void registerDbObserver(DatabaseObserver databaseObserver) {
        if (!observerArrayList.contains(databaseObserver)) {
            observerArrayList.add(databaseObserver);
            Log.d("db", "database observer regiserting..... " + observerArrayList.size());
        }
    }

    @Override
    public void removeDbObserver(DatabaseObserver databaseObserver) {
        observerArrayList.remove(databaseObserver);
    }

    @Override
    public void notifyDbChanged() {
        Log.d("db", "Notify db changed db helper " + observerArrayList.size());
        for (DatabaseObserver databaseObserver:observerArrayList){
            if (databaseObserver!= null){
                Log.d("db", "Notify db changed db helperr done ");

                try {
                    databaseObserver.onDatabaseChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Wallet Table


        String createTable = "CREATE TABLE " + WALLET + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, WALLET_NAME TEXT, BANK TEXT)";

        db.execSQL(createTable);
        Log.d("database", "Wallet Table Created");

        //Income Table


        createTable = "CREATE TABLE " + INCOME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + WALLET + "_ID INT, AMOUNT REAL, DATE TEXT, NOTE TEXT, FOREIGN KEY(WALLET_ID) REFERENCES WALLET(ID))";

        db.execSQL(createTable);
        Log.d("database", "Income Table Created");

        //Category Table
        createTable = "CREATE TABLE CATEGORY (" + ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)";
        db.execSQL(createTable);
        Log.d("database", "Income Table Created");

        //Expense Table


        createTable = "CREATE TABLE " + TABLE_EXPENSES + "(" + ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, AMOUNT REAL, DATE TEXT, CATEGORY INTEGER, NOTE TEXT, WALLET_ID INT, FOREIGN KEY(WALLET_ID) REFERENCES WALLET(ID))";


        db.execSQL(createTable);
        Log.d("database", "Expense Table Created");

        //Goal Table
        createTable = "CREATE TABLE GOAL (" + ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, GOAL_NAME TEXT, DATE TEXT, AMOUNT REAL)";
        db.execSQL(createTable);
        Log.d("database", "Goal Table Created");

        //Goal Money
        createTable = "CREATE TABLE GOAL_MONEY (" + ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, GOAL_ID INT, AMOUNT REAL, FOREIGN KEY(GOAL_ID) REFERENCES GOAL(ID))";
        db.execSQL(createTable);
        Log.d("database", "Goal Money Table Created");

        //Budget Table
        createTable = "CREATE TABLE BUDGET (CAT_ID INT PRIMARY KEY, AMOUNT REAL, FOREIGN KEY(CAT_ID) REFERENCES CATEGORY(ID))";
        db.execSQL(createTable);
        Log.d("database", "Budget Table Created");

        addCategoryItems(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE " + INCOME);
        db.execSQL("DROP TABLE CATEGORY");
        db.execSQL("DROP TABLE " + WALLET);
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
    }

    //Add Expenses
    public boolean addExpense(DailyExpense dailyExpense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT", dailyExpense.getAmount());
        contentValues.put("DATE", String.valueOf(dailyExpense.getDate()));
        contentValues.put("CATEGORY", dailyExpense.getCategoryId());
        contentValues.put("NOTE", dailyExpense.getNote());

        contentValues.put("WALLET_ID", dailyExpense.getWalletID());


        long status = db.insert(TABLE_EXPENSES, null, contentValues);

        db.close();
        notifyDbChanged();
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
            category.setCategoryId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
            category.setCategoryName(cursor.getString(cursor.getColumnIndex("NAME")));

            arrayList.add(category);
            cursor.moveToNext();
        }
        db.close();
        return arrayList;
    }

    //Get WalletList
    public ArrayList<Wallet> getWalletsList() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Wallet> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + WALLET, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            Wallet wallet = new Wallet();

            wallet.setWalletId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
            wallet.setWalletName(cursor.getString(cursor.getColumnIndex("WALLET_NAME")));
            wallet.setBank(cursor.getString(cursor.getColumnIndex("BANK")));

            arrayList.add(wallet);
            cursor.moveToNext();
        }

        db.close();
        return arrayList;
    }

    //Get Expenses ArrayList
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<DailyExpense> getMonthlyExpenses(int month, int year) throws ParseException {
        String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String monthString = monthArray[month];

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DailyExpense> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM EXPENSES WHERE DATE like '%" + monthString + "%" + year + "'", null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            DailyExpense dailyExpense = new DailyExpense();
            dailyExpense.setRecordId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
            dailyExpense.setAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));

            dailyExpense.setWalletID(cursor.getInt(cursor.getColumnIndex("WALLET_" + ID_TABLE)));

            dailyExpense.setCategoryId(cursor.getInt(cursor.getColumnIndex("CATEGORY")));
            dailyExpense.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
            dailyExpense.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));

            arrayList.add(dailyExpense);
            cursor.moveToNext();
        }

        db.close();
        return arrayList;
    }

    public boolean updateExpense(DailyExpense dailyExpense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("AMOUNT", dailyExpense.getAmount());
        contentValues.put("DATE", String.valueOf(dailyExpense.getDate()));
        contentValues.put("CATEGORY", dailyExpense.getCategoryId());
        contentValues.put("NOTE", dailyExpense.getNote());
        contentValues.put("WALLET_ID", dailyExpense.getWalletID());

        long status = db.update(TABLE_EXPENSES, contentValues, ID_TABLE + " = " + dailyExpense.getRecordId(), null);

        db.close();
        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }

        //add goal
        public boolean addGoal(FutureGoal futureGoal){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("GOAL_NAME", futureGoal.getGoal());
            contentValues.put("AMOUNT", futureGoal.getTotalAmount());
            contentValues.put("DATE", futureGoal.getDate().toString());

            long status = db.insert("GOAL", null, contentValues);

            notifyDbChanged();
            db.close();
            if (status == -1) {
                return false;
            } else {
                return true;
            }
        }

        //Get Daily Expense By Id
        public DailyExpense getDailyExpenseById ( int id) throws ParseException {
            SQLiteDatabase db = this.getReadableDatabase();

            String sqlQuery = "SELECT * FROM EXPENSES WHERE " + ID_TABLE + " = " + id;
            Cursor cursor = db.rawQuery(sqlQuery, null);

            DailyExpense dailyExpense = new DailyExpense();

            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            if (cursor.moveToFirst()) {
                dailyExpense.setRecordId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
                dailyExpense.setAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));
                dailyExpense.setWalletID(cursor.getInt(cursor.getColumnIndex("WALLET_" + ID_TABLE)));
                dailyExpense.setCategoryId(cursor.getInt(cursor.getColumnIndex("CATEGORY")));
                dailyExpense.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
                dailyExpense.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
            }
            db.close();

            return dailyExpense;
        }

        //Get Category Name By Id
        public String getCategoryName ( int id){
            SQLiteDatabase db = this.getReadableDatabase();

            String sqlQuery = "SELECT * FROM CATEGORY WHERE " + ID_TABLE + " = " + id;

            Cursor cursor = db.rawQuery(sqlQuery, null);

            if (cursor.moveToFirst()) {
                System.out.println("category" + cursor.getString(cursor.getColumnIndex("NAME")));
                return cursor.getString(cursor.getColumnIndex("NAME"));
            }
            db.close();
            return null;
        }

        //Get Wallet Name By Id
        public String getWalletNameById ( int id){
            SQLiteDatabase db = this.getReadableDatabase();

            String sqlQuery = "SELECT * FROM WALLET WHERE " + ID_TABLE + " = " + id;

            Cursor cursor = db.rawQuery(sqlQuery, null);

            if (cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex("WALLET_NAME"));
            }

            return null;
        }

        //Get Expenses By Date
        public ArrayList<DailyExpense> getDailyExpensesByDate (String date) throws ParseException {
            ArrayList<DailyExpense> dailyExpenses = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String sqlQuery = "SELECT * FROM EXPENSES WHERE DATE LIKE'" + date + "'";
            Cursor cursor = db.rawQuery(sqlQuery, null);

            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                DailyExpense dailyExpense = new DailyExpense();

                dailyExpense.setRecordId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
                dailyExpense.setAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));
                dailyExpense.setWalletID(cursor.getInt(cursor.getColumnIndex("WALLET_" + ID_TABLE)));
                dailyExpense.setCategoryId(cursor.getInt(cursor.getColumnIndex("CATEGORY")));
                dailyExpense.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
                dailyExpense.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));

                dailyExpenses.add(dailyExpense);

                cursor.moveToNext();
            }
            db.close();
            return dailyExpenses;
        }

        //Delete Expenses Record
        public boolean deleteExpenseRecord ( int id){
            SQLiteDatabase db = getWritableDatabase();
            String whereClause = ID_TABLE + " = " + id;
            long status = db.delete("EXPENSES", whereClause, null);

            notifyDbChanged();

            db.close();
            if (status == -1) {
                return false;
            } else {
                return true;
            }

        }

        //Get date array from Income Table
        public ArrayList<Date> getDatesFromIncome () throws ParseException {
            ArrayList<Date> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlQuery = "SELECT * FROM INCOME";

            Cursor cursor = db.rawQuery(sqlQuery, null);
            cursor.moveToFirst();

            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            while (cursor.isAfterLast() == false) {
                arrayList.add(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
                cursor.moveToNext();
            }
            db.close();

            return arrayList;
        }

        //Get date array from Expenses Table
        public ArrayList<Date> getDatesFromExpenses () throws ParseException {
            ArrayList<Date> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            String sqlQuery = "SELECT * FROM " + TABLE_EXPENSES;

            Cursor cursor = db.rawQuery(sqlQuery, null);
            cursor.moveToFirst();

            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            while (cursor.isAfterLast() == false) {
                arrayList.add(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
                cursor.moveToNext();
            }

            db.close();
            return arrayList;
        }


        //addincome
        public boolean addincome (IncomeModel incomeModel){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("AMOUNT", incomeModel.getMoney());
            contentValues.put("DATE", String.valueOf(incomeModel.getDate()));
            contentValues.put("NOTE", incomeModel.getText());
            contentValues.put(WALLET + "_ID", incomeModel.getWalletid());

            long status = db.insert(INCOME, null, contentValues);

            if (status == -1) {
                return false;
            } else {
                return true;
            }
        }

        //addwallet
        public boolean addwallet (Wallet wallet){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("WALLET_NAME", wallet.getWalletName());
            contentValues.put("BANK ", wallet.getBank());

            long status = db.insert(WALLET, null, contentValues);

            if (status == -1) {
                db.close();
                return false;
            } else {
                return true;
            }

        }

        //getincomeList

        public ArrayList<IncomeModel> getincomesList () throws ParseException {

           SQLiteDatabase db = this.getWritableDatabase();
            ArrayList<IncomeModel> arrayList = new ArrayList<>();
            Cursor cursor = db.rawQuery("SELECT * FROM " + INCOME, null);
            cursor.moveToFirst();

            DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            while (cursor.isAfterLast() == false) {
                IncomeModel incomeModel = new IncomeModel();
                incomeModel.setRecordID(cursor.getInt(cursor.getColumnIndex("ID")));
                incomeModel.setText(cursor.getString(cursor.getColumnIndex("NOTE")));
                incomeModel.setMoney(cursor.getDouble(cursor.getColumnIndex("AMOUNT")));
                incomeModel.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));

                arrayList.add(incomeModel);
                cursor.moveToNext();
            }
            db.close();
            return arrayList;
        }


    public ArrayList<IncomeToWallet> getMonthlyIncomeFlow(int month, int year) throws ParseException {
        String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String monthString = monthArray[month];

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<IncomeToWallet> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM INCOME WHERE DATE like '%" + monthString + "%" + year + "'", null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            IncomeToWallet incomeFlow = new IncomeToWallet();
            incomeFlow.setAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));

            incomeFlow.setRecordId(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));

            incomeFlow.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
            incomeFlow.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));

            arrayList.add(incomeFlow);
            cursor.moveToNext();
        }
        db.close();
        return arrayList;
    }

//get wallet by id
        public Wallet getwalletbyid(int walletid)
        {
            SQLiteDatabase db = this.getReadableDatabase();

            String sqlQuery = "SELECT * FROM WALLET WHERE " + ID_TABLE + " = " + walletid;

            Cursor cursor = db.rawQuery(sqlQuery, null);

            Wallet wallet = new Wallet();


            if (cursor.moveToFirst()) {
                wallet.setBank(cursor.getString(cursor.getColumnIndex("BANK")));
                wallet.setWalletName(cursor.getString(cursor.getColumnIndex("WALLET_NAME")));
            }
            return wallet;

        }

//update wallet
        public boolean updatewallet(Wallet wallet){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("WALLET_NAME", wallet.getWalletName());
            contentValues.put("BANK",wallet.getBank());


            long status = db.update(WALLET, contentValues, ID_TABLE + " = " +wallet.getWalletId(), null);

            notifyDbChanged();
            if (status == -1) {
                return false;
            } else {
                return true;
            }


        }

        //update income
        public boolean updateincome(IncomeModel incomeModel)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("AMOUNT", incomeModel.getMoney());
            contentValues.put("DATE", String.valueOf(incomeModel.getDate()));
            contentValues.put("NOTE", incomeModel.getText());

            System.out.println("updateone"+incomeModel.getRecordID());

            long status = db.update(INCOME, contentValues, ID_TABLE + " = " + incomeModel.getRecordID(), null);

            if (status == -1) {
                return false;
            } else {
                return true;
            }
        }

        //get incomebyid
    public IncomeModel getincomeById ( int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM INCOME WHERE " + ID_TABLE + " = " + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);

       IncomeModel incomeModel= new IncomeModel();

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        if (cursor.moveToFirst()) {
            incomeModel.setMoney(cursor.getDouble(cursor.getColumnIndex("AMOUNT")));
            incomeModel.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));
            incomeModel.setText(cursor.getString(cursor.getColumnIndex("NOTE")));
        }

        return incomeModel;
    }

// get incomelistbyid
    public ArrayList<IncomeModel> getincomesListbyid (int walletid) throws ParseException {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ArrayList<IncomeModel> arrayList = new ArrayList<>();
        String sqlQuery ="SELECT * FROM INCOME WHERE WALLET_ID = " + walletid;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();


        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        while (cursor.isAfterLast() == false) {
            IncomeModel incomeModel = new IncomeModel();
            incomeModel.setRecordID(cursor.getInt(cursor.getColumnIndex("ID")));
            incomeModel.setText(cursor.getString(cursor.getColumnIndex("NOTE")));
            incomeModel.setMoney(cursor.getDouble(cursor.getColumnIndex("AMOUNT")));
            incomeModel.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));

            arrayList.add(incomeModel);
            cursor.moveToNext();
        }
        System.out.println("array size " + arrayList.size());
        return arrayList;
    }


    //Delete WALLET Record
    public boolean deleteWallet ( int id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = ID_TABLE + " = " + id;
        long status = db.delete("WALLET", whereClause, null);
        whereClause = "WALLET_ID" + " = " + id;
        status = db.delete("INCOME", whereClause, null);

        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }



    //Delete incomerecord
    public boolean deleteincomerecord ( int id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = ID_TABLE + " = " + id;
        long status = db.delete("INCOME", whereClause, null);

        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

//get amount
    public double getfullamount(){
        
        double TOTAL = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "SELECT sum(AMOUNT) FROM INCOME";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        return cursor.getDouble(0);

    }


    //get full amount by id
    public double getfullamountbyid(int walletid){
        double TOTAL = 0;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sqlQuery = "SELECT sum(AMOUNT) FROM INCOME WHERE WALLET_ID="+walletid;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        return cursor.getDouble(0);
    }

    //Add Budget
    public boolean addBudget(Budgetmodel budgetmodel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CAT_ID" , budgetmodel.getCat_ID());
        contentValues.put("AMOUNT", budgetmodel.getAmount());

        long status = db.insert(TABLE_BUDGET, null, contentValues);

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Get Budget
    public ArrayList<Budgetmodel> getBudgetArray(){
        ArrayList<Budgetmodel> budgetmodelArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Calendar cal = Calendar.getInstance();
        String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int month = cal.getTime().getMonth();
        int year = cal.getTime().getYear() + 1900;

        String sqlQuery = "SELECT * FROM BUDGET";
        Cursor cursor = db.rawQuery(sqlQuery, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Budgetmodel budgetmodell = new Budgetmodel();
            budgetmodell.setCat_ID(cursor.getInt(0));
            budgetmodell.setAmount(cursor.getDouble(1));

            double totalAmount = 0.0;
            String sqlQuery1 = "SELECT * FROM EXPENSES WHERE CATEGORY = "+ budgetmodell.getCat_ID() +" AND DATE like '%" + monthArray[month] + "%" + year + "'" ;
            Cursor cursor1 = db.rawQuery(sqlQuery1, null);
            cursor1.moveToFirst();

            while (cursor1.isAfterLast() == false) {
                totalAmount += cursor1.getDouble(cursor1.getColumnIndex("AMOUNT"));
                cursor1.moveToNext();
            }

            budgetmodell.setUsedAmount(totalAmount);

            budgetmodelArrayList.add(budgetmodell);
            cursor.moveToNext();
        }

        Log.d("msg", "Array List size " + budgetmodelArrayList.size());
        return budgetmodelArrayList;
    }

    public boolean updateBudget(Budgetmodel budgetmodel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("AMOUNT", budgetmodel.getAmount());

        Log.d("msg", String.valueOf(budgetmodel.getCat_ID()));

        long status = db.update("BUDGET", contentValues,  "CAT_ID = " + budgetmodel.getCat_ID(), null);

        if (status == -1) {
            return false;
        } else {
            return true;
        }


    }

    //viewGoal

    public ArrayList<FutureGoal> getfutureGoal() throws ParseException {

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ArrayList<FutureGoal> arrayList = new ArrayList<>();
       Cursor cursor = db.rawQuery("SELECT * FROM GOAL " , null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            FutureGoal futureGoal = new FutureGoal();
            futureGoal.setRecord_id(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
            futureGoal.setGoal(cursor.getString(cursor.getColumnIndex("GOAL_NAME")));
            futureGoal.setTotalAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));
            futureGoal.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));


            arrayList.add(futureGoal);
            cursor.moveToNext();
        }
        return arrayList;
    }

    //Update Goal

    public boolean updateGoal(FutureGoal futureGoal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("GOAL_NAME", futureGoal.getGoal());
        contentValues.put("AMOUNT", futureGoal.getTotalAmount());
        contentValues.put("DATE", String.valueOf(futureGoal.getDate()));

        long status = db.update("GOAL", contentValues,  ID_TABLE + " = " + futureGoal.getRecord_id(), null);

        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }
    }

    //get Goal

    public FutureGoal getGoalById(int Record_id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM GOAL WHERE " + ID_TABLE + " = " + Record_id;
        Cursor cursor = db.rawQuery(sqlQuery, null);

        FutureGoal futureGoal = new FutureGoal();

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        if (cursor.moveToFirst()) {
            futureGoal.setRecord_id(cursor.getInt(cursor.getColumnIndex(ID_TABLE)));
            futureGoal.setGoal(cursor.getString(cursor.getColumnIndex("GOAL_NAME")));
            futureGoal.setTotalAmount(cursor.getFloat(cursor.getColumnIndex("AMOUNT")));
            futureGoal.setDate(formatter.parse(cursor.getString(cursor.getColumnIndex("DATE"))));

        }

        return futureGoal;
    }


    //Delete Budget Record
    public boolean onDeletBtnBudget (final int cat_ID) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "CAT_ID"+ " = " + cat_ID;
        long status = db.delete("BUDGET", whereClause, null);
  notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean deleteGoalRecord(int Record_id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = ID_TABLE + " = " + Record_id;
        long status = db.delete("GOAL", whereClause, null);

        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Get Daily Budget By Id
    public Budgetmodel getDailyBudgetById ( int id) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        String sqlQuery = "SELECT * FROM BUDGET WHERE CAT_ID =" + id;
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Budgetmodel budgetmodel = new Budgetmodel();

        if (cursor.moveToFirst()) {
            budgetmodel.setCat_ID(cursor.getInt(cursor.getColumnIndex("CAT_ID")));
            budgetmodel.setAmount(cursor.getDouble(cursor.getColumnIndex("AMOUNT")));

        }
        db.close();
        return budgetmodel;
    }

    public boolean addAmount(int record_id,double amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("AMOUNT", amount);
        contentValues.put("GOAL_ID", record_id);

        long status = db.insert("GOAL_MONEY", null, contentValues);
        notifyDbChanged();

        if (status == -1) {
            return false;
        } else {
            return true;
        }

    }
    public double getGoalCurrenValue(int id) {
        SQLiteDatabase db =  databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GOAL_MONEY  WHERE GOAL_ID = " + id , null);

        cursor.moveToFirst();
        double total = 0.0;

        while (cursor.isAfterLast() == false) {
            total += cursor.getDouble(cursor.getColumnIndex("AMOUNT"));
            cursor.moveToNext();
        }
        return total;
    }

}
