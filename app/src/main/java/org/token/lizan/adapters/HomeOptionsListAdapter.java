package org.token.lizan.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.token.lizan.R;
import org.token.lizan.databinding.ContentHomeOptionsBinding;

public class HomeOptionsListAdapter extends RecyclerView.Adapter<HomeOptionsListAdapter.MyHolder>{

    private final Context context;
    private final String[] titles;
    private final Integer[] images;
    private Intent[] intents;

    public HomeOptionsListAdapter(Context context) {
        this.context = context;
        titles = context.getResources().getStringArray(R.array.home_options_title);
        images = new Integer[]{R.drawable.o1,R.drawable.o2,R.drawable.o3,R.drawable.o4};
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentHomeOptionsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.content_home_options,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(context).load(images[position]).into(holder.binding.image);
        holder.binding.title.setText(titles[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ContentHomeOptionsBinding binding;
        public MyHolder(@NonNull ContentHomeOptionsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
