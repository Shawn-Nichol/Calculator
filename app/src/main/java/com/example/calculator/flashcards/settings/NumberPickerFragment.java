package com.example.calculator.flashcards.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.calculator.BR;

public class NumberPickerFragment extends DialogFragment {
    private static final String TAG = "Calculator NumberPickerFragment";


    NumberPicker numberPicker;
    FlashCardsSettingViewModel mViewModel;

    NumberPickerListener listener;

    public interface NumberPickerListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

    public NumberPickerFragment(FlashCardsSettingViewModel mViewModel) {
        this.mViewModel = mViewModel;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            Log.d(TAG, "onAttach: try");
            listener = (NumberPickerListener) getActivity();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            Log.d(TAG, "onAttach: fail");
            throw new ClassCastException(" must implement NumberPickerListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        numberPicker = new NumberPicker(getActivity());
        numberPicker.setMinValue(5);
        numberPicker.setMaxValue(20);
        numberPicker.setValue(mViewModel.getNumberOfQuestions());




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Number of Questions")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Postive " + numberPicker.getValue());
                        mViewModel.setNumberOfQuestions(numberPicker.getValue());
                        listener.onDialogPositiveClick(NumberPickerFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: onClickNegative");

                    }
                });


        builder.setView(numberPicker);
        return builder.create();

    }




}
