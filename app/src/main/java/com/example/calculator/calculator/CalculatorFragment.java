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
import android.widget.TextView;

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










    /**
     * Does basic math.
     */
//    private void equals() {
//
//        // Converts string into a double
//        double numberOne = Double.parseDouble(viewModel.getNumberOne());
//        double numberTwo = Double.parseDouble(viewModel.getNumberTwo());
//        String symbol = viewModel.getSavedSymbol();
//
//        // Persistable set of key/value pairs which are used as the input for workers.
//        Data equationData = new Data.Builder()
//                .putDouble(KEY_NUMBER_ONE, numberOne)
//                .putDouble(KEY_NUMBER_TWO, numberTwo)
//                .putString(KEY_SYMBOL, symbol)
//                .build();
//
//        // Creates a single work request to be completed on a background thread.
//        OneTimeWorkRequest mathWork = new OneTimeWorkRequest.Builder(MathWorker.class)
//                .setInputData(equationData) // Adds input data to the work.
//                .build(); // Builds work request
//
//        // Enqueues deferrable work that is guaranteed to execute sometime after its constraints are met.
//        WorkManager.getInstance(getActivity()).enqueue(mathWork);
//
//        // Gets results of Work after it is finished.
//        WorkManager.getInstance(getActivity()).getWorkInfoByIdLiveData(mathWork.getId())
//                .observe(getActivity(), workInfo -> {
//                    if(workInfo.getState() == WorkInfo.State.SUCCEEDED) {
//                        Double result = workInfo.getOutputData().getDouble(KEY_RESULT, NaN);
//                        Log.d(TAG, "equals: " + result);
//
//                        viewModel.setSolution(result);
//
//                        formula.setVisibility(View.VISIBLE);
//                        formula.setText(viewModel.getSavedFormula() + " = " + viewModel.getSolution());
//                        solution.setText(String.valueOf(result));
//                    }
//                });
//    }





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
