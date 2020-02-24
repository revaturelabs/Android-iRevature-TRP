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
import com.revature.revaturetrainingroomplanner.data.model.BuildingWithRooms;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BatchRepository;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.BuildingRepository;
import com.revature.revaturetrainingroomplanner.databinding.BuildingRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.BuildingWithRoomsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsWithBatchAssignmentsAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsWithSearchFragment extends Fragment implements SortedListAdapter.Callback,BuildingWithRoomsAdapter.OnItemListener {

    private static final Comparator<BuildingWithRooms> BUILDING_WITH_ROOMS_COMPARATOR = (a, b) -> a.getBuilding().getBuilding_name().compareTo(b.getBuilding().getBuilding_name());
    private static final Comparator<RoomWithBatchAssignments> ROOM_WITH_BATCH_ASSIGNMENTS_COMPARATOR = (a, b) -> a.getRoom().getRoom_name().compareTo(b.getRoom().getRoom_name());

    private List<BuildingWithRooms> mModels;
    private RecyclerView mRecyclerView;
    private BuildingWithRoomsAdapter mAdapter;
    private BuildingRowBinding mBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private BuildingRepository mBuildingRepository;
    private BatchRepository mBatchSelected;
    private static int counter = 1;
    private long mCampusSelectedID;
    private TextView campus;
    private TextView location;
    private OnItemListener mOnItemListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mBuildingRepository = new BuildingRepository(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.building_row, container, false);

        mOnItemListener = (OnItemListener) ((getParentFragment() instanceof OnItemListener) ? getParentFragment() :  getParentFragment().getParentFragment());

        View root = inflater.inflate(R.layout.fragment_rooms_with_search, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerview_rooms_with_search_list_rooms);
        mSearchView = root.findViewById(R.id.searchview_rooms_with_search_search_room);
        mProgressBar = root.findViewById(R.id.progressbar_rooms_with_search_progress);
        campus = root.findViewById(R.id.tv_select_building_campus);
        location = root.findViewById(R.id.tv_select_building_campus_location);

        mAdapter = new BuildingWithRoomsAdapter(getContext(), BUILDING_WITH_ROOMS_COMPARATOR, this);
        mAdapter.addCallback(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        retrieveBuildingsAndRooms();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                final List<BuildingWithRooms> filteredModelList = filter(mModels, query);
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

    private static List<BuildingWithRooms> filter(List<BuildingWithRooms> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<BuildingWithRooms> filteredModelList = new ArrayList<>();
        for (BuildingWithRooms model : models) {
            for (RoomWithBatchAssignments room: model.getRooms()) {
                final String text = room.getRoom().getRoom_name().toLowerCase();
                if (text.contains(lowerCaseQuery)) {
                    filteredModelList.add(model);
                }
            }
        }
        return filteredModelList;
    }

    public void setCampus(String campus){
        this.campus.setText(campus);

        this.location.setText("");
    }

    private void retrieveBuildingsAndRooms() {

        mBuildingRepository.retrieveAllTask().observe(getViewLifecycleOwner(), buildings -> {

            if (buildings != null) {
                List<BuildingWithRooms> filteredBuildings = new ArrayList<>();

                for (BuildingWithRooms buildingWithRooms: buildings) {
                    if (buildingWithRooms.getBuilding().getCampus_id() == mCampusSelectedID) {
                        buildingWithRooms.getBuilding().setRoomsWithBatchAssignmentsAdapter(new RoomsWithBatchAssignmentsAdapter(getContext(), ROOM_WITH_BATCH_ASSIGNMENTS_COMPARATOR, mOnItemListener));
                        filteredBuildings.add(buildingWithRooms);
                    }
                }

                mAdapter.edit()
                        .replaceAll(filteredBuildings)
                        .commit();

                mModels = filteredBuildings;
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    public void setCampusIDFilter(long campusIDFilter) {
        mCampusSelectedID = campusIDFilter;
    }

    @Override
    public void onBuildingClick(BuildingWithRooms buildingClicked) {
        if (buildingClicked.isRoomsVisible()) {
            buildingClicked.getBuilding().getRoomsWithBatchAssignmentsAdapter().edit()
                    .removeAll()
                    .commit();

            buildingClicked.setRoomsVisible(false);
        } else {
            buildingClicked.getBuilding().getRoomsWithBatchAssignmentsAdapter().edit()
                    .replaceAll(buildingClicked.getRooms())
                    .commit();

            buildingClicked.setRoomsVisible(true);
        }
    }

}
