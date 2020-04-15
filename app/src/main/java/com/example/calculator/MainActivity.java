package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import static com.example.calculator.Constants.TAG_FRAG_CALCUALOTR;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "CalculatorMainActivity";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        
        initFragment(savedInstanceState);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);


    }

    /**
     * Loads the calculator fragment.
     */
    private void initFragment(Bundle savedIinstanceState) {
        Log.d(TAG, "initFragment: ");

//        CalculatorFragment fragment;
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        if(savedIinstanceState == null) {
//            fragment = new CalculatorFragment();
//        } else {
//            fragment = (CalculatorFragment) fragmentManager.findFragmentByTag(TAG_FRAG_CALCUALOTR);
//        }
//
////        fragmentTransaction.replace(R.id.main_container, fragment, TAG_FRAG_CALCUALOTR);
////        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_calculator:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.calculatorScreen);
                break;
            case R.id.nav_fragment2:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_fragment2);
        }

        return false;
    }
}
