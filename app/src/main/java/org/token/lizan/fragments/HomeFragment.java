package org.token.lizan.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.token.lizan.R;
import org.token.lizan.adapters.HomeOptionsListAdapter;
import org.token.lizan.adapters.StoriesListAdapter;
import org.token.lizan.databinding.FragmentHistoryBinding;
import org.token.lizan.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);

//        HomeOptionsListAdapter adapter = new HomeOptionsListAdapter(getContext());
//        binding.listServices.setAdapter(adapter);

        StoriesListAdapter storiesListAdapter = new StoriesListAdapter(getContext());
        binding.listStories.setAdapter(storiesListAdapter);

        return binding.getRoot();
    }
}