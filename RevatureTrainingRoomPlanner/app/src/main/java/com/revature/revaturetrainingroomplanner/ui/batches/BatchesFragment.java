package com.revature.revaturetrainingroomplanner.ui.batches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;

public class BatchesFragment extends Fragment implements BatchesAdapter.OnItemListener {

    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_batches, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onBatchClick(Batch batch) {
        BatchAssignment batchAssignment = new BatchAssignment();

        batchAssignment.setBatch_id(batch.getBatch_id());

        BatchesFragmentDirections.ActionNavBatchesToNavRooms actionNavBatchesToNavRooms = BatchesFragmentDirections.actionNavBatchesToNavRooms(batchAssignment, batch);
        mNavController.navigate(actionNavBatchesToNavRooms);
    }


}