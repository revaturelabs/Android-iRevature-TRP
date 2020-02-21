package com.revature.revaturetrainingroomplanner.ui.batches;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.Batch;

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

    public BatchInfo() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_batch_info, container, false);
        mBatchSelected = BatchInfoArgs.fromBundle(getArguments()).getBatchSelected();
        skills = view.findViewById(R.id.tv_batchinfo_skills);
        s_date = view.findViewById(R.id.tv_batchinfo_startdate);
        status = view.findViewById(R.id.tv_batchinfo_status);
        e_date = view.findViewById(R.id.tv_batchinfo_enddate);
        number = view.findViewById(R.id.tv_batchinfo_associates);
        TextView name = view.findViewById(R.id.tv_batchinfo_label_name);
//        String b_skills = "";
//        for(int i=0; i< mBatchSelected.getSkills_required().size(); i++){
//            b_skills = b_skills.concat(mBatchSelected.getSkills_required().get(i))+"\n";
//        }

        name.setText(mBatchSelected.getBatch_name());
        if(mBatchSelected.isIs_assigned()) {
            status.setText("Assigned");
        } else {
            status.setText("Not assigned");
        }
        s_date.setText(dateFormat(mBatchSelected.getStart_date()));
        e_date.setText(dateFormat(mBatchSelected.getEnd_date()));
        number.setText(Integer.toString(mBatchSelected.getAssociates()));
        String[] skillArr = mBatchSelected.getBatch_name().split("_");
        skills.setText(skillArr[skillArr.length-1].toLowerCase());

        return view;

    }

    private String dateFormat(String start_date) {
        return start_date.substring(0,2) + "/" + start_date.substring(2,4)+"/"+start_date.substring(4);
    }


}
