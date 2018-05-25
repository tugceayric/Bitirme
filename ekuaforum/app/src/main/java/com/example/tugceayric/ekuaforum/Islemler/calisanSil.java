package com.example.tugceayric.ekuaforum.Islemler;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.calisanAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import com.example.tugceayric.ekuaforum.Kaynak.Calisan.*;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 31.03.2018.
 */

public class calisanSil extends AppCompatActivity {
    private String Json_url_deletecalisan=url+"calisanlar/delete";
    private String Json_url_getir=url+"calisanlar/all/";
    private String JSON_URL_duzenle=url+"calisanlar/update";
    OkHttpClient client;
    ListView listView;
    EditText ad;
    EditText soyad;
    EditText sifre;
    EditText adres;
    EditText tc;
    EditText telefon;

    String sonuc;
    Button duzenle;
    Button sil;
    Calisan calisan;
    List<Calisan> calisanlar ;
    List<Calisan> calisanlarim ;
    int islenenid;
    DialogInterface dialog;
    DialogInterface dialog3;
    Dialog dialog2;
    calisanAdapter adapter;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calisan_sil);

        listView = (ListView) findViewById(R.id.listview2);
        client = new OkHttpClient();

        dialog2=new Dialog(calisanSil.this);
        dialog2.setTitle("Kişiyi düzenle");
        dialog2.setContentView(R.layout.custom_dialog);
        duzenle=(Button)dialog2.findViewById(R.id.duzenle);
        sil=(Button)dialog2.findViewById(R.id.sil);
        ad=(EditText)dialog2.findViewById(R.id.ad);
        soyad=(EditText)dialog2.findViewById(R.id.soyad);
        sifre=(EditText)dialog2.findViewById(R.id.sifre);
        adres=(EditText)dialog2.findViewById(R.id.adres);
        tc=(EditText)dialog2.findViewById(R.id.tc);
        telefon=(EditText)dialog2.findViewById(R.id.telefon);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Calisan c=(Calisan) listView.getItemAtPosition(i);
                islenenid=c.id;
                dialog2.show();
            }
        });

        new getir().execute();
    }
    public class getir extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                String results = run(Json_url_getir);
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
            adapter = new calisanAdapter(calisanSil.this, calisanlar, getApplicationContext());
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


    public class calisansil extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_deletecalisan + "/" +
                                islenenid)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                calisanlarim = new ArrayList<Calisan>();
                Gson gson = new Gson();
                calisanlarim = Arrays.asList(gson.fromJson(String.valueOf(object), Calisan[].class));
            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(getApplicationContext(),"Çalisan silindi",Toast.LENGTH_SHORT).show();
            Intent gec = new Intent(getApplicationContext(), Tab_Yonetici.class);
            startActivity(gec);
        }
    }
    class calisanduzenle extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try{

                String adi=ad.getText().toString();
                String soyadi=soyad.getText().toString();
                String sifresi=sifre.getText().toString();
                String adresi=adres.getText().toString();
                String  tci=tc.getText().toString();
                String telefonu=telefon.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("ad",adi);
                json.addProperty("soyad",soyadi);
                json.addProperty("sifre",sifresi);
                json.addProperty("adres",adresi);
                json.addProperty("tc",tci);
                json.addProperty("telefon",telefonu);
                String jsonString = json.toString();

                OkHttpClient client=new OkHttpClient();

                RequestBody body = RequestBody.create(JSON, jsonString);

                Request request=new Request.Builder()
                        .url(JSON_URL_duzenle+"/"+
                        islenenid)
                        .post(body)
                        .build();
                Response response=client.newCall(request).execute();
                String results=response.body().string();

                JSONArray object=new JSONArray(results);
                Gson gson = new Gson();
                calisanlar = new ArrayList<Calisan>();

                calisanlar = Arrays.asList(gson.fromJson(String.valueOf(object), Calisan[].class));

            }catch (Exception e){
                Log.e("yyyyyyyy",e.toString());
            }
            return calisan;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            new getir().execute();
            dialog2.dismiss();
            Toast.makeText(getApplicationContext(),"Calisan düzenlendi", Toast.LENGTH_SHORT).show();
        }
    }

    public void sil(View view){
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("SIL!")
                .setMessage("Silmek istediginizden emin misiniz?");

        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog=dialogInterface;
                new calisansil().execute();
            }
        });
        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        Toast.makeText(getApplicationContext(),"İşlem tamam", Toast.LENGTH_SHORT).show();

    }
    public void duzenle(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("DÜZENLE!")
                .setMessage("Düzenleme Kaydedilsin mi?");

        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog3=dialogInterface;
                new calisanduzenle().execute();
            }
        });
        builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }

}


