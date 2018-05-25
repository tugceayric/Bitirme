package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Kategori;
import com.example.tugceayric.ekuaforum.Kaynak.Meslek;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 29.03.2018.
 */

public class kategoriAdapter extends BaseAdapter {
    private List<Kategori> kategoriler;
    private Context context;
    LayoutInflater layoutInflater;
    private Activity activity;

    public kategoriAdapter(Activity activity,List<Kategori> kategoriler, Context context) {
        this.kategoriler = kategoriler;
        this.context = context;
        this.activity=activity;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return kategoriler.size();
    }

    @Override
    public Object getItem(int i) {
        return kategoriler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return kategoriler.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview = layoutInflater.inflate(R.layout.malzeme_spinner, null);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.malzeme_spinner, viewGroup, false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.text1);
        final Kategori kategori=kategoriler.get(i);
        name.setText(kategori.ad);
        return satirview;
    }
}
