package com.example.finaltest.ui.show;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.finaltest.R;
import com.example.finaltest.ui.adapters.CustomAdapter;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Character> mCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String text = getIntent().getStringExtra(getString(R.string.text));
        if (text != null) {
            mCharacters = new ArrayList<>();

            for (int i = 0; i < text.length(); i++) {
                mCharacters.add(new Character(text.charAt(i)));
            }

            mRecyclerView = findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new CustomAdapter(mCharacters));
        }
    }
}
