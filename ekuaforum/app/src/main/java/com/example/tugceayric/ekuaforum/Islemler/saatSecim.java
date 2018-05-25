package com.example.tugceayric.ekuaforum.Islemler;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugceayric.ekuaforum.CustomAdapter.cokluAdapter;
import com.example.tugceayric.ekuaforum.Kaynak.Randevu;
import com.example.tugceayric.ekuaforum.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.tugceayric.ekuaforum.Islemler.cek.url;

/**
 * Created by Tugce Ayric on 11.05.2018.
 */

public class saatSecim extends AppCompatActivity {
    private String Json_url_arama =url+"randevular/search";
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12;
    EditText et,et2;
    String gelenveri;
    Dialog dialog;
    DialogInterface dialog2;

    int sonuc = 0;
    List<Randevu> randevu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_saat);

        btn1 = (Button) findViewById(R.id.bir1);
        btn2 = (Button) findViewById(R.id.iki2);
        btn3 = (Button) findViewById(R.id.uc3);
        btn4 = (Button) findViewById(R.id.dort4);
        btn5 = (Button) findViewById(R.id.bes5);
        btn6 = (Button) findViewById(R.id.alti6);
        btn7 = (Button) findViewById(R.id.yedi7);
        btn8 = (Button) findViewById(R.id.sekiz8);
        btn9 = (Button) findViewById(R.id.dokuz9);
        btn10 = (Button) findViewById(R.id.on10);
        btn11 = (Button) findViewById(R.id.onbir11);
        btn12 = (Button) findViewById(R.id.oniki12);
        et = (EditText) findViewById(R.id.saat);
        et2=(EditText) findViewById(R.id.tarih);
        Intent i = getIntent();
        this.gelenveri = i.getStringExtra("gidenveri");
        dialog = new Dialog(saatSecim.this);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("09:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("10:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("11:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("12:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("13:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("14:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("15:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("16:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });


        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("17:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("18:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("19:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et.setText("20:00");
                et2.setText(gelenveri);
                new arama().execute();

                    String veri = et.getText().toString();
                    Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                    gec.putExtra("giden", gelenveri);
                    gec.putExtra("veri", veri);
                    startActivity(gec);

            }
        });

    }

    public class arama extends AsyncTask<String, Void, String> {

        int sonuc = 0;

        @Override
        protected String doInBackground(String... strings) {

            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(Json_url_arama+"/"+et2.getText()+"-"+ et.getText())
                        .build();
                Response response = client.newCall(request).execute();
                String results = response.body().string();
                JSONObject object = new JSONObject(results);
                Gson gson = new Gson();

                sonuc = response.code();
                if(sonuc==200)
                {

                    Randevu rnd=new Randevu();
                    rnd=gson.fromJson(String.valueOf(object),Randevu.class);
                    randevu = new ArrayList<Randevu>();
                    randevu.add(rnd);

                }

                et.setText("");
                } catch (Exception e) {
                Log.e("yyyyyyyy", e.toString());
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (sonuc == 200) {
                Toast.makeText(getApplicationContext(), "Başka bir saat tercih ediniz", Toast.LENGTH_LONG).show();
                String veri = et.getText().toString();
                Intent gec = new Intent(saatSecim.this, randevuOlustur.class);
                gec.putExtra("giden", gelenveri);
                gec.putExtra("veri", veri);
                startActivity(gec);

            } else {
                Toast.makeText(getApplicationContext(), "Randevunuzu Tamamlayınız", Toast.LENGTH_LONG).show();
            }

        }
    }
}
