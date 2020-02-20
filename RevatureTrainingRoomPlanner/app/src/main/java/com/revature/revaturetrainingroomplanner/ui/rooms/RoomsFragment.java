package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.util.Log;
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

    private BatchAssignment mBatchAssignment;
    private RoomsViewModel roomsViewModel;
    private NavController mNavController;
    private Batch mBatchSelected;

    TextView campus, location;
    ImageView campusImg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);
        View fragment = root.findViewById(R.id.fragment_rooms_search_fragment);

        mBatchSelected = RoomsFragmentArgs.fromBundle(getArguments()).getBatchSelected();
        String batchCampus = mBatchSelected.getBatch_name().substring(3,6);
        if(batchCampus.equals("Res"))
            batchCampus = "DC";

        campus = fragment.findViewById(R.id.tv_select_building_campus);
        location = fragment.findViewById(R.id.tv_select_building_campus_location);
        campusImg = fragment.findViewById(R.id.img_select_building_campus);
        campus.setText(batchCampus);
        location.setText(setLocation(batchCampus));

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

    private String setLocation(String campus) {

        switch (campus) {
            case "USF":
                campusImg.setImageResource(R.drawable.tampa);
                return "Tampa, FL";
            case "UTA":
                campusImg.setImageResource(R.drawable.dallas);
                return "Arlington, TX";
            case "DC":
                campusImg.setImageResource(R.drawable.reston);
                return "Reston, VA";
            case "WVU":
                campusImg.setImageResource(R.drawable.morgantown);
                return "Morgantown, WVU";
            default:
                return "N/A";
        }
    }

}