package org.token.lizan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.token.lizan.R;
import org.token.lizan.databinding.ContentIntroSliderBinding;

public class IntroSliderAdapter extends RecyclerView.Adapter<IntroSliderAdapter.MyHolder>{

    private Context context;
    private Integer[] images;
    private String[] descriptions;

    public IntroSliderAdapter(Context context) {
        this.context = context;
        images = new Integer[]{R.drawable.directions_rafiki,R.drawable.tour_guide_rafiki,R.drawable.marketplace_rafiki};
        descriptions = context.getResources().getStringArray(R.array.intro_slider_descriptions);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentIntroSliderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.content_intro_slider,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(context).load(images[position]).into(holder.binding.picture);
        holder.binding.description.setText(descriptions[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ContentIntroSliderBinding binding;
        public MyHolder(@NonNull ContentIntroSliderBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
