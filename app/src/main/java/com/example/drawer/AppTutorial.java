package com.example.drawer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class AppTutorial extends AppIntro2 {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Welcome to OrganizeIt!");
        sliderPage1.setDescription("OrganizeIt provides you with a platform for you to keep track of anything you need!");
        sliderPage1.setImageDrawable(R.drawable.main);
        sliderPage1.setBgColor(getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("You Can Create Boards");
        sliderPage2.setDescription("Boards work like a project. Think of it as the cover of a book.");
        sliderPage2.setImageDrawable(R.drawable.createboards);
        sliderPage2.setBgColor(getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Organize Your Boards By Adding Cards!");
        sliderPage3.setDescription("Inside each board you can add cards. You can add a list of tasks to a card so you can keep track of the things you want!");
        sliderPage3.setImageDrawable(R.drawable.createcards);
        sliderPage3.setBgColor(getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Manage Your Team!");
        sliderPage4.setDescription("With OrganizeIt you can add collaborators to your Boards if you are working on something together1");
        sliderPage4.setImageDrawable(R.drawable.manageteam);
        sliderPage4.setBgColor(getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage4));

        SliderPage sliderPage5 = new SliderPage();
        sliderPage5.setTitle("You are Ready !");
        sliderPage5.setDescription("You are now ready to start using the application! Feel free to explore other awesome features!");
        sliderPage5.setImageDrawable(R.drawable.readystartexploring);
        sliderPage5.setBgColor(getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage5));

        // Bind the background to the intro
        //setBackgroundResource(R.drawable.ic_launcher_background);


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Toast.makeText(this, "You should have read the tutorial!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Login.class));
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
        startActivity(new Intent(this, Login.class));
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
