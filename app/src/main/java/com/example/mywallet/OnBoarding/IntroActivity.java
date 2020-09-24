package com.example.mywallet.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.mywallet.DatabaseHelper;
import com.example.mywallet.MainActivity;
import com.example.mywallet.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    private Button btnNext, btnGetStarted;
    private int position;
    private Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        //We need to check the shared preferences before load this acitivity

        if (loadSharedPref()) {
            Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
            startActivity(intent);
            finish();
        }

        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.close();
        final List<ScreenItem> itemList = new ArrayList<>();
        itemList.add(new ScreenItem("Add your Income", "Do you have an idea about your total income in a month. Now you can track your every income source and wallets at one place.", R.drawable.add_expenses_main_image));
        itemList.add(new ScreenItem("Manage you Expenses", "Do you know how much you daily spend on different things. Now you can keep records of your spends and track them.", R.drawable.manage_expenses));
        itemList.add(new ScreenItem("Create a budget for you", "Getting a trouble at the end of the month. Expenses are too high. Create a budget and manage things.", R.drawable.create_budget));
        itemList.add(new ScreenItem("Save for a Goal", "So many goals in life. But no money. Here you can save money for your goals and track them.", R.drawable.save_for_goal));
        //setup ViewPager
        viewPager = findViewById(R.id.viewPager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, itemList);
        btnGetStarted = findViewById(R.id.getStarted);
        btnGetStarted.setVisibility(View.INVISIBLE);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        viewPager.setAdapter(introViewPagerAdapter);

        tabIndicator = findViewById(R.id.tabItems);

        //Setup tab layout with viewpager
        tabIndicator.setupWithViewPager(viewPager);

        //NEXT Button
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();

                if (position < itemList.size()) {
                    position ++;
                    viewPager.setCurrentItem(position);
                }

                if (position == itemList.size() - 1) {
                    loadLastScreen();
                }
            }
        });

        //Tab Layout add on change listner
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = viewPager.getCurrentItem();
                if (position == itemList.size() - 1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                //Then we need to store a value in the storage to get that these screens should not display again
                //We can use shared prefrences
                savePrefData();
            }
        });
    }

    private boolean loadSharedPref() {
        boolean ret = false;

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        ret = sharedPreferences.getBoolean("isIntroOpened", false);
        return ret;
    }

    private void savePrefData() {
        SharedPreferences myPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.putString("date", Calendar.getInstance().getTime().toString());
        editor.commit();
    }

    private void loadLastScreen() {
        btnGetStarted.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);

        btnGetStarted.setAnimation(btnAnimation);
    }
}