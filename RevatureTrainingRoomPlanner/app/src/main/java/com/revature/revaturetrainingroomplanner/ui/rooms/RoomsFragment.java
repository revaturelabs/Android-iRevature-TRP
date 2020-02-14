package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;

import java.util.Objects;

public class RoomsFragment extends Fragment implements RoomsAdapter.OnItemListener {

    private RoomsViewModel roomsViewModel;
    private NavController mNavController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);
        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        return root;
    }

    @Override
    public void onItemClick(int position) {
        Bundle args = new Bundle();
        mNavController.navigate(R.id.action_nav_rooms_to_nav_rooms_info, args);
    }
}