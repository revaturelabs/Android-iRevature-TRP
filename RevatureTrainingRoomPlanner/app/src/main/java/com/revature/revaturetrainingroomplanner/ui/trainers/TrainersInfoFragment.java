package com.revature.revaturetrainingroomplanner.ui.trainers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.batches.BatchesFragmentDirections;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersInfoFragment extends Fragment implements View.OnClickListener{

    NavController mNavController;

    public TrainersInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trainer_info, container, false);
        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        Button assign = rootView.findViewById(R.id.btn_trainer_info_assign);
        assign.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "Trainer assigned to batch", Toast.LENGTH_LONG).show();
        // update "database"
        Bundle args = new Bundle();
        mNavController.navigate(R.id.nav_batches, args);

    }
}
