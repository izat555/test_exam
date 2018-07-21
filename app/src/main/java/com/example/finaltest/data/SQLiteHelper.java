package com.example.finaltest.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DB_CHARS";
    private static final int DB_VERSION = 5;

    private static final String TABLE_NAME = "TABLE_CHARS";
    private static final String ID = "_id";
    private static final String CHARACTER = "CHARACTER";

    private static final String CREATE_TABLE
            = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + ID + " INTEGER_PRIMARY_KEY, "
            + CHARACTER + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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

    public ArrayList<Character> getSavedChars() {
        SQLiteDatabase database = getReadableDatabase();
        ArrayList<Character> savedChars = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String strChar = cursor.getString(cursor.getColumnIndex(CHARACTER));
                savedChars.add(strChar.charAt(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return savedChars;
    }

    public long saveChars(ArrayList<Character> chars) {
        deleteChars();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long rowInserted = 0;

        for (int i = 0; i < chars.size(); i++) {
            contentValues.put(ID, i);
            contentValues.put(CHARACTER, String.valueOf(chars.get(i)));
            rowInserted = database.insert(TABLE_NAME, null, contentValues);
        }

        database.close();
        return rowInserted;
    }

    private int deleteChars() {
        SQLiteDatabase database = getWritableDatabase();
        int countDeleted = database.delete(TABLE_NAME, null, null);
        database.close();
        return countDeleted;
    }
}
