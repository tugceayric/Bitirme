package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.CalisanDetay;
import com.example.tugceayric.ekuaforum.Kaynak.Meslek;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 28.03.2018.
 */

public class uzmanlikAdapter extends BaseAdapter {
    private List<CalisanDetay> uzmanliklar;
    private Context context;
    LayoutInflater layoutInflater;

    public uzmanlikAdapter(List<CalisanDetay> uzmanliklar, Context context) {
        this.uzmanliklar = uzmanliklar;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return uzmanliklar.size();
    }

    @Override
    public Object getItem(int i) {
        return uzmanliklar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return uzmanliklar.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.spinner_adapter,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.spinner_adapter,viewGroup,false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.text2);
        final CalisanDetay uzmanlik=uzmanliklar.get(i);
        name.setText(uzmanlik.uzmanlik);
        return satirview;
    }

}
