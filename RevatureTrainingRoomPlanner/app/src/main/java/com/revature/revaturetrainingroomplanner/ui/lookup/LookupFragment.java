package com.revature.revaturetrainingroomplanner.ui.lookup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesFragment;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesFragmentDirections;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesWithSearchFragment;

import java.util.Objects;

import static com.revature.revaturetrainingroomplanner.R.id.navhost_lookup_search_fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookupFragment extends Fragment implements TrainersAdapter.OnItemListener, BatchesAdapter.OnItemListener, RoomsAdapter.OnItemListener {

    private final int TRAINER_TAB_LOCATION = 0;
    private final int BATCH_TAB_LOCATION = 1;
    private final int ROOM_TAB_LOCATION = 2;

    private NavController mMainNavController;
    private NavController mSearchNavController;
    private TabLayout mTabLayout;
    private TabLayout.Tab mTrainersTab;
    private TabLayout.Tab mRoomsTab;
    private TabLayout.Tab mBatchesTab;
    private View mNavHost;

    public LookupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lookup, container, false);

        mNavHost = root.findViewById(navhost_lookup_search_fragment);
        mTabLayout = root.findViewById(R.id.tablayout_lookup_categories);
        mSearchNavController = Navigation.findNavController(root.findViewById(navhost_lookup_search_fragment));
        mMainNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        mTrainersTab = mTabLayout.newTab();
        mTrainersTab.setText(R.string.tab_trainers);

        mBatchesTab = mTabLayout.newTab();
        mBatchesTab.setText(R.string.tab_batches);

        mRoomsTab = mTabLayout.newTab();
        mRoomsTab.setText(R.string.tab_rooms);

        mTabLayout.addTab(mTrainersTab);
        mTabLayout.addTab(mBatchesTab);
        mTabLayout.addTab(mRoomsTab);

        if (savedInstanceState != null) {
            mTabLayout.selectTab(mTabLayout.getTabAt(savedInstanceState.getInt("last tab")));
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 switch(mTabLayout.getSelectedTabPosition()) {
                     case TRAINER_TAB_LOCATION: {
                         if (tab.getPosition() == BATCH_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_batchesFragment_to_trainersFragment);
                         } else if (tab.getPosition() == ROOM_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_roomsFragment_to_trainersFragment);
                         }
                     }
                     break;

                     case BATCH_TAB_LOCATION: {
                         if (tab.getPosition() == TRAINER_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_trainersFragment_to_batchesFragment);
                         } else if (tab.getPosition() == ROOM_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_roomsFragment_to_batchesFragment);
                         }
                     }
                     break;

                     case ROOM_TAB_LOCATION: {
                         if (tab.getPosition() == TRAINER_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_trainersFragment_to_roomsFragment);
                         } else if (tab.getPosition() == BATCH_TAB_LOCATION) {
                             mSearchNavController.navigate(R.id.action_batchesFragment_to_roomsFragment);
                         }
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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("last tab", mTabLayout.getSelectedTabPosition());
    }



    @Override
    public void onTrainerClick(int position) {
        LookupFragmentDirections.ActionNavLookupToNavTrainerInfo actionNavLookupToNavTrainerInfo = LookupFragmentDirections.actionNavLookupToNavTrainerInfo();
        mMainNavController.navigate(actionNavLookupToNavTrainerInfo);
    }

    @Override
    public void onBatchClick(int position) {
        LookupFragmentDirections.ActionNavLookupToNavBatchInfo actionNavLookupToNavBatchInfo = LookupFragmentDirections.actionNavLookupToNavBatchInfo();
        mMainNavController.navigate(actionNavLookupToNavBatchInfo);
    }

    @Override
    public void onRoomClick(int position) {
        LookupFragmentDirections.ActionNavLookupToNavRoomInfo actionNavLookupToNavRoomInfo = LookupFragmentDirections.actionNavLookupToNavRoomInfo();
        mMainNavController.navigate(actionNavLookupToNavRoomInfo);
    }

}
