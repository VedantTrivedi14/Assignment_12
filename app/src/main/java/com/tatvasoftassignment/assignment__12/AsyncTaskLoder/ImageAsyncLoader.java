package com.tatvasoftassignment.assignment__12.AsyncTaskLoder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class ImageAsyncLoader extends AsyncTaskLoader<String> {
    public ImageAsyncLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return "https://images.hindustantimes.com/rf/image_size_960x540/HT/p2/2020/02/07/Pictures/_66d32462-4973-11ea-8b8c-fba542a06006.jpg";
    }
}