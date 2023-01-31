package org.token.lizan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.token.lizan.databinding.ActivityMainBinding;
import org.token.lizan.dialogs.MenuDialog;
import org.token.lizan.fragments.LanguagesFragment;
import org.token.lizan.utils.DataManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataManager dataManager = new DataManager(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.appBar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(intent);
        });

        binding.menu.setOnClickListener(v -> {
            MenuDialog menuDialog = new MenuDialog(MainActivity.this);
            menuDialog.show();
        });

        if (dataManager.isFirstSetLanguage()){
            LanguagesFragment languagesFragment = new LanguagesFragment();
            languagesFragment.setCancelable(false);
            languagesFragment.show(getSupportFragmentManager(), getClass().getSimpleName());
            dataManager.setFirstSetLanguage(false);
        }
    }
}