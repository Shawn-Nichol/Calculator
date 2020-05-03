package com.example.calculator.flashcards.game;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.R;
import com.example.calculator.databinding.FragmentFcGameBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardGameFragment extends Fragment {

    Context mContext;
    FragmentFcGameBinding binding;

    public FlashCardGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fc_game, container, false);
        mContext = getActivity();

        GameHandler handler = new GameHandler(mContext);
        binding.setHandlers(handler);

        View view= binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
