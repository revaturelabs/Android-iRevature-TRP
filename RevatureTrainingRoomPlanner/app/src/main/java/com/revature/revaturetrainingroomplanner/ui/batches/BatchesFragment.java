package com.revature.revaturetrainingroomplanner.ui.batches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;

import java.util.Objects;

public class BatchesFragment extends Fragment implements BatchesAdapter.OnItemListener {

    private BatchesViewModel batchesViewModel;
    private NavController mNavController;
    private View batchInfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        batchInfo = root.findViewById(R.id.include_batches_batch_info);
        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        batchInfo.setVisibility(View.GONE);

        root.findViewById(R.id.btn_batch_info_assign_room).setOnClickListener((v) -> mNavController.navigate(BatchesFragmentDirections.actionNavBatchesToNavRooms()));

//        final TextView textView = root.findViewById(R.id.text_send);
//        batchesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
    @Override
    public void onBatchClick(int position) {
        batchInfo.setVisibility(View.VISIBLE);

        // set number of associates based on whats clicked
        TextView associates = batchInfo.findViewById(R.id.tv_batch_info_associates);
        String associatesNum = "Associates: " + 0; // parse endpoint
        associates.setText(associatesNum);

    }
}