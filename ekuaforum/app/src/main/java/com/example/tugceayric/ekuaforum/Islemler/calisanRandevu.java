package com.example.tugceayric.ekuaforum.Islemler;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.tugceayric.ekuaforum.CustomAdapter.calisanRandevusu;
import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
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

/**
 * Created by Tugce Ayric on 15.05.2018.
 */

public class calisanRandevu extends AppCompatActivity {
    private String Json_url_randevular = url+"calisanlar/cek/";
    SharedPreferences sharedpreferences;
    int calisanid;
    List<Randevu> randevular;
    calisanRandevusu adapter;
    ListView listview;
 Calisan calisan;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calisan_randevu);

        listview = (ListView) findViewById(R.id.listv1);

        sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        calisanid = sharedpreferences.getInt("id", 0);
        new listele().execute();


    }
    public class listele extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

                try {
                    String results = run(Json_url_randevular+calisanid);
                    JSONObject object = new JSONObject(results);
                    Gson gson = new Gson();
                    calisan = new Calisan();
                    calisan = gson.fromJson(String.valueOf(object), Calisan.class);

                } catch (Exception e) {
                    Log.e("yyyyyyyy",e.toString());

                }
                return null;
            }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            randevular=new ArrayList<>();
            randevular=calisan.randevuListe;
            adapter=new calisanRandevusu(calisanRandevu.this,randevular,getApplicationContext());
            listview.setAdapter(adapter);
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
