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
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;


public class NumberPickerFragment extends AppCompatDialogFragment {
    private static final String TAG = "Calculator NumberPickerFragment";

    NumberPickerListener numberPickerListener;
    NumberPicker numberPicker;

    FlashCardsSettingViewModel mViewModel;
    Context mContext;



    public NumberPickerFragment(FlashCardsSettingViewModel mViewModel, Context context) {
        this.mViewModel = mViewModel;
        this.mContext = context;
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
                        numberPickerListener.selectedNumber(numberPicker.getValue());

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


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            numberPickerListener = (NumberPickerListener)mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface NumberPickerListener {
        void selectedNumber(int number);
    }
}
