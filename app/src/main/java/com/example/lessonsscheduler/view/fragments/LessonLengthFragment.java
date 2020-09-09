package com.example.lessonsscheduler.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonLengthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonLengthFragment extends Fragment implements InformationFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private NumberPicker lengthPicker;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textLength;

    public LessonLengthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiscInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonLengthFragment newInstance(String param1, String param2) {
        LessonLengthFragment fragment = new LessonLengthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_lesson_length, container, false);
        lengthPicker = (NumberPicker) rootView.findViewById(R.id.lengthPicker);
        textLength = (TextView) rootView.findViewById(R.id.textLength);
        final String[] values = new String[] {"15", "30", "45", "60", "90", "120"};
        lengthPicker.setWrapSelectorWheel(false);
        lengthPicker.setDisplayedValues(values);
        lengthPicker.setMinValue(0);
        lengthPicker.setMaxValue(5);
        lengthPicker.setValue(1);
        textLength.setText("Minutes: " + values[lengthPicker.getValue()]);
        lengthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textLength.setText("Minutes: " + values[i1]);
            }
        });
        return rootView;
    }

    @Override
    public void setInformation(MainActivity activity) {

    }
}