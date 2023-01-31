package org.token.lizan.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.token.lizan.R;
import org.token.lizan.databinding.FragmentPasswordRecoveryBinding;

public class PasswordRecoveryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentPasswordRecoveryBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_password_recovery,container,false);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                assert getParentFragment() != null;
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.recovery_to_sign_in);
            }
        });
        return binding.getRoot();
    }


}