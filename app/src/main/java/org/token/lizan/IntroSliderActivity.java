package org.token.lizan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.token.lizan.adapters.IntroSliderAdapter;
import org.token.lizan.databinding.ActivityIntroSliderBinding;
import org.token.lizan.utils.DataManager;

public class IntroSliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIntroSliderBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_intro_slider);

        DataManager d = new DataManager(this);
        if (!d.isFirstEntry()){
            toActivity(SplashScreenActivity.class);
        }

        IntroSliderAdapter adapter = new IntroSliderAdapter(this);

        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int i = binding.viewPager.getCurrentItem();

                if (i==0){
                    binding.back.setVisibility(View.GONE);
                }else{
                    binding.back.setVisibility(View.VISIBLE);
                }
                if (i==2){
                    binding.next.setText(R.string.inter);
                }else {
                    binding.next.setText(R.string.next);
                }
            }

        });
        binding.back.setOnClickListener(view -> {
            int i = binding.viewPager.getCurrentItem();
            if (i!=0){
                i--;
                binding.viewPager.setCurrentItem(i);
            }
        });
        binding.next.setOnClickListener(view -> {
            int i = binding.viewPager.getCurrentItem();
            if (i!=2){
                i++;
                binding.viewPager.setCurrentItem(i);
            }else {
                d.setFirstEntry(false);
                toActivity(MainActivity.class);

            }
        });
        binding.dotsIndicator.attachTo(binding.viewPager);
    }

    private void toActivity(Class<?> aClass){
        startActivity(new Intent(IntroSliderActivity.this,aClass));
        finish();
    }
}