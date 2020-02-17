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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Room;
import com.revature.revaturetrainingroomplanner.data.persistence.repository.RoomRepository;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBinding;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter;
import com.revature.revaturetrainingroomplanner.ui.adapter.RoomsAdapter.OnItemListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsWithSearchFragment extends Fragment implements SortedListAdapter.Callback, View.OnClickListener {

    private static final String[] ROOMS = new String[]{
            "Phirom",
            "Suck",
            "My",
            "Boobs"
    };

    private static final Comparator<Room> ALPHABETICAL_COMPARATOR = (a, b) -> a.getText().compareTo(b.getText());

    private List<Room> mModels;
    private RecyclerView mRecyclerView;
    private RoomsAdapter mAdapter;
    private RoomRowBinding mBinding;
    private Animator mAnimator;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private RoomRepository mRoomRepository;
    private static int counter = 1;

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
        root.findViewById(R.id.btn_rooms_with_search_add_fake_data).setOnClickListener(this);
        root.findViewById(R.id.btn_rooms_with_search_clear_fake_data).setOnClickListener(this);

        mAdapter = new RoomsAdapter(getContext(), ALPHABETICAL_COMPARATOR, onItemListener);

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
                final List<Room> filteredModelList = filter(mModels, query);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_rooms_with_search_add_fake_data: {
                insertFakeData(new Room("Fake Room #" + counter));
                counter++;
            }
            break;

            case R.id.btn_rooms_with_search_clear_fake_data: {
                clearFakeData();
            }
            break;

            default:

        }
    }

    private static List<Room> filter(List<Room> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Room> filteredModelList = new ArrayList<>();
        for (Room model : models) {
            final String text = model.getText().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private void retrieveRooms() {

        mRoomRepository.retrieveAllTask().observe(getViewLifecycleOwner(), rooms -> {
            if (rooms != null) {
                mModels = rooms;
                mAdapter.edit()
                        .replaceAll(rooms)
                        .commit();
            } else {
                mModels = new ArrayList<>();
            }
        });
    }

    public void insertFakeData(Room room) {
        mRoomRepository.insertRoomTask(room);
    }

    public void clearFakeData() {
        mRoomRepository.deleteAllTask(new Room(""));
    }
}
