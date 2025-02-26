package com.samcain.calculatorremix;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.samcain.calculatorremix.databinding.ActivityMainBinding;

/**
 * MainActivity class
 * Handles TabLayout and ViewPager2 to switch between calculator fragments.
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Set up the activity
        super.onCreate(savedInstanceState);
        // Initialize View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
