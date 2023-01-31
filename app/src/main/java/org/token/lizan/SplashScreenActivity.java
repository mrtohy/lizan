package org.token.lizan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.window.SplashScreen;

import org.token.lizan.databinding.ActivitySplashScreenBinding;
import org.token.lizan.viewModels.SplashScreenViwModel;

public class SplashScreenActivity extends AppCompatActivity {

    private boolean check_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        SplashScreenViwModel viwModel = new ViewModelProvider(this).get(SplashScreenViwModel.class);


        binding.imageView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                viwModel.responseRequest().observe(SplashScreenActivity.this, throwable -> {

                    if (throwable == null) {
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                        finish();
                    }
                    if (throwable != null) {
                        if (App.getInstance().isCheckConnection()){
                            binding.txtAlert.setText(R.string.no_internet);
                        }else {
                            binding.txtAlert.setText(R.string.not_connection_server);
                        }
                    }
                });
            }
        });


        viwModel.retry().observe(this, aBoolean -> {
            if (aBoolean){
                binding.loader.setVisibility(View.GONE);
                binding.refresh.setVisibility(View.VISIBLE);
                check_refresh = true;
            }else {
                binding.loader.setVisibility(View.VISIBLE);
                binding.refresh.setVisibility(View.GONE);
                check_refresh = false;
            }
        });

        binding.refresh.setOnClickListener(v -> {
            if (check_refresh){
                binding.loader.setVisibility(View.VISIBLE);
                binding.refresh.setVisibility(View.GONE);
                binding.txtAlert.setText(R.string.please_wait);
                check_refresh = false;
                viwModel.sendRequest();
            }
        });
    }
}