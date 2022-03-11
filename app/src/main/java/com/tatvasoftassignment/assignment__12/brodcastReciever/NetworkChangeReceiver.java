package com.tatvasoftassignment.assignment__12.brodcastReciever;

import static com.tatvasoftassignment.assignment__12.fragment.AsyncTaskLoaderFragment.binding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.tatvasoftassignment.assignment__12.AsyncTaskLoder.ImageAsyncLoader;
import com.tatvasoftassignment.assignment__12.R;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            binding.txtAsyncDummyText.setVisibility(View.VISIBLE);
            binding.imgImage.setVisibility(View.GONE);
        } else {
            Toast.makeText(context, R.string.message, Toast.LENGTH_SHORT).show();
            binding.imgImage.setVisibility(View.VISIBLE);
            binding.txtAsyncDummyText.setVisibility(View.GONE);
            new ImageAsyncLoader(context);
        }
    }
}