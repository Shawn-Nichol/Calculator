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
import android.widget.Button;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentCalculatorBinding;



public class CalculatorFragment extends Fragment {

    private static final String TAG = "CalculatorFragment";

    // Saved Instance state
    public static final String TAG_FORMULA = "formula";
    public static final String TAG_ANSWER = "answer";
    public static final String TAG_NUMBER = "number";


    // BindingData
    FragmentCalculatorBinding binding;

    private CalculatorViewModel viewModel;

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
            reloadData(savedInstanceState);
        }
    }

    private void initViewModel(){
        // Creates viewModel class, only done once.
        Log.d(TAG, "initViewModel: ");
        // get
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
    }

    private void reloadData(Bundle savedInstanceState) {
        double savedAnswer = savedInstanceState.getDouble(TAG_ANSWER);
        String savedFormula = savedInstanceState.getString(TAG_FORMULA);
        String savedNumber = savedInstanceState.getString(TAG_NUMBER);
        Log.d(TAG, "onViewCreated: load saved instance state " +
                "\n number: " + savedNumber +
                "\n formula: " + savedFormula +
                "\n answer: " + savedAnswer);

        viewModel.setNumber(savedNumber);
        viewModel.setFormula(savedFormula);
        viewModel.setAnswer(savedAnswer);
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: ");

        outState.putDouble(TAG_ANSWER, viewModel.getAnswer());
        outState.putString(TAG_FORMULA, viewModel.getFormula());
        outState.putString(TAG_NUMBER, viewModel.getNumber());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
