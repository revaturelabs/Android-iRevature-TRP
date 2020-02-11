package com.revature.revaturetrainingroomplanner.ui.trainers;

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

public class TrainersFragment extends Fragment {

    private TrainersViewModel trainersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trainersViewModel =
                ViewModelProviders.of(this).get(TrainersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trainers, container, false);
//        final TextView textView = root.findViewById(R.id.text_send);
//        trainersViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}