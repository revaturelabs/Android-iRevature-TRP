package com.revature.revaturetrainingroomplanner.ui.campuses;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class CampusesFragment extends Fragment implements CampusesAdapter.OnItemListener {

    private NavController mNavController;
    MyViewModel model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_campuses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
    }

    @Override
    public void onCampusClick(Campus campusClicked) {
        model.select(campusClicked.getCampus_name());
        CampusesFragmentDirections.ActionCampusesFragmentToNavLookup actionCampusesFragmentToNavLookup = CampusesFragmentDirections.actionCampusesFragmentToNavLookup(campusClicked);
        mNavController.navigate(actionCampusesFragmentToNavLookup);
    }

}
