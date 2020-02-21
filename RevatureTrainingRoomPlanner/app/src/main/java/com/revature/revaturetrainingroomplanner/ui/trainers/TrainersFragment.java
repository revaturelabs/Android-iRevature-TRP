package com.revature.revaturetrainingroomplanner.ui.trainers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.data.model.TrainerWithSkills;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.TrainerRepository;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainerWithSkillsAdapter;

import java.util.Objects;

public class TrainersFragment extends Fragment implements TrainerWithSkillsAdapter.OnItemListener {

    private BatchAssignment mBatchAssignment;
    private RoomWithBatchAssignments mRoomSelected;
    private TrainersViewModel trainersViewModel;
    private NavController mNavController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trainersViewModel =
                ViewModelProviders.of(this).get(TrainersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trainers, container, false);

        TrainersWithSearchFragment searchFragment = (TrainersWithSearchFragment) getChildFragmentManager().findFragmentById(R.id.fragment_trainers_search_fragment);

        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        mBatchAssignment = TrainersFragmentArgs.fromBundle(getArguments()).getBatchAssignment();
        mRoomSelected = TrainersFragmentArgs.fromBundle(getArguments()).getRoomSelected();

        searchFragment.setCampusIDFilter(mRoomSelected.getRoom().getCampus_id());


//        final TextView textView = root.findViewById(R.id.text_send);
//        trainersViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        TrainerRepository trainerRepository = new TrainerRepository(getContext());
        trainerRepository.retrieveTrainersFromAPI();
    }

    @Override
    public void onTrainerClick(TrainerWithSkills trainerWithSkillsClicked) {

        TrainersFragmentDirections.ActionNavTrainersToNavTrainerInfo actionNavTrainersToNavTrainerInfo = TrainersFragmentDirections.actionNavTrainersToNavTrainerInfo(mBatchAssignment, trainerWithSkillsClicked.getTrainer());
        actionNavTrainersToNavTrainerInfo.setDisplayButton(true);
        mNavController.navigate(actionNavTrainersToNavTrainerInfo);

    }
}