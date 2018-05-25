package com.example.tugceayric.ekuaforum.Islemler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.kategoriAdapter;
import com.example.tugceayric.ekuaforum.CustomAdapter.malzemeDetayAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Kategori;
import com.example.tugceayric.ekuaforum.Kaynak.Malzeme;
import com.example.tugceayric.ekuaforum.Kaynak.MalzemeDetay;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 29.03.2018.
 */

public class malzemeEkle extends AppCompatActivity {
    EditText ad;
    EditText malzemeSayisi;
    Spinner kategori;
    Spinner mdetay;
    int malzemeDetayId;
    int kategoriId;
    kategoriAdapter kategoriAdapter;
    malzemeDetayAdapter malzemeDetayAdapter;

    private String Json_url_addmalzeme = url+"malzemeler/add/";
    private String Json_url_allkategori = url+"kategoriler/all/";
    private String Json_url_allmalzemedetay = url+"mdetaylar/all/";


    List<Kategori> kategoriler;
    List<MalzemeDetay> mdetaylar;
    Malzeme malzeme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malzeme_ekle);

        ad = (EditText) findViewById(R.id.editText7);
        malzemeSayisi = (EditText) findViewById(R.id.editText8);
        kategori = (Spinner) findViewById(R.id.spinner3);
        mdetay = (Spinner) findViewById(R.id.spinner4);

        kategoriler = new ArrayList<>();
        mdetaylar = new ArrayList<>();

        new kategorigetir().execute();
        new malzemedetaygetir().execute();


        kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                kategoriId = kategoriler.get(i).id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mdetay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                malzemeDetayId = mdetaylar.get(i).id;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public class calisanolustur extends AsyncTask<String, Void, String> {

        int sonuc = 0;

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_addmalzeme +
                                ad.getText().toString() + "/" +
                                malzemeSayisi.getText().toString() + "/" +
                                kategoriId +"/"+
                                malzemeDetayId)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONObject object = new JSONObject(results);
                Gson gson = new Gson();
                malzeme = gson.fromJson(String.valueOf(object), Malzeme.class);
                sonuc = response.code();

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
                kategori.setAdapter(kategoriAdapter);
                mdetay.setAdapter( malzemeDetayAdapter);
                ad.setText("");
                malzemeSayisi.setText("");
                Toast.makeText(getApplicationContext(),"eklendi",Toast.LENGTH_LONG).show();

        }
    }

    public class kategorigetir extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_allkategori)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                kategoriler = Arrays.asList(gson.fromJson(String.valueOf(object), Kategori[].class));

            } catch (Exception e) {
                Log.e("zzzzzy", e.toString());
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            kategoriAdapter = new kategoriAdapter(malzemeEkle.this, kategoriler, getApplicationContext());
            kategori.setAdapter(kategoriAdapter);

        }
    }

    public class malzemedetaygetir extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_allmalzemedetay)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                mdetaylar = Arrays.asList(gson.fromJson(String.valueOf(object), MalzemeDetay[].class));

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            malzemeDetayAdapter = new malzemeDetayAdapter(mdetaylar, getApplicationContext());
            mdetay.setAdapter(malzemeDetayAdapter);

        }
    }

    public void olustur(View view) {
        new calisanolustur().execute();
    }
}

