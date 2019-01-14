package com.example.c13.m;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mess_meal";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MESS_MEAL = "meal_info";
    public static final String TABLE_BAZAR_AND_EXTRA = "bazar_and_extra";

    public static final String COL_ID = "id";
    public static final String MESS_MEMBER_NAME = "mess_member_name";
    public static final String COL_DEPOSIT = "deposit";
    public static final String COL_MEAL = "meal";

    public static final String COL_TOTAL_BAZAR = "total_bazar";
    public static final String COL_TOTAL_EXTRA = "total_extra";





    private static final String CREATE_MESS_MEAL_TABLE = " CREATE TABLE " + TABLE_MESS_MEAL +
            "( " + COL_ID + " INTEGER PRIMARY KEY," + MESS_MEMBER_NAME + " TEXT, " + COL_DEPOSIT + " TEXT, " + COL_MEAL + " TEXT )";


    private static final String CREATE_MEAL_BAZAR_AND_EXTRA_TABLE = " CREATE TABLE " + TABLE_BAZAR_AND_EXTRA +
            "( " + COL_ID + " INTEGER PRIMARY KEY," + COL_TOTAL_BAZAR + " TEXT, " + COL_TOTAL_EXTRA + " TEXT )";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MESS_MEAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEAL_BAZAR_AND_EXTRA_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }


}
