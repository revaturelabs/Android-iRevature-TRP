package com.revature.revaturetrainingroomplanner.ui.campuses;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.CampusModel;
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

    private static final Comparator<CampusModel> ALPHABETICAL_COMPARATOR = (a, b) -> a.getText().compareTo(b.getText());

//    private CampusesViewModel campusesViewModel;
    private List<CampusModel> mModels;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CampusesAdapter mAdapter;
    private CampusRowBinding mBinding;
    private Animator mAnimator;
    private SearchView searchView;
    private ProgressBar mProgressBar;
    private OnItemListener mOnItemListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        campusesViewModel =
//                ViewModelProviders.of(this).get(CampusesViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_campuses, container, false);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.campus_row, container, false);

        mOnItemListener = (OnItemListener) getParentFragment();

        View root = inflater.inflate(R.layout.fragment_campuses_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_campuses_with_search_list_campuses);
        searchView = root.findViewById(R.id.searchview_campuses_with_search_search_campus);
        mProgressBar = root.findViewById(R.id.progressbar_campuses_with_search_progress);

        mAdapter = new CampusesAdapter(getContext(), ALPHABETICAL_COMPARATOR, mOnItemListener);

        mAdapter.addCallback(this);

        layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mModels = new ArrayList<>();
        int id = 0;
        for (String campus: CAMPUSES) {
            mModels.add(new CampusModel(id, campus));
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
                final List<CampusModel> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });
        searchView.setQueryHint("Look for campus");

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

    private static List<CampusModel> filter(List<CampusModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<CampusModel> filteredModelList = new ArrayList<>();
        for (CampusModel model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}