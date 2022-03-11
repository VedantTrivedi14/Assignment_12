package com.tatvasoftassignment.assignment__12.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tatvasoftassignment.assignment__12.Adapter.ViewPagerAdapter;
import com.tatvasoftassignment.assignment__12.R;
import com.tatvasoftassignment.assignment__12.databinding.ActivityMainBinding;
import com.tatvasoftassignment.assignment__12.fragment.AsyncTaskLoaderFragment;
import com.tatvasoftassignment.assignment__12.fragment.CursorLoaderFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tabLayout.setupWithViewPager(binding.viewPager);
        ViewPagerAdapter viewPager = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.addFragment(new AsyncTaskLoaderFragment(), getString(R.string.AsyncTaskLoader));
        viewPager.addFragment(new CursorLoaderFragment(), getString(R.string.CursorLoader));
        binding.viewPager.setAdapter(viewPager);


    }

}