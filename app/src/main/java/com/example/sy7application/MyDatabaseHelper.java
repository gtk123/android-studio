package com.example.sy7application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACTS = "create table contacts ("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "phone text,"+
            "sex text)";

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
