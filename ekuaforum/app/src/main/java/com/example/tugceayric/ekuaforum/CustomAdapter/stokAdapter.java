package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.Malzeme;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 28.03.2018.
 */

public class stokAdapter extends BaseAdapter {
    private List<Malzeme> malzemeler;
    private LayoutInflater layoutInflater;
    private Context context;
    private Activity activity;
    private Boolean goster;

    public stokAdapter(Activity activity, List<Malzeme> malzemeler,Context context){
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.malzemeler=malzemeler;
        this.context=context;
        this.activity=activity;
        this.goster=goster;
    }


    @Override
    public int getCount() {
        return malzemeler.size();
    }

    @Override
    public Object getItem(int i) {
        return malzemeler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return malzemeler.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View satirview;
        satirview=layoutInflater.inflate(R.layout.stok_custom_adapter,null);
        if(view==null){
            view=layoutInflater.inflate(R.layout.stok_custom_adapter,viewGroup,false);
        }

        TextView ad=(TextView) satirview.findViewById(R.id.textView);
        TextView adet=(TextView) satirview.findViewById(R.id.textView5);
        TextView sayi=(TextView) satirview.findViewById(R.id.textView6);

        final Malzeme malzeme=malzemeler.get(i);
        sayi.setText(""+(i+1));
        ad.setText(malzeme.ad);
        adet.setText(""+(malzeme.malzemeSayisi));
        return satirview;

    }
}
