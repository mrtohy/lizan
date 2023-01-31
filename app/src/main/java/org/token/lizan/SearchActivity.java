package org.token.lizan;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import org.token.lizan.databinding.ActivitySearchBinding;
import org.token.lizan.models.HistoryModel;
import org.token.lizan.viewModels.SearchViewModel;

import java.util.Date;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_search);

        SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        binding.back.setOnClickListener(v -> {
            String a = binding.searchField.getText().toString();
            if (a.isEmpty()){
                finish();
            }else {
                binding.searchField.setText("");
            }
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        NavController navController = Navigation.findNavController(this,R.id.fragmentContainerSearch);

        binding.searchField.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                String a = binding.searchField.getText().toString();
                HistoryModel h = new HistoryModel(0,a,0,new Date().toString());
                viewModel.insert(h);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                navController.navigateUp();
                navController.navigate(R.id.to_search_item);
            }
            return false;
        });
        binding.search.setOnClickListener(v -> {
            String a = binding.searchField.getText().toString();
            HistoryModel h = new HistoryModel(0,a,0,new Date().toString());
            viewModel.insert(h);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            navController.navigateUp();
            navController.navigate(R.id.to_search_item);

        });
    }
}
