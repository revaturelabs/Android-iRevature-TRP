package com.revature.revaturetrainingroomplanner.ui.lookup;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.ui.adapter.CampusesAdapter.OnItemListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookupFragment extends Fragment implements OnItemListener {


    public LookupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lookup, container, false);
    }

    @Override
    public void onItemClick(int position) {

    }
}
