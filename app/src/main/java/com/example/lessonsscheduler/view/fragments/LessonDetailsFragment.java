package com.example.lessonsscheduler.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LessonDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LessonDetailsFragment extends Fragment implements InformationFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView nameText, contactText, daysText, timeText, lengthText;

    public LessonDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LessonDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LessonDetailsFragment newInstance(String param1, String param2) {
        LessonDetailsFragment fragment = new LessonDetailsFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lesson_details, container, false);
        nameText = rootView.findViewById(R.id.nameText);
        contactText = rootView.findViewById(R.id.contactText);
        daysText = rootView.findViewById(R.id.daysText);
        timeText = rootView.findViewById(R.id.timeText);
        lengthText = rootView.findViewById(R.id.lengthText);
        return rootView;
    }

    @Override
    public void setInformation(MainActivity activity) {

    }
}