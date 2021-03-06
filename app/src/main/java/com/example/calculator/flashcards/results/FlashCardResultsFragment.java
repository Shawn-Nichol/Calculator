package com.example.calculator.flashcards.results;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentFlashCardResultsBinding;
import com.example.calculator.flashcards.game.FlashCardGameFragmentArgs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardResultsFragment extends Fragment {

    Context mContext;
    FragmentFlashCardResultsBinding binding;

    // Args
    int mCorrectAnswers;
    int mNumberOfQuestions;

    public FlashCardResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("FC Results");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_card_results, container, false);

        mContext = getActivity();
        getArgs();
        ResultsHandler handler = new ResultsHandler(mContext, mCorrectAnswers, mNumberOfQuestions);

        binding.setHandlers(handler);


        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getArgs() {
        FlashCardResultsFragmentArgs args = FlashCardResultsFragmentArgs.fromBundle(getArguments());
        mCorrectAnswers = args.getCorrecQuestions();
        mNumberOfQuestions = args.getNumberOfQuestions();
    }
}
