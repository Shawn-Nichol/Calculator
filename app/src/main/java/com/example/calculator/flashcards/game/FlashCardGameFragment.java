package com.example.calculator.flashcards.game;

import android.content.Context;
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
import com.example.calculator.databinding.FragmentFlashCardsGameBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardGameFragment extends Fragment {

    private static final String TAG = "Calculator FlashCardGameFragment";

    Context mContext;
    FragmentFlashCardsGameBinding mBinding;
    FlashCardsGameViewModel mViewModel;

    boolean mAddition;
    boolean mMinus;
    boolean mMultiple;
    boolean mDivide;


    public FlashCardGameFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_cards_game, container, false);
        mContext = getContext(); // Get Context of view
        initViewModel();
        GameHandler handler = new GameHandler(mContext, mViewModel);
        mBinding.setHandlers(handler);

        View view= mBinding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FlashCardGameFragmentArgs args = FlashCardGameFragmentArgs.fromBundle(getArguments());
        mAddition = args.getAddition();
        mMinus = args.getMinus();
        mMultiple = args.getMultiple();
        mDivide = args.getDivide();
        Log.d(TAG, "onStart: " +
                "\n Addition " + mAddition +
                "\n Minus " + mMinus +
                "\n Multiple " + mMultiple +
                "\n Divide " + mDivide);

        mViewModel.setNumberOfQuestions(10);

        if(savedInstanceState == null) {
            initFlashCards();
        }
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(FlashCardsGameViewModel.class);
    }

    private void initFlashCards() {

        if(mAddition) mViewModel.addArithmeticType(" + ");
        if(mMinus) mViewModel.addArithmeticType(" - " );
        if(mMultiple) mViewModel.addArithmeticType(" X ");
        if(mDivide) mViewModel.addArithmeticType(" / ");

        for(int i =0; i < mViewModel.getNumberOfQuestions(); i++) {
            Random random = new Random();
            int minNum = 0;
            int maxNum = 10;

            int arithmetic = random.ints(0, mViewModel.getArithmeticType().size()).findFirst().getAsInt();

            mViewModel.addArithmeticSelected(mViewModel.getArithmeticType().get(arithmetic));

            int numOne = random.ints(minNum, maxNum).findFirst().getAsInt();

            if(mViewModel.getArithmeticSelected().get(i).equals(" / ") || mViewModel.getArithmeticSelected().get(i).equals(" - ")) {
                maxNum = numOne;
                if(maxNum == minNum ) maxNum = minNum + 1;
            }

            int numTwo = random.ints(minNum, maxNum).findFirst().getAsInt();

            switch(mViewModel.getArithmeticSelected().get(i)) {
                case " + ":
                    mViewModel.addAnswer(numOne + numTwo);
                    break;
                case " - ":
                    mViewModel.addAnswer(numOne - numTwo);
                    break;
                case " X ":
                    mViewModel.addAnswer(numOne * numTwo);
                    break;
                case " / ":
                    if(numOne == 0 || numTwo == 0) {
                        mViewModel.addAnswer(0);
                    } else {
                        mViewModel.addAnswer(numOne / numTwo);
                    }
                    break;
            }
            mViewModel.addFormula(numOne + mViewModel.getArithmeticSelected().get(i) +  numTwo);

            Log.d(TAG, "initFlashCards: formula " + mViewModel.getFormula().get(i) + " = " + mViewModel.getAnswer().get(i));

        }

    }
}
