package com.revature.revaturetrainingroomplanner.ui.batches;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.databinding.FragmentBatchInfoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatchInfo extends Fragment {

    private Batch mBatchSelected;
    private TextView skills;
    private TextView s_date;
    private TextView e_date;
    private TextView status;
    private TextView number;
    private FragmentBatchInfoBinding mBinding;

    public BatchInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_batch_info, container, false);

        mBinding = FragmentBatchInfoBinding.inflate(inflater);

        mBatchSelected = BatchInfoArgs.fromBundle(getArguments()).getBatchSelected();
        skills = root.findViewById(R.id.tv_batchinfo_skills);
        s_date = root.findViewById(R.id.tv_batchinfo_startdate);
        status = root.findViewById(R.id.tv_batchinfo_status);
        e_date = root.findViewById(R.id.tv_batchinfo_enddate);
        number = root.findViewById(R.id.tv_batchinfo_associates);
        TextView name = root.findViewById(R.id.tv_batchinfo_label_name);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_batch_info_skills);
//        String b_skills = "";
//        for(int i=0; i< mBatchSelected.getSkills_required().size(); i++){
//            b_skills = b_skills.concat(mBatchSelected.getSkills_required().get(i))+"\n";
//        }

        mBinding.setBatch(mBatchSelected);
        mBinding.tvBatchinfoLabelName.setTransitionName(getString(R.string.transition_batch_to_batch_info_name, mBatchSelected.getBatch_id()));
//        mBinding.tvBatchinfoLabelName.setText(mBatchSelected.getBatch_name());

        if(mBatchSelected.isIs_assigned()) {
            status.setText("Assigned");
        } else {
            status.setText("Not assigned");
        }
        s_date.setText(dateFormat(mBatchSelected.getStart_date()));
        e_date.setText(dateFormat(mBatchSelected.getEnd_date()));
        number.setText(Integer.toString(mBatchSelected.getAssociates()));
        String[] skillArr = mBatchSelected.getBatch_name().split("_");
        skills.setText(skillArr[skillArr.length-1].substring(0, 1) + skillArr[skillArr.length-1].substring(1).toLowerCase());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mBatchSelected.getSkillsAdapter());

        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.setBatch(mBatchSelected);
    }

    private String dateFormat(String start_date) {
        return start_date.substring(0,2) + "/" + start_date.substring(2,4)+"/"+start_date.substring(4);
    }


}
