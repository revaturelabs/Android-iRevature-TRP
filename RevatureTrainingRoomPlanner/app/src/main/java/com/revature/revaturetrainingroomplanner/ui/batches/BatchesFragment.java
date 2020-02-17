package com.revature.revaturetrainingroomplanner.ui.batches;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;

import java.util.Objects;

public class BatchesFragment extends Fragment implements BatchesAdapter.OnItemListener {

    private BatchesViewModel batchesViewModel;
    private NavController mNavController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_batches, container, false);

//        final TextView textView = root.findViewById(R.id.text_send);
//        batchesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNavController = Navigation.findNavController(view);

        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void onBatchClick(int position) {

        mNavController.navigate(BatchesFragmentDirections.actionNavBatchesToNavRooms());

    }
}
