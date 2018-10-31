package com.untitledlangproject.wordlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.untitledlangproject.R;
import com.untitledlangproject.dao.WordItem;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {

    private List<WordItem> words;

    public WordAdapter(List<WordItem> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_word_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final WordItem word = new WordItem(
                words.get(position).getText(),
                words.get(position).getNumberOfUsage());

        holder.tvWord.setText(word.getText());
        holder.tvNumberOfUsages.setText(String.valueOf(word.getNumberOfUsage()));

        holder.imgNoKeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                words.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvWord, tvNumberOfUsages;
        private Button imgNoKeep;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_word);
            tvNumberOfUsages = itemView.findViewById(R.id.tv_number_of_usages);
            imgNoKeep = itemView.findViewById(R.id.img_no_keep);
        }


    }
}
