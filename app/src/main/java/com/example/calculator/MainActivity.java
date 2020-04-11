package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorMainActivity";
    
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        
        initFragment();
    }

    /**
     * Loads the calculator fragment.
     */
    private void initFragment() {
        Log.d(TAG, "initFragment: ");
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        CalculatorFragment fragment = new CalculatorFragment();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }
}
