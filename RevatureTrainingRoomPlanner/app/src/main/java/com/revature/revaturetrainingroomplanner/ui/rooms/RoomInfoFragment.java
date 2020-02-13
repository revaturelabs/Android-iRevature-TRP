package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.trainers.TrainersFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomInfoFragment extends Fragment implements View.OnClickListener{

    public RoomInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_info, container, false);
        Button assign = getView().findViewById(R.id.btn_room_info_assign);
        assign.setOnClickListener(this);

        TextView maxSeats = getView().findViewById(R.id.tv_room_info_seats);
        TextView confirmed = getView().findViewById(R.id.tv_room_info_confirmed);

        // TODO: set seats and room confirmation based on room
        String max = "Max seats: " + "##";
        maxSeats.setText(max);
        String confirmation = "Confirmed: " + "##";
        confirmed.setText(confirmation);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Room assigned", Toast.LENGTH_SHORT).show();
        // TODO: move to trainer fragment, store room and batch

        Fragment fragment = new TrainersFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
