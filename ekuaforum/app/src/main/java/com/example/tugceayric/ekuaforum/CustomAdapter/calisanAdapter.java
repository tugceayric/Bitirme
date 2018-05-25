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
 * Created by Tugce Ayric on 25.03.2018.
 */

public class calisanAdapter extends BaseAdapter {

    private List<Calisan> calisanlar;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private Boolean goster;


    public calisanAdapter(Activity activity, List<Calisan> calisanlar,Context context){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.calisanlar=calisanlar;
        this.context=context;
        this.activity=activity;
        this.goster=goster;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.calisan_custom_adapter,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.calisan_custom_adapter,viewGroup,false);
        }

        TextView name=(TextView) satirview.findViewById(R.id.textView2);
        TextView surname=(TextView) satirview.findViewById(R.id.textView3);
        TextView sayi=(TextView) satirview.findViewById(R.id.textView4);

        final Calisan calisan=calisanlar.get(i);
        sayi.setText(""+(i+1));
        name.setText(calisan.ad);
        surname.setText(calisan.soyad);
        return satirview;

    }
}
