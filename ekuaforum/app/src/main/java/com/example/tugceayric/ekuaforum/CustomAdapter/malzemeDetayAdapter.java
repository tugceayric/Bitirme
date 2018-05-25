package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.CalisanDetay;
import com.example.tugceayric.ekuaforum.Kaynak.Malzeme;
import com.example.tugceayric.ekuaforum.Kaynak.MalzemeDetay;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 29.03.2018.
 */

public class malzemeDetayAdapter extends BaseAdapter {
    private List<MalzemeDetay> mdetaylar;
    private Context context;
    LayoutInflater layoutInflater;

    public malzemeDetayAdapter(List<MalzemeDetay> mdetaylar, Context context) {
        this.mdetaylar = mdetaylar;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mdetaylar.size();
    }

    @Override
    public Object getItem(int i) {
        return mdetaylar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mdetaylar.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview = layoutInflater.inflate(R.layout.malzeme_spinner, null);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.malzeme_spinner, viewGroup, false);
        }

        TextView detay = (TextView) satirview.findViewById(R.id.text1);

        final MalzemeDetay mdetay = mdetaylar.get(i);
        detay.setText(mdetay.renk);
        return satirview;
    }
}