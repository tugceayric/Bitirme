package com.example.tugceayric.ekuaforum.Islemler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.tugceayric.ekuaforum.CustomAdapter.calisanAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.Randevu;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 21.03.2018.
 */

public class calisanListele extends AppCompatActivity {
    private String Json_url_getcalisanlar = url+"calisanlar/all";
    OkHttpClient client;
    ListView listView;
    String sonuc;
    List<Calisan> calisanlar ;
    calisanAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calisan_listele);

        listView = (ListView) findViewById(R.id.lv1);
        client = new OkHttpClient();
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(Json_url_getcalisanlar);

    }


    public class OkHttpHandler extends AsyncTask {
        String sonuc = "giriş işlemi başarısız lütfen daha sonra tekrar deneyin!!";

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                String results = run(Json_url_getcalisanlar);
                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                calisanlar = new ArrayList<Calisan>();
                calisanlar = Arrays.asList(gson.fromJson(String.valueOf(object), Calisan[].class));
            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
                sonuc = ("sonuç başarısız daha sonra tekrar deneyin");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            adapter = new calisanAdapter(calisanListele.this, calisanlar, getApplicationContext());
            listView.setAdapter(adapter);
        }

    }

    public String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String results = response.body().string();
        return results;
    }
}
