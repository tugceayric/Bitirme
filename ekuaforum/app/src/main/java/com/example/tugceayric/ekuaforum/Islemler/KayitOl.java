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
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.Kaynak.Musteri;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;


public class KayitOl extends AppCompatActivity {

    private String Json_url_addcalisan = url+"musteriler/add/";
    EditText ad;
    EditText soyad;
    EditText telefon;
    EditText sifre;
    EditText mail;
    Musteri musteri;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitol);
        ad = (EditText) findViewById(R.id.editText);
        soyad = (EditText) findViewById(R.id.editText2);
        telefon = (EditText) findViewById(R.id.editText3);
        sifre = (EditText) findViewById(R.id.editText4);
        mail = (EditText) findViewById(R.id.editText5);

    }
    public class kullaniciekle extends AsyncTask<String, Void, String> {

        int sonuc = 0;

        @Override
        protected String doInBackground(String... strings) {

                try{
                    String adi=ad.getText().toString();
                    String soyadi=soyad.getText().toString();
                    String telefonu=telefon.getText().toString();
                    String sifresi=sifre.getText().toString();
                    String maili=mail.getText().toString();
                    JsonObject json = new JsonObject();
                    json.addProperty("ad",adi);
                    json.addProperty("soyad",soyadi);
                    json.addProperty("telefon",telefonu);
                    json.addProperty("sifre",sifresi);
                    json.addProperty("mail",maili);

                    String jsonString = json.toString();

                    OkHttpClient client=new OkHttpClient();

                    RequestBody body = RequestBody.create(JSON, jsonString);

                    Request request=new Request.Builder()
                            .url(Json_url_addcalisan)
                            .post(body)
                            .build();
                    Response response=client.newCall(request).execute();
                    String results=response.body().string();

                    JSONObject object=new JSONObject(results);

                    Gson gson = new Gson();
                    musteri=gson.fromJson(String.valueOf(object),Musteri.class);

                    sonuc=response.code();

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.putInt("id",musteri.id);
            editor.putString("ad",musteri.ad);
            editor.putString("soyad",musteri.soyad);
            editor.putLong("telefon",musteri.telefon);
            editor.putString("sifre",musteri.sifre);
            editor.putString("mail ",musteri.mail);

            editor.commit();
            Intent kaydett = new Intent(KayitOl.this, Home.class);
            startActivity(kaydett);
        }
    }
    public void kullanicieklee(View view)
    {
        new kullaniciekle().execute();
        Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
    }

}
