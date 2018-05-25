package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 7.05.2018.
 */

public class kuaforAdapter extends BaseAdapter {
    private List<Calisan> calisanlar;
    private Context context;
    LayoutInflater layoutInflater;
    private Activity activity;

    public kuaforAdapter(Activity activity,List<Calisan> calisanlar, Context context) {
        this.calisanlar = calisanlar;
        this.context = context;
        this.activity=activity;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return calisanlar.size();
    }

    @Override
    public Object getItem(int i) {
        return calisanlar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return calisanlar.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview = layoutInflater.inflate(R.layout.kuafor_adapter, null);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.kuafor_adapter, viewGroup, false);
        }

        TextView ad=(TextView) satirview.findViewById(R.id.text1);
        TextView soyad=(TextView) satirview.findViewById(R.id.text2);
        final Calisan calisan=calisanlar.get(i);
        ad.setText(calisan.ad);
        soyad.setText(calisan.soyad);
        return satirview;
    }
}