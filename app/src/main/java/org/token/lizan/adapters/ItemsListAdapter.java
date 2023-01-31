package org.token.lizan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.token.lizan.R;
import org.token.lizan.databinding.ContentItemsBinding;
import org.token.lizan.models.ItemsModels;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.MyHolder>{

    private Context context;
    private List<ItemsModels> itemsModelsList;

    public ItemsListAdapter(Context context) {
        this.context = context;
        itemsModelsList = new ArrayList<>();
    }

    public void setItemsModelsList(List<ItemsModels> itemsModelsList) {
        this.itemsModelsList = itemsModelsList;
        notifyItemRangeChanged(0,itemsModelsList.size());
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_items,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemsModelsList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ContentItemsBinding binding;
        public MyHolder(@NonNull ContentItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
