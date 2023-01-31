package org.token.lizan.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.token.lizan.ProfileActivity;
import org.token.lizan.R;
import org.token.lizan.databinding.FragmentSigninBinding;

public class SigninFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentSigninBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.userName.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getContext(), R.string.err_empty_fields, Toast.LENGTH_SHORT).show();
                } else {

                    ParseUser user = new ParseUser();
                    // Set the user's username and password, which can be obtained by a forms
                    user.setUsername(userName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                startActivity(new Intent(getContext(), ProfileActivity.class));
//                    showAlert("Successful Sign Up!", "Welcome" + "<Your username here>" +"!");
                            } else {
                                ParseUser.logOut();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
        binding.signup.setOnClickListener(v -> {
            assert getParentFragment() != null;
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.to_sign_up);
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                assert getParentFragment() != null;
                getActivity().finish();
            }
        });
        binding.passwordRecovery.setOnClickListener(v -> {
            assert getParentFragment() != null;
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.to_password_recovery);
        });

        return binding.getRoot();
    }

}