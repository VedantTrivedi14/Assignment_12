package com.tatvasoftassignment.assignment__12.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tatvasoftassignment.assignment__12.fragment.AsyncTaskLoaderFragment;
import com.tatvasoftassignment.assignment__12.fragment.CursorLoaderFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new CursorLoaderFragment();
        }
        return new AsyncTaskLoaderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
