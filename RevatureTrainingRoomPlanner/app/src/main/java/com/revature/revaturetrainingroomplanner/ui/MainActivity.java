package com.revature.revaturetrainingroomplanner.ui;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.revature.revaturetrainingroomplanner.NavigationMainDirections;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.util.KeyboardUtil;

public class MainActivity extends AppCompatActivity {

    private AppCompatActivity appCompatActivity;
    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appCompatActivity = this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_batches, R.id.nav_lookup)
                .setDrawerLayout(drawer)
                .build();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mNavigationView, mNavController);

        logoutSetup();

        hideKeyboardWhenDestinationChanges();

//        navController.navigate(R.id.nav_batches);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        KeyboardUtil.hideKeyboard(this);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        View currentFragment = navHostFragment.getChildFragmentManager().getFragments().get(0).getView();
//        currentFragment.clearFocus();

        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void logoutSetup() {
        mNavigationView.getMenu().getItem(4).setOnMenuItemClickListener(item -> {
            mNavController.navigate(NavigationMainDirections.actionGlobalNavLoginActivity());
            return false;
        });
    }

    private void hideKeyboardWhenDestinationChanges() {
        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                KeyboardUtil.hideKeyboard(appCompatActivity);
            }
        });
    }

}
