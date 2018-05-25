package com.example.tugceayric.ekuaforum.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tugceayric.ekuaforum.Kaynak.Meslek;
import com.example.tugceayric.ekuaforum.R;

import java.util.List;

/**
 * Created by Tugce Ayric on 29.03.2018.
 */

public class meslekAdapter  extends BaseAdapter {
    private List<Meslek> meslekler;
    private Context context;
    LayoutInflater layoutInflater;

    public meslekAdapter(List<Meslek> meslekler, Context context) {
        this.meslekler = meslekler;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return meslekler.size();
    }

    @Override
    public Object getItem(int i) {
        return meslekler.get(i);
    }

    @Override
    public long getItemId(int i) {
        return meslekler.get(i).id;
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
        final Meslek meslek=meslekler.get(i);
        name.setText(meslek.ad);
        return satirview;
    }
}
