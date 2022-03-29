package com.tatvasoftassignment.assignment__12.Adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tatvasoftassignment.assignment__12.fragment.AsyncTaskLoaderFragment;
import com.tatvasoftassignment.assignment__12.fragment.CursorLoaderFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    Context context;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new CursorLoaderFragment(context);
        }
        return new AsyncTaskLoaderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
