package com.samcain.calculatorremix;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.samcain.calculatorremix.databinding.FragmentDistanceBinding;

/**
 * DistanceFragment class
 * Handles conversion between miles and kilometers.
 */
public class DistanceFragment extends Fragment {
    private FragmentDistanceBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDistanceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.convertButton.setOnClickListener(v -> convertDistance());

        textWatcher();
        clearOnClick();
    }

    /**
     * Clears the opposite input field when the other input field is active.
     */
    private void textWatcher() {
        binding.milesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.kilometersInput.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.kilometersInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.milesInput.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Clears the input field when the other input field is active.
     */
    private void clearOnClick() {
        binding.milesInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.milesInput.getText().toString().isEmpty()) {
                binding.milesInput.setText("");
            }
        });

        binding.kilometersInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.kilometersInput.getText().toString().isEmpty()) {
                binding.kilometersInput.setText("");
            }
        });
    }

    /**
     * Converts distance based on user input.
     */
    private void convertDistance() {
        String milesText = binding.milesInput.getText().toString();
        String kilometersText = binding.kilometersInput.getText().toString();

        if (!milesText.isEmpty() && kilometersText.isEmpty()) {
            convertMilesToKilometers(milesText);
        } else if (milesText.isEmpty() && !kilometersText.isEmpty()) {
            convertKilometersToMiles(kilometersText);
        } else {
            showToast("Please enter a value for Miles or Kilometers");
        }
    }

    /**
     * Converts Miles to Kilometers.
     * @param milesText the miles input text.
     */
    private void convertMilesToKilometers(String milesText) {
        try {
            double miles = Double.parseDouble(milesText);
            double kilometers = miles * 1.60934;
            binding.kilometersInput.setText(String.format("%.2f", kilometers));
            binding.resultTextView.setText(String.format("Result: %.2f miles is %.2f km.", miles, kilometers));
        } catch (NumberFormatException e) {
            showToast("Invalid input for Miles");
        }
    }

    /**
     * Converts Kilometers to Miles.
     * @param kilometersText the kilometers input text.
     */
    private void convertKilometersToMiles(String kilometersText) {
        try {
            double kilometers = Double.parseDouble(kilometersText);
            double miles = kilometers / 1.60934;
            binding.milesInput.setText(String.format("%.2f", miles));
            binding.resultTextView.setText(String.format("Result: %.2f km. is %.2f miles", kilometers, miles));
        } catch (NumberFormatException e) {
            showToast("Invalid input for Kilometers");
        }
    }

    /**
     * Shows a toast message.
     * @param message the message to display.
     */
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Destroys the binding object.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
