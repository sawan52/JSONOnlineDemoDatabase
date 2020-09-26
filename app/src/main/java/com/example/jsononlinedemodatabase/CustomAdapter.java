package com.example.jsononlinedemodatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    public void add(ExtractJsonData object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ExtractJSONDataHolder extractJSONDataHolder;
        View row;
        row = convertView;

        if (row == null) {

            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_in_list_layout, parent, false);
            extractJSONDataHolder = new ExtractJSONDataHolder();

            extractJSONDataHolder.tx_name = row.findViewById(R.id.json_name);
            extractJSONDataHolder.tx_password = row.findViewById(R.id.json_password);
            extractJSONDataHolder.tx_contact = row.findViewById(R.id.json_contact);
            extractJSONDataHolder.tx_country = row.findViewById(R.id.json_country);
            row.setTag(extractJSONDataHolder);

        } else {
            extractJSONDataHolder = (ExtractJSONDataHolder) row.getTag();
        }

        ExtractJsonData extractJsonData = (ExtractJsonData) this.getItem(position);

        extractJSONDataHolder.tx_name.setText(extractJsonData.getName());
        extractJSONDataHolder.tx_password.setText(extractJsonData.getPassword());
        extractJSONDataHolder.tx_contact.setText(extractJsonData.getContact());
        extractJSONDataHolder.tx_country.setText(extractJsonData.getCountry());


        return row;
    }

    static class ExtractJSONDataHolder {

        TextView tx_name, tx_password, tx_contact, tx_country;

    }
}
