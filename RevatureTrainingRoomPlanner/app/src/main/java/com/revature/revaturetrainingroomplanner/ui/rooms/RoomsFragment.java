package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;

import java.util.Objects;

public class RoomsFragment extends Fragment implements RoomsAdapter.OnItemListener {

    private BatchAssignment mBatchAssignment;
    private RoomsViewModel roomsViewModel;
    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);

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


}