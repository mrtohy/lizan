package org.token.lizan.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.parse.ParseUser;

import org.token.lizan.LoginActivity;
import org.token.lizan.R;
import org.token.lizan.databinding.DialogMenuBinding;

public class MenuDialog extends Dialog {
    public MenuDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public MenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    private void init(){
        DialogMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_menu,null,false);
        setContentView(binding.getRoot());

        Window w = getWindow();
        w.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.login.setOnClickListener(v -> getContext().startActivity(new Intent(getContext(), LoginActivity.class)));
    }
}
