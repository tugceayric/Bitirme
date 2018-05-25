package com.example.tugceayric.ekuaforum.Islemler;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.R;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

public class Home extends AppCompatActivity {

    private String Json_url_loginmusteri = url+"musteriler/login/";
    private String Json_url_logincalisan = url+"calisanlar/login/";
    private String Json_url_loginyonetici = url+"yonetici/login/";
    EditText musteri_eposta,musteri_sifre;
    EditText calisan_tc,calisan_sifre;
    EditText yonetici_username,yonetici_sifre;
    Button musteri_giris;
    Button calisan_giris;
    Button yonetici_giris;
    TextView txt_kayit;
    int id;
    int rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        id = sharedpreferences.getInt("id", 0);
        rol = sharedpreferences.getInt("rol", 0);
            if(id!=0) {
                if (rol == 1) {
                    Intent gec = new Intent(Home.this, Tab_Musteri.class);
                    startActivity(gec);
                } else if (rol == 2) {
                    Intent gec = new Intent(Home.this, Tab_Calisan.class);
                    startActivity(gec);
                } else if (rol == 3) {
                    Intent gec = new Intent(Home.this, Tab_Yonetici.class);
                    startActivity(gec);
                }
            }
            else
                    Toast.makeText(getApplicationContext(), "Tekrar giriş yapınız", Toast.LENGTH_LONG).show();

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


        musteri_eposta = (EditText) findViewById(R.id.musteri_eposta);
        musteri_sifre = (EditText) findViewById(R.id.musteri_sifre);

        calisan_tc = (EditText) findViewById(R.id.calisan_tc);
        calisan_sifre = (EditText) findViewById(R.id.calisan_sifre);

        yonetici_username = (EditText) findViewById(R.id.yonetici_kullanici_adi);
        yonetici_sifre = (EditText) findViewById(R.id.yonetici_sifre);

        musteri_giris = (Button) findViewById(R.id.musteri_giris);
        calisan_giris = (Button) findViewById(R.id.calisan_giris);
        yonetici_giris = (Button) findViewById(R.id.yonetici_giris);
        txt_kayit = (TextView) findViewById(R.id.txt_kayit);


        txt_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), KayitOl.class);
                startActivity(i);
            }
        });

    }


    class loginmusteri extends AsyncTask<String,Void,String> {
        int sonuc=0;
        @Override
        protected String doInBackground(String... strings) {

            try{
                String musmail=musteri_eposta.getText().toString();
                String mussifre=musteri_sifre.getText().toString();


                Response response=run(Json_url_loginmusteri+musmail+"/"+mussifre);
                String results=response.body().string();
                JSONObject object=new JSONObject(results);
                sonuc=response.code();
                id=object.getInt("id");

            }catch (Exception e){
                Log.e("yyyyyyyy",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(sonuc==200){
                Toast.makeText(getApplicationContext(),"Hoşgeldiniz",Toast.LENGTH_LONG).show();
                SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.putInt("id",id);
                editor.putInt("rol",1);
                editor.putString("mail",musteri_eposta.getText().toString());
                editor.commit();
                Intent gec = new Intent(Home.this, Tab_Musteri.class);
                startActivity(gec);
            }
            else
                Toast.makeText(getApplicationContext(),"giriş başarısız...",Toast.LENGTH_LONG).show();
        }
    }

    class logincalisan extends AsyncTask<String,Void,String> {
        int sonuc=0;

        @Override
        protected String doInBackground(String... strings) {
            try{
                String caltc=calisan_tc.getText().toString();
                String calsifre=calisan_sifre.getText().toString();


                Response response=run(Json_url_logincalisan+caltc+"/"+calsifre);
                String results=response.body().string();
                JSONObject object=new JSONObject(results);
                sonuc=response.code();
                id=object.getInt("id");

            }catch (Exception e){
                Log.e("yyyyyyyy",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(sonuc==200){
                Toast.makeText(getApplicationContext(),"Hoşgeldiniz",Toast.LENGTH_LONG).show();
                SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();


                    editor.clear();
                    editor.putInt("id", id);
                    editor.putInt("rol",2);
                    editor.putString("tc", calisan_tc.getText().toString());
                    editor.commit();
                    Intent gec = new Intent(Home.this, Tab_Calisan.class);
                    startActivity(gec);

            }
            else
                Toast.makeText(getApplicationContext(),"giriş başarısız...",Toast.LENGTH_LONG).show();
        }
    }

    class loginyonetici extends AsyncTask<String,Void,String> {
        int sonuc=0;
        @Override
        protected String doInBackground(String... strings) {

            try{
                String yonadi=yonetici_username.getText().toString();
                String yonsifre=yonetici_sifre.getText().toString();


                Response response=run(Json_url_loginyonetici+yonadi+"/"+yonsifre);
                String results=response.body().string();
                JSONObject object=new JSONObject(results);
                sonuc=response.code();
                id=object.getInt("id");

            }catch (Exception e){
                Log.e("yyyyyyyy",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(sonuc==200){
                Toast.makeText(getApplicationContext(),"Hoşgeldiniz",Toast.LENGTH_LONG).show();
                SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.putInt("id", id);
                    editor.putInt("rol",3);
                    editor.putString("ad", yonetici_username.getText().toString());
                    editor.commit();
                    Intent gec = new Intent(Home.this, Tab_Yonetici.class);
                    startActivity(gec);

            }
            else
                Toast.makeText(getApplicationContext(),"giriş başarısız...",Toast.LENGTH_LONG).show();
        }
    }

    public Response run (String url) throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Response response=client.newCall(request).execute();
        return response;
    }


    public void musteri(View view)
    {
        new loginmusteri().execute();
    }
    public void calisan(View view)
    {
        new logincalisan().execute();
    }
    public void yonetici(View view){ new loginyonetici().execute(); }




    }



