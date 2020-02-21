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
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;

import java.util.Objects;

public class RoomsFragment extends Fragment implements RoomsAdapter.OnItemListener {

    private static final long USFID = 1;
    private static final long UTAID = 2;
    private static final long WVUID = 3;
    private static final long RestonID = 4;

    RoomsWithSearchFragment searchFragment;
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
        searchFragment = (RoomsWithSearchFragment) getChildFragmentManager().findFragmentById(R.id.fragment_rooms_search_fragment);

        mBatchSelected = RoomsFragmentArgs.fromBundle(getArguments()).getBatchSelected();
        batchCampus = mBatchSelected.getBatch_name().substring(3,6);
        searchFragment.setCampusIDFilter(mBatchSelected.getCampus_id());

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
    public void onRoomClick(Room roomClicked) {
        RoomsFragmentDirections.ActionNavRoomsToNavRoomsInfo actionNavRoomsToNavRoomsInfo = RoomsFragmentDirections.actionNavRoomsToNavRoomsInfo(mBatchAssignment, roomClicked);
        actionNavRoomsToNavRoomsInfo.setDisplayButton(true);
        mNavController.navigate(actionNavRoomsToNavRoomsInfo);
    }

    private String setLocation(long campusID) {

        if (campusID == USFID) {
            campusImg.setImageResource(R.drawable.tampa);
            return "Tampa, FL";
        } else if (campusID == UTAID) {
            campusImg.setImageResource(R.drawable.dallas);
            return "Arlington, TX";
        } else if (campusID == RestonID) {
            campusImg.setImageResource(R.drawable.reston);
            return "Reston, VA";
        } else if (campusID == WVUID) {
            campusImg.setImageResource(R.drawable.morgantown);
            return "Morgantown, WVU";
        } else {
            return "N/A";
        }
    }

}