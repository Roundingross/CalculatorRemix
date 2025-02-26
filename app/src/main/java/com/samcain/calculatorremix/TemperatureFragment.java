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

import com.samcain.calculatorremix.databinding.FragmentTemperatureBinding;

/**
 * TemperatureFragment class
 * Handles conversion between temperature units.
 */
public class TemperatureFragment extends Fragment {
    private FragmentTemperatureBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTemperatureBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.convertButton.setOnClickListener(v -> convertTemperature());

        textWatcher();
        clearOnClick();
    }

    /**
     * Clears the opposite input field when the other input field is active.
     */
    private void textWatcher() {
        binding.fahrenheitInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.celsiusInput.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        binding.celsiusInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.fahrenheitInput.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Clears the input field when the other input field is active.
     */
    private void clearOnClick() {
        binding.fahrenheitInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.fahrenheitInput.getText().toString().isEmpty()) {
                binding.fahrenheitInput.setText("");
            }
        });
        binding.celsiusInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.celsiusInput.getText().toString().isEmpty()) {
                binding.celsiusInput.setText("");
            }
        });
    }

    /**
     * Converts temperature based on user input.
     */
    private void convertTemperature() {
        String fahrenheitText = binding.fahrenheitInput.getText().toString();
        String celsiusText = binding.celsiusInput.getText().toString();

        if (!fahrenheitText.isEmpty() && celsiusText.isEmpty()) {
            convertFahrenheitToCelsius(fahrenheitText);
        } else if (fahrenheitText.isEmpty() && !celsiusText.isEmpty()) {
            convertCelsiusToFahrenheit(celsiusText);
        } else {
            showToast("Please enter a value for Fahrenheit or Celsius");
        }
    }


    /**
     * Converts Fahrenheit to Celsius.
     */
    private void convertFahrenheitToCelsius(String fahrenheitText) {
        try {
            double fahrenheit = Double.parseDouble(fahrenheitText);
            double celsius = (fahrenheit - 32) * 5 / 9;
            binding.celsiusInput.setText(String.format("%.2f", celsius));
            binding.resultTextView.setText(String.format("Result: %.2f°F is %.2f°C", fahrenheit, celsius));
        } catch (NumberFormatException e) {
            showToast("Invalid input for Fahrenheit");
        }
    }

    /**
     * Converts Celsius to Fahrenheit.
     */
    private void convertCelsiusToFahrenheit(String celsiusText) {
        try {
            double celsius = Double.parseDouble(celsiusText);
            double fahrenheit = (celsius * 9 / 5) + 32;
            binding.fahrenheitInput.setText(String.format("%.2f", fahrenheit));
            binding.resultTextView.setText(String.format("Result: %.2f°C is %.2f°F", celsius, fahrenheit));
        } catch (NumberFormatException e) {
            showToast("Invalid input for Celsius");
        }
    }

    /**
     * Shows a toast message.
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