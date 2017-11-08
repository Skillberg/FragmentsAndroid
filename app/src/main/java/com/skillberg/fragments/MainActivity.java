package com.skillberg.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements SelectionFragment.OnColorSelectedListener {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        final FragmentManager fragmentManager = getFragmentManager();

        final SelectionFragment selectionFragment =
                (SelectionFragment) fragmentManager.findFragmentById(R.id.selection_fragment);

        if (isTablet) {
            fragmentManager.addOnBackStackChangedListener(new FragmentManager
                    .OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    DetailFragment detailFragment = (DetailFragment)
                            fragmentManager.findFragmentByTag("detail");

                    selectionFragment.selectButtonWithColor(detailFragment.getColor());
                }
            });
        }
    }

    @Override
    public void onColorSelected(int color) {
        if (!isTablet) {
            // Телефон

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_COLOR, color);

            startActivity(intent);
        } else {
            // Планшет

            DetailFragment detailFragment = DetailFragment.newInstance(color);


            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if (fragmentManager.findFragmentByTag("detail") != null) {
                // Если фрагмент уже добавлен — заменяем

                fragmentTransaction.replace(R.id.container, detailFragment, "detail");
                fragmentTransaction.addToBackStack(null);
            } else {
                // Иначе добавляем

                fragmentTransaction.add(R.id.container, detailFragment, "detail");
            }

            fragmentTransaction.commit();
        }
    }
}