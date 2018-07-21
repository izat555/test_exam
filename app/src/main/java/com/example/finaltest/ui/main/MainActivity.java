package com.example.finaltest.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finaltest.R;
import com.example.finaltest.config.AppConstants;
import com.example.finaltest.ui.first.FirstFragment;

public class MainActivity extends AppCompatActivity {
    private EditText mEtText;
    private TextView mTvText;
    private Fragment mFirstFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtText = findViewById(R.id.et_text);
        mEtText.addTextChangedListener(mTextWatcher);
        mTvText = findViewById(R.id.tv_text);

        if (mFirstFragment == null) {
            mFirstFragment = new FirstFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.fragment_container, mFirstFragment)
                    .commit();
        } else {
            if (savedInstanceState != null) {
                String text  = savedInstanceState.getString(getString(R.string.text));
                mEtText.setText(text);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                break;
            case R.id.action_last_list:
                Intent intent = new Intent("my.intent.action.show");
                intent.putExtra(getString(R.string.text), "");
                startActivity(intent);
                break;
        }
        return true;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == AppConstants.MAX_TEXT_SIZE) {
                s.delete(s.length() - 1, s.length());
            } else {
                mTvText.setText(s.toString());
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.text), mEtText.getText().toString());
    }
}
