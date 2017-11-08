package com.skillberg.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Фрагмент, в котором будет происходить выбор цвета
 */
public class SelectionFragment extends Fragment {

    @Nullable
    private OnColorSelectedListener onColorSelectedListener;

    public SelectionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onColorSelectedListener = (OnColorSelectedListener) context;
    }

    @Override
    public void onDetach() {
        onColorSelectedListener = null;

        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.colors_rg);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        return view;
    }


    /**
     * Listener для отслеживания выбора RadioButton
     */
    private final RadioGroup.OnCheckedChangeListener onCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    @ColorRes int colorResId;

                    switch (checkedId) {
                        case R.id.red_rb:
                            colorResId = R.color.colorRed;
                            break;

                        case R.id.green_rb:
                            colorResId = R.color.colorGreen;
                            break;

                        case R.id.blue_rb:
                            colorResId = R.color.colorBlue;
                            break;

                        default:
                            throw new IllegalArgumentException("Invalid id: " + checkedId);
                    }

                    Context context = getActivity();

                    if (context != null) {
                        int color = context.getResources().getColor(colorResId);

                        if (onColorSelectedListener != null) {
                            onColorSelectedListener.onColorSelected(color);
                        }
                    }
                }
            };


    /**
     * Listener для передачи цвета в Activity
     */
    public interface OnColorSelectedListener {

        void onColorSelected(int color);

    }
}