package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.CalisanDetay;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 9.05.2018.
 */

public class cokluUzmanlikAdapter extends BaseAdapter {
    private List<CalisanDetay> detaylar;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private Boolean goster;

    public cokluUzmanlikAdapter(Activity activity, List<CalisanDetay> detaylar,Context context){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.detaylar=detaylar;
        this.context=context;
        this.activity=activity;
        this.goster=goster;
    }

    @Override
    public int getCount() {
        return detaylar.size();
    }

    @Override
    public Object getItem(int i) {
        return detaylar.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.coklu_uzmanlik,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.coklu_uzmanlik,viewGroup,false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.textv2);
        TextView sayi=(TextView) satirview.findViewById(R.id.textv);

        final CalisanDetay cdetay=detaylar.get(i);
        sayi.setText(""+(i+1));
        name.setText(cdetay.uzmanlik);
        return satirview;

    }
}