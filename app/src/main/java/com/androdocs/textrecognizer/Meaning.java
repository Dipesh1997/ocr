package com.androdocs.textrecognizer;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Meaning extends AppCompatActivity {

    ListView lvOG ;
    //int start,end=0;
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
        lvOG.setAdapter(new CustomAdapter(this, list));
/*        SpannableString ss = new SpannableString(value);
        for(final String word : list){
            if(word.startsWith(".")){
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        //use word here to make a decision
                        Spanned sp = (Spanned) ((TextView)textView).getText();
                        start = sp.getSpanStart(this);
                        end = sp.getSpanEnd(this);
                        String word = sp.subSequence(start, end).toString();
                        Toast.makeText(getApplicationContext(),word,Toast.LENGTH_SHORT).show();
                    }
                };
                ss.setSpan(clickableSpan,start, end + word.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        TextView textView = (TextView) findViewById(R.id.textSpan);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());*/

    }


}
