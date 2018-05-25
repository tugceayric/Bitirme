package com.example.tugceayric.post.model;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.example.tugceayric.post.R;

public class Tab_Yonetici extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_yonetici);
        Button btn=findViewById(R.id.btn_giris7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listele=new Intent(getApplicationContext(),calisanListele.class);
                startActivity(listele);

            }
        });

        final TabHost tabh = (TabHost) findViewById(R.id.tabhost);
        tabh.setup();

        TabHost.TabSpec tab1 = tabh.newTabSpec("tab menü 1. seçenek");
        TabHost.TabSpec tab2 = tabh.newTabSpec("tab menü 2. seçenek");
        TabHost.TabSpec tab3 = tabh.newTabSpec("tab menü 3. seçenek");


        tab1.setIndicator("RANDEVULAR");
        tab1.setContent(R.id.tab_randevu);

        tab2.setIndicator("STOK");
        tab2.setContent(R.id.tab_stok);

        tab3.setIndicator("ÇALIŞAN");
        tab3.setContent(R.id.tab_calisan);

        tabh.addTab(tab1);
        tabh.addTab(tab2);
        tabh.addTab(tab3);

    }
}
