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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchModel;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchesAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BatchesFragment extends Fragment implements SortedListAdapter.Callback, BatchesAdapter.OnItemListener {

    private static final String[] BATCHES = new String[]{
            "2001Mobile",
            "2100FullStack",
            "2200FrontEnd",
            "4150Backend"
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
    private ViewDataBinding mBinding;
    private Animator mAnimator;
    private SearchView searchView;
    private ProgressBar mProgressBar;
    private NavController mNavController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.batch_row, container, false);

        View root = inflater.inflate(R.layout.fragment_batches, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_batches_list_batches);
        searchView = root.findViewById(R.id.searchview_batches_search_batch);
        mProgressBar = root.findViewById(R.id.progressbar_batches_progress);
        mNavController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        
        mAdapter = new BatchesAdapter(getContext(), ALPHABETICAL_COMPARATOR, this);

        mAdapter.addCallback(this);

        layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mModels = new ArrayList<>();
        int id = 0;
        for (String batch: BATCHES) {
            mModels.add(new BatchModel(id, batch));
            id++;
        }
        mAdapter.edit()
                .add(mModels)
                .commit();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<BatchModel> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });

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

    @Override
    public void onItemClick(int position) {
        Bundle args = new Bundle();
        mNavController.navigate(R.id.action_nav_batches_to_nav_rooms);
    }
}