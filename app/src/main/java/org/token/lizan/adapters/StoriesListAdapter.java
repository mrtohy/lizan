package org.token.lizan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.token.lizan.R;
import org.token.lizan.databinding.ContentStoryBinding;

public class StoriesListAdapter extends RecyclerView.Adapter<StoriesListAdapter.MyHolder>{

    private Context context;
    private Integer[] stories;
    private String[] titles;

    public StoriesListAdapter(Context context) {
        this.context = context;
        stories = new Integer[]{R.drawable.stethoscope,R.drawable.restaurant,R.drawable.local_taxi,R.drawable.hotel};
        titles = context.getResources().getStringArray(R.array.home_options_title);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentStoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.content_story,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.title.setText(titles[position]);
        Glide.with(context).load(stories[position]).into(holder.binding.image);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ContentStoryBinding binding;
        public MyHolder(@NonNull ContentStoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.title.setSelected(true);
        }
    }
}
