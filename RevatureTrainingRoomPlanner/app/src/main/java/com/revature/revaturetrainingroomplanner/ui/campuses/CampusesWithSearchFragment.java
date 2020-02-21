package com.revature.revaturetrainingroomplanner.ui.campuses;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.CampusRepository;
import com.revature.revaturetrainingroomplanner.databinding.CampusRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CampusesWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    private static final String[] CAMPUSES = new String[]{
            "USF",
            "WVU",
            "TX",
            "NYU",
            "UTA"
    };

    private static final Comparator<Campus> ALPHABETICAL_COMPARATOR = (a, b) -> a.getText().compareTo(b.getText());

    private static final String TAG = "CampusesSearchFragment";

//    private CampusesViewModel campusesViewModel;
    private List<Campus> mModels;
    private RecyclerView mRecyclerView;
    private CampusesAdapter mAdapter;
    private CampusRowBinding mBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private CampusRepository mCampusRepository;
    private static int counter = 1;
    private ImageView campusImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mCampusRepository = new CampusRepository(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        campusesViewModel =
//                ViewModelProviders.of(this).get(CampusesViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_campuses, container, false);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.campus_row, container, false);

        OnItemListener onItemListener = (OnItemListener) ((getParentFragment() instanceof OnItemListener) ? getParentFragment() :  getParentFragment().getParentFragment());
        View root = inflater.inflate(R.layout.fragment_campuses_with_search, container, false);

//        mBinding.getRoot().setOnClickListener((View.OnClickListener) onItemListener);

        mRecyclerView = root.findViewById(R.id.recyclerview_campuses_with_search_list_campuses);
        mSearchView = root.findViewById(R.id.searchview_campuses_with_search_search_campus);
        mProgressBar = root.findViewById(R.id.progressbar_campuses_with_search_progress);

        mAdapter = new CampusesAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);
        mAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        retrieveCampuses();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<Campus> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });
        mSearchView.setQueryHint("Look for campus");

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

    private static List<Campus> filter(List<Campus> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Campus> filteredModelList = new ArrayList<>();
        for (Campus model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void retrieveCampuses() {

        mCampusRepository.retrieveAllTask().observe(getViewLifecycleOwner(), campuses -> {
            if (campuses != null) {
                mModels = campuses;
                mAdapter.edit()
                        .replaceAll(campuses)
                        .commit();
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    private void insertFakeData(Campus campus) {
        mCampusRepository.insertCampusTask(campus);
    }

    private void clearFakeData() {
        mCampusRepository.deleteAllTask(new Campus(""));
    }
}