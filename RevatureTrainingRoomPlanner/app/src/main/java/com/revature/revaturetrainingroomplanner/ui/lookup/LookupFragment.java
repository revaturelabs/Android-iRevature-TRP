package com.revature.revaturetrainingroomplanner.ui.lookup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter;
import com.revature.revaturetrainingroomplanner.ui.rooms.RoomsWithSearchFragmentDirections;
import com.revature.revaturetrainingroomplanner.ui.trainers.TrainersWithSearchFragmentDirections;

import java.util.Objects;

import static com.revature.revaturetrainingroomplanner.R.id.navhost_lookup_search_fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookupFragment extends Fragment implements CampusesAdapter.OnItemListener, TrainersAdapter.OnItemListener, RoomsAdapter.OnItemListener {

    private NavController mMainNavController;
    private NavController mSearchNavController;
    private TabLayout mTabLayout;
    private TabLayout.Tab trainersTab;
    private TabLayout.Tab roomsTab;
    private View mNavHost;

    public LookupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lookup, container, false);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mNavHost = root.findViewById(navhost_lookup_search_fragment);
        mNavHost.setVisibility(View.GONE);
        mTabLayout = root.findViewById(R.id.tablayout_lookup_categories);
        mSearchNavController = Navigation.findNavController(root.findViewById(navhost_lookup_search_fragment));
        mMainNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        View campusWithSearchFragment = root.findViewById(R.id.fragment_lookup_campuses_with_search);

        trainersTab = mTabLayout.newTab();
        trainersTab.setText(R.string.menu_trainers);

        roomsTab = mTabLayout.newTab();
        roomsTab.setText(R.string.menu_rooms);

        mTabLayout.addTab(trainersTab);
        mTabLayout.addTab(roomsTab);

        if (savedInstanceState != null) {
            mTabLayout.selectTab(mTabLayout.getTabAt(savedInstanceState.getInt("last tab")));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 switch(mTabLayout.getSelectedTabPosition()) {
                     case 0: {
                         mSearchNavController.navigate(RoomsWithSearchFragmentDirections.actionLookupRoomsWithSearchToLookupTrainersWithSearch());
//                         savedInstanceState.putInt("last tab", mTabLayout.getSelectedTabPosition());
                     }
                     break;

                     case 1: {
                         mSearchNavController.navigate(TrainersWithSearchFragmentDirections.actionLookupTrainersWithSearchToLookupRoomsWithSearch());
//                         savedInstanceState.putInt("last tab", mTabLayout.getSelectedTabPosition());
                     }
                     break;
                 }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    @Override
    public void onCampusClick(int position) {
        mNavHost.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTrainerClick(int position) {
        LookupFragmentDirections.ActionNavLookupToNavTrainerInfo actionNavLookupToNavTrainerInfo = LookupFragmentDirections.actionNavLookupToNavTrainerInfo();
        actionNavLookupToNavTrainerInfo.setDisplayButton(false);
        mMainNavController.navigate(actionNavLookupToNavTrainerInfo);
    }

    @Override
    public void onRoomClick(int position) {
        mMainNavController.navigate(LookupFragmentDirections.actionNavLookupToNavRoomInfo());
        mTabLayout.selectTab(trainersTab);
    }

}
