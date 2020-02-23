package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter;

import java.util.Objects;

public class RoomsFragment extends Fragment implements RoomsWithBatchAssignmentsAdapter.OnItemListener {

    private static final long USF_ID = 1;
    private static final long UTA_ID = 2;
    private static final long WVU_ID = 3;
    private static final long Reston_ID = 4;

    RoomsWithSearchFragment mSearchFragment;
    private BatchAssignment mBatchAssignment;
    private RoomsViewModel roomsViewModel;
    private NavController mNavController;
    private Batch mBatchSelected;
    private String batchCampus;
    private TextView campus, location;
    private ImageView campusImg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);
        View fragment = root.findViewById(R.id.fragment_rooms_search_fragment);
        mSearchFragment = (RoomsWithSearchFragment) getChildFragmentManager().findFragmentById(R.id.fragment_rooms_search_fragment);

        mBatchSelected = RoomsFragmentArgs.fromBundle(getArguments()).getBatchSelected();
        batchCampus = mBatchSelected.getBatch_name().substring(3,6);
        mSearchFragment.setCampusIDFilter(mBatchSelected.getCampus_id());

        campus = fragment.findViewById(R.id.tv_select_building_campus);
        location = fragment.findViewById(R.id.tv_select_building_campus_location);
        campusImg = fragment.findViewById(R.id.img_select_building_campus);
        campus.setText(batchCampus);
        location.setText(setLocation(mBatchSelected.getCampus_id()));

        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBatchAssignment = RoomsFragmentArgs.fromBundle(getArguments()).getBatchAssignment();
    }

    @Override
    public void onStart() {
        super.onStart();
//        CampusRepository campusRepository = new CampusRepository(getContext());
//        campusRepository.retrieveCampusesFromAPI();
    }

    @Override
    public void onRoomClick(RoomWithBatchAssignments roomClicked) {
        RoomsFragmentDirections.ActionNavRoomsToNavRoomsInfo actionNavRoomsToNavRoomsInfo = RoomsFragmentDirections.actionNavRoomsToNavRoomsInfo(mBatchAssignment, roomClicked);
        actionNavRoomsToNavRoomsInfo.setDisplayButton(true);
        mNavController.navigate(actionNavRoomsToNavRoomsInfo);
    }

    private String setLocation(long campusID) {

        if (campusID == USF_ID) {
            campusImg.setImageResource(R.drawable.tampa);
            return "Tampa, FL";
        } else if (campusID == UTA_ID) {
            campusImg.setImageResource(R.drawable.dallas);
            return "Arlington, TX";
        } else if (campusID == Reston_ID) {
            campusImg.setImageResource(R.drawable.reston);
            return "Reston, VA";
        } else if (campusID == WVU_ID) {
            campusImg.setImageResource(R.drawable.morgantown);
            return "Morgantown, WVU";
        } else {
            return "N/A";
        }
    }

}