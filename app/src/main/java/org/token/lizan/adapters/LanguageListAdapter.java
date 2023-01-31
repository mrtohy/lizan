package org.token.lizan.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.token.lizan.R;
import org.token.lizan.databinding.ContentLanguageBinding;
import org.token.lizan.intefaces.OnSelectLanguage;


public class LanguageListAdapter extends RecyclerView.Adapter<LanguageListAdapter.MyHolder> {

    private final Context context;
    private final String[] languages;
    private final String[] codeLanguages;

    private OnSelectLanguage selectLanguage;

    public LanguageListAdapter(Context context) {
        this.context = context;
        languages = new String[]{"English","فارسی","kurdî"};
        codeLanguages = new String[]{"en", "fa","ku"};
    }

    public void setOnSelectLanguage(OnSelectLanguage listener) {
        this.selectLanguage = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentLanguageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.content_language, parent, false);
        return new MyHolder(binding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if (AppCompatDelegate.getApplicationLocales().toLanguageTags().equals(codeLanguages[position])) {

            holder.binding.txtLanguage.setTextColor(Color.WHITE);
            holder.binding.check.setVisibility(View.VISIBLE);
        }
        holder.binding.txtLanguage.setText(languages[position]);
        holder.itemView.setOnClickListener(v -> {
            if (selectLanguage != null) {
                selectLanguage.onSelect(codeLanguages[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return languages.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final ContentLanguageBinding binding;

        public MyHolder(@NonNull ContentLanguageBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
