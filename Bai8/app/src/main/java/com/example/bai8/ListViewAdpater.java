package com.example.bai8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdpater extends ArrayAdapter<Phone> {

    private Context context;
    private List<Phone> phones;
    private LayoutInflater inflater;


    public ListViewAdpater(@NonNull Context context, int resource, @NonNull List<Phone> phones) {
        super(context, resource, phones);
        this.context = context;
        this.phones = phones;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_san_pham, parent, false);
        Phone phone = phones.get(position);
        TextView tvTen = convertView.findViewById(R.id.tvTen);
        tvTen.setText(phone.getName());
        return convertView;
    }
}