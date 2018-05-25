package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.Islem;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 8.05.2018.
 */

public class cokluAdapter extends BaseAdapter {
    private List<Islem> islemler;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private Boolean goster;

    public cokluAdapter(Activity activity, List<Islem> islemler,Context context){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.islemler=islemler;
        this.context=context;
        this.activity=activity;
        this.goster=goster;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.coklu_islem,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.coklu_islem,viewGroup,false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.textVieww2);
        TextView sayi=(TextView) satirview.findViewById(R.id.textVieww);

        final Islem islem=islemler.get(i);
        sayi.setText(""+(i+1));
        name.setText(islem.ad);
        return satirview;

    }
}



