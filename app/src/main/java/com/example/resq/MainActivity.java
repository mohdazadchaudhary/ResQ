package com.example.resq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 imageSlider;
    private final Handler sliderHandler = new Handler();
    private final int[] images = {
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSlider = findViewById(R.id.imageSlider);
        imageSlider.setAdapter(new ImageSliderAdapter(images));

        imageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        imageSlider.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sliderHandler.removeCallbacks(sliderRunnable);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sliderHandler.postDelayed(sliderRunnable, 3000);
                    break;
            }
            return false;
        });

        setClick(R.id.btnPolice, "100");
        setClick(R.id.btnFire, "101");
        setClick(R.id.btnMed, "102");
        setClick(R.id.btnWomen, "1091");
        setClick(R.id.btnAll, "112");
        setClick(R.id.btnDisaster, "108");
    }

    private void setClick(int id, String number) {
        ImageButton btn = findViewById(id);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, CallActivity.class);
            intent.putExtra("number", number);
            startActivity(intent);
        });
    }

    private final Runnable sliderRunnable = () -> {
        int next = (imageSlider.getCurrentItem() + 1) % images.length;
        imageSlider.setCurrentItem(next, true);
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }
}
