package com.example.sandali.vast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHandler extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "LoginDetails.db";

    static final String TABLE_NAME = "LoginStatus";

    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_STATUS = "status";

    String CREATE_TABLE = String.format("CREATE TABLE %s(%s PRIMARY KEY, %s )",
            TABLE_NAME, COLUMN_EMAIL, COLUMN_STATUS);

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public void addAccount(AccountModel account){

        SQLiteDatabase writeDatabase = SQLiteHandler.this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, account.getEmail().toString());
        values.put(COLUMN_STATUS, Integer.parseInt(String.valueOf(account.getStatus())));

        writeDatabase.insert(TABLE_NAME, null, values);
        writeDatabase.close();

    }

    //getting if there's already logged in account
    public AccountModel getAccount(){

        SQLiteDatabase readDatabase = SQLiteHandler.this.getReadableDatabase();
        Cursor cursor = readDatabase.query(TABLE_NAME, new String[] {COLUMN_EMAIL, COLUMN_STATUS}, COLUMN_STATUS +
                "= 1" , null, null, null, null);


        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            AccountModel loggedIn = new AccountModel(cursor.getString(0), Integer.parseInt(cursor.getString(1)));
            return loggedIn;
        }else {
            return null;
        }



    }

    public AccountModel getAccount2(){

        SQLiteDatabase readDatabase = SQLiteHandler.this.getReadableDatabase();
        Cursor cursor = readDatabase.query(TABLE_NAME, new String[] {COLUMN_EMAIL, COLUMN_STATUS}, COLUMN_EMAIL +
                "= ?" , new String[]{MainActivity.loggedEmail}, null, null, null);



        if ((cursor != null) && (cursor.getCount() > 0)) {
            cursor.moveToFirst();
            AccountModel loggedIn2 = new AccountModel(cursor.getString(0), Integer.parseInt(cursor.getString(1)));

            return loggedIn2;
        }else {
            return null;
        }



    }

    public void logOutAccount(AccountModel logOutAccount, String email) {

        SQLiteDatabase updateDatabase = SQLiteHandler.this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS,logOutAccount.getStatus());

        updateDatabase.update(TABLE_NAME, values, COLUMN_EMAIL + " = ?", new String[]{email});

        updateDatabase.close();

    }

    public void logInAccount(AccountModel logInAccount, String email) {

        SQLiteDatabase updateDatabase2 = SQLiteHandler.this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS,logInAccount.getStatus());

        updateDatabase2.update(TABLE_NAME, values, COLUMN_EMAIL + " = ?", new String[]{email});

        updateDatabase2.close();

    }

}
