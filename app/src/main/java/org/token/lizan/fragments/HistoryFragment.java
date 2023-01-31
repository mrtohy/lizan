package org.token.lizan.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.token.lizan.R;
import org.token.lizan.adapters.HistoriesListAdapter;
import org.token.lizan.databinding.FragmentHistoryBinding;
import org.token.lizan.databinding.FragmentHomeBinding;
import org.token.lizan.intefaces.OnHistoryItemClickListener;
import org.token.lizan.models.HistoryModel;
import org.token.lizan.utils.Contacts;
import org.token.lizan.viewModels.HistoryViewModel;
import org.token.lizan.viewModels.SearchViewModel;

import java.util.List;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHistoryBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_history,container,false);

        SearchViewModel viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        HistoriesListAdapter adapter = new HistoriesListAdapter(getContext());
        binding.listHistory.setAdapter(adapter);

        viewModel.getHistories().observe(getViewLifecycleOwner(), historyModels -> {
            if (historyModels.size()>0) {
                adapter.setHistories(historyModels);
            }else {
                binding.titleHistory.setVisibility(View.GONE);
            }
        });

        adapter.setOnHistoryItemClickListener(historyModel -> {
            Bundle bundle = new Bundle();
            bundle.putString(Contacts.EXTRA_TEXT, historyModel.getText());
            assert getParentFragment() != null;
            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.to_search_item,bundle);
        });

        return binding.getRoot();
    }
}