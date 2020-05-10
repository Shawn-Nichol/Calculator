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


public class NumberOfQuestionsDialog extends DialogFragment {
    private static final String TAG = "Calculator NumberPickerFragment";


    private NumberPicker picker;

    private FlashCardsSettingViewModel mViewModel;
    private Context mContext;


    /**
     * Constructor
     * @param mViewModel
     * @param context
     */
    NumberOfQuestionsDialog(FlashCardsSettingViewModel mViewModel, Context context) {
        Log.d(TAG, "NumberPickerFragment: constructor ");
        this.mViewModel = mViewModel;
        this.mContext = context;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");

        picker = new NumberPicker(getActivity());
        picker.setMinValue(5);
        picker.setMaxValue(20);
        picker.setValue(mViewModel.getNumberOfQuestions());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select Number of Questions")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: Postive " + picker.getValue());
                        mViewModel.setNumberOfQuestions(picker.getValue());


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: onClickNegative");

                    }
                });


        builder.setView(picker);
        return builder.create();

    }




}
