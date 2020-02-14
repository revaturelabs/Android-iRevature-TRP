package com.revature.revaturetrainingroomplanner.ui.rooms;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.revature.revaturetrainingroomplanner.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomLookupFragment extends Fragment {

    Spinner location, room;

    List<String> locationArray =  new ArrayList<String>();
    List<String> roomArray =  new ArrayList<String>();

    public RoomLookupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_lookup, container, false);

        location = view.findViewById(R.id.spinner_room_lookup_location);
        room = view.findViewById(R.id.spinner_room_lookup_room);

        // dummy values
        locationArray.add("USF");
        locationArray.add("WVU");
        locationArray.add("TEX");
        locationArray.add("RES");

        roomArray.add("NEC 100");
        roomArray.add("NEC 200");
        roomArray.add("NEC 300");
        roomArray.add("NEC 400");

        // populate spinners
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>
                (getContext(), android.R.layout.simple_spinner_dropdown_item, locationArray);

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>
                (getContext(), android.R.layout.simple_spinner_dropdown_item, roomArray);

        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(locationAdapter);

        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(roomAdapter);

        // To get a selected item if wanted
        // String selected = location.getSelectedItem().toString();

        return view;
    }

}
