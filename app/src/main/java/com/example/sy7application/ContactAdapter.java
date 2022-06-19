package com.example.sy7application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * 自定义适配器
 */
public class ContactAdapter extends ArrayAdapter<PhoneContact> {
    private int resourceId;
    public ContactAdapter(@NonNull Context context, int resource, List<PhoneContact> objects) {
        super(context, resource ,objects);
        resourceId =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取实例
        PhoneContact phoneContact =getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView name = view.findViewById(R.id.text_name);
        TextView phone = view.findViewById(R.id.text_number);
        name.setText(phoneContact.getName());
        phone.setText(phoneContact.getPhone());
        return view;
    }
}
