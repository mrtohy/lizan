package org.token.lizan.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import org.token.lizan.R;
import org.token.lizan.databinding.ContentHistoryBinding;
import org.token.lizan.intefaces.OnHistoryItemClickListener;
import org.token.lizan.models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoriesListAdapter extends RecyclerView.Adapter<HistoriesListAdapter.MyHolder> {

    private final Context context;
    private List<HistoryModel> histories;

    private OnHistoryItemClickListener itemClickListener;

    public HistoriesListAdapter(Context context) {
        this.context = context;
        histories = new ArrayList<>();
    }

    public void setOnHistoryItemClickListener(OnHistoryItemClickListener listener) {
        itemClickListener = listener;
    }

    public List<HistoryModel> getHistories() {
        return histories;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setHistories(List<HistoryModel> list) {
        histories = list;
        notifyItemRangeChanged(0,list.size()+1);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentHistoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_history, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.setHistory(histories.get(position));

        holder.binding.history.setOnClickListener(view -> {
            if (itemClickListener != null)
                itemClickListener.onItemClick(histories.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ContentHistoryBinding binding;

        public MyHolder(@NonNull ContentHistoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
