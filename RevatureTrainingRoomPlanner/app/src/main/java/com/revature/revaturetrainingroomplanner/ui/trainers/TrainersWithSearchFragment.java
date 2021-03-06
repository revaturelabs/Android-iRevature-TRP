package com.revature.revaturetrainingroomplanner.ui.trainers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
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
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.model.TrainerWithSkills;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.TrainerRepository;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.SkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainerWithSkillsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.TrainerWithSkillsAdapter.OnItemListener;
import com.revature.revaturetrainingroomplanner.ui.viewmodels.CampusSelectedViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainersWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    private static final String TAG = "TrainersWithSearchFragm";

    private static final Comparator<TrainerWithSkills> ALPHABETICAL_COMPARATOR = (a, b) -> a.getTrainer().getTrainer_name().compareTo(b.getTrainer().getTrainer_name());
    private static final Comparator<Skill> ALPHABETICAL_COMPARATOR_SKILLS = (a, b) -> a.getText().compareTo(b.getText());

    private static final long USF_ID = 1;
    private static final long UTA_ID = 2;
    private static final long WVU_ID = 3;
    private static final long Reston_ID = 4;

    private List<TrainerWithSkills> mTrainerWithSkillsModels;
    private RecyclerView mTrainerRecyclerView;
    private TrainerWithSkillsAdapter mTrainerWithSkillsAdapter;
    private TrainerRowBinding mTrainerRowBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private TrainerRepository mTrainerRepository;
    private static int counter = 1;
    private Campus mCampusSelected;
    private ImageView mCapusImageView;
    private TextView mTextViewCampusName;
    private TextView mTextViewLocation;
    private ConstraintLayout mCampusLayout;
    private CampusSelectedViewModel mCampusSelectedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTrainerRepository = new TrainerRepository(getContext());
        mTrainerWithSkillsModels = new ArrayList<>();
        mCampusSelectedViewModel = new ViewModelProvider(requireActivity()).get(CampusSelectedViewModel.class);
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
        mCampusLayout = root.findViewById(R.id.constraintlayout_campus_selected);
        mCapusImageView = root.findViewById(R.id.img_select_building_campus);
        mTextViewCampusName = root.findViewById(R.id.tv_select_building_campus);
        mTextViewLocation = root.findViewById(R.id.tv_select_trainers_campus_location);
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
        mSearchView.setQueryHint("Search by trainer's name");

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
                    if (trainer.getCampus_id() == mCampusSelected.getCampus_id()) {
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

    private void subscribeObservers() {
        mCampusSelectedViewModel.getCampusSelected().observe(getViewLifecycleOwner(), campus -> {
            if (campus != null) {
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
            mTextViewLocation.setText("Tampa, FL");
        } else if (campusID == UTA_ID) {
            mCapusImageView.setImageResource(R.drawable.dallas);
            mTextViewLocation.setText("Arlington, TX");
        } else if (campusID == Reston_ID) {
            mCapusImageView.setImageResource(R.drawable.reston);
            mTextViewLocation.setText("Reston, VA");
        } else if (campusID == WVU_ID) {
            mCapusImageView.setImageResource(R.drawable.morgantown);
            mTextViewLocation.setText("Morgantown, WV");
        }
    }
}
