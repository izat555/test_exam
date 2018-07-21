package com.example.finaltest.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finaltest.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {
    private ArrayList<Character> mCharacters;

    class CustomHolder extends RecyclerView.ViewHolder {
        TextView tvChar;

        public CustomHolder(View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tv_char);
        }
    }

    public CustomAdapter(ArrayList<Character> characters) {
        mCharacters = characters;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        CustomHolder customHolder = new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        holder.tvChar.setText(String.valueOf(mCharacters.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }


}
