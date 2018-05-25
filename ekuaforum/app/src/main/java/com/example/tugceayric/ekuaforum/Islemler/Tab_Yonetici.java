package com.example.tugceayric.ekuaforum.Islemler;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.yoneticiRandevu;
import com.example.tugceayric.ekuaforum.Kaynak.Randevu;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

public class Tab_Yonetici extends AppCompatActivity {
    private String Json_url_allrandevu = url + "randevular/all";
    SharedPreferences sharedpreferences;
    int yoneticiid;
    List<Randevu> randevular;
    ListView lv;
    yoneticiRandevu adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_yonetici);

        final TabHost tabh = (TabHost) findViewById(R.id.tabhost);
        tabh.setup();

        TabHost.TabSpec tab1 = tabh.newTabSpec("tab menü 1. seçenek");
        TabHost.TabSpec tab2 = tabh.newTabSpec("tab menü 2. seçenek");
        TabHost.TabSpec tab3 = tabh.newTabSpec("tab menü 3. seçenek");


        tab3.setIndicator("RANDEVULAR");
        tab3.setContent(R.id.tab_randevu);

        tab2.setIndicator("STOK");
        tab2.setContent(R.id.tab_stok);

        tab1.setIndicator("ÇALIŞAN");
        tab1.setContent(R.id.tab_calisan);

        tabh.addTab(tab1);
        tabh.addTab(tab2);
        tabh.addTab(tab3);

        sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        yoneticiid = sharedpreferences.getInt("id", 0);
        String yoneticiadi = sharedpreferences.getString("adi", "k");
        if (yoneticiid == 0) {
            Intent gec = new Intent(Tab_Yonetici.this, Home.class);
            startActivity(gec);
        } else {

            Button btn7 = findViewById(R.id.cikis);
            Button btn6 = findViewById(R.id.btn_giris6);
            Button btn5 = findViewById(R.id.btn_giris5);
            Button btn4 = findViewById(R.id.btn_giris4);
            Button btn3 = findViewById(R.id.btn_giris9);
            Button btn2 = findViewById(R.id.btn_giris8);
            Button btn = findViewById(R.id.btn_giris7);
            lv = findViewById(R.id.listviewim);
            new listele().execute();

            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    Intent anasayfa = new Intent(Tab_Yonetici.this, Home.class);
                    startActivity(anasayfa);
                }
            });
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent malzemesil = new Intent(getApplicationContext(), malzemeSil.class);
                    startActivity(malzemesil);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent calisansil = new Intent(getApplicationContext(), calisanSil.class);
                    startActivity(calisansil);
                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent malzemeekle = new Intent(getApplicationContext(), malzemeEkle.class);
                    startActivity(malzemeekle);
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent stoklistele = new Intent(getApplicationContext(), stokListele.class);
                    startActivity(stoklistele);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ekle = new Intent(getApplicationContext(), calisanEkle.class);
                    startActivity(ekle);
                }
            });
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent calisanlistele = new Intent(getApplicationContext(), calisanListele.class);
                    startActivity(calisanlistele);

                }
            });


        }
    }

        public class listele extends AsyncTask {

            @Override
            protected Object doInBackground(Object[] objects) {

                try {
                    String results = run(Json_url_allrandevu);
                    JSONArray object = new JSONArray(results);
                    Gson gson = new Gson();
                    randevular = new ArrayList<Randevu>();
                    randevular = Arrays.asList(gson.fromJson(String.valueOf(object), Randevu[].class));

                } catch (Exception e) {
                    Log.e("yyyyyyyy",e.toString());

                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                adapter=new yoneticiRandevu(Tab_Yonetici.this,randevular,getApplicationContext());
                lv.setAdapter(adapter);
            }
        }

    public String run (String url) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Response response=client.newCall(request).execute();
        String results=response.body().string();
        return results;
    }
}
