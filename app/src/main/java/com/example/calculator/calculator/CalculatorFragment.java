package com.example.calculator.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {

    private static final String TAG = "CalculatorFragment";

    // BindingData
    FragmentCalculatorBinding binding;

    private CalculatorViewModel viewModel;

    // Saved State
    static final String STATE_FORMULA = "savedFormula";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calculator, container, false);

        initViewModel();

        Handler handler = new Handler(getContext(), viewModel);
        binding.setHandlers(handler);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");

        if(savedInstanceState != null) {
            Log.d(TAG, "onViewCreated: load saved instance state " + savedInstanceState.getString(STATE_FORMULA));

        }
    }

    private void initViewModel(){
        // Creates viewModel class, only done once.
        Log.d(TAG, "initViewModel: ");
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
