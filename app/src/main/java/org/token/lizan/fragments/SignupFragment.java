package org.token.lizan.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.token.lizan.R;
import org.token.lizan.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSignupBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_signup,container,false);
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = binding.userName.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                String passwordagain = binding.passwordagain.getText().toString();

                if (email.isEmpty()&&password.isEmpty()&&passwordagain.isEmpty()){
                    Toast.makeText(getContext(), R.string.err_empty_fields, Toast.LENGTH_SHORT).show();
                }else {
                    if (password.equals(passwordagain)){
                        ParseUser user = new ParseUser();
                        // Set the user's username and password, which can be obtained by a forms
                        user.setUsername(userName);
                        user.setEmail( email);
                        user.setPassword( password);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    assert getParentFragment() != null;
                                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.to_sign_in);
//                    showAlert("Successful Sign Up!", "Welcome" + "<Your username here>" +"!");
                                } else {
                                    ParseUser.logOut();
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            }
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                assert getParentFragment() != null;
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.to_sign_in);
            }
        });
        return binding.getRoot();
    }
}