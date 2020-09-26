package com.example.jsononlinedemodatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONProperFormatActivity extends AppCompatActivity {

    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;

    CustomAdapter customAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonproper_format);

        customAdapter = new CustomAdapter(this, R.layout.display_in_list_layout);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(customAdapter);

        JSON_STRING = getIntent().getExtras().getString("json_data");

        String name, password, contact, country;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            int count = 0;
            jsonArray = jsonObject.getJSONArray("server_response");

            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name");
                password = JO.getString("password");
                contact = JO.getString("contact");
                country = JO.getString("country");

                ExtractJsonData extractJsonData = new ExtractJsonData(name, password, contact, country);
                customAdapter.add(extractJsonData);
                count++;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
