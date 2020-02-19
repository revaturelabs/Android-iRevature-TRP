package com.revature.revaturetrainingroomplanner.ui.trainers;

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
public class TrainersInfoFragment extends Fragment implements View.OnClickListener{

    private Button assignBtn;
    private NavController mNavController;
    private TextView name;
    private TextView email;

    public TrainersInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trainer_info, container, false);
        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        assignBtn = rootView.findViewById(R.id.btn_trainer_info_assign);
        name = rootView.findViewById(R.id.tv_trainers_name);
        email = rootView.findViewById(R.id.tv_trainers_email);

        name.setText("Phirom Peterschmidt");
        email.setText("phiromp@gmail.com");

        if(!TrainersInfoFragmentArgs.fromBundle(getArguments()).getDisplayButton()) {
            assignBtn.setVisibility(View.GONE);
        }

        assignBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Trainer/room assigned to batch", Toast.LENGTH_SHORT).show();
        mNavController.navigate(R.id.action_nav_trainer_info_to_nav_batches);
    }
}
