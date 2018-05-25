package com.example.tugceayric.ekuaforum.Islemler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.islemAdapter;
import com.example.tugceayric.ekuaforum.CustomAdapter.kuaforAdapter;
import com.example.tugceayric.ekuaforum.CustomAdapter.cokluAdapter;

import com.example.tugceayric.ekuaforum.Kaynak.Calisan;
import com.example.tugceayric.ekuaforum.Kaynak.CalisanDetay;
import com.example.tugceayric.ekuaforum.Kaynak.Islem;
import com.example.tugceayric.ekuaforum.Kaynak.Randevu;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 27.04.2018.
 */

public class randevuOlustur extends AppCompatActivity implements View.OnClickListener {

    private String Json_url_allcalisan = url+"calisanlar/all/";
    private String Json_url_allislem = url+"islemler/all/";
    private String Json_url_addrandevu = url+"randevular/add/";

        Spinner calisan;
        Spinner islem;
        islemAdapter islemAdapter;
        EditText selectDate,selectTime,tarih;
        private int mYear, mMonth, mDay;
        kuaforAdapter kuaforAdapter;
        List<Calisan> calisanlar;
        List<Islem> islemler;
        int calisanId;
        int islemId;
        int musteriId;
        List<CalisanDetay> uzmanliklar;
        SharedPreferences sharedpreferences;
        Islem islemlerim;
        ListView lv;
        Button btn,btn2;
        List<Islem> islemler_Adlar;
        Islem getIslemlerim;
        cokluAdapter cokluAdapter;
        TextView et;
        String gelenVeri;
        String gelenveri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.musteri_randevu);
            sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
            musteriId = sharedpreferences.getInt("id", 0);

            selectDate = (EditText) findViewById(R.id.date);
            selectDate.setOnClickListener(this);
            selectTime = (EditText) findViewById(R.id.time);


            et = (TextView) findViewById(R.id.et1);
            calisan = (Spinner) findViewById(R.id.spinnerr);
            islem = (Spinner) findViewById(R.id.spinnerr2);
            tarih = (EditText) findViewById(R.id.date);
            lv = (ListView) findViewById(R.id.lv8);
            btn = (Button) findViewById(R.id.button3);
            btn2=(Button)findViewById(R.id.giriss);
            calisanlar = new ArrayList<>();
            islemler = new ArrayList<>();
            uzmanliklar = new ArrayList<>();
            islemler_Adlar = new ArrayList<>();

            new calisangetir().execute();
            new islemgetir().execute();


            Intent i = getIntent();
            this.gelenVeri= i.getStringExtra("veri");
            et.setText(gelenVeri);

            Intent a = getIntent();
            this.gelenveri= a.getStringExtra("giden");
            selectDate.setText(gelenveri);

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent anasayfa=new Intent(randevuOlustur.this,Tab_Musteri.class);
                    startActivity(anasayfa);
                }
            });


            islem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    islemId = islemler.get(i).id;
                    getIslemlerim = islemler.get(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            calisan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    calisanId = calisanlar.get(i).id;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }


    public class randevuolustur extends AsyncTask<String, Void, String> {

        int sonuc = 0;

        @Override
        protected String doInBackground(String... strings) {
            try {
                    String aaa = "" + islemler_Adlar.get(0).id;
                    for (int i = 1; i < islemler_Adlar.size(); i++) {
                        aaa = aaa + "," + islemler_Adlar.get(i).id;
                    }

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(Json_url_addrandevu +
                                    tarih.getText() + "-" +
                                    et.getText() + "/" +
                                    musteriId + "/" +
                                    calisanId + "/" +
                                    aaa)
                            .build();
                    Response response = client.newCall(request).execute();
                    String results = response.body().string();

                    JSONObject object = new JSONObject(results);
                    Gson gson = new Gson();
                    islemlerim = gson.fromJson(String.valueOf(object), Islem.class);
                    sonuc = response.code();

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
                islem.setAdapter(islemAdapter);
                calisan.setAdapter(kuaforAdapter);
                tarih.setText("");
                et.setText("");
                islemler_Adlar=new ArrayList<>();
                cokluAdapter = new cokluAdapter(randevuOlustur.this,islemler_Adlar, getApplicationContext());
                cokluAdapter.notifyDataSetChanged();
                lv.setAdapter(cokluAdapter);
                Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();

        }
    }
    public class calisangetir extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_allcalisan)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                calisanlar = Arrays.asList(gson.fromJson(String.valueOf(object), Calisan[].class));

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            kuaforAdapter = new kuaforAdapter(randevuOlustur.this,calisanlar, getApplicationContext());
            calisan.setAdapter(kuaforAdapter);

        }
    }

    public class islemgetir extends AsyncTask<String, Void, String> {
        String sonuc = "giriş işlemi başarısız lütfen daha sonra tekrar deneyin!!";
        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_allislem)
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();

                JSONArray object = new JSONArray(results);
                Gson gson = new Gson();
                islemler = Arrays.asList(gson.fromJson(String.valueOf(object), Islem[].class));

            } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            islemAdapter = new islemAdapter(randevuOlustur.this,islemler, getApplicationContext());
            islem.setAdapter(islemAdapter);

        }
    }
        @Override
        public void onClick(View view) {

            if (view == selectDate) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                              selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }

        }


    public void randevuolustur(View view) {
        new randevuolustur().execute();
    }

    public void islemekle(View view) {

        btn.setEnabled(true);
        islemler_Adlar.add(getIslemlerim);
        cokluAdapter = new cokluAdapter(randevuOlustur.this,islemler_Adlar, getApplicationContext());
        cokluAdapter.notifyDataSetChanged();
        lv.setAdapter(cokluAdapter);

    }

    public void gecis(View view)
    {

        String veri= selectDate.getText().toString();
        Intent gec=new Intent(randevuOlustur.this,saatSecim.class);
        gec.putExtra("gidenveri",veri);
        startActivity(gec);



    }


}


