package com.example.tugceayric.ekuaforum.Islemler;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tugceayric.ekuaforum.R;


public class Tab_Musteri extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    int musteriid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_musteri);
        sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        musteriid = sharedpreferences.getInt("id", 0);
        String mail = sharedpreferences.getString("mail", "k");
            if (musteriid == 0) {
                Intent gec = new Intent(Tab_Musteri.this, Home.class);
                startActivity(gec);
            }
            else {
                Button btn=findViewById(R.id.btn_giris2);
                Button btn2=findViewById(R.id.btn_giris11);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent gec=new Intent(Tab_Musteri.this,randevuOlustur.class);
                        startActivity(gec);
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent anasayfa=new Intent(Tab_Musteri.this,Home.class);
                        startActivity(anasayfa);
                    }
                });



            }
    }
}
