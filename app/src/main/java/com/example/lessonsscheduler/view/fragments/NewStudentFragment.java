package com.example.lessonsscheduler.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewStudentFragment extends Fragment implements InformationFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NumberPicker gradePicker;
    private TextView textGrade;
    private EditText contactText;
    private RadioGroup contactGroup;
    private RadioButton radioPhone, radioEmail;

    public NewStudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewStudentFragment newInstance(String param1, String param2) {
        NewStudentFragment fragment = new NewStudentFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_new_student, container, false);
        gradePicker = rootView.findViewById(R.id.gradePicker);
        textGrade = rootView.findViewById(R.id.textGrade);
        contactGroup = rootView.findViewById(R.id.contactGroup);
        contactText = rootView.findViewById(R.id.contactEditText);
        radioEmail = rootView.findViewById(R.id.radioEmail);
        radioPhone = rootView.findViewById(R.id.radioPhone);
        final String[] values = new String[] {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "University", "N/A"};
        gradePicker.setWrapSelectorWheel(false);
        gradePicker.setDisplayedValues(values);
        gradePicker.setMinValue(0);
        gradePicker.setMaxValue(13);
        gradePicker.setValue(8);
        textGrade.setText("Grade: " + values[gradePicker.getValue()]);
        gradePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textGrade.setText("Grade: " + values[i1]);
            }
        });
        contactGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.radioEmail:
                        contactText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        break;
                    default:
                        contactText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
                contactText.setText("");
            }
        });
        contactGroup.check(R.id.radioPhone);
        return rootView;
    }

    @Override
    public void setInformation(MainActivity activity) {

    }
}