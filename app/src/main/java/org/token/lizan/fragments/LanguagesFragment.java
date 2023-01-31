package org.token.lizan.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.token.lizan.R;
import org.token.lizan.adapters.LanguageListAdapter;
import org.token.lizan.databinding.FragmentLanguagesBinding;

public class LanguagesFragment extends BottomSheetDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentLanguagesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_languages, container, false);

        LanguageListAdapter adapter = new LanguageListAdapter(getContext());
        adapter.setOnSelectLanguage(lanTag -> {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(lanTag));
            dismiss();
        });
        binding.listLanguage.setAdapter(adapter);

        return binding.getRoot();
    }

}