package com.revature.revaturetrainingroomplanner.ui.trainers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private Trainer mTrainerSelected;
    private BatchAssignment mBatchAssignment;


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
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_trainer_info_skills);

        name = rootView.findViewById(R.id.tv_trainers_name);
        email = rootView.findViewById(R.id.tv_trainers_email);
        location = rootView.findViewById(R.id.tv_trainers_location);

        mBatchAssignment = TrainersInfoFragmentArgs.fromBundle(getArguments()).getBatchAssignment();
        mTrainerSelected = TrainersInfoFragmentArgs.fromBundle(getArguments()).getTrainerSelected();

        String imgURL = mTrainerSelected.getTrainer_profile_picture_url();
//        Log.d("URL", imgURL);

        name.setText(mTrainerSelected.getTrainer_name());
        email.setText(mTrainerSelected.getTrainer_email());
        location.setText(mTrainerSelected.getTrainer_location());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mTrainerSelected.getSkillsAdapter());

        Picasso.get().load(imgURL).into(profile);

        // for fun
        if(name.getText().toString().equals("John Cena"))
            profile.setImageResource(R.drawable.thiago);


        if (!TrainersInfoFragmentArgs.fromBundle(getArguments()).getDisplayButton()) {
            assignBtn.setVisibility(View.GONE);
        }

        assignBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        mBatchAssignment.setTrainer_id(mTrainerSelected.getTrainer_id());

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setCancelable(true);
        builder.setTitle("Batch Assignment Created");
        builder.setPositiveButton("Okay", (dialog, which) -> { });
        AlertDialog dialog = builder.create();
        dialog.show();

//        For developing
//        Toast.makeText(getContext(), "Batch" + mBatchAssignment.toString() + "assigned", Toast.LENGTH_LONG).show();

        String filename = "BatchAssignment";
        String fileContents = mBatchAssignment.toString();
        try (FileOutputStream fos = getActivity().getApplicationContext().openFileOutput(filename, Context.MODE_APPEND)) {
            fos.write(fileContents.getBytes());
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        mNavController.navigate(R.id.action_nav_trainer_info_to_nav_batches);
    }

}