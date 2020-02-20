package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.Room;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomInfoFragment extends Fragment implements View.OnClickListener {

    private Room mRoomSelected;
    private BatchAssignment mBatchAssignment;
    private Button assignBtn;
    private NavController mNavController;

    public RoomInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_info, container, false);

        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        assignBtn = rootView.findViewById(R.id.btn_room_info_assign);
        if(!RoomInfoFragmentArgs.fromBundle(getArguments()).getDisplayButton()) {
            assignBtn.setVisibility(View.GONE);
        }
        assignBtn.setOnClickListener(this);

        mRoomSelected = RoomInfoFragmentArgs.fromBundle(getArguments()).getRoomSelected();
        mBatchAssignment = getArguments().getParcelable("batchAssignment");


        TextView maxSeats = rootView.findViewById(R.id.tv_room_info_seats);
        String max = "Max seats: " + mRoomSelected.getOccupancy();
        maxSeats.setText(max);

        String roomNumber = "Room Number: " + mRoomSelected.getRoom_name();
        TextView room = rootView.findViewById(R.id.tv_room_info_header);
        room.setText(roomNumber);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        mBatchAssignment.setRoom_id(mRoomSelected.getRoom_id());
        Bundle args = new Bundle();
        args.putParcelable("batchAssignment", mBatchAssignment);
        mNavController.navigate(R.id.action_nav_room_info_to_nav_trainers, args);
    }
}
