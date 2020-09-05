package com.example.seaofthievesmkb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String lastSearch = "";
    private static String[] Details = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Details = Details == null ? getString(R.string.Locations).split("\\|") : Details;
        UpdateListView(null);
        findViewById(R.id.SearchBox).setOnClickListener((v) -> ((EditText) v).setText(""));
        ((EditText) findViewById(R.id.SearchBox)).addTextChangedListener(SearchTextWatcher);

    }

    protected TextWatcher SearchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String text = s.toString();
            if (lastSearch != text) {
                lastSearch = text;
                UpdateListView(lastSearch == "" ? null : lastSearch);
            }
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    private void UpdateListView(String Search) {
        ArrayList<String> arrayList;
        if (Search != null) {
            arrayList = new ArrayList<>();
            for (String detail : Details) {
                if (detail.toLowerCase().contains(lastSearch.toLowerCase())) {
                    arrayList.add(detail);
                }
            }
        } else {
            arrayList = new ArrayList<>(Arrays.asList(Details));
        }

        ListView list = findViewById(R.id.ListView);
        list.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList));
    }


}