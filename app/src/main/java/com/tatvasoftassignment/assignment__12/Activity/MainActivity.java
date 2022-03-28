package com.tatvasoftassignment.assignment__12.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.tatvasoftassignment.assignment__12.Adapter.ViewPagerAdapter;
import com.tatvasoftassignment.assignment__12.R;
import com.tatvasoftassignment.assignment__12.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this,this);
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {

            if (position == 0) {
                tab.setText(getString(R.string.CursorLoader));
            } else if (position == 1) {
                tab.setText(getString(R.string.AsyncTaskLoader));
            }
        }).attach();

    }

}