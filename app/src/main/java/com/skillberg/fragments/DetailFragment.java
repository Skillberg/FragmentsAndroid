package com.skillberg.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Фрагмент, отображающий выбранный цвет
 */
public class DetailFragment extends Fragment {

    private static final String ARG_COLOR = "color";

    private int color = 0;


    public static DetailFragment newInstance(int color) {
        DetailFragment fragment = new DetailFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);

        fragment.setArguments(args);

        return fragment;
    }

    public DetailFragment() {
    }

    public int getColor() {
        return color;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if (args != null && args.containsKey(ARG_COLOR)) {
            color = args.getInt(ARG_COLOR);

            view.setBackgroundColor(color);
        }
    }
}