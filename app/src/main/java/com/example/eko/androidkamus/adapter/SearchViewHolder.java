package com.example.eko.androidkamus.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.eko.androidkamus.DetailActivity;
import com.example.eko.androidkamus.R;
import com.example.eko.androidkamus.model.KamusModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;
    public SearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final KamusModel item){
        tvWord.setText(item.getWord());
        tvTranslate.setText(item.getTranslate());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mInten =  new Intent(itemView.getContext(), DetailActivity.class);
                mInten.putExtra(DetailActivity.ITEM_WORD, item.getWord());
                mInten.putExtra(DetailActivity.ITEM_TRANSLATE, item.getTranslate());
                itemView.getContext().startActivity(mInten);
            }
        });
    }

}
