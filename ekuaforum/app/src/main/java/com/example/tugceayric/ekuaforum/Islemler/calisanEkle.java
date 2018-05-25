package com.example.tugceayric.ekuaforum.Islemler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.cokluUzmanlikAdapter;
import com.example.tugceayric.ekuaforum.CustomAdapter.meslekAdapter;
import com.example.tugceayric.ekuaforum.CustomAdapter.uzmanlikAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.CalisanDetay;
import com.example.tugceayric.ekuaforum.Kaynak.Meslek;
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
 * Created by Tugce Ayric on 26.03.2018.
 */

public class calisanEkle extends AppCompatActivity {
    private String Json_url_addcalisan = url+"calisanlar/add/";
    private String Json_url_allmeslekler = url+"meslekler/all/";
    private String Json_url_alluzmanliklar = url+"cdetaylar";


    EditText ad;
    EditText soyad;
    EditText sifre;
    EditText adres;
    EditText tc;
    EditText telefon;
    Spinner meslek;
    Spinner uzmanlik;
    meslekAdapter meslekAdapter;
    uzmanlikAdapter uzmanlikAdapter;
    cokluUzmanlikAdapter cokluUzmanlikAdapter;
    ListView lv;
    Button btn;

    List<Meslek> meslekler;
    List<CalisanDetay> uzmanliklar;
    List<CalisanDetay> uzmanliklarim;
    Calisan calisan;
    CalisanDetay calisandetayigetir;
    int uzmanlikId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calisan_ekle);

        ad = (EditText) findViewById(R.id.editText);
        soyad = (EditText) findViewById(R.id.editText2);
        sifre = (EditText) findViewById(R.id.editText3);
        adres = (EditText) findViewById(R.id.editText4);
        tc = (EditText) findViewById(R.id.editText5);
        telefon = (EditText) findViewById(R.id.editText6);
        meslek=(Spinner)findViewById(R.id.spinner);
        uzmanlik=(Spinner)findViewById(R.id.spinner2);
        btn=(Button)findViewById(R.id.buttonn);
        lv=(ListView)findViewById(R.id.lv9);
        meslekler=new ArrayList<>();
        uzmanliklar=new ArrayList<>();
        uzmanliklarim=new ArrayList<>();



        new calisandetaygetir().execute();

        meslek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                uzmanliklarim=meslekler.get(i).cdetay;

                uzmanlikAdapter = new uzmanlikAdapter(uzmanliklarim, getApplicationContext());
                uzmanlik.setAdapter(uzmanlikAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uzmanlik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                uzmanlikId=uzmanliklarim.get(i).id;
                calisandetayigetir=uzmanliklarim.get(i);
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
                String aaa = "" + uzmanliklar.get(0).id;
                for (int i = 1; i < uzmanliklar.size(); i++) {
                    aaa = aaa + "," + uzmanliklar.get(i).id;
                }
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_addcalisan +
                                ad.getText().toString() + "/" +
                                soyad.getText().toString() + "/" +
                                adres.getText().toString() + "/" +
                                sifre.getText().toString() + "/" +
                                tc.getText().toString() + "/" +
                                telefon.getText().toString()+"/"+
                                aaa)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONObject object = new JSONObject(results);
                Gson gson = new Gson();
                calisan = gson.fromJson(String.valueOf(object), Calisan.class);
                sonuc = response.code();

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(sonuc==200) {
                uzmanlik.setAdapter(uzmanlikAdapter);
                meslek.setAdapter(meslekAdapter);
                ad.setText("");
                soyad.setText("");
                sifre.setText("");
                adres.setText("");
                tc.setText("");
                telefon.setText("");

                uzmanliklar=new ArrayList<>();
                cokluUzmanlikAdapter = new cokluUzmanlikAdapter(calisanEkle.this,uzmanliklar, getApplicationContext());
                cokluUzmanlikAdapter.notifyDataSetChanged();
                lv.setAdapter(cokluUzmanlikAdapter);
                Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getApplicationContext(),"ekleme başarısız",Toast.LENGTH_LONG).show();
        }
    }

    public class calisandetaygetir extends AsyncTask<String, Void, String> {
        String sonuc = "giriş işlemi başarısız lütfen daha sonra tekrar deneyin!!";
        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_allmeslekler)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                meslekler = Arrays.asList(gson.fromJson(String.valueOf(object), Meslek[].class));

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            meslekAdapter = new meslekAdapter(meslekler, getApplicationContext());
            meslek.setAdapter(meslekAdapter);

        }
    }

    public void olustur(View view) {
        new calisanolustur().execute();
    }

    public void uzmanlikEkle(View view)
    {
        btn.setEnabled(true);
        uzmanliklar.add(calisandetayigetir);
        cokluUzmanlikAdapter = new cokluUzmanlikAdapter(calisanEkle.this,uzmanliklar, getApplicationContext());
        cokluUzmanlikAdapter.notifyDataSetChanged();
        lv.setAdapter(cokluUzmanlikAdapter);
    }
}
