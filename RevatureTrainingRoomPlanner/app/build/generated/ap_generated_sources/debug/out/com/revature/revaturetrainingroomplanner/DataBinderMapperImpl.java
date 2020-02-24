package com.revature.revaturetrainingroomplanner;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.revature.revaturetrainingroomplanner.databinding.BatchRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.BuildingRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.CampusRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.CampusWithBatchesRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.FragmentTrainersWithSearchBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.RoomRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.SkillRowBindingImpl;
import com.revature.revaturetrainingroomplanner.databinding.TrainerRowBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_BATCHROW = 1;

  private static final int LAYOUT_BUILDINGROW = 2;

  private static final int LAYOUT_CAMPUSROW = 3;

  private static final int LAYOUT_CAMPUSWITHBATCHESROW = 4;

  private static final int LAYOUT_FRAGMENTTRAINERSWITHSEARCH = 5;

  private static final int LAYOUT_ROOMROW = 6;

  private static final int LAYOUT_SKILLROW = 7;

  private static final int LAYOUT_TRAINERROW = 8;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(8);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.batch_row, LAYOUT_BATCHROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.building_row, LAYOUT_BUILDINGROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.campus_row, LAYOUT_CAMPUSROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.campus_with_batches_row, LAYOUT_CAMPUSWITHBATCHESROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.fragment_trainers_with_search, LAYOUT_FRAGMENTTRAINERSWITHSEARCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.room_row, LAYOUT_ROOMROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.skill_row, LAYOUT_SKILLROW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.revature.revaturetrainingroomplanner.R.layout.trainer_row, LAYOUT_TRAINERROW);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_BATCHROW: {
          if ("layout/batch_row_0".equals(tag)) {
            return new BatchRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for batch_row is invalid. Received: " + tag);
        }
        case  LAYOUT_BUILDINGROW: {
          if ("layout/building_row_0".equals(tag)) {
            return new BuildingRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for building_row is invalid. Received: " + tag);
        }
        case  LAYOUT_CAMPUSROW: {
          if ("layout/campus_row_0".equals(tag)) {
            return new CampusRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for campus_row is invalid. Received: " + tag);
        }
        case  LAYOUT_CAMPUSWITHBATCHESROW: {
          if ("layout/campus_with_batches_row_0".equals(tag)) {
            return new CampusWithBatchesRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for campus_with_batches_row is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTRAINERSWITHSEARCH: {
          if ("layout/fragment_trainers_with_search_0".equals(tag)) {
            return new FragmentTrainersWithSearchBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_trainers_with_search is invalid. Received: " + tag);
        }
        case  LAYOUT_ROOMROW: {
          if ("layout/room_row_0".equals(tag)) {
            return new RoomRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for room_row is invalid. Received: " + tag);
        }
        case  LAYOUT_SKILLROW: {
          if ("layout/skill_row_0".equals(tag)) {
            return new SkillRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for skill_row is invalid. Received: " + tag);
        }
        case  LAYOUT_TRAINERROW: {
          if ("layout/trainer_row_0".equals(tag)) {
            return new TrainerRowBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for trainer_row is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "model");
      sKeys.put(2, "room");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(8);

    static {
      sKeys.put("layout/batch_row_0", com.revature.revaturetrainingroomplanner.R.layout.batch_row);
      sKeys.put("layout/building_row_0", com.revature.revaturetrainingroomplanner.R.layout.building_row);
      sKeys.put("layout/campus_row_0", com.revature.revaturetrainingroomplanner.R.layout.campus_row);
      sKeys.put("layout/campus_with_batches_row_0", com.revature.revaturetrainingroomplanner.R.layout.campus_with_batches_row);
      sKeys.put("layout/fragment_trainers_with_search_0", com.revature.revaturetrainingroomplanner.R.layout.fragment_trainers_with_search);
      sKeys.put("layout/room_row_0", com.revature.revaturetrainingroomplanner.R.layout.room_row);
      sKeys.put("layout/skill_row_0", com.revature.revaturetrainingroomplanner.R.layout.skill_row);
      sKeys.put("layout/trainer_row_0", com.revature.revaturetrainingroomplanner.R.layout.trainer_row);
    }
  }
}
