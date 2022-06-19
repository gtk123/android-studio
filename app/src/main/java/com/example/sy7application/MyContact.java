package com.example.sy7application;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class MyContact extends AppCompatActivity {

    List<PhoneContact> contactList = new ArrayList<PhoneContact>();
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        //获得ListView布局
        ListView contactsView = findViewById(R.id.list_view1);
        adapter = new ContactAdapter(this, R.layout.contact_view , contactList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }
    }

    private void readContacts(){
        //获得内容提供者
        Cursor cursor = null;
        try {
            //查询联系人数据
            cursor = getContentResolver().
                    query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    PhoneContact phoneContact = new PhoneContact(name,number);
                    contactList.add(phoneContact);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }
}
