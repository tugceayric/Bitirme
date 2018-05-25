package com.example.tugceayric.post.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugceayric.post.R;

public class Home extends AppCompatActivity {

    EditText musteri_eposta,musteri_sifre,calisan_tc,calisan_sifre,yonetici_username,yonetici_sifre;
    Button btn_giris;
    TextView txt_kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TabHost tabh = (TabHost) findViewById(R.id.tabhost);
        tabh.setup();

        TabHost.TabSpec tab1 = tabh.newTabSpec("tab menü 1. seçenek");
        TabHost.TabSpec tab2 = tabh.newTabSpec("tab menü 2. seçenek");
        TabHost.TabSpec tab3 = tabh.newTabSpec("tab menü 3. seçenek");


        tab1.setIndicator("MÜŞTERİ");
        tab1.setContent(R.id.tab_musteri);

        tab2.setIndicator("ÇALIŞAN");
        tab2.setContent(R.id.tab_calisan);

        tab3.setIndicator("YÖNETİCİ");
        tab3.setContent(R.id.tab_yonetici);

        tabh.addTab(tab1);
        tabh.addTab(tab2);
        tabh.addTab(tab3);

        init();


        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index=tabh.getCurrentTab();
                switch (index)
                {
                    case 0:
                        if(musteri_eposta.getText().toString().equals("m1") && musteri_sifre.getText().toString().equals("123"))
                        {
                            Intent i=new Intent(getApplicationContext(), com.example.tugceayric.post.model.Tab_Musteri.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Yanlış E-posta veya Şifre",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        if(calisan_tc.getText().toString().equals("tc") && calisan_sifre.getText().toString().equals("123"))
                        {
                            Intent i=new Intent(getApplicationContext(), com.example.tugceayric.post.model.Tab_Calisan.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Yanlış TC veya Şifre",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        if(yonetici_username.getText().toString().equals("admin") && yonetici_sifre.getText().toString().equals("123"))
                        {
                            Intent i=new Intent(getApplicationContext(), com.example.tugceayric.post.model.Tab_Yonetici.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Yanlış Kullanıcı Adı veya Şifre",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });


        txt_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), com.example.tugceayric.post.model.KayitOl.class);
                startActivity(i);
            }
        });

    }

    public void init()
    {
        musteri_eposta=(EditText)findViewById(R.id.musteri_eposta);
        musteri_sifre=(EditText)findViewById(R.id.musteri_sifre);

        calisan_tc=(EditText)findViewById(R.id.calisan_tc);
        calisan_sifre=(EditText)findViewById(R.id.calisan_sifre);

        yonetici_username=(EditText)findViewById(R.id.yonetici_kullanici_adi);
        yonetici_sifre=(EditText)findViewById(R.id.yonetici_sifre);

        btn_giris=(Button)findViewById(R.id.btn_giris);

        txt_kayit=(TextView)findViewById(R.id.txt_kayit);


    }
}
