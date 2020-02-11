package com.revature.revaturetrainingroomplanner.ui.batches;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BatchesFragment extends Fragment {

    private static final String[] BATCHES = new String[]{
            "2001Mobile"
    };

    private static final Comparator<BatchModel> ALPHABETICAL_COMPARATOR = new Comparator<BatchModel>() {
        @Override
        public int compare(BatchModel a, BatchModel b) {
        return a.getText().compareTo(b.getText());
        }
    };

    private BatchesViewModel batchesViewModel;
    private List<BatchModel> mModels;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private BatchesAdapter mAdapter;
    private BatchRowBinding mBinding;
    private Animator mAnimator;
    SearchView searchView;
    FrameLayout frameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_batches, container, false);

//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_batches, container, false);
//
//        mAdapter = new BatchesAdapter(getContext(), ALPHABETICAL_COMPARATOR);
//
//        RecyclerView recyclerView = mBinding.getRoot().findViewById(R.id.recyclerview_batches_list_batches);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(mAdapter);
//
//        mModels = new ArrayList<>();
//        int id = 0;
//        for (String batch: BATCHES) {
//            mModels.add(new BatchModel(id, batch));
//            id++;
//        }
//        mAdapter.edit()
//                .add(mModels)
//                .commit();
//
//        searchView = root.findViewById(R.id.searchview_batches_search_batch);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                final List<BatchModel> filteredModelList = filter(mModels, query);
//                mAdapter.edit()
//                        .replaceAll(filteredModelList)
//                        .commit();
//                recyclerView.scrollToPosition(0);
//                return true;
//            }
//        });

        frameLayout = root.findViewById(R.id.framelayout_batches_batch_detail);
//        final TextView textView = root.findViewById(R.id.text_batches);
//        batchesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }

    private static List<BatchModel> filter(List<BatchModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<BatchModel> filteredModelList = new ArrayList<>();
        for (BatchModel model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}