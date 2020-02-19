package com.revature.revaturetrainingroomplanner.ui.lookup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesWithSearchFragmentDirections;
import com.revature.revaturetrainingroomplanner.ui.rooms.RoomsWithSearchFragmentDirections;
import com.revature.revaturetrainingroomplanner.ui.trainers.TrainersWithSearchFragmentDirections;

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
    private int mCurrentTab = 0;

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
                 switch(tab.getPosition()) {
                     case TRAINER_TAB_LOCATION: {
                         if (mCurrentTab == BATCH_TAB_LOCATION) {
                             mSearchNavController.navigate(BatchesWithSearchFragmentDirections.actionNavLookupBatchesToNavLookupTrainers());
                         } else if (mCurrentTab == ROOM_TAB_LOCATION) {
                             mSearchNavController.navigate(RoomsWithSearchFragmentDirections.actionNavLookupRoomsToNavLookupTrainers());
                         }
                         mCurrentTab = 0;
                     }
                     break;

                     case BATCH_TAB_LOCATION: {
                         if (mCurrentTab == TRAINER_TAB_LOCATION) {
                             mSearchNavController.navigate(TrainersWithSearchFragmentDirections.actionNavLookupTrainersToNavLookupBatches());
                         } else if (mCurrentTab == ROOM_TAB_LOCATION) {
                             mSearchNavController.navigate(RoomsWithSearchFragmentDirections.actionNavLookupRoomsToNavLookupBatches());
                         }
                         mCurrentTab = 1;
                     }
                     break;

                     case ROOM_TAB_LOCATION: {
                         if (mCurrentTab == TRAINER_TAB_LOCATION) {
                             mSearchNavController.navigate(TrainersWithSearchFragmentDirections.actionNavLookupTrainersToNavLookupRooms());
                         } else if (mCurrentTab == BATCH_TAB_LOCATION) {
                             mSearchNavController.navigate(BatchesWithSearchFragmentDirections.actionNavLookupBatchesToNavLookupRooms());
                         }
                         mCurrentTab = 2;
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
    public void onTrainerClick(Trainer trainerClicked) {
        LookupFragmentDirections.ActionNavCategoryLookupToNavTrainerInfo actionNavLookupToNavTrainerInfo = LookupFragmentDirections.actionNavCategoryLookupToNavTrainerInfo(trainerClicked);
        mMainNavController.navigate(actionNavLookupToNavTrainerInfo);
    }

    @Override
    public void onBatchClick(Batch batch) {
        LookupFragmentDirections.ActionNavCategoryLookupToNavBatchInfo actionNavLookupToNavBatchInfo = LookupFragmentDirections.actionNavCategoryLookupToNavBatchInfo(batch);
        mMainNavController.navigate(actionNavLookupToNavBatchInfo);
        mTabLayout.selectTab(mTabLayout.getTabAt(TRAINER_TAB_LOCATION));
    }

    @Override
    public void onRoomClick(Room roomClicked) {
        LookupFragmentDirections.ActionNavCategoryLookupToNavRoomInfo actionNavCategoryLookupToNavRoomInfo = LookupFragmentDirections.actionNavCategoryLookupToNavRoomInfo(roomClicked);
        mMainNavController.navigate(actionNavCategoryLookupToNavRoomInfo);
        mTabLayout.selectTab(mTabLayout.getTabAt(TRAINER_TAB_LOCATION));
    }

}
