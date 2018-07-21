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
            = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER_PRIMARY_KEY, %s TEXT);"
            , TABLE_NAME, ID, CHARACTER);

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
                savedChars.add(cursor.getString(cursor.getColumnIndex(CHARACTER)).toCharArray()[0]);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return savedChars;
    }

    public void saveChars(ArrayList<Character> chars) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < chars.size(); i++) {
            contentValues.put(ID, i);
            contentValues.put(CHARACTER, String.valueOf(chars.get(i)));
            database.insert(TABLE_NAME, null, contentValues);
        }

        database.close();
    }
}
