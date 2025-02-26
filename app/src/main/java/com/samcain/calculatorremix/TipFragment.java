package com.samcain.calculatorremix;

import android.annotation.SuppressLint;
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
import com.samcain.calculatorremix.databinding.FragmentTipBinding;

/**
 * TipFragment class
 * Handles tip calculations based on bill amount, tip percentage, and number of people.
 */
public class TipFragment extends Fragment {
    private FragmentTipBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTipBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.calculateButton.setOnClickListener(v -> calculateTip());

        textWatcher();
        clearOnClick();
    }

    /**
     * Clears the opposite input field when the other input field is active.
     */
    private void textWatcher() {
        binding.billAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.resultTextView.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        binding.tipPercentageInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.resultTextView.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        binding.numPeopleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) binding.resultTextView.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Clears the input field when the other input field is active.
     */
    private void clearOnClick() {
        binding.billAmountInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.billAmountInput.getText().toString().isEmpty()) {
                binding.billAmountInput.setText("");
            }
        });
        binding.tipPercentageInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.tipPercentageInput.getText().toString().isEmpty()) {
                binding.tipPercentageInput.setText("");
            }
        });
        binding.numPeopleInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && !binding.numPeopleInput.getText().toString().isEmpty()) {
                binding.numPeopleInput.setText("");
            }
        });
    }

    /**
     * Calculates tip amount and total per person.
     */
    private void calculateTip() {
        String billText = binding.billAmountInput.getText().toString();
        String tipText = binding.tipPercentageInput.getText().toString();
        String peopleText = binding.numPeopleInput.getText().toString();

        // Check if all fields are filled
        if (!billText.isEmpty() && !tipText.isEmpty() && !peopleText.isEmpty()) {
            try {
                double billAmount = Double.parseDouble(billText);
                double tipPercentage = Double.parseDouble(tipText);
                int numPeople = Integer.parseInt(peopleText);

                // Validate input
                if (billAmount <= 0 || tipPercentage < 0 || numPeople <= 0) {
                    showToast("Values must be positive.");
                    return;
                }

                // Calculate tip amount, total amount, and per person
                double tipAmount = (billAmount * (tipPercentage / 100));
                double totalAmount = billAmount + tipAmount;
                double perPerson = totalAmount / numPeople;

                // Format and display the results
                binding.resultTextView.setText(String.format("Total: $%.2f\nTip: $%.2f\nPer Person: $%.2f",
                        totalAmount, tipAmount, perPerson));
            } catch (NumberFormatException e) {
                showToast("Invalid input. Please enter valid numbers.");
            }
        } else {
            showToast("Please fill in all fields.");
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
