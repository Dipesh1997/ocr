package com.androdocs.textrecognizer;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {
    String [] result;
    Context context;
    private static LayoutInflater inflater=null;
    //public CustomAdapter(TextList activity, String[] prgmNameList) {
    public CustomAdapter(Meaning activity, String[] prgmNameList) {

        result = prgmNameList;
        context = activity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView = inflater.inflate(R.layout.list_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        // holder.tv.setText(result[position]);

        String span[] = result[position].split(" ") ;

        SpannableString ss = new SpannableString(result[position]);


        ClickableSpan spans[] = new ClickableSpan[span.length];
        for(int spanCount = 0 ; spanCount < span.length ; spanCount++){
            spans[spanCount] = new ClickableSpan() {
                @Override
                public void onClick(View textView) {

                    TextView v = (TextView)textView ;
                    String text = v.getText().toString() ;

                    Log.d("View" , text);
                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                }
            };
        }

        int start = 0 ;
        int end = 0 ;

        try {
            for(int spanCount = 0 ; spanCount <span.length ; spanCount++){
                if(spanCount==0) {
                    end += span[spanCount].length();
                }else{

                    end += span[spanCount].length()+1;
                }
                ss.setSpan(spans[spanCount], start, end , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                start += span[spanCount].length()+1;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tv.setText(ss);
        holder.tv.setMovementMethod(LinkMovementMethod.getInstance());

        Log.d("SpannableString" , ss.toString());

        return rowView;
    }

}