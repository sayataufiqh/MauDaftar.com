package com.example.yandi.maudaftar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoarding extends Activity {

    ViewPager viewPager;
    PagerAdapter adapter;
//    int[] flag;
    String[] flag;
    LinearLayout llDots;
    private TextView next, skip;
    int jumlah = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_act);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        flag = new int[] {
//                R.drawable.onboard1,
//                R.drawable.onboard1,
//                R.drawable.onboard1,
//                R.drawable.onboard1
//                };
        flag = new String[]{
                "gambar/onboard1.png",
                "gambar/onboard2.png",
                "gambar/onboard3.png"
        };

        llDots = (LinearLayout) findViewById(R.id.llDots);
        viewPager = (ViewPager) findViewById(R.id.pager);
        next = (TextView) findViewById(R.id.selanjutnya);
        skip = (TextView) findViewById(R.id.skip);

        adapter = new ViewPagerAdapter(this, flag);
        viewPager.setAdapter(adapter);

        for (int i = 0; i < adapter.getCount(); i++) {
            ImageButton imgDot = new ImageButton(this);
            imgDot.setTag(i);
            imgDot.setImageResource(R.drawable.dot_selector);
            imgDot.setBackgroundResource(0);
            imgDot.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            imgDot.setLayoutParams(params);
            if (i == 0) {
                imgDot.setSelected(true);
            }

            llDots.addView(imgDot);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                Log.e("", "Page Selected is ===> " + pos);
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (i != pos) {
                        ((ImageView) llDots.findViewWithTag(i)).setSelected(false);
                    }
                }
                ((ImageView) llDots.findViewWithTag(pos)).setSelected(true);
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
                if (pos == jumlah){
                    next.setText("Selesai");
                }
                else
                    next.setText("Selanjutnya");

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == jumlah) {
                    finishOnboarding();
                }
                else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishOnboarding();
            }
        });
    }

    private void finishOnboarding() {
        // Get the shared preferences
        SharedPreferences preferences =
                getSharedPreferences("my_preferences", MODE_PRIVATE);

        // Set onboarding_complete to true
        preferences.edit()
                .putBoolean("onboarding_complete",true).apply();

        // Launch the menu_main Activity, called Act_Beranda
        Intent main = new Intent(this, Act_Beranda.class);
        main.putExtra("dialog", "ya");
        startActivity(main);

        // Close the OnboardingActivity
        finish();
    }
}
