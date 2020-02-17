package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomInfoFragment extends Fragment implements View.OnClickListener{

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

        TextView maxSeats = rootView.findViewById(R.id.tv_room_info_seats);
        TextView confirmed = rootView.findViewById(R.id.tv_room_info_confirmed);

        // TODO: set seats and room confirmation based on room
        String max = "Max seats: " + "##";
        maxSeats.setText(max);
        String confirmation = "Confirmed: " + "##";
        confirmed.setText(confirmation);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        // Once Trainers Fragment ready navigate there
        mNavController.navigate(R.id.action_nav_room_info_to_nav_trainers);
    }
}
