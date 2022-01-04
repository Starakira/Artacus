package com.lab.artacus.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lab.artacus.R;
import com.lab.artacus.view.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private RelativeLayout mRelativeLayoutMain;
    private TextView startText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startText = (TextView) findViewById(R.id.mainTextView );

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        startText.startAnimation(anim);

        mRelativeLayoutMain = (RelativeLayout) findViewById(R.id.layoutMain);;

        mRelativeLayoutMain.setOnTouchListener((View.OnTouchListener) this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        startText.setText("");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new LoginFragment());
        fragmentTransaction.commit();

        return false;
    }
}