package com.example.calculator.flashcards.settings;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentFlashCardsSettingBinding;


public class FlashCardsSettingFragment extends Fragment {

    private static final String TAG = "Calculator FlashCardsSettingFragment";

    private FragmentFlashCardsSettingBinding mBinding;
    private Context mContext;
    FlashCardsSettingViewModel mViewModel;

    private SettingHandler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_cards_setting, container, false);

        mContext = getActivity();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        initViewModel();


        mHandler = new SettingHandler(mContext, mViewModel, mBinding, fm);

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
