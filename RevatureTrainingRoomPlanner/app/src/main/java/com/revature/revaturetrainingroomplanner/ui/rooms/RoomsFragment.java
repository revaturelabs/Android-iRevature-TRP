package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;

public class RoomsFragment extends Fragment implements RoomsAdapter.OnItemListener {

    private RoomsViewModel roomsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);
        return root;
    }

    @Override
    public void onItemClick(int position) {

    }
}