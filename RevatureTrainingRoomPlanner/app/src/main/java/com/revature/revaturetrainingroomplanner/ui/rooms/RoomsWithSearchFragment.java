package com.revature.revaturetrainingroomplanner.ui.rooms;

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
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BatchRepository;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.RoomRepository;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsWithSearchFragment extends Fragment implements SortedListAdapter.Callback {

    private static final String[] ROOMS = new String[]{
            "Phirom",
            "Suck",
            "My",
            "Boobs"
    };

    private static final Comparator<RoomWithBatchAssignments> ALPHABETICAL_COMPARATOR = (a, b) -> a.getRoom().getRoom_name().compareTo(b.getRoom().getRoom_name());

    private List<RoomWithBatchAssignments> mModels;
    private RecyclerView mRecyclerView;
    private RoomsWithBatchAssignmentsAdapter mAdapter;
    private RoomRowBinding mBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private RoomRepository mRoomRepository;
    private BatchRepository mBatchSelected;
    private static int counter = 1;
    private long mCampusSelectedID;
    private TextView campus;
    private TextView location;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mRoomRepository = new RoomRepository(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.room_row, container, false);

        OnItemListener onItemListener = (OnItemListener) ((getParentFragment() instanceof OnItemListener) ? getParentFragment() :  getParentFragment().getParentFragment());

        View root = inflater.inflate(R.layout.fragment_rooms_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_rooms_with_search_list_rooms);
        mSearchView = root.findViewById(R.id.searchview_rooms_with_search_search_room);
        mProgressBar = root.findViewById(R.id.progressbar_rooms_with_search_progress);
        campus = root.findViewById(R.id.tv_select_building_campus);
        location = root.findViewById(R.id.tv_select_building_campus_location);

        mAdapter = new RoomsWithBatchAssignmentsAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);
        mAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        retrieveRooms();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<RoomWithBatchAssignments> filteredModelList = filter(mModels, query);
                mAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
                mRecyclerView.scrollToPosition(0);
                return true;
            }
        });
        mSearchView.setQueryHint("Look for room");


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

    private static List<RoomWithBatchAssignments> filter(List<RoomWithBatchAssignments> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<RoomWithBatchAssignments> filteredModelList = new ArrayList<>();
        for (RoomWithBatchAssignments model : models) {
            final String text = model.getRoom().getRoom_name().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public void setCampus(String campus){
        this.campus.setText(campus);

        this.location.setText("");
    }

    private void retrieveRooms() {

        mRoomRepository.retrieveAllTask().observe(getViewLifecycleOwner(), rooms -> {

            if (rooms != null) {
                List<RoomWithBatchAssignments> filteredRooms = new ArrayList<>();

                for (RoomWithBatchAssignments roomWithBatchAssignments: rooms) {
                    if (roomWithBatchAssignments.getRoom().getCampus_id() == mCampusSelectedID) {
                        filteredRooms.add(roomWithBatchAssignments);
                    }
                }

                mAdapter.edit()
                        .replaceAll(filteredRooms)
                        .commit();

                mModels = filteredRooms;
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    public void setCampusIDFilter(long campusIDFilter) {
        mCampusSelectedID = campusIDFilter;
    }
}
