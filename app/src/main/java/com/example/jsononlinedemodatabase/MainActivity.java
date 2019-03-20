package com.example.jsononlinedemodatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button getJSON, parseJSON;
    TextView display_text_view;
    String json_string;
    String JSON_STRING;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        getJSON = findViewById(R.id.get_json_button);
        parseJSON = findViewById(R.id.parse_json_button);
        display_text_view = findViewById(R.id.display_json);

        getJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Fetching JSON data...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                new BackgroundTask().execute();

            }
        });

        parseJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, JSONProperFormatActivity.class);
                intent.putExtra("json_data", JSON_STRING);
                startActivity(intent);

            }
        });

    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{

        String json_url = "http://myfirstsite.eu3.biz/retrieve.php";

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // collect all the data and store it in String Builder
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null){

                    stringBuilder.append(json_string + "\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            display_text_view.setText(result);
            JSON_STRING = result;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
