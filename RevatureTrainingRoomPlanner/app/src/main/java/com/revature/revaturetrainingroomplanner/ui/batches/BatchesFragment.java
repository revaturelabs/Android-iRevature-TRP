package com.revature.revaturetrainingroomplanner.ui.batches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);

        Button assign = root.findViewById(R.id.btn_batch_detail_assign);
        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavController.navigate(R.id.action_nav_batches_to_nav_rooms);
            }
        });


//        final TextView textView = root.findViewById(R.id.text_send);
//        batchesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }

    @Override
    public void onItemClick(int position) {
        // TODO: update text fields (batch details) based on batch clicked on
        Bundle args = new Bundle();


    }
}