package com.revature.revaturetrainingroomplanner.ui.trainers;

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
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.TrainerRepository;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainersAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersWithSearchFragment extends Fragment implements SortedListAdapter.Callback, View.OnClickListener {

    private static final String[] TRAINERS = new String[]{
            "2001Mobile",
            "2100FullStack",
            "2200FrontEnd",
            "4150Backend"
    };

    private static final Comparator<Trainer> ALPHABETICAL_COMPARATOR = (a, b) -> a.getText().compareTo(b.getText());

    private List<Trainer> mModels;
    private RecyclerView mRecyclerView;
    private TrainersAdapter mAdapter;
    private TrainerRowBinding mBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private TrainerRepository mTrainerRepository;
    private static int counter = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTrainerRepository = new TrainerRepository(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.trainer_row, container, false);

        OnItemListener onItemListener = (getParentFragment() instanceof OnItemListener) ? (OnItemListener) getParentFragment() : (OnItemListener) getParentFragment().getParentFragment();

        View root = inflater.inflate(R.layout.fragment_trainers_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_trainers_with_search_list_trainers);
        mSearchView = root.findViewById(R.id.searchview_trainers_with_search_search_trainer);
        mProgressBar = root.findViewById(R.id.progressbar_trainers_with_search_progress);
        root.findViewById(R.id.btn_trainers_with_search_add_fake_data).setOnClickListener(this);
        root.findViewById(R.id.btn_trainers_with_search_clear_fake_data).setOnClickListener(this);

        mAdapter = new TrainersAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);

        mAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        retrieveTrainers();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<Trainer> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });
        mSearchView.setQueryHint("Look for trainer");


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_trainers_with_search_add_fake_data: {
                insertFakeData(new Trainer("Fake Trainer #" + counter));
                counter++;
            }
            break;

            case R.id.btn_trainers_with_search_clear_fake_data: {
                clearFakeData();
            }
            break;

            default:

        }
    }

    private static List<Trainer> filter(List<Trainer> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Trainer> filteredModelList = new ArrayList<>();
        for (Trainer model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void retrieveTrainers() {

        mTrainerRepository.retrieveAllTask().observe(getViewLifecycleOwner(), trainers -> {
            if (trainers != null) {
                mModels = trainers;
                mAdapter.edit()
                        .replaceAll(trainers)
                        .commit();
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    public void insertFakeData(Trainer trainer) {
        mTrainerRepository.insertTrainerTask(trainer);
    }

    public void clearFakeData() {
        mTrainerRepository.deleteAllTask(new Trainer(""));
    }

}
