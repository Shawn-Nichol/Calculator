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
import com.example.calculator.flashcards.settings.FlashCardsSettingViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardGameFragment extends Fragment {

    private static final String TAG = "Calculator FlashCardGameFragment";

    Context mContext;
    FragmentFlashCardsGameBinding mBinding;
    FlashCardsGameViewModel mViewModel;

    public FlashCardGameFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

        FlashCardGameFragmentArgs args = FlashCardGameFragmentArgs.fromBundle(getArguments());
        Boolean addition = args.getAddition();
        Boolean minus = args.getMinus();
        Boolean multiple = args.getMultiple();
        Boolean divide = args.getDivide();
        Log.d(TAG, "onStart: " +
                "\n Addition " + addition +
                "\n Minus " + minus +
                "\n Multiple " + multiple +
                "\n Divide " + divide);



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
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(FlashCardsGameViewModel.class);
    }
}
