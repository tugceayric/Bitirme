package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.Randevu;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 17.05.2018.
 */

public class yoneticiRandevu extends BaseAdapter {
    private List<Randevu> randevular;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private Boolean goster;

    public yoneticiRandevu(Activity activity, List<Randevu> randevular,Context context){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.randevular=randevular;
        this.context=context;
        this.activity=activity;
        this.goster=goster;
    }


    @Override
    public int getCount() {
        return randevular.size();
    }

    @Override
    public Object getItem(int i) {
        return randevular.get(i);
    }

    @Override
    public long getItemId(int i) {
        return randevular.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.yonetici_custom_randevu,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.yonetici_custom_randevu,viewGroup,false);
        }

        TextView tarih=(TextView) satirview.findViewById(R.id.textim);
        TextView musteriad=(TextView) satirview.findViewById(R.id.textim5);
        TextView sayi=(TextView) satirview.findViewById(R.id.textim6);
        TextView musterisoyad=(TextView) satirview.findViewById(R.id.textim7);

        final Randevu randevu=randevular.get(i);

        sayi.setText(""+(i+1));
        tarih.setText(""+(randevu.tarih));
        musteriad.setText(""+(randevu.musteri.ad));
        musterisoyad.setText(""+(randevu.musteri.soyad));

        return satirview;
    }
}

