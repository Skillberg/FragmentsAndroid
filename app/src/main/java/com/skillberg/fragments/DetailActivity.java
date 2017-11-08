package com.skillberg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity для отображения DetailFragment на телефоне
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "color";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();

        if (extras == null || !extras.containsKey(EXTRA_COLOR)) {
            finish();
            return;
        }

        int color = extras.getInt(EXTRA_COLOR);

        DetailFragment detailFragment = DetailFragment.newInstance(color);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, detailFragment)
                .commit();
    }
}