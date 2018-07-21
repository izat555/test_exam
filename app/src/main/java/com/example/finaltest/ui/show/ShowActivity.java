package com.example.finaltest.ui.show;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.example.finaltest.R;
import com.example.finaltest.data.SQLiteHelper;
import com.example.finaltest.ui.adapters.CustomAdapter;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Character> mCharacters;
    private SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mSQLiteHelper = new SQLiteHelper(this);

        String text = getIntent().getStringExtra(getString(R.string.text));
        if (!TextUtils.isEmpty(text)) {
            mCharacters = new ArrayList<>();

            for (int i = 0; i < text.length(); i++) {
                mCharacters.add(new Character(text.charAt(i)));
            }

            long rowInserted = mSQLiteHelper.saveChars(mCharacters);
            Log.d(getString(R.string.my_log), String.valueOf(rowInserted));
        } else if (TextUtils.isEmpty(text)) {
            mCharacters = mSQLiteHelper.getSavedChars();
        }

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CustomAdapter(mCharacters));
    }
}
