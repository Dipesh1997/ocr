package com.androdocs.textrecognizer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Meaning extends AppCompatActivity {

    ListView lvOG ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);
        String value = getIntent().getStringExtra("RECTEXT");
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
        String[] list =value.split(" ");
        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter<CharSequence>(this,android.R.layout.simple_list_item_1,list);
        lvOG=(ListView) findViewById(R.id.listViewOriginal);
        lvOG.setAdapter(adapter);
    }


}
