package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.revature.revaturetrainingroomplanner.R;

public class RoomsFragment extends Fragment {

    private RoomsViewModel roomsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        roomsViewModel =
                ViewModelProviders.of(this).get(RoomsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        roomsViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}