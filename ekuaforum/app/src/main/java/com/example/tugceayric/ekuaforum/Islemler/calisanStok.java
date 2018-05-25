package com.example.tugceayric.ekuaforum.Islemler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.stokAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Malzeme;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 4.05.2018.
 */

public class calisanStok extends AppCompatActivity {
    private String Json_url_deletemalzeme=url+"malzemeler/delete";
    private String Json_url_getir=url+"malzemeler/all";
    private String Json_url_duzenle=url+"malzemeler/update";

    OkHttpClient client;
    ListView listView;
    EditText ad;
    EditText malzemesayisi;
    Button duzenle;
    Button sil;
    String sonuc;

    List<Malzeme> malzemeler;
    int islenenid;
    DialogInterface dialog;
    DialogInterface dialog2;
    Dialog dialog3;
    stokAdapter adapter;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calisan_stok);
        listView = (ListView) findViewById(R.id.listview5);
        client = new OkHttpClient();

        dialog3 = new Dialog(calisanStok.this);
        dialog3.setTitle("Malzeme düzenle");
        dialog3.setContentView(R.layout.custom_dialog_malzeme);
        duzenle = (Button) dialog3.findViewById(R.id.mduzenle);
        sil = (Button) dialog3.findViewById(R.id.msil);
        ad = (EditText) dialog3.findViewById(R.id.ad);
        malzemesayisi = (EditText) dialog3.findViewById(R.id.malzemesayisi);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Malzeme c=(Malzeme) listView.getItemAtPosition(i);
                islenenid=c.id;
                dialog3.show();
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
            adapter = new stokAdapter(calisanStok.this, malzemeler, getApplicationContext());
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



    public class malzemesil extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_deletemalzeme + "/" +
                                islenenid)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                malzemeler = new ArrayList<Malzeme>();
                Gson gson = new Gson();
                malzemeler = Arrays.asList(gson.fromJson(String.valueOf(object), Malzeme[].class));
            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Toast.makeText(getApplicationContext(),"Malzeme silindi",Toast.LENGTH_SHORT).show();
            Intent gec = new Intent(getApplicationContext(), Tab_Calisan.class);
            startActivity(gec);
        }
    }
    class malzemeduzenle extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try{

                String adi=ad.getText().toString();
                String malzemesayisii=malzemesayisi.getText().toString();


                JsonObject json = new JsonObject();
                json.addProperty("ad",adi);
                json.addProperty("malzemeSayisi",malzemesayisii);
                String jsonString = json.toString();

                OkHttpClient client=new OkHttpClient();

                RequestBody body = RequestBody.create(JSON, jsonString);

                Request request=new Request.Builder()
                        .url(Json_url_duzenle+"/"+
                                islenenid)
                        .post(body)
                        .build();
                Response response=client.newCall(request).execute();
                String results=response.body().string();

                JSONArray object=new JSONArray(results);
                Gson gson = new Gson();
                malzemeler = new ArrayList<Malzeme>();
                malzemeler = Arrays.asList(gson.fromJson(String.valueOf(object), Malzeme[].class));

            }catch (Exception e){
                Log.e("yyyyyyyy",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            new getir().execute();
            dialog3.dismiss();
            Toast.makeText(getApplicationContext(),"Malzeme düzenlendi", Toast.LENGTH_SHORT).show();
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
                new malzemesil().execute();
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
                dialog2=dialogInterface;
                new malzemeduzenle().execute();
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
