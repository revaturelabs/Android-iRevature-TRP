package com.revature.revaturetrainingroomplanner.ui.campuses;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesFragmentDirections;


/**
 * A simple {@link Fragment} subclass.
 */
public class CampusesFragment extends Fragment implements CampusesAdapter.OnItemListener, View.OnClickListener {

    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_campuses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onCampusClick(int position) {
//        CampusesFragmentDirections.actionCampusesFragmentToNavLookup();
        Log.d("debug", "on campus click");
        mNavController.navigate(CampusesFragmentDirections.actionCampusesFragmentToNavLookup());
    }


    @Override
    public void onClick(View v) {
        mNavController.navigate(CampusesFragmentDirections.actionCampusesFragmentToNavLookup());
    }
}
