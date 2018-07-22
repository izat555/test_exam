package com.example.finaltest.ui.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finaltest.R;

public class FirstFragment extends Fragment {
    private EditText mEtText;
    private TextView mTvText;
    private Button mBtnGo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(getContext().getString(R.string.my_log), "onCreateView");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mEtText = view.findViewById(R.id.et_text);
        mTvText = view.findViewById(R.id.tv_text);
        mBtnGo = view.findViewById(R.id.btn_go);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getContext().getString(R.string.my_log), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        mEtText.addTextChangedListener(mTextWatcher);
        mBtnGo.setOnClickListener(mOnClickListener);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d(getContext().getString(R.string.my_log), "onViewStateRestored");
        if (savedInstanceState != null) {
            mEtText.setText(savedInstanceState.getString(getContext().getString(R.string.text)));

        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(getContext().getString(R.string.my_log), "onSaveInstanceState");
        outState.putString(getContext().getString(R.string.text), mEtText.getText().toString());
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
            mTvText.setText(s.toString());
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent("my.intent.action.show")
                    .putExtra(getContext().getString(R.string.text), mEtText.getText().toString()));
        }
    };
}
