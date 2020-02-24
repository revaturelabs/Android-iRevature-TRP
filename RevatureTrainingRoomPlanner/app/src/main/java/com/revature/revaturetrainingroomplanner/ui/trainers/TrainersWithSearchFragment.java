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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.model.TrainerWithSkills;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.TrainerRepository;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.SkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainerWithSkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainerWithSkillsAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    private static final String TAG = "TrainersWithSearchFragm";

    private static final String[] TRAINERS = new String[]{
            "2001Mobile",
            "2100FullStack",
            "2200FrontEnd",
            "4150Backend"
    };

    private static final Comparator<TrainerWithSkills> ALPHABETICAL_COMPARATOR = (a, b) -> a.getTrainer().getTrainer_name().compareTo(b.getTrainer().getTrainer_name());
    private static final Comparator<Skill> ALPHABETICAL_COMPARATOR_SKILLS = (a, b) -> a.getText().compareTo(b.getText());

    private List<TrainerWithSkills> mTrainerWithSkillsModels;
    private RecyclerView mTrainerRecyclerView;
    private TrainerWithSkillsAdapter mTrainerWithSkillsAdapter;
    private TrainerRowBinding mTrainerRowBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private TrainerRepository mTrainerRepository;
    private static int counter = 1;
    private long mCampusSelectedID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTrainerRepository = new TrainerRepository(getContext());
        mTrainerWithSkillsModels = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTrainerRowBinding = DataBindingUtil.inflate(inflater, R.layout.trainer_row, container, false);

        OnItemListener onItemListener = (OnItemListener) ((getParentFragment() instanceof OnItemListener) ? getParentFragment() :  getParentFragment().getParentFragment());

        View root = inflater.inflate(R.layout.fragment_trainers_with_search, container, false);
        mTrainerRecyclerView = root.findViewById(R.id.recyclerview_trainers_with_search_list_trainers);
        mSearchView = root.findViewById(R.id.searchview_trainers_with_search_search_trainer);
        mProgressBar = root.findViewById(R.id.progressbar_trainers_with_search_progress);

        mTrainerWithSkillsAdapter = new TrainerWithSkillsAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);

        mTrainerWithSkillsAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mTrainerRecyclerView.setLayoutManager(layoutManager);
        mTrainerRecyclerView.setAdapter(mTrainerWithSkillsAdapter);

        retrieveTrainers();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<TrainerWithSkills> filteredModelList = filter(mTrainerWithSkillsModels, query);
                mTrainerWithSkillsAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mTrainerRecyclerView.scrollToPosition(0);
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

        mTrainerRecyclerView.animate().alpha(0.5f);
    }

    @Override
    public void onEditFinished() {
        mTrainerRecyclerView.scrollToPosition(0);
        mTrainerRecyclerView.animate().alpha(1.0f);

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

    private static List<TrainerWithSkills> filter(List<TrainerWithSkills> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<TrainerWithSkills> filteredModelList = new ArrayList<>();
        for (TrainerWithSkills model : models) {
            final String text = model.getTrainer().getTrainer_name().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void retrieveTrainers() {

        mTrainerRepository.retrieveAllTask().observe(getViewLifecycleOwner(), trainers -> {
            if (trainers != null) {

                List<TrainerWithSkills> filteredTrainers = new ArrayList<>();

                Trainer trainer;

                for (TrainerWithSkills trainerWithSkills: trainers) {
                    trainer = trainerWithSkills.getTrainer();
                    if (trainer.getCampus_id() == mCampusSelectedID) {
                        trainer.setSkillsAdapter(new SkillsAdapter(getContext(), ALPHABETICAL_COMPARATOR_SKILLS));
                        filteredTrainers.add(trainerWithSkills);
                    }
                }

                mTrainerWithSkillsAdapter.edit()
                        .replaceAll(filteredTrainers)
                        .commit();
                mTrainerWithSkillsModels = filteredTrainers;
            } else {
                mTrainerWithSkillsModels = new ArrayList<>();
            }
        });
    }

    public void setCampusIDFilter(long campusIDFilter) {
        mCampusSelectedID = campusIDFilter;
    }
}
