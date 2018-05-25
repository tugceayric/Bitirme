package com.example.tugceayric.ekuaforum.Islemler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.tugceayric.ekuaforum.CustomAdapter.stokAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Malzeme;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 27.03.2018.
 */

public class stokListele extends AppCompatActivity {
    private String Json_url_getstok = url+"malzemeler/all";
    OkHttpClient client;
    ListView listView;
    String sonuc;
    List<Malzeme> malzemeler ;
    stokAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stok_listele);

        listView = (ListView) findViewById(R.id.lv1);
        client = new OkHttpClient();
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(Json_url_getstok);

    }

    public class OkHttpHandler extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                String results = run(Json_url_getstok);
                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                malzemeler = new ArrayList<Malzeme>();
                malzemeler = Arrays.asList(gson.fromJson(String.valueOf(object), Malzeme[].class));
            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
                sonuc = ("sonuç başarısız daha sonra tekrar deneyin");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            adapter = new stokAdapter(stokListele.this, malzemeler, getApplicationContext());
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
