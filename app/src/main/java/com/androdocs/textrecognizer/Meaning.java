package com.androdocs.textrecognizer;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


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

        lvOG= findViewById(R.id.listViewOriginal);
        lvOG.setAdapter(new CustomAdapter(this, list));




        TextView textView = (TextView) findViewById(R.id.textSpan);
        String completeString = value;
        final int arraySize = list.length;

        for(int i = 0; i < arraySize; i++)
        {
            final String singleElement = list[i];
            String partToClick = singleElement;
            createLink(textView, completeString, partToClick,
                    new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
                            // your action
                            Toast.makeText(Meaning.this, "Start Sign up activity "+arraySize+singleElement, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            // this is where you set link color, underline, typeface etc.
                            int linkColor = ContextCompat.getColor(Meaning.this, R.color.colorAccent);
                            ds.setColor(linkColor);
                            ds.setUnderlineText(false);
                        }
                    }
            );
        }
    }
    public static TextView createLink(TextView targetTextView, String completeString,
                                      String partToClick, ClickableSpan clickableAction) {



        SpannableString spannableString = new SpannableString(completeString);

        // make sure the String is exist, if it doesn't exist
        // it will throw IndexOutOfBoundException

        int startPosition = completeString.indexOf(partToClick);
        int endPosition = completeString.lastIndexOf(partToClick) + partToClick.length();

        spannableString.setSpan(clickableAction, startPosition, endPosition,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        targetTextView.setText(spannableString);
        targetTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return targetTextView;
    }



}
