package com.example.eko.androidkamus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_WORD = "ITEM_WORD";
    public static final String ITEM_TRANSLATE = "ITEM_TRANSLATE";


    @BindView(R.id.tv_word)
    TextView tvWord;

    @BindView(R.id.tv_translate)
    TextView tvTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        tvWord.setText(getIntent().getStringExtra(ITEM_WORD));
        tvTranslate.setText(getIntent().getStringExtra(ITEM_TRANSLATE));
    }
}
