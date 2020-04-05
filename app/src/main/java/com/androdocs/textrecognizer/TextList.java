package com.androdocs.textrecognizer;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class TextList extends AppCompatActivity {

    ListView lv ;
    public static String [] prgmNameList={"Android Dev","SpearHead Inc","Dipesh Kataria"}; // You can put dynamic strings in this array .
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList));

    }


}