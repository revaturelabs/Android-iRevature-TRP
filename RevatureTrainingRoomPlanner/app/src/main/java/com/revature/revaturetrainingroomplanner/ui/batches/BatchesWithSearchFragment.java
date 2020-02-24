package com.revature.revaturetrainingroomplanner.ui.batches;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchWithSkills;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BatchRepository;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchWithSkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.BatchWithSkillsAdapter.OnItemListener;
import com.revature.revaturetrainingroomplanner.ui.adapter.SkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.viewmodels.CampusSelectedViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BatchesWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    /* Constants */
    private static final String TAG = "BatchesSearchFragment";

    private static final Comparator<BatchWithSkills> ALPHABETICAL_COMPARATOR = (a, b) -> a.getBatch().getBatch_name().compareTo(b.getBatch().getBatch_name());
    private static final Comparator<Skill> ALPHABETICAL_COMPARATOR_SKILLS = (a, b) -> a.getSkill().compareTo(b.getSkill());

    private static final long USF_ID = 1;
    private static final long UTA_ID = 2;
    private static final long WVU_ID = 3;
    private static final long Reston_ID = 4;

    /* UI Components */
    private RecyclerView mRecyclerView;
    private BatchRowBinding mBinding;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private ConstraintLayout mCampusLayout;
    private ImageView mCapusImageView;
    private TextView mTextViewCampusName;

    /* Variables */
    private BatchesViewModel batchesViewModel;
    private List<BatchWithSkills> mModels;
    private BatchWithSkillsAdapter mAdapter;
    private Animator mAnimator;
    private BatchRepository mBatchesRepository;
    private Campus mCampusSelected;
    private CampusSelectedViewModel mCampusSelectedViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBatchesRepository = new BatchRepository(getContext());

        mCampusSelectedViewModel = new ViewModelProvider(requireActivity()).get(CampusSelectedViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        batchesViewModel =
//                ViewModelProviders.of(this).get(BatchesViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_batches, container, false);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.batch_row, container, false);

        OnItemListener onItemListener = (OnItemListener) ((getParentFragment() instanceof OnItemListener) ? getParentFragment() :  getParentFragment().getParentFragment());

        View root = inflater.inflate(R.layout.fragment_batches_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_batches_with_search_list_batches);
        mSearchView = root.findViewById(R.id.searchview_batches_with_search_search_batch);
        mProgressBar = root.findViewById(R.id.progressbar_batches_with_search_progress);
        mCampusLayout = root.findViewById(R.id.constraintlayout_campus_selected);
        mCapusImageView = root.findViewById(R.id.img_select_building_campus);
        mTextViewCampusName = root.findViewById(R.id.tv_select_building_campus);

        mAdapter = new BatchWithSkillsAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);

        mAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        retrieveBatches();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<BatchWithSkills> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });

        mSearchView.setQueryHint("Look for batch");

        subscribeObservers();

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

    private static List<BatchWithSkills> filter(List<BatchWithSkills> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<BatchWithSkills> filteredModelList = new ArrayList<>();
        for (BatchWithSkills model : models) {
            final String text = model.getBatch().getBatch_name().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void retrieveBatches() {

        mBatchesRepository.retrieveAllTask().observe(getViewLifecycleOwner(), batches -> {
            if (batches != null) {
                List<BatchWithSkills> filteredBatches = new ArrayList<>();

                for (BatchWithSkills batchWithSkills: batches) {
                    if (mCampusSelected != null) {
                        if (batchWithSkills.getBatch().getCampus_id() == mCampusSelected.getCampus_id()) {
                            batchWithSkills.getBatch().setSkillsAdapter(new SkillsAdapter(getContext(), ALPHABETICAL_COMPARATOR_SKILLS));
                            filteredBatches.add(batchWithSkills);

                            mAdapter.edit()
                                    .replaceAll(filteredBatches)
                                    .commit();

                            mModels = filteredBatches;
                        }
                    } else {
                        batchWithSkills.getBatch().setSkillsAdapter(new SkillsAdapter(getContext(), ALPHABETICAL_COMPARATOR_SKILLS));
                        filteredBatches.add(batchWithSkills);
                    }
                }

                if (mCampusSelected != null) {
                    mAdapter.edit()
                            .replaceAll(filteredBatches)
                            .commit();

                    mModels = filteredBatches;
                } else {
                    mAdapter.edit()
                            .replaceAll(batches)
                            .commit();

                    mModels = batches;
                }

                Log.d(TAG, "onChanged: " + mModels.size());
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    private void subscribeObservers() {
        mCampusSelectedViewModel.getCampusSelected().observe(getViewLifecycleOwner(), campus -> {
            if (campus == null) {
                mCampusLayout.setVisibility(View.GONE);
            } else {
                mCampusSelected = campus;
                mCampusLayout.setVisibility(View.VISIBLE);

                setLocation(mCampusSelected.getCampus_id());
                mTextViewCampusName.setText(mCampusSelected.getCampus_name());
            }
        });
    }

    private void setLocation(long campusID) {

        if (campusID == USF_ID) {
            mCapusImageView.setImageResource(R.drawable.tampa);
        } else if (campusID == UTA_ID) {
            mCapusImageView.setImageResource(R.drawable.dallas);
        } else if (campusID == Reston_ID) {
            mCapusImageView.setImageResource(R.drawable.reston);
        } else if (campusID == WVU_ID) {
            mCapusImageView.setImageResource(R.drawable.morgantown);
        }
    }

}