package com.revature.revaturetrainingroomplanner.ui.batches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.BatchWithSkills;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchWithSkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.viewmodels.CampusSelectedViewModel;

public class BatchesFragment extends Fragment implements BatchWithSkillsAdapter.OnItemListener {

    ConstraintLayout mCampusLayout;
    private NavController mNavController;
    private CampusSelectedViewModel mCampusSelectedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCampusSelectedViewModel = new ViewModelProvider(requireActivity()).get(CampusSelectedViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        mCampusSelectedViewModel.setCampusSelected(null);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onStart() {
        super.onStart();
//        BatchRepository batchRepository = new BatchRepository(getContext());
//        batchRepository.retrieveBatchesFromAPI();
    }

    @Override
    public void onBatchClick(BatchWithSkills batchWithSkills) {
        BatchAssignment batchAssignment = new BatchAssignment();

        batchAssignment.setBatch_id(batchWithSkills.getBatch().getBatch_id());

        BatchesFragmentDirections.ActionNavBatchesToNavRooms actionNavBatchesToNavRooms = BatchesFragmentDirections.actionNavBatchesToNavRooms(batchAssignment, batchWithSkills.getBatch());
        mNavController.navigate(actionNavBatchesToNavRooms);
    }

//    public void set

}