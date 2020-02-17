package com.revature.revaturetrainingroomplanner.ui.batches;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BatchesWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    /* Constants */
    private static final String[] BATCHES = new String[]{
            "2001Mobile",
            "2100FullStack",
            "2200FrontEnd",
            "4150Backend"
    };

    private static final Comparator<Batch> ALPHABETICAL_COMPARATOR = (a, b) -> a.getText().compareTo(b.getText());

    /* UI Components */
    private RecyclerView mRecyclerView;
    private BatchRowBinding mBinding;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;

    /* Variables */
    private BatchesViewModel batchesViewModel;
    private List<Batch> mModels;
    private BatchesAdapter mAdapter;
    private Animator mAnimator;
//    private BatchRepository mAppRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mAppRepository = new BatchRepository<Batch>(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.batch_row, container, false);

        OnItemListener onItemListener = (OnItemListener) getParentFragment();

        View root = inflater.inflate(R.layout.fragment_batches_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_batches_with_search_list_batches);
        mSearchView = root.findViewById(R.id.searchview_batches_with_search_search_batch);
        mProgressBar = root.findViewById(R.id.progressbar_batches_with_search_progress);

        mAdapter = new BatchesAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);

        mAdapter.addCallback(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        int id = 0;
        mModels = new ArrayList<>();
        for (String batch: BATCHES) {
            Batch batch1 = new Batch(batch);
            batch1.setId(id);
            mModels.add(batch1);
            id++;
        }
        mAdapter.edit()
                .add(mModels)
                .commit();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<Batch> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });

        mSearchView.setQueryHint("Look for batch");

        return root;
    }

    @Override
    public void onEditStarted() {
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setAlpha(0.0f);
        }

        if (mAnimator != null) {
            mAnimator.cancel();
        }

        mAnimator = ObjectAnimator.ofFloat(mProgressBar, View.ALPHA, 1.0f);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.start();

        mRecyclerView.animate().alpha(0.5f);
    }

    @Override
    public void onEditFinished() {
        mRecyclerView.scrollToPosition(0);
        mRecyclerView.animate().alpha(1.0f);

        if (mAnimator != null) {
            mAnimator.cancel();
        }

        mAnimator = ObjectAnimator.ofFloat(mProgressBar, View.ALPHA, 0.0f);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {

            private boolean mCanceled = false;

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                mCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!mCanceled) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mAnimator.start();
    }

    private static List<Batch> filter(List<Batch> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Batch> filteredModelList = new ArrayList<>();
        for (Batch model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

//    private void retrieveBatches() {
//        mAppRepository.retrieveTask().observe(this, new Observer<List<Batch>>() {
//            @Override
//            public void onChanged(List<Batch> batches) {
//                if (!mModels.isEmpty()) {
//                    mModels.clear();
//                }
//
//                if (batches != null) {
//                    mModels.addAll(batches);
//                }
//
//                mAdapter.notifyDataSetChanged();
//            }
//        });
//    }
}