package com.revature.revaturetrainingroomplanner.ui.lookup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
public class LookupFragment extends Fragment implements View.OnClickListener, CampusesAdapter.OnItemListener, TrainersAdapter.OnItemListener, RoomsAdapter.OnItemListener {

    private NavController mMainNavController;
    private NavController mSearchNavController;
    private TabLayout mTabLayout;
    private TabLayout.Tab trainersTab;
    private TabLayout.Tab roomsTab;
    private View mNavHost;
    LinearLayout mCampusContainer;
    LinearLayout mCategoriesContainer;

    public LookupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_lookup, container, false);

//        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mNavHost = root.findViewById(navhost_lookup_search_fragment);
        mTabLayout = root.findViewById(R.id.tablayout_lookup_categories);
        mSearchNavController = Navigation.findNavController(root.findViewById(navhost_lookup_search_fragment));
        mMainNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        mCampusContainer = root.findViewById(R.id.linearlayout_fragment_lookup_campus_container);
        mCategoriesContainer = root.findViewById(R.id.linearlayout_fragment_lookup_categories_container);

        mCategoriesContainer.setVisibility(View.GONE);

        mCampusContainer.setOnClickListener(this);
        mCategoriesContainer.setOnClickListener(this);

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

        LinearLayout linearLayout = root.findViewById(R.id.linearlayout_lookup_fragment);

//        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                linearLayout.getWindowVisibleDisplayFrame(r);
//                int screenHeight = linearLayout.getRootView().getHeight();
//                int keypadHeight = screenHeight - r.bottom;
//                if (keypadHeight > screenHeight * 0.3) {
//                    Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                    campusWithSearchFragment.setVisibility(View.GONE);
//                } else {
//                    Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//                    campusWithSearchFragment.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.linearlayout_fragment_lookup_campus_container: {
//                Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                mCategoriesContainer.setVisibility(View.GONE);
            }
            break;

            case R.id.linearlayout_fragment_lookup_categories_container: {
//                Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            }
            break;

            default:
        }

    }

    @Override
    public void onCampusClick(int position) {
        mCategoriesContainer.setVisibility(View.VISIBLE);
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
