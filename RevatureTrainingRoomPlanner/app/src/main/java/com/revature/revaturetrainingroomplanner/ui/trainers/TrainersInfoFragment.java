package com.revature.revaturetrainingroomplanner.ui.trainers;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersInfoFragment extends Fragment implements View.OnClickListener {

    private Button assignBtn;
    private NavController mNavController;
    private TextView name;
    private TextView email;
    private TextView location;
    private TextView skills;
    private Trainer mTrainerSelected;


    public TrainersInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trainer_info, container, false);
        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        assignBtn = rootView.findViewById(R.id.btn_trainer_info_assign);
        ImageView profile = rootView.findViewById(R.id.img_trainers_profile);

        name = rootView.findViewById(R.id.tv_trainers_name);
        email = rootView.findViewById(R.id.tv_trainers_email);
        location = rootView.findViewById(R.id.tv_trainers_location);
        skills = rootView.findViewById(R.id.tv_trainers_skills);

        String imgURL = TrainersAdapter.currProfileURL;
        Log.d("URL", imgURL);

        name.setText(TrainersAdapter.currName);
        email.setText(TrainersAdapter.currEmail);
        location.setText(TrainersAdapter.currLocation);

        Picasso.get().load(imgURL).into(profile);

//        String skill = "";
//        for (int i = 1; i <= TrainersAdapter.skills.size(); i++) {
//            skill = TrainersAdapter.skills.get(i) + ", ";
//        }
//        TrainersAdapter.currSkills;
//        skills.setText(skill);


        if (!TrainersInfoFragmentArgs.fromBundle(getArguments()).getDisplayButton()) {
            assignBtn.setVisibility(View.GONE);
        }

        mTrainerSelected = TrainersInfoFragmentArgs.fromBundle(getArguments()).getTrainerSelected();

        assignBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Trainer/room assigned to batch", Toast.LENGTH_SHORT).show();
        mNavController.navigate(R.id.action_nav_trainer_info_to_nav_batches);
    }

}