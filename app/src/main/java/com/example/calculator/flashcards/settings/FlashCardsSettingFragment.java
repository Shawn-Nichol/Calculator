package com.example.calculator.flashcards.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentFlashCardsSettingBinding;


public class FlashCardsSettingFragment extends Fragment {

    private static final String TAG = "FlashCardsSettingFragment";

    FragmentFlashCardsSettingBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flash_cards_setting, container, false);

        SettingHandler handler = new SettingHandler(getContext());
        binding.setHandlers(handler);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
    }
}
