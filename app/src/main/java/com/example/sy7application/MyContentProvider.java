package com.example.sy7application;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class MyContentProvider extends ContentProvider {
    private static final int CONTACTS_DIR = 0;
    private static final int CONTACTS_ITEM = 1;
    private static final String AUTHORITY = "com.example.sy7application.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "contacts", CONTACTS_DIR);
        uriMatcher.addURI(AUTHORITY, "contacts/#", CONTACTS_ITEM);
    }
    public MyContentProvider() {
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
            case CONTACTS_ITEM:
                long newContactsId = db.insert("contacts",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/contacts/"+
                        newContactsId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDatabaseHelper(getContext(),"ContactStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                cursor = db.query("contacts",projection,selection,selectionArgs,null,
                        null,sortOrder);
            case CONTACTS_ITEM:
                String contacts_id = uri.getPathSegments().get(1);
                cursor = db.query("contacts",projection,"id=?",new String[]{contacts_id},
                        null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                return "vnd.android.cursor.dir/vdn.com.example.sy7application.provider.contacts";
            case CONTACTS_ITEM:
                return "vnd.android.cursor.item/vdn.com.example.sy7application.provider.contacts";
        }
        return null;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}