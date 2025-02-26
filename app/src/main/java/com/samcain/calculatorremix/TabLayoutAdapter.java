package com.samcain.calculatorremix;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * TabLayoutAdapter class
 * Provides fragments to ViewPager2.
 */
public class TabLayoutAdapter extends FragmentStateAdapter {

    /**
     * Constructor
     */
    public TabLayoutAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    /**
     * Creates a fragment based on position.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TemperatureFragment();
            case 1:
                return new TipFragment();
            case 2:
                return new DistanceFragment();
            default:
                throw new IllegalStateException("Invalid position: " + position);
        }
    }

    /**
     * Returns the number of fragments.
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
