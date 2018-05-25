package com.example.tugceayric.ekuaforum.Islemler;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tugceayric.ekuaforum.Kaynak.Musteri;
import com.example.tugceayric.ekuaforum.R;

import java.util.ArrayList;
import java.util.List;


public class Tab_Calisan extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    int calisanid;
    List<Musteri> musteriler;

    @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tab_calisan);

            sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
            calisanid = sharedpreferences.getInt("id", 0);
            String calisan_tc = sharedpreferences.getString("tc", "k");
            if (calisanid == 0) {
                Intent gec = new Intent(Tab_Calisan.this, Home.class);
                startActivity(gec);
            }
        else {
            Button btn=findViewById(R.id.btn_giris);
            Button btn2=findViewById(R.id.calisan_randevu);
            Button btn3=findViewById(R.id.calisan_cikis);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gec = new Intent(Tab_Calisan.this, calisanStok.class);
                    startActivity(gec);
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent anasayfa=new Intent(Tab_Calisan.this,Home.class);
                    startActivity(anasayfa);
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent randevum=new Intent(Tab_Calisan.this,calisanRandevu.class);
                    startActivity(randevum);
                }
            });


        }
    }
}
