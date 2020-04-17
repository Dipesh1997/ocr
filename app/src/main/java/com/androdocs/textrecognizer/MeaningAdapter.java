package com.androdocs.textrecognizer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.WordMeaningVH> {

    private static final String TAG = "MeaningAdapter";
    List<WordMeaning> wordMeaningList;

    public MeaningAdapter(List<WordMeaning> wordMeaningList) {
        this.wordMeaningList = wordMeaningList;
    }

    @NonNull
    @Override
    public WordMeaningVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_row, parent, false);
        return new WordMeaningVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordMeaningVH holder, int position) {

        WordMeaning wordMeaning = wordMeaningList.get(position);
        holder.titleTextView.setText(wordMeaning.getTitle());
        holder.yearTextView.setText(wordMeaning.getYear());
        holder.ratingTextView.setText(wordMeaning.getRating());
        holder.plotTextView.setText(wordMeaning.getPlot());

        boolean isExpanded = wordMeaningList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return wordMeaningList.size();
    }

    class WordMeaningVH extends RecyclerView.ViewHolder {
        ConstraintLayout expandableLayout;
        TextView titleTextView, yearTextView, ratingTextView, plotTextView;
        String url;

        public WordMeaningVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            plotTextView = itemView.findViewById(R.id.plotTextView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    WordMeaning wordMeaning = wordMeaningList.get(getAdapterPosition());
                    wordMeaning.setExpanded(!wordMeaning.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                    toast();
                }




                private void toast() {

                }
            });
            plotTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toast();
                }

                private void toast() {
                    String word = titleTextView.getText().toString();
                    String noSpaceStr = word.replaceAll("\\s", "");
                    Toast.makeText(itemView.getContext(),noSpaceStr,Toast.LENGTH_SHORT).show();
                    DictionaryRequest dictionaryRequest = new DictionaryRequest(itemView.getContext(), plotTextView);
                    url = dictionaryEntries();
                    dictionaryRequest.execute(url);
                }

                private String dictionaryEntries() {
                    final String language = "en-gb";
                    final String word = titleTextView.getText().toString();
                    final String noSpaceStr = word.replaceAll("\\s", "");
                    final String fields = "definitions";
                    final String strictMatch = "false";
                    final String word_id = noSpaceStr.toLowerCase();
                    return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
                }

            });

        }

    }
}