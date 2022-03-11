package com.tatvasoftassignment.assignment__12.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tatvasoftassignment.assignment__12.AsyncTaskLoder.ImageAsyncLoader;
import com.tatvasoftassignment.assignment__12.R;
import com.tatvasoftassignment.assignment__12.Service.NetworkChangeService;
import com.tatvasoftassignment.assignment__12.databinding.FragmentAsyncTaskLoaderBinding;

import java.util.Random;


public class AsyncTaskLoaderFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    public static FragmentAsyncTaskLoaderBinding binding;
    private LoaderManager manager;
    private Intent intent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAsyncTaskLoaderBinding.inflate(inflater, container, false);
        manager = LoaderManager.getInstance(this);
        binding.btnLoad.setOnClickListener(view1 -> {
            intent = new Intent(requireContext(), NetworkChangeService.class);
            requireContext().startService(intent);

            manager.initLoader(new Random().nextInt(100), null, this).forceLoad();
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireContext().stopService(intent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new ImageAsyncLoader(requireActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_android)
                .error(R.drawable.ic_android);
        Glide.with(requireContext()).load(data).apply(options).into(binding.imgImage);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }

}
