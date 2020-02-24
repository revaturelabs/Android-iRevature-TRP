package com.revature.revaturetrainingroomplanner.ui.rooms;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.revature.revaturetrainingroomplanner.R;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.model.RoomWithBatchAssignments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomInfoFragment extends Fragment implements View.OnClickListener {

    private RoomWithBatchAssignments mRoomSelected;
    private BatchAssignment mBatchAssignment;
    private Button assignBtn;
    private NavController mNavController;
    private MCalendarView calendarView;
    public RoomInfoFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_room_info, container, false);

        mNavController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment);
        calendarView = rootView.findViewById(R.id.calendarView);
        assignBtn = rootView.findViewById(R.id.btn_room_info_assign);
        if(!RoomInfoFragmentArgs.fromBundle(getArguments()).getDisplayButton()) {
            calendarView.setClickable(false);
            assignBtn.setVisibility(View.GONE);
        }
        assignBtn.setOnClickListener(this);

        mRoomSelected = RoomInfoFragmentArgs.fromBundle(getArguments()).getRoomSelected();
        mBatchAssignment = getArguments().getParcelable("batchAssignment");


        TextView maxSeats = rootView.findViewById(R.id.tv_room_info_seats);
        String max = "Max seats: " + mRoomSelected.getRoom().getOccupancy();
        maxSeats.setText(max);

        String roomNumber = "Room Number: " + mRoomSelected.getRoom().getRoom_name();
        TextView room = rootView.findViewById(R.id.tv_room_info_header);
        room.setText(roomNumber);

        if(mBatchAssignment!=null) {
            calendarView.setOnDateClickListener(new OnDateClickListener() {
                @Override
                public void onDateClick(View view, DateData date) {
                    // check if date is already booked
                    LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
                    LocalDate endDate = localDate.plusWeeks(12);
                    DateData endDateData = new DateData(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth());

                    if (calendarView.getMarkedDates().check(date).getColor() == Color.RED)
                        Snackbar.make(view, "Start date unavailable", Snackbar.LENGTH_SHORT).show();
                    else if (calendarView.getMarkedDates().check(endDateData).getColor() == Color.RED) {
                        Snackbar.make(view, "End date unavailable", Snackbar.LENGTH_SHORT).show();
                    } else {
                        // date format thats in API
                        //String dateStr = date.getMonthString() + date.getDayString() + date.getYear();
                        String dateNiceString = "Start date selected: " + date.getMonthString() + "/" + date.getDayString() + "/" + date.getYear();
                        Snackbar.make(view, dateNiceString, Snackbar.LENGTH_SHORT).show();

                        mBatchAssignment.setStart_date(localDate.toString());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            // assume room gets booked for 12 weeks of training
                            localDate = localDate.plusWeeks(12);
                        }
                        mBatchAssignment.setEnd_date(localDate.toString());
                    }
                }
            });
        }
        markCalender();

        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        mBatchAssignment.setRoom_id(mRoomSelected.getRoom().getRoom_id());
        Bundle args = new Bundle();
        args.putParcelable("batchAssignment", mBatchAssignment);
        args.putLong("campusID", mRoomSelected.getRoom().getCampus_id());
        mNavController.navigate(R.id.action_nav_room_info_to_nav_trainers, args);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void markCalender() {
        ArrayList<DateData> dates = new ArrayList<>();
        // TODO: filter date marking for room availability
        LocalDate date = LocalDate.now();

        // set everyday available except in the past
        for (int j = 1; j < 32; j++) {
            for (int k = 1; k < 13; k++) {
                if ((k > date.getMonthValue()) || (k == date.getMonthValue() && j > date.getDayOfMonth())){
                    dates.add(new DateData(2020, k, j).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, Color.GREEN)));
                    dates.add(new DateData(2019, k, j).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, Color.GREEN)));

                }
                else {
                    dates.add(new DateData(2020, k, j).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, Color.RED)));
                    dates.add(new DateData(2019, k, j).setMarkStyle(new MarkStyle(MarkStyle.BACKGROUND, Color.GREEN)));

                }

            }
        }

        for (int i = 0; i < dates.size(); i++) {
            calendarView.markDate(dates.get(i));
        }
    }

}
