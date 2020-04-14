package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import static com.example.calculator.Constants.TAG_FRAG_CALCUALOTR;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorMainActivity";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        
        initFragment(savedInstanceState);
    }

    /**
     * Loads the calculator fragment.
     */
    private void initFragment(Bundle savedIinstanceState) {
        Log.d(TAG, "initFragment: ");

        CalculatorFragment fragment;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if(savedIinstanceState == null) {
            fragment = new CalculatorFragment();
        } else {
            fragment = (CalculatorFragment) fragmentManager.findFragmentByTag(TAG_FRAG_CALCUALOTR);
        }

        fragmentTransaction.replace(R.id.main_container, fragment, TAG_FRAG_CALCUALOTR);
        fragmentTransaction.commit();
    }
}
