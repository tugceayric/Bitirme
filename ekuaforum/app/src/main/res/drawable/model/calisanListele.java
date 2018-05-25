package com.example.tugceayric.post.model;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tugceayric.post.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tugce Ayric on 21.03.2018.
 */

public class calisanListele extends AppCompatActivity {
    private String url="http://10.45.72.22:8080/calisanlar/all";
    OkHttpClient client;
    TextView tv;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tv);
        client = new OkHttpClient();
        OkHttpHandler okHttpHandler= new OkHttpHandler();
        okHttpHandler.execute(url);

    }

    public class OkHttpHandler extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                string=response.body().string();
                //Log.e("aaaaaaa",response.body().string()+"");
                return null;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            tv.setText(string);
        }
    }
}
