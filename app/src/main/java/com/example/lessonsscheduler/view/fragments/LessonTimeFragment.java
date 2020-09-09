package com.example.lessonsscheduler.view.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.view.MainActivity;

import java.util.ArrayList;

public class LessonTimeFragment extends Fragment implements InformationFragment
{
    private ArrayList<TextView> textViews;
    private ArrayList<CheckBox> checkBoxes;
    private TimePicker timePicker;

    public LessonTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_lesson_time, container, false);
        // Inflate the layout for this fragment
        textViews = new ArrayList<>(7);
        checkBoxes = new ArrayList<>(7);

        textViews.add((TextView) rootView.findViewById(R.id.textSunday));
        textViews.add((TextView) rootView.findViewById(R.id.textMonday));
        textViews.add((TextView) rootView.findViewById(R.id.textTuesday));
        textViews.add((TextView) rootView.findViewById(R.id.textWednesday));
        textViews.add((TextView) rootView.findViewById(R.id.textThursday));
        textViews.add((TextView) rootView.findViewById(R.id.textFriday));
        textViews.add((TextView) rootView.findViewById(R.id.textSaturday));

        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxSunday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxMonday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxTuesday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxWednesday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxThursday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxFriday));
        checkBoxes.add((CheckBox) rootView.findViewById(R.id.checkBoxSaturday));

        timePicker = rootView.findViewById(R.id.timePicker);

        for(int i = 0; i < 7; i++) {
            final CheckBox checkBox = checkBoxes.get(i);
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkBox.performClick();
                }
            });
        }

        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setInformation(MainActivity activity)
    {
        activity.setTime(timePicker.getHour(), timePicker.getMinute());
        activity.setDays(checkBoxes.get(0).isChecked(), checkBoxes.get(1).isChecked(),
                         checkBoxes.get(2).isChecked(), checkBoxes.get(3).isChecked(),
                         checkBoxes.get(4).isChecked(), checkBoxes.get(5).isChecked(),
                         checkBoxes.get(6).isChecked());
    }
}