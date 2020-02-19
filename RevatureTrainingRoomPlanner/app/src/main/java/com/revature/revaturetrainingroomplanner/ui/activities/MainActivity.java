package com.revature.revaturetrainingroomplanner.ui.activities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.revature.revaturetrainingroomplanner.NavigationMainDirections;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BatchRepository;
import com.revature.revaturetrainingroomplanner.ui.login.SaveSharedPreference;
import com.revature.revaturetrainingroomplanner.util.KeyboardUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final int LOGOUT_MENU_LOCATION = 3;

    private BatchRepository mBatchRepository;
    private AppCompatActivity appCompatActivity;
    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView mNavigationView;
    SaveSharedPreference saveSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        saveSharedPreference = new SaveSharedPreference(this);
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

        hideKeyboardBetweenDestinations();

        // call requires API level 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkConnection();
        }

//        navController.navigate(R.id.nav_batches);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBatchRepository = new BatchRepository(this);
        mBatchRepository.retrieveBatchesFromAPI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        KeyboardUtil.hideSoftKeyboard(this);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void logoutSetup() {
        mNavigationView.getMenu().getItem(LOGOUT_MENU_LOCATION).setOnMenuItemClickListener(item -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Are you sure you want to logout?");
            builder.setPositiveButton("NO", (dialog, which) -> { });
            builder.setNegativeButton("Yes",
                    (dialog, which) -> {
                        saveSharedPreference.saveLoginDetails("","");
                        Log.d("debug", "deleting login info");
                        mNavController.navigate(NavigationMainDirections.actionGlobalNavLoginActivity());
                        finish();
                    });

            AlertDialog dialog = builder.create();
            dialog.show();


            return false;
        });
    }

    public void hideKeyboardBetweenDestinations() {
        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            KeyboardUtil.hideSoftKeyboard(appCompatActivity);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkConnection() {
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            Toast.makeText(this, "CONNECTED TO INTERNET!!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "NOOOO INTERNET!!", Toast.LENGTH_LONG).show();
        }

// NOT DEPRECATED BUT REQUIRES Higher API lvl
//        assert cm != null;
//        Network nw = cm.getActiveNetwork();
//        NetworkCapabilities actNw = cm.getNetworkCapabilities(nw);
//
//        assert actNw != null;
//        if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
//            Toast.makeText(this, "CONNECTED TO WIFI!!", Toast.LENGTH_LONG).show();
//        }
//        else if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
//            Toast.makeText(this, "CONNECTED TO CELLULAR DATA!!", Toast.LENGTH_LONG).show();
//        }


    }

}
