package com.samcain.calculatorremix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayoutMediator;
import com.samcain.calculatorremix.databinding.TabContainerBinding;

/**
 * TabLayoutContainer class
 * Manages TabLayout and ViewPager2 for switching between fragments.
 */
public class TabLayoutContainer extends Fragment {
    private TabContainerBinding binding;

    /**
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TabContainerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up ViewPager2 and Adapter
        TabLayoutAdapter adapter = new TabLayoutAdapter(this);
        binding.viewPager.setAdapter(adapter);

        // Link TabLayout with ViewPager
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Temperature");
                    break;
                case 1:
                    tab.setText("Tip");
                    break;
                case 2:
                    tab.setText("Distance");
                    break;
            }
        }).attach();
    }
}
