package com.example.lessonsscheduler.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.view.fragments.InformationFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonNext, buttonPrevious;
    private ViewFlipper viewFlipper, viewFlipperNext, viewFlipperPrevious;
    private String[] titles;
    private boolean[] prevValid, nextValid;
    private ArrayList<InformationFragment> fragments;

    private int hour, minute, length;
    boolean su, mo, tu, we, th, fr, sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNext = (FloatingActionButton) findViewById(R.id.buttonNext);
        buttonPrevious = (FloatingActionButton) findViewById(R.id.buttonPrevious);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        viewFlipperNext = (ViewFlipper) findViewById(R.id.viewFlipperNext);
        viewFlipperPrevious = (ViewFlipper) findViewById(R.id.viewFlipperPrevious);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragments.get(viewFlipper.getDisplayedChild()).setInformation(MainActivity.this);
                showNextFragment();
            }
        });
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPreviousFragment();
            }
        });

        titles = new String[] {"New Lesson: Time and Days", "New Lesson: Length", "New Lesson: Student", "New Student", "Lesson Details"};
        prevValid = new boolean[] {false, true, true, true, true, true};
        nextValid = new boolean[] {true, true, false, true, true, true};
        buttonPrevious.setEnabled(prevValid[0]);
        buttonNext.setEnabled(nextValid[0]);
    }

    public void showPreviousFragment()
    {
        int screen = viewFlipper.getDisplayedChild() - 1;
        if(screen < 0)
            return;
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right));
        if((prevValid[screen + 1] && !prevValid[screen]) || (!prevValid[screen + 1] && prevValid[screen]))
        {
            viewFlipperPrevious.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
            viewFlipperPrevious.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right));
            viewFlipperPrevious.showPrevious();
            buttonPrevious.setEnabled(!buttonPrevious.isEnabled());
        }
        if((nextValid[screen + 1] && !nextValid[screen]) || (!nextValid[screen + 1] && nextValid[screen]))
        {
            viewFlipperNext.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
            viewFlipperNext.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right));
            viewFlipperNext.showPrevious();
            buttonNext.setEnabled(!buttonNext.isEnabled());
        }
        viewFlipper.showPrevious();
        updateTitle();
    }

    public void showNextFragment()
    {
        int screen = viewFlipper.getDisplayedChild() + 1;
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
        if((prevValid[screen - 1] && !prevValid[screen]) || (!prevValid[screen - 1] && prevValid[screen]))
        {
            viewFlipperPrevious.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
            viewFlipperPrevious.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
            viewFlipperPrevious.showNext();
            buttonPrevious.setEnabled(!buttonPrevious.isEnabled());
        }
        if((nextValid[screen - 1] && !nextValid[screen]) || (!nextValid[screen - 1] && nextValid[screen]))
        {
            viewFlipperNext.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
            viewFlipperNext.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
            viewFlipperNext.showNext();
            buttonNext.setEnabled(!buttonNext.isEnabled());
        }
        viewFlipper.showNext();
        updateTitle();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragments == null)
            fragments = new ArrayList<>();
        fragments.add((InformationFragment) fragment);
    }

    public void updateTitle()
    {
        getSupportActionBar().setTitle(titles[viewFlipper.getDisplayedChild()]);
    }

    public void setTime(int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }

    public void setDays(boolean su, boolean mo, boolean tu, boolean we, boolean th, boolean fr, boolean sa)
    {
        this.su = su;
        this.mo = mo;
        this.tu = tu;
        this.we = we;
        this.th = th;
        this.fr = fr;
        this.sa = sa;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
}