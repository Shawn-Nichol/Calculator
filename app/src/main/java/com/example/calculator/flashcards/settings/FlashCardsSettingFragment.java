package com.example.calculator.flashcards.settings;

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
import com.example.calculator.databinding.FragmentFlashCardsSettingBinding;


public class FlashCardsSettingFragment extends Fragment {

    private static final String TAG = "Calculator FlashCardsSettingFragment";

    FragmentFlashCardsSettingBinding mBinding;
    Context mContext;
    FlashCardsSettingViewModel mViewModel;

    SettingHandler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_cards_setting, container, false);

        mContext = getActivity();
        initViewModel();

        mHandler = new SettingHandler(mContext, getActivity().getSupportFragmentManager(), mViewModel, mBinding);

        mBinding.setHandlers(mHandler);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(FlashCardsSettingViewModel.class);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        mHandler.saveSwitchState();
    }
}
