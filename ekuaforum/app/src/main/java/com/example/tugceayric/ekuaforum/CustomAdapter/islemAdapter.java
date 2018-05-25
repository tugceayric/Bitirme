package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Islem;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 8.05.2018.
 */

public class islemAdapter extends BaseAdapter {
    private List<Islem> islemler;
    private Context context;
    LayoutInflater layoutInflater;
    private Activity activity;

    public islemAdapter(Activity activity,List<Islem> islemler, Context context) {
        this.islemler = islemler;
        this.context = context;
        this.activity=activity;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return islemler.size();
    }

    @Override
    public Object getItem(int i) {
        return islemler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return islemler.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview = layoutInflater.inflate(R.layout.islem_adapter, null);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.islem_adapter, viewGroup, false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.text1);
        final Islem islem=islemler.get(i);
        name.setText(islem.ad);
        return satirview;
    }
}