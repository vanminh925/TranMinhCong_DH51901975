package com.example.tranminhcong_dh51901975;

import android.widget.BaseAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class ListSinhVienAdapter extends BaseAdapter {
    List<Student> list;
    Context context;
    int layout;

    public ListSinhVienAdapter(List<Student> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvDTB = convertView.findViewById(R.id.tvDTB);
        TextView tvTen = convertView.findViewById(R.id.tvTen);

        tvDTB.setText("Mã: "+list.get(i).getDtb());
        tvTen.setText("Tên: "+list.get(i).getTen());



        return convertView;
    }
}
